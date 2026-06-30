package cn.astriva.utils;

import cn.astriva.basic.CurrentEntity;

/**
 * 当前上下文持有者数据
 *
 * @author 棠野·Lyrical
 */
public class CurrentHolder {
    /**
     * 当前上下文线程
     * <p>实现 InheritableThreadLocal，确保子线程继承父线程的上下文</p>
     */
    private static final ThreadLocal<CurrentEntity> threadLocal = new InheritableThreadLocal<>();

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
