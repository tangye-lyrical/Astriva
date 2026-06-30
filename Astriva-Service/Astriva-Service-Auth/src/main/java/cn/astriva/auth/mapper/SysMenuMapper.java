package cn.astriva.auth.mapper;

import cn.astriva.auth.pojo.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单映射器
 *
 * @author Mr. Tao
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据登录者ID查询其拥有的权限码集合
     *
     * @param userId 登录者ID
     * @return 权限码集合
     */
    List<String> findMenuCodeByUserId(Long userId);
}
