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

    </head>
    <body>

         <%@ include file="nav.jsp" %>

        <h1>
            Kirjastotietokantasysteemi.
        </h1>

        <form method="get" action="${pageContext.request.contextPath}/ListaaKirjat">
            <select name="valinta">
                <option value="kirjailijat">kirjailijat</option>
                <option value="kirjat">kirjat</option>
            </select>

            <input type="submit" value="Listaa!" name="haku" />
        </form>

        <p><br>HAKUTULOKSET</p>

        <c:forEach var="k" items="${lista}">
            ${k.kirjailijanNimi}<br>
        </c:forEach>


        <script src="linkit.js"></script>
    </body>
</html>
