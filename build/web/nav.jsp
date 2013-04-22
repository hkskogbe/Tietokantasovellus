<nav class="nav">
    <a href="index.jsp">Haku</a>
    <a href="Ohjeet.jsp">Ohjeet</a>

    <%
        if (session.getAttribute("overlord") != null) {
    %>
    <a href="LisaaKirja.jsp">Lisää kirja</a>
    <a href="PoistaKirja.jsp">Poista kirja</a>
    <a href="LisaaKayttaja.jsp">Lisaa kayttaja</a>
    <a href="Logout.jsp" class="oikea">Kirjaudu ulos</a> 
    <%    } else if (session.getAttribute("kirjautunut") != null) {%>  
    <a href="LisaaKirja.jsp">Lisää kirja</a>
    <a href="PoistaKirja.jsp">Poista kirja</a>
    <a href="Logout.jsp" class="oikea">Kirjaudu ulos</a>
    <%    } else {%>
    <a href="Kirjautuminen.jsp" class="oikea">Kirjaudu sisään</a>
    <%}%>
</nav>