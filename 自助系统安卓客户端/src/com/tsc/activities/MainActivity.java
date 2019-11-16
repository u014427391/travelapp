package com.tsc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tsc.fragment.AllLineFragment;
import com.tsc.fragment.HomePageFragment;
import com.tsc.fragment.MyFragment;
import com.tsc.fragment.OrderFragment;
import com.tsc.listener.IBtnCallListener;

/**
 * 
 *主界面类
 *fragment是3.0以后的东西，为了在低版本中使用fragment就要用到android-support-v4.jar兼容包,
 *而fragmentActivity就是这个兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 */
public class MainActivity extends FragmentActivity implements IBtnCallListener{
		//RadioButton
	    private RadioButton mHomePage;
	    private RadioButton mOrder;
	    private RadioButton mFound;
	    private RadioButton mMy;

	    //Fragment界面类
	    private HomePageFragment mHomePageFragment;
	    private OrderFragment mOrderFragment;
	    private AllLineFragment lineFragment;
	    private MyFragment mMyFragment;
	    
	    private Intent intent;
	    private Bundle bundle;
	    
	    private IBtnCallListener mBtnCallListener;  
	    
	    private Bundle sendBundle;
	    
	    private String account;
	    
	    FragmentTransaction ft;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //设置无标题
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_main);
	       
	        initView();
	        //初始化选择的Fragment
	        setSelection(3);
        	
	    }
	 
	    /**
	     * 初始化组件
	     */
	    private void initView() {
	        mHomePage = (RadioButton) findViewById(R.id.rb_homePage);
	        mOrder = (RadioButton)findViewById(R.id.rb_order);
	        mFound = (RadioButton) findViewById(R.id.rb_found);
	        mMy = (RadioButton) findViewById(R.id.rb_my);

	    }
	    /**
	     * 点击底部菜单栏后，图标和文字变色的实现
	     * @param index
	     */
	    private void setSelection(int index){
	        resetImg();
	        //创建FragmentTransaction
	        ft = getSupportFragmentManager().beginTransaction();
	        hideFragments(ft);
	        switch (index){
	            case 0:
	            	//变换图标
	            	mHomePage.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.guide_homepage_on, 0, 0);
	            	//变换文字颜色
	            	mHomePage.setTextColor(mHomePage.getResources().getColor(R.color.lightblue));
	            	
	                if(mHomePageFragment== null){
	                	mHomePageFragment = new HomePageFragment();
	                	
	                	//重新添加到FragmentTransaction
	                    ft.add(R.id.fg_content,mHomePageFragment);
	                   
	                }else{
	                    ft.show(mHomePageFragment);
	                }
	                
	                break;
	            case 1:
	            	orderFragmentTransfermsg();
	            	break;
	            case 2:
	            	 mFound.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.guide_found_on, 0, 0);
	            	 mFound.setTextColor(mFound.getResources().getColor(R.color.lightblue));
	            	 if(lineFragment== null){
	                     lineFragment = new AllLineFragment();
	                     ft.add(R.id.fg_content,lineFragment);
	                 }else{
	                     ft.show(lineFragment);
	                 }
	            	break;
	            case 3:
	               myFragmentTransfermsg();
	                break;
	        }
	        ft.commit();
	    }
	    
	    /**
	     *  恢复默认图片
	     */
	    private void resetImg() {
	    	mHomePage.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.guide_homepage, 0, 0);
	    	mHomePage.setTextColor(mHomePage.getResources().getColor(R.color.tab_text_bg));
	    	
	        mOrder.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.guide_order, 0, 0);
	        mOrder.setTextColor(mOrder.getResources().getColor(R.color.tab_text_bg));
	        
	        mFound.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.guide_found, 0, 0);
	        mFound.setTextColor(mFound.getResources().getColor(R.color.tab_text_bg));
	        
	        mMy.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.guide_my, 0, 0);
	        mMy.setTextColor(mMy.getResources().getColor(R.color.tab_text_bg));
	    }
	    /**
	     * 隐藏Fragment
	     * @param ft
	     * 
	     */
	    private void hideFragments(FragmentTransaction ft){
	        if(mHomePageFragment != null){
	            ft.hide(mHomePageFragment);
	        }
	        if(mOrderFragment != null){
	            ft.hide(mOrderFragment);
	        }
	        if(lineFragment != null){
	            ft.hide(lineFragment);
	        }
	        if(mMyFragment != null){
	            ft.hide(mMyFragment);
	        }
	       
	    }
	    //选择主页Fragment
	    public void onHomePageClicked(View view){
	        setSelection(0);
	    }
	    //选择消息Fragment
	    public void onOrderClicked(View view){
	    	setSelection(1);
	    }
	    //选择通讯录Fragment
	    public void onFoundClicked(View view){
	        setSelection(2);
	    }
	    //选择我的Fragment
	    public void onMyClicked(View view){
	        setSelection(3);
	    }
	    
	    
	    /**
	     * 按回车键
	     */
	    
	    private long exitTime = 0;

	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK
	                && event.getAction() == KeyEvent.ACTION_DOWN) {
	            if ((System.currentTimeMillis() - exitTime) > 2000) {
	                Toast.makeText(getApplicationContext(), "再按一次退出程序",
	                        Toast.LENGTH_SHORT).show();
	                exitTime = System.currentTimeMillis();
	            } else {
	                moveTaskToBack(false);
	                finish();

	            }
	            return true;
	        }
	        return super.onKeyDown(keyCode, event);
	    }

	    
	
	    /**
	     * 重载的onAttachFragment方法
	     */
	    @Override
	    public void onAttachFragment(Fragment fragment) {
	    	try {  
	        	 mBtnCallListener=(IBtnCallListener) fragment; 
	        } catch (Exception e) {  

	        } 
	    	super.onAttachFragment(fragment);
	    }
	    
	    
	    /**
	     * 实现IBtnCallListener接口的抽象方法
	     */
		@Override
		public void myFragmentTransfermsg() {
			mMy.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.guide_my_on, 0, 0);
            mMy.setTextColor(mMy.getResources().getColor(R.color.lightblue));
            if(mMyFragment == null){
                mMyFragment  = new MyFragment();
                //通过Bundle向Fragment类传送参数
                account = getIntent().getStringExtra("account");
                sendBundle = new Bundle();
    			sendBundle.putString("account", account);
    			//设置Arguments
                mMyFragment.setArguments(sendBundle);
                
                ft.add(R.id.fg_content,mMyFragment);
            }
            ft.show(mMyFragment);
		}

		@Override
		public void homePageFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void orderFragmentTransfermsg() {
			 mOrder.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.guide_order_on, 0, 0);
        	 mOrder.setTextColor(mOrder.getResources().getColor(R.color.lightblue));
        	 if(mOrderFragment== null){
                 mOrderFragment = new OrderFragment();
               //通过Bundle向Fragment类传送参数
                 account = getIntent().getStringExtra("account");
                 sendBundle = new Bundle();
     			sendBundle.putString("account", account);
     			//设置Arguments
                 mOrderFragment.setArguments(sendBundle);
                 ft.add(R.id.fg_content,mOrderFragment);
             }else{
                 ft.show(mOrderFragment);
             }
		}

		@Override
		public void foundFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}
		
		
}