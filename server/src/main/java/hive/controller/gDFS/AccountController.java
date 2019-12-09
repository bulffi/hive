package hive.controller.gDFS;

import hive.entity.Account;
import hive.entity.wrapper.AccountList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.f4.proto.omg.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/09
 **/
@RestController
public class AccountController {
    @Autowired
    gDFSGrpc.gDFSBlockingStub stub;
    @GetMapping("/g/account/info")
    public Object getAllTheAccount() {
        TableContent content = stub.readTable(TableName.newBuilder().setName("account").build());
        String[] lines = content.getContent().split("\n");
        List<Account> accounts = new ArrayList<>();
        for (String line :
                lines) {
            String[] attributes = line.split("&");
            Account account = new Account();
            try {
                account.setAccount_id(attributes[0]);
                account.setPassword(attributes[1]);
                account.setNickname(attributes[2]);
                account.setGender(Integer.parseInt(attributes[3]));
                account.setDescription(attributes[4]);
                accounts.add(account);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accounts;
    }

    @PostMapping("/g/account/update")
    public void updateAllAccount(@RequestBody AccountList accountList){
        StringBuilder stringToWrite = new StringBuilder();
        for (Account a :
                accountList.getList()) {
            stringToWrite.append(a.getAccount_id()).append("&")
                    .append(a.getPassword()).append("&")
                    .append(a.getNickname()).append("&")
                    .append(a.getGender()).append("&")
                    .append(a.getDescription()).append("\n");
        }
        Status status = stub.updateTable(Table.newBuilder()
                .setName("account")
                .setContent(stringToWrite.toString())
                .build());
        if(status.getStatus() == 0){
            System.out.println("gDFS error write document!");
        }
    }

}
