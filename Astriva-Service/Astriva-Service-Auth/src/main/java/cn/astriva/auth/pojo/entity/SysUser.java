package cn.astriva.auth.pojo.entity;

import cn.astriva.basic.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统用户实体类
 *
 * @author 棠野·Lyrical
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends AuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5833983998273791041L;
    /**
     * 用户id
     */
    @TableId
    private Long id;
    /**
     * 部门id，0 -> 未分配
     */
    private Long deptId;
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
     * 用户类型: 0 -> 系统管理员，1 -> 系统用户
     */
    private Integer userType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别: 0 -> 男，1 -> 女，2 -> 未知
     */
    private Integer sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态: 0 -> 正常，1 -> 停用
     */
    private Integer status;
    /**
     * 删除标志: 0 -> 存在，1 -> 删除
     */
    @TableLogic
    private Integer delFlag;
}
