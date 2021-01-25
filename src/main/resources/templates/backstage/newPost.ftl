<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="https://cdn.renfei.net/thunder/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.renfei.net/thunder/mui/css/mui.min.css">
    <link rel="stylesheet" type="text/css" href="../css/app.css" />
    <link href="https://cdn.renfei.net/thunder/mui/css/mui.picker.css" rel="stylesheet" />
    <link href="https://cdn.renfei.net/thunder/mui/css/mui.poppicker.css" rel="stylesheet" />
</head>

<body style="height: 100%;">
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">发布新博客</h1>
</header>
<div class="mui-content">
    <div class="mui-content-padded">
        <h5>标题：</h5>
        <div class="mui-input-row">
            <input type="text" class="mui-input-clear">
        </div>
        <h5>描述：</h5>
        <div class="mui-input-row">
            <input type="text" class="mui-input-clear">
        </div>
        <h5>关键字：</h5>
        <div class="mui-input-row">
            <input type="text" class="mui-input-clear">
        </div>
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">
                <span>原创</span>
                <div class="mui-switch mui-switch-mini mui-active">
                    <div class="mui-switch-handle"></div>
                </div>
            </li>
        </ul>
        <div class="row">
            <div class="col-12 col-xl-6">
                <h5>作者：</h5>
                <div class="mui-input-row">
                    <input type="text" class="mui-input-clear">
                </div>
            </div>
            <div class="col-12 col-xl-6">
                <h5>原文链接：</h5>
                <div class="mui-input-row">
                    <input type="text" class="mui-input-clear">
                </div>
            </div>
        </div>
        <h5>分类</h5>
        <select class="mui-btn mui-btn-block">
            <option value="item-1">item-1</option>
            <option value="item-2">item-2</option>
            <option value="item-3">item-3</option>
            <option value="item-4">item-4</option>
            <option value="item-5">item-5</option>
        </select>
        <h5>博文内容：</h5>
        <div class="mui-input-row">
            <script id="editor" class="mb-2" type="text/plain" style="width:100%;min-height:200px;"></script>
        </div>
        <h5>特色图像：</h5>
        <div class="mui-input-row">
            <input type="file" class="mui-input-clear" accept="image/*">
        </div>
        <div class="mui-button-row">
            <button type="button" class="mui-btn mui-btn-primary mui-btn-block">发 布</button>
        </div>
    </div>
</div>
<script src="https://cdn.renfei.net/thunder/js/jquery-3.5.1.min.js" type='text/javascript' charset="UTF-8"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/mui.min.js"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/update.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="https://cdn.renfei.net/font/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    window.UEDITOR_HOME_URL = "/";
    var ue = UE.getEditor('editor');
</script>
</body>
</html>