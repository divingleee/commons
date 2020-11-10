package com.mg.commons.utils.date;

/**
 * @author divinglee
 * @date Create in 14:58 2019/6/12
 * @description
 */
public enum DateEnum {

    FIRST(1, "一季度"),
    SECOND(2, "二季度"),
    THIRD(3, "三季度"),
    FORTH(4, "四季度");

    public Integer code;
    public String message;

    DateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static DateEnum of(Integer of) {
        switch (of) {
            case 1:
                return DateEnum.FIRST;
            case 2:
                return DateEnum.SECOND;
            case 3:
                return DateEnum.THIRD;
            case 4:
                return DateEnum.FORTH;
            default:
                return DateEnum.FIRST;
        }
    }
}
