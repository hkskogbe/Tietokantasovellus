<%-- 
    Document   : index
    Created on : Mar 31, 2013, 10:22:23 PM
    Author     : hkskogbe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type"text/css" href="Tyylit.css" />

              <%
                  session.removeAttribute("lista");
                  response.sendRedirect("Etusivu");
              %>

    </head>
    <body>

        <%@ include file="nav.jsp" %>

        <h1>
            Kirjastotietokanta
        </h1>


        <script src="linkit.js"></script>
    </body>
</html>
