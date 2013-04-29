<%-- 
    Document   : LisaaKirja
    Created on : Mar 31, 2013, 11:27:12 PM
    Author     : hkskogbe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">LisaaKirja
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />

    </head>
    <body>

        <%@include  file="vaatiiKirjautumisen.jsp" %>

        <%@ include file="nav.jsp" %>




        <div class="kentat">
            <fieldset>
                <p class="pienella">
                    Lisää kirja tietokantaan antamalla sen tiedot alla oleviin kenttiin.<br><br>
                    
                    Voit liittää kirjaan useamman kirjailijan erottamalla ne puolipisteellä toisistaan (merkki:  ; ). Useamman aiheen erottaminen tapahtuu pilkun (merkki:  , ) avulla.<br><br>

                    Tähdellä merkityt kentät ovat pakollisia.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/Lisays" method="post">
                    <label>Kirjan nimi*</label>      <input class="inputtyyli" type="text" name="nimi"><br><br>
                    <label>ISBN*</label>             <input class="inputtyyli" type="text" name="isbn"><br><br>
                    <label>Kirjailija*</label>       <input class="inputtyyli" type="text" name="kirjailija"><br><br>
                    <label>Aiheet</label>           <input class="inputtyyli" type="text" name="aiheet"><br><br>
                    <label>Julkaisuvuosi*</label>    <input class="inputtyyli" type="text" name="vuosi"><br><br>
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
