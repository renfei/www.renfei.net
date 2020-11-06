![RenFei.Net Social Banner](https://cdn.renfei.net/thunder/renfei.net.jpg)

## RENFEI.NET ```thunder```
[![Actions Status](https://github.com/renfei/www.renfei.net/workflows/CI/badge.svg)](https://github.com/renfei/www.renfei.net/actions)
[![Actions Status](https://github.com/renfei/www.renfei.net/workflows/Release/badge.svg)](https://github.com/renfei/www.renfei.net/actions)
[![Build Status](https://api.travis-ci.com/renfei/www.renfei.net.svg?branch=main)](https://travis-ci.com/renfei/www.renfei.net)

此版本是2020年重写版本，代号```thunder```，这个版本有什么故事呢？

在2020年我主要研究了SpringCloud的微服务，但是发现性能下降严重，速度远不比单机应用，所以放弃了使用微服务的想法，想要让页面请求的速度再快一点，就诞生了这个版本。

这个版本从底层到页面全部重写，主要以速度优先，与旧版相比主要有以下优化：

- 增加Redis缓存策略，全站所有数据在Redis中缓存2小时
- 全新的站内搜索，使用Elasticsearch搜索引擎，放弃MySQL的全文检索
- 前端引擎改为Freemarker，放弃Thymeleaf，执行效率太低
- 重写前端页面，放弃别人的框架，完全自己手写，尽可能轻量，能不引的就不引
- 移除广告拦截提示，用户体验过差，已经不在检测广告屏蔽插件
- IP数据库从查询MySQL数据库改为检索BIN文件，速度提升明显