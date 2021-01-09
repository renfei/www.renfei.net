<#macro head headVO title>
    <@compress single_line=true>
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <title>${title!?html}</title>
            <#if headVO.description?? && ((headVO.description)?length lt 150)>
                <meta name="description" content="${headVO.description!}">
            <#elseif headVO.description??>
                <meta name="description" content="${(headVO.description)?substring(0,150)}...">
            </#if>
            <meta name="keywords" content="${headVO.keywords!}">
            <meta name="author" content="任霏,i@renfei.net"/>
            <meta name="copyright" content="CopyRight RENFEI.NET, All Rights Reserved."/>
            <meta http-equiv="Content-Language" content="zh-CN"/>
            <meta name="renderer" content="webkit">
            <meta name="theme-color" content="#333333"/>
            <meta name="_csrf" content="${(_csrf.token)!}"/>
            <meta name="_csrf_header" content="${(_csrf.headerName)!}"/>
            <link rel="dns-prefetch" href="//www.renfei.net">
            <link rel="dns-prefetch" href="//hm.baidu.com">
            <link rel="dns-prefetch" href="//www.googletagmanager.com">
            <link rel="dns-prefetch" href="//www.google-analytics.com">
            <link rel="dns-prefetch" href="//cloudflareinsights.com">
            <#if headVO.ogprotocol??>
                <meta property="og:title" content="${headVO.ogprotocol.title!}"/>
                <meta property="og:description" content="${headVO.ogprotocol.description!}"/>
                <meta property="og:type" content="article"/>
                <meta property="og:image" content="${headVO.ogprotocol.image!}"/>
                <meta property="og:url" content="${headVO.ogprotocol.url!}"/>
                <meta property="og:release_date"
                      content="${headVO.ogprotocol.releaseDate?string("yyyy-MM-dd")+'T'+headVO.ogprotocol.releaseDate?string("HH:mm:ss")+'Z'}"/>
                <meta property="og:author" content="${headVO.ogprotocol.author!}"/>
                <meta property="og:locale" content="${headVO.ogprotocol.locale!}"/>
                <meta property="og:site_name" content="${headVO.ogprotocol.siteName!}"/>
                <meta name="twitter:card" content="summary_large_image"/>
                <meta name="twitter:site" content="@renfeii"/>
                <meta name="twitter:creator" content="@renfeii"/>
                <meta name="twitter:title" content="${headVO.ogprotocol.title!}">
                <meta name="twitter:description" content="${headVO.ogprotocol.description!}">
                <meta name="twitter:domain" content="www.renfei.net">
                <meta name="twitter:image:src" content="${headVO.ogprotocol.image!}">
                <meta name="og:image:alt" content="RenFei 任霏">
            </#if>
            <meta property="fb:app_id" content="205704373959112"/>
            <meta property="fb:pages" content="108842630700746"/>
            <link id="favicon" rel="shortcut icon" href="https://cdn.renfei.net/favicon.ico" type="image/x-icon"/>
            <link rel="apple-touch-icon" href="https://cdn.renfei.net/Logo/logo_192.png">
            <link rel="search" type="application/opensearchdescription+xml"
                  href="https://www.renfei.net/search/search.xml" title="任霏博客"/>
            <#list headVO.css! as css>
                <link href="${css}" rel="stylesheet" type="text/css"/>
            </#list>
            <style>
                ${headVO.cssText!}
            </style>
            <script>
                ${headVO.jsText!}
            </script>
            <#nested>
        </head>
    </@compress>
