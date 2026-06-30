package cn.astriva.auth.service;

import cn.astriva.auth.pojo.entity.Captcha;
import cn.astriva.auth.pojo.vo.CreateCaptchaVo;

/**
 * 验证码服务
 *
 * @author Mr. Tao
 */
public interface CaptchaService {
    /**
     * 校验验证码
     *
     * @param captcha 验证码
     */
    void validateCaptcha(Captcha captcha);

    /**
     * 生成图片验证码
     *
     * @return 验证码图片base64编码
     */
    CreateCaptchaVo createImageCaptcha();
}
