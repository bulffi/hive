package hive.entity.wrapper;

import hive.entity.Account;
import lombok.Data;

import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class AccountList {
    private List<Account> list;
}
