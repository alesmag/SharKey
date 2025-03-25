package it.sharkey.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseManager 
{
    private static DatabaseManager instance;
   
    
    private DatabaseManager()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        } 
        
        catch(ClassNotFoundException e)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    // restituisce l'istanza della classe
    public static DatabaseManager getInstance()
    {
        if(instance == null)
            instance = new DatabaseManager();
        
        return instance;
    }
    
    // stabilisce una connessione con il database
    public Connection getDbConnection()
    {
        String db = "jdbc:postgresql://localhost:5432/panicatthecisco";
        String user = "postgres";
        String pass = "fondamenti";
        
        try
        {
            Connection conn = DriverManager.getConnection(db, user, pass);
            return conn;
        }
        
        catch(SQLException e)
        {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    
    // verifica la connessione con il database. ritorna true se è connesso, altrimenti false
    public boolean isConnected()
    {
        String db = "jdbc:postgresql://localhost:5432/panicatthecisco";
        String user = "postgres";
        String pass = "fondamenti";
        
        try
        {
            Connection conn = DriverManager.getConnection(db, user, pass);
            return (conn != null && !conn.isClosed());
        }
        
        catch(SQLException e) {}
        
        return false;
    }
}
