<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            if ((username.equals("h") && password.equals("h"))) {
                session.setAttribute("kirjautunut", username);
                response.sendRedirect("LisaaKirja.jsp");
            } else {
                response.sendRedirect("Kirjautuminen.jsp");
            }
        %>
    </body>
</html>


Read more: http://mrbool.com/how-to-create-a-login-form-with-jsp/25685#ixzz2PJfPAygJ