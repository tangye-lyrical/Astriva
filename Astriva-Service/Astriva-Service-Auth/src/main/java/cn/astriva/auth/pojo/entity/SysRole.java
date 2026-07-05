package cn.astriva.auth.pojo.entity;

import cn.astriva.mp.basic.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
* 角色表
* @TableName sys_role
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole extends AuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4160210663722809018L;
    /**
    * 角色id
    */
    @TableId
    private Long id;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色编码
    */
    private String roleCode;
    /**
    * 显示排序
    */
    private Integer roleSort;
    /**
    * 数据权限范围: 1 -> 全部，2 -> 本部门，3 -> 本部门及子部门，4 -> 仅本人，5 -> 自定义
    */
    private Integer dataScope;
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
