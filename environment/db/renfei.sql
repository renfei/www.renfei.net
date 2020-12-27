SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) NOT NULL COMMENT 'UUID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `registration_date` datetime DEFAULT NULL COMMENT '注册时间',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `totp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '两步验证',
  `registration_ip` varchar(255) DEFAULT NULL COMMENT '注册IP地址',
  `trial_error_times` int(11) NOT NULL DEFAULT 0 COMMENT '密码试错次数',
  `lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
  `state_code` int(11) NOT NULL DEFAULT 0 COMMENT '状态码：0全都未验证；1邮箱验证；2手机验证；3邮箱和手机都验证',
  `last_name` varchar(255) DEFAULT NULL COMMENT '姓氏',
  `first_name` varchar(255) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='账户';

-- ----------------------------
-- Table structure for t_account_keep_name
-- ----------------------------
DROP TABLE IF EXISTS `t_account_keep_name`;
CREATE TABLE `t_account_keep_name` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='保留用户名';

-- ----------------------------
-- Table structure for t_cache
-- ----------------------------
DROP TABLE IF EXISTS `t_cache`;
CREATE TABLE `t_cache` (
  `uuid` char(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cache_key` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cache_value` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `expires` datetime NOT NULL,
  PRIMARY KEY (`uuid`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1030 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_photo_img
-- ----------------------------
DROP TABLE IF EXISTS `t_photo_img`;
CREATE TABLE `t_photo_img` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `photo_id` bigint(20) unsigned NOT NULL COMMENT '相册ID',
  `uri` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '照片地址',
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1003421 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_posts_extra
-- ----------------------------
DROP TABLE IF EXISTS `t_posts_extra`;
CREATE TABLE `t_posts_extra` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) unsigned NOT NULL,
  `extra_type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `extra_value` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章扩展信息';

-- ----------------------------
-- Table structure for t_secret_key
-- ----------------------------
DROP TABLE IF EXISTS `t_secret_key`;
CREATE TABLE `t_secret_key` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `public_key` text DEFAULT NULL COMMENT '公钥',
  `private_key` text DEFAULT NULL COMMENT '私钥',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `uuid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uuid` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COMMENT='秘钥表';

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `keys` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '键',
  `values` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '值',
  `orders` int(10) unsigned DEFAULT NULL COMMENT '排序',
  `extend` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展',
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10035 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_system_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_log`;
CREATE TABLE `t_system_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `log_level` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '日志等级',
  `log_model` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '功能模块',
  `log_type` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '操作类型',
  `log_desc` text CHARACTER SET utf8mb4 NOT NULL COMMENT '操作描述',
  `requ_param` longtext CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '请求参数',
  `resp_param` longtext CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '返回参数',
  `user_uuid` varchar(36) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作用户ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作用户名',
  `requ_method` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '操作方法',
  `requ_uri` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '请求URI',
  `requ_ip` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '客户IP',
  `requ_agent` text COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端浏览器相关信息',
  `log_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=343690 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `en_name` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `zh_name` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `describe` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=987 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uri_path` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_verification_code
-- ----------------------------
DROP TABLE IF EXISTS `t_verification_code`;
CREATE TABLE `t_verification_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '验证码',
  `expires` datetime NOT NULL COMMENT '到期时间',
  `addressee` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收件人',
  `channel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '渠道：SMS、EMAIL',
  `be_used` tinyint(1) NOT NULL DEFAULT 0 COMMENT '被使用',
  `auth_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '验证类型',
  `account_uuid` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联账号',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `keyword_unique` (`keyword`) USING BTREE
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
