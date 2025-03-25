package it.sharkey.model;

import it.sharkey.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecensioneFactory 
{
    private static RecensioneFactory instance;
    
    
    // restituisce l'istanza della classe
    public static RecensioneFactory getInstance()
    {
        if(instance == null)
            instance = new RecensioneFactory();
        
        return instance;
    }
    
    
    // ricerca le ultime 5 recensioni inserite e le restituisce sotto forma di lista
    public List<Recensione> getRecensioni()
    {
        // inizializzazione oggetti
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        List<Recensione> recensioni = new ArrayList<>();
        
        try
        {
            // stabilisce una connessione con il database
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "select * from recensioni order by data desc limit 5";
            stat = conn.prepareStatement(query);
            set = stat.executeQuery();
            
            // continua a scansionare finchè il set ha una prossima riga
            while(set.next())
            {
                Recensione recensione = new Recensione();
                
                // INIZIO INSERIMENTO VALORI NELLA CLASSE
                recensione.setTitolo(set.getString("titolo"));
                recensione.setTesto(set.getString("testo"));
                recensione.setVoto(set.getInt("voto"));
                recensione.setData(set.getString("data"));
                recensione.setUsername(set.getString("username"));
                // FINE INSERIMENTO VALORI NELLA CLASSE
                
                // aggiunge l'oggetto recensione alla lista
                recensioni.add(recensione);
            }
            
            return recensioni;
        }
        
        catch(SQLException e)
        {
            Logger.getLogger(RecensioneFactory.class.getName()).log(Level.SEVERE, null, e);
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
