package cn.astriva.auth.service;

import cn.astriva.auth.pojo.dto.IUSysUserDto;
import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.dto.SysUserUpdatePassDto;
import cn.astriva.auth.pojo.entity.SysLogin;
import cn.astriva.auth.pojo.queryParam.SysUserQueryParam;
import cn.astriva.auth.pojo.vo.SysUserInfoVo;
import cn.astriva.auth.pojo.vo.SysUserPageVo;
import cn.astriva.common.result.PageResult;
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

    /**
     * 系统用户退出登录
     *
     * @param userId 用户ID
     */
    void userLogout(Long userId);

    /**
     * 查询当前用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUserInfoVo findUserInfo(Long userId);

    /**
     * 新增系统用户
     *
     * @param sysUserDto 新增系统用户表单
     */
    void insertSysUser(IUSysUserDto sysUserDto);

    /**
     * 管理员修改系统用户
     *
     * @param sysUserDto 修改系统用户表单
     * @param userId 被修改的用户ID
     */
    void updateSysUser(IUSysUserDto sysUserDto, Long userId);

    /**
     * 管理员批量删除系统用户
     *
     * @param userIds 被删除的用户ID列表
     */
    void deleteSysUserByIds(Long[] userIds);

    /**
     * 管理员修改系统用户状态
     *
     * @param userId 被修改的用户ID
     * @param status 状态
     */
    void updateSysUserStatus(Long userId, Integer status);

    /**
     * 分页查询系统用户
     *
     * @param sysUserQueryParam 系统用户查询参数
     * @return 系统用户分页结果
     */
    PageResult<SysUserPageVo> pageSysUser(SysUserQueryParam sysUserQueryParam);

    /**
     * 重置系统用户密码
     * @param id 用户ID
     */
    void resetSysUserPassword(Long id);

    /**
     * 系统用户修改密码
     * @param updatePassDto 修改密码表单
     * @param id 用户ID
     */
    void updateSysUserPassword(SysUserUpdatePassDto updatePassDto, Long id);
}
