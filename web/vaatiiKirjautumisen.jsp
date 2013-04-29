<%
    request.removeAttribute("lista");
    if (session.getAttribute("kirjautunut") == null) {
        response.sendRedirect("Kirjautuminen.jsp");
    }
%>