<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="SharKey è un sito di vendita di chiavi di videogiochi online consumer-to-consumer.">
        <meta name="keywords" content="sharkey, key, videogame, game, buy, sell">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/index.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <title>SharKey</title>
    </head>
    <body>
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <main class="col-9">
            <section class="gray_rectangle">
                <form action="newReview.jsp" method="POST">
                    <h3 class="adjustable_H center"> Ti sei trovato bene? Lascia una recensione al nostro sito! </h3>
                    <input type="submit" value="Recensisci" class="gradientButton">
                </form>

                <br><br>

                <h3 class="adjustable_H center"> Le ultime recensioni </h3>

                <c:if test="${empty reviewList}"> 
                    <article class="inner_rectangle review">
                        <h3 class="adjustable_H rev_title">
                            bllxm - 5 Stelle
                        </h3>
                        <p class="adjustable_P">
                            Ho comprato Baldur's Gate 3 a 2 euri!!!
                            La grafica e' molto strana pero' sembra figo, vi aggiorno poi.
                        </p>
                        <br>
                        <p class="adjustable_P">
                            Da: xXThe_LolloXx | 2023-06-09 11:29
                        </p>
                    </article>

                    <article class="inner_rectangle review">
                        <h3 class="adjustable_H">
                            Molto rock - 3 Stelle
                        </h3>
                        <p class="adjustable_P">
                            Put your loving key out darling.
                            I'm beggin', beggin' youuuuuuu
                        </p>
                        <br>
                        <p class="adjustable_P">
                            Da: damianodeimaneskin | 2023-06-03 09:52
                        </p>
                    </article>

                    <article class="inner_rectangle review">
                        <h3 class="adjustable_H">
                            Eccezionale! - 4 Stelle
                        </h3>
                        <p class="adjustable_P">
                            Davvero eccezionale! Sito ben curato e intuitivo!
                        </p>
                        <br>
                        <p class="adjustable_P">
                            Da: Asdrubale | 2023-05-13 17:34
                        </p>
                    </article>

                    <article class="inner_rectangle review">
                        <h3 class="adjustable_H">
                            Fa schifo - 1 Stella
                        </h3>
                        <p class="adjustable_P">
                            Sito sgocciolante, fatto male, penzola proprio. E' LEZZO!
                        </p>
                        <br>
                        <p class="adjustable_P">
                            Da: Zeb89AceGamer | 2023-02-17 18:47
                        </p>
                    </article>

                    <article class="inner_rectangle review">
                        <h3 class="adjustable_H">
                            WOW STREPITOSO! - 1 Stella
                        </h3>
                        <p class="adjustable_P"> 
                            Ho ricevuto la mia chiave ISTANTANEAMENTE!
                        </p>
                        <br>
                        <p class="adjustable_P">
                            Da: marietto_45 | 2023-01-30 14:23
                        </p>
                    </article>
                </c:if>
            
            
                <c:if test="${not empty reviewList}">
                    <c:forEach items="${reviewList}" var="recensione">
                        <article class="inner_rectangle review">
                            <h3 class="adjustable_H"> ${recensione.getTitolo()} - ${recensione.getVoto()} Stelle </h3>
                            <p class="adjustable_P"> ${recensione.getTesto()} </p>
                            <br>
                            <p class="adjustable_P"> Da: ${recensione.getUsername()} | ${recensione.getData()} </p>
                        </article>
                    </c:forEach>
                </c:if>
            </section>
        </main>
        
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>

