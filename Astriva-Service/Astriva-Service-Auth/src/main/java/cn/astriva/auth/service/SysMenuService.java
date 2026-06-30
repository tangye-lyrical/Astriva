package cn.astriva.auth.service;

import java.util.List;

/**
 * 系统菜单服务接口
 * @author Mr. Tao
 */
public interface SysMenuService {
    /**
     * 根据登录者ID查询其拥有的权限码集合
     * @param userId 登录者ID
     * @return 权限码集合
     */
    List<String> findPermissionListByLoginId(Long userId);
}
