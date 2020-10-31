SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户',
  `password` varchar(71) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `totp` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '两步验证',
  `is_open_otp` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否启用两步验证',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
  `registration_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '注册时间',
  `tries` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '密码尝试次数',
  `lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_cache
-- ----------------------------
DROP TABLE IF EXISTS `t_cache`;
CREATE TABLE `t_cache` (
  `uuid` char(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cache_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cache_value` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `expires` datetime NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) unsigned NOT NULL,
  `en_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `zh_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `featured_image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '特色图像',
  PRIMARY KEY (`id`),
  KEY `fk_category_type` (`type_id`),
  CONSTRAINT `fk_category_type` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `code_type` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '代码类型',
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '描述',
  `code` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `target_id` bigint(20) unsigned NOT NULL COMMENT '目标ID',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '目标的分类ID',
  `author` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者名称',
  `author_email` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者邮箱',
  `author_url` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者链接',
  `author_IP` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者IP',
  `author_address` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者物理地址',
  `addtime` datetime NOT NULL COMMENT '评论时间',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级评论ID',
  `is_owner` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是官方回复',
  PRIMARY KEY (`id`),
  KEY `fk_comment_type` (`type_id`),
  KEY `fk_comments` (`parent_id`),
  CONSTRAINT `fk_comment_type` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `fk_comments` FOREIGN KEY (`parent_id`) REFERENCES `t_comments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_download
-- ----------------------------
DROP TABLE IF EXISTS `t_download`;
CREATE TABLE `t_download` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文件编号',
  `disable_area` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '禁止区域',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图标地址',
  `size` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件大小',
  `created` datetime NOT NULL COMMENT '创建时间',
  `hash` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '哈希值',
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名',
  `bucket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '储存在哪里',
  `file_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存储路径',
  `baidu_yun_pan_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '百度云盘下载',
  `baidu_yun_pan_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '百度云盘授权码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_library
-- ----------------------------
DROP TABLE IF EXISTS `t_library`;
CREATE TABLE `t_library` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '所属菜单ID',
  `resource_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`),
  KEY `fk_library_menu` (`menu_id`),
  CONSTRAINT `fk_library_menu` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=769 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_library_details
-- ----------------------------
DROP TABLE IF EXISTS `t_library_details`;
CREATE TABLE `t_library_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `library_id` bigint(20) unsigned NOT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名',
  `lang` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `down_load` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '下载链接',
  `post_date_string` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布日期',
  `SHA1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'MD5校验码',
  `size` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`),
  KEY `fk_library_details` (`library_id`),
  CONSTRAINT `fk_library_details` FOREIGN KEY (`library_id`) REFERENCES `t_library` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14291 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sitename` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '站点名称',
  `sitelink` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '站点链接',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `addtime` datetime NOT NULL,
  `audit_pass` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否审核通过',
  `link_type` int(11) NOT NULL DEFAULT 1 COMMENT '交换类型：1对等交换，2交叉交换',
  `in_site_link` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '在对方的链接位置',
  `contact_name` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  `contact_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系邮箱',
  `contact_qq` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系QQ',
  `remarks` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `order_id` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `uuid` char(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datetime` datetime NOT NULL,
  `level` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ACCESS;DEBUG;INFO;WARN;ERROR;FATAL;信息等级',
  `inorout` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'input/output，系统输入还是输出',
  `log_value` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日志内容',
  `remote_ip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端IP',
  `remote_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端用户名',
  `remote_agent` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端浏览器相关信息',
  `request_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问地址',
  `request_method` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '访问方法',
  `status_code` int(11) DEFAULT NULL COMMENT '响应状态码',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) unsigned NOT NULL DEFAULT 0,
  `menu_text` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `menu_link` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '链接',
  `is_new_win` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否新窗口打开',
  `is_enable` tinyint(1) DEFAULT 1 COMMENT '是否启用',
  `menu_type` int(10) unsigned NOT NULL COMMENT '菜单类型，页头菜单还是页尾菜单',
  `order_number` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_movie
-- ----------------------------
DROP TABLE IF EXISTS `t_movie`;
CREATE TABLE `t_movie` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL COMMENT '电影分类',
  `featured_image` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电影封面',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电影名称',
  `region` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电影地区',
  `years` int(11) NOT NULL COMMENT '年代',
  `director` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '导演',
  `lead` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主演',
  `synopsis` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩',
  `score` double DEFAULT NULL COMMENT '评分',
  `resource` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源地址',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_movie_cat` (`category_id`),
  CONSTRAINT `fk_movie_cat` FOREIGN KEY (`category_id`) REFERENCES `t_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_page
-- ----------------------------
DROP TABLE IF EXISTS `t_page`;
CREATE TABLE `t_page` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL,
  `title` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '页面标题',
  `featured_image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '特色图像',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '页面内容',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `describes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介用于SEO',
  `keyword` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关键字用于SEO',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '软删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
  `descritpion` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源接口地址',
  `method` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求方法',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_role`;
CREATE TABLE `t_permission_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_permission_role` (`role_id`),
  KEY `fk_permission_permission` (`permission_id`),
  CONSTRAINT `fk_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `fk_permission_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_photo`;
CREATE TABLE `t_photo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL,
  `title` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `featured_image` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '缩略图',
  `describes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_photo_img
-- ----------------------------
DROP TABLE IF EXISTS `t_photo_img`;
CREATE TABLE `t_photo_img` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `photo_id` bigint(20) unsigned NOT NULL COMMENT '相册ID',
  `uri` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '照片地址',
  PRIMARY KEY (`id`),
  KEY `fk_photo_img` (`photo_id`),
  CONSTRAINT `fk_photo_img` FOREIGN KEY (`photo_id`) REFERENCES `t_photo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=528 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_posts
-- ----------------------------
DROP TABLE IF EXISTS `t_posts`;
CREATE TABLE `t_posts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL COMMENT '文章分类',
  `featured_image` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '特色图像',
  `title` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章标题',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容',
  `is_original` tinyint(1) unsigned NOT NULL COMMENT '是否原创文章',
  `source_url` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原文链接',
  `source_name` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章来源名称',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `describes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章简介用于SEO',
  `keyword` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关键字用于SEO',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '软删除',
  `is_comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论',
  `avg_views` double NOT NULL DEFAULT 0 COMMENT '日均浏览量',
  `avg_comment` double NOT NULL DEFAULT 0 COMMENT '日均评论量',
  `page_rank` double NOT NULL DEFAULT 10000 COMMENT '权重排序',
  `page_rank_update_time` datetime DEFAULT NULL COMMENT '权重更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1003413 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_posts_extra
-- ----------------------------
DROP TABLE IF EXISTS `t_posts_extra`;
CREATE TABLE `t_posts_extra` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) unsigned NOT NULL,
  `extra_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `extra_value` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章扩展信息';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_role_account
-- ----------------------------
DROP TABLE IF EXISTS `t_role_account`;
CREATE TABLE `t_role_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_account` (`account_id`),
  KEY `fk_role_role` (`role_id`),
  CONSTRAINT `fk_role_account` FOREIGN KEY (`account_id`) REFERENCES `t_account` (`id`),
  CONSTRAINT `fk_role_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_secret_key
-- ----------------------------
DROP TABLE IF EXISTS `t_secret_key`;
CREATE TABLE `t_secret_key` (
  `uid` binary(16) NOT NULL,
  `server_private_key` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `client_public_key` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `aes_key` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `expire_time` datetime NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `keys` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
  `values` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '值',
  `orders` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `extend` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_short_url
-- ----------------------------
DROP TABLE IF EXISTS `t_short_url`;
CREATE TABLE `t_short_url` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `short_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '短网址编号',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '长网址',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `views` bigint(255) unsigned NOT NULL DEFAULT 0 COMMENT '访问量',
  `state_code` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
  `add_user` bigint(20) DEFAULT NULL COMMENT '添加用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10028 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `en_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `zh_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `describe` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_tag_relation`;
CREATE TABLE `t_tag_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) unsigned NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  `target_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tag` (`tag_id`),
  KEY `fk_tag_type` (`type_id`),
  CONSTRAINT `fk_tag` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `fk_tag_type` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=961 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户名',
  `token` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'TOKEN',
  `is_remember` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否记住我',
  `expiration_time` datetime NOT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uri_path` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) unsigned NOT NULL,
  `title` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `featured_image` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '特色图像',
  `describes` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `is_delete` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '删除标志',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩',
  `is_comment` tinyint(1) unsigned NOT NULL DEFAULT 1 COMMENT '是否允许评论',
  `download` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_video_source
