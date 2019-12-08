package hive.entity.wrapper;

import hive.entity.Coupon;
import lombok.Data;

import java.util.List;

/**
 * @program: hive
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/08
 **/
@Data
public class CouponList {
    private List<Coupon> list;
}
