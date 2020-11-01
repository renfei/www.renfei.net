<#include "../layout/layout.ftl"/>
<#include "../layout/kitboxmenu.ftl"/>
<@compress single_line=true>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
    <@head headVO,title>
    </@head>
    <body>
    <@header headerVO>

    </@header>
    <div class="container" style="padding-top: 50px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
                <@KitBoxMenu KitBoxMenus></@KitBoxMenu>
            </div>
            <div class="col-sm-9 col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">域名Whois信息查询工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            域名Whois信息查询工具，查询域名是否已经被注册，以及注册域名的详细信息的数据库（如域名所有人、域名注册商、域名注册日期和过期日期等）。通过域名Whois服务器查询，可以查询域名归属者联系方式，以及注册和到期时间。
                        </h6>
                        <div>
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" class="form-control" id="domain"
                                           placeholder="Domain Name" value="renfei.net"/>
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="query()">查询</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <div class="col-sm-12">
                                        <textarea id="data" name="data" class="form-control" rows="15"></textarea>
                                    </div>
                                </div>
                            </form>

                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>IP查询接口服务已经开放。更多开放接口服务，请参考<a href="/swagger-ui.html" target="_blank">开放接口文档</a>。
                                    </p>
                                    <p>此工具使用的接口是：https://www.renfei.net/api/dns/dig/{domain}</p>
                                    <footer>{domain} - 更换为需要查询的域名，使用 GET 请求。</footer>
                                </blockquote>
                            </div>
                            <div class="d-none d-sm-block">
                                <ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-8859756463807757"
                                     data-ad-slot="3646319863" data-ad-format="auto"
                                     data-full-width-responsive="true"></ins>
                                <script>
                                    (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script>
            function query() {
                let domain = $("#domain").val();
                if (domain === "") {
                    msg("请填写要查询的域名", "error");
                    return;
                }
                $("#data").val("Please wait a moment while you are inquiring.\n正在查询请稍后。");
                $.ajax({
                    url: '/api/dns/whois/' + domain,
                    type: 'GET',
                    async: true,
                    timeout: 60000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#data").val(data.data);
                        } else {
                            $("#data").val("Error.\n" + data.message);
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        $("#data").val("Error.\n" + xhr.responseText);
                        msg(xhr.responseText, "error");
                    }
                });
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>