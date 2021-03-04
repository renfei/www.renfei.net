<#include "../layout/layout.ftl"/>
<#include "../layout/kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <style>
            .footer{
                display: none;
            }
            .texthidden {
                text-indent: -9999px;
                white-space: nowrap;
                line-height: 0;
                margin: 0;
            }
        </style>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <h1 class="texthidden">${onlineDocuments.title}</h1>
    <p class="texthidden">${onlineDocuments.describe}</p>
    <embed id="apiContent" src='${onlineDocuments.docLink}'/>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
    </@footer>
    <script type="text/javascript">
        $(function() {
            $("#apiContent").height($(window).height()-95);
            $("#apiContent").width($(window).width());
        });
    </script>
    </body>
    </html>
</@compress>