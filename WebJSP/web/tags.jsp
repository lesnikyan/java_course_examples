<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>JSP: JSTL tags</h1>
        <div>
            manual: 
            <a href="http://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm">
                http://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm
            </a>
        </div>
        
        <c:set var="x" value="${5}" />
        <div>x = 5;</div>
        <div>
            test:
        <c:if test="${x == 5}">
            [x equals of 5]
        </c:if>
        </div>
        
        
        <c:set var="x" value="${x*2}" />
        <div>next: x = x * 2; </div>
        <div>
            test: [
        <c:choose>
            <c:when test="${x < 5}">
                x < 5
            </c:when>
            <c:when test="${x == 5}">
                x == 5
            </c:when>
            <c:when test="${x > 5}">
                x > 5
            </c:when>
        </c:choose>
            ]
        </div>
    </body>
</html>
