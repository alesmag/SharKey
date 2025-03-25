package it.sharkey.model;

import java.util.Objects;


public class Recensione 
{
    // INIZIO DICHIARAZIONE ATTRIBUTI
    private int id;
    private String titolo;
    private String testo;
    private int voto;
    private String data;
    private String username;
    // FINE DICHIARAZIONE ATTRIBUTI
    
    
    // INIZIO DICHIARAZIONE GETTERS
    public int getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getTesto() { return testo; }
    public int getVoto() { return voto; }
    public String getData() { return data; }
    public String getUsername() { return username; }
    // FINE DICHIARAZIONE GETTERS
    
    
    // INIZIO DICHIARAZIONE SETTERS
    public void setId(int id) { this.id = id; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setTesto(String testo) { this.testo = testo; }
    public void setVoto(int voto) { this.voto = voto; }
    public void setData(String data) { this.data = data; }
    public void setUsername(String username) { this.username = username; }
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
        
        final Recensione other = (Recensione) obj;
        
        if(!Objects.equals(this.id, other.id))
            return false;
        
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.titolo);
        hash = 83 * hash + Objects.hashCode(this.testo);
        hash = 83 * hash + this.voto;
        hash = 83 * hash + Objects.hashCode(this.data);
        hash = 83 * hash + Objects.hashCode(this.username);
        return hash;
    }
}
