package hive.controller;


import hive.entity.ReceiveInfo;
import hive.entity.wrapper.ReceiveInfoList;
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
public class ReceiveInfoController {
    @GetMapping("/receiveInfo/info")
    public Object getAllTheReceiveInfo(){
        List<ReceiveInfo> receiveInfoList = new ArrayList<>();
        // do sth with the list
        // and return it

        return receiveInfoList;
    }

    @PostMapping("/receiveInfo/update")
    public void updateAllTheReceiveInfo(@RequestBody ReceiveInfoList receiveInfoList){
        // do sth with the receive info list;



        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
