package com.tsc.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsc.Constant;
import com.tsc.encoder.AESEncryptor;
import com.tsc.service.LoginCheckService;

/**
 * 登录验证的Activity类
 *
 */
public class LoginActivity extends Activity implements OnClickListener{

	//注册按钮
	private Button registerButton;
	
	//登录按钮
	private Button loginButton;
	
	//返回的TextView
	private TextView returnButton;
	
	//账号文本框
	private EditText accountEditText;
	//密码文本框
	private EditText passwordEditText;
	
	//账号
	private String account;
	//密码
	private String password;
	
	private String accountValue;
	private String passwordValue;
	
	//进度条
	private ProgressDialog pd = null;
	
	//记住账号的CheckBox
	private CheckBox savedAccountCheckBox;
	//自动登录的CheckBox
	private CheckBox autoLoginCheckBox;
	
	//SharePreferences对象，用于记住账号
	private SharedPreferences sp;
	
	//记住账号的标志常数
	private final String MAK = "innoview";
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		//账号的文本框
		accountEditText = (EditText)this.findViewById(R.id.et_account);
		//密码的文本框
		passwordEditText = (EditText)this.findViewById(R.id.et_password);
		//保存账号的CheckBox
		savedAccountCheckBox = (CheckBox)findViewById(R.id.cb_savedAccount);
		//自动登录的CheckBox
		autoLoginCheckBox = (CheckBox)findViewById(R.id.cb_autoLogin);
		//获取保存在SharePreferences里面的账号信息，实现自动登录
		sp = getSharedPreferences("accountInfo",Context.MODE_WORLD_READABLE);
		
		if(sp.getBoolean("ISCHECK", false)){
			
			savedAccountCheckBox.setChecked(true);
			
			try{
				 accountValue = sp.getString("ACCOUNTVALUE","");
				 System.out.println("<<<<<<<<<<<<"+"加密后的账号"+accountValue);
                 account= AESEncryptor.decrypt(MAK, accountValue);
                 System.out.println("<<<<<<<<<<<<"+"解密后的账号"+account);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			accountEditText.setText(account);
			
			try{
				 passwordValue = sp.getString("PASSWORDVALUE","");
				 System.out.println("<<<<<<<<<<<<"+"加密后的密码"+passwordValue);
                password= AESEncryptor.decrypt(MAK, passwordValue);
                System.out.println("<<<<<<<<<<<<"+"解密后的密码"+password);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			passwordEditText.setText(password);
			
			if(sp.getBoolean("AUTO_ISCHECK", false)){
				 autoLoginCheckBox.setChecked(true);
				 initLogin();
		    }
		}
		
		savedAccountCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
			if (savedAccountCheckBox.isChecked()) {
			                     
				System.out.println("记住账号框未选中状态");
				sp.edit().putBoolean("ISCHECK", true).commit();

			}else {

				System.out.println("记住账号框未选中");
				sp.edit().putBoolean("ISCHECK", false).commit();

			}

			}
		});
		
		autoLoginCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	        @Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
	        	if (autoLoginCheckBox.isChecked()) {
	        		System.out.println("自动登录功能以启用");
	        		sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

	        	} else {
	        		System.out.println("自动登录已关闭");
	        		sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
	        	}
	        }
		});
		
		initView();
	}
	
	/**
	 * 初始化View
	 */
	public void initView(){
		registerButton = (Button)findViewById(R.id.bt_register);
		registerButton.setOnClickListener(this);
		
		loginButton = (Button)findViewById(R.id.bt_login);
		loginButton.setOnClickListener(this);
		
		returnButton = (TextView)findViewById(R.id.mimedetail_back);
		returnButton.setOnClickListener(this);
	}

	/**
	 * 登录验证
	 */
	public void initLogin(){
		//显示进度条
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setMessage("数据加载...");
		pd.show();
		
		account = accountEditText.getText().toString();
		password = passwordEditText.getText().toString();
		
		if(savedAccountCheckBox.isChecked())
		{
		 
		 Editor editor = sp.edit();

		    try {
	            editor.putString("ACCOUNTVALUE", AESEncryptor.encrypt(MAK,account));
		        System.out.println("<<<<<<<<"+"加密后的账号"+AESEncryptor.encrypt(MAK,account));
		    } catch (Exception e) {
		        Toast.makeText(LoginActivity.this,"账号加密异常",Toast.LENGTH_SHORT).show();
		        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		    }
		    
		    try {
	            editor.putString("PASSWORDVALUE", AESEncryptor.encrypt(MAK,password));
		        System.out.println("<<<<<<<<"+"加密后的密码"+AESEncryptor.encrypt(MAK,password));
		    } catch (Exception e) {
		        Toast.makeText(LoginActivity.this,"密码加密异常",Toast.LENGTH_SHORT).show();
		        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		    }
		    
		   editor.commit();
		}
		new LoginAsyncTask().execute(Constant.URL_Login,account,password);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			initLogin();
			break;
	
		case R.id.bt_register:
			Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent); 
			break;
		
		case R.id.mimedetail_back:
			LoginActivity.this.finish();
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
	class LoginAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			
			LoginCheckService aw = new LoginCheckService();
			String str = params[1];
			try{
			str = aw.loginCheck(params[0], str, params[2]);
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
				pd.dismiss();
			}else if(result.equals("networderror")){
				Toast.makeText(getApplicationContext(), "网络异常", Toast.LENGTH_LONG).show();
				pd.dismiss();
			}else{
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				account = accountEditText.getText().toString();
				intent.putExtra("account", result);
				startActivity(intent);
				Constant.LoginMsg.isLogin = true;
				pd.dismiss();
				LoginActivity.this.finish();
				Toast.makeText(getApplicationContext(), "登录成功!", 1000).show();
				
			}
		}
	}
		
	

}
