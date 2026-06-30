package cn.astriva.auth.service.impl;

import cn.astriva.auth.mapper.SysRoleMapper;
import cn.astriva.auth.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色服务实现类
 *
 * @author 棠野·Lyrical
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    /**
     * 角色映射器
     */
    private final SysRoleMapper sysRoleMapper;

    /**
     * 根据登录ID查询角色标识列表
     *
     * @param userId 登录ID
     * @return 角色标识列表
     */
    @Override
    @Cacheable(value = "Astriva:roles:", key = "#userId")
    public List<String> findRoleListByLoginId(Long userId) {
        return sysRoleMapper.selectListByUserId(userId);
    }
}
