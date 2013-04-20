<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p><br>HAKUTULOKSET</p>

<div class="haku" style="margin-top: 20px;">

    <%
        if (session.getAttribute("tiedotNakyvissa") != null) {
    %>

    <!--<div class="haku" style="margin-top:20px;">-->
    <fieldset>

        <div class="vasen">
            Teoksen nimi<br>
            ISBN<br>
            Julkaisuvuosi<br>
            Kirjailija(t)<br>
            Aihe<br>

        </div>
        <div class ="oikea">

            <c:forEach var="k" items="${tiedot}">
                ${k}<br>
            </c:forEach>

        </div>


    </fieldset>
    <!--</div>-->

    <%    }
        session.removeAttribute("tiedotNakyvissa");
    %>

    
    <fieldset>
    
        <br>
        <c:forEach var="k" items="${lista}">
            <form method="get">
                <a href="<c:url value="/TarkemmatTiedot"><c:param name="isbn" value="${k.ISBN}"/></c:url>">${k.nimi}, ${k.julkaisuvuosi}</a>
                </form>
        </c:forEach>
    
    </fieldset>
</div>