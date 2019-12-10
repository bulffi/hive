package hive.controller.gDFS;

import com.f4.proto.nn.*;
import hive.entity.Coupon;
import hive.entity.wrapper.CouponList;
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
 * @create: 2019/12/09
 **/
@RestController
public class GCouponController {
    @Autowired
    MasterGrpc.MasterBlockingStub stub;

    @GetMapping("/g/coupon/info")
    public Object getAllTheCouponInfo() {
        TableContent content = stub.readTable(TableName.newBuilder().setName("coupon").build());
        String[] lines = content.getContent().split("\n");
        List<Coupon> coupons = new ArrayList<>();
        for (String line :
                lines) {
            try {
                String[] att = line.split("&");
                Coupon coupon = new Coupon();
                coupon.setCoupon_id(att[0]);
                coupon.setDiscount(Double.parseDouble(att[1]));
                coupon.setInventory(Integer.parseInt(att[2]));
                coupons.add(coupon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return coupons;
    }

    @PostMapping("/g/coupon/update")
    public void updateAllTheCouponInfo(@RequestBody CouponList couponList){
        StringBuilder stringToWrite = new StringBuilder();
        for (Coupon coupon: couponList.getList()) {
            stringToWrite.append(coupon.getCoupon_id()).append("&")
                    .append(coupon.getDiscount()).append("&")
                    .append(coupon.getInventory()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder()
                .setName("coupon")
                .setContent(stringToWrite.toString())
                .build());
        if(status.getStatus() == 0){
            System.out.println("gDFS write error!");
        }
    }
}
