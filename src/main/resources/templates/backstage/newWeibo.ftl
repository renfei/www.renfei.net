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
</head>

<body style="height: 100%;">
<header class="mui-bar mui-bar-nav">
    <h1 class="mui-title">发布新微博</h1>
</header>
<div class="mui-content">
    <form class="mui-content-padded" name="weiboinfo">
        <h5>微博内容：</h5>
        <div class="mui-input-row">
            <textarea id="textarea" name="content" rows="6" placeholder="微博内容"></textarea>
        </div>
        <h5>特色图像：</h5>
        <div class="mui-input-row">
            <input type="file" name="image" class="mui-input-clear" accept="image/*">
        </div>
        <div class="mui-button-row">
            <button type="button" class="mui-btn mui-btn-primary mui-btn-block" onclick="sendWeibo()">发 布</button>
        </div>
    </form>
</div>
<script src="https://cdn.renfei.net/thunder/js/jquery-3.5.1.min.js" type='text/javascript' charset="UTF-8"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/mui.min.js"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/update.js" type="text/javascript" charset="utf-8"></script>
<script>
    function sendWeibo() {
        var oData = new FormData(document.forms.namedItem("weiboinfo"));
        var oReq = new XMLHttpRequest();
        try {
            oReq.open("post", "/private/api/newWeibo", true);
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