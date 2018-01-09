/*
Navicat MySQL Data Transfer

Source Server         : own
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-12-27 22:15:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bl_attach
-- ----------------------------
DROP TABLE IF EXISTS `bl_attach`;
CREATE TABLE `bl_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) DEFAULT NULL,
  `ftype` varchar(50) DEFAULT NULL,
  `fkey` varchar(100) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `created` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_attach
-- ----------------------------

-- ----------------------------
-- Table structure for bl_code_task
-- ----------------------------
DROP TABLE IF EXISTS `bl_code_task`;
CREATE TABLE `bl_code_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(500) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT '0',
  `function_name` varchar(100) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `msg_id` varchar(100) DEFAULT NULL,
  `user_id` decimal(10,0) DEFAULT NULL,
  `result` varchar(100) DEFAULT '0',
  `type` varchar(100) DEFAULT NULL,
  `way` decimal(10,0) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `deleted` char(1) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_code_task
-- ----------------------------

-- ----------------------------
-- Table structure for bl_comments
-- ----------------------------
DROP TABLE IF EXISTS `bl_comments`;
CREATE TABLE `bl_comments` (
  `coid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT '0',
  `created` int(11) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `author_id` int(11) DEFAULT '0',
  `owner_id` int(11) DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` longtext,
  `type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `parent` int(11) DEFAULT '0',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_comments
-- ----------------------------

-- ----------------------------
-- Table structure for bl_contents
-- ----------------------------
DROP TABLE IF EXISTS `bl_contents`;
CREATE TABLE `bl_contents` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `slug` varchar(500) DEFAULT NULL,
  `thumb_img` varchar(500) DEFAULT NULL,
  `created` int(11) DEFAULT NULL,
  `modified` int(11) DEFAULT NULL,
  `content` longtext,
  `author_id` int(11) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `fmt_type` varchar(50) DEFAULT 'markdown',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(11) DEFAULT '0',
  `comments_num` int(11) DEFAULT '0',
  `allow_comment` int(11) DEFAULT NULL,
  `allow_ping` int(11) DEFAULT NULL,
  `allow_feed` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_contents
-- ----------------------------
INSERT INTO `bl_contents` VALUES ('1', '关于', 'about', null, '1487853610', '1487872488', '### Hello World\r\n\r\n这是我的关于页面\r\n\r\n### 当然还有其他\r\n\r\n具体你来写点什么吧', '1', 'page', 'publish', null, null, null, '31', '0', '1', '1', '1');
INSERT INTO `bl_contents` VALUES ('2', '网站开通成功了奥！', null, null, '1487861184', '1487872798', '## Hello  World.\r\n\r\n> 网站成功开通了奥！！...\r\n\r\n不知道说撒,,,,,,,写个Hello Word！ hhhh\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\"Hello Word.\");\r\n}\r\n```', '1', 'post', 'publish', null, '', '默认分类', '44', '0', '1', '1', '1');
INSERT INTO `bl_contents` VALUES ('3', '友情链接', 'links', null, '1505643727', '1505643888', '## 友情链接\r\n\r\n- :lock: [java技术论坛]()\r\n\r\n## 链接须知\r\n\r\n> 请确定贵站可以稳定运营\r\n> 原创博客优先，技术类博客优先，设计、视觉类博客优先\r\n> 经常过来访问和评论，眼熟的\r\n\r\n备注：默认申请友情链接均为内页（当前页面）\r\n\r\n## 基本信息\r\n\r\n                网站名称：yangxs博客\r\n                网站地址：暂时没有\r\n\r\n请在当页通过评论来申请友链，其他地方不予回复\r\n\r\n暂时先这样，同时欢迎互换友链，这个页面留言即可。 ^_^\r\n\r\n还有，我会不定时对无法访问的网址进行清理，请保证自己的链接长期有效。', '1', 'page', 'publish', 'markdown', null, null, '60', '0', '1', '1', null);

-- ----------------------------
-- Table structure for bl_logs
-- ----------------------------
DROP TABLE IF EXISTS `bl_logs`;
CREATE TABLE `bl_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) DEFAULT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bl_metas
-- ----------------------------
DROP TABLE IF EXISTS `bl_metas`;
CREATE TABLE `bl_metas` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `parent` int(11) DEFAULT '0',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_metas
-- ----------------------------
INSERT INTO `bl_metas` VALUES ('1', '笔记', null, 'category', null, '0', '0');
INSERT INTO `bl_metas` VALUES ('2', '我喜欢的歌曲', null, 'category', null, '1', '0');

-- ----------------------------
-- Table structure for bl_options
-- ----------------------------
DROP TABLE IF EXISTS `bl_options`;
CREATE TABLE `bl_options` (
  `name` varchar(100) NOT NULL,
  `value` longtext NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_options
-- ----------------------------
INSERT INTO `bl_options` VALUES ('allow_install', '0', '是否允许重新安装博客');
INSERT INTO `bl_options` VALUES ('site_description', '博客系统,Blade框架', null);
INSERT INTO `bl_options` VALUES ('site_keywords', '博客系统,Blade框架', null);
INSERT INTO `bl_options` VALUES ('site_theme', 'default', null);
INSERT INTO `bl_options` VALUES ('social_github', '', null);
INSERT INTO `bl_options` VALUES ('social_twitter', '', null);
INSERT INTO `bl_options` VALUES ('social_weibo', '', null);
INSERT INTO `bl_options` VALUES ('social_zhihu', '', null);

-- ----------------------------
-- Table structure for bl_relationships
-- ----------------------------
DROP TABLE IF EXISTS `bl_relationships`;
CREATE TABLE `bl_relationships` (
  `cid` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_relationships
-- ----------------------------
INSERT INTO `bl_relationships` VALUES ('2', '1');

-- ----------------------------
-- Table structure for bl_safe_code
-- ----------------------------
DROP TABLE IF EXISTS `bl_safe_code`;
CREATE TABLE `bl_safe_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `scene_id` varchar(32) DEFAULT NULL,
  `open_id` varchar(100) DEFAULT NULL,
  `s_key` varchar(200) DEFAULT NULL,
  `s_value` varchar(100) DEFAULT NULL,
  `scangen_key` varchar(100) DEFAULT NULL,
  `business_type` varchar(32) DEFAULT NULL,
  `scan_time` datetime DEFAULT NULL,
  `expiry_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bl_safe_code
-- ----------------------------

-- ----------------------------
-- Table structure for bl_users
-- ----------------------------
DROP TABLE IF EXISTS `bl_users`;
CREATE TABLE `bl_users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `screen_name` varchar(100) DEFAULT NULL,
  `created` int(11) DEFAULT NULL,
  `activated` int(11) DEFAULT NULL,
  `logged` int(11) DEFAULT NULL,
  `group_name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `AK_UNQ_BL_USER_USERNAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

drop table if exists bl_visited;

/*==============================================================*/
/* Table: bl_visited                                            */
/*==============================================================*/
create table bl_visited
(
   id                   integer not null auto_increment,
   user_id              integer,
   ip                   varchar(50),
   country              varchar(50),
   area                 varchar(50),
   region               varchar(50),
   city                 varchar(50),
   county               varchar(50),
   visited_num          integer default 1,
   create_time          integer,
   primary key (id)
)
charset = UTF8;

