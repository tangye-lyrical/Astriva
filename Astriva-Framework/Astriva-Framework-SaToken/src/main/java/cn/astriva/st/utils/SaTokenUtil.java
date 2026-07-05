package cn.astriva.st.utils;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;

import java.util.Map;

/**
 * SaToken工具类
 *
 * @author 棠野·Lyrical
 */
public class SaTokenUtil {
    /**
     * 用户登录扩展信息 - 用户名
     */
    public static final String EXTRA_USERNAME = "username";
    /**
     * 用户登录扩展信息 - 部门ID
     */
    public static final String EXTRA_DEPT_ID   = "deptId";
    /**
     * 用户登录扩展信息 - 昵称
     */
    public static final String EXTRA_NICKNAME  = "nickname";

    /**
     * 私有构造方法，避免被实例化
     */
    private SaTokenUtil() {
        throw new AssertionError("工具类禁止实例化!");
    }

    /**
     * 用户登录
     *
     * @param userId 用户ID
     *
     * @return 登录token
     */
    public static String userLogin(Long userId) {
        // 调用sa-token的login方法，以实现传入ID的用户完成登录
        StpUtil.login(userId);

        return StpUtil.getTokenValue();
    }

    /**
     * 用户登录（带JWT扩展信息）
     *
     * @param userId 用户ID
     * @param extras 会话扩展信息，将被写入登录凭证
     *
     * @return 登录token
     */
    public static String userLogin(Long userId, Map<String, Object> extras) {
        // 创建登录参数对象，并设置扩展信息
        SaLoginParameter param = new SaLoginParameter();
        // 当拓展信息不为空时，将拓展信息写入登录参数对象，避免NPE异常
        if (extras != null && !extras.isEmpty()) {
            extras.forEach(param::setExtra);
        }
        // 调用sa-token的login方法，以实现传入ID的用户完成拓展登录
        StpUtil.login(userId, param);

        return StpUtil.getTokenValue();
    }

    /**
     * 用户登出
     *
     * @param userId 用户ID
     */
    public static void userLogout(Long userId) {
        StpUtil.logout(userId);
    }

    /**
     * 当前会话是否登录
     *
     * @return 是否登录
     */
    public static boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * 获取当前登录用户ID并转换为Long类型
     *
     * @return 用户ID
     */
    public static Long getLoginIdAsLong() {
        return StpUtil.getLoginIdAsLong();
    }

    /**
     * 获取登录扩展信息
     *
     * @param key 扩展信息key
     * @return 扩展信息值
     */
    public static Object getExtra(String key) {
        return StpUtil.getExtra(key);
    }
}
