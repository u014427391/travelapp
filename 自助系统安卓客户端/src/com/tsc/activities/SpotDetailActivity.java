package com.tsc.activities;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.tsc.Constant;

/**
 * 
 * 景点详细信息的Activity
 * 数据是从主界面传过来，不用再次进行服务器访问，提供系统性能
 *
 */
public class SpotDetailActivity extends Activity{

	private RelativeLayout rl_spotPic;//RelativeLayout用来显示景点图片
	private TextView tv_position;//显示景点位置的TextView
	private TextView tv_introduce;//显示景点介绍的TextView
	
	private TextView tv_black;//返回TextView,点击后,返回
	
	private HashMap<String,Object> map;//获取从主界面传过来的景点信息
	
	private Context context;//Context
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_spot_detail);
		initView();
	}
	
	
	/**
	 * 初始化操作
	 */
	@SuppressWarnings("unchecked")
	public void initView(){
		context = this;
		
		rl_spotPic = (RelativeLayout)findViewById(R.id.rl_spotPic);
		tv_position = (TextView)findViewById(R.id.tv_position);
		tv_introduce = (TextView)findViewById(R.id.tv_intro);
		
		//提供HashMap获取数据
		map = (HashMap<String, Object>) getIntent().getSerializableExtra("map");
		
		/**Volley框架进行网络图片访问**/
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		
		ImageRequest imageRequest = new ImageRequest(
				Constant.URL_WEB_SERVER+map.get("picture").toString(),
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						//设置一下RelativeLayout的背景图片，用景点的图片进行填充
						rl_spotPic.setBackgroundDrawable(new BitmapDrawable(response));
					}//参数3、4表示图片宽高,Bitmap.Config.ARGB_8888表示图片每个像素占据4个字节大小
				},0,0, Config.ARGB_8888, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// 设置加载失败后的默认图片
						rl_spotPic.setBackgroundResource(R.drawable.ic_pic_normal);
					}
				});
		requestQueue.add(imageRequest);
		
		//加数据显示到TextView
		tv_position.setText(map.get("position").toString());
		tv_introduce.setText(Html.fromHtml(map.get("introduction").toString()));
		
		//返回TextView的操作
		tv_black = (TextView)findViewById(R.id.tv_black);
		
		tv_black.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SpotDetailActivity.this.finish();
			}
		});
	}
	
	
}
