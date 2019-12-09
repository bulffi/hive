package hive.controller.gDFS;

import com.f4.proto.omg.*;
import hive.entity.Order;
import hive.entity.wrapper.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/09
 **/
@Controller
public class OrderController {
    @Autowired
    gDFSGrpc.gDFSBlockingStub stub;

    @GetMapping("/g/order/info")
    public Object getAllTheOrderInfo(){
        List<Order> orders = new ArrayList<>();
        TableContent content = stub.readTable(TableName.newBuilder().setName("order").build());
        String[] lines = content.getContent().split("\n");
        for (String line :
                lines) {
            Order order = new Order();
            String[] att = line.split("&");
            try {
                order.setOrder_id(att[0]);
                order.setBuyer_account_id(att[1]);
                order.setGood_name(att[2]);
                order.setSize(att[3]);
                order.setProduce_place(att[4]);
                order.setAmount(Integer.parseInt(att[5]));
                order.setYear(Integer.parseInt(att[6]));
                order.setMonth(Integer.parseInt(att[7]));
                order.setDay(Integer.parseInt(att[8]));
                order.setPrice(Double.parseDouble(att[9]));
                order.setReceive_address(att[10]);
                orders.add(order);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return orders;
    }

    @PostMapping("/g/order/update")
    public void updateAllTheOrderInfo(@RequestBody OrderList orderList){
        StringBuilder stringToWrite = new StringBuilder();
        for(Order order:orderList.getList()){
            stringToWrite.append(order.getOrder_id()).append("&")
                    .append(order.getBuyer_account_id()).append("&")
                    .append(order.getGood_name()).append("&")
                    .append(order.getSize()).append("&")
                    .append(order.getProduce_place()).append("&")
                    .append(order.getAmount()).append("&")
                    .append(order.getYear()).append("&")
                    .append(order.getMonth()).append("&")
                    .append(order.getDay()).append("&")
                    .append(order.getPrice()).append("&")
                    .append(order.getReceive_address()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder().setName("order").setContent(stringToWrite.toString()).build());
        if(status.getStatus() == 0){
            System.out.println("gDFS write error!");
        }
    }
}
