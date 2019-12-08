package com.f4.hive.entity.wrapper;

import com.f4.hive.entity.Good;
import lombok.Data;

import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class GoodList {
    private List<Good> list;
}