</#macro>
<#macro header headerVO>
    <@compress single_line=true>
        <nav class="navbar navbar-expand-lg navbar-dark nav">
            <div class="container-xl">
                <a class="navbar-brand site-logo-name" href="/">
                    <img class="align-middle" src="https://cdn.renfei.net/Logo/RF_white.svg" height="25" alt="任霏博客"/>
                    <span class="align-middle">${headerVO.siteName!}</span>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07XL"
                        aria-controls="navbarsExample07XL" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample07XL">
                    <ul class="navbar-nav mr-auto">
                        <#if headerVO.menus??>
                            <#list headerVO.menus as menu>
                                <#if menu.subLink?? && (menu.subLink?size > 0)>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="${menu.href!}"
                                           target="${menu.target!}"
                                           rel="${menu.rel!}" style="${menu.style!}" id="dropdown07XL"
                                           data-toggle="dropdown"
                                           aria-expanded="false">${menu.text!}</a>
                                        <div class="dropdown-menu" aria-labelledby="dropdown07XL">
                                            <#list menu.subLink as submenu>
                                                <a class="dropdown-item" href="${submenu.href!}"
                                                   target="${submenu.target!}"
                                                   rel="${submenu.rel!}" style="${submenu.style!}">
                                                    ${submenu.text!}
                                                </a>
                                            </#list>
                                        </div>
                                    </li>
                                <#else>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${menu.href!}" target="${menu.target!}"
                                           rel="${menu.rel!}"
                                           style="${menu.style!}">
                                            ${menu.text!}
                                        </a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                    <form action="/search" method="get" class="form-inline my-2 my-lg-0">
                        <div class="input-group" style="width: 100%;">
                            <div class="input-group-prepend">
                                <select class="form-control form-control-sm btn-dark" name="type"
                                        style="border-top-right-radius: 0;border-bottom-right-radius: 0;">
                                    <option selected value="all">全部</option>
                                    <option value="post">文章</option>
                                    <option value="bbs">论坛</option>
                                    <option value="weibo">微博</option>
                                    <option value="page">页面</option>
                                    <option value="photo">相册</option>
                                    <option value="video">视频</option>
                                    <option value="kitbox">工具箱</option>
                                </select>
                            </div>
                            <input type="text" class="form-control form-control-sm btn-dark" placeholder="搜索一下，发现更多"
                                   name="w"
                                   aria-label="搜索一下，发现更多" aria-describedby="button-search">
                            <div class="input-group-append">
                                <button class="btn btn-dark btn-sm" type="submit" id="button-search">搜索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <#if headerVO.notice??>
            <div style="background-color: #0071E3;color:#FFFFFF;text-align: center;padding: 12px 0;font-size: 12px;">
                ${headerVO.notice!}
            </div>
        </#if>
        <#if headerVO.account??>
            <nav class="navbar navbar-expand-lg navbar-light bg-light nav" style="z-index: 1;">
                <div class="container-xl">
                    <div class="navbar-collapse"
                         style="display: -ms-flexbox!important;display: flex!important;-ms-flex-preferred-size: auto;flex-basis: auto;">
                        <ul class="navbar-nav ml-auto" style="-ms-flex-direction: row;flex-direction: row;">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="" style=""
                                   data-toggle="dropdown"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;"
                                   aria-expanded="false">${headerVO.account.userName!}</a>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="/account/manage" style="">
                                        管理账户
                                    </a>
                                    <a class="dropdown-item" href="JavaScript:signOut()" style="">
                                        登 出
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        <#else>
            <nav class="navbar navbar-expand-lg navbar-light bg-light nav" style="z-index: 1;">
                <div class="container-xl">
                    <div class="navbar-collapse"
                         style="display: -ms-flexbox!important;display: flex!important;-ms-flex-preferred-size: auto;flex-basis: auto;">
                        <ul class="navbar-nav ml-auto" style="-ms-flex-direction: row;flex-direction: row;">
                            <li class="nav-item">
                                <a class="nav-link" href="JavaScript:signInFun()"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;">登录</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/auth/signUp"
                                   style="font-size: 14px;padding-right: .5rem;padding-left: .5rem;">注册</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </#if>
        <#nested>
        <input type="hidden" id="_paste" value="">
    </@compress>
