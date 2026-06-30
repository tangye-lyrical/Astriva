package cn.astriva.auth.mapper;

import cn.astriva.auth.pojo.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统角色映射器
 *
 * @author Mr. Tao
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据登录ID查询角色标识列表
     *
     * @param userId 登录ID
     * @return 角色标识列表
     */
    List<String> selectListByUserId(Long userId);
}
