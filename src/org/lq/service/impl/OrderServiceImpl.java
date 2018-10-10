package org.lq.service.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.lq.bean.Cart;
import org.lq.bean.CartItem;
import org.lq.bean.Order;
import org.lq.bean.OrderItem;
import org.lq.dao.OrderDao;
import org.lq.dao.impl.OrderDaoImpl;
import org.lq.exception.ServerException;
import org.lq.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao dao = new OrderDaoImpl(); 
	@Override
	public void fillAndSaveOrderInfo(Order order, Cart cart) throws ServerException {
		
		// 存入数据库
		
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPayState(0);
		Collection<CartItem> cartItems = cart.getItemsMap().values();
		List<OrderItem> items = new LinkedList<>();
		for(CartItem cartItem : cartItems) {
			OrderItem it = new OrderItem();
			it.setBook(cartItem.getBook());
			it.setNumber(cartItem.getNumbers());
			items.add(it);
		}
		order.setItems(items);
		
		try {
			dao.save(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}

}
