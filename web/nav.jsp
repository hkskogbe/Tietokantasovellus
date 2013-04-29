<nav class="nav">
    <a href="index.jsp">Haku</a>
    <a href="Ohjeet.jsp">Ohjeet</a>

   
    <% if (session.getAttribute("kirjautunut") != null) {%>  
    <a href="LisaaKirja.jsp">Lisää kirja</a>
    <a href="PoistaKirja.jsp">Poistomerkinnät</a>
    <a href="PaivitaTiedot.jsp">Tietojen muuttaminen</a>
    <a href="PoistaLopullisesti.jsp">Poista kirja</a>
    <a href="Lainaukset.jsp">Lainaukset</a>
    <%        if (session.getAttribute("yllapitaja") != null) {%>
    <a href="Kayttajat.jsp">Käyttäjät</a>
    <% }%>
    <a href="Logout.jsp" class="oikea">Kirjaudu ulos</a>
    <%    } else {%>
    <a href="Kirjautuminen.jsp" class="oikea">Kirjaudu sisään</a>
    <%}%>
</nav>