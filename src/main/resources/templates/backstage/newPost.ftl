<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="_csrf" content="${(_csrf.token)!}"/>
    <meta name="_csrf_header" content="${(_csrf.headerName)!}"/>
    <link href="https://cdn.renfei.net/thunder/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.renfei.net/thunder/mui/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="../css/app.css"/>
    <link href="https://cdn.renfei.net/thunder/mui/css/mui.picker.css" rel="stylesheet"/>
    <link href="https://cdn.renfei.net/thunder/mui/css/mui.poppicker.css" rel="stylesheet"/>
</head>

<body style="height: 100%;">
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">发布新博客</h1>
</header>
<div class="mui-content">
    <form class="mui-content-padded" name="weiboinfo">
        <div class="mui-content-padded">
            <h5>标题：</h5>
            <div class="mui-input-row">
                <input name="title" type="text" class="mui-input-clear">
            </div>
            <h5>描述：</h5>
            <div class="mui-input-row">
                <input name="describes" type="text" class="mui-input-clear">
            </div>
            <h5>关键字：</h5>
            <div class="mui-input-row">
                <input name="keyword" type="text" class="mui-input-clear">
            </div>
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <span>原创</span>
                    <div id="isOriginal" class="mui-switch mui-switch-mini mui-active">
                        <div class="mui-switch-handle"></div>
                    </div>
                </li>
            </ul>
            <div class="row">
                <div class="col-12 col-xl-6">
                    <h5>作者：</h5>
                    <div class="mui-input-row">
                        <input name="sourceName" type="text" class="mui-input-clear">
                    </div>
                </div>
                <div class="col-12 col-xl-6">
                    <h5>原文链接：</h5>
                    <div class="mui-input-row">
                        <input name="sourceUrl" type="text" class="mui-input-clear">
                    </div>
                </div>
            </div>
            <h5>分类</h5>
            <select id="categoryId" class="mui-btn mui-btn-block" name="categoryId">
                <#list category as cat>
                    <option value="${cat.id}">${cat.zhName}</option>
                </#list>
            </select>
            <h5>博文内容：</h5>
            <div class="mui-input-row">
                <script id="editor" class="mb-2" type="text/plain" style="width:100%;min-height:250px;"></script>
            </div>
            <h5>特色图像：</h5>
            <div class="mui-input-row">
                <input type="file" name="image" class="mui-input-clear" accept="image/*">
            </div>
            <h5 class="mui-content-padded">标签</h5>
            <div class="mui-card" style="height: 250px;overflow-y: auto;">
                <#list tags as tag>
                    <div class="mui-input-row mui-checkbox mui-left">
                        <label>${tag.zhName}</label>
                        <input name="tags" value="${tag.id}" type="checkbox">
                    </div>
                </#list>
            </div>
            <div class="mui-button-row">
                <button type="button" class="mui-btn mui-btn-primary mui-btn-block" onclick="save()">发 布</button>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.renfei.net/thunder/js/jquery-3.5.1.min.js" type='text/javascript' charset="UTF-8"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/mui.min.js"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/update.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="/js/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    window.UEDITOR_HOME_URL = "/";
    var ue = UE.getEditor('editor');

    function save() {
        var oData = new FormData(document.forms.namedItem("weiboinfo"));
        var oReq = new XMLHttpRequest();
        var ele = document.getElementsByName("tags");
        var tags = "";
        for (var i = 0; i < ele.length; i++) {
            if (ele[i].checked) {
                if (i === (ele.length - 1)) {
                    tags += ele[i].value;
                } else {
                    tags += ele[i].value + ",";
                }
            }
        }
        oData.append("content", UE.getEditor('editor').getContent());
        oData.append("isOriginal", document.getElementById("isOriginal").classList.contains("mui-active"));
        oData.append("tags", tags);
        try {
            oReq.open("post", "/private/api/post", false);
            oReq.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            oReq.send(oData);
            alert("完成");
        } catch (e) {
            alert(e);
        }
    }
</script>
</body>
</html>