<#include "../layout/layout.ftl"/>
<#include "../layout/pagination.ftl"/>
<#include "../layout/comments.ftl"/>
<#include "./sidebar.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/plyr@3.6.2/dist/plyr.css"/>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <h3>${video.title!}</h3>
        <hr>
        <div class="row">
            <div class="col-md-9 col-sm-7">
                <div class="row">
                    <div class="col-12">
                        <video style="width: 100%;" controls crossorigin playsinline poster="${video.featuredImage}" id="player">
                            <#if video.videoSource?? && (video.videoSource?size > 0)>
                                <#list video.videoSource as source>
                                    <source src="${source.videoSrc}" type="${source.videoType}"
                                            size="${source.videoSize}"/>
                                </#list>
                            </#if>
                            <#if video.videoTrack?? && (video.videoTrack?size > 0)>
                                <#list video.videoTrack as track>
                                    <track kind="${track.kind}" label="${track.label}" srclang="${track.srclang}"
                                           src="${track.src}"/>
                                </#list>
                            </#if>
                            <#if video.download??>
                                <a href="${video.download}" target="_blank" download>Download</a>
                            </#if>
                        </video>
                        <div class="card-body">
                            <p class="card-text"><small
                                        class="text-muted">${video.releaseTime?string["yyyy-MM-dd HH:mm:ss"]}</small>
                            <p class="card-text">
                                ${video.describes!}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
                <hr>
                <div class="row">
                    <div class="col-12">
                        <@comments commentsVO></@comments>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-5">
                <@sidebar active></@sidebar>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <!-- Polyfills -->
        <script
                src="https://cdn.polyfill.io/v3/polyfill.min.js?features=es6,Array.prototype.includes,CustomEvent,Object.entries,Object.values,URL,Math.trunc&flags=gated"
                crossorigin="anonymous"
        ></script>

        <!-- Sharing libary (https://shr.one) -->
        <script src="https://cdn.shr.one/2.0.0-beta.2/shr.js" crossorigin="anonymous"></script>
        <!-- Docs script -->
        <script src="https://cdn.jsdelivr.net/npm/plyr@3.6.2/dist/plyr.min.js" crossorigin="anonymous"></script>
    </@footer>
    </body>
    </html>
</@compress>