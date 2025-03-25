<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Pagina dedicata all'inserimento delle recensioni sul sito">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/newReview.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/code.js"></script>
        <title> Nuova Recensione </title>
    </head>
    <body>
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <c:if test="${empty username}">
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <main class="col-9">
            <section id="new_review" class="gray_rectangle">
                <h3 class="adjustable_H" id="page_title"> Lasciaci una recensione! </h3>
                
                <br>
                
                <form method="POST" action="review" id="check_rev">
                    <label for="titolo" id="caratteriTitolo">Titolo (0/50)</label>
                    <input type="text" name="titolo" id="titolo" placeholder="Titolo" class="filling_animation input">

                    <br>

                    <label for="testo" id="caratteriRecensione">Recensione (0/300)</label>
                    <textarea rows="4" cols="20" name="testo" id="testo" placeholder="Gioco molto bello!" class="filling_animation input"></textarea>

                    <br>

                    <label for="voto">Voto</label>
                    <select name="voto" id="voto" class="filling_animation input">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>

                    <br>

                    <input type="submit" value="Pubblica" class="gradientButton">       
                </form>
            </section>
        </main>
            
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
