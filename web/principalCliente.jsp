<%-- 
    Document   : principalCliente
--%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        request.setAttribute("mensagem", "Você não tem uma sessão válida de usuário do tipo cliente");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    } else {
%>
<%@page import="smdecommerce.usuario.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMDiscos</title>
    </head>
    <body>
        <h1>SMDiscos</h1>
        <hr/>
        <h3>Bem-vindo, <%= usuario.getNome()%>!</h3>
        <a href="Logout">Sair</a>
    </body>
</html>
<%
    }
%>