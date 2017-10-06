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
        
        <h1>Main page.</h1>
        
        <jsp:useBean class="lesson.jsp.DemoBean" id="demo" />
        <div>[ <jsp:getProperty name="demo" property="value" /> ]</div>
        
        <%-- Exception example --%>
        <%-- int exVal = Integer.parseInt("qwerty"); --%>
    </body>
</html>
