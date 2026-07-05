package cn.astriva.auth.pojo.dto;

import cn.astriva.common.basic.Create;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * 管理员操作系统用户表单
 *
 * @author 棠野·Lyrical
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IUSysUserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8529751778663821918L;

    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空", groups = {Create.class})
    private String nickname;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {Create.class})
    @Pattern(regexp = "^[a-zA-Z](?!.*?__)[a-zA-Z0-9_]{4,19}$", message = "用户名格式不正确")
    private String username;

    /**
     * 真实姓名
     */
    @NotNull(message = "真实姓名不能为空", groups = {Create.class})
    @Length(min = 2, max = 5, message = "真实姓名长度不正确")
    private String realName;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = {Create.class})
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 性别: 0 -> 男，1 -> 女，2 -> 未知
     */
    @NotNull(message = "性别不能为空", groups = {Create.class})
    @Range(min = 0, max = 2, message = "性别范围不正确")
    private Integer sex;

    /**
     * 头像地址
     */
    @NotNull(message = "头像地址不能为空", groups = {Create.class})
    private String avatar;

    /**
     * 部门ID
     */
    private Long deptId;
}
