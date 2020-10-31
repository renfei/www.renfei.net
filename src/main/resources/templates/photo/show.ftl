<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <link rel="stylesheet" type="text/css" href="//cdn.renfei.net/css/lightbox.css?v=20200506135243"/>
        <link rel="stylesheet" type="text/css" href="//cdn.renfei.net/css/baguetteBox.min.css"/>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-12">
                <div class="card mb-3">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <img src="${photo.featuredImage}" class="card-img" alt="${photo.title}">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${photo.title}</h5>
                                <p class="card-text"><small class="text-muted">${photo.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small></p>
                                <p class="card-text">${photo.describes}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h2 class="title text-center">Photos</h2>
        <div class="row photos-content">
            <#if photoImg.photoImgs?? && (photoImg.photoImgs?size > 0)>
                <#list photoImg.photoImgs as img>
                    <div class="col-sm-3 mb-2">
                        <div class="portfolio-wrapper">
                            <div class="portfolio-single">
                                <div class="portfolio-thumb">
                                    <a href="${img.uri}" class="lightbox" style="position: initial;" data-lightbox="example-set">
                                        <img src="${img.uri}?x-oss-process=style/992.558" class="img-fluid shadow bg-white rounded mx-auto d-block" alt="">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script type='text/javascript' charset="UTF-8" src="//cdn.renfei.net/js/lightbox.min.js?v=20200506135243"
                async></script>
        <script type='text/javascript' charset="UTF-8" src="//cdn.renfei.net/js/baguetteBox.min.js"></script>
        <script>
            $(function () {
                baguetteBox.run('.photos-content');
            });
        </script>
    </@footer>
    </body>
    </html>
</@compress>