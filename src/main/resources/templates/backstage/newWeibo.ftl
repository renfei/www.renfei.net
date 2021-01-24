<#include "../layout/layout.ftl"/>
<#include "backstage_menu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/ueditor.all.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/lang/zh-cn/zh-cn.js"></script>
    </@head>
    <body>
    <@header headerVO></@header>
    <div class="container" style="padding-top: 20px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
                <@backstageMenu></@backstageMenu>
            </div>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="customFile" accept="image/*">
                        <label class="custom-file-label" for="customFile">选择图片</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="describes">内容</label>
                    <textarea class="form-control" id="describes" rows="3"></textarea>
                </div>
                <button type="button" class="btn btn-primary btn-lg btn-block">发布</button>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script type="text/javascript">
            window.UEDITOR_HOME_URL = "/";
            var ue = UE.getEditor('editor');
        </script>
    </@footer>
    </body>
</html>
</@compress>