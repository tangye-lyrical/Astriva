package cn.astriva.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应结果
 *
 * @author 棠野·Lyrical
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> rows;
}
