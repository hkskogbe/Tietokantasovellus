<nav class="nav">
    <a href="index.jsp">Haku</a>
    <a href="Ohjeet.jsp">Ohjeet</a>

    <%
        if (session.getAttribute("yllapitaja") != null) {
    %>
    <a href="LisaaKirja.jsp">Lis‰‰ kirja</a>
    <a href="PoistaKirja.jsp">Poista kirja</a>
    <a href="Lainaukset.jsp">Lainaukset</a>
    <a href="LisaaKayttaja.jsp">Lis‰‰ k‰ytt‰j‰</a>
    <a href="Logout.jsp" class="oikea">Kirjaudu ulos</a> 
    <%    } else if (session.getAttribute("kirjautunut") != null) {%>  
    <a href="LisaaKirja.jsp">Lis‰‰ kirja</a>
    <a href="PoistaKirja.jsp">Poista kirja</a>
    <a href="Lainaukset.jsp">K‰sittele lainauksia</a>
    <a href="Logout.jsp" class="oikea">Kirjaudu ulos</a>
    <%    } else {%>
    <a href="Kirjautuminen.jsp" class="oikea">Kirjaudu sis‰‰n</a>
    <%}%>
</nav>