package cn.astriva.auth.pojo.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
* 角色和菜单权限关联表
* @TableName sys_role_menu
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = -1613243723890944012L;
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 菜单id
    */
    private Long menuId;
}
