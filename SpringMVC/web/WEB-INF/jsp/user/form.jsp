
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>form</h1>
        <sf:form method="POST" action="/SpringMVC/user/create" modelAttribute="user-form">
            <div><sf:input path="name" /> <sf:label path="name">Login</sf:label> </div>
            <div><sf:input path="pass" /> <sf:label path="pass">Password</sf:label></div>
            <div><sf:input path="email" /> <sf:label path="email">Email</sf:label></div>
            <div><sf:input path="alias" /> <sf:label path="alias">Public name or Psevdonym</sf:label></div>
            <div><input type="submit" value="Create" /></div>
        </sf:form>
    </body>
</html>
