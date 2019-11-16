package com.tsc.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsc.Constant;
import com.tsc.fragment.CalendarFragment;
import com.tsc.service.AddOrderService;

/**
 * 预定旅游服务的Activity类
 *  
 */
public class ReserveActivity extends Activity implements OnClickListener{
	
	private TextView tv_back;//返回的TextView
	private TextView tv_price;//单价的TextView
	private TextView tv_total_price;//总价的TextView
	private TextView tv_clear;//清空的TextView
	private TextView tv_go_to_pay;//去支付的TextView
	private TextView tv_intro;//显示线路名称的TextView
	
	
	private TextView tv_num;//显示票数的TextView
	private TextView tv_reduce;//点击可以票数减1
	private TextView tv_add;//点击可以票数加1
	
	private EditText et_time;//日历EditText，功能测试ing
	
	private Context context;//Context
	
	private String account;//获取从主界面传来的账号
	
	private double totalPrice = 0.00;//总价钱
	private int totalCount = 0;//总票数
	private String priceString;//单件，String类型的
	
	CalendarFragment calendarFragment;//日历的Fragment

	int lineID=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reserve);
		initView();
		initEvents();
	}
	
	/**
	 * 初始化操作
	 */
	public void initView(){
		context = this;
		
		//获取账号
		account = getIntent().getStringExtra("account");
		
		
		tv_back = (TextView)findViewById(R.id.tv_back);//返回
		tv_price = (TextView)findViewById(R.id.tv_price);//显示单价的TextView
		tv_total_price = (TextView)findViewById(R.id.tv_total_price);//显示总价钱的TextViw
		tv_clear = (TextView)findViewById(R.id.tv_clear);//点击清空，总价置0
		tv_go_to_pay = (TextView)findViewById(R.id.tv_go_to_pay);//去付款
		
		tv_num = (TextView)findViewById(R.id.tv_num);//显示票数的TextView
		tv_reduce = (TextView)findViewById(R.id.tv_reduce);//点击票数减1
		tv_add = (TextView)findViewById(R.id.tv_add);//点击票数加1
		
		et_time = (EditText)findViewById(R.id.et_time);//出游时间
		
		String tourism_time = getIntent().getExtras().getString("tourism_time");
		et_time.setText(tourism_time);
		
		double price = getIntent().getExtras().getDouble("price");
		totalCount=1;
		totalPrice = price;
		tv_price.setText("¥"+price);
		tv_total_price.setText("¥"+price);
		priceString = tv_price.getText().toString().replace("¥", "");//获取单价
		tv_num.setText("1");
		tv_go_to_pay.setText("结算(1)");
		
		lineID=getIntent().getExtras().getInt("ID");
		
		String name = getIntent().getExtras().getString("name");
		tv_intro=(TextView)findViewById(R.id.tv_intro);//线路名称
		tv_intro.setText(name);
	}
	
	
	/**
	 * 事件操作
	 */
	public void initEvents(){
		tv_back.setOnClickListener(this);
		tv_clear.setOnClickListener(this);
		tv_go_to_pay.setOnClickListener(this);
		
		tv_reduce.setOnClickListener(this);
		tv_add.setOnClickListener(this);
		
		et_time.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		AlertDialog alert;
		switch (v.getId())
		{
		
		case R.id.et_time:
			
			break;
	
		case R.id.tv_go_to_pay:
			if (totalCount == 0)
			{
				Toast.makeText(context, "请选择要预定的旅游服务", Toast.LENGTH_LONG).show();
				return;
			}
			alert = new AlertDialog.Builder(context).create();
			alert.setTitle("操作提示");
			alert.setMessage("总计:\n" + totalCount + "张票\n" + totalPrice + "元");
			alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					return;
				}
			});
			alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					String countString = String.valueOf(totalCount);//票数
					String priceString = String.valueOf(totalPrice);//单价
					String lineIdString = Integer.toString(lineID);//旅游路线ID
					String timeString = et_time.getText().toString();//出游时间
					//执行异步任务
					new AddOrderAsyncTask().execute(Constant.URL_AddOrder,account,countString,priceString,lineIdString,timeString);
				}
			});
			alert.show();
			break;
		case R.id.tv_clear:
			if (totalCount == 0)
			{
				Toast.makeText(context, "请选择要取消预定的旅游服务", Toast.LENGTH_LONG).show();
				return;
			}
			alert = new AlertDialog.Builder(context).create();
			alert.setTitle("操作提示");
			alert.setMessage("您确定要取消预定的旅游服务吗？");
			alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					return;
				}
			});
			alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					totalCount = 0;
					totalPrice = 0.00;
					tv_num.setText("0");
					tv_total_price.setText("￥0.00");
					tv_go_to_pay.setText("结算(0)");
				}
			});
			alert.show();
			break;
		case R.id.tv_back:
			this.finish();
			break;
		case R.id.tv_reduce:
			if(totalCount >= 1){
				totalCount--;
				tv_num.setText(String.valueOf(totalCount));
				totalPrice = Double.parseDouble(priceString)*totalCount;				
				tv_total_price.setText(String.valueOf(totalPrice));
				tv_go_to_pay.setText("结算("+totalCount+")");
			}else if(totalCount <= 0){
				Toast.makeText(context, "数量不可以小于0哦!", 1).show();
			}
			break;
		case R.id.tv_add:
			totalCount++;
			tv_num.setText(String.valueOf(totalCount));
			totalPrice = Double.parseDouble(priceString)*totalCount;
			tv_total_price.setText(String.valueOf(totalPrice));
			tv_go_to_pay.setText("结算("+totalCount+")");
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
	class AddOrderAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			AddOrderService addOrderService = new AddOrderService();
			String retStr = addOrderService.addOrder(params[0], params[1],params[2],params[3],params[4],params[5]);
			
			return retStr;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result.equals("error")){
				Toast.makeText(getApplicationContext(), "提交订单失败!", 1).show();
			}else if(result.equals("success")){
				Toast.makeText(getApplicationContext(), "提交订单成功!", 1).show();
				ReserveActivity.this.finish();
			}
		}
		
	}

}
