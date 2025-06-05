/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.command;
import dao.ViagemDAO;
import factory.ViagemFactory;
import Model.Viagem;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor
 */
public class AbrirFormularioEdicaoViagemCommand implements Command {
    private ViagemDAO viagemDAO;
    
    public AbrirFormularioEdicaoViagemCommand(ViagemDAO viagemDAO){
        this.viagemDAO = viagemDAO;
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        String idParam = request.getParameter("id");
        String mensagemErro = null;
        Viagem viagemParaEditar = null;
        
        if(idParam != null && !idParam.isEmpty()){
            try{
                int id = Integer.parseInt(idParam);
                Viagem viagemComId = new Viagem.ViagemBuilder()
                        .comId(id)
                        .build();                
                
                viagemParaEditar = viagemDAO.consultarById(viagemComId);
                
                if(viagemParaEditar == null){
                    mensagemErro = "Viagem com ID " + id + " não encontrado."; 
                }
            }catch (NumberFormatException e){
                e.printStackTrace();
                mensagemErro = "ID da viagem não fornecido para edição.";
            }catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
                mensagemErro = "Erro ao consultar viagem para edição " + e.getMessage();
            }
        }else {
            mensagemErro = "ID da viagem não fornecido para edição.";
        }
        
        if(mensagemErro != null){
            request.setAttribute("mensagemErro", mensagemErro);
            
            new ListaViagensCommand(viagemDAO).execute(request, response);
        }else {
            request.setAttribute("viagem", viagemParaEditar);
            request.setAttribute("acao", "EDITAR");
            
            String[] statusDisponiveis = {"PENDENTE", "CONFIRMADA", "CANCELADA", "REALIZADA"};
            request.setAttribute("statusDisponiveis", statusDisponiveis);
            request.getRequestDispatcher("/formularioViagem.jsp").forward(request, response);
        }
    }
    
}
