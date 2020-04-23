<#macro html>
    <!doctype html>
    <html lang="en">
        <#nested>
    </html>
</#macro>

<#macro head title>
    <head>
        <meta charset="UTF-8">
        <#nested>
    </head>
</#macro>

<#macro body>
    <body>
        <#nested>
    </body>
</#macro>