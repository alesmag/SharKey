<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Visualizzazione del catalogo dei prodotti venduti dai nostri utenti">
        <meta name="keywords" content="sharkey, catalog, view, product, videogame">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/catalog">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/code.js"></script>
        <script type="text/javascript" src="js/ajax.js"></script>
        <title>Catalogo</title>
    </head>
    <body>
        <c:if test="${empty username}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        <c:set var="page" value="catalog" scope="request"/>

        <main class="col-9">
            <div class="col-2">
                <button id="prev_videogame" class="round_button"><</button>
            </div>
            
            <article id="catalogo" class="col-8">
                <h3 id="cat_title" class="adjustable_H"> ${prodotto.getTitolo()} </h3>
                <br>
                <img src="${prodotto.getImmagine()}" alt="uploadedimg" width="250" height="250" id="cat_img"/>
                
                
                <p id="cat_descrizione" class="adjustable_P">${prodotto.getDescrizione()} </p>
                <p id="cat_prezzo" class="adjustable_P"> <b>Prezzo:</b> ${prodotto.getPrezzo()}€ </p>
                <p id="cat_data" class="adjustable_P"> <b>Data d'uscita:</b> ${prodotto.getDataUscita()} </p>
                <p id="cat_pegi" class="adjustable_P"> <b>PEGI ${prodotto.getPegi()}</b> </p>
                <p id="cat_user" class="adjustable_P"> <b>Venduto da:</b> ${prodotto.getIdVenditore()} </p>
                
                <input type="submit" value="Acquista" class="gradientButton">
            </article>
            
            <div class="col-2">
                <button id="next_videogame" class="round_button">></button>
            </div>    
        </main>
        
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
