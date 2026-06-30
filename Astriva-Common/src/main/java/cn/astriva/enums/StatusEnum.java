package cn.astriva.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
