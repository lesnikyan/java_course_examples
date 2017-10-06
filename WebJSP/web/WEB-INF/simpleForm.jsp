<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form example</title>
        <style>
            form input {
                width: 300px;
                margin: 2px;
                padding: 0;
            }
        </style>
    </head>
    <body>
        <h1>${helloMessage}</h1>
        <form method="POST" action="/WebJSP/form1/">
            <div>
                <input type="text" name="sometext" />
            </div>
            <div>
                <input type="submit" value="Send" />
            </div>
        </form>
    </body>
</html>
