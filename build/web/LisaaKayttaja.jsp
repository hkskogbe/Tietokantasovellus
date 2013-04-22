<%-- 
    Document   : LisaaKirja
    Created on : Mar 31, 2013, 11:27:12 PM
    Author     : hkskogbe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">LisaaKayttaja
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />

    </head>
    <body>

        <%
            request.removeAttribute("lista");
            if (session.getAttribute("kirjautunut") == null) {
                response.sendRedirect("Kirjautuminen.jsp");
            }
        %>

        <%@ include file="nav.jsp" %>




        <div class="kentat">
            <fieldset>
                <p class="pienella">
                    Lisää kirja tietokantaan antamalla sen tiedot alla oleviin kenttiin.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/LisaaKayttaja" method="post">
                    <label>Käyttäjätunnus</label><input type="text" name="nimi"><br><br>
                    <label>Salasana</label> <input type="password" name="isbn"><br><br>
                    <input type="submit" value="Lisää käyttäjä">
                </form>
            </fieldset>
        </div>


        <%
            if (session.getAttribute("lisattiinkayttaja") != null) {
                session.removeAttribute("lisattiinkayttaja");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Käyttäjä lisättiin onnistuneesti tietokantaan.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>


        <%
            if (session.getAttribute("virhelisattaessakayttajaa") != null) {
                session.removeAttribute("virhelisattaessakayttajaa");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe. Tarkista, että kaikki kentät on täytetty.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>


        <script src="linkit.js"></script>
    </body>
</html>
