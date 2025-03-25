package it.sharkey.model;

import it.sharkey.database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UtenteFactory 
{
    private static UtenteFactory instance;
    
    
    // restituisce l'istanza della classe
    public static UtenteFactory getInstance()
    {
        if(instance == null)
            instance = new UtenteFactory();
        
        return instance;
    }
    
    
    // ricerca un utente nel database tramite username e password, e ne restituisce il risultato
    public Utente getUtenteByUsernamePassword(String username, String password)
    {
        // inizializzazione oggetti
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        
        try
        {
            // stabilisce una connessione con il database
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "select * from utenti where username = ? and psw = ?";
            
            // precompilazione della query
            stat = conn.prepareStatement(query);
            
            // INIZIO INSERIMENTO VALORI QUERY
            stat.setString(1, username);
            stat.setString(2, password);
            // FINE INSERIMENTO VALORI QUERY
            
            // esegue la query
            set = stat.executeQuery();
            
            // true se il set ha una riga successiva (unico risultato)
            if(set.next())
            {
                Utente utente = new Utente();
                
                // INIZIO INSERIMENTO VALORI NELLA CLASSE
                utente.setUsername(set.getString("username"));
                utente.setEmail(set.getString("email"));
                utente.setPsw(set.getString("psw"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                // FINE INSERIMENTO VALORI NELLA CLASSE
                
                return utente;
            }
            
            else
                return null;
        }
        
        catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);
        }
        
        finally
        {
            try { set.close(); } catch(SQLException e) {}
            try { stat.close(); } catch(SQLException e) {}
            try { conn.close(); } catch(SQLException e) {}
        }
        
        return null;
    }
    
    
    // ricerca un utente nel database tramite il solo username, e ne restituisce il risultato
    public Utente getUtenteByUsername(String username)
    {
        // inizializzazione oggetti
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet set = null;
        
        try
        {
            // stabilisce una connessione con il database
            conn = DatabaseManager.getInstance().getDbConnection();
            
            String query = "select * from utenti where username = ?";
            
            // precompilazione della query
            stat = conn.prepareStatement(query);
            
            // inserimento dello username nella query
            stat.setString(1, username);
            
            // esegue la query
            set = stat.executeQuery();
            
            
            // true se il set ha una riga successiva (unico risultato)
            if(set.next())
            {
                Utente utente = new Utente();
                
                // INIZIO INSERIMENTO VALORI NELLA CLASSE
                utente.setUsername(set.getString("username"));
                utente.setEmail(set.getString("email"));
                utente.setPsw(set.getString("psw"));
                utente.setNome(set.getString("nome"));
                utente.setCognome(set.getString("cognome"));
                // FINE INSERIMENTO VALORI NELLA CLASSE
                
                return utente;
            }
            
            else
                return null;
        }
        
        catch(SQLException e)
        {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, e);
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
