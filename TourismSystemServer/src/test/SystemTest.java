package test;

import java.util.List;

import com.tss.dao.SpotDAO;
import com.tss.dao.impl.SpotDAOImpl;
import com.tss.po.Spot;

public class SystemTest {

	public static void main(String[] args) {
//		OrderDAO orderDao = new OrderDAOImpl();
//		List<Vector> orderList = orderDao.getInfoById(1222);
//		
//		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
//		
//		for(Vector vector : orderList){
//			System.out.println(vector.get(0));
//			System.out.println(vector.get(1));
//			System.out.println(vector.get(2));
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("name", vector.get(0));
//			map.put("order_time", vector.get(1).toString());
//			map.put("total_price", vector.get(2));
//			list.add(map);
//		}
		
		SpotDAO spotDao = new SpotDAOImpl();
		List<Spot> spots = spotDao.spotInfoOrder(1);
		for(Spot s:spots){
			System.out.println(s.getIntroduction());
		}
		
		
		
//		Order order = orderDao.getOrderById(1);
//		System.out.println(order.getID());
//		System.out.println(order.getOrder_time());
//		System.out.println(order.getTotal_price());
		
//		UserDAO userDao = new UserDAOImpl();
//		User user = userDao.getInfoById(1222);
//		System.out.println(user.getName());
		
		//UserDAO userDao = new UserDAOImpl();
		
	}
}
