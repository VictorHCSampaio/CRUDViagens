<%-- 
    Document   : error
    Created on : 3 de jun. de 2025, 08:57:24
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Erro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/css.css">
</head>
<body>
    <h1>Ocorreu um Erro</h1>
    <p>Desculpe, um erro inesperado ocorreu.</p>
    <p><strong>Mensagem:</strong> ${mensagemErro}</p> <c:if test="${pageContext.exception != null}">
        <hr>
        <h2>Detalhes do Erro (Debug):</h2>
        <pre>
        <% 
        if (exception != null) {
            java.io.StringWriter sw = new java.io.StringWriter();
            java.io.PrintWriter pw = new java.io.PrintWriter(sw);
            exception.printStackTrace(pw);
            out.print(sw.toString());
        } else if (request.getAttribute("javax.servlet.error.exception") != null) {
            Throwable e = (Throwable) request.getAttribute("javax.servlet.error.exception");
            java.io.StringWriter sw = new java.io.StringWriter();
            java.io.PrintWriter pw = new java.io.PrintWriter(sw);
            e.printStackTrace(pw);
            out.print(sw.toString());
        }
        %>
        </pre>
    </c:if>
    
    <p><a href="${pageContext.request.contextPath}/ManterProduto?op=LISTAR" class="botao">Voltar para a Lista de Produtos</a></p>
</body>
</html>
