package cn.astriva.auth.mapper;

import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户映射器
 *
 * @author Mr. Tao
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据登录账号查询用户
     * @param sysLoginDto 登录表单数据
     * @return 查询到的用户信息
     */
    SysUser selectOneByUsername(SysLoginDto sysLoginDto);
}
