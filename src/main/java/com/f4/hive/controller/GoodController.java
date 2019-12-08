package com.f4.hive.controller;

import com.f4.hive.entity.Good;
import com.f4.hive.entity.wrapper.CouponList;
import com.f4.hive.entity.wrapper.GoodList;
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
public class GoodController {
    @GetMapping("/good/info")
    public Object getAllTheGoodInfo(){
        List<Good> goodList = new ArrayList<>();
        // do sth with the good list
        // and return it

        return goodList;
    }


    @PostMapping("/good/update")
    public void updateAllTheGoodInfo(@RequestBody GoodList goodList){
        // do sth with the good list;



        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
