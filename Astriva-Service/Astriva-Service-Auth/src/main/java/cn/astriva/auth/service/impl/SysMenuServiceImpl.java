package cn.astriva.auth.service.impl;

import cn.astriva.auth.mapper.SysMenuMapper;
import cn.astriva.auth.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统菜单服务实现类
 *
 * @author 棠野·Lyrical
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    /**
     * 菜单映射器
     */
    private final SysMenuMapper sysMenuMapper;

    /**
     * 根据登录者ID查询其拥有的权限码集合
     *
     * @param userId 登录者ID
     * @return 权限码集合
     */
    @Override
    @Cacheable(value = "Astriva:permissions", key = "#userId")
    public List<String> findPermissionListByLoginId(Long userId) {
        return sysMenuMapper.findMenuCodeByUserId(userId);
    }
}
