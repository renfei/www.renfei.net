<#include "layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>

    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div style="background-color: #000000;">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12" style="padding-left: 0;padding-right: 0;">
                    <div id="banner-box">
                        <div id="banner">
                            <div class="slider">
                                <div class="box1 box">
                                    <div class="bg"></div>
                                    <div class="details">
                                        <h1>任霏博客</h1>
                                        <p>
                                            这是 任霏 的个人网站与博客，一个程序员自己写的网站，不仅仅是文章内容，还包括网站程序的代码。
                                            对新鲜事物都十分感兴趣，利用这个站点向大家分享自己的所见所得，同时这个站点也是我的实验室。
                                        </p>
                                        <a href="https://www.renfei.net">任霏博客</a>
                                    </div>

                                    <div class="illustration">
                                        <div class="inner">
                                            <img src="https://cdn.renfei.net/Logo/RF_white.svg" alt="任霏博客"
                                                 style="margin-top: 38px;"/>
                                        </div>
                                    </div>
                                </div>


                                <div class="box2 box">
                                    <div class="bg"></div>
                                    <div class="details">
                                        <h1>任霏微博</h1>
                                        <p>
                                            任霏的微型博客，很多事情并不适合长篇大论，用简短的词汇就够了。在这个信息爆炸的时代，时间很重要。
                                        </p>
                                        <a href="https://www.renfei.net/weibo">任霏微博</a>
                                    </div>

                                    <div class="illustration">
                                        <div class="inner"></div>
                                    </div>
                                </div>

                                <div class="box3 box">
                                    <div class="bg"></div>
                                    <div class="details">
                                        <h1>开发工具箱</h1>
                                        <p>
                                            子曰："工欲善其事，必先利其器。"
                                            利用工具优化自己的学习工作效率，将时间留给更美好的事物。
                                        </p>
                                        <a href="https://www.renfei.net/kitbox">开发工具箱</a>
                                    </div>

                                    <div class="illustration">
                                        <img src="/img/developer_toolkit_icon.png" alt="开发工具箱"
                                             style="margin-top: 38px;"/>
                                    </div>
                                </div>

                                <div class="box4 box">
                                    <div class="bg"></div>
                                    <div class="details">
                                        <h1>开源项目</h1>
                                        <p>
                                            让编程更简单和有趣。我从开源社区学习得到各种技术知识，同时也将我学到的分享出去。感谢热衷于分享的极客们。
                                        </p>
                                        <a href="https://github.com/renfei-net" target="_blank" rel="nofollow noopener">Github</a>
                                    </div>

                                    <div class="illustration">
                                        <div class="inner"></div>
                                    </div>
                                </div>

                                <div class="box5 box">
                                    <div class="bg"></div>
                                    <div class="details">
                                        <h1>Maven仓库</h1>
                                        <p>
                                            Maven公共代理仓库，助力开发者快速构建自己的项目。
                                            代理多种仓库，解决国内仓库速度慢、仓库包丢失的问题。
                                        </p>
                                        <a href="https://www.renfei.net/maven">Maven仓库</a>
                                    </div>

                                    <div class="illustration">
                                        <img src="/img/maven-logo-black-on-white.png" alt="Maven仓库"
                                             style="margin-top: 38px;"/>
                                    </div>
                                </div>

                            </div>

                            <svg xmlns="http://www.w3.org/2000/svg" class="prev" width="56.898" height="91"
                                 viewBox="0 0 56.898 91">
                                <path d="M45.5,0,91,56.9,48.452,24.068,0,56.9Z" transform="translate(0 91) rotate(-90)"
                                      fill="#fff"/>
                            </svg>
                            <svg xmlns="http://www.w3.org/2000/svg" class="next" width="56.898" height="91"
                                 viewBox="0 0 56.898 91">
                                <path d="M45.5,0,91,56.9,48.452,24.068,0,56.9Z" transform="translate(56.898) rotate(90)"
                                      fill="#fff"/>
                            </svg>
                            <div class="trail">
                                <div class="box1 active">1</div>
                                <div class="box2">2</div>
                                <div class="box3">3</div>
                                <div class="box4">4</div>
                                <div class="box5">5</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="background-color: #f5f5f7;">
        <div class="container" style="padding-top: 50px;">
            <h3>最新文档</h3>
            <div class="row">
                <div class="col-sm-12 col-card">
                    <#if posts?? && (posts?size > 0)>
                        <#list posts as post>
                            <#if post_index == 0>
                                <div class="card">
                                    <div class="row no-gutters">
                                        <div class="col-md-8">
                                            <img src="${post.featuredImage!}?x-oss-process=style/992.558"
                                                 class="card-img" alt="${post.title}">
                                        </div>
                                        <div class="col-md-4">
                                            <div class="card-body">
                                                <a class="stretched-link" href="/posts/${post.id?c}"
                                                   style="color: #000000;">
                                                    <h5 class="card-title">
                                                        <#if (post.title)?length lt 25>
                                                            ${post.title!}
                                                        <#else>
                                                            ${(post.title)?substring(0,25)}...
                                                        </#if>
                                                    </h5>
                                                </a>
                                                <p class="card-text">
                                                    <#if (post.describes)?length lt 50>
                                                        ${post.describes!}
                                                    <#else>
                                                        ${(post.describes)?substring(0,50)}...
                                                    </#if>
                                                </p>
                                                <p class="card-text"><small
                                                            class="text-muted">${post.releaseTime?string("yyyy-MM-dd")!}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        </#list>
                    </#if>
                </div>
            </div>
            <div class="row">
                <#if posts?? && (posts?size > 0)>
                    <#list posts as post>
                        <#if post_index gt 0 && post_index lt 3>
                            <div class="col-sm-6 col-card">
                                <div class="card">
                                    <img src="${post.featuredImage!}?x-oss-process=style/992.558"
                                         class="card-img" alt="${post.title}">
                                    <div class="card-body">
                                        <a class="stretched-link" href="/posts/${post.id?c}" style="color: #000000;">
                                            <h5 class="card-title">
                                                <#if (post.title)?length lt 31>
                                                    ${post.title!}
                                                <#else>
                                                    ${(post.title)?substring(0,30)}...
                                                </#if>
                                            </h5>
                                        </a>
                                        <p class="card-text">
                                            <#if (post.describes)?length lt 50>
                                                ${post.describes!}
                                            <#else>
                                                ${(post.describes)?substring(0,50)}...
                                            </#if>
                                        </p>
                                        <p class="card-text"><small
                                                    class="text-muted">${post.releaseTime?string("yyyy-MM-dd")!}</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <div class="row">
                <#if posts?? && (posts?size > 0)>
                    <#list posts as post>
                        <#if post_index gt 2 && post_index lt 9>
                            <div class="col-md-4 col-sm-6 col-card">
                                <div class="card">
                                    <img src="${post.featuredImage!}?x-oss-process=style/992.558"
                                         class="card-img" alt="${post.title}">
                                    <div class="card-body">
                                        <a class="stretched-link" href="/posts/${post.id?c}" style="color: #000000;">
                                            <h5 class="card-title">
                                                <#if (post.title)?length lt 25>
                                                    ${post.title!}
                                                <#else>
                                                    ${(post.title)?substring(0,25)}...
                                                </#if>
                                            </h5>
                                        </a>
                                        <p class="card-text">
                                            <#if (post.describes)?length lt 45>
                                                ${post.describes!}
                                            <#else>
                                                ${(post.describes)?substring(0,45)}...
                                            </#if>
                                        </p>
                                        <p class="card-text"><small
                                                    class="text-muted">${post.releaseTime?string("yyyy-MM-dd")!}</small>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
    <div style="background-color: #FFFFFF;">
        <div class="container more" style="padding-top: 50px;">
            <h3>更多文档</h3>
            <hr>
            <div class="row">
                <#if posts?? && (posts?size > 0)>
                    <#list posts as post>
                        <#if post_index gt 8 && post_index lt 15>
                            <div class="col-md-6">
                                <div class="card ">
                                    <div class="row no-gutters">
                                        <div class="col-3 col-md-5 col-lg-3">
                                            <img src="${post.featuredImage!}?x-oss-process=style/200_200"
                                                 class="card-img" alt="${post.title}">
                                        </div>
                                        <div class="col-9 col-md-7 col-lg-9">
                                            <div class="card-body">
                                                <a class="stretched-link" href="/posts/${post.id?c}"
                                                   style="color: #000000;">
                                                    <h5 class="card-title" style="font-size: 18px;">
                                                        <#if (post.title)?length lt 25>
                                                            ${post.title!}
                                                        <#else>
                                                            ${(post.title)?substring(0,25)}...
                                                        </#if>
                                                    </h5>
                                                </a>
                                                <p class="card-text"><small
                                                            class="text-muted">${post.releaseTime?string("yyyy-MM-dd")!}</small>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </#list>
                </#if>
            </div>
            <hr>
            <div class="text-center">
                <a href="/posts" class="btn btn-link">查看更多文档 >></a>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script src="/js/gsap-latest-beta.min.js"></script>
        <script src="/js/CSSRulePlugin3.min.js"></script>
        <script src="/js/banner.js"></script>
        <script type="application/ld+json">
            {
                "@context": "http://schema.org/",
                "@graph": [
                    {
                        "@type": "Organization",
                        "logo": "https://cdn.renfei.net/logo/logo_112.png",
                        "url": "https://www.renfei.net"
                    },
                    {
                        "@type": "WebSite",
                        "url": "https://www.renfei.net",
                        "potentialAction": {
                            "@type": "SearchAction",
                            "target": "https://www.renfei.net/search?type=all&w={search_term_string}",
                            "query-input": "required name=search_term_string"
                        }
                    },
                    {
                        "@type": "Person",
                        "name": "任霏",
                        "url": "https://www.renfei.net",
                        "sameAs": [
                            "https://github.com/NeilRen",
                            "https://www.facebook.com/renfeii",
                            "https://twitter.com/renfeii",
                            "https://www.youtube.com/channel/UCPsjiVvFMS7piLgC-RHBWxg"
                        ]
                    }
                ]
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>