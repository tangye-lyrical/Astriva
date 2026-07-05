package cn.astriva.common.constant;

/**
 * Redis 常量类
 *
 * @author 棠野·Lyrical
 */
public class RedisConstant {
    /**
     * redis 前缀
     */
    private static final String REDIS_TITLE = "Astriva:";
    /**
     * 业务前缀 => 存储验证码答案
     */
    public static final String REDIS_CAPTCHA_PREFIX = REDIS_TITLE + "captcha:";
}
