package it.sharkey.servlet;

import it.sharkey.database.DatabaseManager;
import it.sharkey.exceptions.InvalidParamException;
import it.sharkey.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


@MultipartConfig(fileSizeThreshold=1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

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
            String id = request.getParameter("key");
            String prezzo = request.getParameter("prezzo");
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            Part immagine = request.getPart("immagine");
            String pegi = request.getParameter("pegi");
            String dataUscita = request.getParameter("dataUscita");
            // FINE OTTENIMENTO PARAMETRI INSERITI
            
            String idVenditore = session.getAttribute("username").toString();
            
            // inizializzazione degli oggetti
            Connection conn = null;
            PreparedStatement stat = null;
            int res = 0;
            String imgURL = "";
            InputStream imgContent = null;
            File uploadedImg = null;
            
            try
            {
                // INIZIO VALIDAZIONE DEI DATI
                Utils.checkString(id, 1, 30);
                Utils.checkDouble(prezzo, 0.01, 999.99);
                Utils.checkString(nome, 1, 50);
                Utils.checkString(descrizione, 1, 300);
                Utils.checkString(String.valueOf(immagine), 1, 300);
                Utils.checkInteger(pegi, 1, 18);
                // FINE VALIDAZIONE DEI DATI
                
                // INIZIO GESTIONE OUTPUT DINAMICO (SUCCESSO)
                request.setAttribute("servredirect", "catalog");
                request.setAttribute("servtitle", "Product Success");
                request.setAttribute("msg", "Prodotto inserito con successo!");
                request.setAttribute("whereto", "al catalogo");
                // FINE GESTIONE OUTPUT DINAMICO (SUCCESSO)
                
                /* se non c'è connessione al database, verrà mostrato il corretto inserimento
                del prodotto, nonostante non venga davvero caricato */
                if(!DatabaseManager.getInstance().isConnected())
                    request.getRequestDispatcher("output.jsp").forward(request, response);
                
                // controllo formato immagine
                if(String.valueOf(immagine).contains(".jpg") || 
                   String.valueOf(immagine).contains(".jpeg") ||
                   String.valueOf(immagine).contains(".png"))
                {
                    // ottiene l'immagine dall'input
                    imgContent = immagine.getInputStream();

                    // percorso assoluto locale dell'immagine caricata
                    uploadedImg = new File("/home/fpw/NetBeansProjects/SharKey/web/uploads/" + immagine.getSubmittedFileName());

                    // imposta la stringa che verrà passata al database, ovvero l'URL dell'immagine
                    imgURL = "http://localhost:8080/SharKey/uploads/" + immagine.getSubmittedFileName();
                }
                
                else
                    throw new InvalidParamException("Formato immagine non valido. Dev'essere PNG, JPG o JPEG");
                
                
                try
                {
                    // stabilisce una connessione con il database
                    conn = DatabaseManager.getInstance().getDbConnection();

                    String query = "insert into prodotti values (?, ?, ?, ?, ?, ?, ?, ?)";

                    // precompilazione della query
                    stat = conn.prepareStatement(query);
                    
                    // INIZIO INSERIMENTO VALORI QUERY
                    stat.setString(1, id);
                    stat.setDouble(2, Double.parseDouble(prezzo));
                    stat.setString(3, nome);
                    stat.setString(4, descrizione);
                    stat.setString(5, imgURL);
                    stat.setInt(6, Integer.parseInt(pegi));
                    stat.setDate(7, Date.valueOf(dataUscita));
                    stat.setString(8, idVenditore);
                    // FINE INSERIMENTO VALORI QUERY
                    
                    // esegue la query e restituisce il numero di righe del databse modificate
                    res = stat.executeUpdate();
                }
                
                catch(SQLException e) {}
                
                if(res > 0)
                {
                    // copia l'immagine nella cartella specificata dagli oggetti
                    Files.copy(imgContent, uploadedImg.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    
                    request.getRequestDispatcher("output.jsp").forward(request, response);
                }

                else
                    throw new InvalidParamException("Key '" + id + "' gia' registrata");
            }

            catch(InvalidParamException e)
            {
                // INIZIO GESTIONE OUTPUT DINAMICO (ERRORE)
                request.setAttribute("servredirect", "newProduct.jsp");
                request.setAttribute("servtitle", "Product Error");
                request.setAttribute("msg", "Errore: " + e.getMessage());
                request.setAttribute("whereto", "alla pagina di inserimento");
                // FINE GESTIONE OUTPUT DINAMICO (ERROE)
                
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
