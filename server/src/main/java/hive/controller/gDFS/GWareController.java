package hive.controller.gDFS;

import com.f4.proto.nn.*;
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
 * @create: 2019/12/09
 **/
@RestController
public class GWareController {
    @Autowired
    MasterGrpc.MasterBlockingStub stub;

    @GetMapping("/g/ware/info")
    public Object getAllTheWareInfo(){
        List<Ware> wares = new ArrayList<>();
        TableContent content = stub.readTable(TableName.newBuilder().setName("ware").build());
        String[] lines = content.getContent().split("\n");
        for (String line :
                lines) {
            Ware ware = new Ware();
            String[] att = line.split("&");
            try {
                ware.setGood_name(att[0]);
                ware.setWare_id(att[1]);
                ware.setSize(att[2]);
                ware.setProduce_place(att[3]);
                ware.setPrice(Double.parseDouble(att[4]));
                ware.setInventory(Integer.parseInt(att[5]));
                wares.add(ware);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return wares;
    }

    @PostMapping("/g/ware/update")
    public void updateAllTheWareInfo(@RequestBody WareList wareList){
        StringBuilder stringToWrite = new StringBuilder();
        for (Ware w :
                wareList.getList()) {
            stringToWrite.append(w.getGood_name()).append("&")
                    .append(w.getWare_id()).append("&")
                    .append(w.getSize()).append("&")
                    .append(w.getProduce_place()).append("&")
                    .append(w.getPrice()).append("&")
                    .append(w.getInventory()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder().setName("ware").setContent(stringToWrite.toString()).build());
        if(status.getStatus() == 0){
            System.out.println("gDFS write error!");
        }
    }
}
