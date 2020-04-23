<#import "index.ftl" as index>
<@index.html>
    <@index.head "Sign up"></@index.head>
    <@index.body>
        <div>
            <form action="/signUp" method="post" accept-charset="UTF-8">
                <input type="text" name="username" placeholder="username">
                <input type="password" name="password" placeholder="password">
                <input type="submit" value="sign up">
            </form>
        </div>
    </@index.body>
</@index.html>