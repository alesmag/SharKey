<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5; url=login.jsp"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="Pagina dedicata alla visualizzazione degli errori riguardanti il login o il register">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/logregError.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <title> ${servtitle} </title>
    </head>
    <body>
        <c:if test="${not empty username}">
            <c:redirect url="user"/>
        </c:if>
        
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <main class="col-9">
            <section class="gray_rectangle">
                <h2 class="adjustable_H center"> ${msg} </h2>
                <br><br>
                <h3 class="adjustable_H center"> Verrai portato/a ${whereto} tra 5 secondi </h3>
            </section>
        </main>
            
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
