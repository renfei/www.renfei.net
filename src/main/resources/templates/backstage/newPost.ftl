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
                    <label for="title">标题</label>
                    <input type="text" class="form-control" id="title">
                </div>
                <div class="form-group">
                    <label for="keyword">关键字</label>
                    <input type="text" class="form-control" id="keyword">
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="customFile" accept="image/*">
                                    <label class="custom-file-label" for="customFile">选择图片</label>
                                </div>
                                <label class="pt-2">分类</label>
                                <select class="custom-select">
                                    <option selected>默认分类</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="describes">描述</label>
                            <textarea class="form-control" id="describes" rows="3"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <script id="editor" class="mb-2" type="text/plain" style="width:100%;height:500px;"></script>
                </div>
                <div class="form-group">
                    <label for="describes">标签：</label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
                        <label class="form-check-label" for="inlineCheckbox1">1</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
                        <label class="form-check-label" for="inlineCheckbox2">2</label>
                    </div>
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