package cn.astriva.auth.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 创建图片验证码VO实体类
 *
 * @author 棠野·Lyrical
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCaptchaVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 8098735572449227233L;
    /**
     * 验证码-key
     */
    private String captchaKey;
    /**
     * 验证码图片base64编码
     */
    private String imageBase64Url;
}
