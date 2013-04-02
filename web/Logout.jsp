<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjaudutaan ulos. . .</title>
        <link rel="stylesheet" type"text/css" href="Tyylit.css" />
    </head>
    <body>
        <%
            session.removeAttribute("username");
            session.removeAttribute("password");
            session.invalidate();
        %>
        
        <p>Uloskirjautuminen onnistui.</p>

        <%
            response.sendRedirect("index.jsp");
        %>
    </body>
</html>