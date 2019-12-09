package hive.controller.gDFS;

import com.f4.proto.omg.*;
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
 * @create: 2019/12/09
 **/
@RestController
public class GoodController {
    @Autowired
    gDFSGrpc.gDFSBlockingStub stub;

    @GetMapping("/g/good/info")
    public Object getAllTheGoodInfo(){
        List<Good> goods = new ArrayList<>();
        TableContent content = stub.readTable(TableName.newBuilder().setName("good").build());
        String[] lines = content.getContent().split("\n");
        for (String line:
            lines ) {
            try {
                String[] att = line.split("&");
                Good good = new Good();
                good.setGood_name(att[0]);
                good.setDescription(att[1]);
                good.setDelivery_fee(Integer.parseInt(att[2]));
                goods.add(good);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return lines;
    }

    @PostMapping("/g/good/update")
    public void updateAllTheGoodInfo(@RequestBody GoodList goodList){
        StringBuilder stringToWrite = new StringBuilder();
        for (Good g :
                goodList.getList()) {
            stringToWrite.append(g.getGood_name()).append("&")
                    .append(g.getDescription()).append("&")
                    .append(g.getDelivery_fee()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder().setName("good").setContent(stringToWrite.toString()).build());
        if(status.getStatus() == 0){
            System.out.println("gDFS write error!");
        }
    }

}
