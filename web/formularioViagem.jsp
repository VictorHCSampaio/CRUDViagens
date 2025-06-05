<%-- 
    Document   : formularioViagem
    Created on : 3 de jun. de 2025, 08:53:12
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${acao == 'EDITAR' ? 'Editar' : 'Cadastrar'} Viagem</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/css.css">
</head>
<body>
    <h1>${acao == 'EDITAR' ? 'Editar' : 'Cadastrar'} Viagem</h1>

    <c:if test="${not empty mensagemErro}">
        <p style="color: red;">${mensagemErro}</p>
    </c:if>

    <form action="ManterViagem" method="POST">
        <input type="hidden" name="op" value="SALVAR">
        <input type="hidden" name="acaoOriginal" value="${acao}">
        
        <c:if test="${acao == 'EDITAR'}">
            <input type="hidden" name="id" value="${viagem.id}">
        </c:if>

        <table align="center">
            <tr>
                <td class="cor">Nome do Cliente:</td>
                <td><input type="text" name="nomeCliente" value="<c:out value='${viagem.nomeCliente}'/>" class="txt" required></td>
            </tr>
            <tr>
                <td class="cor">Email do Cliente:</td>
                <td><input type="email" name="emailCliente" value="<c:out value='${viagem.emailCliente}'/>" class="txt"></td>
            </tr>
            <tr>
                <td class="cor">Destino:</td>
                <td><input type="text" name="destino" value="<c:out value='${viagem.destino}'/>" class="txt" required></td>
            </tr>
            <tr>
                <td class="cor">Data de Partida:</td>
                <td><input type="date" name="dataPartida" value="<fmt:formatDate value='${viagem.dataPartida}' pattern='yyyy-MM-dd' />" class="txt" required></td>
            </tr>
            <tr>
                <td class="cor">Data de Retorno:</td>
                <td><input type="date" name="dataRetorno" value="<fmt:formatDate value='${viagem.dataRetorno}' pattern='yyyy-MM-dd' />" class="txt"></td>
            </tr>
            <tr>
                <td class="cor">Status:</td>
                <td>
                    <select name="status" class="txt" required>
                        <c:forEach var="s" items="${statusDisponiveis}">
                             <option value="${s}" ${s == viagem.status ? 'selected' : ''}><c:out value="${s}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="cor">Valor Total (R$):</td>
                <td><input type="text" name="valorTotal" value="<fmt:formatNumber value='${viagem.valorTotal}' type='number' minFractionDigits='2' maxFractionDigits='2' groupingUsed='false' />" class="txt" pattern="[0-9]+([,.][0-9]{1,2})?" placeholder="Ex: 1250.75"></td>
            </tr>
            <tr>
                <td class="cor">Observações:</td>
                <td><textarea name="observacoes" class="txt" rows="3"><c:out value='${viagem.observacoes}'/></textarea></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center;">
                    <input type="submit" value="${acao == 'EDITAR' ? 'Atualizar' : 'Cadastrar'}" class="botao">
                    <a href="ManterViagem?op=LISTAR" class="botao">Cancelar</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>