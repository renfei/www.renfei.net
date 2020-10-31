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
                        <h5 class="card-title">IP地址信息查询工具</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            IP地址信息查询工具，开放服务接口实现IP信息查询，查询IP地址所属地理位置</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">IP:</div>
                                    </div>
                                    <input type="text" class="form-control" value="${myip!''}"
                                           id="iptext" placeholder="IP">
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" onclick="query()">Query</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">国家代码</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryShort"
                                               value="${ipdto.countryShort!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-2 col-form-label col-form-label-sm">国家</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryLong"
                                               value="${ipdto.countryLong!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="region" class="col-sm-2 col-form-label col-form-label-sm">省份</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="region"
                                               value="${ipdto.region!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="city" class="col-sm-2 col-form-label col-form-label-sm">城市</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="city"
                                               value="${ipdto.city!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="isp" class="col-sm-2 col-form-label col-form-label-sm">ISP</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="isp"
                                               value="${ipdto.isp!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="zipcode" class="col-sm-2 col-form-label col-form-label-sm">邮编</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="zipcode"
                                               value="${ipdto.zipcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="latitude" class="col-sm-2 col-form-label col-form-label-sm">纬度</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="latitude"
                                               value="${ipdto.latitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="longitude" class="col-sm-2 col-form-label col-form-label-sm">经度</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="longitude"
                                               value="${ipdto.longitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="timezone" class="col-sm-2 col-form-label col-form-label-sm">时区</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="timezone"
                                               value="${ipdto.timezone!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="domain" class="col-sm-2 col-form-label col-form-label-sm">domain</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="domain"
                                               value="${ipdto.domain!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="iddcode"
                                           class="col-sm-2 col-form-label col-form-label-sm">iddcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="iddcode"
                                               value="${ipdto.iddcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="areacode"
                                           class="col-sm-2 col-form-label col-form-label-sm">areacode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="areacode"
                                               value="${ipdto.areacode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationcode" class="col-sm-2 col-form-label col-form-label-sm">weatherstationcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationcode"
                                               value="${ipdto.weatherstationcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationname" class="col-sm-2 col-form-label col-form-label-sm">weatherstationname</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationname"
                                               value="${ipdto.weatherstationname!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mcc" class="col-sm-2 col-form-label col-form-label-sm">mcc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mcc"
                                               value="${ipdto.mcc!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mnc" class="col-sm-2 col-form-label col-form-label-sm">mnc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mnc"
                                               value="${ipdto.mnc!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mobilebrand" class="col-sm-2 col-form-label col-form-label-sm">mobilebrand</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="mobilebrand"
                                               value="${ipdto.mobilebrand!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="elevation"
                                           class="col-sm-2 col-form-label col-form-label-sm">elevation</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="elevation"
                                               value="${ipdto.elevation!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="usagetype"
                                           class="col-sm-2 col-form-label col-form-label-sm">usagetype</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="usagetype"
                                               value="${ipdto.usagetype!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="status" class="col-sm-2 col-form-label col-form-label-sm">status</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="status"
                                               value="${ipdto.status!'...'}">
                                    </div>
                                </div>
                            </form>
                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>IP查询接口服务已经开放。更多开放接口服务，请参考<a href="/swagger-ui.html" target="_blank">开放接口文档</a>。
                                    </p>
                                    <p>此工具使用的接口是：https://www.renfei.net/api/ip/{ip}</p>
                                    <footer>{ip} - 更换为需要查询的IP地址，使用 GET 请求。</footer>
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
    </div>
    <@footer footerVO performance_execTimeTotal performance_execCountTotal>
        <script>
            function query() {
                var ip = $("#iptext").val();
                if (ip === '') {
                    msg("请填写要查询的IP地址", "error");
                    return;
                }
                $.ajax({
                    url: '/api/ip/' + ip,
                    type: 'GET',
                    async: true,
                    timeout: 10000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.code === 200) {
                            $("#countryShort").val(data.data.countryShort);
                            $("#countryLong").val(data.data.countryLong);
                            $("#region").val(data.data.region);
                            $("#city").val(data.data.city);
                            $("#isp").val(data.data.isp);
                            $("#latitude").val(data.data.latitude);
                            $("#longitude").val(data.data.longitude);
                            $("#timezone").val(data.data.timezone);
                            $("#domain").val(data.data.domain);
                            $("#zipcode").val(data.data.zipcode);
                            $("#netspeed").val(data.data.netspeed);
                            $("#iddcode").val(data.data.iddcode);
                            $("#areacode").val(data.data.areacode);
                            $("#weatherstationcode").val(data.data.weatherstationcode);
                            $("#weatherstationname").val(data.data.weatherstationname);
                            $("#mcc").val(data.data.mcc);
                            $("#mnc").val(data.data.mnc);
                            $("#mobilebrand").val(data.data.mobilebrand);
                            $("#elevation").val(data.data.elevation);
                            $("#usagetype").val(data.data.usagetype);
                        } else {
                            msg(data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        msg(xhr.responseText, "error");
                    }
                })
            }
        </script>
    </@footer>
    </body>
    </html>
</@compress>