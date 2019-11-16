package com.tsc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsc.Constant;
import com.tsc.activities.LoginActivity;
import com.tsc.activities.R;
import com.tsc.activities.SettingActivity;
import com.tsc.listener.IBtnCallListener;
/**
 * MyFragment类
 *
 */
public class MyFragment extends Fragment implements IBtnCallListener{

	  private View view;
	
	  private RelativeLayout contactLayout;
	  private RelativeLayout editLayout;
	  private RelativeLayout settingLayout;
	  
	  
	  private Bundle bundle;
	  private String account;//账号
	  
	  private TextView tv_1;//
	  private TextView tv_2;
	  
	    //@Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_my,container,false);
	        initView(view);
	        return view;
	    }
	    
	    public void initView(View view){
	    	tv_1 = (TextView)view.findViewById(R.id.text_01);
	    	tv_2 = (TextView)view.findViewById(R.id.text_02);
	    	myFragmentTransfermsg();
	    	
	    	contactLayout = (RelativeLayout)view.findViewById(R.id.mime_layout_02);
	    	//contactLayout.get(new GetTouristInfoByIdThread());
	    	
	    	editLayout = (RelativeLayout)view.findViewById(R.id.mime_layout_05);
	    	editLayout.setOnClickListener(new EditInfoClass());
	    	
	    	settingLayout = (RelativeLayout)view.findViewById(R.id.mime_layout_07);
	    	settingLayout.setOnClickListener(new SoftWareSettingClass());
	    	
	    }
	    
	   
	    
	    class EditInfoClass implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),LoginActivity.class);
				intent.putExtra("account", account);
				startActivity(intent);
				//getActivity().finish();
			}
	    	
	    }
	    
	    class SoftWareSettingClass implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),SettingActivity.class);
				startActivity(intent);
				
				//getActivity().finish();
			}
	    	
	    }

	    
		@Override
		public void homePageFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void orderFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void foundFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void myFragmentTransfermsg() {
			bundle = getArguments();
			account = bundle.getString("account");
			if(Constant.LoginMsg.isLogin){
	    		tv_1.setText("已登录");
	    		tv_2.setText("账号:"+account); 
	    	}
			System.out.println("由Activity传输过来的信息:"+account); 
		}
	   
	    
}
