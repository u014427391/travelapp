package com.tsc.view.ab;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * 名称：AbViewPagerAdapter.java 
 * 描述：一个通用的ViewPager适配器
 */
@SuppressLint("UseSparseArrays")
@SuppressWarnings("unused")
public class AbViewPagerAdapter extends PagerAdapter{
	
	/** The m context. */
	private Context mContext;
	
	/** The m list views. */
	private ArrayList<View> mListViews = null;
	
	/** The m views. */
	private HashMap <Integer,View> mViews = null;


	/**
	 * Instantiates a new ab view pager adapter.
	 * @param context the context
	 * @param mListViews the m list views
	 */
	public AbViewPagerAdapter(Context context,ArrayList<View> mListViews) {
		this.mContext = context;
		this.mListViews = mListViews;
		this.mViews = new HashMap <Integer,View>();
	}

	/**
	 * 描述：获取数量.
	 *
	 * @return the count
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return mListViews.size();
	}

	/**
	 * 描述：Object是否对应这个View.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return true, if is view from object
	 * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	/**
	 * 描述：显示View.
	 *
	 * @param container the container
	 * @param position the position
	 * @return the object
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.View, int)
	 */
	@Override
	public Object instantiateItem(View container, int position) {
		View v = mListViews.get(position);
		((ViewPager) container).addView(v);
		return v;
	}

	/**
	 * 描述：移除View.
	 *
	 * @param container the container
	 * @param position the position
	 * @param object the object
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.View, int, java.lang.Object)
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View)object);
	}
	
	/**
	 * 描述：很重要，否则不能notifyDataSetChanged.
	 *
	 * @param object the object
	 * @return the item position
	 * @see android.support.v4.view.PagerAdapter#getItemPosition(java.lang.Object)
	 */
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
	

}
