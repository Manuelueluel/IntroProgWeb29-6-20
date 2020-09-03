package it.unitn.disi.web.manuel;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet per la gestione del gioco
 * @author manuel
 */
public class GestioneGioco extends HttpServlet {
    
    /**
     * Utilizzato per richiedere alla servlet i valori della matrice.
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Matrice matrice = (Matrice) session.getAttribute("matrice");
        
        int colonna, riga;
        PrintWriter out = response.getWriter();
        int value = 0;
        
        try{
            riga = Integer.parseInt( request.getParameter("riga"));
            colonna = Integer.parseInt( request.getParameter("colonna"));
            value = matrice.getValue(riga, colonna);
        }catch(NumberFormatException e){
            System.exit(1);
        }
        
        out.print(value);
    }
}
