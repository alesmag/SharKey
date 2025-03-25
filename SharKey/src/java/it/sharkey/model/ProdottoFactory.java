package it.sharkey.model;

import it.sharkey.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdottoFactory 
{
    private static ProdottoFactory instance;
    
    
    // restituisce l'istanza della classe
    public static ProdottoFactory getInstance()
    {
        if(instance == null)
            instance = new ProdottoFactory();
        
        return instance;
    }
    
    
    /* ricerca i prodotti nel database e ne restituisce il risultato.
    viene passato l'offset come parametro per essere utilizzato all'interno della query */
    public Prodotto getProdotto(String offset)
    {
        // inizializzazione oggetti
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        Prodotto prodotto = new Prodotto();
        
        try
        {
            // stabilisce una connessione con il database
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "select * from prodotti limit 1 offset ?";
            
            // precompilazione della query
            stat = conn.prepareStatement(query);
            
            // inserisce l'offset come valore nella query
            stat.setInt(1, Integer.parseInt(offset));
            
            // esegue la query e restituisce il numero di righe del databse modificate
            set = stat.executeQuery();
            
            // continua a scansionare finchè il set ha una prossima riga
            while(set.next())
            {
                // INIZIO INSERIMENTO VALORI NELLA CLASSE
                prodotto.setKey(set.getString("id"));
                prodotto.setPrezzo(set.getDouble("prezzo"));
                prodotto.setTitolo(set.getString("nome"));
                prodotto.setDescrizione(set.getString("descrizione"));
                prodotto.setImmagine(set.getString("immagine"));
                prodotto.setPegi(set.getInt("pegi"));
                prodotto.setDataUscita(set.getString("data_uscita"));
                prodotto.setIdVenditore(set.getString("id_venditore"));
                // FINE INSERIMENTO VALORI NELLA CLASSE
            }
            
            return prodotto;
        }
        
        catch(SQLException e)
        {
            Logger.getLogger(ProdottoFactory.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally
        {
            try { set.close(); } catch(SQLException e) {}
            try { stat.close(); } catch(SQLException e) {}
            try { conn.close(); } catch(SQLException e) {}
        }
        
        return null;
    }
}
