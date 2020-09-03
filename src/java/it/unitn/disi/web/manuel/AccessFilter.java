package it.unitn.disi.web.manuel;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Filtro per l'accesso al gioco.
 * @author manuel
 */
public class AccessFilter implements Filter {
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
      
    /**
     *  Controlla sessione e parametri
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;
        HttpSession session = hreq.getSession(false);
        String fname = hreq.getParameter("fname");
        String url = hreq.getContextPath()+"/index.html";
        
        // Vero se la sessione non è esistente
        if( session == null){
            // Allora la creo se fname valido
            if( isUserOk( fname)){
                session = hreq.getSession(true);
                session.setAttribute("fname", fname);
                session.setAttribute("matrice", new Matrice());
                chain.doFilter( hreq, hres);
            }else{
                hres.sendRedirect( url);
            }
            
        }else{
            chain.doFilter( hreq, hres);
        }
    }

    /**
     * Controlla che lo user sia valido, quindi diverso da null e che non sia
     * una stringa vuota
     * @param user stringa che conterrà lo fname da controllare
     * @return true se user è valido, false altrimenti
     */
    public boolean isUserOk( String user){
        return ( user != null && !user.equals(""));
    }
    
    @Override
    public void destroy() {
        this.filterConfig = null;
    }
   
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
}
