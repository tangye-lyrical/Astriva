package cn.astriva.auth.pojo.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 验证码实体
 *
 * @author 棠野·Lyrical
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Captcha implements Serializable {
    @Serial
    private static final long serialVersionUID = -463362148227997656L;
    /**
     * 验证码 - 存储在数据库中的验证码答案
     */
    private String captcha;
    /**
     * 用户输入的验证码
     */
    private String inputCaptcha;
    /**
     * 验证码-key
     */
    private String captchaKey;
    /**
     * 验证码图片base64编码
     */
    private String imageBase64Url;
}
