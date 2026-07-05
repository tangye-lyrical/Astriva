package cn.astriva.auth.pojo.queryParam;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统用户分页查询参数
 *
 * @author 棠野·Lyrical
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SysUserQueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 1258754668084721339L;

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    @Schema(description = "页码")
    private Integer page;

    /**
     * 每页展示记录数
     */
    @NotNull(message = "每页展示记录数不能为空")
    @Min(value = 10, message = "每页展示记录数最小为1")
    @Schema(description = "每页展示记录数")
    private Integer pageSize;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Length(max = 20, message = "用户名长度不能超过20")
    private String username;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @Length(max = 11, message = "手机号长度不能超过11")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @Length(max = 50, message = "邮箱长度不能超过50")
    private String email;

    /**
     * 状态
     */
    @Schema(description = "状态：0-正常，1-异常")
    @Range(min = 0, max = 1, message = "状态值非法")
    private Integer status;
}
