package hive.controller;


import hive.entity.Account;
import hive.entity.wrapper.AccountList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.f4.proto.skr.*;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@RestController
public class AccountController {
    @Autowired
    hiveGrpc.hiveBlockingStub hub;
    @GetMapping("/account/info")
    public Object getAllTheAccount() throws ClassNotFoundException, SQLException {
        List<Account> list = new ArrayList<>();
        AccountInfo accountList = null;
        while (accountList == null){
            try {
                accountList = hub.getAccount(Nothing.newBuilder().build());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        List<Account> accounts = new ArrayList<>();
        for (com.f4.proto.skr.Account a :
                accountList.getAccountListList()) {
            Account account = new Account();
            account.setAccount_id(a.getAccountId());
            account.setDescription(a.getDescription());
            account.setGender(a.getGender());
            account.setNickname(a.getNickname());
            account.setPassword(a.getPassword());
            account.setGender(a.getGender());
            accounts.add(account);
        }
        return accounts;
    }

    @PostMapping("/account/update")
    public void updateAllAccount(@RequestBody AccountList accountList){
        // do sth with the account list;
        AccountInfo.Builder builder = AccountInfo.newBuilder();
        for (Account a :
                accountList.getList()) {
            com.f4.proto.skr.Account account = com.f4.proto.skr.Account.newBuilder()
                    .setAccountId(a.getAccount_id())
                    .setPassword(a.getPassword())
                    .setNickname(a.getNickname())
                    .setGender(a.getGender())
                    .setDescription(a.getDescription())
                    .build();
            builder.addAccountList(account);
        }
        try {
            Nothing nothing = hub.updateAccountInfo(builder.build());
        }catch (Exception e){
            e.printStackTrace();
        }

        // if you want a reply message, just modify the return value to object
        // and return the message as an object
    }

}
