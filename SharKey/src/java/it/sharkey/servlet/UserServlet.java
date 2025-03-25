package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.model.Utente;
import it.sharkey.model.UtenteFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

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
        
        // viene verificata l'esistenza di una sessione
        HttpSession session = request.getSession(false);
        
        // se non c'è connessione al database, reindirizza direttamente all'area personale
        if(!DatabaseManager.getInstance().isConnected())
            request.getRequestDispatcher("personalArea.jsp").forward(request, response);
        
        
        if(session != null)
        {
            try
            {
                // esegue una query di ricerca dell'utente e assegna il risultato all'oggetto
                Utente user = UtenteFactory.getInstance().getUtenteByUsername(session.getAttribute("username").toString());

                if(user != null)
                {
                    // INIZIO SETTING ATTRIBUTI REQUEST
                    request.setAttribute("email", user.getEmail());
                    request.setAttribute("nome", user.getNome());
                    request.setAttribute("cognome", user.getCognome());
                    // FINE SETTING ATTRIBUTI REQUEST
                    
                    request.getRequestDispatcher("personalArea.jsp").forward(request, response);
                }
            }

            catch(IOException | ServletException e)
            {
                // in caso di errore, invalida la sessione
                session.invalidate();
                
                // INIZIO GESTIONE OUTPUT DINAMICO (ERRORE)
                request.setAttribute("servtitle", "Personal Area Error");
                request.setAttribute("msg", "Errore nella ricezione dei dati");
                request.setAttribute("whereto", "alla pagina di login");
                // FINE GESTIONE OUTPUT DINAMICO (ERRORE)
                
                request.getRequestDispatcher("logregError.jsp").forward(request, response);
            }
        }
        
        else
            response.sendRedirect("login.jsp");
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
