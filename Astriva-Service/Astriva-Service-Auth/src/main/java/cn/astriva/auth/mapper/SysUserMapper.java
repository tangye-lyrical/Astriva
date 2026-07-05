package cn.astriva.auth.mapper;

import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.SysUser;
import cn.astriva.auth.pojo.queryParam.SysUserQueryParam;
import cn.astriva.auth.pojo.vo.SysUserInfoVo;
import cn.astriva.auth.pojo.vo.SysUserPageVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 根据用户ID查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUserInfoVo selectVoById(Long userId);

    /**
     * 分页查询系统用户
     * @param page 分页参数
     * @param query 系统用户查询参数
     * @return 分页查询结果
     */
    IPage<SysUserPageVo> selectVoPage(Page<SysUserPageVo> page, SysUserQueryParam query);
}
