package org.lq.service;

import org.lq.bean.Cart;
import org.lq.bean.Order;
import org.lq.exception.ServerException;

public interface OrderService {
	void fillAndSaveOrderInfo(Order order,Cart cart) throws ServerException;
}
