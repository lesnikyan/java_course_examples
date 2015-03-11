
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error page</title>
    </head>
    <body>
        <h1>Error!</h1>
            <pre>
                <%-- exception.printStackTrace(response.getWriter()); --%>
            </pre>
        <div>
            <div><span style="font-weight: bold;"><%= exception.toString() %></span></div>
            <div>StackTrace:</div>
            <% for(StackTraceElement traceRow: exception.getStackTrace()){ %>
            <div>
                <%= traceRow.toString() %>
            </div>
            <%}%>
        </div>
    </body>
</html>
