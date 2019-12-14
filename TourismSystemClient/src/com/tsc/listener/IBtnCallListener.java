package com.tsc.listener;
/**
 * 
 * 传送Fragment和FragmentActivity之间的控件交互信息
 *
 */
public interface IBtnCallListener {
	//发送消息到HomePageFragment
	public void homePageFragmentTransfermsg();
	//发送消息到OrderFragment
	public void orderFragmentTransfermsg();
	//发送数据到FoundFragment
	public void foundFragmentTransfermsg();
	//发生数据到MyFragment
	public void myFragmentTransfermsg();
}