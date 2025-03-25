<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Alessio Giuseppe Muggittu, Michele Cocco, Anna Chiara Mameli">
        <meta name="description" content="pagina di login o di registrazione al sito">
        <meta name="keywords" content="sharkey, login, register, signin, signup">
        <meta name="robots" content="noindex">
        <link rel="canonical" href="http://localhost:8080/SharKey/login.jsp">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <link rel="icon" type="image/gif" href="img/SharKey_Logo.png">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/code.js"></script>
        <title>Login</title>
    </head>
    <body>
        <c:if test="${not empty username}">
            <c:redirect url="user"/>
        </c:if>
        
        <%@include file="common/header.jspf"%>
        <jsp:include page="common/nav.jsp"/>
        
        <main class="col-9">
            <div id="log-reg">
                <section id="login">
                    <h2>Login</h2>
                    <form  method="POST" action="login" id="check_log">
                        <label for="username" id="caratteriUs">Username (0/50)</label>
                        <input type="text" name="username" id="username" placeholder="Username" class="filling_animation input">
                        <br>
                        <label for="password" id="caratteriPsw">Password (0/50)</label>
                        <input type="password" name="password" id="password" placeholder="Password" class="filling_animation input">
                        <br>
                        <input type="submit" value="Accedi" class="gradientButton">
                    </form>
                </section>
                <section id="register">
                    <h2>Register</h2>
                    <form action="register" method="POST" id="check_re">
                        <label for="usernamer" id="caratteriUsRe">Username (0/50)</label>
                        <input type="text" name="username" id="usernamer" placeholder="Username" class="filling_animation input">
                        <br>
                        <label for="email" id="caratteriEm">E-mail (0/50)</label>
                        <input type="text" name="email" id="email" placeholder="E-mail" class="filling_animation input">
                        <br>
                        <label for="passwordr" id="caratteriPswr">Password (0/50)</label>
                        <input type="password" name="password" id="passwordr" placeholder="Password" class="filling_animation input">
                        <br>
                        <label for="nome" id="caratteriNom">Nome (0/50)</label>
                        <input type="text" name="nome" id="nome" placeholder="Nome" class="filling_animation input">
                        <br>
                        <label for="cognome" id="caratteriCogn">Cognome (0/50)</label>
                        <input type="text" name="cognome" id="cognome" placeholder="Cognome" class="filling_animation input">
                        <br>
                        <input type="submit" value="Registrati" class="gradientButton">
                    </form>
                </section>
            </div>
        </main>
        
        <%@include file="common/aside.jspf"%>
        <%@include file="common/footer.jspf"%>
    </body>
</html>
