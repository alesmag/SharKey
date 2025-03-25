<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="col-12">
            <ul class="col-12">
                <li class="${pageContext.request.requestURI eq '/SharKey/index.jsp' ? ' active' : ' filling_animation nav'}"><a href="home">Home</a></li>
                <c:if test="${not empty username}">
                    <li class="${pageContext.request.requestURI eq '/SharKey/catalog.jsp' ? ' active' : ' filling_animation nav'}"><a href="catalog">Catalogo</a></li>
                    <li class="${pageContext.request.requestURI eq '/SharKey/newProduct.jsp' ? ' active' : ' filling_animation nav'}"><a href="newProduct.jsp">Nuovo Prodotto</a></li>
                </c:if>       
                <li class="${pageContext.request.requestURI eq '/SharKey/aboutUs.jsp' ? ' active' : ' filling_animation nav'}"><a href="aboutUs.jsp">Chi siamo</a></li>
                <c:choose>
                    <c:when test="${empty username}"> <!-- TODO: se l'utente non è loggato -->
                        <li class="${pageContext.request.requestURI eq '/SharKey/login.jsp' ? ' active' : ' filling_animation nav'}"><a href="login.jsp">Login / Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="${pageContext.request.requestURI eq '/SharKey/personalArea.jsp' ? ' active' : ' filling_animation nav'}"><a href="user">Area Personale</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </body>
</html>
