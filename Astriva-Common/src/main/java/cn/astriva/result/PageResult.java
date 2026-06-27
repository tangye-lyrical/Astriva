package cn.astriva.result;

import lombok.Data;

import java.util.List;

/**
 * 分页响应结果
 *
 * @author 棠野·Lyrical
 */
@Data
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
