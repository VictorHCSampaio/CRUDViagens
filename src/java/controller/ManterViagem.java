/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import controller.command.*;
import dao.ViagemDAO;
import factory.ViagemFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor
 */

@WebServlet("/ManterViagem")
public class ManterViagem extends HttpServlet {
    
    private Map<String, Command> commands;
    private ViagemDAO viagemDAO;
    private ViagemFactory viagemFactory;
    
    @Override
    public void init() throws ServletException {
        super.init();
        viagemDAO = new ViagemDAO();
        viagemFactory = new ViagemFactory();
        commands = new HashMap<>();
        
        commands.put("LISTAR", new ListaViagensCommand(viagemDAO));
        commands.put("NOVO", new AbrirFormularioNovaViagemCommand(viagemFactory));
        commands.put("SALVAR", new SalvarViagemCommand(viagemDAO, viagemFactory));
        commands.put("EDITAR", new AbrirFormularioEdicaoViagemCommand (viagemDAO));
        commands.put("DELETAR", new DeletarViagemCommand(viagemDAO));
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/hmtl;charset=UTF-8");
        
        String op = request.getParameter("op");
        if(op == null || op.isEmpty()){
            op = "LISTAR";
        }
        
        Command command = commands.get(op.toUpperCase());
        
        try{
            if(command != null){
                command.execute(request, response);
            }else{
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Operação não encontrada: " + op);
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Ocorreu um erro no servidor " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet principal para manutenção de viagens";
    }
}
