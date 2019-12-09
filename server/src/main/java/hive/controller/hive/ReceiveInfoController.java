package hive.controller.hive;


import com.f4.proto.skr.Nothing;
import com.f4.proto.skr.Receive;
import com.f4.proto.skr.hiveGrpc;
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
 * @create: 2019/12/08
 **/
@RestController
public class ReceiveInfoController {
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/receiveInfo/info")
    public Object getAllTheReceiveInfo(){
        List<ReceiveInfo> receiveInfoList = new ArrayList<>();
        // do sth with the list
        // and return it
        com.f4.proto.skr.ReceiveInfo info = hub.getReceive(Nothing.newBuilder().build());
        for (Receive r :
                info.getReceiveListList()) {
            ReceiveInfo receiveInfo = new ReceiveInfo();
            receiveInfo.setReceive_info_id(r.getReceiveInfoId());
            receiveInfo.setReceiver_account_id(r.getReceiverAccountId());
            receiveInfo.setPhone_number(r.getPhoneNumber());
            receiveInfo.setAddress(r.getAddress());
            receiveInfoList.add(receiveInfo);
        }
        return receiveInfoList;
    }

    @PostMapping("/receiveInfo/update")
    public void updateAllTheReceiveInfo(@RequestBody ReceiveInfoList receiveInfoList){
        // do sth with the receive info list;
        com.f4.proto.skr.ReceiveInfo.Builder builder = com.f4.proto.skr.ReceiveInfo.newBuilder();
        for (ReceiveInfo r :
                receiveInfoList.getList()) {
            builder.addReceiveList(Receive.newBuilder()
                    .setReceiverAccountId(r.getReceiver_account_id())
                    .setReceiveInfoId(r.getReceive_info_id())
                    .setPhoneNumber(r.getPhone_number())
                    .setAddress(r.getAddress())
                    .build());
        }
        Nothing nothing = hub.setReceiveInfo(builder.build());
        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }
}
