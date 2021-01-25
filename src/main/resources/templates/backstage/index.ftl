<!DOCTYPE html>
<html style="height: 100%;">
<head>
    <meta charset="utf-8">
    <title>RENFEI.NET 管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link href="https://cdn.renfei.net/thunder/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdn.renfei.net/thunder/mui/css/mui.min.css">
    <style type="text/css">
        #list {
            /*避免导航边框和列表背景边框重叠，看起来像两条边框似得；*/
            margin-top: -1px;
        }

        .title {
            margin: 20px 15px 7px;
            color: #6d6d72;
            font-size: 15px;
        }
    </style>
    <script>
        function goMenu(href) {
            $('#iframe').attr('src', href);
        }
    </script>
</head>

<body style="height: 100%;">
<div class="container-fluid" style="height: 100%;">
    <div class="row" style="height: 100%;">
        <div class="col-5 col-xl-3" style="border-right: 1px #c3c2c7 solid;padding: 0;height: 100%;overflow-y: auto;">
            <div class="mui-content">
                <div class="title">
                    RENFEI.NET 管理系统
                </div>
                <ul id="list" class="mui-table-view mui-table-view-chevron">
                    <li class="mui-table-view-cell">
                        <a class="mui-navigate-right" href="JavaScript:goMenu('')">
                            发送邮件
                        </a>
                    </li>
                </ul>
                <div class="title">
                    博客管理
                </div>
                <ul id="list" class="mui-table-view mui-table-view-chevron">
                    <li class="mui-table-view-cell">
                        <a class="mui-navigate-right" href="JavaScript:goMenu('/backstage/newPost')">
                            发布博客
                        </a>
                    </li>
                </ul>
                <div class="title">
                    微博管理
                </div>
                <ul id="list" class="mui-table-view mui-table-view-chevron">
                    <li class="mui-table-view-cell">
                        <a class="mui-navigate-right" href="JavaScript:goMenu('/backstage/newWeibo')">
                            发布微博
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-7 col-xl-9" style="padding: 0;">
            <iframe id="iframe" src="" style="border: 0;width: 100%;height: 100%;overflow-y: auto;"></iframe>
        </div>
    </div>
</div>
<script src="https://cdn.renfei.net/thunder/js/jquery-3.5.1.min.js" type='text/javascript' charset="UTF-8"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/mui.min.js"></script>
<script src="https://cdn.renfei.net/thunder/mui/js/update.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>