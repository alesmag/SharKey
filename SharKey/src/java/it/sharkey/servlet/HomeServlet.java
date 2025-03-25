package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.exceptions.InvalidParamException;
import it.sharkey.model.Recensione;
import it.sharkey.model.RecensioneFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
        
        // se non c'è connessione al database reindirizza direttamente all'index, che contiene recensioni statiche
        if(!DatabaseManager.getInstance().isConnected())
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        try
        {
            // esegue una query di ottenimento delle recensioni e assegna il risultato all'oggetto
            List<Recensione> recensioni = RecensioneFactory.getInstance().getRecensioni();
            
            // imposta la lista delle recensioni come attributo, questa lista verra' poi usata nell'index
            if(recensioni != null)
            {
                request.setAttribute("reviewList", recensioni);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            else
                throw new InvalidParamException("Nessuna recensione");
        }
        
        catch(InvalidParamException e)
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
