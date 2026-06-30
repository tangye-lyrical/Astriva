package cn.astriva.auth.pojo.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统登录实体
 *
 * @author 棠野·Lyrical
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLogin implements Serializable {
    @Serial
    private static final long serialVersionUID = -2583764024651379950L;
    /**
     * 登录用户的昵称
     */
    private String nickname;
    /**
     * 登录用户的账号
     */
    private String username;
    /**
     * 登录用户的头像路径
     */
    private String avtarUrl;
    /**
     * 登录令牌
     */
    private String token;
}
