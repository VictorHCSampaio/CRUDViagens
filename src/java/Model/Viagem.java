/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author victor
 */
public class Viagem {
    
    private int id;
    private String destino;
    private Date dataPartida;
    private Date dataRetorno;
    private String nomeCliente;
    private String emailCliente;
    private String status;
    private double valorTotal;
    private String observacoes;

    // Construtor padrão (pode ser usado pelo DAO) e construtor privado para o Builder
    public Viagem() { // Construtor padrão
    }

    private Viagem(ViagemBuilder builder) {
        this.id = builder.id;
        this.destino = builder.destino;
        this.dataPartida = builder.dataPartida;
        this.dataRetorno = builder.dataRetorno;
        this.nomeCliente = builder.nomeCliente;
        this.emailCliente = builder.emailCliente;
        this.status = builder.status;
        this.valorTotal = builder.valorTotal;
        this.observacoes = builder.observacoes;
    }

    // Getters
    public int getId() { return id; }
    public String getDestino() { return destino; }
    public Date getDataPartida() { return dataPartida; }
    public Date getDataRetorno() { return dataRetorno; }
    public String getNomeCliente() { return nomeCliente; }
    public String getEmailCliente() { return emailCliente; }
    public String getStatus() { return status; }
    public double getValorTotal() { return valorTotal; }
    public String getObservacoes() { return observacoes; }

    // Setters (mantidos para compatibilidade com o DAO atual)
    public void setId(int id) { this.id = id; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setDataPartida(Date dataPartida) { this.dataPartida = dataPartida; }
    public void setDataRetorno(Date dataRetorno) { this.dataRetorno = dataRetorno; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
    public void setStatus(String status) { this.status = status; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }


    public static ViagemBuilder builder() {
        return new ViagemBuilder();
    }

    public static class ViagemBuilder {
        private int id;
        private String destino;
        private Date dataPartida;
        private Date dataRetorno;
        private String nomeCliente;
        private String emailCliente;
        private String status = "PENDENTE"; // Valor padrão
        private double valorTotal;
        private String observacoes;

        public ViagemBuilder() {}

        public ViagemBuilder comId(int id) {
            this.id = id;
            return this;
        }
        // ... (outros métodos com... como definidos anteriormente) ...
        public ViagemBuilder comDestino(String destino) { this.destino = destino; return this; }
        public ViagemBuilder comDataPartida(Date dataPartida) { this.dataPartida = dataPartida; return this; }
        public ViagemBuilder comDataRetorno(Date dataRetorno) { this.dataRetorno = dataRetorno; return this; }
        public ViagemBuilder comNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; return this; }
        public ViagemBuilder comEmailCliente(String emailCliente) { this.emailCliente = emailCliente; return this; }
        public ViagemBuilder comStatus(String status) {
            if (status != null && !status.isEmpty()) { this.status = status; }
            return this;
        }
        public ViagemBuilder comValorTotal(double valorTotal) { this.valorTotal = valorTotal; return this; }
        public ViagemBuilder comObservacoes(String observacoes) { this.observacoes = observacoes; return this; }


        public Viagem build() {
            return new Viagem(this);
        }
    }}
