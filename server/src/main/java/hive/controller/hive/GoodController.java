package hive.controller.hive;


import com.f4.proto.skr.GoodInfo;
import com.f4.proto.skr.Nothing;
import com.f4.proto.skr.hiveGrpc;
import hive.entity.Good;
import hive.entity.wrapper.GoodList;
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
public class GoodController {
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/good/info")
    public Object getAllTheGoodInfo(){
        List<Good> goodList = new ArrayList<>();
        // do sth with the good list
        // and return it
        try{
            GoodInfo info = hub.getGood(Nothing.newBuilder().build());
            for (com.f4.proto.skr.Good g :
                    info.getGoodListList()) {
                Good good = new Good();
                good.setGood_name(g.getGoodName());
                good.setDescription(g.getDescription());
                good.setDelivery_fee(g.getDeliveryFee());
                goodList.add(good);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return goodList;
    }


    @PostMapping("/good/update")
    public void updateAllTheGoodInfo(@RequestBody GoodList goodList){
        // do sth with the good list;
        GoodInfo.Builder builder = GoodInfo.newBuilder();
        for (Good g :
                goodList.getList()) {
            builder.addGoodList(com.f4.proto.skr.Good.newBuilder()
                    .setGoodName(g.getGood_name())
                    .setDescription(g.getDescription())
                    .setDeliveryFee(g.getDelivery_fee())
                    .build());
        }
        Nothing nothing = hub.setGoodInfo(builder.build());
        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
