package cn.astriva.common.constant;

/**
 * 权限常量类
 *
 * @author 棠野·Lyrical
 */
public class PermissionConstant {
    // ========== 系统权限前缀 ==========
    /**
     * 系统权限前缀
     */
    private static final String SYS_PREFIX = "system:";

    // ========== 系统模块权限前缀 ==========
    /**
     * 系统模块权限前缀 => 系统用户模块
     */
    private static final String SYS_USER_PREFIX = SYS_PREFIX + "user:";
    /**
     * 系统模块权限前缀 => 系统角色模块
     */
    private static final String SYS_ROLE_PREFIX = SYS_PREFIX + "role:";
    /**
     * 系统模块权限前缀 => 系统菜单模块
     */
    private static final String SYS_Menu_PREFIX = SYS_PREFIX + "menu:";
    /**
     * 系统模块权限前缀 => 系统部门模块
     */
    private static final String SYS_DEPT_PREFIX = SYS_PREFIX + "dept:";
    /**
     * 系统模块权限前缀 => 系统岗位模块
     */
    private static final String SYS_POST_PREFIX = SYS_PREFIX + "post:";

    // ========== 功能类型 ==========
    /**
     * 查询
     */
    private static final String QUERY = "query";
    /**
     * 新增
     */
    private static final String ADD = "add";
    /**
     * 修改
     */
    private static final String UPDATE = "edit";
    /**
     * 删除
     */
    private static final String DELETE = "remove";

    // ========== 功能权限 ==========
    /**
     * 系统用户权限 => 查询
     */
    public static final String SYS_USER_QUERY = SYS_USER_PREFIX + QUERY;
    /**
     * 系统用户权限 => 新增
     */
    public static final String SYS_USER_ADD = SYS_USER_PREFIX + ADD;
    /**
     * 系统用户权限 => 修改
     */
    public static final String SYS_USER_UPDATE = SYS_USER_PREFIX + UPDATE;
    /**
     * 系统用户权限 => 删除
     */
    public static final String SYS_USER_DELETE = SYS_USER_PREFIX + DELETE;
}
