
CREATE DATABASE IF NOT EXISTS `hightech`;
USE `hightech`;
DROP table if exists ht_user_info;
CREATE TABLE `ht_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64)  COMMENT '姓名',
  `user_no` varchar(16)  NOT NULL COMMENT '电话号码',
  `password` varchar(64)  NOT NULL COMMENT '加密后的密码',
  `salt` varchar(32)  NOT NULL COMMENT '加密盐值',
  `post` varchar(128) DEFAULT NULL COMMENT '职位，主要给操作员使用',
  `role` varchar(128)  NOT NULL default 'SYS_ADMIN' COMMENT '用户角色，可能有多角色；SYS_ADMIN：系统管理员；GUEST_MANAGER：客户经理；ENTERPRISE_ADMIN：企业管理员；ENTERPRISE_OPERATOR：企业操作员；ORG_ADMIN:机构管理员',
  `status` varchar(8)  NOT NULL default 'normal' COMMENT '状态，normal：在职，quit：离职，handover：交接',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `idx_username` (`user_name`),
  KEY `idx_userno` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

INSERT INTO `hightech`.`ht_user_info` (`user_id`, `user_name`, `user_no`, `password`, `salt`, `post`, `role`, `status`) VALUES ('1', '贾晓彤', '18992053696', '0b66#11aa#afca6#dda#6Fbc2ga0#g20242@0ba!1ba*2d#ba@a0b#540g#*#adf', '841a035a81614177b71194ac8cdf7445', NULL, 'SYS_ADMIN', 'normal');

drop table if exists ht_user_ext_info;
CREATE TABLE `ht_user_ext_info` (
  `user_id` int(11) NOT NULL  COMMENT '用户ID',
  `ext_key` varchar(16) NOT NULL COMMENT '扩展信息key',
  `ext_value` varchar(64)  NOT NULL COMMENT '扩展信息value',
  `is_valid` varchar(1)  NOT NULL COMMENT '是否有效：Y/N',
  `memo` varchar(128)  NOT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (user_id,ext_key)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;

drop table if exists ht_node_info;
CREATE TABLE `ht_node_info` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织节点ID',
  `node_name` varchar(128) NOT NULL COMMENT '组织节点名称',
  `parent_id` int(11) NOT NULL  COMMENT '父级节点',
   node_type varchar(1) not null comment '节点类型：INNER：内部组织，ORG：机构组织',
  `parent_node_list` varchar(128) NOT NULL COMMENT '父级节点列表',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (node_id),
  key (node_name,node_type),
  key(parent_id)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;


drop table if exists ht_node_user_rela;
CREATE TABLE `ht_node_user_rela` (
  `node_id` int(11) NOT NULL COMMENT '组织节点ID',
  `user_id` varchar(128) NOT NULL COMMENT '用户ID',
  `is_manager` varchar(1) NOT NULL  COMMENT '是否为管理员：Y/N',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (node_id,user_id)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;

drop table if exists ht_org_info;
CREATE TABLE `ht_org_info` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `org_name` varchar(128) NOT NULL COMMENT '机构名称',
   node_id  int(11)  not null comment '归属组织节点ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (org_id),
  key (org_name)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;

drop table if exists ht_enterprise_info;
CREATE TABLE `ht_enterprise_info` (
  `enterprise_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '企业ID',
  `enterprise_name` varchar(128) NOT NULL COMMENT '企业名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (enterprise_id),
  key (enterprise_name)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;

INSERT INTO `hightech`.`ht_enterprise_info` (`enterprise_id`, `enterprise_name`, `create_time`, `update_`) VALUES ('1', '上海陈楠公司', '2020-10-18 17:29:31', '2020-10-18 17:29:35');
INSERT INTO `hightech`.`ht_enterprise_info` (`enterprise_id`, `enterprise_name`, `create_time`, `update_`) VALUES ('2', '西安宇星公司', '2020-10-18 17:29:52', '2020-10-18 17:29:55');
INSERT INTO `hightech`.`ht_enterprise_info` (`enterprise_id`, `enterprise_name`, `create_time`, `update_`) VALUES ('3', '深圳芳草公司', '2020-10-18 17:30:21', '2020-10-18 17:30:24');



drop table if exists ht_enterprise_user_rela;
CREATE TABLE `ht_enterprise_user_rela` (
  `enterprise_id` int(11) NOT NULL COMMENT '企业ID',
  `user_id` varchar(128) NOT NULL COMMENT '用户ID',
  `rela_type` varchar(16) NOT NULL  COMMENT 'GUEST_MANAGER：客户经理；ENTERPRISE_ADMIN：企业管理员；ENTERPRISE_OPERATOR：企业操作员',
  `rela_status`  varchar(16) NOT NULL  COMMENT 'normal：在职，handover：交接中',
  `rela_user_id` int(11) comment '交接对象',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (enterprise_id,user_id)
) ENGINE=InnoDB  DEFAULT CHARSET=gbk;



