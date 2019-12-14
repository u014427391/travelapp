package com.tsc.activities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsc.Constant;
import com.tsc.adapter.SpotAdapter;
import com.tsc.entity.Parameter;
import com.tsc.service.SyncHttp;

/**
 *
* @ClassName: SearchSpotInfoActivity
* @Description: 
* @author 陈钟涛
* @date 2016年3月31日 下午1:37:28
*
 */
public class SearchSpotInfoActivity extends Activity {

	private ImageView seach_delete;//删除文本框的文字
	private EditText etSearch;	//文本框
	private ListView searchspot;
	private Button searchbtn;
	private String searchStr;//搜索
	private TextView backbtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_spotinfo);
        //实现可以消除文本框的文字功能
        seach_delete = (ImageView) findViewById(R.id.seach_delete);
        etSearch = (EditText) findViewById(R.id.etSearch);
        searchbtn = (Button) findViewById(R.id.search_spot_btn);
        initView();
    }
    public void initView(){
    	//获取搜索返回的内容
    	 searchbtn.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View v) {
 				searchStr=etSearch.getText().toString();
 				//获取文框的文字
 				if(searchStr.length()>0){
 					new GetAllSpotInfoAsyncTask().execute(Constant.URL_SearchSpot,searchStr);
 					}else{
 					Toast.makeText(getApplicationContext(), "请输入要搜索的景点哦！", Toast.LENGTH_SHORT).show();
 					}
 			}
 		});
    	 //清除功能
         seach_delete.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View v) {
 				etSearch.setText("");
 			}
 		});
         //是否显示删除
         etSearch.addTextChangedListener(new TextWatcher() {
 			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
 				// TODO Auto-generated method stub
 			}
 			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
 					int after) {
 				// TODO Auto-generated method stub
 			}
 			@Override
			public void afterTextChanged(Editable s) {
 				if (s.length() == 0) {
 					seach_delete.setVisibility(View.GONE);
 				} else {
 					seach_delete.setVisibility(View.VISIBLE);
 				}
 			}
 		});
    	
    	//将景点信息输出
    	searchspot = (ListView)findViewById(R.id.search_spot_listview);   	
    	searchspot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				HashMap<String, Object> map = (HashMap<String,Object>)arg0.getItemAtPosition(arg2);
				Intent intent = new Intent(getApplicationContext(), SpotDetailActivity.class);
				intent.putExtra("map", map);
				startActivity(intent);
			}
		});
        backbtn = (TextView)findViewById(R.id.seach_back);
		
        backbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchSpotInfoActivity.this.finish();
			}
		});
    }
    /**
	 * 
	 * 获取所有景点的异步任务
	 * 
	 * 异步任务使用方法描述
	 * Asynchronous Task必须是作为一个子类来使用，
	 * task实例必须在UI线程创建
	 * execute(Params...)必须在UI线程调用
	 * 不要手工调用onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...)。
	 * task只可以execute一次，执行多次就报异常
	 *
	 */
	class GetAllSpotInfoAsyncTask extends AsyncTask<String, Void, List<HashMap<String,Object>>>{
		/**第1个参数String是传入的参数类型,修改的话,doInBackground的方法参数类型也要修改
		 * 参数3是doInBackground的返回类型,修改的话,doInBackground的参数返回类型也要修改
		 * **/
		@Override
		protected List<HashMap<String,Object>> doInBackground(String... params) {
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			try{
				List<Parameter> param = new ArrayList<Parameter>();
				param.add(new Parameter("SearchStr",params[1])); //将搜索内容加进url
				list = getSpotsInfo(params[0],param);
			}catch (Exception e) {
				// TODO: handle exception
			}
			return list;
		}
		
		/**doInBackground获取到的List<HashMap<String,Object>>参数传入到onPostExecute里调用**/	
		@Override
		protected void onPostExecute(List<HashMap<String, Object>> list) {
			// TODO Auto-generated method stubhi
			if(list.size()==0){
				Toast.makeText(getApplicationContext(), "没有相关的信息哦！！", Toast.LENGTH_SHORT).show();
			}
			SpotAdapter adapter = new SpotAdapter(getApplicationContext(), list);
			searchspot.setAdapter(adapter);
		}
	}
	public List<HashMap<String,Object>> getSpotsInfo(String url,List<Parameter> param){
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		SyncHttp syncHttp = new SyncHttp();
		
		try {
			String retStr = syncHttp.httpPost(url, param);
			JSONObject jsonObject = new JSONObject(retStr);
			
			int ret = jsonObject.getInt("ret");
			if(ret == 0){
				JSONObject dataObject = jsonObject.getJSONObject("data");
				
				int totalNum = dataObject.getInt("totalNum");
				if(totalNum > 0){
					JSONArray spotList = dataObject.getJSONArray("spotsInfo");
					for(int i = 0; i < spotList.length(); i++){
						JSONObject spotMap = (JSONObject) spotList.opt(i);
						HashMap<String,Object> map = new HashMap<String, Object>();
						map.put("ID", spotMap.get("ID"));
						map.put("position", spotMap.get("position"));
						map.put("tour_project", spotMap.get("tour_project"));
						map.put("price", spotMap.get("price"));
						map.put("introduction", spotMap.get("introduction"));
						map.put("picture", spotMap.get("picture"));
						map.put("time", spotMap.get("time"));
						list.add(map);
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
