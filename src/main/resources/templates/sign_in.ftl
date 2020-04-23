<#import "index.ftl" as index>
<@index.html>
    <@index.head "Sign in"></@index.head>
    <@index.body>
        <div>
            <form action="/signIn" method="post" accept-charset="UTF-8">
                <input type="text" name="username" placeholder="username">
                <input type="password" name="password" placeholder="password">
                <input type="submit" value="sign in">
            </form>
        </div>
    </@index.body>
</@index.html>