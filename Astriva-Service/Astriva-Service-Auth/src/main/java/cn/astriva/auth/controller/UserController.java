package cn.astriva.auth.controller;

import cn.astriva.auth.pojo.dto.SysUserUpdatePassDto;
import cn.astriva.auth.pojo.entity.Captcha;
import cn.astriva.auth.pojo.vo.SysUserInfoVo;
import cn.astriva.auth.service.CaptchaService;
import cn.astriva.auth.service.SysUserService;
import cn.astriva.common.result.AjaxResult;
import cn.astriva.common.utils.CurrentHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户控制器 => 以普通用户操作自身用户信息
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
@Tag(name = "系统用户管理API")
public class UserController {
    /**
     * 系统用户服务
     */
    private final SysUserService sysUserService;
    /**
     * 验证码服务
     */
    private final CaptchaService captchaService;

    /**
     * 系统用户退出登录
     */
    @GetMapping("/logout")
    @Operation(summary = "当前系统用户退出登录API")
    public AjaxResult<Void> userLogout() {
        // 系统用户退出登录
        sysUserService.userLogout(CurrentHolder.getCurrentEntity().getUserId());

        return AjaxResult.success("退出登录成功！");
    }

    /**
     * 查询当前用户信息
     */
    @GetMapping("/info")
    @Operation(summary = "查询当前登录用户信息API")
    public AjaxResult<SysUserInfoVo> findUserInfo() {
        // 获取当前用户ID
        Long userId = CurrentHolder.getCurrentEntity().getUserId();
        // 调用用户服务，依据ID获取登录用户的信息
        SysUserInfoVo sysUserInfoVo = sysUserService.findUserInfo(userId);

        return AjaxResult.success("查询当前用户信息成功！", sysUserInfoVo);
    }

    /**
     * 用户修改密码
     *
     * @param updatePassDto 修改密码表单
     */
    @PostMapping("/update")
    @Operation(summary = "用户修改密码API")
    public AjaxResult<Void> updateSysUserPassword(@RequestBody @Validated SysUserUpdatePassDto updatePassDto) {
        // 校验验证码 => 使用builder模式创建验证码对象，若校验失败则抛出异常
        captchaService.validateCaptcha(
                Captcha.builder()
                        .captchaKey(updatePassDto.getCaptchaKey())
                        .inputCaptcha(updatePassDto.getInputCaptcha())
                        .build());
        // 获取当前用户ID
        Long userId = CurrentHolder.getCurrentEntity().getUserId();
        // 执行修改
        sysUserService.updateSysUserPassword(updatePassDto, userId);

        return AjaxResult.success("修改密码成功！");
    }
}
