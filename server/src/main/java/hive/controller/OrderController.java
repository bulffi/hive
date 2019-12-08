package hive.controller;


import com.f4.proto.skr.Nothing;
import com.f4.proto.skr.OrderInfo;
import com.f4.proto.skr.hiveGrpc;
import hive.entity.Order;
import hive.entity.wrapper.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/order/info")
    public Object getAllTheOrderInfo(){
        List<Order> orderList = new ArrayList<>();
        // do sth with the order list
        // and return it
        OrderInfo info = hub.getOrder(Nothing.newBuilder().build());
        for (com.f4.proto.skr.Order o:
             info.getOrderListList() ) {
            Order order = new Order();
            order.setOrder_id(o.getOrderId());
            order.setBuyer_account_id(o.getBuyerAccountId());
            order.setGood_name(o.getGoodName());
            order.setSize(o.getSize());
            order.setProduce_place(o.getProducePlace());
            order.setAmount(o.getAmount());
            order.setYear(o.getYear());
            order.setMonth(o.getMonth());
            order.setDay(o.getDay());
            order.setPrice(o.getPrice());
            order.setReceive_address(o.getReceiveAddress());
            orderList.add(order);
        }
        return orderList;
    }
    @PostMapping("/order/update")
    public void updateAllTheOrderInfo(@RequestBody OrderList orderList){
        // do sth with the order list;
        OrderInfo.Builder builder = OrderInfo.newBuilder();
        for (Order o :
                orderList.getList()) {
            builder.addOrderList(com.f4.proto.skr.Order.newBuilder()
                    .setOrderId(o.getOrder_id())
                    .setBuyerAccountId(o.getBuyer_account_id())
                    .setGoodName(o.getGood_name())
                    .setSize(o.getSize())
                    .setProducePlace(o.getProduce_place())
                    .setAmount(o.getAmount())
                    .setYear(o.getYear())
                    .setMonth(o.getMonth())
                    .setDay(o.getDay())
                    .setPrice(o.getPrice())
                    .setReceiveAddress(o.getReceive_address())
                    .build());
        }
        Nothing nothing = hub.setOrderInfo(builder.build());
        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
