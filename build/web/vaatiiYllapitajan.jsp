<%
    request.removeAttribute("lista");
    if (session.getAttribute("yllapitaja") == null) {
        response.sendRedirect("Kirjautuminen.jsp");
    }
%>