package cn.astriva.auth.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户分页查询VO
 *
 * @author 棠野·Lyrical
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserPageVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 7913981030774611415L;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 所属角色
     */
    private String roleName;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
