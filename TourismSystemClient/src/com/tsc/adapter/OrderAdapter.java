package com.tsc.adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
 * 订单的适配器
 *
 */
public class OrderAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private List<HashMap<String,Object>> datas;
	
	public OrderAdapter(Context context,List<HashMap<String,Object>> datas){
		inflater = LayoutInflater.from(context);
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
		final ViewHolder viewHolder ;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_order_list, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.iv_spotImage = (ImageView)convertView.findViewById(R.id.order_spot_iv);
			viewHolder.tv_spotName = (TextView)convertView.findViewById(R.id.order_spotName_tv);
			viewHolder.tv_time = (TextView)convertView.findViewById(R.id.order_time_tv);
			viewHolder.tv_price = (TextView)convertView.findViewById(R.id.order_price_tv);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}
	
		//创建一个RequestQueue对象
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		     	
	    //创建ImageRequest对象
		ImageRequest imageRequest = new ImageRequest(
				Constant.URL_WEB_SERVER+datas.get(position).get("picPath").toString(),//url
		    	new Response.Listener<Bitmap>() {//监听器Listener
					@Override
		    		public void onResponse(Bitmap response) {
		    			viewHolder.iv_spotImage.setImageBitmap(response);
		    		}
		    		//参数3、4表示图片宽高,Bitmap.Config.ARGB_8888表示图片每个像素占据4个字节大小
		    	}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {//图片加载请求失败的回调Listener
		    			@Override
		    			public void onErrorResponse(VolleyError error) {
		    				viewHolder.iv_spotImage.setImageResource(R.drawable.ic_normal_pic);
		    			}
		    	});
		//将ImageRequest加载到Queue
		 requestQueue.add(imageRequest);
		    
		viewHolder.tv_spotName.setText(datas.get(position).get("name").toString());
		viewHolder.tv_time.setText("订单时间:"+datas.get(position).get("order_time").toString());
		viewHolder.tv_price.setText("总价:￥"+datas.get(position).get("total_price").toString());
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_spotImage;//旅游景点图片的ImageView
		TextView tv_spotName;//旅游线路名称的TextView
		TextView tv_time;//下单时间的TextView
		TextView tv_price;//出游时间的TextView
		
	}

}
