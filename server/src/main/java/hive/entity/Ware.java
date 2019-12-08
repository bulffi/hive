package hive.entity;

import lombok.Data;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class Ware {
    private String good_name;
    private String ware_id;
    private String size;
    private String produce_place;
    private double price;
    private int inventory;
}
