package cn.astriva.auth.permission;

import cn.astriva.auth.service.SysMenuService;
import cn.astriva.auth.service.SysRoleService;
import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实现SaToken的权限接口
 *
 * @author 棠野·Lyrical
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {
    /**
     * 角色映射器
     */
    private final SysRoleService sysRoleService;
    /**
     * 菜单映射器
     */
    private final SysMenuService sysMenuService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 获取当前登录者ID
        Long userId = Long.parseLong(loginId.toString());
        // 依据登录者ID获取其拥有的权限码集合
        return sysMenuService.findPermissionListByLoginId(userId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 获取当前登录者ID
        Long userId = Long.parseLong(loginId.toString());
        // 依据登录者ID获取其拥有的角色标识集合
        return sysRoleService.findRoleListByLoginId(userId);
    }
}
