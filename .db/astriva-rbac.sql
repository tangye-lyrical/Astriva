-- ============================================================
-- 1. 部门表 (sys_dept)
-- ============================================================
drop table if exists `sys_dept`;
create table `sys_dept`
(
    `id`          bigint       not null comment '部门id',
    `parent_id`   bigint       not null default 0 comment '父部门id，0 -> 顶级',
    `ancestors`   varchar(500) not null comment '祖级列表，逗号分隔，如: 0,1,2',
    `dept_name`   varchar(100) not null comment '部门名称',
    `dept_code`   varchar(64)  not null comment '部门编码，唯一',
    `order_num`   int          not null default 0 comment '显示排序',
    `leader`      bigint       not null default 0 comment '负责人id，0 -> 暂无',
    `status`      tinyint      not null default 1 comment '状态: 0 -> 停用，1 -> 正常',
    `del_flag`    tinyint      not null default 0 comment '删除标志: 0 -> 存在，1 -> 删除',
    `create_by`   bigint       not null default 0 comment '创建者',
    `create_time` datetime     not null default current_timestamp comment '创建时间',
    `update_by`   bigint       not null default 0 comment '更新者',
    `update_time` datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `uk_dept_code` (`dept_code`),
    key `idx_parent_id` (`parent_id`),
    key `idx_status` (`status`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='部门表';

-- ============================================================
-- 2. 岗位表 (sys_post)
-- ============================================================
drop table if exists `sys_post`;
create table `sys_post`
(
    `id`          bigint       not null comment '岗位id',
    `post_code`   varchar(64)  not null comment '岗位编码，唯一',
    `post_name`   varchar(100) not null comment '岗位名称',
    `order_num`   int          not null default 0 comment '显示排序',
    `status`      tinyint      not null default 1 comment '状态: 0 -> 停用，1 -> 正常',
    `del_flag`    tinyint      not null default 0 comment '删除标志: 0 -> 存在，1 -> 删除',
    `create_by`   bigint       not null default 0 comment '创建者',
    `create_time` datetime     not null default current_timestamp comment '创建时间',
    `update_by`   bigint       not null default 0 comment '更新者',
    `update_time` datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `uk_post_code` (`post_code`),
    key `idx_status` (`status`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='岗位表';

-- ============================================================
-- 3. 用户表 (sys_user)
-- ============================================================
drop table if exists `sys_user`;
create table `sys_user`
(
    `id`          bigint       not null comment '用户id',
    `dept_id`     bigint       not null default 0 comment '部门id，0 -> 未分配',
    `username`    varchar(64)  not null comment '登录账号，唯一',
    `nickname`    varchar(64)  not null comment '用户昵称',
    `real_name`   varchar(64)  not null comment '真实姓名',
    `user_type`   tinyint      not null default 0 comment '用户类型: 0 -> 系统管理员，1 -> 系统用户',
    `email`       varchar(100) not null comment '邮箱，唯一',
    `phone`       char(15)     not null comment '手机号，唯一',
    `sex`         tinyint      not null default 2 comment '性别: 0 -> 男，1 -> 女，2 -> 未知',
    `avatar`      varchar(255) not null default '' comment '头像地址',
    `password`    char(60)     not null comment '密码（bcrypt 密文）',
    `status`      tinyint      not null default 1 comment '状态: 0 -> 停用，1 -> 正常',
    `del_flag`    tinyint      not null default 0 comment '删除标志: 0 -> 存在，1 -> 删除',
    `create_by`   bigint       not null default 0 comment '创建者',
    `create_time` datetime     not null default current_timestamp comment '创建时间',
    `update_by`   bigint       not null default 0 comment '更新者',
    `update_time` datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `uk_username` (`username`),
    unique key `uk_email` (`email`),
    unique key `uk_phone` (`phone`),
    key `idx_dept_id` (`dept_id`),
    key `idx_status` (`status`),
    key `idx_del_flag` (`del_flag`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='用户表';

-- ============================================================
-- 4. 用户-岗位关联表 (sys_user_post)
-- ============================================================
drop table if exists `sys_user_post`;
create table `sys_user_post`
(
    `user_id` bigint not null comment '用户id',
    `post_id` bigint not null comment '岗位id',
    primary key (`user_id`, `post_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='用户与岗位关联表';

-- ============================================================
-- 5. 角色表 (sys_role)
-- ============================================================
drop table if exists `sys_role`;
create table `sys_role`
(
    `id`          bigint       not null comment '角色id',
    `role_name`   varchar(100) not null comment '角色名称',
    `role_code`   varchar(100) not null comment '角色编码，唯一，如: admin, manager',
    `role_sort`   int          not null default 0 comment '显示排序',
    `data_scope`  tinyint      not null default 1 comment '数据权限范围: 1 -> 全部，2 -> 本部门，3 -> 本部门及子部门，4 -> 仅本人，5 -> 自定义',
    `status`      tinyint      not null default 1 comment '状态: 0 -> 停用，1 -> 正常',
    `del_flag`    tinyint      not null default 0 comment '删除标志: 0 -> 存在，1 -> 删除',
    `create_by`   bigint       not null default 0 comment '创建者',
    `create_time` datetime     not null default current_timestamp comment '创建时间',
    `update_by`   bigint       not null default 0 comment '更新者',
    `update_time` datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `uk_role_code` (`role_code`),
    key `idx_status` (`status`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='角色表';

-- ============================================================
-- 6. 用户-角色关联表 (sys_user_role)
-- ============================================================
drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `user_id` bigint not null comment '用户id',
    `role_id` bigint not null comment '角色id',
    primary key (`user_id`, `role_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='用户和角色关联表';

-- ============================================================
-- 7. 菜单/权限表 (sys_menu)
-- 设计为菜单和权限合一，通过 menu_type 区分
-- menu_type: m -> 目录，c -> 菜单，f -> 按钮/api
-- ============================================================
drop table if exists `sys_menu`;
create table `sys_menu`
(
    `id`          bigint       not null comment '菜单id',
    `menu_name`   varchar(100) not null comment '菜单名称',
    `menu_code`   varchar(100) not null comment '菜单编码，唯一，同时也是权限标识，如: system:user:list',
    `parent_id`   bigint       not null default 0 comment '父菜单id，0 -> 顶级',
    `order_num`   int          not null default 0 comment '显示排序',
    `path`        varchar(255) not null default '' comment '路由地址',
    `component`   varchar(255) not null default '' comment '组件路径',
    `query`       varchar(255) not null default '' comment '路由参数',
    `is_frame`    tinyint      not null default 0 comment '是否为外链: 0 -> 否，1 -> 是',
    `is_cache`    tinyint      not null default 0 comment '是否缓存: 0 -> 否，1 -> 是',
    `menu_type`   char(1)      not null comment '菜单类型: m -> 目录，c -> 菜单，f -> 按钮/api',
    `icon`        varchar(100) not null default '#' comment '菜单图标',
    `method`      char(10)     not null default '' comment '请求方法: get/post/put/delete，用于 api 级权限',
    `status`      tinyint      not null default 1 comment '状态: 0 -> 停用，1 -> 正常',
    `visible`     tinyint      not null default 1 comment '显示状态: 0 -> 隐藏，1 -> 显示',
    `del_flag`    tinyint      not null default 0 comment '删除标志: 0 -> 存在，1 -> 删除',
    `create_by`   bigint       not null default 0 comment '创建者',
    `create_time` datetime     not null default current_timestamp comment '创建时间',
    `update_by`   bigint       not null default 0 comment '更新者',
    `update_time` datetime     not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`),
    unique key `uk_menu_code` (`menu_code`),
    key `idx_parent_id` (`parent_id`),
    key `idx_menu_type` (`menu_type`),
    key `idx_status` (`status`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='菜单权限表';

-- ============================================================
-- 8. 角色-菜单/权限关联表 (sys_role_menu)
-- ============================================================
drop table if exists `sys_role_menu`;
create table `sys_role_menu`
(
    `role_id` bigint not null comment '角色id',
    `menu_id` bigint not null comment '菜单id',
    primary key (`role_id`, `menu_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='角色和菜单权限关联表';

-- ============================================================
-- 9. 角色-部门关联表 (sys_role_dept)
-- 用于数据权限自定义范围
-- ============================================================
drop table if exists `sys_role_dept`;
create table `sys_role_dept`
(
    `role_id` bigint not null comment '角色id',
    `dept_id` bigint not null comment '部门id',
    primary key (`role_id`, `dept_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='角色和部门关联表（数据权限）';

-- ============================================================
-- 初始化数据
-- ============================================================

-- 初始化部门
insert into `sys_dept` (`id`, `parent_id`, `ancestors`, `dept_name`, `dept_code`, `order_num`, `leader`, `status`,
                        `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`)
values (1, 0, '0', 'astriva科技', 'astriva', 0, 0, 1, 0, 0, now(), 0, now()),
       (2, 1, '0,1', '研发部', 'dev', 1, 0, 1, 0, 0, now(), 0, now()),
       (3, 1, '0,1', '测试部', 'test', 2, 0, 1, 0, 0, now(), 0, now()),
       (4, 1, '0,1', '运维部', 'ops', 3, 0, 1, 0, 0, now(), 0, now());

-- 初始化岗位
insert into `sys_post` (`id`, `post_code`, `post_name`, `order_num`, `status`, `del_flag`, `create_by`, `create_time`,
                        `update_by`, `update_time`)
values (1, 'java_dev', 'java开发工程师', 1, 1, 0, 0, now(), 0, now()),
       (2, 'frontend_dev', '前端开发工程师', 2, 1, 0, 0, now(), 0, now()),
       (3, 'test_eng', '测试工程师', 3, 1, 0, 0, now(), 0, now()),
       (4, 'ops_eng', '运维工程师', 4, 1, 0, 0, now(), 0, now()),
       (5, 'architect', '架构师', 5, 1, 0, 0, now(), 0, now());

-- 初始化用户 (密码: admin123，bcrypt加密)
insert into `sys_user` (`id`, `dept_id`, `username`, `nickname`, `real_name`, `user_type`, `email`, `phone`, `sex`,
                        `avatar`, `password`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`,
                        `update_time`)
values (1, 2, 'admin', '系统管理员', '管理员', 0, 'admin@astriva.io', '13800138000', 0, '',
        '$2a$10$n.zmdr9k7uocqb376nounutj8iat6z5ehsm8le9lbosl7iat6z5eo', 1, 0, 0, now(), 0, now()),
       (2, 2, 'astriva', 'astriva', '框架作者', 0, 'dev@astriva.io', '13800138001', 0, '',
        '$2a$10$n.zmdr9k7uocqb376nounutj8iat6z5ehsm8le9lbosl7iat6z5eo', 1, 0, 0, now(), 0, now());

-- 初始化角色
insert into `sys_role` (`id`, `role_name`, `role_code`, `role_sort`, `data_scope`, `status`, `del_flag`, `create_by`,
                        `create_time`, `update_by`, `update_time`)
values (1, '超级管理员', 'super_admin', 1, 1, 1, 0, 0, now(), 0, now()),
       (2, '系统管理员', 'system_admin', 2, 1, 1, 0, 0, now(), 0, now()),
       (3, '普通用户', 'common_user', 3, 4, 1, 0, 0, now(), 0, now()),
       (4, '部门经理', 'dept_manager', 4, 3, 1, 0, 0, now(), 0, now());

-- 初始化用户-角色关联
insert into `sys_user_role` (`user_id`, `role_id`)
values (1, 1),
       (2, 2);

-- 初始化菜单/权限
-- 系统管理模块
insert into `sys_menu` (`id`, `menu_name`, `menu_code`, `parent_id`, `order_num`, `path`, `component`, `query`,
                        `is_frame`, `is_cache`, `menu_type`, `icon`, `method`, `status`, `visible`, `del_flag`,
                        `create_by`, `create_time`, `update_by`, `update_time`)
values (1, '系统管理', 'system', 0, 1, '/system', '', '', 0, 0, 'm', 'el-icon-setting', '', 1, 1, 0, 0, now(), 0, now()),
       (2, '用户管理', 'system:user', 1, 1, 'user', 'system/user/index', '', 0, 0, 'c', 'el-icon-user', '', 1, 1, 0, 0,
        now(), 0, now()),
       (3, '用户查询', 'system:user:query', 2, 1, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (4, '用户新增', 'system:user:add', 2, 2, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (5, '用户修改', 'system:user:edit', 2, 3, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (6, '用户删除', 'system:user:remove', 2, 4, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (7, '用户导出', 'system:user:export', 2, 5, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (8, '角色管理', 'system:role', 1, 2, 'role', 'system/role/index', '', 0, 0, 'c', 'el-icon-s-custom', '', 1, 1, 0,
        0, now(), 0, now()),
       (9, '角色查询', 'system:role:query', 8, 1, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (10, '角色新增', 'system:role:add', 8, 2, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (11, '角色修改', 'system:role:edit', 8, 3, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (12, '角色删除', 'system:role:remove', 8, 4, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (13, '菜单管理', 'system:menu', 1, 3, 'menu', 'system/menu/index', '', 0, 0, 'c', 'el-icon-menu', '', 1, 1, 0, 0,
        now(), 0, now()),
       (14, '菜单查询', 'system:menu:query', 13, 1, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (15, '菜单新增', 'system:menu:add', 13, 2, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (16, '菜单修改', 'system:menu:edit', 13, 3, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (17, '菜单删除', 'system:menu:remove', 13, 4, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (18, '部门管理', 'system:dept', 1, 4, 'dept', 'system/dept/index', '', 0, 0, 'c', 'el-icon-office-building', '', 1,
        1, 0, 0, now(), 0, now()),
       (19, '部门查询', 'system:dept:query', 18, 1, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (20, '部门新增', 'system:dept:add', 18, 2, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (21, '部门修改', 'system:dept:edit', 18, 3, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (22, '部门删除', 'system:dept:remove', 18, 4, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (23, '岗位管理', 'system:post', 1, 5, 'post', 'system/post/index', '', 0, 0, 'c', 'el-icon-postcard', '', 1, 1, 0,
        0, now(), 0, now()),
       (24, '岗位查询', 'system:post:query', 23, 1, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (25, '岗位新增', 'system:post:add', 23, 2, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (26, '岗位修改', 'system:post:edit', 23, 3, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now()),
       (27, '岗位删除', 'system:post:remove', 23, 4, '', '', '', 0, 0, 'f', '#', '', 1, 1, 0, 0, now(), 0, now());

-- 超级管理员拥有所有权限
insert into `sys_role_menu` (`role_id`, `menu_id`)
select 1, id
from sys_menu
where del_flag = 0;

-- 系统管理员拥有系统管理模块全部权限
insert into `sys_role_menu` (`role_id`, `menu_id`)
values (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7),
       (2, 8), (2, 9), (2, 10), (2, 11), (2, 12),
       (2, 13), (2, 14), (2, 15), (2, 16), (2, 17),
       (2, 18), (2, 19), (2, 20), (2, 21), (2, 22),
       (2, 23), (2, 24), (2, 25), (2, 26), (2, 27);
