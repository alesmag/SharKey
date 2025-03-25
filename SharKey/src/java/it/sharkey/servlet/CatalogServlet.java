package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.model.Prodotto;
import it.sharkey.model.ProdottoFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CatalogServlet", urlPatterns = {"/catalog"})
public class CatalogServlet extends HttpServlet {

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
        
        // INIZIO GESTIONE STATICA
        
        // se non c'è connessione al database imposta staticamente i dati del prodotto
        if(!DatabaseManager.getInstance().isConnected())
        {
            Prodotto prodotto = new Prodotto();
            
            // INIZIO SETTING DEI DATI NELLA CLASSE
            prodotto.setImmagine("http://localhost:8080/SharKey/img/placeholder.jpg");
            prodotto.setTitolo("Minecraft: Java & Bedrock Edition");
            prodotto.setPrezzo(24.99);
            prodotto.setDescrizione("Minecraft è un gioco dove si scava (mine) e costruisce (craft) " +
                                    "con diversi tipi di blocchi 3D, all'interno di un grande mondo " +
                                    "fatto da diversi tipi di terreni e habitat da esplorare. In " +
                                    "questo mondo il sole sorge e tramonta, si va al lavoro, si " +
                                    "raccolgono materiali e si costruiscono utensili.");
            prodotto.setDataUscita("2011-11-18");
            prodotto.setPegi(7);
            prodotto.setIdVenditore("xXDaRkAnGeLcRaFtXx");
            // FINE SETTING DEI DATI NELLA CLASSE
            
            request.setAttribute("prodotto", prodotto);
            request.getRequestDispatcher("catalog.jsp").forward(request, response);
        }
        // FINE GESTIONE STATICA
        
        
        String offset = request.getParameter("offsetId");
        
        // passaggio dei valori nel JSON per l'utilizzo di AJAX
        if(offset != null)
        {
            // esegue una query di ricerca del prodotto e assegna il risultato all'oggetto
            Prodotto prodotto = ProdottoFactory.getInstance().getProdotto(offset);
            
            /* imposta l'oggetto prodotto come attributo per essere salvato nel file JSON
            ed essere utilizzato nel catalogo */
            request.setAttribute("prodotto", prodotto);
            
            response.setContentType("application/json");
            response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            request.getRequestDispatcher("prodottoJSON.jsp").forward(request, response);
        }
        
        else
        {
            Prodotto prodotto = ProdottoFactory.getInstance().getProdotto("0");
            request.setAttribute("prodotto", prodotto);
            request.getRequestDispatcher("catalog.jsp").forward(request, response);
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
