package cn.astriva.auth.service;

import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.SysLogin;
import jakarta.validation.Valid;

/**
 * 系统用户服务接口
 *
 * @author Mr. Tao
 */
public interface SysUserService {
    /**
     * 系统用户登录
     *
     * @param sysLoginDto 系统登录登录表单
     * @return 系统登录返回信息
     */
    SysLogin sysLogin(@Valid SysLoginDto sysLoginDto);
}
