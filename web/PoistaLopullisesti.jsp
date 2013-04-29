<%-- 
    Document   : PoistaLopullisesti
    Created on : Apr 24, 2013, 6:34:59 PM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjastotietokanta</title>
        <link rel="stylesheet" type="text/css" href="Tyylit.css" />
    </head>
    <body>

        <%@include  file="vaatiiKirjautumisen.jsp" %>
        <%@include file="vaatiiYllapitajan.jsp" %>

        <%@ include file="nav.jsp" %>

        <div class="kentat">
            <fieldset>
                <p class="pienella">
                    Kirjoita kenttään tietokannasta LOPULLISESTI POISTETTAVAN teoksen ISBN.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/PoistaLopullisesti" method="post">
                    <label>ISBN</label> <input type="text" name="isbn"><br><br>

                    <input type="submit" value="Varmista poisto">
                </form>

            </fieldset>
        </div>

        <%
            if (session.getAttribute("virhepoistettaessalopullisesti") != null) {
                session.removeAttribute("virhepoistettaessalopullisesti");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Kirjan poistaminen ei onnistunut. Varmista, että kyseinen 
                    teos on tietokannassa esimerkiksi hakutoiminnon avulla.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>         
        
         <%
            if (session.getAttribute("poistaminenonnistuilopullisesti") != null) {
                session.removeAttribute("poistaminenonnistuilopullisesti");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Kirjan ja siihen liittyneiden tietojen sisältöjen poistaminen onnistui.
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>         

        <script src="linkit.js"></script>

    </body>
</html>
