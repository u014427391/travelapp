package com.tsw.dao;

import java.util.List;

import com.tsw.entitys.Line;

public interface LineDAO {

	/**
	 * 获取所有的旅游路线信息
	 * @return
	 */
	public List<Line> getAllInfo();

	/**
	 * 通过ID获取旅游路线信息
	 * @param ID
	 * 			旅游路线ID
	 * @return
	 */
	public Line getInfoById(int ID);
	
	/**
	 *新增旅游路线信息 
	 * @param line
	 * 			旅游路线的对象
	 * @return
	 */
	public boolean addInfo(Line line);
	
	public int getCount();
	
	/**
	 * 修改旅游路线信息
	 * @param line
	 * 			
	 * @return
	 */
	public boolean updateInfo(Line line);
	
}
