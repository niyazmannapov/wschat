<#import "index.ftl" as index>
<@index.html>
    <@index.head "Home"></@index.head>
    <@index.body>
        <#if authenticated>
            <a href="/chat">чаты</a>
            <a href="/signOut">выйти</a>
        <#else>
            <a href="/signIn">вход</a>
            <a href="/signUp">регистрация</a>
        </#if>
    </@index.body>
</@index.html>