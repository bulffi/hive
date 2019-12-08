package hive.controller;


import com.f4.proto.skr.CouponInfo;
import com.f4.proto.skr.Nothing;
import com.f4.proto.skr.hiveGrpc;
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
 * @create: 2019/12/08
 **/
@RestController
public class CouponController {
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/coupon/info")
    public Object getAllTheCouponInfo(){
        List<Coupon> couponList = new ArrayList<>();
        CouponInfo info = null;
        while (info == null){
            try {
                info = hub.getCoupon(Nothing.newBuilder().build());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (com.f4.proto.skr.Coupon x :
                info.getCouponListList()) {
            Coupon c  = new Coupon();
            c.setCoupon_id(x.getCouponId());
            c.setDiscount(x.getDiscount());
            c.setInventory(x.getInventory());
            couponList.add(c);
        }
        // do sth with the coupon list
        // and return it
        return couponList;
    }
    @PostMapping("/coupon/update")
    public void updateAllTheCouponInfo(@RequestBody CouponList couponList){
        // do sth with the coupon list;



        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }

}
