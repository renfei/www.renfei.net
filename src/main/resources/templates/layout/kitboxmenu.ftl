<#macro KitBoxMenu KitBoxMenus>
    <div class="accordion" id="KitBoxMenus">
        <#if KitBoxMenus?? && (KitBoxMenus?size>0)>
            <#list KitBoxMenus as menu>
                <div class="card">
                    <div class="card-header p-0" id="${menu.elementId+'s'}">
                        <h2 class="mb-0">
                            <button class="btn btn-light btn-block" type="button" data-toggle="collapse"
                                    data-target="#${menu.elementId}" aria-expanded="true"
                                    aria-controls="${menu.elementId}">
                                ${menu.title!}
                            </button>
                        </h2>
                    </div>

                    <div id="${menu.elementId}" class="collapse${menu.isOpen?string(" show","")}"
                         aria-labelledby="${menu.elementId+'s'}"
                         data-parent="#KitBoxMenus">
                        <div class="card-body">
                            <#if menu.links??>
                                <#list menu.links as link>
                                    <a class="btn btn-light btn-block" href="${link.href}"
                                       role="button">${link.text}</a>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
        <ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-8859756463807757" data-ad-slot="6567996671" data-ad-format="auto" data-full-width-responsive="true"></ins>
        <script>
            (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
    </div>
</#macro>