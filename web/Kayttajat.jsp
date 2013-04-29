<%-- 
    Document   : LisaaKirja
    Created on : Mar 31, 2013, 11:27:12 PM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">Kayttajat
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    </head>
    <body>

        <%@include file="vaatiiKirjautumisen.jsp" %>
        <%@include file="vaatiiYllapitajan.jsp" %>

        <%@ include file="nav.jsp" %>


        <div style="margin-top:50px;margin-bottom:50px;">
            <form action="${pageContext.request.contextPath}/LisaaKayttaja" method="get">
                <input type="hidden" name="lisaaNakyma" value="tosi">
                <button class="nappi">
                    <p>
                        Lisää
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/LisaaKayttaja" method="get">
                <input type="hidden" name="poistoNakyma" value="tosi">
                <button class="nappi" style="margin-left:85px;">
                    <p>
                        Poista
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/SelaaKayttaja" method="get">
                <input type="hidden" name="selaa" value="tosi">
                <button class="nappi" style="margin-left:170px;">
                    <p>
                        Selaa
                    </p>
                </button>
            </form>
        </div>

        <%
            if (session.getAttribute("lisaysNakyma") != null) {
                session.removeAttribute("lisaysNakyma");
        %>
        <div class="kentat1">
            <fieldset>
                <p class="pienella">
                    Lisää uusi käyttäjä tietokantaan antamalla tarvittavat tiedot alla oleviin kenttiin.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/LisaaKayttaja" method="post">
                    <label>Käyttäjätunnus</label><input class="inputtyyli" type="text" name="nimi"><br><br>
                    <label>Salasana</label> <input class="inputtyyli" type="password" name="salasana"><br><br>

                    <p class="pienella">
                        <br>
                            Valitse uuden tunnuksen käyttäjätyyppi
                    </p>

                    <div>
                        <input type="radio" name="tyyppi" value="kirjautunut" checked=""><p class="pienella">Kirjautunut</p>
                        <input type="radio" name="tyyppi" value="yllapitaja"><p class="pienella">Ylläpitäjä</p>
                    </div>

                    <input type="submit" value="Lisää käyttäjä">
                </form>
            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("poistoNakyma") != null) {
                session.removeAttribute("poistoNakyma");
        %>
        <div class="kentat1">
            <fieldset>
                <p class="pienella">
                    Poista käyttäjä tietokannasta antamalla tarvittavat tiedot alla oleviin kenttiin.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/PoistaKayttaja" method="post">
                    <label>Käyttäjätunnus</label><input class="inputtyyli" type="text" name="nimi"><br><br>
                    <input  type="submit" value="Poista käyttäjä">
                </form>
            </fieldset>



        </div>
        <% }%>

        <%
            if (session.getAttribute("kayttajalista") != null) {
        %>
        <div class="kentat1">
            <fieldset>
                <p class="pienella">
                    Lista tietokantaan kirjatuista käyttäjistä.<br><br><br>
                </p>

                <div><p1>Käyttäjätunnus</p1><p1 class="oikea">Käyttäjätyyppi</p1><br><br></div>

                <c:forEach var="k" items="${kayttajalista}">
                    <p1>${k.nimi}</p1> <p1 class="oikea">${k.tyyppi}</p1><br>
                    </c:forEach>

            </fieldset>
        </div>
        <%  }
            session.removeAttribute("kayttajalista");
        %>


        <%@include file="kayttajavirheilmoitus.jsp" %>


        <script src="linkit.js"></script>
    </body>
</html>
