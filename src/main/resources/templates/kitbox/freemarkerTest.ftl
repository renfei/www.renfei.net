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
                        <h5 class="card-title">FreeMarker(FTL)在线测试工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">FreeMarker(FTL) Online Test Tools</h6>
                        <div class="row">
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>FreeMarker (ftl) Code</label>
                                        <textarea id="ftl" name="ftl" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <form>
                                    <div class="form-group">
                                        <label>JavaBean (JSON) Code</label>
                                        <textarea id="json" name="json" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <button type="button" class="btn btn-primary" onclick="test()"
                                        style="width: 100%;margin: 10px 0;">Test
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <form>
                                    <div class="form-group">
                                        <label>Result</label>
                                        <textarea id="data" name="data" class="form-control" rows="8"></textarea>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <blockquote style="font-size: 14px;">
                                <p>在「FreeMarker (ftl) Code」中填写FreeMarker代码；在「JavaBean (JSON)
                                    Code」中填写入参对象的JSON字符串；「Result」将返回FreeMarker引擎的运行结果。
                                </p>
                                <footer>只支持自带函数方法，不支持自定义函数方法</footer>
                            </blockquote>
                        </div>
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
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script>
            $(function () {
                $("#ftl").val("<html>\n\t<body>\n\t\t ${'$'}{demo.text}\n\t</body>\n</html>");
                $("#json").val("{\n\t\"demo\":{\n\t\t\"text\":\"Hello World\"\n\t}\n}");
            });

            function test() {
                let ftl = $("#ftl").val();
                let json = $("#json").val();
                if (ftl === "") {
                    $("#data").val("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！");
                    msg("Error: FreeMarker Code is empty!\n错误：FreeMarker Code是空！", "error");
                    return;
                }
                if (json === "") {
                    $("#data").val("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！");
                    msg("Error: JavaBean Code is empty!\n错误：JavaBean Code是空！", "error");
                    return;
                }
                $.ajax({
                    url: '/api/freemarker/test',
                    type: 'POST',
                    async: true,
                    data: {
                        ftl: ftl,
                        beanJson: json
                    },
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