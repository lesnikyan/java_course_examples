<%-- 
    Document   : main
    Created on : 11.10.2018, 6:20:42
    Author     : Less
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    <style> 
        div.menu a {
            display: inline-block;
            padding: 1px 2px 1px 2px;
            margin: 0 2px 0 2px;
            border-radius: 2px;
            border: 1px solid #eee;
            color: #a00;
            background-color: #fee;
        }
    </style>
    </head>
    <body>
        <h1>Main page of Spring project with Hibernate models</h1>
        <div class="menu">
            <a href="${urlPrefix}" >Main</a>
            <a href="${urlPrefix}/user/test" >Test add user</a>
        </div>
    </body>
</html>
