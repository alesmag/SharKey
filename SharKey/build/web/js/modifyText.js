$(document).ready(function()
{
    const step = 2;
    const defSizeP = 16;
    const maxSizeP = defSizeP * 2;
    const minSizeP = defSizeP * 0.75;
    var sizeP = defSizeP;

    const defSizeH = 20;
    const maxSizeH = defSizeP * 2;
    const minSizeH = defSizeP * 0.75;
    var sizeH = defSizeH;

    // funzione per l'ingrandimento del testo
    $("#more").on("click",function()
    {
        if(sizeP + step < maxSizeP) sizeP += step;
        if(sizeH + step < maxSizeH) sizeH += step;
        $(".adjustable_P").css("font-size",sizeP + "px");
        $(".adjustable_H").css("font-size",sizeH + "px");
    });
    
    // funzione per impostare il testo a grandezza default
    $("#default").on("click",function()
    {
        $(".adjustable_P").css("font-size",defSizeP + "px");
        $(".adjustable_H").css("font-size",defSizeH + "px");
        sizeP = defSizeP;
        sizeH = defSizeH;
    });

    // funzione per la riduzione del testo
    $("#less").on("click",function()
    {
        if(sizeP - step > minSizeP) sizeP -= step;
        if(sizeH - step > minSizeH) sizeH -= step;
        $(".adjustable_P").css("font-size",sizeP + "px");
        $(".adjustable_H").css("font-size",sizeH + "px");
    });
});