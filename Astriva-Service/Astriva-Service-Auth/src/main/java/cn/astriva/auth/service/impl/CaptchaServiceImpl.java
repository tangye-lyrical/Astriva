package cn.astriva.auth.service.impl;

import cn.astriva.auth.pojo.entity.Captcha;
import cn.astriva.auth.pojo.vo.CreateCaptchaVo;
import cn.astriva.auth.service.CaptchaService;
import cn.astriva.common.constant.RedisConstant;
import cn.astriva.common.enums.SysLoginError;
import cn.astriva.common.exceptions.ValidateException;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 验证码服务实现类
 *
 * @author 棠野·Lyrical
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {
    /**
     * Redis操作
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 校验验证码
     *
     * @param captcha 验证码
     */
    @Override
    public void validateCaptcha(Captcha captcha) {
        // 从Redis中获取验证码答案
        String captchaFromRedis = (String) redisTemplate.opsForValue().get(RedisConstant.REDIS_CAPTCHA_PREFIX + captcha.getCaptchaKey());
        // 当Redis中获取的验证码答案不存在时，抛出异常
        if (StrUtil.isEmpty(captchaFromRedis)) {
            throw new ValidateException(SysLoginError.CAPTCHA_EXPIRED);
        }
        // 不论验证码是否正确，都删除Redis中的验证码答案
        redisTemplate.delete(captcha.getCaptchaKey());
        // 校验验证码是否正确
        if (!StrUtil.equals(captchaFromRedis, captcha.getInputCaptcha())) {
            throw new ValidateException(SysLoginError.CAPTCHA_ERROR);
        }
    }

    /**
     * 生成图片验证码
     *
     * @return 验证码图片base64编码
     */
    @Override
    public CreateCaptchaVo createImageCaptcha() {
        // 创建【扭曲干扰验证码】
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        // 设置验证码Key为当前时间戳
        String captchaKey = String.valueOf(System.currentTimeMillis());
        // 将生成的验证码图片使用base64编码进行传递
        String captchaBase64 = captcha.getImageBase64Data();
        // 将验证码答案存储到Redis中，过期时间为5分钟
        redisTemplate.opsForValue().set(RedisConstant.REDIS_CAPTCHA_PREFIX + captchaKey, captcha.getCode(), 5, TimeUnit.MINUTES);

        log.debug("验证码Key: {}，验证码答案: {}", captchaKey, captcha.getCode());

        // 返回验证码对象
        return CreateCaptchaVo.builder()
                .captchaKey(captchaKey)
                .imageBase64Url(captchaBase64)
                .build();
    }
}
