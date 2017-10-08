<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error.jsp"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <link rel="stylesheet" href="/WebJSP/media/css/main.css" />
        <script src="/WebJSP/media/js/jquery-3.2.1.js"></script>
        <script src="/WebJSP/media/js/main.js"></script>
    </head>
    <body>
        
        <%-- Example of JSP --%>
        <!-- Тут был Вася... -->
        
        <%@include file="menu.jspf" %>
        
        <h1>Main page.</h1>
        
        <jsp:useBean class="lesson.jsp.DemoBean" id="demo" />
        <div>[ <jsp:getProperty name="demo" property="value" /> ]</div>
        
        <%-- Exception example --%>
        <%-- int exVal = Integer.parseInt("qwerty"); --%>
    </body>
</html>
