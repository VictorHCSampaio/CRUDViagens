/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.command;
import dao.ViagemDAO;
import Model.Viagem;
import factory.ViagemFactory;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor
 */
public class SalvarViagemCommand implements Command {
    private ViagemDAO viagemDAO;
    private ViagemFactory viagemFactory;
    
    public SalvarViagemCommand(ViagemDAO viagemDAO, ViagemFactory viagemFactory){
        this.viagemDAO = viagemDAO;
        this.viagemFactory = viagemFactory;
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        Viagem viagem = viagemFactory.createViagemFromRequest(request);
        String acaoOriginal = request.getParameter("acaoOriginal");
        
        String mensagem;
        try{
            if(viagem.getId() == 0){
                viagemDAO.cadastrar(viagem);
                mensagem = "Viagem cadastrada com sucesso!";
            } else {
                viagemDAO.atualizar(viagem);
                mensagem = "Viagem atualizada com sucesso!";
            }
            request.setAttribute("mensagemSucesso", mensagem);
            new ListaViagensCommand(viagemDAO).execute(request, response);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao salver vaigem: " + e.getMessage());
            request.setAttribute("viagem", viagem);
            if ("CADASTRAR".equals(acaoOriginal)) {
                 request.setAttribute("acao", "CADASTRAR");
            } else {
                 request.setAttribute("acao", "EDITAR");
            }
            // Adicionar lista de status para o formulário em caso de erro
            String[] statusDisponiveis = {"PENDENTE", "CONFIRMADA", "CANCELADA", "REALIZADA"};
            request.setAttribute("statusDisponiveis", statusDisponiveis);
            request.getRequestDispatcher("/formularioViagem.jsp").forward(request, response);
        }
    }
    
}
