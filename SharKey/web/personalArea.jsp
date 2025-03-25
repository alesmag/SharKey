<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Pagina dedicata alla visualizzazione delle informazioni dell'utente al momento loggato">
        <meta name="keywords" content="sharkey, personalarea, personal, userpage">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/personalArea.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <title>Area Personale</title>
    </head>
    <body>
        <c:if test="${empty username}">
            <c:redirect url="login.jsp" />
        </c:if>
  
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>

        <main class="col-9">
            <section class="gray_rectangle">
                <h2 class="adjustable_H">Ciao ${username}!</h2>
                <br>
                <h3 class="adjustable_H">Le tue info:</h3>
                <p class="adjustable_P"> <b>E-mail:</b> ${email}</p>
                <p class="adjustable_P"> <b>Nome:</b> ${nome}</p>
                <p class="adjustable_P"> <b>Cognome:</b> ${cognome}</p>
                <br>

                <form action="logout" method="POST">
                    <input type="submit" value="Logout" class="gradientButton">
                </form>
            </section>
        </main>

        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
