<%-- 
    Document   : hakusivu
    Created on : Apr 14, 2013, 3:10:09 PM
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


    </head>
    <body>

        <%@ include file="nav.jsp" %>

        <h1>
            Kirjastotietokanta
        </h1>

        <%@ include file="haku.jsp" %>

        <%@ include file="hakutulokset.jsp" %>

        <script src="linkit.js"></script>
    </body>
</html>
