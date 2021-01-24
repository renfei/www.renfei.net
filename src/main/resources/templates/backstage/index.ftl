<#include "../layout/layout.ftl"/>
<#include "backstage_menu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title></@head>
    <body>
    <@header headerVO></@header>
    <div class="container" style="padding-top: 20px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
                <@backstageMenu></@backstageMenu>
            </div>
            <div class="col-sm-9 col-md-9">
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
    </@footer>
    </body>
</html>
</@compress>