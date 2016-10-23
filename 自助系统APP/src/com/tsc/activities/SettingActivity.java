package com.tsc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * 软件设置的Activity类
 *
 */
public class SettingActivity  extends Activity{

	private TextView returnTextView;
	private Button exitLoginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		initView();
	}
	
	public void initView(){
		returnTextView = (TextView)findViewById(R.id.activity_setting_back);
		returnTextView.setOnClickListener(new ReturnBtnClass());
		
		exitLoginButton = (Button)findViewById(R.id.exit_login_btn);
		exitLoginButton.setOnClickListener(new ReturnBtnClass());
	}
	
	class ReturnBtnClass implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
	
	

}
