package com.tsc.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tsc.Constant;
import com.tsc.activities.R;
import com.tsc.activities.ReserveActivity;
import com.tsc.adapter.OrderAdapter;
import com.tsc.listener.IBtnCallListener;
import com.tsc.service.SyncHttp;
/**
 * OrderFragment类
 */
public class OrderFragment extends Fragment implements IBtnCallListener{
	  private View view;

	  private ListView listView;
	  
	  private Bundle bundle;
	  
	  private String account;
	  
	  private List<HashMap<String,Object>> orderList;
	  
	  //
	  private TextView tv_order;
	  
	    //@Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_order,container,false);
	        initView(view);
	        return view;
	    }
	    
	    public void initView(View view){
	    	orderFragmentTransfermsg();
	    	tv_order = (TextView)view.findViewById(R.id.tv_order);
	    	
	    	tv_order.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(),ReserveActivity.class);
					intent.putExtra("account", account);
					startActivity(intent);
				}
			});
	    	
	    	listView = (ListView)view.findViewById(R.id.order_listview);
	    	new GetOrderAsyncTask().execute(Constant.URL_GetOrder,account);
	    }
	    
	    public List<HashMap<String,Object>> getOrderList(String url,String account){
	    	List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	    	
	    	//同步加载的类
	    	SyncHttp syncHttp = new SyncHttp();
	    	
	    	String params = "account=" + account;
	    	
	    	try {
				String retStr = syncHttp.httpGet(url, params);	
				JSONObject jsonObject = new JSONObject(retStr);
				
				int ret = jsonObject.getInt("ret");
				if(ret == 0){
					JSONObject dataObject = jsonObject.getJSONObject("data");
					int totalNum = dataObject.getInt("totalNum");
					if(totalNum > 0){
						JSONArray orderlist = dataObject.getJSONArray("orderList");
						for(int i = 0;i<orderlist.length();i++){
							JSONObject orderMap = (JSONObject)orderlist.opt(i);
							HashMap<String, Object> map = new HashMap<String,Object>();
							map.put("picPath", orderMap.getString("picPath"));
							map.put("name", orderMap.getString("name"));
							map.put("order_time", orderMap.get("order_time"));
							map.put("total_price", orderMap.getDouble("total_price"));
							list.add(map);
						}
					}
				}else{
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return list;
	    }
	    
	    @SuppressWarnings("rawtypes")
		class GetOrderAsyncTask extends AsyncTask<String, List, List<HashMap<String,Object>>>{
	    	
	    	@Override
			protected List<HashMap<String,Object>> doInBackground(String... params) {
				
	    		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
				try{
					list = getOrderList(params[0], params[1]);
				}catch(Exception e){
				   e.printStackTrace();
				}
				return list;
			}

			@Override
			protected void onPostExecute(List<HashMap<String,Object>> orderList) {
				OrderAdapter adapter = new OrderAdapter(getActivity(), orderList);
			    listView.setAdapter(adapter);
			}
	    }	    
	    
	    @Override
		public void homePageFragmentTransfermsg() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void orderFragmentTransfermsg() {
			// TODO Auto-generated method stub
			bundle = getArguments();
			account = bundle.getString("account");
			System.out.println("Activity传输过来的信息:"+account); 
		}

		@Override
		public void foundFragmentTransfermsg() {

		}

		@Override
		public void myFragmentTransfermsg() {
			
		}
	    
}
