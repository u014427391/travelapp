package com.tsc.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tsc.Constant;
import com.tsc.service.RegisterService;

/**
 * 注册的Activity类
 *
 */
public class RegisterActivity extends Activity implements OnClickListener{

	//注册按钮
	private Button registerButton;
	
	//返回按钮
	private Button returnButton;
	
	//标题栏的返回按钮
	private Button tReturnButton;
	
	//账号文本框
	private EditText accountEditText;
	
	//密码文本框
	private EditText passwordEditText;
	
	//账号
	private String account;
	
	//密码
	private String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initView();
	}

	public void initView(){
		registerButton = (Button)findViewById(R.id.bt_register);
		returnButton = (Button)findViewById(R.id.bt_return);
		tReturnButton = (Button)findViewById(R.id.btn_black);
		
		//添加事件监听器
		registerButton.setOnClickListener(this);
		returnButton.setOnClickListener(this);
		tReturnButton.setOnClickListener(this);
		
		//实例化文本框
		accountEditText = (EditText)findViewById(R.id.et_account);
		passwordEditText = (EditText)findViewById(R.id.et_password);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//注册
		case R.id.bt_register:
			//点击按钮实现注册，发送请求
			account = accountEditText.getText().toString();
			password = passwordEditText.getText().toString();
			new RegisterAsyncTask().execute(Constant.URL_Register,account,password);
			break;
	    //返回按钮
		case R.id.bt_return:
			RegisterActivity.this.finish();
			break;
		//标题栏的返回按钮
		case R.id.btn_black:
			RegisterActivity.this.finish();
			break;
		default:
			break;
		}
	}

	
	/**
	*
	*简介
	AsyncTask可以使得使用UI线程变的更容易更适当，它可以在后台运行一些操作然后在UI上展现，不用操作具体的线程和handlers
	一个 asynchronous task包括三种基本类型（调用参数，进度和结果），和四个步骤（调用开始，在后台运行，处理进度，结束）
	), and most often will override a second one (onPostExecute(Result).)

	使用方法描述
	Asynchronous Task必须是作为一个子类来使用，
	task实例必须在UI线程创建
	execute(Params...)必须在UI线程调用
	不要手工调用onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...)。
	task只可以execute一次，执行多次就报异常

	*
	*/
	class RegisterAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			RegisterService aw = new RegisterService();
			String str = params[0];
			try{
			str = aw.register(str, params[1], params[2]);
			}catch(Exception e){
			   e.printStackTrace();
			   str = "networderror";
			}
			return str;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if(result.equals("error")){
				Toast.makeText(getApplicationContext(), "服务器连接失败", Toast.LENGTH_LONG).show();
			}else if(result.equals("networderror")){
				Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_LONG).show();
			}else{
				Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
				startActivity(intent);
				RegisterActivity.this.finish();
				Toast.makeText(getApplicationContext(), "注册成功!", 1000).show();
			}
		}
		
	}
	
}
