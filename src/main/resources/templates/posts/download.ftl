<#macro download downloadfile>
    <div>
        <div class="card mb-3">
            <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${downloadfile.icon}" width="249px" height="246px" style="width: 100%;height:auto;"
                         class="img-responsive" alt="${downloadfile.name}">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${downloadfile.name!"暂无"}</h5>
                        <p class="card-text mb-0"><small
                                    class="text-muted">${downloadfile.created?string("yyyy-MM-dd HH:mm:ss")!"Unknown"}</small>
                        </p>
                        <form>
                            <div class="form-group row mb-0">
                                <label class="col-md-2 col-form-label">Name</label>
                                <div class="col-md-10">
                                    <label class="col-form-label"
                                           style="font-weight: 300;">${downloadfile.name!"暂无"}</label>
                                </div>
                            </div>
                            <div class="form-group row mb-0">
                                <label class="col-md-2 col-form-label">Size</label>
                                <div class="col-md-10">
                                    <label class="col-form-label"
                                           style="font-weight: 300;">${downloadfile.size!"暂无"}</label>
                                </div>
                            </div>
                            <div class="form-group row mb-0">
                                <label class="col-md-2 col-form-label">Hash</label>
                                <div class="col-md-10">
                                    <label class="col-form-label"
                                           style="font-weight: 300;">${downloadfile.hash!"暂无"}</label>
                                </div>
                            </div>
                            <div class="form-group row mb-0">
                                <label class="col-md-2 col-form-label">Files</label>
                                <div class="col-md-10">
                                    <label class="col-form-label"
                                           style="font-weight: 300;">${downloadfile.fileName!"暂无"}</label>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="alert alert-info" role="alert">
            社区分享已经上线：<a href="https://bbs.renfei.net/forum-41-1.html" target="_blank">https://bbs.renfei.net/forum-41-1.html</a>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                下载服务<small>Download Service</small>
            </div>
            <div class="card-body">
                <form>
                    <div class="form-group row">
                        <label class="col-md-2 col-form-label">百度云盘</label>
                        <div class="col-md-10">
                            <label class="col-form-label" style="font-weight: 300;">
                                <a target="_blank"
                                   href="${downloadfile.baiduYunPanUrl!'JavaScript:void(0)'}">
                                    ${downloadfile.baiduYunPanUrl!'暂无'}
                                </a>
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-2 col-form-label">提取码</label>
                        <div class="col-md-10">
                            <label class="col-form-label" style="font-weight: 300;">
                                ${downloadfile.baiduYunPanCode!'暂无'}
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-2 col-form-label">极速下载</label>
                        <div class="col-md-10">
                            <label class="col-form-label" style="font-weight: 300;text-align:left;">
                                <#if downloadfile.filePath??>
                                    <a id="downloadfile_jisu_link" href="JavaScript:void(0)" target="_blank">
                                        请扫描二维码关注微信公众号获得授权码
                                    </a>
                                <#else>
                                    <a href="JavaScript:void(0)">
                                        抱歉，该文件暂不提供极速下载。
                                    </a>
                                </#if>
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-2 col-form-label">极速期限</label>
                        <div class="col-md-10">
                            <label class="col-form-label" style="font-weight: 300;" id="downloadfile_jisu_exp">--
                            </label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-2 col-form-label">极速授权码</label>
                        <div class="col-md-3">
                            <#if downloadfile.filePath??>
                                <input type="text" class="form-control"
                                       id="downloadfile_jisu_code">
                            <#else>
                                <div>抱歉，该文件暂不提供极速下载。</div>
                            </#if>


                        </div>
                        <div class="col-md-7">
                            <#if downloadfile.filePath??>
                                <a class="btn btn-primary" href="javascript:void(0);"
                                   id="downloadfile_jisu_btn"
                                   onclick="getJiSuDownloadLink()">获取极速下载连接
                                </a>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                            <div class="alert alert-warning" role="alert">
                                <strong>注意！</strong>
                                获取极速下载链接后请勿刷新页面，页面刷新需要重新获取
                            </div>
                        </div>
                    </div>
                    <div class="form-group row" style="padding: 5px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                            <div class="row wechatcard">
                                <div class="col-xs-4 col-md-3" style="padding: 0;">
                                    <img src="https://cdn.renfei.net/images/WechatQR.png"
                                         width="500px" height="500px" style="width: 100%;height: auto;">
                                </div>
                                <div class="col-xs-8 col-md-9" style="font-size: 14px;">
                                    <div class="wechatcard_title"><strong class="wecharcolor">
                                            微信订阅号：</strong>RenFeiNet
                                    </div>
                                    <#if downloadfile.filePath??>
                                        <div>
                                            使用微信扫描二维码关注公众号，回复【
                                            <span class="wechatcard_red" style="color:red;font-weight: 800;">
                                            ${'极速下载'+downloadfile.id?c}
                                            </span>
                                            】，获得极速下载授权码。
                                        </div>
                                    <#else>
                                        <div>
                                            抱歉，该文件暂不提供极速下载。使用微信扫描二维码关注公众号，获得更多信息推送。
                                        </div>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</#macro>