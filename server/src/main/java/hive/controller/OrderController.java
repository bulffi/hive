package hive.controller;


import hive.entity.Order;
import hive.entity.wrapper.OrderList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@RestController
public class OrderController {
    @GetMapping("/order/info")
    public Object getAllTheOrderInfo(){
        List<Order> orderList = new ArrayList<>();
        // do sth with the order list
        // and return it

        return orderList;
    }
    @PostMapping("/order/update")
    public void updateAllTheOrderInfo(@RequestBody OrderList orderList){
        // do sth with the order list;



        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
