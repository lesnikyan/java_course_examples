<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error.jsp"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <%-- Example of JSP --%>
        <!-- Тут был Вася... -->
        <%@include file="menu.jspf" %>
        <h1>Hello World!</h1>
        <%-- Exception example --%>
        <%-- int exVal = Integer.parseInt("qwerty"); --%>
    </body>
</html>
