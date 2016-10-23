package com.tsc.activities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tsc.Constant;
import com.tsc.entity.Parameter;
import com.tsc.service.LineJsonParse;
import com.tsc.service.SyncHttp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpecifiedLineActivity extends Activity {

	static List<Map<String, Object>> listdata = null;
	private PullToRefreshListView mListView = null;
	private Context mContext=null;
	RequestQueue mQueue = null;
	int page=0;
	public static boolean HaveReadAll=false;
	LineAdapter lineadapter=null;
	TextView tv_back;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.line_listview_select);

		mContext=this;
		//        videoinfo=MainActivity.listdata.get(getIntent().getExtras().getInt("position"));

		mQueue=Volley.newRequestQueue(this); 
		loadView();

		UpdateList(1);
		//        if (LoginState.isLogin==true) {
		//        	AddBrowse();
		//		}

	}

	public void loadView(){

		mListView = (PullToRefreshListView) findViewById(R.id.listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LineDetailActivity.map_data=(Map<String, Object>) parent.getAdapter().getItem(position);
				Intent Intent = new Intent(mContext, LineDetailActivity.class);
				startActivity(Intent);
			}
		});

		tv_back=(TextView)findViewById(R.id.tv_back);
		tv_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});



		//设定下拉或上拉监听函数  
		mListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {

				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				Log.e("HaveReadAll", HaveReadAll+"");
				
				if(HaveReadAll==false){
					UpdateList(2);
				}else {
					Toast.makeText(mContext, "已无更多信息", 0).show();
					Message message=new Message();
					message.what=006;
					handler.sendMessage(message);
					//					mListView.onRefreshComplete();  
				}
			}
		});




		mListView.setMode(Mode.PULL_FROM_END);//向下拉刷新
		// mPullRefreshListView.setMode(Mode.PULL_FROM_START);//向上拉刷新


	}



	Handler handler = new Handler()  {
		@Override
		public void handleMessage(Message msg) {  
			super.handleMessage(msg);  
			if(msg.what == 004) { 
				lineadapter=new LineAdapter();
				mListView.setAdapter(lineadapter);
				//adapter.notifyDataSetChanged();
			}else if(msg.what == 005) { 
				lineadapter.notifyDataSetChanged();
				mListView.onRefreshComplete();  
			}else if(msg.what == 006) { 
				mListView.onRefreshComplete();  
			}
		}  
	};  


	void UpdateList(final int state){
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Looper.prepare(); 

				String spotName=getIntent().getExtras().getString("spotName");
				

				page++;
//				String PATH=Constant.URL_WEB_SERVER +"/GetSpecifiedLineServlet?spotName="+spotName+"&page="+page;
				List<Parameter> param = new ArrayList<Parameter>();
				param.add(new Parameter("spotName",spotName));
				param.add(new Parameter("page",Integer.toString(page)));
				
				SyncHttp syncHttp = new SyncHttp();
				String result="";
				try {
					result = syncHttp.httpPost(Constant.URL_WEB_SERVER +"/GetSpecifiedLineServlet", param);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				try {
					Message message=new Message();
					if (state==1){ //开启Activity时加载数据
						listdata=LineJsonParse.getList2(result,"SpecifiedLineActivity");
						message.what=004;
					}else{//下拉刷新加载更多数据
						listdata=LineJsonParse.UpdateList2(result, listdata,"SpecifiedLineActivity");
						message.what=005;
					}
					handler.sendMessage(message);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(),"无法连接服务器",1000).show();
					e.printStackTrace();
				}
				Looper.loop();
			}

		}).start(); 

	}

	public class BitmapCache implements ImageCache {  

		private LruCache<String, Bitmap> mCache;  

		public BitmapCache() {  
			int maxSize = 10 * 1024 * 1024;  
			mCache = new LruCache<String, Bitmap>(maxSize) {  
				@Override  
				protected int sizeOf(String key, Bitmap bitmap) {  
					return bitmap.getRowBytes() * bitmap.getHeight();  
				}  
			};  
		}  
		@Override
		public Bitmap getBitmap(String url) {  
			return mCache.get(url);  
		}  
		@Override
		public void putBitmap(String url, Bitmap bitmap) {  
			mCache.put(url, bitmap);  
		}  

	}  






	class LineAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return listdata.size();
		}

		@Override
		public Map getItem(int position) {
			return listdata.get(position);			
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {			

			ViewHolder holder = null;	


			//			if (convertView == null) {  
			holder=new ViewHolder();    
			convertView = View.inflate(getApplicationContext(), R.layout.line_item, null);  
			holder.tv_name1 = (TextView) convertView.findViewById(R.id.txt_title);
			holder.tv_name2 = (TextView) convertView.findViewById(R.id.txt_address);
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			//				convertView.setTag(holder);  
			//			}else {  
			//				holder = (ViewHolder)convertView.getTag();  
			//			}  
			Double price=Double.parseDouble((String)(getItem(position).get("price")));
			DecimalFormat    df   = new DecimalFormat("######0");//设置不显示小数位   
			holder.tv_name1.setText("¥"+df.format(price));
			holder.tv_name2.setText((String)(getItem(position).get("name")));



			ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());  

			ImageListener listener = ImageLoader.getImageListener(holder.imageView,  
					R.drawable.defaultcovers, R.drawable.defaultcovers);  

			String picture=(String)getItem(position).get("picture");
			String []array=picture.split(";");
			String picture_0=array[0];

			imageLoader.get(Constant.URL_WEB_SERVER +picture_0,  
					listener);  

			return convertView;			
		}

		class ViewHolder {
			TextView tv_name1;
			TextView tv_name2;
			ImageView imageView;
		}
	}





}