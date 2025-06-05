/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.command;
import dao.ViagemDAO;
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
public class DeletarViagemCommand implements Command {
    private ViagemDAO viagemDAO;
    
    public DeletarViagemCommand(ViagemDAO viagemDAO){
        this.viagemDAO = viagemDAO;
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String idParam = request.getParameter("id");
        String mensagem;
        
        if (idParam != null && !idParam.isEmpty()){
            try{
                int id = Integer.parseInt(idParam);
                Viagem v = new Viagem.ViagemBuilder()
                        .comId(id)
                        .build();
                
                viagemDAO.deletar(v);
                mensagem = "Viagem deletada com sucesso";
                request.setAttribute("mensagemSucesso", mensagem);
            }catch (NumberFormatException e){
                e.printStackTrace();
                mensagem = "ID de viagem não encontrado";
                request.setAttribute("mensagemErro", mensagem);
            }catch (ClassNotFoundException | SQLException e){
                e.printStackTrace();
                mensagem = "Erro ao deletar viagem " + e.getMessage();
                request.setAttribute("mensagemErro", mensagem);
            }
        }else {
            mensagem = "ID da viagem não fornecido para deleção.";
            request.setAttribute("mensagemErro", mensagem);
        }
        
        new ListaViagensCommand(viagemDAO).execute(request, response);
    }
}
