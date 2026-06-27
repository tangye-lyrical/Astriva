package cn.astriva.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 审核实体类
 *
 * @author 棠野·Lyrical
 */
@Getter
@Setter
public class AuditEntity extends BasicEntity {
    @Serial
    private static final long serialVersionUID = -4834572872328840815L;
    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
