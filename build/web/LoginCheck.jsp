<!--
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjaudutaan. . .</title>
        <link rel="stylesheet" type"text/css" href="Tyylit.css" />
    </head>
    <body>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if ((username.equals("h") && password.equals("e"))) {
                session.setAttribute("kirjautunut", username);
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("onnistuiko", "ei");
                response.sendRedirect("Kirjautuminen.jsp");
            }
        %>--%>
    </body>
</html>-->