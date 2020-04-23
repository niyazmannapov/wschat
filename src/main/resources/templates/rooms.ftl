<#-- @ftlvariable name="rooms" type="java.util.List<ru.itis.chat.websockets.dto.ChatRoomDto>" -->
<#import "index.ftl" as index>
<@index.html>
    <@index.head "Chat"></@index.head>
    <@index.body>
        <form action="/chat" method="post" accept-charset="UTF-8">
            <input type="text" name="name" placeholder="room name">
            <input type="submit">
        </form>
        <hr>
        <#list rooms as room>
            <a href="/chat/${room.id}">&gt; ${room.name}</a>
            <br>
        </#list>
    </@index.body>
</@index.html>