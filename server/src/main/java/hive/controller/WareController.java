package hive.controller;


import com.f4.proto.skr.Nothing;
import com.f4.proto.skr.WareInfo;
import com.f4.proto.skr.hiveGrpc;
import hive.entity.Ware;
import hive.entity.wrapper.WareList;
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
public class WareController {
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/ware/info")
    public Object getAllTheWareInfo(){
        List<Ware> wareList = new ArrayList<>();
        // do sth with the ware list
        // and return it
        WareInfo info = hub.getWare(Nothing.newBuilder().build());
        for(com.f4.proto.skr.Ware w:info.getWareListList()){
            Ware ware = new Ware();
            ware.setGood_name(w.getGoodName());
            ware.setWare_id(w.getWareId());
            ware.setSize(w.getSize());
            ware.setPrice(w.getPrice());
            ware.setInventory(w.getInventory());
            wareList.add(ware);
        }
        return wareList;
    }

    @PostMapping("/ware/update")
    public void updateAllTheWareInfo(@RequestBody WareList wareList){
        // do sth with the ware list;
        WareInfo.Builder builder = WareInfo.newBuilder();
        for (Ware w :
                wareList.getList()) {
            builder.addWareList(com.f4.proto.skr.Ware.newBuilder()
                    .setGoodName(w.getGood_name())
                    .setWareId(w.getWare_id())
                    .setSize(w.getSize())
                    .setProducePlace(w.getProduce_place())
                    .setPrice(w.getPrice())
                    .setInventory(w.getInventory())
                    .build());
        }
        Nothing nothing = hub.setWareInfo(builder.build());
        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
