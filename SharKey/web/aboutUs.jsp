<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chi Siamo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Informazioni aggiuntive sui fondatori del sito.">
        <meta name="keywords" content="sharkey, founders, about, creators">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/aboutUs.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
    </head>
    <body>
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <main class="col-9">
            <div class="gray_rectangle CS_membro">
                <div class="CS_img">
                    <img src="img/Scoc.jpg" alt="Scoc" width="150" height="150"/>
                </div>
                <section class="CS_text">
                    <h3 class="adjustable_H">
                        Scoc
                    </h3>
                    <br>
                    <p class="adjustable_P">
                        Ciao sono Michele, conosciuto anche come "Mike of Quartucciu" sul web. 
                        Sono attualmente il giocatore di S4 League più forte in provincia di Cagliari. 
                        Mi piacciono le alette di pollo.
                    </p>
                </section>
            </div>
            
            <div class="gray_rectangle CS_membro">
                <div class="CS_img">
                    <img src="img/Smag.png" alt="Smag" width="150" height="150"/>
                </div>
                <section class="CS_text">
                    <h3 class="adjustable_H">
                        Smag
                    </h3>
                    <br>
                    <p class="adjustable_P">
                        Ex pro player di Roblox, ho dovuto abbandonare la mia fruttuosa carriera per dedicare anima e corpo a questo sito. 
                        Nel tempo libero urlo fortissimo per cercare di diventare un Super Saiyan.
                    </p>
                </section>
            </div>
            
            <div class="gray_rectangle CS_membro">
                <div class="CS_img">
                    <img src="img/Pandanna.jpeg" alt="Pandanna" width="150" height="150"/>
                </div>
                <section id="CS_text">
                    <h3 class="adjustable_H">
                        Pandanna
                    </h3>
                    <br>
                    <p class="adjustable_P">
                        Allevatrice di panda, makeup artist e programmatrice notturna. 
                        Java è il mio linguaggio preferito. 
                        Se avete prodotti o utenti da segnalare contattatemi dato che sono io a gestire i vostri dati.
                    </p>
                </section>
            </div>
        </main>
       
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
