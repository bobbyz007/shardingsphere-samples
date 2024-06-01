package com.jbobby.shardingsphere.smsdt.service.impl;

import com.jbobby.shardingsphere.smsdt.entity.Address;
import com.jbobby.shardingsphere.smsdt.entity.Order;
import com.jbobby.shardingsphere.smsdt.entity.OrderItem;
import com.jbobby.shardingsphere.smsdt.mapper.AddressRepository;
import com.jbobby.shardingsphere.smsdt.mapper.OrderItemRepository;
import com.jbobby.shardingsphere.smsdt.mapper.OrderRepository;
import com.jbobby.shardingsphere.smsdt.service.ShadowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShadowServiceImpl implements ShadowService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void initEnvironment() {
        orderRepository.createTableIfNotExists();
        orderItemRepository.createTableIfNotExists();
        addressRepository.createTableIfNotExists();

        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        addressRepository.truncateTable();

        // init shadow table through sql hint
        orderRepository.createTableIfNotExistsShadow();
        orderRepository.truncateTableShadow();

        // for boradcast table t_address
        addressRepository.createTableIfNotExistsShadow();
        addressRepository.truncateTableShadow();
    }

    @Override
    public void cleanEnvironment() {
        orderRepository.dropTableShadow();
        addressRepository.dropTableShadow(); // for broadcast table t_address in shadow db

        orderRepository.dropTable();
        orderItemRepository.dropTable();
        addressRepository.dropTable();
    }

    @Override
    public boolean insert(String type, int count) {
        List<Integer> result = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            if (type.equalsIgnoreCase(INSERT_TYPE_ORDER)) {
                Order order = new Order();
                order.setUserId(i);
                order.setOrderType(i % 2);
                order.setAddressId(i);
                order.setStatus("INSERT_TEST");
                orderRepository.insert(order);

                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getOrderId());
                orderItem.setUserId(i);
                orderItem.setPhone("13800000001");
                orderItem.setStatus("INSERT_TEST");
                orderItemRepository.insert(orderItem);
            } else if (type.equalsIgnoreCase(INSERT_TYPE_ADDRESS)) {
                Address address = new Address();
                address.setAddressId((long) i);
                address.setAddressName("address_test_" + i);
                addressRepository.insert(address);
            }
        }
        return true;
    }

    @Override
    public List<Order> listOrder(String type) {
        if (type.equalsIgnoreCase(LIST_ORDER_TYPE_PRODUCTION)) {
            return orderRepository.selectAll();
        } else if (type.equalsIgnoreCase(LIST_ORDER_TYPE_SHADOW)) {
            return orderRepository.selectShadowOrder();
        }
        return new ArrayList<>();
    }

    @Override
    public List<OrderItem> listOrderItem() {
        return orderItemRepository.selectAll();
    }

    @Override
    public List<Address> listAddress() {
        return addressRepository.selectAll();
    }
}
