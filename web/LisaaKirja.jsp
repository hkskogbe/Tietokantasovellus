<%-- 
    Document   : LisaaKirja
    Created on : Mar 31, 2013, 11:27:12 PM
    Author     : hkskogbe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type"text/css" href="Tyylit.css" />

    </head>
    <body>

        <%
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

                <form name="" action="${pageContext.request.contextPath}/Lisays" method="post">
                    <label>Kirjan nimi</label><input type="text" name="nimi"><br><br>
                    <label>ISBN</label> <input type="text" name="isbn"><br><br>
                    <label>Kirjailija</label><input type="text" name="kirjailija"><br><br>
                    <label>Julkaisuvuosi</label> <input type="text" name="vuosi"><br><br>
                    <input type="submit" value="Lisää kirja">
                </form>
            </fieldset>
        </div>


        <%
            if (session.getAttribute("lisattiinkirja") != null) {
                session.removeAttribute("lisattiinkirja");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Kirja lisättiin onnistuneesti tietokantaan.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
        
        
        <%
            if (session.getAttribute("virhelisattaessa") != null) {
                session.removeAttribute("virhelisattaessa");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe. Tarkista, että julkaisuvuosi sisältää vain numeroita ja että kaikki kentät on täytetty.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
        
        
        <script src="linkit.js"></script>
    </body>
</html>
