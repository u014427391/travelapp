package com.tss.dao;

import java.util.List;

import com.tss.po.Spot;

public interface SpotDAO {

	/**
	 * 获取所有的景点信息
	 * @return
	 */
	public List<Spot> getAllInfo();
	
	/**
	 * 获取搜索的结果
	 * @return
	 */
	public List<Spot> getSearchInfo(String searchSpot);
	
	/**
	 * 根据地区进行景点搜索
	 * @param position
	 * @return
	 */
	public List<Spot> getInfoByPosition(String position);
	
	/**
	 * 根据景点类别进行景点搜索
	 * @param position
	 * @return
	 */
	public List<Spot> getInfoBySort(int sort);
	
	/**
	 * 景点信息排序
	 * @param id
	 * 			标志
	 * @return
	 */
	public List<Spot> spotInfoOrder(int id);
}
