package com.tsw.dao;

import java.util.List;

import com.tsw.entitys.Line;
import com.tsw.entitys.Spot;

public interface SpotDAO {

	/**
	 * 获取所有的景点信息
	 * @return
	 */
	public List<Spot> getAllInfo();
	
	/**
	 * 通过ID获取景点信息
	 * @param ID
	 * 			景点ID
	 * @return
	 */
	public Spot getInfoById(int ID);
	
	/**
	 * 新增景点信息
	 * @param spot
	 * @return
	 */
	public boolean addInfo(Spot spot);
	
	/**
	 * 更新景点信息
	 * @param spot
	 * @return
	 */
	public boolean updateInfo(Spot spot);
}
