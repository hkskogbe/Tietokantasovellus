<%-- 
    Document   : hakuvirheilmoitus
    Created on : Apr 13, 2013, 2:14:59 PM
    Author     : hkskogbe
--%>
<% if (session.getAttribute("virheellinenVuosi") != null) {
        session.removeAttribute("virheellinenVuosi");
%>

<br><p class="pienella">Annoit virheellisen sy�tteen julkaisuvuosikenttiin.
    Julkaisuvuoden tulee olla muotoa YYYY tai j�tetty tyhj�ksi.<br></p>

<%
    }
%>

<% if (session.getAttribute("lvuosiEnnenAvuotta") != null) {
        session.removeAttribute("lvuosiEnnenAvuotta");
%>

<br><p class="pienella">Tarkista, ett� julkaisuvuosi-kent�n ensimm�inen arvo on pienempi kuin j�lkimm�inen.<br></p>

<%
    }
%>