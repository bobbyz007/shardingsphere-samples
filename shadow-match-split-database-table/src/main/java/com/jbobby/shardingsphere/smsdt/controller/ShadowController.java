package com.jbobby.shardingsphere.smsdt.controller;


import com.jbobby.shardingsphere.smsdt.entity.Address;
import com.jbobby.shardingsphere.smsdt.entity.Order;
import com.jbobby.shardingsphere.smsdt.entity.OrderItem;
import com.jbobby.shardingsphere.smsdt.service.ShadowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jbobby.shardingsphere.smsdt.service.ShadowService.*;

@RestController
@RequestMapping("/shadow")
public class ShadowController {

    @Autowired
    private ShadowService shadowService;

    @PostMapping("init-env")
    public boolean cleanEnvironment() {
        shadowService.cleanEnvironment();
        shadowService.initEnvironment();
        return true;
    }

    @PostMapping("insert/{type}/{count}")
    public boolean insert(@PathVariable("type") String type, @PathVariable("count") int count) {
        if (type.equalsIgnoreCase("all")) {
            shadowService.insert(INSERT_TYPE_ORDER, count);
            shadowService.insert(INSERT_TYPE_ADDRESS, count);
        } else if (type.equalsIgnoreCase(INSERT_TYPE_ORDER)) {
            shadowService.insert(INSERT_TYPE_ORDER, count);
        } else if (type.equalsIgnoreCase(INSERT_TYPE_ADDRESS)) {
            shadowService.insert(INSERT_TYPE_ADDRESS, count);
        }
        return true;
    }

    @GetMapping("list-order/{type}")
    public List<Order> listOrder(@PathVariable("type") String type) {
        if (type.equalsIgnoreCase("all")) {
            List<Order> result = shadowService.listOrder(LIST_ORDER_TYPE_PRODUCTION);
            result.addAll(shadowService.listOrder(LIST_ORDER_TYPE_SHADOW));
            return result;
        } else  {
            return shadowService.listOrder(type);
        }
    }

    @GetMapping("list-order-item")
    public List<OrderItem> listOrderItem() {
        return shadowService.listOrderItem();
    }

    @GetMapping("list-address")
    public List<Address> listAddress() {
        return shadowService.listAddress();
    }
}
