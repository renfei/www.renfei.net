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
    <h1 class="mui-title">发送系统邮件</h1>
</header>
<div class="mui-content">
    <div class="mui-content-padded">
        <h5>收件人：</h5>
        <div class="mui-input-row">
            <input type="text" id="to" class="mui-input-clear">
        </div>
        <h5>昵称：</h5>
        <div class="mui-input-row">
            <input type="text" id="name" class="mui-input-clear">
        </div>
        <h5>主题：</h5>
        <div class="mui-input-row">
            <input type="text" id="subject" class="mui-input-clear">
        </div>
        <h5>邮件内容：</h5>
        <div class="mui-input-row">
            <script id="editor" class="mb-2" type="text/plain" style="width:100%;min-height:200px;"></script>
        </div>
        <div class="mui-button-row">
            <button type="button" class="mui-btn mui-btn-primary mui-btn-block" onclick="send()">发 布</button>
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

    function send() {
        let sendEmailVO = {};
        sendEmailVO.to = $("#to").val();
        sendEmailVO.name = $("#name").val();
        sendEmailVO.subject = $("#subject").val();
        sendEmailVO.contents = UE.getEditor('editor').getContent();
        $.ajax({
            url: '/private/api/sendEmail',
            type: 'POST',
            async: false,
            data: JSON.stringify(sendEmailVO),
            timeout: 20000,
            dataType: 'JSON',
            contentType: "application/json;charset=utf-8",
            beforeSend: function (xhr) {
                xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code === 200) {
                    alert("发送成功");
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>
</body>
</html>