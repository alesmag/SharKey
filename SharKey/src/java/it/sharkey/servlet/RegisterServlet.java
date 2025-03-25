package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.exceptions.InvalidParamException;
import it.sharkey.utils.Utils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
        
        // INIZIO OTTENIMENTO PARAMETRI INSERITI
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        // FINE OTTENIMENTO PARAMETRI INSERITI
        
        // true se è stato inserito lo username di uno dei membri del gruppo
        boolean flag = username.equals("muggittu") || username.equals("cocco") || username.equals("mameli");
        
        // inizializzazione oggetti
        Connection conn = null;
        PreparedStatement stat = null;
        int res = 0;

        try
        {
            // INIZIO VALIDAZIONE DEI DATI
            Utils.checkString(username, 1, 50);
            Utils.checkString(email, 1, 50);
            Utils.checkString(password, 1, 50);
            Utils.checkString(nome, 1, 50);
            Utils.checkString(cognome, 1, 50);
            // FINE VALIDAZIONE DEI DATI
            
            // INIZIO SETTING ATTRIBUTI SESSIONE
            session.setAttribute("username", username);
            session.setAttribute("email", email);
            session.setAttribute("nome", nome);
            session.setAttribute("cognome", cognome);
            session.setMaxInactiveInterval(300);
            // FINE SETTING ATTRIBUTI SESSIONE
            
            // INIZIO GESTIONE OUTPUT DINAMICO (SUCCESSO)
            request.setAttribute("servredirect", "user");
            request.setAttribute("servtitle", "Register Success");
            request.setAttribute("msg", "Utente registrato con successo!");
            request.setAttribute("whereto", "all'area personale");
            // FINE GESTIONE OUTPUT DINAMICO (SUCCESSO)
            
            // se flag = true, significa che è stato inserito uno dei membri del gruppo, che però esiste già, e darà errore
            if(flag)
                throw new InvalidParamException("Utente '" + username + "' gia' registrato");
            
            // se non c'è connessione al database, l'utente verrà sempre registrato ma non salvato effettivamente
            if(!DatabaseManager.getInstance().isConnected())
                request.getRequestDispatcher("output.jsp").forward(request, response);
            
            try
            {
                // stabilisce una connessione con il database
                conn = DatabaseManager.getInstance().getDbConnection();

                String query = "insert into utenti values (?, ?, ?, ?, ?)";

                // precompilazione della query
                stat = conn.prepareStatement(query);
                
                // INIZIO INSERIMENTO VALORI QUERY
                stat.setString(1, username);
                stat.setString(2, email);
                stat.setString(3, password);
                stat.setString(4, nome);
                stat.setString(5, cognome);
                // FINE INSERIMENTO VALORI QUERY

                // esegue la query e restituisce il numero di righe del databse modificate
                res = stat.executeUpdate();
            }
            
            catch(SQLException e) {}
            
            
            if(res > 0)
                request.getRequestDispatcher("output.jsp").forward(request, response);
                
            else
                throw new InvalidParamException("Utente '" + username + "' gia' registrato");
        }
        
        catch(InvalidParamException e)
        {
            // in caso di errore, invalida la sessione
            session.invalidate();
            
            // INIZIO GESTIONE OUTPUT DINAMICO (ERRORE)
            request.setAttribute("servtitle", "Register Error");
            request.setAttribute("msg", "Errore: " + e.getMessage());
            request.setAttribute("whereto", "alla pagina di registrazione");
            // FINE GESTIONE OUTPUT DINAMICO (ERRORE)
            
            request.getRequestDispatcher("logregError.jsp").forward(request, response);
        }
        
        finally
        {
            try { stat.close(); } catch(SQLException e) {}
            try { conn.close(); } catch(SQLException e) {}
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
