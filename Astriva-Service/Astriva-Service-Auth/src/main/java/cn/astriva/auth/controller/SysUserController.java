package cn.astriva.auth.controller;

import cn.astriva.auth.pojo.dto.IUSysUserDto;
import cn.astriva.auth.pojo.queryParam.SysUserQueryParam;
import cn.astriva.auth.pojo.vo.SysUserInfoVo;
import cn.astriva.auth.pojo.vo.SysUserPageVo;
import cn.astriva.auth.service.SysUserService;
import cn.astriva.common.basic.Create;
import cn.astriva.common.basic.Update;
import cn.astriva.common.constant.PermissionConstant;
import cn.astriva.common.enums.StatusEnum;
import cn.astriva.common.enums.SysUserError;
import cn.astriva.common.exceptions.ValidateException;
import cn.astriva.common.result.AjaxResult;
import cn.astriva.common.result.PageResult;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.PhoneUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户控制器 => 以管理员操作管理系统用户信息
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/sysUser")
@Tag(name = "系统用户管理Api")
public class SysUserController {
    /**
     * 系统用户服务
     */
    private final SysUserService sysUserService;

    /**
     * 依据ID查询用户信息
     */
    @GetMapping("/{userId}")
    @Operation(summary = "依据Id查询用户信息API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_QUERY)
    public AjaxResult<SysUserInfoVo> findUserInfoById(@Parameter(description = "用户ID") @PathVariable Long userId) {
        // 调用用户服务，依据ID获取登录用户的信息
        SysUserInfoVo sysUserInfoVo = sysUserService.findUserInfo(userId);

        return AjaxResult.success("查询用户信息成功！", sysUserInfoVo);
    }

    /**
     * 新增系统用户
     *
     * @param sysUserDto 新增系统用户表单
     */
    @PostMapping
    @Operation(summary = "管理员新增系统用户信息API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_ADD)
    public AjaxResult<Void> insertSysUser(@RequestBody @Validated(Create.class) IUSysUserDto sysUserDto) {
        // 手机号是必填项，使用Hutool校验手机号格式
        if (!PhoneUtil.isPhone(sysUserDto.getPhone())) {
            throw new ValidateException(SysUserError.PHONE_ERROR);
        }
        // 执行新增
        sysUserService.insertSysUser(sysUserDto);

        return AjaxResult.success("新增系统用户成功！");
    }

    /**
     * 修改系统用户
     *
     * @param sysUserDto 修改系统用户表单
     * @param userId     被系统用户ID
     */
    @PutMapping("/{userId}")
    @Operation(summary = "管理员修改系统用户信息API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_UPDATE)
    public AjaxResult<Void> updateSysUser(@RequestBody @Validated(Update.class) IUSysUserDto sysUserDto, @PathVariable @Parameter(description = "用户ID") Long userId) {
        // 执行修改
        sysUserService.updateSysUser(sysUserDto, userId);

        return AjaxResult.success("修改系统用户成功！");
    }

    /**
     * 批量删除系统用户
     *
     * @param userIds 被删除的系统用户ID
     */
    @DeleteMapping
    @Operation(summary = "管理员批量删除系统用户API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_DELETE)
    public AjaxResult<Void> deleteSysUserByIds(@Parameter(description = "用户ID数组") @RequestParam("userIds") Long[] userIds) {
        // 执行删除
        sysUserService.deleteSysUserByIds(userIds);

        return AjaxResult.success("批量删除系统用户成功！");
    }

    /**
     * 修改系统用户状态
     *
     * @param userId 被修改的系统用户ID
     * @param status 用户状态
     */
    @PutMapping("/{userId}/status")
    @Operation(summary = "管理员修改系统用户状态API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_UPDATE)
    public AjaxResult<Void> updateSysUserStatus(@Parameter(description = "用户ID") @PathVariable Long userId, @Parameter(description = "用户状态") Integer status) {
        // 校验用户状态是否为0=>正常或1=>异常
        if (!StatusEnum.isValid(status)) {
            throw new ValidateException(SysUserError.STATUS_ERROR);
        }
        // 执行修改
        sysUserService.updateSysUserStatus(userId, status);

        return AjaxResult.success("修改系统用户状态成功！");
    }

    /**
     * 分页查询系统用户
     * @param sysUserQueryParam 系统用户分页查询参数
     *
     * @return 分页查询系统用户结果
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询系统用户API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_QUERY)
    public AjaxResult<PageResult<SysUserPageVo>> selectSysUserPage(@Validated SysUserQueryParam sysUserQueryParam) {
        // 执行分页查询
        PageResult<SysUserPageVo> pageResult = sysUserService.pageSysUser(sysUserQueryParam);

        return AjaxResult.success("分页查询系统用户成功！", pageResult);
    }

    /**
     * 管理员重置系统用户密码
     *
     * @param id 被重置用户的ID
     */
    @PostMapping("/reset/{id}")
    @Operation(summary = "管理员重置系统用户密码API")
    @SaCheckPermission(value = PermissionConstant.SYS_USER_UPDATE)
    public AjaxResult<Void> resetSysUserPassword(@PathVariable Long id) {
        // 执行重置
        sysUserService.resetSysUserPassword(id);

        return AjaxResult.success("重置密码成功！");
    }
}
