<#include "../layout/layout.ftl"/>
<#include "../layout/kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/html" xml:lang="zh-CN" lang="zh-CN"
          dir="ltr"
          prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
        <style>
            .featurette-divider {
                margin-top: 20px;
                margin-bottom: 30px;
            }

            .red {
                color: red;
            }
        </style>
        <script>
            function toSubmit(e) {
                e.preventDefault();
                window.open('https://github.renfei.net/' + document.getElementsByName('q')[0].value);
                return false;
            }
        </script>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
                <@KitBoxMenu KitBoxMenus active></@KitBoxMenu>
            </div>
            <div class="col-sm-9 col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">GitHub 文件在线下载代理加速工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            GitHub 文件 , Releases , archive , gist , raw.githubusercontent.com 文件代理加速下载服务。
                        </h6>
                        <div style="margin: auto;max-width: 700px;">
                            <form action="./" method="get" target="__blank"
                                  onsubmit="toSubmit(event)">
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" class="form-control" id="quantityNumber"
                                           name="q"
                                           placeholder="输入 GitHub 文件链接"
                                           pattern="^((https|http):\/\/)?(github\.com\/.+?\/.+?\/(?:releases|archive|blob)|((?:raw|gist)\.(?:githubusercontent|github)\.com))\/.+$"
                                           aria-describedby="button-go" required/>
                                    <div class="input-group-prepend">
                                        <button class="btn btn-primary" type="submit" id="button-go">下载</button>
                                    </div>
                                </div>
                            </form>
                            <small class="text-muted">GitHub 文件 , Releases , archive , gist , raw.githubusercontent.com
                                文件代理加速下载服务。</small>
                            <div class="row featurette">
                                <div class="col-md-12">
                                    <p>使用当前页面地址栏输入合规链接（参考以下链接）点击下载按钮：</p>
                                    <p>支持 <span class="red">raw.githubusercontent.com</span> , <span class="red">gist.github.com</span>
                                        , <span class="red">gist.githubusercontent.com</span> 文件下载.</p>
                                    <h4 class="text-muted">Raw 文件</h4>
                                    <p>https://raw.githubusercontent.com/renfei/start/master/LICENSE</p>
                                    <h4 class="text-muted">分支源码</h4>
                                    <p>https://github.com/renfei/start/archive/master.zip</p>
                                    <h4 class="text-muted">Releases 源码</h4>
                                    <p>https://github.com/renfei/renfei-java-sdk/archive/refs/tags/1.1.1.tar.gz</p>
                                    <h4 class="text-muted">Releases 文件</h4>
                                    <p>https://github.com/fatedier/frp/releases/download/v0.33.0/frp_0.33.0_linux_amd64.tar.gz</p>
                                </div>
                            </div>
                            <hr class="featurette-divider">
                            <div class="row featurette">
                                <div class="col-md-12">
                                    <h2 class="featurette-heading">终端命令行 <span class="text-muted">Terminal command line</span></h2>
                                    <p>支持终端命令行 git clone , wget , curl 等工具下载.</p>
                                    <p>支持 <span class="red">raw.githubusercontent.com</span> , <span class="red">gist.github.com</span>
                                        , <span class="red">gist.githubusercontent.com</span> 文件下载.</p>
                                    <p>注意：不支持 SSH Key 方式 git clone 下载.</p>
                                    <h4 class="text-muted">git clone</h4>
                                    <p>git clone <span class="red">https://github.renfei.net/</span>https://github.com/renfei/start
                                    </p>
                                    <h4 class="text-muted">wget & curl</h4>
                                    <p>wget <span class="red">https://github.renfei.net/</span>https://github.com/renfei/start/archive/master.zip
                                    </p>
                                    <p>wget
                                        <span class="red">https://github.renfei.net/</span>https://raw.githubusercontent.com/renfei/start/master/LICENSE
                                    </p>
                                    <p>curl -O <span class="red">https://github.renfei.net/</span>https://github.com/renfei/start/archive/master.zip
                                    </p>
                                    <p>curl -O
                                        <span class="red">https://github.renfei.net/</span>https://raw.githubusercontent.com/renfei/start/master/LICENSE
                                    </p>
                                </div>
                            </div>
                            <div class="d-none d-sm-block">
                                <@adsense "9903187829" active></@adsense>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script>
            function generate() {
                let data = {
                    upperCase: true,
                    hyphen: true,
                    quantity: 1
                };
                if (!$("#upper").prop('checked')) {
                    data.upperCase = false;
                }
                if (!$("#hyphen").prop('checked')) {
                    data.hyphen = false;
                }
                if ($("#quantityNumber").val() !== "") {
                    data.quantity = $("#quantityNumber").val();
                } else {
                    msg("请输入生成的数量", "error");
                    return;
                }
                $("#data").val("UUID is being generated, please wait.\nUUID正在生成中，请稍后。");
                $.ajax({
                    url: '/api/uuid',
                    type: 'GET',
                    async: true,
                    data: data,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            var str = "";
                            for (var i = 0; i < data.data.length; i++) {
                                str += data.data[i] + "\n";
                            }
                            $("#data").val(str);
                        } else {
                            $("#data").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#data").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                })
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>