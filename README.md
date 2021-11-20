# 停止更新维护

此仓库为 v1.x.x 版本，已经停止更新维护，将由新版本替代：[https://github.com/renfei/renfeid](https://github.com/renfei/renfeid)

![RenFei.Net Social Banner](https://cdn.renfei.net/thunder/renfei.net.jpg)

## RENFEI.NET ```thunder```

[![Actions Status](https://github.com/renfei/www.renfei.net/workflows/CI/badge.svg)](https://github.com/renfei/www.renfei.net/actions)
[![Actions Status](https://github.com/renfei/www.renfei.net/workflows/Release/badge.svg)](https://github.com/renfei/www.renfei.net/actions)
[![Build Status](https://api.travis-ci.com/renfei/www.renfei.net.svg?branch=main)](https://travis-ci.com/renfei/www.renfei.net)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Frenfei%2Fwww.renfei.net.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Frenfei%2Fwww.renfei.net?ref=badge_shield)
![Analytics](https://ga-beacon.appspot.com/UA-45593016-5/www.renfei.net?flat)

此版本是2020年重写版本，代号```thunder```，这个版本有什么故事呢？

在2020年我主要研究了SpringCloud的微服务，但是发现性能下降严重，速度远不比单机应用，所以放弃了使用微服务的想法，想要让页面请求的速度再快一点，就诞生了这个版本。

这个版本从底层到页面全部重写，主要以速度优先，与旧版相比主要有以下优化：

- 增加Redis缓存策略，全站所有数据在Redis中缓存2小时
- 全新的站内搜索，使用Elasticsearch搜索引擎，放弃MySQL的全文检索
- 前端引擎改为Freemarker，放弃Thymeleaf，执行效率太低
- 重写前端页面，放弃别人的框架，完全自己手写，尽可能轻量，能不引的就不引
- 移除广告拦截提示，用户体验过差，已经不在检测广告屏蔽插件
- IP数据库从查询MySQL数据库改为检索BIN文件，速度提升明显

## 代码仓库
- [Main] Github:[https://github.com/renfei/www.renfei.net](https://github.com/renfei/www.renfei.net)
- [Mirror] Gitlab:[https://gitlab.com/renfei/www.renfei.net](https://gitlab.com/renfei/www.renfei.net)
- [Mirror] Gitee:[https://gitee.com/rnf/www.renfei.net](https://gitee.com/rnf/www.renfei.net)
- [Mirror] Coding:[https://githubi.coding.net/public/renfei/www.renfei.net/git/files](https://githubi.coding.net/public/renfei/www.renfei.net/git/files)

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Frenfei%2Fwww.renfei.net.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Frenfei%2Fwww.renfei.net?ref=badge_large)

## 求鼓励

如果这个项目帮助到了你，是否能给我点个免费的星星 (Star) 给个鼓励呢。高星项目我将持续关注努力更新的。