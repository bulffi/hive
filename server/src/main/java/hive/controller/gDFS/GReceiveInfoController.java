package hive.controller.gDFS;

import com.f4.proto.nn.*;
import hive.entity.ReceiveInfo;
import hive.entity.wrapper.ReceiveInfoList;
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
public class GReceiveInfoController {
    @Autowired
    MasterGrpc.MasterBlockingStub stub;

    @GetMapping("/g/receiveInfo/info")
    public Object getAllTheReceiveInfo(){
        List<ReceiveInfo> receiveInfos = new ArrayList<>();
        TableContent content = stub.readTable(TableName.newBuilder().setName("receive").build());
        String[] lines = content.getContent().split("\n");
        for (String line :
                lines) {
            String[] att = line.split("&");
            ReceiveInfo receiveInfo = new ReceiveInfo();
            try {
                receiveInfo.setReceive_info_id(att[0]);
                receiveInfo.setReceiver_account_id(att[1]);
                receiveInfo.setPhone_number(att[2]);
                receiveInfo.setAddress(att[3]);
                receiveInfos.add(receiveInfo);
            }catch (Exception e){
                System.out.println(line);
                e.printStackTrace();
            }
        }
        return receiveInfos;
    }

    @PostMapping("/g/receiveInfo/update")
    public void updateAllTheReceiveInfo(@RequestBody ReceiveInfoList receiveInfoList){
        StringBuilder stringToWrite = new StringBuilder();
        for (ReceiveInfo info :
                receiveInfoList.getList()) {
            stringToWrite.append(info.getReceive_info_id()).append("&")
                    .append(info.getReceiver_account_id()).append("&")
                    .append(info.getPhone_number()).append("&")
                    .append(info.getAddress()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder().setName("receive").setContent(stringToWrite.toString()).build());
        if(status.getStatus() == 0){
            System.out.println("gDFS write error!");
        }
    }
}
