package com.tsc.activities;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity{
	
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		//隔一秒后自动跳到登录界面
		  intent = new Intent(this, MainActivity.class); //登录界面
		 Timer timer = new Timer();
		 TimerTask task = new TimerTask() {
			   @Override
			   public void run() {
			    startActivity(intent); //ִ启动Activity
			   }
		  };
		timer.schedule(task, 1000 * 3); //3秒
	}

}
