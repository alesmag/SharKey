package it.sharkey.model;

import java.util.Objects; 

public class Prodotto 
{
    // INIZIO DICHIARAZIONE ATTRIBUTI
    private String key;
    private double prezzo;
    private String titolo;
    private String descrizione;
    private String immagine;
    private int pegi;
    private String dataUscita;
    private String idVenditore;
    // FINE DICHIARAZIONE ATTRIBUTI
    
    
    // INIZIO DICHIARAZIONE GETTERS
    public String getKey() { return key; }
    public double getPrezzo() { return prezzo; }
    public String getTitolo() { return titolo; }
    public String getDescrizione() { return descrizione; }
    public String getImmagine() { return immagine; }
    public int getPegi() { return pegi; }
    public String getDataUscita() { return dataUscita; }
    public String getIdVenditore() { return idVenditore; }
    // FINE DICHIARAZIONE GETTERS
    
    
    // INIZIO DICHIARAZIONE SETTERS
    public void setKey(String key) { this.key = key; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setImmagine(String immagine) { this.immagine = immagine; }
    public void setPegi(int pegi) { this.pegi = pegi; }
    public void setDataUscita(String dataUscita) { this.dataUscita = dataUscita; }
    public void setIdVenditore(String idVenditore) { this.idVenditore = idVenditore; }
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
        
        final Prodotto other = (Prodotto) obj;
        
        if(!Objects.equals(this.key, other.key))
            return false;
        
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.key);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.prezzo) ^ (Double.doubleToLongBits(this.prezzo) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.titolo);
        hash = 71 * hash + Objects.hashCode(this.descrizione);
        hash = 71 * hash + Objects.hashCode(this.immagine);
        hash = 71 * hash + this.pegi;
        hash = 71 * hash + Objects.hashCode(this.dataUscita);
        hash = 71 * hash + Objects.hashCode(this.idVenditore);
        return hash;
    }
}