</#macro>
<#macro footer footerVO execTimeTotal execCountTotal>
    <@compress single_line=true>
        <div class="footer" style="margin-top: 20px;padding-top: 30px;">
            <div class="container">
                <div class="row">
                    <#list footerVO.footerMenus! as footerMenus>
                        <div class="col-lg-3 col-md-6 col-12 footerMenus">
                            <h3>${footerMenus.title!}</h3>
                            <ul>
                                <#list footerMenus.links! as link>
                                    <li>
                                        <a href="${link.href!}" target="${link.target!}" rel="nofollow noopener"
                                           style="${link.style!}">
                                            ${link.text!}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </#list>
                </div>
                <#if footerVO.showFriendlyLink??>
                    <div class="row" style="margin-top: 20px;">
                        <div class="col-12 text-left">
                            <ul class="footer-menu-ul">
                                <li class="float-left">
                                    <a href="javascipt:void(0)">
                                        <small class="text-muted small">友情链接：</small>
                                    </a>
                                </li>
                                <#list footerVO.friendlyLink! as friendlyLink>
                                    <li class="float-left">
                                        <a href="${friendlyLink.href!}" target="${friendlyLink.target!}"
                                           rel="noopener" style="${friendlyLink.style!}">
                                            <small class="text-muted small">${friendlyLink.text!}</small>
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </#if>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-12 text-right">
                        <ul class="footer-menu-ul">
                            <#list footerVO.smallMenu! as smallMenu>
                                <li class="float-right">
                                    <a href="${smallMenu.href!}" target="${smallMenu.target!}" rel="nofollow noopener"
                                       style="${smallMenu.style!}">
                                        <small class="text-muted small">${smallMenu.text!}</small>
                                    </a>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <hr style="margin: 7px 0;">
                <div class="row">
                    <div class="col-12">
                        <small class="text-muted small float-left" style="color: #86868b;">Copyright
                            © ${.now?string["yyyy"]} RENFEI.NET All rights
                            reserved.</small>
                        <ul class="float-right footer-menu-ul">
                            <#if execCountTotal??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        calls ${execCountTotal} methods.</small>
                                </li>
                            </#if>
                            <#if execTimeTotal??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Processed in ${execTimeTotal} second(s),</small>
                                </li>
                            </#if>
                            <li class="float-right">
                                <small class="text-muted small float-right"
                                       style="color: #86868b;">GMT+8, ${.now?string["yyyy-MM-dd HH:mm:ss"]},</small>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <ul class="float-right footer-menu-ul">
                            <#if footerVO.buildTime??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Build: ${footerVO.buildTime!}</small>
                                </li>
                            </#if>
                            <#if footerVO.version??>
                                <li class="float-right">
                                    <small class="text-muted small float-right" style="color: #86868b;">
                                        Ver: ${footerVO.version!}</small>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
                <div class="row" style="height: 20px;"></div>
            </div>
        </div>
        </div>
        <#list footerVO.jss! as js>
            <script src="${js}" type='text/javascript' charset="UTF-8"></script>
        </#list>
        <script>
            ${footerVO.jsText!}
        </script>
        <#nested>
    </@compress>
</#macro>
<#macro share sharevo>
    <@compress single_line=true>
        <#if sharevo??>
            <div class="row">
                <div class="col-12">
                    <div class="btn-group" role="group" aria-label="Third group">
                        <button id="wechat-share-box" type="button" class="btn btn-sm btn-outline-dark"
                                style="border: 0;">
                            <i class="fab fa-weixin"></i>
                            <div class="wechat-share">
                                <img src="/other/qrcode?content=${sharevo.url}" width="150">
                            </div>
                        </button>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://connect.qq.com/widget/shareqq/index.html?url=${sharevo.url}&title=${sharevo.title}&source=RENFEI.NET&desc=${sharevo.describes}&pics=${sharevo.pics}&summary=${sharevo.title}"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-qq"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://www.facebook.com/dialog/feed?app_id=${sharevo.fb_appid}&redirect_uri=${sharevo.url}&link=${sharevo.url}&display=popup"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-facebook-square"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="https://twitter.com/intent/tweet?text=${sharevo.title}&url=${sharevo.url}"
                           target="_blank"
                           rel="nofollow noopener" class="btn btn-sm btn-outline-dark" style="border: 0;">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="http://service.weibo.com/share/share.php?appkey=4264535112&url=${sharevo.url}&title=${sharevo.title}&pic=${sharevo.pics}"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-weibo"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <a href="https://www.linkedin.com/shareArticle?mini=true&url=${sharevo.url}&title=${sharevo.title}&source=RENFEI.NET"
                           target="_blank" rel="nofollow noopener" class="btn btn-sm btn-outline-dark"
                           style="border: 0;">
                            <i class="fab fa-linkedin"></i>
                        </a>
                    </div>
                    <div class="btn-group" role="group" aria-label="Third group">
                        <button type="button" class="copyUrlBtn btn btn-sm btn-outline-dark" style="border: 0;">
                            <i class="fas fa-link"></i>
                        </button>
                    </div>
                    <#if sharevo.views??>
                        <div class="btn-group" role="group" aria-label="Third group">
                            <button type="button" class="btn btn-sm btn-outline-dark" style="border: 0;">
                                <i class="fas fa-eye"></i> ${sharevo.views}
                            </button>
                        </div>
                    </#if>
                    <#nested>
                </div>
            </div>
        </#if>
    </@compress>
</#macro>
<#macro adsense slot active>
    <@compress single_line=true>
        <#if active=="prod">
            <ins class="adsbygoogle"
                 style="display:block"
                 data-ad-client="ca-pub-8859756463807757"
                 data-ad-slot="${slot}"
                 data-ad-format="auto"
                 data-full-width-responsive="true"></ins>
            <script>
                (adsbygoogle = window.adsbygoogle || []).push({});
            </script>
        </#if>
    </@compress>
</#macro>