package cn.astriva.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 状态枚举
 *
 * @author Mr. Tao
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 异常
     */
    EXCEPTION(1, "异常")
    ;

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态描述
     */
    private final String desc;

    /**
     * 判断传入的值是否是合法状态
     */
    public static boolean isValid(Integer value) {
        // 当传入值为空时，返回 false
        if (value == null) {
            return false;
        }
        // 遍历枚举值，判断传入值是否合法
        for (StatusEnum e : values()) {
            // 当任意枚举值的状态值与传入值相等时，返回 true
            if (Objects.equals(e.value, value)) {
                return true;
            }
        }
        return false;
    }
}
