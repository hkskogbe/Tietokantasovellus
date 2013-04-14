<%-- 
    Document   : hakuvirheilmoitus
    Created on : Apr 13, 2013, 2:14:59 PM
    Author     : hkskogbe
--%>
<% if (session.getAttribute("virheellinenVuosi") != null) {
        session.removeAttribute("virheellinenVuosi");
%>

<br><p class="pienella">Annoit virheellisen syötteen julkaisuvuosikenttiin.
    Julkaisuvuoden tulee olla muotoa YYYY tai jätetty tyhjäksi.<br></p>

<%
    }
%>

<% if (session.getAttribute("lvuosiEnnenAvuotta") != null) {
        session.removeAttribute("lvuosiEnnenAvuotta");
%>

<br><p class="pienella">Tarkista, että julkaisuvuosi-kentän ensimmäinen arvo on pienempi kuin jälkimmäinen.<br></p>

<%
    }
%>