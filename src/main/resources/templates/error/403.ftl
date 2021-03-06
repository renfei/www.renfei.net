<#include "../layout/layout.ftl"/>
<#include "../layout/comments.ftl"/>
<@compress single_line=true>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head headVO,title></@head>
<body>
<@header headerVO></@header>
<div class="about-banner">
    <div class="container">
        <div class="row">
            <div class="col-12 about-img" style="background-image: url('/img/security.svg');background-position:right center;margin-top: 10px;">
                <div class="about-title d-none d-sm-block">
                    <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                        Error 403 (Forbidden)!!
                    </h1>
                    <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                        错误 403 – 拒绝访问！！
                    </h3>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container" style="padding-top: 50px;">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="d-sm-none mt-5">
                    <h1 style="font-size: 40px;line-height: 1.05;font-weight: 500;letter-spacing: .008em;">
                        Error 403 (Forbidden)!!
                    </h1>
                    <h3 style="font-size: 32px;line-height: 1.09375;font-weight: 300;letter-spacing: .011em;">
                        错误 403 – 拒绝访问！！
                    </h3>
                </div>
            </div>
        </div>
        <div class="row mt-5">
            您没有权限访问该资源，服务器理解了本次请求但是拒绝执行该任务。
        </div>
    </div>
</div>
<@footer footerVO performance_execTimeTotal performance_execCountTotal></@footer>
</body>
</html>
</@compress>