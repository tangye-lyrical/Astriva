package cn.astriva.auth.service.impl;

import cn.astriva.auth.mapper.SysUserMapper;
import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.SysLogin;
import cn.astriva.auth.pojo.entity.SysUser;
import cn.astriva.auth.service.SysUserService;
import cn.astriva.enums.StatusEnum;
import cn.astriva.enums.SysLoginError;
import cn.astriva.exceptions.SysLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.digest.BCrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 系统用户服务实现类 => 为便于排查SQL相关问题，此处使用MyBatis操作SQL
 *
 * @author 棠野·Lyrical
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    /**
     * 系统用户服务接口
     */
    private final SysUserMapper sysUserMapper;

    /**
     * 系统用户登录
     *
     * @param sysLoginDto 系统登录登录表单
     * @return 系统登录返回信息
     */
    @Override
    public SysLogin sysLogin(SysLoginDto sysLoginDto) {
        // 由 "username" 字段查询系统用户数据，在数据库中指定：账号、手机号、邮箱等唯一，故此处查询的数据只能有一个用户
        SysUser sysUser = sysUserMapper.selectOneByUsername(sysLoginDto);
        // 当用户不存在时，抛出异常
        if (ObjUtil.isNull(sysUser)) {
            log.debug("系统用户{}不存在", sysLoginDto.getUsername());
            throw new SysLoginException(SysLoginError.USER_NOT_FOUND);
        }
        // 校验用户状态/删除标志是否正常
        if (!Objects.equals(sysUser.getStatus(), StatusEnum.NORMAL.getValue()) || !Objects.equals(sysUser.getDelFlag(), StatusEnum.NORMAL.getValue())) {
            log.debug("系统用户{}状态/删除标志异常", sysLoginDto.getUsername());
            throw new SysLoginException(SysLoginError.USER_STATUS_ERROR);
        }
        // 比对密码是否正确
        if (!BCrypt.checkpw(sysLoginDto.getPassword(), sysUser.getPassword())) {
            log.debug("系统用户{}密码错误", sysLoginDto.getUsername());
            throw new SysLoginException(SysLoginError.PASSWORD_ERROR);
        }
        // 使用SaToken进行登录，并将用户信息存储到登录凭证中
        StpUtil.login(sysUser.getId(), new SaLoginParameter()
                .setExtra("username", sysUser.getUsername())
                .setExtra("deptId", sysUser.getDeptId())
                .setExtra("nickname", sysUser.getNickname())
        );

        // 返回登录凭证
        return SysLogin.builder()
                .nickname(sysUser.getNickname())
                .username(sysUser.getUsername())
                .avtarUrl(sysUser.getAvatar())
                .token(StpUtil.getTokenValue())
                .build();
    }
}
