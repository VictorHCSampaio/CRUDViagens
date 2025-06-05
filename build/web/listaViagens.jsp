<%-- 
    Document   : listaViagens
    Created on : 3 de jun. de 2025, 08:52:09
    Author     : victor
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Viagem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Viagens</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/css.css">
    <%
            List<Viagem> viagens = (List<Viagem>) request.getAttribute("viagens");
        %>        
</head>
<center>
<body>
    <h1>Lista de Viagens Agendadas</h1>

    <c:if test="${not empty mensagemSucesso}">
        <p style="color: green;">${mensagemSucesso}</p>
    </c:if>
    <c:if test="${not empty mensagemErro}">
        <p style="color: red;">${mensagemErro}</p>
    </c:if>

    <p><a href="ManterViagem?op=NOVO" class="botao">Nova Viagem</a></p>

    <table border="1" class="style-class" align="center">                    
        <th class="cor">ID</th>
        <th class="cor">Cliente</th>
        <th class="cor">Destino</th>
        <th class="cor">Partida</th>
        <th class="cor">Retorno</th>
        <th class="cor">Status</th>
        <th class="cor">Valor Total</th>
        <th class="cor">Email</th>
        <th class="cor">Observações</th>
        <th class="cor">Ações</th>  
            <%for (Viagem prod : viagens) {%>                   
        <tr>                    
            <td class="cor"><%out.print(prod.getId());%></td>
            <td class="cor"><%out.print(prod.getNomeCliente());%></td>
            <td class="cor"><%out.print(prod.getDestino());%></td>
            <td class="cor"><%out.print(prod.getDataPartida());%></td>
            <td class="cor"><%out.print(prod.getDataRetorno());%></td>
            <td class="cor"><%out.print(prod.getStatus());%></td>
            <td class="cor"><%out.print(prod.getValorTotal());%></td>
            <td class="cor"><%out.print(prod.getEmailCliente());%></td>
            <td class="cor"><%out.print(prod.getObservacoes());%></td>
            
            <td class="center-img"><a href="ManterViagem?op=EDITAR&id=<%out.print(prod.getId());%>" class="botao">Editar</a></td>
            <td class="center-img"><a href="ManterViagem?op=DELETAR&id=<%out.print(prod.getId());%>" onclick="return confirm('Tem certeza que deseja deletar esta viagem?');" class="botao">Deletar</a></td>                    
        </tr>            
            <c:if test="${empty viagens}">
                <tr>
                    <td colspan="10" style="text-align: center;">Nenhuma viagem cadastrada.</td>
                </tr>
            </c:if>
                <%}%>        
    </table>
</body>
</center>
</html>