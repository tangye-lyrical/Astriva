package cn.astriva.auth.service.impl;

import cn.astriva.auth.mapper.SysUserMapper;
import cn.astriva.auth.pojo.dto.IUSysUserDto;
import cn.astriva.auth.pojo.dto.SysLoginDto;
import cn.astriva.auth.pojo.entity.SysLogin;
import cn.astriva.auth.pojo.entity.SysUser;
import cn.astriva.auth.pojo.queryParam.SysUserQueryParam;
import cn.astriva.auth.pojo.vo.SysUserInfoVo;
import cn.astriva.auth.pojo.vo.SysUserPageVo;
import cn.astriva.auth.service.SysUserService;
import cn.astriva.common.basic.ServiceException;
import cn.astriva.common.constant.SysUserConstant;
import cn.astriva.common.enums.StatusEnum;
import cn.astriva.common.enums.SysLoginError;
import cn.astriva.common.exceptions.SysLoginException;
import cn.astriva.common.result.PageResult;
import cn.astriva.common.utils.CurrentHolder;
import cn.astriva.st.utils.SaTokenUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
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
        // 用户登录，并拓展登录信息
        String token = SaTokenUtil.userLogin(sysUser.getId(), Map.of(
                SaTokenUtil.EXTRA_USERNAME, sysUser.getUsername(),
                SaTokenUtil.EXTRA_DEPT_ID, sysUser.getDeptId(),
                SaTokenUtil.EXTRA_NICKNAME, sysUser.getNickname()
        ));

        // 返回登录凭证
        return SysLogin.builder()
                .nickname(sysUser.getNickname())
                .username(sysUser.getUsername())
                .avtarUrl(sysUser.getAvatar())
                .token(token)
                .build();
    }

    /**
     * 系统用户退出登录
     *
     * @param userId 用户ID
     */
    @Override
    public void userLogout(Long userId) {
        log.info("id为{}的系统用户退出当前会话", userId);
        SaTokenUtil.userLogout(userId);
    }

    /**
     * 查询当前用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public SysUserInfoVo findUserInfo(Long userId) {
        log.info("id为{}的系统用户查询当前用户信息", userId);
        // 从数据库获取当前用户信息
        SysUserInfoVo sysUser = sysUserMapper.selectVoById(userId);
        // 将手机号信息去敏
        sysUser.setPhone(DesensitizedUtil.mobilePhone(sysUser.getPhone()));

        return sysUser;
    }

    /**
     * 管理员新增系统用户
     *
     * @param sysUserDto 新增系统用户表单
     */
    @Override
    public void insertSysUser(IUSysUserDto sysUserDto) {
        log.info("超级管理员：{} 新增系统用户: {}", CurrentHolder.getCurrentEntity().getUsername(), sysUserDto.getUsername());

        // 将dto实体转换为实体类
        SysUser sysUser = BeanUtil.copyProperties(sysUserDto, SysUser.class);
        // 设置默认密码并加密
        sysUser.setPassword(BCrypt.hashpw(SysUserConstant.DEFAULT_PASSWORD));
        // 执行新增
        sysUserMapper.insert(sysUser);
    }

    /**
     * 管理员修改系统用户
     *
     * @param sysUserDto 修改系统用户表单
     * @param userId     被修改的用户ID
     */
    @Override
    public void updateSysUser(IUSysUserDto sysUserDto, Long userId) {
        log.info("管理员{}修改id为{}的系统用户: {}", CurrentHolder.getCurrentEntity().getUsername(), userId, sysUserDto.getUsername());
        // 校验被修改的用户是否存在
        if (ObjUtil.isNull(sysUserMapper.selectById(userId))) {
            throw new ServiceException("被修改的系统用户不存在");
        }
        // 将dto实体转换为实体类
        SysUser sysUser = BeanUtil.copyProperties(sysUserDto, SysUser.class);
        sysUser.setId(userId);
        // 依据ID执行修改
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 管理员批量删除系统用户
     *
     * @param userIds 被删除的用户ID数组
     */
    @Override
    public void deleteSysUserByIds(Long[] userIds) {
        log.info("管理员{}批量删除id为{}系统用户", CurrentHolder.getCurrentEntity().getUsername(), Arrays.toString(userIds));
        // 执行批量逻辑删除
        sysUserMapper.deleteByIds(Arrays.asList(userIds));
    }

    /**
     * 管理员修改系统用户状态
     *
     * @param userId 被修改的用户ID
     * @param status 状态
     */
    @Override
    public void updateSysUserStatus(Long userId, Integer status) {
        log.info("管理员{}修改id为{}的系统用户状态为{}", CurrentHolder.getCurrentEntity().getUsername(), userId, status);
        sysUserMapper.updateById(SysUser.builder().id(userId).status(status).build());
    }

    /**
     * 分页查询系统用户
     *
     * @param sysUserQueryParam 系统用户查询参数
     * @return 系统用户分页结果
     */
    @Override
    public PageResult<SysUserPageVo> pageSysUser(SysUserQueryParam sysUserQueryParam) {
        log.info("管理员{}分页查询系统用户，参数：{}", CurrentHolder.getCurrentEntity().getUsername(), sysUserQueryParam);

        // 构造分页对象，页码从 1 开始
        Page<SysUserPageVo> page = new Page<>(sysUserQueryParam.getPage(), sysUserQueryParam.getPageSize());
        // 执行连表分页查询，MP 自动完成 LIMIT 和 COUNT
        IPage<SysUserPageVo> result = sysUserMapper.selectVoPage(page, sysUserQueryParam);

        return new PageResult<>(result.getTotal(), result.getRecords());
    }
}
