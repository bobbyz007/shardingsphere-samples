package com.jbobby.shardingsphere.smsdt.service;

import com.jbobby.shardingsphere.smsdt.entity.Address;
import com.jbobby.shardingsphere.smsdt.entity.Order;
import com.jbobby.shardingsphere.smsdt.entity.OrderItem;

import java.util.List;

public interface ShadowService {
    String INSERT_TYPE_ORDER = "order";
    String INSERT_TYPE_ADDRESS = "address";

    String LIST_ORDER_TYPE_PRODUCTION = "production";
    String LIST_ORDER_TYPE_SHADOW = "shadow";

    void initEnvironment();

    void cleanEnvironment();

    /**
     * @param type: order/order-item/address
     * @param count
     * @return inserted count list
     */
    boolean insert(String type, int count);

    /**
     * @param type: shadow/product
     * @return
     */
    List<Order> listOrder(String type);

    List<OrderItem> listOrderItem();

    List<Address> listAddress();
}