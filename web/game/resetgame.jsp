<%-- 
    Author     : manuel
    
    Pagina jsp con il solo scopo di resettare la matrice di gioco.
    Mi è risultato necessario creare una nuova matrice e comunque eseguire su
    di essa un reset, con la funzione resetMatrice(), sebbene la creazione di 
    nuova matrice implica che essa sia generata casualmente, problema di cui
    non capisco il motivo.
--%>

<%@page import="it.unitn.disi.web.manuel.Matrice"%>

<%!
    Matrice m = new Matrice();
    
%>

<%
    session = request.getSession();
    m.resetMatrice();
    session.removeAttribute("matrice");
    session.setAttribute("matrice", m);
    response.sendRedirect("mineSweeper.jsp");
%>