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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.tsc.Constant;
import com.tsc.activities.R;
import com.tsc.activities.SpotDetailActivity;
import com.tsc.activities.TitleDialog;
import com.tsc.adapter.SpotAdapter;
import com.tsc.entity.MenuItem;
import com.tsc.entity.Parameter;
import com.tsc.service.SyncHttp;
import com.tsc.view.ExpandTabView;
import com.tsc.view.RefreshableView;
import com.tsc.view.RefreshableView.PullToRefreshListener;
import com.tsc.view.TabViewOne;
import com.tsc.view.TabViewTwo;
import com.tsc.view.ab.AbSlidingPlayView;

/**
 * 首页的Fragment
 *
 */
public class HomePageFragment extends Fragment{

	    private View view;
	  
	    RefreshableView refreshableView;
	 
	    private ExpandTabView expandTabView;//菜单控件头部，封装了下拉动画，动态生成头部按钮个数
		private ArrayList<View> mViewArray = new ArrayList<View>();

		private TabViewTwo tabView1;
		private TabViewOne tabView2;
		private TabViewOne tabView3;

		private List<MenuItem> groups;
		private List<ArrayList<MenuItem>> childrens;

		private List<MenuItem> sequenceItems;

		private List<MenuItem> sortItems;
		
		
		private ListView spotList;
	

		
	//首页轮播
		private AbSlidingPlayView viewPager;
		
//		/**存储首页轮播的界面*/
//		private ArrayList<View> allListView;
//		/**首页轮播的界面的资源*/
//		private int[] resId = { R.drawable.v_1, R.drawable.v_2, R.drawable.v_3, R.drawable.v_4, R.drawable.v_5, R.drawable.v_6 };

	  
	    //@Nullable
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        view = inflater.inflate(R.layout.fragment_homepage,container,false);
//	        viewPager = (AbSlidingPlayView) view.findViewById(R.id.viewPager_menu);
//			//设置播放方式为顺序播放
//			viewPager.setPlayType(1);
//			//设置播放间隔时间
//			viewPager.setSleepTime(3000);
//	        initViewPager();
	        
	        initView(view);
	        
			initData();
			setOnClickListener();
	        
	        return view;
	    }
	    
	    
