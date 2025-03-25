$(document).ready(function()
{
    var offset = 0;
    
    function aggiornaProdotto(prodotto)
    {
        $("#cat_img").attr("src", prodotto.immagine);
        $("#cat_title").text(prodotto.titolo);
        $("#cat_prezzo").html("<b>Prezzo: </b>" + prodotto.prezzo + "€");
        $("#cat_descrizione").text(prodotto.descrizione);
        $("#cat_data").html("<b>Data d'uscita: </b>" + prodotto.dataUscita);
        $("#cat_pegi").html("<b>PEGI " + prodotto.pegi + "</b>");
        $("#cat_user").html("<b>Venduto da:</b> " + prodotto.venditore);
    }
    
    // aggiorna il prodotto quando si preme sul tasto "indietro"
    $("#prev_videogame").click(function()
    {
        if(offset > 0)
            offset--;
        $.ajax
        ({
            url: "catalog",
            data: { offsetId: offset },
            dataType: "json",
            success: function(data, state) { aggiornaProdotto(data); },
            error: function(data, state) {}
        });
    });
    
    // aggiorna il prodotto quando si preme sul tasto "avanti"
    $("#next_videogame").click(function()
    {
        offset++;
        $.ajax
        ({
            url: "catalog",
            data: { offsetId: offset },
            dataType: "json",
            success: function(data, state) 
            { 
                if(data.venditore === "")
                    offset--;
                else
                    aggiornaProdotto(data); 
            },
            error: function(data, state) {}
        });
    });
});