package cn.astriva.auth.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户信息VO
 *
 * @author 棠野·Lyrical
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserInfoVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 632744734408261993L;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 登录账号
     */
    @Schema(description = "登录账号")
    private String username;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 用户类型: 0 -> 系统管理员，1 -> 系统用户
     */
    @Schema(description = "用户类型")
    private String userType;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 性别: 0 -> 男，1 -> 女，2 -> 未知
     */
    @Schema(description = "性别: 0 -> 男，1 -> 女，2 -> 未知")
    private String sex;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String avatar;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
