package cn.astriva.handler;

import cn.astriva.utils.CurrentHolder;
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
        this.strictInsertFill(metaObject, "createBy", Long.class, CurrentHolder.getCurrentEntity().getUserId());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新操作时填充字段值
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("开始进行更新数据填充...");
        this.strictUpdateFill(metaObject, "updateBy", Long.class, CurrentHolder.getCurrentEntity().getUserId());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
