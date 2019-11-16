package com.tsc.view.ab;


/**
 * 名称：AbOnScrollListener.java 
 * 描述：滚动监听器
 */
public interface AbOnScrollListener {
    
    /**
     * 滚动.
     * @param arg1 返回参数
     */
    public void onScroll(int arg1); 
    
    /**
	 * 滚动停止.
	 */
    public void onScrollStoped();

	/**
	 * 滚到了最左边.
	 */
    public void onScrollToLeft();

	/**
	 * 滚到了最右边.
	 */
    public void onScrollToRight();

}
