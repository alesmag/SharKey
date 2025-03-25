package it.sharkey.model;

import java.util.Objects;

public class Utente 
{
    // INIZIO DICHIARAZIONE ATTRIBUTI
    private String username;
    private String email;
    private String psw;
    private String nome;
    private String cognome;
    // FINE DICHIARAZIONE ATTRIBUTI
    
    
    // INIZIO DICHIARAZIONE GETTERS
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPsw() { return psw; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    // FINE DICHIARAZIONE GETTERS
    
    // INIZIO DICHIARAZIONE SETTERS
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPsw(String psw) { this.psw = psw; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    // FINE DICHIARAZIONE SETTERS
    
    
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        
        if(this == null)
            return false;
        
        if(getClass() != obj.getClass())
            return false;
        
        final Utente other = (Utente) obj;
        
        if(!Objects.equals(this.username, other.username))
            return false;
        
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.psw);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cognome);
        return hash;
    }
}
