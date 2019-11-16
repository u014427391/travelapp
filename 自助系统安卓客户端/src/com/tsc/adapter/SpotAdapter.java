package com.tsc.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tsc.Constant;
import com.tsc.activities.R;

/**
 * 
 * 景点信息的适配器
 *
 */
public class SpotAdapter extends BaseAdapter{

	LayoutInflater layoutInflater;
	Context context;
	private List<HashMap<String,Object>> datas;//获取的数据,放在数组列表里，泛型的应用

	public SpotAdapter(Context context,List<HashMap<String,Object>> datas){
		layoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.datas = datas;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if(convertView == null){
			convertView = layoutInflater.inflate(R.layout.item_spot_list, parent, false );
			viewHolder = new ViewHolder();//视图控制器
			viewHolder.iv_spotPic = (ImageView)convertView.findViewById(R.id.spot_iv);
			viewHolder.tv_spotName = (TextView)convertView.findViewById(R.id.spot_name_tv);
			viewHolder.tv_spotIntro = (TextView)convertView.findViewById(R.id.spot_intro_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		/**Volley框架加载网络图片**/
		
		//创建一个RequestQueue
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		//创建ImageRequest
		ImageRequest imageRequest = new ImageRequest(
				//服务器加载图片的URL，传过来的是图片路径,Constant里保存的是服务器网络路径
				Constant.URL_WEB_SERVER+datas.get(position).get("picture").toString(),
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						//加载成功，显示url访问到的图片
						viewHolder.iv_spotPic.setImageBitmap(response);
					}//0表示不调整图片的最大宽高,Bitmap.ARGB表示，每个图片像素占用4个字节
				},0,0, Config.ARGB_8888, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						//加载失败就显示默认图片
						viewHolder.iv_spotPic.setImageResource(R.drawable.ic_normal_pic);
					}
				});
		//将ImageRequest添加到RequestQueue
		requestQueue.add(imageRequest);
		//景点名称添加到TextView
		viewHolder.tv_spotName.setText(datas.get(position).get("tour_project").toString());
		
		String getString = datas.get(position).get("introduction").toString();
		//字符串筛选,多出的以"..."代替
		if(getString.length()>40){
			getString = getString.substring(0, 40)+"...";
		}
		viewHolder.tv_spotIntro.setText(Html.fromHtml(getString));
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_spotPic;//景点图片
		TextView tv_spotName;//项目名称
		TextView tv_spotIntro;//景点介绍
	}

}
