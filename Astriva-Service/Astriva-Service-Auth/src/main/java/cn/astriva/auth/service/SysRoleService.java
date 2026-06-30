package cn.astriva.auth.service;

import java.util.List;

/**
 * 系统角色服务接口
 *
 * @author Mr. Tao
 */
public interface SysRoleService {
    /**
     * 根据登录ID查询角色标识列表
     *
     * @param userId 登录ID
     * @return 角色标识列表
     */
    List<String> findRoleListByLoginId(Long userId);
}
