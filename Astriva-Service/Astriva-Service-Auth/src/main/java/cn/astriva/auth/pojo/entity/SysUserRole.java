package cn.astriva.auth.pojo.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
* 用户和角色关联表
* @TableName sys_user_role
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -4851378065486620538L;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 角色id
    */
    private Long roleId;
}
