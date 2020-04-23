<#-- @ftlvariable name="room" type="ru.itis.chat.websockets.dto.ChatRoomDto" -->
<#import "index.ftl" as index>
<@index.html>
    <@index.head "Chat">
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.js"></script>
        <script src="/static/js/stomp.js"></script>
    </@index.head>
    <@index.body>

        <hr>
        <form>
            <input id="text" type="text" placeholder="message">
            <input type="button" value="send message" onclick="sendMessage()">
        </form>
        <hr>
        <h1>${room.name}</h1>
        <hr>
        <ul id="messages"></ul>
    </@index.body>
    <script>
        function escape(html) {
            return html
                .replace(/&/g, "&amp;")
                .replace(/</g, "&lt;")
                .replace(/>/g, "&gt;")
                .replace(/"/g, "&quot;")
                .replace(/'/g, "&#039;");
        }

        const messages = document.getElementById("messages");

        function addMessage(message) {
            const msgStr = "<code>" + message.sender + ":</code> " + escape(message.text)+"<br>";
            messages.innerHTML = msgStr + messages.innerHTML;
        }

        <#list room.messages as message>
        addMessage({
            "sender": "${message.sender}",
            "text": "${message.text}",
        });
        </#list>

        const socket = new SockJS("/stomp/chat");
        const stomp = Stomp.over(socket);

        stomp.connect({}, () => {
            stomp.subscribe("/topic/chat/${room.id}", (data) => {
                addMessage(JSON.parse(data.body));
            });
        });

        const textInput = document.getElementById("text");

        function sendMessage() {
            const body = {"text": textInput.value};
            textInput.value = "";

            stomp.send("/app/chat/${room.id}", {}, JSON.stringify(body));
        }
    </script>
</@index.html>