package it.sharkey.utils;

import it.sharkey.exceptions.*;


public class Utils 
{
    // controlla se una stringa è compresa tra min e max
    public static void checkString(String param, int min, int max) throws InvalidParamException
    {
        if(param == null)
            throw new InvalidParamException("Parametro nullo");
        
        if(param.length() < min || param.length() > max)
            throw new InvalidParamException("La stringa \'" + param + "\' non e' valida. Deve avere una dimensione compresa tra \'" + min + "\' e \'" + max + "\' caratteri");
    }
    
    
    // controlla se un numero intero è compreso tra min e max
    public static void checkInteger(String param, int min, int max) throws InvalidParamException
    {
        try
        { 
            if(Integer.parseInt(param) < min || Integer.parseInt(param) > max)
                throw new InvalidParamException("Il valore \'" + param + "\' dev'essere compreso tra \'" + min + "\' e \'" + max + "\'");
        }
        
        catch(NumberFormatException e)
        {
            throw new InvalidParamException("Il valore \'" + param + "\' non rappresenta un numero intero");
        }
    }
    
    
    // controlla se un numero a doppia precisione è compreso tra min e max
    public static void checkDouble(String param, double min, double max) throws InvalidParamException
    {
        try
        {
            if(Double.parseDouble(param) < min || Double.parseDouble(param) > max)
                throw new InvalidParamException("Il valore \'" + param + "\' dev'essere compreso tra \'" + min + "\' e \'" + max + "\'");
        }
        
        catch(NumberFormatException e)
        {
            throw new InvalidParamException("Il valore \'" + param + "\' non rappresenta un formato prezzo");
        }
    }
}
