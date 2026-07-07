package cn.astriva.auth.controller;

import cn.astriva.st.annotation.NotLogin;
import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.Captcha;
import cn.astriva.auth.pojo.entity.SysLogin;
import cn.astriva.auth.pojo.vo.CreateCaptchaVo;
import cn.astriva.auth.service.CaptchaService;
import cn.astriva.auth.service.SysUserService;
import cn.astriva.common.basic.ServiceException;
import cn.astriva.common.result.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权控制器
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "系统用户鉴权API")
public class AuthController {
    /**
     * 系统用户服务
     */
    private final SysUserService sysUserService;
    /**
     * 验证码服务
     */
    private final CaptchaService captchaService;

    /**
     * 系统用户登录 -> 无需登录即可访问
     *
     * @param sysLoginDto 登录参数
     * @return 登录结果
     */
    @NotLogin
    @PostMapping("/login")
    @Operation(summary = "系统用户登录API")
    public AjaxResult<SysLogin> sysLogin(@Valid @RequestBody SysLoginDto sysLoginDto) {
        log.debug("系统用户{}正在登录", sysLoginDto.getUsername());

        // 在截获异常前，先初始化登录结果为null
        SysLogin sysLogin = null;
        // 当系统用户登录异常时，捕获异常并记录日志，同时重新抛出异常
        try {
            // 校验验证码 => 使用builder模式创建验证码对象，若校验失败则抛出异常
            captchaService.validateCaptcha(
                    Captcha.builder()
                            .captchaKey(sysLoginDto.getCaptchaKey())
                            .inputCaptcha(sysLoginDto.getInputCaptcha())
                            .build()
            );
            // 系统用户登录
            sysLogin = sysUserService.sysLogin(sysLoginDto);
        } catch (ServiceException e) {
            log.error("系统用户登录异常：{}", e.getMessage());
            // 重新抛出异常，让前端捕获并处理
            throw e;
        }

        return AjaxResult.success("登录成功！", sysLogin);
    }

    /**
     * 获取验证码 -> 无需登录即可访问
     *
     * @return 验证码图片
     */
    @NotLogin
    @PostMapping("/captcha")
    @Operation(summary = "获取图片验证码API")
    public AjaxResult<CreateCaptchaVo> getImageCaptcha() {
        // 生成图片验证码
        CreateCaptchaVo captcha = captchaService.createImageCaptcha();

        return AjaxResult.success("获取验证码成功！", captcha);
    }
}
