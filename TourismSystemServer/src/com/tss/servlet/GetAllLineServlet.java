package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.utils.DBHelperUtil;

public class GetAllLineServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int data_count=6;//每页显示记录条数

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");	    
		String page_str=new String(request.getParameter("page").getBytes("ISO-8859-1"),"UTF-8");//景点名称
		int page=Integer.parseInt(page_str);

		
		DBHelperUtil manager;
		ResultSet rs;
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append('[');

		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			String sql = "select count(*) from line";	
			Object[] params = new Object[]{};
			rs = manager.executeQuery(sql, params);	
			int count=0;
			while(rs.next()){
				count=rs.getInt("count(*)");
			}
			
			
//			System.out.println("page:"+page);
			int m=(page-1)*data_count;
//			System.out.println(m);
			sql = "select * from line ORDER BY ID desc limit ?,?";	
			
//			System.out.println("page*data_count:"+page*data_count);
//			System.out.println("count:"+count);
			if(page*data_count>count){
				params = new Object[]{m,count-m};
				sb.append('{').append("\"HaveReadAll\":").append("\"true\"").append('}').append(",");	
			}else{
				params = new Object[]{m,data_count};
				sb.append('{').append("\"HaveReadAll\":").append("\"false\"").append('}').append(",");
			}
			

		
			
			rs = manager.executeQuery(sql, params);		
			while(rs.next()){
				sb.append('{').append("\"ID\":").append(URLEncoder.encode(Integer.toString(rs.getInt("ID")), "UTF-8")).append(",");   
				System.out.println("ID:"+rs.getInt("ID"));
				sb.append("\"price\":").append(URLEncoder.encode(Double.toString(rs.getInt("price")), "UTF-8")).append(",");   
				sb.append("\"name\":").append("\""+URLEncoder.encode(rs.getString("name"), "UTF-8")+"\"").append(",");
				sb.append("\"tourism_time\":").append("\""+ URLEncoder.encode(rs.getString("tourism_time"), "UTF-8")+"\"").append(",");
				sb.append("\"catering\":").append("\""+URLEncoder.encode(rs.getString("catering"), "UTF-8")+"\"").append(","); 			
				sb.append("\"accommodation\":").append("\""+URLEncoder.encode(rs.getString("accommodation"), "UTF-8")+"\"").append(","); 		
				sb.append("\"traffic\":").append("\""+URLEncoder.encode(rs.getString("traffic"), "UTF-8")+"\"").append(",");		
				sb.append("\"picture\":").append("\""+URLEncoder.encode(rs.getString("picture"), "UTF-8")+"\"").append(",");		
				sb.append("\"contact\":").append("\""+URLEncoder.encode(rs.getString("contact"), "UTF-8")+"\""); 		
				sb.append('}').append(",");	
			}			
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');
		out.write(new String(sb));
		out.flush();
		out.close();
	}


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
