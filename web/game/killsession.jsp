<%-- 
    Author     : manuel
    
    Pagina jsp con il solo scopo di invalidare la sessione e reindirizzare
    alla pagina iniziale.
--%>

<%
    session = request.getSession();
    session.setAttribute("fname", null);
    session.setAttribute("matrice", null);
    session.invalidate();
    response.sendRedirect("/MineSweeper/index.html");
%>

