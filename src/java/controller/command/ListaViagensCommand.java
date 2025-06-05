/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.command;
import dao.ViagemDAO;
import Model.Viagem;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

/**
 *
 * @author victor
 */
public class ListaViagensCommand implements Command {
    private ViagemDAO viagemDAO;
    
    public ListaViagensCommand(ViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {        
        List<Viagem> viagens = viagemDAO.consultarTodos();
        request.setAttribute("viagens", viagens);
        request.getRequestDispatcher("listaViagens.jsp").forward(request, response);
    }
}
