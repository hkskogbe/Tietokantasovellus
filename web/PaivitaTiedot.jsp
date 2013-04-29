<%-- 
    Document   : PaivitaTiedot
    Created on : Apr 26, 2013, 3:42:05 PM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />

    </head>
    <body>

        <%@include  file="vaatiiKirjautumisen.jsp" %>

        <%@ include file="nav.jsp" %>




        <div class="kentat">
            <fieldset>
                <p class="pienella">
                    Päivitä tietokannassa olevan teoksen tietoja antamalla ne alla oleviin kenttiin.<br><br>

                    Teoksen identifiointi tapahtuu ISBN:n avulla.<br><br>

                    Tähdellä merkityt kentät ovat pakollisia.<br><br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/PaivitaTiedot" method="post">
                    <label>ISBN*</label>             <input type="text" name="isbn"><br><br><br>

                    <label>Kirjan nimi*</label>      <input class="inputtyyli" type="text" name="nimi"><br><br>
                    <label>Kirjailija*</label>       <input class="inputtyyli" type="text" name="kirjailija"><br><br>
                    <label>Aiheet</label>           <input class="inputtyyli" type="text" name="aiheet"><br><br>
                    <label>Julkaisuvuosi*</label>    <input class="inputtyyli" type="text" name="vuosi"><br><br>
                    <input type="submit" value="Päivitä">
                </form>
            </fieldset>
        </div>




        <%
            if (session.getAttribute("muutosEpaonnistui") != null) {
                session.removeAttribute("muutosEpaonnistui");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe. Tarkista, että julkaisuvuosi sisältää vain numeroita ja että kaikki tarpeelliset kentät on täytetty.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
        
        <%
            if (session.getAttribute("muutosOnnistui") != null) {
                session.removeAttribute("muutosOnnistui");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Kirjaan liittyvät tiedot päivitettiin onnistuneesti.
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
    </body>
</html>