-- ----------------------------
DROP TABLE IF EXISTS `t_video_source`;
CREATE TABLE `t_video_source` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `video_id` bigint(20) unsigned NOT NULL COMMENT '视频ID',
  `video_type` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频类型',
  `video_size` int(10) unsigned NOT NULL COMMENT '视频分辨率',
  `video_src` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频源地址',
  PRIMARY KEY (`id`),
  KEY `fk_video` (`video_id`),
  CONSTRAINT `fk_video` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_video_track
-- ----------------------------
DROP TABLE IF EXISTS `t_video_track`;
CREATE TABLE `t_video_track` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `video_id` bigint(20) unsigned NOT NULL,
  `kind` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `label` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `srclang` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `src` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_video_track` (`video_id`),
  CONSTRAINT `fk_video_track` FOREIGN KEY (`video_id`) REFERENCES `t_video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_wechat_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_keyword`;
CREATE TABLE `t_wechat_keyword` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关键字',
  `key_typw` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型，文本还是关联内部系统',
  `key_value` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyword_unique` (`keyword`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_weibo
-- ----------------------------
DROP TABLE IF EXISTS `t_weibo`;
CREATE TABLE `t_weibo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `img_id` bigint(20) unsigned DEFAULT NULL COMMENT '引用图片ID',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `views` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '浏览量',
  `thumbs_up` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点赞量',
  `thumbs_down` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '点踩量',
  PRIMARY KEY (`id`),
  KEY `fk_weibo_photo` (`img_id`),
  CONSTRAINT `fk_weibo_photo` FOREIGN KEY (`img_id`) REFERENCES `t_photo_img` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
