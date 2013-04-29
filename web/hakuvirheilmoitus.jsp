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

<br><p class="pienella">Tarkista, että julkaisuvuosi-kentän ensimmäinen arvo on 
    pienempi kuin jälkimmäinen.<br></p>

<%
    }
%>

<% if (session.getAttribute("virheSyotteessa") != null) {
        session.removeAttribute("virheSyotteessa");
%>

<p class="pienella">Syötteen käsittelyssä tietokannassa tapahtui virhe. 
    Varmista, että hakukenttä ei sisällä erikoisia symboleita kuten ' -merkkiä.<br></p>

<%
    }
%>

<% if (session.getAttribute("virheTarkentaessa") != null) {
        session.removeAttribute("virheTarkentaessa");
%>

<p class="pienella">Haettu kohde ei ollut saatavilla. Mikäli kyseisen
    teoksen tiedot ovat varmasti tietokannassa ja tämä virhe toistuu, ota yhteys ylläpitäjään.<br></p>

<%
    }
%>