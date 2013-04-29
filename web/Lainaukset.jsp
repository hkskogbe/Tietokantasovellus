<%-- 
    Document   : Lainaukset
    Created on : Apr 23, 2013, 10:08:33 AM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">Lainaukset
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    </head>
    <body>

        <%@include  file="vaatiiKirjautumisen.jsp" %>

        <%@ include file="nav.jsp" %>




        <div style="margin-top:50px;margin-bottom:50px;">
            <form action="${pageContext.request.contextPath}/LainausValinnat" method="get">
                <input type="hidden" name="lainaa" value="tosi">
                <button class="nappi">
                    <p>
                        Lainaa
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/LainausValinnat" method="post">
                <input type="hidden" name="selaa" value="tosi">
                <button class="nappi" style="margin-left:85px;">
                    <p>
                        Selaa
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/LainausValinnat" method="post">
                <input type="hidden" name="palauta" value="tosi">
                <button class="nappi" style="margin-left:170px;">
                    <p>
                        Palauta
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/LainausValinnat" method="post">
                <input type="hidden" name="lisaa" value="tosi">
                <button class="nappi" style="margin-left:255px;">
                    <p>
                        Lisää
                    </p>
                </button>
            </form>

            <form action="${pageContext.request.contextPath}/LainausValinnat" method="post">
                <input type="hidden" name="poista" value="tosi">
                <button class="nappi" style="margin-left:340px;">
                    <p>
                        Poista
                    </p>
                </button>
            </form>
        </div>

        <%
            if (session.getAttribute("selaaLainauksia") != null) {
                session.removeAttribute("selaaLainauksia");
        %>

        <div class="kentat1">
            <fieldset>
                <p class="pienella">
                    Alla olevasta painikkeesta saat listattua kaikki kokoelmassa olevat lainattavat teokset.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/SelaaLainauksia" method="post">
<!--                    <label>Haku</label><input type="text" name="syote"><br><br>

                    <div style="clear:both;">
                        <input type="radio" name="toiminto" value="teos" checked=""><p class="pienella">Teoksen nimellä/ISBN:llä</p>
                        <input type="radio" name="toiminto" value="kayttaja"><p class="pienella">Käyttäjällä</p>
                    </div>-->

                    <input type="submit" value="Lainauksiin">
                </form>
            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("lainaaLainauksia") != null) {
                session.removeAttribute("lainaaLainauksia");
        %>

        <div class="kentat1">
            <fieldset>
                <p class="pienella">
                    Anna lainattavan teoksen ISBN sekä muut tarpeelliset tiedot.<br><br>

                    Varmista, että lainattava teos löytyy tietokannasta selaa-toiminnon avulla.<br><br>
                </p>
                <form name="" action="${pageContext.request.contextPath}/Lainaukset" method="post">

                    <input type="hidden" name="lainaa" value="true">
                    <label>ISBN</label>                 <input class="inputtyyli" type="text" name="isbn"><br><br>
                    <label>Lainaaja</label>             <input class="inputtyyli" type="text" name="lainaaja"><br><br>
                    <label>Mihin asti lainassa</label>  <input class="inputtyyli" type="text" name="mihinasti"><br><br>
                    <input type="submit" value="Lainaa">

                </form>
            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("palautaLainauksia") != null) {
                session.removeAttribute("palautaLainauksia");
        %>

        <div class="kentat1">
            <fieldset>
                <p class="pienella">

                    Palauta lainattu teos alla olevien kenttien avulla.<br><br>

                </p>

                <form name="" action="${pageContext.request.contextPath}/Lainaukset" method="post">

                    <input type="hidden" name="palauta" value="true">
                    <label>ISBN</label>                 <input class="inputtyyli" type="text" name="isbn"><br><br>
                    <label>Lainaaja</label>             <input class="inputtyyli" type="text" name="lainaaja"><br><br>
                    <input type="submit" value="Palauta">

                </form>

            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("lisaaLainauksia") != null) {
                session.removeAttribute("lisaaLainauksia");
        %>

        <div class="kentat1">
            <fieldset>
                <p class="pienella">

                    Lisää tietokantaan lainattava kopio teoksesta antamalla teoksen ISBN alla olevaan kenttään.<br><br>

                </p>

                <form name="" action="${pageContext.request.contextPath}/Lainaukset" method="post">

                    <input type="hidden" name="lisaa" value="true">
                    <label>ISBN</label>                 <input class="inputtyyli" type="text" name="isbn"><br><br>
                    <input type="submit" value="Lisää">

                </form>

            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("poistaLainauksia") != null) {
                session.removeAttribute("poistaLainauksia");
        %>

        <div class="kentat1">
            <fieldset>
                <p class="pienella">

                    Poista tietokannasta kopio teoksesta antamalla teoksen ISBN ja lainaajan nimi alla oleviin kenttiin.<br><br>

                </p>

                <form name="" action="${pageContext.request.contextPath}/Lainaukset" method="post">

                    <input type="hidden" name="poista" value="true">
                    <label>ISBN</label>                 <input class="inputtyyli" type="text" name="isbn"><br><br>
                    <input type="submit" value="Poista">

                </form>

            </fieldset>
        </div>
        <% }%>

        <%
            if (session.getAttribute("lainalista") != null) {
        %>


        <div class="kentat1">

            <table class="lainaustaulu">

                <tr>
                    <th>Teoksen nimi</th>
                    <th>ISBN</th>
                    <th>Lainassa</th>
                    <th>Lainaaja</th>
                    <th>Lainauspäivämäärä</th>
                    <th>Lainassa mihin asti</th>
                </tr>

                <c:forEach var="k" items="${lainalista}">
                    <tr>
                        <td>${k.kirja.nimi}</td>
                        <td>${k.kirja.ISBN}</td>
                        <td>${k.lainassa}</td>
                        <td>${k.lainaaja}</td>
                        <td>${k.lainauspvm}</td>
                        <td>${k.lainassaMihinAsti}</td>
                    </tr>

                </c:forEach>

            </table>

        </div>


        <%            }
            session.removeAttribute("lainalista");
        %>

        <% if (session.getAttribute("syotevirhe") != null) {
                session.removeAttribute("syotevirhe");
        %>

        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe. Tarkista, että kaikki kentät on täytetty ja että syötteet ovat oikein.<br>
                </p>       
            </fieldset>
        </div>

        <% }%>

        <% if (session.getAttribute("eivarastossa") != null) {
                session.removeAttribute("eivarastossa");
        %>

        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Etsimääsi teosta ei löytynyt tai kaikki kopiot ovat lainassa. Tarkista tilanne Selaa-toiminnon avulla.
                </p>       
            </fieldset>
        </div>

        <% }%>

        <% if (session.getAttribute("lainatapahtumaonnistui") != null) {
                session.removeAttribute("lainatapahtumaonnistui");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Tapahtuma käsiteltiin onnistuneesti.
                </p>       
            </fieldset>
        </div>
        <% }%>

        <script src="linkit.js"></script>

    </body>
</html>
