package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.exceptions.InvalidParamException;
import it.sharkey.utils.Utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReviewServlet", urlPatterns = {"/review"})
public class ReviewServlet extends HttpServlet {

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
        
        if(session != null)
        {
            // INIZIO OTTENIMENTO PARAMETRI INSERITI
            String titolo = request.getParameter("titolo");
            String testo = request.getParameter("testo");
            String voto = request.getParameter("voto");
            // FINE OTTENIMENTO PARAMETRI INSERITI
            
            String username = session.getAttribute("username").toString();
            
            // formattazione data e ora
            LocalDateTime local = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            
            // inizializzazione oggetti
            Connection conn = null;
            PreparedStatement stat = null;
            int res = 0;
            
            try
            {
                // INIZIO VALIDAZIONE DEI DATI
                Utils.checkString(titolo, 1, 50);
                Utils.checkString(testo, 1, 300);
                Utils.checkInteger(voto, 1, 5);
                // FINE VALIDAZIONE DEI DATI
                
                // INIZIO GESTIONE OUTPUT DINAMICO (SUCCESSO)
                request.setAttribute("servredirect", "home");
                request.setAttribute("servtitle", "Review Success");
                request.setAttribute("msg", "Recensione inserita con successo!");
                request.setAttribute("whereto", "alla home page");
                // FINE GESTIONE OUTPUT DINAMICO (SUCCESSO)
                
                /* se non c'è connessione al database, verrà mostrato il corretto inserimento
                della recensione, nonostante non venga davvero caricata */
                if(!DatabaseManager.getInstance().isConnected())
                    request.getRequestDispatcher("output.jsp").forward(request, response);
                
                try
                {
                    // stabilisce una connessione con il database
                    conn = DatabaseManager.getInstance().getDbConnection();
                    
                    String query = "insert into recensioni (titolo, testo, voto, data, username) values (?, ?, ?, ?, ?)";
                    
                    // precompilazione della query
                    stat = conn.prepareStatement(query);
                    
                    // INIZIO INSERIMENTO VALORI QUERY
                    stat.setString(1, titolo);
                    stat.setString(2, testo);
                    stat.setInt(3, Integer.parseInt(voto));
                    stat.setString(4, local.format(format));
                    stat.setString(5, username);
                    // FINE INSERIMENTO VALORI QUERY
                    
                    // esegue la query e restituisce il numero di righe del databse modificate
                    res = stat.executeUpdate();
                }
                
                catch(SQLException e) {}
                
                if(res > 0)
                    request.getRequestDispatcher("output.jsp").forward(request, response);
            }
            
            catch(InvalidParamException e) 
            {
                // INIZIO GESTIONE OUTPUT DINAMICO (ERRORE)
                request.setAttribute("servredirect", "newReview.jsp");
                request.setAttribute("servtitle", "Review Error");
                request.setAttribute("msg", "Errore: " + e.getMessage());
                request.setAttribute("whereto", "alla pagina di inserimento");
                // FINE GESTIONE OUTPUT DINAMICO (ERRORE)
                
                request.getRequestDispatcher("output.jsp").forward(request, response);
            }

            finally
            {
                try { stat.close(); } catch(SQLException e) {}
                try { conn.close(); } catch(SQLException e) {}
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
