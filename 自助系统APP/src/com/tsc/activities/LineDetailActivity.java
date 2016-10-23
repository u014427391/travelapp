package com.tsc.activities;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.tsc.Constant;
import com.tsc.entity.PagerItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class LineDetailActivity extends Activity {

	private Context mContext=null;
	RequestQueue mQueue = null;
	private ViewPager viewPager;
	private LinearLayout dot_layout;
	private TextView tv_picture_title;
	private TextView tv_back;
	public static Map<String, Object> map_data=new HashMap<String, Object>();
	private ArrayList<PagerItem> list = new ArrayList<PagerItem>();
	private ImageHandler handler = new ImageHandler(new WeakReference<LineDetailActivity>(this));  
	TextView tv_catering,tv_accommodation,tv_contact,tv_tourism_time,tv_traffic;
	TextView tv_price2,tv_go_to_pay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.line_detail);
		mContext=this;
//		map_data=LineActivity.listdata.get(getIntent().getExtras().getInt("ID"));
		mQueue=Volley.newRequestQueue(this); 


		loadView();
	}

	public void loadView(){
		tv_catering=(TextView) findViewById(R.id.tv_catering);
		tv_accommodation=(TextView) findViewById(R.id.tv_accommodation);
		tv_contact=(TextView) findViewById(R.id.tv_contact);
		tv_tourism_time=(TextView) findViewById(R.id.tv_tourism_time);
		tv_traffic=(TextView) findViewById(R.id.tv_traffic);
		tv_price2=(TextView) findViewById(R.id.tv_price2);
		tv_go_to_pay=(TextView) findViewById(R.id.tv_go_to_pay);
		
		tv_catering.setText((String)map_data.get("catering"));
		tv_accommodation.setText((String)map_data.get("accommodation"));
		tv_contact.setText((String)map_data.get("contact"));
		tv_tourism_time.setText((String)map_data.get("tourism_time"));
		tv_traffic.setText((String)map_data.get("traffic"));
		
		final Double price=Double.parseDouble((String)map_data.get("price"));
		tv_price2.setText("¥"+price);
		
		tv_go_to_pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext,ReserveActivity.class);
				Bundle bundle =new Bundle();
				bundle.putDouble("price",price);
				bundle.putString("tourism_time", (String)map_data.get("tourism_time"));
				bundle.putInt("ID", Integer.parseInt((String)map_data.get("ID")));
				bundle.putString("name", (String)map_data.get("name"));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		tv_back=(TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		String title=(String)map_data.get("name");

		String picture=(String)map_data.get("picture");
		String []array=picture.split(";");
		for (String string : array) {
			list.add(new PagerItem(Constant.URL_WEB_SERVER +string, title));
		}

		tv_picture_title=(TextView) findViewById(R.id.tv_picture_title);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		dot_layout = (LinearLayout) findViewById(R.id.dot_layout);//标志当前第几页的小圆点

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {//此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position
				//				Log.e("Activity", "position: "+position);
				updateIntroAndDot();
				handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0)); 
			}
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。其中三个参数的含义分别为：
				//arg0 :当前页面，及你点击滑动的页面
				//arg1:当前页面偏移的百分比
				//arg2:当前页面偏移的像素位置   
			}

			//覆写该方法实现轮播效果的暂停和恢复  ：
			@Override
			public void onPageScrollStateChanged(int state) {
				//此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
				//arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
				switch (state) {  
				case ViewPager.SCROLL_STATE_DRAGGING:  //state ==1,表示正在滑动
					handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);  
					break;  
				case ViewPager.SCROLL_STATE_IDLE:  //state ==0,表示什么都没做
					handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);  
					break;  
				default:  
					break;  
				}  


			}
		});

		viewPager.setAdapter(new MyPagerAdapter());
		
		viewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界  



		initDots();//初始化小圆点
		updateIntroAndDot();

		//开始轮播效果  
		handler.sendEmptyMessage(ImageHandler.MSG_BREAK_SILENT);
	}


	private void initDots(){
		for (int i = 0; i < list.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(8, 8);
			if(i!=0){
				params.leftMargin = 5;
			}
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selector_dot);
			dot_layout.addView(view);
		}
	}


	private void updateIntroAndDot(){
		int currentPage = viewPager.getCurrentItem()%list.size();
		tv_picture_title.setText(list.get(currentPage).getTitle());

		for (int i = 0; i < dot_layout.getChildCount(); i++) {
			dot_layout.getChildAt(i).setEnabled(i==currentPage);//标志当前第几页的小圆点
		}
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





	private static class ImageHandler extends Handler{  

		/** 
		 * 请求更新显示的View。 
		 */  
		protected static final int MSG_UPDATE_IMAGE  = 1;  
		/** 
		 * 请求暂停轮播。 
		 */  
		protected static final int MSG_KEEP_SILENT   = 2;  
		/** 
		 * 请求恢复轮播。 
		 */  
		protected static final int MSG_BREAK_SILENT  = 3;  
		/** 
		 * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。 
		 * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页， 
		 * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。 
		 */  
		protected static final int MSG_PAGE_CHANGED  = 4;  

		//轮播间隔时间  
		protected static final long MSG_DELAY = 3000;  

		//使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等  
		private WeakReference<LineDetailActivity> weakReference;  
		private int currentItem = 0;  

		protected ImageHandler(WeakReference<LineDetailActivity> wk){  
			weakReference = wk;  
		}  

		@Override  
		public void handleMessage(Message msg) {  
			super.handleMessage(msg);  
			//            Log.d(LOG_TAG, "receive message " + msg.what);  
			LineDetailActivity activity = weakReference.get();  
			if (activity==null){  
				//Activity已经回收，无需再处理UI了  
				return ;  
			}  
			//检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。  
			if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)){  
				activity.handler.removeMessages(MSG_UPDATE_IMAGE);  
			}  
			switch (msg.what) {  
			case MSG_UPDATE_IMAGE:  
//				Log.e("调用到了", "handler处理信息方法");
				currentItem++;  
				activity.viewPager.setCurrentItem(currentItem);  
				//准备下次播放  
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
				break;  
			case MSG_KEEP_SILENT:  
				//只要不发送消息就暂停了  
				break;  
			case MSG_BREAK_SILENT:  
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);  
				break;  
			case MSG_PAGE_CHANGED:  
				//记录当前的页号，避免播放的时候页面显示不正确。  
				currentItem = msg.arg1;  
				break;  
			default:  
				break;  
			}   
		}  
	}  





	class MyPagerAdapter extends PagerAdapter{

		/**
		 * 返回多少page
		 */
		@Override
		public int getCount() {
			//			return list.size();
			if(list.size()==0||list.size()==1)
				return list.size();
			else
				return Integer.MAX_VALUE;  //设置成最大，使用户看不到边界，即可实现左右循环滑动
		}

		/**
		 * true: 表示不去创建，使用缓存  false:去重新创建
		 * view： 当前滑动的view
		 * object：将要进入的新创建的view，由instantiateItem方法创建
		 */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}

		/**
		 * 类似于BaseAdapger的getView方法
		 * 用了将数据设置给view
		 * 由于它最多就3个界面，不需要viewHolder
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(mContext, R.layout.pager_item, null);

			ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
			PagerItem pagerItem = list.get(position%list.size());
			//由于我们设置了count为 Integer.MAX_VALUE，因此这个position的取值范围很大很大，但我们实际要显示的内容肯定没这么多（往往只有几项），所以这里肯定会有求模操作。  

			ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());  

			ImageListener listener = ImageLoader.getImageListener(imageView,  
					R.drawable.defaultcovers, R.drawable.defaultcovers);  

			imageLoader.get(pagerItem.getPicture_url(),  listener);  

			container.addView(view);//一定不能少，将view加入到viewPager中
			//			tv_picture_title.setText(pagerItem.getTitle());
			//			final int pos=position;
			return view;


		}

		/**
		 * 销毁page
		 * position： 当前需要消耗第几个page
		 * object:当前需要消耗的page
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//			super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
	}



}