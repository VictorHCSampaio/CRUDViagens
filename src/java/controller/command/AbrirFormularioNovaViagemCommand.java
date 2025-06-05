/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.command;

import Model.Viagem;
import factory.ViagemFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor
 */
public class AbrirFormularioNovaViagemCommand implements Command {
    private ViagemFactory viagemFactory;
    
    public AbrirFormularioNovaViagemCommand(ViagemFactory viagemFactory){
        this.viagemFactory = viagemFactory;
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Viagem viagem = viagemFactory.createEmptyViagem();
        request.setAttribute("viagem", viagem);
        request.setAttribute("acao", "CADASTRAR");
        String[] statusDisponiveis = {"PENDENTE", "CONFIRMADA", "CANCELADA", "REALIZADA"};
        request.setAttribute("statusDisponiveis", statusDisponiveis);
        request.getRequestDispatcher("/formularioViagem.jsp").forward(request, response);
    }
}
