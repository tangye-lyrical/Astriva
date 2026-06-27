package cn.astriva.utils;

import cn.astriva.entity.CurrentEntity;


/**
 * 当前上下文持有者数据
 *
 * @author 棠野·Lyrical
 */
public class CurrentHolder {
    private static final ThreadLocal<CurrentEntity> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前登录用户实体
     *
     * @param currentEntity 当前登录用户实体
     */
    public static void setCurrentEntity(CurrentEntity currentEntity) {
        threadLocal.set(currentEntity);
    }

    /**
     * 获取当前登录用户实体
     *
     * @return 当前登录用户实体
     */
    public static CurrentEntity getCurrentEntity() {
        return threadLocal.get();
    }

    /**
     * 清除当前登录用户实体
     */
    public static void clear() {
        threadLocal.remove();
    }
}
