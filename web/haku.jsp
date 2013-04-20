<%
    if (session.getAttribute("tarkennettuHaku") != null) {
        session.removeAttribute("tarkennettuHaku");
%>

<div class="haku">
    <fieldset>
        <%@ include file="hakuvirheilmoitus.jsp" %>

        <form action="${pageContext.request.contextPath}/ListaaKirjat" method="get">

            <div class="vasen">
                <br/>Teoksen nimi tai nimen osa<br>
                <br/>Kirjailija<br>
                <br/>Aihe<br>
                <br/>Julkaisuvuosi välillä<br>
                <br>
            </div>

            <div class="oikea">
                <br><input type="text" name="kirja"><br><br>
                <input type="text" name="kirjailija"><br><br>
                <input type="text" name="aihe"><br><br>
                <input style="float:left; margin-right:11px;" maxlength="4" class="vuosiInput" type="text" name="avuosi"> - <input class="vuosiInput" maxlength="4" type="text" name="lvuosi"><br><br>
                <input type="submit" value="Hae" name="haku" id="hakuehto">
            </div>

        </form>

        <form style="display:inline" action="hakusivu.jsp" method="get">
            <button>
                Yksinkertaiseen hakuun
            </button>
        </form>

    </fieldset>
</div>

<%
} else {
%>

<div class="haku">
    <fieldset>
        <%@ include file="hakuvirheilmoitus.jsp" %>

        <form action="${pageContext.request.contextPath}/ListaaKirjat" method="get">
            <br/>Teoksen nimen osa tai ISBN<input type="text" name="kirja"><br>
            <br/>
            <input type="submit" value="Hae" name="haeNimella" id="hakuehto">
        </form>

        <form style="display:inline" action="${pageContext.request.contextPath}/TarkennettuunHakuun" method="get">
            <button>
                Tarkennettuun hakuun
            </button>
        </form>

    </fieldset>
</div>

<%    }
%>
