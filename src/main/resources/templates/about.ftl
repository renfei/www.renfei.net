<#include "layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <link href="https://cdn.renfei.net/thunder/css/timeline.css" rel="stylesheet" type="text/css"/>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="about-banner">
        <div class="container">
            <div class="row">
                <div class="col-12 about-img">
                    <div class="about-title d-none d-sm-block">
                        <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                            任霏
                        </h1>
                        <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                            软件开发工程师
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container" style="font-size: 14px;">
        <div class="row">
            <div class="col-12">
                <div class="d-sm-none mt-5">
                    <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                        任霏
                    </h1>
                    <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                        软件开发工程师
                    </h3>
                </div>
            </div>
        </div>
        <div class="row mt-5">
            <div class="col-md-6">
                <p>一只90后程序猿，总喜欢尝试新鲜事物，对新奇的东西充满了好奇。很幸运在我年轻的时候赶上了中国计算机的普及浪潮。</p>
                <p>小学有了微机课，初中有了网吧，高中有了自己的PC，开启了建站之旅，大学选择了喜欢的计算机专业，开始了我的故事...</p>
                <p>大学是 C#.NET 专业毕业，后自学 Java 进行技术转型。你现在看到的这个网站就是我从后端到前端完整的手写的。</p>
            </div>
            <div class="col-md-6">
                E-mail: <a href="mailto:i@renfei.net">i@renfei.net</a> <br>
                GitHub:<a href="https://github.com/renfei" target="_blank" rel="nofollow noopener">GitHub</a> <br>
                GitHub:<a href="https://gitlab.com/renfei" target="_blank" rel="nofollow noopener">GitLab</a> <br>
                Facebook:<a href="https://www.facebook.com/renfeii" target="_blank" rel="nofollow noopener">任霏</a> <br>
                Twitter:<a href="https://twitter.com/renfeii" target="_blank" rel="nofollow noopener">@renfeii</a> <br>
                Sina Weibo:<a href="https://weibo.com/5214619228" target="_blank" rel="nofollow noopener">任霏博客网</a> <br>
                QQ Group：<a
                        href="https://shang.qq.com/wpa/qunwpa?idkey=bfbde7e5dec79fd3cdb23c7cf590ca698e3da8b48a71369139ed6aa52f9a7513"
                        target="_blank">130832168</a><br>
                Telegram:<a href="https://t.me/neilren" target="_blank">NeilRen</a> <br>
                Tel:+86 13082843041
            </div>
        </div>
        <hr>
        <h2 class="text-center">开源代码</h2>
        <div class="text-center">
            <small class="text-muted">让编程更简单和有趣</small>
        </div>
        <div class="row mt-5">
            <div class="col-md-4">
                <div class="card mb-2">
                    <img src="https://cdn.renfei.net/thunder/img/renfei.net.jpg" class="card-img-top"
                         alt="任霏的个人博客">
                    <div class="card-body">
                        <h5 class="card-title">任霏的个人博客</h5>
                        <p class="card-text">
                            收集开发中常用的代码工具。虽然程序员们都热衷于重复的"造轮子"，但这样是不对的，如果代码有问题你应该尝试去修复，而不是重新再造一个轮子出来。所以我收集开发中常用的工具代码，以方便在各个项目中重新利用它们。这个工具包被我所有的项目所使用。</p>
                        <a href="https://github.com/renfei/www.renfei.net" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-2">
                    <img src="https://cdn.renfei.net/images/sdk.renfei.net.jpg" class="card-img-top"
                         alt="renfei java sdk">
                    <div class="card-body">
                        <h5 class="card-title">RenFei SDK For Java</h5>
                        <p class="card-text">
                            收集开发中常用的代码工具。虽然程序员们都热衷于重复的"造轮子"，但这样是不对的，如果代码有问题你应该尝试去修复，而不是重新再造一个轮子出来。所以我收集开发中常用的工具代码，以方便在各个项目中重新利用它们。这个工具包被我所有的项目所使用。</p>
                        <a href="https://github.com/renfei/renfei-java-sdk" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-2">
                    <img src="https://cdn.renfei.net/WinterEE/winteree.jpg" class="card-img-top"
                         alt="WinterEE">
                    <div class="card-body">
                        <h5 class="card-title">WinterEE</h5>
                        <p class="card-text">
                            WinterEE是基于SpringCloud的开发脚手架，Spring的名字是代表了传统J2EE的“冬天”之后新的开始成为春天；WinterEE还需要开发者二次开发，我们希望把冬天留给WinterEE来完成，所以取冬天“Winter”，同时对Spring致敬；而
                            EE 是对 J2EE 的致敬。</p>
                        <a href="https://github.com/renfei/WinterEE" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-2">
                    <div class="card-body">
                        <h5 class="card-title">Google Indexing</h5>
                        <p class="card-text">
                            该项目是对 Google API
                            的实现，可以将网站新页面提交给Google搜索引擎，类似国内的百度站长工具提交功能，由于Google的API比较复杂，不能直接简单的POST提交，所以我将代码共享出来，方便需要的站长直接使用或者参考。</p>
                        <a href="https://github.com/renfei/GoogleIndexing" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-2">
                    <div class="card-body">
                        <h5 class="card-title">gitignore</h5>
                        <p class="card-text">
                            我们每个Git项目中都需要一个“.gitignore”文件，这个文件的作用就是告诉Git哪些文件不需要添加到版本管理中。比如Java工程项目中的target文件夹是编译后的文件，并不是源代码文件，所以我们需要让Git忽略这个文件夹。这个仓库中维护的就是常见的忽略策略配置。</p>
                        <a href="https://github.com/renfei/gitignore" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-2">
                    <div class="card-body">
                        <h5 class="card-title">DOPro (DevOps Protocol)</h5>
                        <p class="card-text">
                            受到阿里巴巴的《Java编程手册》的启发，在运维和使用基础环境的时候，也应当有所约定，就诞生了本仓库，欢迎大家一起来维护条目。</p>
                        <a href="https://github.com/renfei/DOPro" class="btn btn-primary stretched-link"
                           target="_blank" rel="nofollow noopener">查看代码</a>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h2 class="text-center">大事记</h2>
        <div class="text-center">
            <small class="text-muted">时间流逝，那些需要纪念的关键转折点</small>
        </div>
        <div class="row mt-5">
            <div class="col-12">
                <div class="timeline timeline-line-dotted">
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2020</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>年度更新：全部重写</h4>
                            </div>
                            <div class="timeline-body">
                                <p>经过对微服务的研究，决定放弃微服务架构，转而寻求更快的页面响应速度，
                                    根据每年一次大更新的习惯，决定将前后端重写，一切以性能和速度为目标，
                                    争取能以最快的速度响应页面请求。增加ElasticSearch搜索引擎为站内搜索服务。增加redis缓存提高响应速度。</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2020-11-28</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2019</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>使用前后端分离</h4>
                            </div>
                            <div class="timeline-body">
                                <p>使用 SpringBoot 和 Vue.Js 重写网站，开始尝试前后端分离</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2019-5-28</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>收购 RENFEI.NET 域名</h4>
                            </div>
                            <div class="timeline-body">
                                <p>收购 RENFEI.NET 域名</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2019-1-4</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2018</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>功能新增</h4>
                            </div>
                            <div class="timeline-body">
                                <p>功能新增：工具箱小工具-弱密码检测接口，并开放接口调用，新增弱密码数据库</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2018-08-21</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>功能新增</h4>
                            </div>
                            <div class="timeline-body">
                                <p>功能新增：利用AOP切面统计执行时间和执行方法数量，对执行时间过长的方法进行日志记录</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2018-8-1</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>功能新增</h4>
                            </div>
                            <div class="timeline-body">
                                <p>功能新增：网页头部节日主题装扮</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2018-7-28</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>技术转型Java开发</h4>
                            </div>
                            <div class="timeline-body">
                                <p>使用SpringBoot重新构建，项目代号 NEILREN4J</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2018-07-16</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2017</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>开启开源之旅</h4>
                            </div>
                            <div class="timeline-body">
                                <p>计划上Github开源管理代码</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2017-06-00</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>计划技术转型Java开发</h4>
                            </div>
                            <div class="timeline-body">
                                <p>开始Java版开发，项目代号 NEILREN4J</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2017-05-00</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>计划技术转型Java开发</h4>
                            </div>
                            <div class="timeline-body">
                                <p>购买搭建Linux服务器</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2017-04-00</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2016</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>计划技术转型Java开发</h4>
                            </div>
                            <div class="timeline-body">
                                <p>尝试使用 SSM 框架构建新的Java版网站</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2016-12-00</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>计划技术转型Java开发</h4>
                            </div>
                            <div class="timeline-body">
                                <p>计划转Java技术，开始筹备Java知识和Linux知识</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2016-10-00</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2015</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>自主独立开发网站，告别开源建站</h4>
                            </div>
                            <div class="timeline-body">
                                <p>NEILREN.COM上线，使用 ASP.NET MVC 独立开发程序，项目代号 NeilNT</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2015-8-1</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>数据库迁移准备搬家到自己的程序上</h4>
                            </div>
                            <div class="timeline-body">
                                <p>数据库迁移，从MySQL迁移到SQL Server</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2015-7-6</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>计划自主开发自己的网站程序</h4>
                            </div>
                            <div class="timeline-body">
                                <p>MVC 配置新服务器，新购阿里云服务器</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2015-6-27</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>大学毕业啦</h4>
                            </div>
                            <div class="timeline-body">
                                <p>大学毕业，在企业实习中，计划独立开发网站程序</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2015-6-21</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>注册neil.ren域名</h4>
                            </div>
                            <div class="timeline-body">
                                <p>注册neil.ren域名/p></p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2015-1-5</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2013</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>更换域名</h4>
                            </div>
                            <div class="timeline-body">
                                <p>注册ren-fei.com 域名更改为ren-fei.com</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2013-03-01</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>更换域名</h4>
                            </div>
                            <div class="timeline-body">
                                <p>注册neilren.com，20岁的生日，更换域名</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2013-11-06</p>
                            </div>
                        </div>
                    </div>
                    <span class="timeline-label">
                        <span class="badge badge-pill badge-secondary px-2">2012</span>
                    </span>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>开启建站之旅</h4>
                            </div>
                            <div class="timeline-body">
                                <p>注册 任霏.中国，中文域名</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2012-06-04</p>
                            </div>
                        </div>
                    </div>
                    <div class="timeline-item">
                        <div class="timeline-point timeline-point-success">
                            <i class="fa fa-money"></i>
                        </div>
                        <div class="timeline-event p-2">
                            <div class="timeline-heading">
                                <h4>开启建站之旅</h4>
                            </div>
                            <div class="timeline-body">
                                <p>使用WordPress搭建了独立的个人博客网站bennett-ren.com</p>
                            </div>
                            <div class="timeline-footer">
                                <p class="text-right">2012-04-08</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <h2 class="text-center">开放信息</h2>
        <div class="text-center">
            <small class="text-muted">数字证书就是虚拟世界的身份证</small>
        </div>
        <div class="row mt-5">
            <div class="col-12">
                SSH Key:<a href="https://cdn.renfei.net/public/ssh/id_dsa.pub" target="_blank">https://cdn.renfei.net/public/ssh/id_dsa.pub</a><br/>
                GPG Key:<a href="https://cdn.renfei.net/public/gpg/gpg.key" target="_blank">https://cdn.renfei.net/public/gpg/gpg.key</a>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>

    </@footer>
    </body>
    </html>
</@compress>