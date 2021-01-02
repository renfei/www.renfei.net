<#include "layout/layout.ftl"/>
<#include "layout/pagination.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="container pt-4 pb-4" style="font-size: 14px;">
        <h3>${(queryTitle!"站内搜索")?html}</h3>
        <hr>
        <#if query??>
            <div class="row" style="min-height: 400px;">
                <div class="col-xl-9 col-lg-9 col-md-8">
                    <div class="row">
                        <div class="col-12">
                            <form action="/search" method="get">
                                <div class="input-group mb-1">
                                    <div class="input-group-prepend">
                                        <select class="form-control" name="type"
                                                style="border-top-right-radius: 0;border-bottom-right-radius: 0;">
                                            <option ${(type=="all"!)?string('selected','')} value="all">全部</option>
                                            <option ${(type=="post"!)?string('selected','')} value="post">文章</option>
                                            <option ${(type=="bbs"!)?string('selected','')} value="bbs">论坛</option>
                                            <option ${(type=="weibo"!)?string('selected','')} value="weibo">微博</option>
                                            <option ${(type=="page"!)?string('selected','')} value="page">页面</option>
                                            <option ${(type=="photo"!)?string('selected','')} value="photo">相册</option>
                                            <option ${(type=="video"!)?string('selected','')} value="video">视频</option>
                                            <option ${(type=="kitbox"!)?string('selected','')} value="kitbox">工具箱
                                            </option>
                                        </select>
                                    </div>
                                    <input type="text" class="form-control" placeholder="搜索一下，发现更多" name="w"
                                           value="${query?html}"
                                           aria-label="搜索一下，发现更多" aria-describedby="button-search">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-primary" type="submit" id="button-search">搜索
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <small class="text-muted">找到 ${total!"0"} 个结果(${searchTime!"0.0000000"} seconds)</small>
                        </div>
                    </div>
                    <div class="pt-4 pb-4">
                        <#if item?? && (item?size > 0)>
                            <#list item as itm>
                                <div class="row mb-3">
                                    <div class="col-12 col-lg-12">
                                        <h3 style="font-size: 18px;line-height: 22px;">
                                            <a href="${itm.url}" target="_blank">
                                                <#if (itm.title)?length lt 38>
                                                    ${itm.title}
                                                <#else>
                                                    ${(itm.title)?substring(0,38)}...
                                                </#if>
                                            </a>
                                        </h3>
                                    </div>
                                    <div class="col-3 col-lg-2">
                                        <img class="img-fluid bg-white rounded mx-auto d-block"
                                             src="${itm.image}" alt="${itm.title}"/>
                                    </div>
                                    <div class="col-9 col-lg-10">
                                        <p class="mb-0">
                                            <small class="text-muted">${itm.date?string("yyyy年MM月dd日")!}</small>
                                            <#if (itm.content)?length lt 50>
                                                ${itm.content!}
                                            <#else>
                                                ${(itm.content)?substring(0,50)}...
                                            </#if>
                                        </p>
                                        <small class="text-muted">${itm.url}</small>
                                    </div>
                                </div>
                            </#list>
                            <@paginationLayout paginationList></@paginationLayout>
                        </#if>
                    </div>
                </div>
                <div class="col-xl-3 col-lg-3 col-md-4">
                    <div class="card mb-3" style="margin-bottom: 1rem;">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 18px;">关注任霏博客</h5>
                            <h6 class="card-subtitle mb-2 text-muted">扫码关注「任霏博客」微信订阅号</h6>
                            <div class="text-center">
                                <img src="https://cdn.renfei.net/images/WechatQR.png" class="img-fluid" width="500px"
                                     height="500px"
                                     style="max-width: 140px;height:auto;margin: auto;"/>
                            </div>
                            <div style="font-size: 12px;" class="mt-2">
                                微博：<a href="https://weibo.com/5214619228" target="_blank"
                                      rel="nofollow noopener">任霏博客网</a><br/>
                                Twitter：<a href="https://twitter.com/renfeii" target="_blank" rel="nofollow noopener">@renfeii</a><br/>
                                Facebook:<a href="https://www.facebook.com/renfeii" target="_blank"
                                            rel="nofollow noopener">任霏</a>
                            </div>
                        </div>
                    </div>
                    <div class="card" style="margin-bottom: 1rem;">
                        <div class="card-body">
                            <h5 class="card-title" style="font-size: 18px;">搜索热榜</h5>
                            <h6 class="card-subtitle mb-2 text-muted">搜索词来自网友历史搜索自动产生，不代表本站立场。</h6>
                            <div class="text-center">
                                <ol class="list-unstyled text-left">
                                    <#if hotSearchList?? && (hotSearchList?size > 0)>
                                        <#list hotSearchList as list>
                                            <li style="white-space: nowrap;overflow: hidden;">
                                                <a href="/search?type=all&w=${list.word?html}"
                                                   title="${list.word?html}">${list.word?html}</a>
                                            </li>
                                        </#list>
                                    </#if>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="row pt-5 pb-4" style="min-height: 400px;">
                <div class="col-md-2">
                </div>
                <div class="col-md-8">
                    <form action="/search" method="get">
                        <div class="input-group input-group-lg mb-3">
                            <div class="input-group-prepend">
                                <select class="form-control form-control-lg" name="type"
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
                            <input type="text" class="form-control" placeholder="搜索一下，发现更多" name="w"
                                   aria-label="搜索一下，发现更多" aria-describedby="button-search">
                            <div class="input-group-append">
                                <button class="btn btn-outline-primary" type="submit" id="button-search">搜索</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </#if>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>

    </@footer>
    </body>
    </html>
</@compress>