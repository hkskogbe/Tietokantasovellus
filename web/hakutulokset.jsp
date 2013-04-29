<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p><br>HAKUTULOKSET</p>

<div class="haku" style="margin-top: 20px;">

    <%
        if (session.getAttribute("tiedotNakyvissa") != null) {
    %>

    <fieldset>

        <table>
            <tr>
                <th style="width:50%"></th>
                <th></th>
            </tr>
            
            <tr>
                <td>Teoksen nimi</td>
                <td><c:out value="${teosNimi}"/></td>
            </tr>
            <tr>
                <td>ISBN</td>
                <td> <c:out value="${teosISBN}"/></td>
            </tr>
            <tr>
                <td>Julkaisuvuosi</td>
                <td><c:out value="${teosJulkaisuvuosi}"/></td>
            </tr>
            <tr>
                <td>Kirjailija(t)</td>
                <td><c:out value="${teosKirjailijat}"/></td>
            </tr>
            <tr>
                <td>Aihe(et)</td>
                <td><c:out value="${teosAiheet}"/></td>
            </tr>
            <tr>
                <td>Saatavilla</td>
                <td><c:out value="${teosSaatavilla}"/></td>
            </tr>
            
        </table>
        
<!--     

        <div class="vasen">

            Teoksen nimi    <br>
            ISBN            <br>
            Julkaisuvuosi   <br>
            Kirjailija(t)   <br>
            Aihe(et)        <br>
            Saatavilla      <br>

        </div>

        
        <div class="oikea">
        
            <c:out value="${teosNimi}"/>            <br>
            <c:out value="${teosISBN}"/>            <br>
            <c:out value="${teosJulkaisuvuosi}"/>   <br>
            <c:out value="${teosKirjailijat}"/>     <br>
            <c:out value="${teosAiheet}"/>          <br>
            <c:out value="${teosSaatavilla}"/>      <br>

        </div>
            
            -->

    </fieldset>

    <%    }
        session.removeAttribute("tiedotNakyvissa");
    %>

    <%
        if (session.getAttribute("hakutulokset") != null) {
            session.removeAttribute("hakutulokset");
    %>
    <fieldset>

        <br>
        <c:forEach var="k" items="${lista}">
            <form method="get">
                <a href="<c:url value="/TarkemmatTiedot"><c:param name="isbn" value="${k.ISBN}"/></c:url>">${k.nimi}, ${k.julkaisuvuosi}</a>
                </form>
        </c:forEach>

    </fieldset>

    <%        }

    %>
</div>