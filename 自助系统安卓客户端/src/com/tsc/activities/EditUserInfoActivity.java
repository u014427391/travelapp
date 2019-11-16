package com.tsc.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.tsc.Constant;
import com.tsc.entity.Tourist;
import com.tsc.service.SyncHttp;

/**
 * 编辑用户资料的Activity类
 */
public class EditUserInfoActivity extends Activity{

	private TextView returnTextView;
	
	private TextView tv_account;
	
	private TextView tv_password;
	
	private TextView tv_sex;
	
	private TextView tv_city;
	
	private Tourist tourist;
	
	private String account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_edit_userinfo);
		
		initView();
	}

	public void initView(){
		returnTextView = (TextView)findViewById(R.id.mimedetail_back);
		returnTextView.setOnClickListener(new ReturnBtnClass());

		account = getIntent().getStringExtra("account");
		
		getInfo(account);
		
		tv_account = (TextView)findViewById(R.id.account);
		tv_password = (TextView)findViewById(R.id.password);
		tv_sex = (TextView)findViewById(R.id.sex);
		tv_city = (TextView)findViewById(R.id.city);
		
//		tv_account.setText(tourist.getAccount());
//		tv_password.setText(tourist.getPassword());
//		tv_sex.setText(tourist.getSex());
//		tv_city.setText(tourist.getCity());
		
	}
	
	class ReturnBtnClass implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
	
	public void getInfo(String account){
		//同步加载的Service类
		SyncHttp syncHttp = new SyncHttp();
		try {
			String retStr = syncHttp.httpGet(Constant.URL_Login, account);
			JSONObject jsonObject = new JSONObject(retStr);
			int retCode = jsonObject.getInt("ret");
			if(retCode == 0){
				JSONObject dataObj = jsonObject.getJSONObject("data");
				
				JSONObject infoObj = dataObj.getJSONObject("touristInfo");
				
//				tourist.setAccount(infoObj.getString("account"));
//				tourist.setPassword(infoObj.getString("password"));
//				tourist.setSex(infoObj.getString("sex"));
//				tourist.setCity(infoObj.getString("city"));
//				tourist.setCredits(infoObj.getInt("credits"));
//				tourist.setRank(infoObj.getString("rank"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
