package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.sharkey.utils.*;
import it.sharkey.exceptions.*;
import it.sharkey.model.*;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // crea la sessione
        HttpSession session = request.getSession();

        // INIZIO OTTENIMENTO PARAMETRI INSRITI
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // FINE OTTENIMENTO PARAMETRI INSERITI
        
        // true se almeno una di queste condizioni viene rispettata, altrimenti false
        boolean flag = (username.equals("muggittu") && password.equals("66124")) || 
                (username.equals("cocco") && password.equals("66123")) || 
                (username.equals("mameli") && password.equals("66127"));
        
        try
        {
            // INIZIO VALIDAZIONE DEI DATI
            Utils.checkString(username, 1, 50);
            Utils.checkString(password, 1, 50);
            // FINE VALIDAZIONE DEI DATI
            
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(300);
            
            /* se flag = true, significa che sono stati inseriti i dati 
            di un componente del gruppo, e reindirizzerà alla pagina personale */
            if(flag)
                response.sendRedirect("user");
            
            /* se flag = false, significa che non sono stati inseriti i dati
            di un componente del gruppo, e verranno cercati i dati inseriti
            all'interno del database */
            else if(!flag)
            {
                /* se non c'è connessione al database, i dati non saranno validi
                essendo impossibile cercarli al suo interno */
                if(!DatabaseManager.getInstance().isConnected())
                    throw new InvalidParamException("Username o Password non validi");
                
                // esegue una ricerca dell'utente e assegna il risultato all'oggetto
                Utente utente = UtenteFactory.getInstance().getUtenteByUsernamePassword(username, password);
                
                if(utente != null)
                    response.sendRedirect("user");
                
                else
                    throw new InvalidParamException("Username o Password non validi");
            }
            
            else
                throw new InvalidParamException("Username o Password non validi");
        }
        
        catch(InvalidParamException e)
        {
            // in caso di errore, invalida la sessione
            session.invalidate();
            
            // INIZIO GESTIONE OUTPUT DINAMICO
            request.setAttribute("servtitle", "Login Error");
            request.setAttribute("msg", "Errore: " + e.getMessage());
            request.setAttribute("whereto", "alla pagina di login");
            request.getRequestDispatcher("logregError.jsp").forward(request, response);
            // FINE GESTIONE OUTPUT DINAMICO
        }
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
