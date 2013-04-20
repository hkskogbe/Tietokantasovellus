<%-- 
    Document   : Ohjeet
    Created on : Apr 18, 2013, 9:50:04 AM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ohjeet kirjastotietokannan käyttöön</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />
    </head>
    <body>
        <%@ include file="nav.jsp" %>

        <div class="ohjekentta">
            <fieldset>
                <%@ include file="hakuohje.jsp" %>
            </fieldset>
        </div>

        <%
            if (session.getAttribute("kirjautunut") != null) {
        %>

        <div class="ohjekentta">
            <fieldset>
                <%@ include file="lisaysohje.jsp" %>
            </fieldset>
        </div>

        <div class="ohjekentta">
            <fieldset>
                <%@ include file="poistoohje.jsp" %>
            </fieldset>
        </div>


        <% }%>


        <script src="linkit.js"></script>
    </body>
</html>
