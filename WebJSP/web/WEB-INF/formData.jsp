<%-- 
    Document   : formData
    Created on : 08.09.2014, 5:19:17
    Author     : Less
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String formData1 = (String) request.getAttribute("someText"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View form data</title>
    </head>
    <body>
        <h1>Form data:</h1>
        <div style="color: darkblue; font-size: 20px;"><%= formData1 %></div><br><br>
        <div><a href="/WebJSP/form1">Go back</a></div>
    </body>
</html>
