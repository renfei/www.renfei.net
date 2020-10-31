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
    <h3>任霏的网络相册</h3>
    <hr>
    <div class="row">
        <div class="col-md-9 col-sm-7">
            <div class="row mb-2">
                <#if photoList?? && (photoList?size > 0)>
                    <#list photoList as photo>
                        <div class="col-md-4">
                            <div class="card mb-2">
                                <img src="${photo.featuredImage}?x-oss-process=style/992.558" class="card-img-top" alt="${photo.title}">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a href="/photo/${photo.id?c}" class="stretched-link" style="color: #000000;">${photo.title}</a>
                                    </h5>
                                    <p class="card-text"><small
                                                class="text-muted">${photo.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small>
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