<%-- 
    Document   : Kirjautuminen
    Created on : Apr 2, 2013, 5:11:12 PM
    Author     : hkskogbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirjaudu kirjastotietokantaan</title>
        <link rel="stylesheet" type"text/css" href="Tyylit.css" />
    </head>
    <body>

        <%@ include file="nav.jsp" %>




        <div class="kentat">
            <fieldset>

                <%
                    if (session.getAttribute("onnistuiko") != null) {
                        session.removeAttribute("onnistuiko");
                %>

                <p class="pienella">
                    Kirjautuminen epäonnistui. Käyttäjätunnus tai salasana saattaa olla väärin.
                </p>       

                <%
                    }
                %>


                <form action="LoginCheck.jsp" method="post">
                    <br/>Käyttäjätunnus:<input type="text" name="username"><br>
                    <br/>Salasana:<input type="password" name="password"><br>
                    <br/><input type="submit" value="OK">
                </form>
            </fieldset>
        </div>



    </body>
</html>
