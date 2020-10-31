<#include "./layout/layout.ftl"/>
<#include "./layout/kitboxmenu.ftl"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN" dir="ltr" prefix="og: http://ogp.me/ns#">
<@head headVO,title>
    <link rel="stylesheet" href="/highlight/styles/idea.css">
</@head>
<body>
<@header headerVO>

</@header>
<div class="maven-banner">
    <div class="container">
        <div class="row">
            <div class="col-md-4 maven-banner-left"></div>
            <div class="col-md-8 text-center align-self-center">
                <h1>Maven公共代理仓库</h1>
                <h3 style="color: #6c757d!important;">Maven Agent Repository</h3>
                <p>Maven公共代理仓库，助力开发者快速构建自己的项目。</p>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <small class="text-muted">注：Maven图形商标版权归 <a href="https://www.apache.org/" rel="nofollow noopener"
                                                target="_blank">Apache Software Foundation</a> 所有。</small>
</div>
<div class="container" style="padding-top: 50px;">
    <h3>Maven公共代理仓库</h3>
    <div class="row">
        <div class="col-12">
            <p><a href="http://maven.renfei.net" target="_blank">maven.renfei.net</a>代理了很多公共的maven仓库，助力开发者快速构建自己的项目。有效解决国内仓库速度慢、仓库包丢失的问题。
            </p>
            <p>
                在我使用Maven构建项目的日常开发中会经常遇到某个包找到不到，或者下载速度非常缓慢的情况。为了解决这个问题我搭建了自己的Maven私服，聚合常见的仓库，顺别将这个服务分享给大家一起使用。同时使用了CDN进行分发，速度会非常快。</p>
            <p>最终的聚合仓库地址是：<a href="http://maven.renfei.net/repository/maven-public/" target="_blank">http://maven.renfei.net/repository/maven-public/</a>
            </p>
        </div>
    </div>
    <h3>Maven配置</h3>
    <div class="row">
        <div class="col-12">
            <p>
                打开maven的配置文件(windows机器一般在maven安装目录的conf/settings.xml)，在 &lt;mirrors&gt;&lt;/mirrors&gt;
                标签中添加mirror子节点:
            </p>
            <pre class="card">
                    <code class="xml">&lt;mirror&gt;
    &lt;id&gt;renfeimaven&lt;/id&gt;
    &lt;mirrorOf&gt;*&lt;/mirrorOf&gt;
    &lt;name&gt;RenFei公共仓库&lt;/name&gt;
    &lt;url&gt;http://maven.renfei.net/repository/maven-public/&lt;/url&gt;
&lt;/mirror&gt;</code>
                    </pre>
        </div>
    </div>
    <h3>gradle配置</h3>
    <div class="row">
        <div class="col-12">
            <p>
                打开maven的配置文件(windows机器一般在maven安装目录的conf/settings.xml)，在 &lt;mirrors&gt;&lt;/mirrors&gt;
                标签中添加mirror子节点:
            </p>
            <pre class="card">
                    <code class="xml">allprojects {
    repositories {
        maven { url 'http://maven.renfei.net/repository/maven-public/' }
        mavenLocal()
        mavenCentral()
    }
}</code>
                    </pre>
        </div>
    </div>
    <h3>代理仓库列表</h3>
    <div class="row">
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <td>仓库名称</td>
                <td>代理源地址</td>
                </thead>
                <tr>
                    <td>public</td>
                    <td>所有仓库的聚合：<br><a href="http://maven.renfei.net/repository/maven-public/" target="_blank">http://maven.renfei.net/repository/maven-public/</a>
                    </td>
                </tr>
                <tr>
                    <td>central</td>
                    <td>https://repo1.maven.org/maven2/</td>
                </tr>
                <tr>
                    <td>jcenter</td>
                    <td>http://jcenter.bintray.com/</td>
                </tr>
                <tr>
                    <td>google</td>
                    <td>https://maven.google.com/</td>
                </tr>
                <tr>
                    <td>gradle-plugin</td>
                    <td>https://plugins.gradle.org/m2/</td>
                </tr>
                <tr>
                    <td>spring</td>
                    <td>http://repo.spring.io/libs-milestone/</td>
                </tr>
                <tr>
                    <td>spring-plugin</td>
                    <td>http://repo.spring.io/plugins-release/</td>
                </tr>
                <tr>
                    <td>grails-core</td>
                    <td>https://repo.grails.org/grails/core</td>
                </tr>
                <tr>
                    <td>apache snapshots</td>
                    <td>https://repository.apache.org/snapshots/</td>
                </tr>
                <tr>
                    <td>aliyun</td>
                    <td>https://maven.aliyun.com/repository/public</td>
                </tr>
                <tr>
                    <td>163</td>
                    <td>http://mirrors.163.com/maven/repository/maven-public/</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<@footer footerVO performance_execTimeTotal performance_execCountTotal>
    <script src="/highlight/highlight.pack.js"></script>
    <script>
        $(function () {
            $("code").each(function () {
                $(this).html("<ol><li>" + $(this).html().replace(/\n/g, "\n</li><li>") + "\n</li></ol>");
            });
        });
        hljs.initHighlightingOnLoad();
    </script>
</@footer>
</body>
</html>