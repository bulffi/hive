package com.f4.hive.controller;

import com.f4.hive.entity.Coupon;
import com.f4.hive.entity.wrapper.CouponList;
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
    @GetMapping("/coupon/info")
    public Object getAllTheCouponInfo(){
        List<Coupon> couponList = new ArrayList<>();
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
