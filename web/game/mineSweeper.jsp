<%-- 
    @author manuel
    Pagina principale del gioco, è possibile selezionare una cella cliccandoci
    con il mouse oppure usando la selezione in basso.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" language="java"%>
 
<%
    String fname;
    fname = (String) session.getAttribute("fname");
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript" src="utility.js"></script> 
        
        <link rel="StyleSheet" type="text/CSS" href="style.css">
        
        <title>MineSweeper</title>
        
    </head>
    
    <body onload="addmatrice();">
        
        <h1>Hello <%= fname%></h1>
        
        <div id="myModal" class="modal">
            <div class="modal-content">
                <p>Hai trovato una bomba e hai perso!
                    Clicca il pulsante per ricominciare la parita
                </p>
                <a href="resetgame.jsp" type="button" class="reset">
                    Ricomincia partita
                </a>
            </div>
        </div>
        
        <div class="grid-container" id="matrice"></div>
    
        <!--Necessario il return false nella onsubmit altrimenti la form 
        faceva un refresh automatico della pagina, la possibile soluzione è
        quella di implementare il doppio select con un altro sistema ma era 
        troppo dispendioso.-->
        <form onsubmit="getValueForm();return false">
           <label>Seleziona cella:</label>     
           <select name="riga" id="riga">
                <option value="0">1</option>
                <option value="1">2</option>
                <option value="2">3</option>
                <option value="3">4</option>
                <option value="4">5</option>
                <option value="5">6</option>
                <option value="6">7</option>
                <option value="7">8</option>
                <option value="8">9</option>
           </select>
           <select name="colonna" id="colonna">
                <option value="0">1</option>
                <option value="1">2</option>
                <option value="2">3</option>
                <option value="3">4</option>
                <option value="4">5</option>
                <option value="5">6</option>
                <option value="6">7</option>
                <option value="7">8</option>
                <option value="8">9</option>
           </select>
           <input type="submit" value="test">
        </form>
    
        <a href="killsession.jsp" type="button" class="reset">Reset</a>
        
    </body>
</html>
