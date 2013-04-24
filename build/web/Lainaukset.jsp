<%-- 
    Document   : Lainaukset
    Created on : Apr 23, 2013, 10:08:33 AM
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
                    Selaa lainauksia teokseen tai käyttäjään liittyen. 

                    Kirjoita hakukenttään hakusana ja valitse hakuehdot.<br><br>
                </p>

                <form name="" action="${pageContext.request.contextPath}/Lainaukset" method="post">
                    <label>Haku</label><input type="text" name="syote"><br><br>

                    <div style="clear:both;">
                        <input type="radio" name="toiminto" value="teos" checked=""><p class="pienella">Teoksen nimellä</p>
                        <input type="radio" name="toiminto" value="kayttaja"><p class="pienella">Käyttäjällä</p>
                    </div>

                    <input type="submit" value="Lainauksiin">
                </form>
            </fieldset>
        </div>


        <script src="linkit.js"></script>

    </body>
</html>
