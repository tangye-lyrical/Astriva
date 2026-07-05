package cn.astriva.mp.handler;

import cn.astriva.common.utils.CurrentHolder;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元对象处理类
 * <p>用于处理插入和更新操作时的字段值</p>
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作时填充字段值
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("开始进行插入数据填充...");
        // 从当前线程上下文获取用户ID
        Long createBy = CurrentHolder.getCurrentEntity().getUserId();
        // 创建人ID不能为空
        if (ObjectUtil.isNotEmpty(createBy)) {
            this.strictInsertFill(metaObject, "createBy", Long.class, createBy);
        }
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新操作时填充字段值
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("开始进行更新数据填充...");
        // 从当前线程上下文获取用户ID
        Long updateBy = CurrentHolder.getCurrentEntity().getUserId();
        // 更新人ID不能为空
        if (ObjectUtil.isNotEmpty(updateBy)) {
            this.strictUpdateFill(metaObject, "updateBy", Long.class, updateBy);
        }
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
