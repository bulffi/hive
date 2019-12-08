package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class Good {
    private String good_name;
    private double delivery_fee;
    private String description;
}
