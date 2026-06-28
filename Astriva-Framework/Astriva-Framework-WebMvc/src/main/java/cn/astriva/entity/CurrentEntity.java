package cn.astriva.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 当前登录用户实体
 *
 * @author 棠野·Lyrical
 */
@Data
public class CurrentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1290202360143023848L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 部门ID -> 数据权限过滤
     */
    private Long deptId;
    /**
     * 昵称
     */
    private String nickname;
}
