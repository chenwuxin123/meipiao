package com.meipiao.entity;

/**
 * 枚举类型
 * @Author: Chenwx
 * @Date: 2020/5/19 17:21
 */
public enum IncrType {
    hotel_incr_inv("Inventory"),
    hotel_incr_rate("Rate"),
    hotel_incr_order("Order"),
    hotel_incr_data("Data"),
    hotel_incr_state("State");

    private String incrType;

    IncrType(String incrType) {
        this.incrType = incrType;
    }

    public String getIncrType() {
        return incrType;
    }

    public static void main(String[] args) {
        String incrType1 = IncrType.hotel_incr_inv.getIncrType();
        String incrType2 = IncrType.hotel_incr_rate.getIncrType();
        String incrType3 = IncrType.hotel_incr_order.getIncrType();
        String incrType4 = IncrType.hotel_incr_data.getIncrType();
        String incrType5 = IncrType.hotel_incr_state.getIncrType();
        System.out.println("通过枚举获取的值："+incrType1);
        System.out.println("通过枚举获取的值："+incrType2);
        System.out.println("通过枚举获取的值："+incrType3);
        System.out.println("通过枚举获取的值："+incrType4);
        System.out.println("通过枚举获取的值："+incrType5);
    }
}




