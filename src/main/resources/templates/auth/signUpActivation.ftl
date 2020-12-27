<#include "../layout/layout.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title></@head>
    <body>
    <@header headerVO></@header>
    <#if pass>
        <div class="pb-4" style="margin-top: 80px;">
            <div class="container text-center">
                <div>
                    <i class="fa fa-check-circle" aria-hidden="true" style="font-size: 60px;color: #28a745;"></i>
                </div>
            </div>
        </div>
        <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h1 style="font-size: 24px;font-weight: 300;">您的账户已经激活。</h1>
                <h4 style="font-size: 14px;">接下来您可以登录您的账户了。</h4>
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                恭喜您已经激活了您的账户，接下来您可以<a href="/auth/signIn">登录</a>您的账户开始享受我们提供的服务了。
            </div>
        </div>
    <#else>
        <div class="pb-4" style="margin-top: 80px;">
            <div class="container text-center">
                <div>
                    <i class="fa fa-times-circle" aria-hidden="true" style="font-size: 60px;color: red;"></i>
                </div>
            </div>
        </div>
        <div style="width: 340px; margin: auto;margin-bottom: 230px;" class="px-3">
            <div class="p-0"
                 style="margin-bottom: 15px;text-align: center;text-shadow: none;background-color: initial;border: 0;">
                <h1 style="font-size: 24px;font-weight: 300;">未能激活您的账户。</h1>
                <h4 style="font-size: 14px;">验证码错误或者已经过期。</h4>
            </div>
            <div class="border rounded mt-3 p-4" style="font-size: 14px;">
                很遗憾，我们未能正确的识别您的账户，这可能是由于验证码错误或者已经过期，您可以尝试<a href="/auth/signIn">登录</a>，我们将为您重新发送激活邮件。
            </div>
        </div>
    </#if>

    <@footer footerVO performance_execTimeTotal performance_execCountTotal></@footer>
    </body>
    </html>
</@compress>