<%-- 
    Document   : PoistaKirja
    Created on : Apr 2, 2013, 10:34:23 PM
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

        <%@ include file="nav.jsp" %>

        <div class="kentat">
            <fieldset>
                <p class="pienella">
                    Kirjoita kenttään poistetuksi merkittävän teoksen ISBN.<br>
                    Varmista, että teoksen kopiot on poistettu tietokannasta Lainaukset-toiminnon avulla ennen teoksen poistamista.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/Poisto" method="post">
                    <label>ISBN</label> <input type="text" name="isbn"><br><br>

                    <div>
                        <input type="radio" name="poistetaan" value="y" checked=""><p class="pienella">Lisätään poistomerkintä</p>
                        <input type="radio" name="poistetaan" value="n"><p class="pienella">Poistetaan poistomerkintä</p>
                    </div>

                    <input type="submit" value="Tee muutos">
                </form>

            </fieldset>
        </div>

        <%
            if (session.getAttribute("virhepoistettaessa") != null) {
                session.removeAttribute("virhepoistettaessa");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Kirjan poistaminen ei onnistunut.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>    
        
         <%
            if (session.getAttribute("poistomerkintaonnistui") != null) {
                session.removeAttribute("poistomerkintaonnistui");
        %>
        <div class="ilmoitus">
            <fieldset>
                <p class="pienella">
                    Tapahtuma käsiteltiin onnistuneesti.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>   

        <script src="linkit.js"></script>

    </body>
</html>
