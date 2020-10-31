<#include "../layout/layout.ftl"/>
<#include "../layout/pagination.ftl"/>
<#include "./sidebar.ftl"/>
<@compress single_line=true>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head headVO,title>
</@head>
<body>
<@header headerVO>

</@header>
<div class="container" style="padding-top: 50px;">
    <h3>任霏的Vlog视频</h3>
    <hr>
    <div class="row">
        <div class="col-md-9 col-sm-7">
            <div class="row mb-2">
                <#if videoDTOList?? && (videoDTOList?size > 0)>
                    <#list videoDTOList as video>
                        <div class="col-md-6">
                            <div class="card mb-2">
                                <img src="${video.featuredImage}" class="card-img-top" alt="${video.title}">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="/video/${video.id?c}" class="stretched-link" style="color: #000000;">${video.title}</a>
                                    </h5>
                                    <p class="card-text"><small
                                                class="text-muted">${video.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small>
                                    <p class="card-text">
                                        <#if (video.describes!)?length lt 80>
                                            ${video.describes!}
                                        <#else>
                                            ${(video.describes!)?substring(0,80)}...
                                        </#if>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            <div class="clear"></div>
            <@paginationLayout paginationList></@paginationLayout>
        </div>
        <div class="col-md-3 col-sm-5">
            <@sidebar></@sidebar>
        </div>
    </div>
</div>
<@footer footerVO performance_execTimeTotal performance_execCountTotal>
</@footer>
</body>
</html>
</@compress>