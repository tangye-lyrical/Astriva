package cn.astriva.auth.pojo.entity;

import cn.astriva.mp.basic.AuditEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜单权限表
 *
 * @author 棠野·Lyrical
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu extends AuditEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 4150442424259052322L;
    /**
     * 菜单id
     */
    @TableId
    private Long id;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单编码，唯一，同时也是权限标识，如: system:user:list
     */
    private String menuCode;
    /**
     * 父菜单id，0 -> 顶级
     */
    private Long parentId;
    /**
     * 显示排序
     */
    private Integer orderNum;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 是否为外链: 0 -> 否，1 -> 是
     */
    private Integer isFrame;
    /**
     * 是否缓存: 0 -> 否，1 -> 是
     */
    private Integer isCache;
    /**
     * 菜单类型: m -> 目录，c -> 菜单，f -> 按钮/api
     */
    private String menuType;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 请求方法: get/post/put/delete，用于 api 级权限
     */
    private String method;
    /**
     * 状态: 0 -> 正常，1 -> 停用
     */
    private Integer status;
    /**
     * 显示状态: 0 -> 隐藏，1 -> 显示
     */
    private Integer visible;
    /**
     * 删除标志: 0 -> 存在，1 -> 删除
     */
    @TableLogic
    private Integer delFlag;
}
