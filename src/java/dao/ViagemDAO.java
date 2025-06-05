/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import util.Conexao;
import Model.Viagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 */
public class ViagemDAO {
    
    public void cadastrar(Viagem v) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO viagens (destino, data_partida, data_retorno, nome_cliente, email_cliente, status, valor_total, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
                PreparedStatement comando = con.prepareStatement(sql)){
            comando.setString(1, v.getDestino());
            comando.setDate(2, v.getDataPartida());
            comando.setDate(3, v.getDataRetorno());
            comando.setString(4, v.getNomeCliente());
            comando.setString(5, v.getEmailCliente());
            comando.setString(6, v.getStatus());
            comando.setDouble(7, v.getValorTotal());
            comando.setString(8, v.getObservacoes());
            comando.execute();
        }
    }
    
    public void deletar(Viagem v) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM viagens WHERE id = ?";
        try (Connection con = Conexao.getConexao();
            PreparedStatement comando = con.prepareStatement(sql)){
            comando.setInt(1, v.getId());
            comando.execute();
        }        
    }
    
    public void atualizar(Viagem v) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE viagens SET destino = ?, data_partida = ?, data_retorno = ?, nome_cliente = ?, email_cliente = ?, status = ?, valor_total = ?, observacoes = ? WHERE id = ?";
        try(Connection con = Conexao.getConexao();
                PreparedStatement comando = con.prepareStatement(sql)){
            comando.setString(1, v.getDestino());
            comando.setDate(2, v.getDataPartida());
            comando.setDate(3, v.getDataRetorno());
            comando.setString(4, v.getNomeCliente());
            comando.setString(5, v.getEmailCliente());
            comando.setString(6, v.getStatus());
            comando.setDouble(7, v.getValorTotal());
            comando.setString(8, v.getObservacoes());
            comando.setInt(9, v.getId());
            comando.execute();
        }
    }
    
    public Viagem consultarById(Viagem viagemInput) throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM viagens WHERE id = ?";
        Viagem v = null;
        try (Connection con = Conexao.getConexao();
                PreparedStatement comando = con.prepareStatement(sql)){
            comando.setInt(1, viagemInput.getId());
            try (ResultSet resultado = comando.executeQuery()){
                if (resultado.next()){
                    v = new Viagem.ViagemBuilder()
                            .comId(resultado.getInt("id"))
                            .comDestino(resultado.getString("destino"))
                            .comDataPartida(resultado.getDate("data_partida"))
                            .comDataRetorno(resultado.getDate("data_retorno"))
                            .comNomeCliente(resultado.getString("nome_cliente"))
                            .comEmailCliente(resultado.getString("email_cliente"))
                            .comStatus(resultado.getString("status"))
                            .comValorTotal(resultado.getDouble("valor_total"))
                            .comObservacoes(resultado.getString("observacoes"))
                            .build();                                                  
                }
            }
        }
        return v;
    }
    
    public List<Viagem> consultarTodos() throws ClassNotFoundException, SQLException{                
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select id, destino, data_partida, data_retorno, nome_cliente, email_cliente, status, valor_total, observacoes from viagens order by id");
        ResultSet resultado = comando.executeQuery();
        List<Viagem> todasViagens = new ArrayList<>();                        
        while (resultado.next()){
            Viagem v = new Viagem.ViagemBuilder()
                    .comId(resultado.getInt("id"))
                    .comDestino(resultado.getString("destino"))
                    .comDataPartida(resultado.getDate("data_partida"))
                    .comDataRetorno(resultado.getDate("data_retorno"))
                    .comNomeCliente(resultado.getString("nome_cliente"))
                    .comEmailCliente(resultado.getString("email_cliente"))
                    .comStatus(resultado.getString("status"))
                    .comValorTotal(resultado.getDouble("valor_total"))
                    .comObservacoes(resultado.getString("observacoes"))
                    .build();                                                   
                todasViagens.add(v);
            }
        con.close();
        return todasViagens;
    }
}