//	    private void initViewPager() {
//	    	
//	    	
//			if (allListView != null) {
//				allListView.clear();
//				allListView = null;
//			}
//			allListView = new ArrayList<View>();
//
//			for (int i = 0; i < resId.length; i++) {
//				//导入ViewPager的布局
//				View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_pic, null);
//				ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
//				imageView.setImageResource(resId[i]);
//				allListView.add(view);
//			}
//			
//			
//			viewPager.addViews(allListView);
//			//开始轮播
//			viewPager.startPlay();
//			viewPager.setOnItemClickListener(new AbOnItemClickListener() {
//				@Override
//				public void onClick(int position) {
//					//跳转到详情界面
//					//Intent intent = new Intent(getActivity(), BabyActivity.class);
//					//startActivity(intent);
//				}
//			});
//		}
	    
	    public void initView(View view){
	    	expandTabView = (ExpandTabView)view.findViewById(R.id.xcc_expandTabView);
	    	
	    	refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);
	    	
	    	refreshableView.setOnRefreshListener(new PullToRefreshListener() {
				@Override
				public void onRefresh() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					refreshableView.finishRefreshing();
				}
			}, 0);
	    	
	    	spotList = (ListView)view.findViewById(R.id.spot_listview);
	    	new GetAllSpotInfoAsyncTask().execute(Constant.URL_GetSpot);
	    	
	    	spotList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					HashMap<String, Object> map = (HashMap<String,Object>)arg0.getItemAtPosition(arg2);
					Intent intent = new Intent(getActivity(), SpotDetailActivity.class);
					intent.putExtra("map", map);
					startActivity(intent);
				}
		
			});
	    	
	    	/**
	    	 * 点击后弹出窗口
	    	 */
	    	ImageView view2=(ImageView) view.findViewById(R.id.iv_new);
	    	
	    	 view2.setOnClickListener(new OnClickListener() {
	 			
	 			@Override
				public void onClick(View v) {
	 				Intent intent = new Intent(getActivity(), TitleDialog.class);
					
					startActivity(intent);
	 			}
	 		});
	    }
	    
	    
	    private void setOnClickListener() {
			tabView1.setOnItemSelectListener(new TabViewTwo.OnItemSelectListener() {

				@Override
				public void itemSelected(int position) {
					onRefreshTab(tabView1, tabView1.getShowText());
					new GetSpotInfoByPositionAsyncTask().execute(Constant.URL_GetSpotInfoByPosition,tabView1.getShowText());
				}
			});

			tabView2.setOnItemSelectListener(new TabViewOne.OnItemSelectListener() {

				@Override
				public void itemSelected(int position) {
					onRefreshTab(tabView2, tabView2.getShowText());

				}
			});

			tabView3.setOnItemSelectListener(new TabViewOne.OnItemSelectListener() {

				@Override
				public void itemSelected(int position) {
					onRefreshTab(tabView3, tabView3.getShowText());
					new GetSpotInfoByPositionAsyncTask().execute(Constant.URL_GetSpotInfoByPosition,tabView1.getShowText());
				}
			});
		}

		private void initData() {
			// 所有分类组
			String provinces[] = getResources().getStringArray(R.array.province_item);
			groups = new ArrayList<MenuItem>();
			for(int i=0;i<provinces.length;i++){
				groups.add(new MenuItem(i,provinces[i]));
			}
			
			// 所有子分类
			childrens = new ArrayList<ArrayList<MenuItem>>();
			
			String BeiJing_province[] = getResources().getStringArray(R.array.beijin_province_item);
			ArrayList<MenuItem> item1 = new ArrayList<MenuItem>();
			for(int i=0;i<BeiJing_province.length;i++){
				item1.add(new MenuItem(i,BeiJing_province[i]));
			}
			
			String TianJing_province[] = getResources().getStringArray(R.array.tianjin_province_item);
			ArrayList<MenuItem> item2 = new ArrayList<MenuItem>();
			for(int i=0;i<TianJing_province.length;i++){
				item2.add(new MenuItem(i,TianJing_province[i]));
			}
			
			String HeBei_province[] = getResources().getStringArray(R.array.hebei_province_item);
			ArrayList<MenuItem> item3 = new ArrayList<MenuItem>();
			for(int i=0;i<HeBei_province.length;i++){
				item3.add(new MenuItem(i,HeBei_province[i]));
			}
			
			String ShanXi_province[] = getResources().getStringArray(R.array.shanxi1_province_item);
			ArrayList<MenuItem> item4 = new ArrayList<MenuItem>();
			for(int i=0;i<ShanXi_province.length;i++){
				item4.add(new MenuItem(i,ShanXi_province[i]));
			}
			
			String NeiMengGu_province[] = getResources().getStringArray(R.array.neimenggu_province_item);
			ArrayList<MenuItem> item5 = new ArrayList<MenuItem>();
			for(int i=0;i<NeiMengGu_province.length;i++){
				item5.add(new MenuItem(i,NeiMengGu_province[i]));
			}
			
			String LiaoNing_province[] = getResources().getStringArray(R.array.liaoning_province_item);
			ArrayList<MenuItem> item6 = new ArrayList<MenuItem>();
			for(int i=0;i<LiaoNing_province.length;i++){
				item6.add(new MenuItem(i,LiaoNing_province[i]));
			}
			
			String JiLin_province[] = getResources().getStringArray(R.array.jilin_province_item);
			ArrayList<MenuItem> item7 = new ArrayList<MenuItem>();
			for(int i=0;i<JiLin_province.length;i++){
				item7.add(new MenuItem(i,JiLin_province[i]));
			}
			
			String HeiLongJiang_province[] = getResources().getStringArray(R.array.heilongjiang_province_item);
			ArrayList<MenuItem> item8 = new ArrayList<MenuItem>();
			for(int i=0;i<HeiLongJiang_province.length;i++){
				item8.add(new MenuItem(i,HeiLongJiang_province[i]));
			}
			
			String ShangHai_province[] = getResources().getStringArray(R.array.shanghai_province_item);
			ArrayList<MenuItem> item9 = new ArrayList<MenuItem>();
			for(int i=0;i<ShangHai_province.length;i++){
				item9.add(new MenuItem(i,ShangHai_province[i]));
			}
			
			String JiangSu_province[] = getResources().getStringArray(R.array.jiangsu_province_item);
			ArrayList<MenuItem> item10 = new ArrayList<MenuItem>();
			for(int i=0;i<JiangSu_province.length;i++){
				item10.add(new MenuItem(i,JiangSu_province[i]));
			}
			
			String ZheJiang_province[] = getResources().getStringArray(R.array.zhejiang_province_item);
			ArrayList<MenuItem> item11 = new ArrayList<MenuItem>();
			for(int i=0;i<ZheJiang_province.length;i++){
				item11.add(new MenuItem(i,ZheJiang_province[i]));
			}
			
			String AnHui_province[] = getResources().getStringArray(R.array.anhui_province_item);
			ArrayList<MenuItem> item12 = new ArrayList<MenuItem>();
			for(int i=0;i<AnHui_province.length;i++){
				item12.add(new MenuItem(1,AnHui_province[i]));
			}
			
			String FuJian_province[] = getResources().getStringArray(R.array.fujian_province_item);
			ArrayList<MenuItem> item13 = new ArrayList<MenuItem>();
			for(int i=0;i<FuJian_province.length;i++){
				item13.add(new MenuItem(i,FuJian_province[i]));
			}
			
			String JiangXi_province[] = getResources().getStringArray(R.array.jiangxi_province_item);
			ArrayList<MenuItem> item14 = new ArrayList<MenuItem>();
			for(int i=0;i<JiangXi_province.length;i++){
				item14.add(new MenuItem(i,JiangXi_province[i]));
			}
			
			String ShanDong_province[] = getResources().getStringArray(R.array.shandong_province_item);
			ArrayList<MenuItem> item15 = new ArrayList<MenuItem>();
			for(int i=0;i<ShanDong_province.length;i++){
				item15.add(new MenuItem(i,ShanDong_province[i]));
			}
			
			String HeNan_province[] = getResources().getStringArray(R.array.henan_province_item);
			ArrayList<MenuItem> item16 = new ArrayList<MenuItem>();
			for(int i=0;i<HeNan_province.length;i++){
				item16.add(new MenuItem(i,HeNan_province[i]));
			}
			
			String HuBei_province[] = getResources().getStringArray(R.array.hubei_province_item);
			ArrayList<MenuItem> item17 = new ArrayList<MenuItem>();
			for(int i=0;i<HuBei_province.length;i++){
				item17.add(new MenuItem(i,HuBei_province[i]));
			}
			
			String HuNan_province[] = getResources().getStringArray(R.array.hunan_province_item);
			ArrayList<MenuItem> item18 = new ArrayList<MenuItem>();
			for(int i=0;i<HuNan_province.length;i++){
				item18.add(new MenuItem(i,HuNan_province[i]));
			}
			
			String GuangDong_province[] = getResources().getStringArray(R.array.guangdong_province_item);
			ArrayList<MenuItem> item19 = new ArrayList<MenuItem>();
			for(int i=0;i<GuangDong_province.length;i++){
				item19.add(new MenuItem(i,GuangDong_province[i]));
			}
			
			String GuangXi_province[] = getResources().getStringArray(R.array.guangxi_province_item);
			ArrayList<MenuItem> item20 = new ArrayList<MenuItem>();
			for(int i=0;i<GuangXi_province.length;i++){
				item20.add(new MenuItem(i,GuangXi_province[i]));
			}
			
			String HaiNan_province[] = getResources().getStringArray(R.array.hainan_province_item);
			ArrayList<MenuItem> item21 = new ArrayList<MenuItem>();
			for(int i=0;i<HaiNan_province.length;i++){
				item21.add(new MenuItem(i,HaiNan_province[i]));
			}
			
			String ChongQing_province[] = getResources().getStringArray(R.array.chongqing_province_item);
			ArrayList<MenuItem> item22 = new ArrayList<MenuItem>();
			for(int i=0;i<ChongQing_province.length;i++){
				item22.add(new MenuItem(i,ChongQing_province[i]));
			}
			
			String SiChuan_province[] = getResources().getStringArray(R.array.sichuan_province_item);
			ArrayList<MenuItem> item23 = new ArrayList<MenuItem>();
			for(int i=0;i<SiChuan_province.length;i++){
				item23.add(new MenuItem(i,SiChuan_province[i]));
			}
			
			String GuiZhou_province[] = getResources().getStringArray(R.array.guizhou_province_item);
			ArrayList<MenuItem> item24 = new ArrayList<MenuItem>();
			for(int i=0;i<GuiZhou_province.length;i++){
				item24.add(new MenuItem(i,GuiZhou_province[i]));
			}
			
			String YunNan_province[] = getResources().getStringArray(R.array.yunnan_province_item);
			ArrayList<MenuItem> item25 = new ArrayList<MenuItem>();
			for(int i=0;i<YunNan_province.length;i++){
				item25.add(new MenuItem(i,YunNan_province[i]));
			}
			
			String XiZang_province[] = getResources().getStringArray(R.array.xizang_province_item);
			ArrayList<MenuItem> item26 = new ArrayList<MenuItem>();
			for(int i=0;i<XiZang_province.length;i++){
				item26.add(new MenuItem(i,XiZang_province[i]));
			}
			
			String ShanXi1_province[] = getResources().getStringArray(R.array.shanxi2_province_item);
			ArrayList<MenuItem> item27 = new ArrayList<MenuItem>();
			for(int i=0;i<ShanXi1_province.length;i++){
				item27.add(new MenuItem(i,ShanXi1_province[i]));
			}
			
			String GanSu_province[] = getResources().getStringArray(R.array.gansu_province_item);
			ArrayList<MenuItem> item28 = new ArrayList<MenuItem>();
			for(int i=0;i<GanSu_province.length;i++){
				item28.add(new MenuItem(i,GanSu_province[i]));
			}
			
			String QingHai_province[] = getResources().getStringArray(R.array.qinghai_province_item);
			ArrayList<MenuItem> item29 = new ArrayList<MenuItem>();
			for(int i=0;i<QingHai_province.length;i++){
				item29.add(new MenuItem(i,QingHai_province[i]));
			}
			
			String NingXia_province[] = getResources().getStringArray(R.array.ningxia_province_item);
			ArrayList<MenuItem> item30 = new ArrayList<MenuItem>();
			for(int i=0;i<NingXia_province.length;i++){
				item30.add(new MenuItem(i,NingXia_province[i]));
			}
			
			String XinJiang_province[] = getResources().getStringArray(R.array.xinjiang_province_item);
			ArrayList<MenuItem> item31 = new ArrayList<MenuItem>();
			for(int i=0;i<XinJiang_province.length;i++){
				item31.add(new MenuItem(i,XinJiang_province[i]));
			}
			
			String TaiWan_province[] = getResources().getStringArray(R.array.taiwan_province_item);
			ArrayList<MenuItem> item32 = new ArrayList<MenuItem>();
			for(int i=0;i<TaiWan_province.length;i++){
				item32.add(new MenuItem(i,TaiWan_province[i]));
			}
			
			String HongKong_province[] = getResources().getStringArray(R.array.hongkong_province_item);
			ArrayList<MenuItem> item33= new ArrayList<MenuItem>();
			for(int i=0;i<HongKong_province.length;i++){
				item33.add(new MenuItem(i,HongKong_province[i]));
			}
			
			String AoMen_province[] = getResources().getStringArray(R.array.aomen_province_item);
			ArrayList<MenuItem> item34 = new ArrayList<MenuItem>();
			for(int i=0;i<AoMen_province.length;i++){
				item34.add(new MenuItem(i,AoMen_province[i]));
			}
			
			
			childrens.add(item1);
			childrens.add(item2);
			childrens.add(item3);
			childrens.add(item4);
			childrens.add(item5);
			childrens.add(item6);
			childrens.add(item7);
			childrens.add(item8);
			childrens.add(item9);
			childrens.add(item10);
			childrens.add(item11);
			childrens.add(item12);
			childrens.add(item13);
			childrens.add(item14);
			childrens.add(item15);
			childrens.add(item16);
			childrens.add(item17);
			childrens.add(item18);
			childrens.add(item19);
			childrens.add(item20);
			childrens.add(item21);
			childrens.add(item22);
			childrens.add(item23);
			childrens.add(item24);
			childrens.add(item25);
			childrens.add(item26);
			childrens.add(item27);
			childrens.add(item28);
			childrens.add(item29);
			childrens.add(item30);
			childrens.add(item31);
			childrens.add(item32);
			childrens.add(item33);
			childrens.add(item34);
			// 第二个tab的数据
			sequenceItems = new ArrayList<MenuItem>();

			sequenceItems.add(new MenuItem(1, "距离最近"));
			sequenceItems.add(new MenuItem(2, "票价最低"));
			sequenceItems.add(new MenuItem(3, "开发最早"));

			// 第三个Tab的数据
			sortItems = new ArrayList<MenuItem>();
			sortItems.add(new MenuItem(1, "所有类别"));
			sortItems.add(new MenuItem(2, "宗教文化"));
			sortItems.add(new MenuItem(2, "名称古迹"));
			sortItems.add(new MenuItem(2, "自然景点"));
			sortItems.add(new MenuItem(2, "红色旅游"));
			sortItems.add(new MenuItem(2, "历史景点"));

			// 3个TAB
			tabView1 = new TabViewTwo(getActivity(), groups, childrens, -1, -1,
					R.drawable.choose_item_selected,
					R.drawable.choose_eara_item_selector,
					R.drawable.choose_item_right,
					R.drawable.choose_plate_item_selector);
			tabView2 = new TabViewOne(getActivity(), sequenceItems, -1,
					R.drawable.choose_item_right,
					R.drawable.choose_eara_item_selector);
			tabView3 = new TabViewOne(getActivity(), sortItems, -1,
					R.drawable.choose_item_right,
					R.drawable.choose_eara_item_selector);
			
			mViewArray.add(tabView1);
			mViewArray.add(tabView2);
			mViewArray.add(tabView3);

			// 设置参数
			expandTabView.setValue(mViewArray, R.drawable.expand_tab_selector,
					R.drawable.choosebar_line);

			// 设置默认标题
			expandTabView.setTitle("地区", 0);
			expandTabView.setTitle("排序", 1);
			expandTabView.setTitle("筛选", 2);

		}
		
		


		/**
		 * 设置TAB标题
		 * 
		 * @param view
		 * @param showText
		 */
		private void onRefreshTab(View view, String showText) {

			expandTabView.onPressBack();
			int position = getPositon(view);
			if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
				expandTabView.setTitle(showText, position);
			}

		}

		private int getPositon(View tView) {
			for (int i = 0; i < mViewArray.size(); i++) {
				if (mViewArray.get(i) == tView) {
					return i;
				}
			}
			return -1;
		}
	
	/**
	 * 获取所有的景点信息
	 * @param url
	 * 			URL
	 * @return
	 */
	public List<HashMap<String,Object>> getAllSpotInfo(String url){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		String params = "";
		
		SyncHttp syncHttp = new SyncHttp();
		
		try {
			String retStr = syncHttp.httpGet(url, params);
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
	


	/**
	 * 根据地理位置获取景点信息
	 * @param url
	 * 			URL
	 * @param position
	 * 			景点位置
	 * @return
	 */
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
	
	
	/**
	 * 
	 * 
	 * 异步任务使用方法描述
	 * Asynchronous Task必须是作为一个子类来使用，
	 * task实例必须在UI线程创建
	 * execute(Params...)必须在UI线程调用
	 * 不要手工调用onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...)。
	 * task只可以execute一次，执行多次就报异常
	 *
	 */
	class GetSpotInfoByPositionAsyncTask extends AsyncTask<String, Void, List<HashMap<String,Object>>>{
		/**第1个参数String是传入的参数类型,修改的话,doInBackground的方法参数类型也要修改
		 * 参数3是doInBackground的返回类型,修改的话,doInBackground的参数返回类型也要修改
		 * **/
		@Override
		protected List<HashMap<String,Object>> doInBackground(String... params) {
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			try{
				List<Parameter> param = new ArrayList<Parameter>();
				param.add(new Parameter("position",params[1])); //将搜索内容加进url
				list = getSpotsInfo(params[0],param);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return list;
		}
		
		/**doInBackground获取到的List<HashMap<String,Object>>参数传入到onPostExecute里调用**/	
		@Override
		protected void onPostExecute(List<HashMap<String, Object>> list) {
			// TODO Auto-generated method stub
			SpotAdapter adapter = new SpotAdapter(getActivity(), list);
			spotList.setAdapter(adapter);
			
			
		}
	}
	
	/**
	 * 
	 * 
	 * 异步任务使用方法描述
	 * Asynchronous Task必须是作为一个子类来使用，
	 * task实例必须在UI线程创建
	 * execute(Params...)必须在UI线程调用
	 * 不要手工调用onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...)。
	 * task只可以execute一次，执行多次就报异常
	 *
	 */
	class GetSpotInfoBySortAsyncTask extends AsyncTask<String, Void, List<HashMap<String,Object>>>{
		/**第1个参数String是传入的参数类型,修改的话,doInBackground的方法参数类型也要修改
		 * 参数3是doInBackground的返回类型,修改的话,doInBackground的参数返回类型也要修改
		 * **/
		@Override
		protected List<HashMap<String,Object>> doInBackground(String... params) {
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			try{
				List<Parameter> param = new ArrayList<Parameter>();
				param.add(new Parameter("sort",params[1])); //将搜索内容加进url
				list = getSpotsInfo(params[0],param);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return list;
		}
		
		/**doInBackground获取到的List<HashMap<String,Object>>参数传入到onPostExecute里调用**/	
		@Override
		protected void onPostExecute(List<HashMap<String, Object>> list) {
			// TODO Auto-generated method stub
			SpotAdapter adapter = new SpotAdapter(getActivity(), list);
			spotList.setAdapter(adapter);
			
			
		}
	}
	
	
	
	/**
	 * 获取所有景点的异步任务
	 * @author Nicky
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
				list = getAllSpotInfo(params[0]);
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			return list;
		}
		
		/**doInBackground获取到的List<HashMap<String,Object>>参数传入到onPostExecute里调用**/	
		@Override
		protected void onPostExecute(List<HashMap<String, Object>> list) {
			// TODO Auto-generated method stub
			SpotAdapter adapter = new SpotAdapter(getActivity(), list);
			spotList.setAdapter(adapter);
			
			
		}
	}
}
