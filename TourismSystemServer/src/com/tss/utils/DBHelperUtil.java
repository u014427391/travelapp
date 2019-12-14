package com.tss.utils;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;


/**
 * 
 * 数据库连接的类，配置信息保存在config.properties里
 *
 */
public class DBHelperUtil {
	//静态成员变量，支持单态模式
	private static DBHelperUtil manager = null;
	//配置资源文件
	private PropertyResourceBundle bundle;
	//JDBC驱动
	private static String jdbcDriver = null;
	//主机
	private String host = "";
	//数据库端口
	private String port = "";
	//数据库名称
	private String database = "";
	//数据库用户名
	private String username = "";
	//数据库密码
	private String password ="";
	
	//数据库连接字符串
	private String connStr = "";
	
	//连接对象
	private Connection conn = null;
	//PrepareStatement对象
	private PreparedStatement pstm = null;
	//CallableStatement对象
	private CallableStatement cstm = null;
	
	/**
	 * 私有构造对象，不可以实例化
	 * @throws IOException
	 */
	public DBHelperUtil() throws IOException{
		bundle = new PropertyResourceBundle(DBHelperUtil.class.getResourceAsStream("config.properties"));
		this.host = getString("host");
		this.database = getString("database");
		this.port = getString("port");
		this.username = getString("username");
		this.password = getString("password");
		jdbcDriver = "com.mysql.jdbc.Driver";
		//数据库连接的url，设置了编码为UTF-8
		connStr = "jdbc:mysql://"+host+":"+port+"/"+database+"?useUnicode=true&characterEncoding=UTF-8";
	}
	
	/**
	 * 读取配置文件中的值
	 * @param 
	 * 		key 配置文件的key
	 * @return 
	 * 		key对应的值
	 */
	private String getString(String key){
		return this.bundle.getString(key);
	}
	
	/**
	 * 单态模式获取实例
	 * 
	 * @return SqlManager对象
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public static DBHelperUtil createInstance() throws IOException, ClassNotFoundException{
		if (manager == null)
		{
			manager = new DBHelperUtil();
			manager.initDB();
		}
		return manager;
	}
	
	/**
	 * 初始化连接参数，由指定的DBType生成
	 * 
	 * @throws ClassNotFoundException
	 */
	public void initDB() throws ClassNotFoundException{
		Class.forName(jdbcDriver);
	}
	
	/**
	 * 连接数据库
	 * @throws SQLException
	 */
	public void connectDB() throws SQLException{
		conn = DriverManager.getConnection(connStr,username,password);
		conn.setAutoCommit(false);// 设置自动提交为false
	}
	/**
	 * 关闭数据库，释放内存
	 * @throws SQLException
	 */
	public void close() throws SQLException  {
		if (pstm != null)
		{
			pstm.close();
		}
		if (cstm != null)
		{
			cstm.close();
		}
		if (conn != null)
		{
			conn.close();
		}
	}
	/**
	 * 设置PrepareStatement对象中Sql语句中的参数
	 * @param sql	
	 * 				sql语句
	 * @param params	
	 * 				参数列表	
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private void setPrepareStatementParams(String sql, Object[] params) throws SQLException{
		pstm = conn.prepareStatement(sql); // 获取对象
		if (params != null)
		{
			for (int i = 0; i < params.length; i++) // 遍历参数列表填充参数
			{
				pstm.setObject(i + 1, params[i]);
			}
		}
	}
	
	/**
	 * 执行查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return 返回ResultSet类型的查询结果
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql, Object[] params) throws SQLException{
		// 执行查询数据库接口
		ResultSet rs = null;
		manager.setPrepareStatementParams(sql, params); // 填充参数
		rs = pstm.executeQuery(); // 执行查询操作
		return rs;
	}
	
	/**
	 * 更新数据库操作
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表
	 * @return 执行操作的结果
	 * @throws SQLException
	 */
	public boolean executeUpdate(String sql, Object[] params)throws SQLException 
	{
		// 执行无返回数据的数据查询，返回值是被改变的书库的数据库项数
		boolean result = false;
		manager.setPrepareStatementParams(sql, params); // 填充参数
		pstm.executeUpdate(); // 执行更新
		manager.commitChange();
		result = true;
		return result;
	}
	
	/**
	 * 提交信息到数据库
	 * @throws SQLException
	 */
	private void commitChange() throws SQLException
	{
		conn.commit();
	}
}
