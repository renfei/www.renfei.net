function msg(content, type) {
    switch (type) {
        case "main":
            bs4pop.notice(content, {type: 'primary'});
            break;
        case "success":
            bs4pop.notice(content, {type: 'success'});
            break;
        case "warning":
            bs4pop.notice(content, {type: 'warning'});
            break;
        case "error":
            bs4pop.notice(content, {type: 'danger'});
            break;
        default:
            bs4pop.notice(content, {type: 'secondary'});
            break;
    }
}

function replyTo(name, id) {
    $("#replyName").val(name);
    $("#replyId").val(id);
    $("#replyTo").show();
}

function cancelReply() {
    $("#replyId").val("");
    $("#replyName").val("");
    $("#comment-content").val("");
    $("#replyTo").hide();
}

console.log("\n %c RENFEI.NET %c 你知道的太多了 %c \n", "color: #fff; background: #3274ff; padding:5px 0; border: 1px solid #3274ff;", "color: #3274ff; background: #fff; padding:5px 0; border: 1px solid #3274ff;", "");
console.log("\n %c 开发交流QQ群 %c 130832168 %c \n", "color: #fff; background: #eb0; padding:5px 0; border: 1px solid #eb0;", "color: #eb0; background: #fff; padding:5px 0; border: 1px solid #eb0;", "");

function comment(typeid, id) {
    let nickname = $("#comment-nickname").val();
    let email = $("#comment-email").val();
    let link = $("#comment-link").val();
    let content = $("#comment-content").val();
    let replyId = $("#replyId").val();
    let data = {};
    if (nickname === "") {
        msg("昵称不能为空", "error");
        return;
    } else {
        data.author = nickname;
    }
    if (email === "") {
        msg("电子邮件地址不能为空", "error");
        return;
    } else {
        data.authoremail = email;
    }
    if (content === "") {
        msg("评论内容不能为空", "error");
        return;
    } else {
        data.content = content;
    }
    if (link !== "") {
        data.link = link;
    }
    if (replyId !== "") {
        data.reply = replyId;
    }
    $.ajax({
        url: '/api/comments/' + typeid + '/' + id,
        type: 'POST',
        async: true,
        data: data,
        timeout: 5000,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data.code === 200) {
                cancelReply();
                msg("提交成功！\n本站有缓存策略，约2小时后可以看到您提交的评论。", "success")
            } else {
                msg("提交失败！" + data.message, "error");
            }
        },
        error: function (xhr, textStatus) {
            msg("Error - 错误:" + xhr.responseText, "error");
        }
    });
}

function getJiSuDownloadLink() {
    var downloadfile_jisu_btn = $("#downloadfile_jisu_btn").html();
    var downloadfile_jisu_code = $("#downloadfile_jisu_code").val();
    if (downloadfile_jisu_code === "") {
        msg("请扫描二维码关注微信公众号获得授权码", "error");
        $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
        return;
    }
    $("#downloadfile_jisu_btn").html("正在获取请稍后");
    $.ajax({
        url: '/other/JiSuDownloadLink',
        type: 'GET',
        async: true,
        data: {
            code: downloadfile_jisu_code
        },
        timeout: 5000,
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            if (data.code === 200) {
                $('#downloadfile_jisu_link').attr('href', data.data.jisulink);
                $('#downloadfile_jisu_link').html(data.data.jisulink);
                $('#downloadfile_jisu_exp').html(data.data.expires);
            } else {
                $("#downloadfile_jisu_link").html("极速下载链接获取失败！" + data.message);
                msg(data.message, "error");
            }
            $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
        },
        error: function (xhr, textStatus) {
            $("#downloadfile_jisu_btn").html(downloadfile_jisu_btn);
            $("#downloadfile_jisu_link").html("极速下载链接获取失败，网络错误！");
            msg(textStatus, xhr.responseText, "error");
        }
    });
}

$(function () {
    if ($(".copyUrlBtn").length) {
        let clipboard = new ClipboardJS('.copyUrlBtn', {
            text: function () {
                return document.location.href;
            }
        });
        clipboard.on('success', function (e) {
            msg("复制链接成功！", "success");
        });
        clipboard.on('error', function (e) {
            msg("当前浏览器不支持此功能，请手动复制。", "error");
        });
    }
})