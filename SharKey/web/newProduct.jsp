<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Pagina dedicata all'inserimento delle chiavi di gioco da mettere in vendita">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/newProduct.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/code.js"></script>
        <title> Nuovo Prodotto </title>
    </head>
    <body>
        <c:if test="${empty username}">
            <c:redirect url="login.jsp"/>
        </c:if>
            
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <main class="col-9">
            <section id="new_product" class="gray_rectangle">
                <h3 class="adjustable_H" id="page_title"> Vendi la tua Key! </h3>
                
                <br>
                
                <form method="POST" action="product" enctype="multipart/form-data" id="check_pro">
                    <label for="key" id="caratteriKey">Product Key (0/30)</label>
                    <input type="text" name="key" id="key" placeholder="XXXX-XXXX-XXXX-XXXX" class="filling_animation input">

                    <br>

                    <label for="nomep" id="caratteriNome">Titolo (0/50)</label>
                    <input type="text" name="nome" id="nomep" placeholder="Grezzo2" class="filling_animation input">

                    <br>

                    <label for="descrizione" id="caratteriDescrizione">Descrizione (0/300)</label>
                    <textarea rows="4" cols="20" name="descrizione" id="descrizione" placeholder="Gioco d'azione" class="filling_animation input"></textarea>

                    <br>

                    <label for="pegi">PEGI</label>
                    <select name="pegi" id="pegi" class="filling_animation input">
                        <option value="3">3</option>
                        <option value="7">7</option>
                        <option value="12">12</option>
                        <option value="16">16</option>
                        <option value="18">18</option>
                    </select>

                    <br>

                    <label for="dataUscita">Data di rilascio</label>
                    <input type="date" name="dataUscita" id="dataUscita" value="2023-01-01" min="1952-01-01" max="2024-12-31" class="filling_animation input">

                    <br>

                    <label for="prezzo">Prezzo</label>
                    <input type="number" name="prezzo" id="prezzo" placeholder="9.99" step="0.01" min="0.01" max="999.99" class="filling_animation input">

                    <br>

                    <label for="immagine" id="caratteriImmagine">Carica immagine</label>
                    <input type="file" name="immagine" id="immagine" accept="image/*" class="filling_animation input"/>

                    <br>

                    <input type="submit" value="Pubblica" class="gradientButton">       
                </form>
            </section>
        </main>
        
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
