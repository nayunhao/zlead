package com.zlead.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getOrderIdByUUId(String machineId) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            //有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0 , 13 代表长度为13 , d 代表参数为正数型
        String orderId=machineId + String.format("%013d", hashCodeV);
        System.out.println(orderId);
        return orderId;
    }

}
