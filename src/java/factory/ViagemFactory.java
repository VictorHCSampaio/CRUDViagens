/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import Model.Viagem;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author victor
 */
public class ViagemFactory {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            java.util.Date parsed = dateFormat.parse(dateStr);
            return new Date(parsed.getTime());
        } catch (ParseException e) {
            System.err.println("Erro ao parsear data: " + dateStr + " - " + e.getMessage());
            return null; 
        }
    }

    public Viagem createViagemFromRequest(HttpServletRequest request) {
        Viagem.ViagemBuilder builder = Viagem.builder();

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                builder.comId(Integer.parseInt(idParam));
            } catch (NumberFormatException e) {
                System.err.println("ID inválido fornecido para factory: " + idParam);
            }
        }

        builder.comDestino(request.getParameter("destino"))
               .comDataPartida(parseDate(request.getParameter("dataPartida")))
               .comDataRetorno(parseDate(request.getParameter("dataRetorno")))
               .comNomeCliente(request.getParameter("nomeCliente"))
               .comEmailCliente(request.getParameter("emailCliente"))
               .comStatus(request.getParameter("status")); 
        
        String valorTotalParam = request.getParameter("valorTotal");
        if (valorTotalParam != null && !valorTotalParam.isEmpty()) {
            try {
                builder.comValorTotal(Double.parseDouble(valorTotalParam.replace(",", ".")));
            } catch (NumberFormatException e) {
                System.err.println("Valor total inválido para factory: " + valorTotalParam);
                builder.comValorTotal(0.0); 
            }
        }
        builder.comObservacoes(request.getParameter("observacoes"));
        
        return builder.build();
    }
    
    public Viagem createEmptyViagem() {
        
        return Viagem.builder().build(); 
    }
}
