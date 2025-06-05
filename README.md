# Agencia de Viagens M2

## **Instruções de Uso**
- **Requisitos:** Ter NetBeans, Banco de Dados MySQL, XAMP, TomCat e SQL Connector.
- **Passos para execução:**
    1. **Clonar o repositório do GitHUB com o link:** `git clone https://github.com/VictorHCSampaio/Acelera-Jovem-Dev-Back-End.git` 
    2. **Abrir o projeto no NetBeans:** `https://github.com/VictorHCSampaio/CRUDViagens.git`
    3. **Executar o SQL no XAMP**
    4. **Executar o seguinte código no SQL:**
  ```json
       CREATE TABLE IF NOT EXISTS viagens (
          id INT AUTO_INCREMENT PRIMARY KEY,
          destino VARCHAR(255) NOT NULL,
          data_partida DATE,
          data_retorno DATE,
          nome_cliente VARCHAR(255) NOT NULL,
          email_cliente VARCHAR(255),
          status VARCHAR(50) DEFAULT 'PENDENTE',
          valor_total DECIMAL(10, 2) DEFAULT 0.0,
          observacoes VARCHAR(255),
          CONSTRAINT chk_status CHECK (status IN ('PENDENTE', 'CONFIRMADA', 'CANCELADA', 'REALIZADA'))
    );
  ```
    5. **Adicionar o TomCat e o SQLconnector no projeto a partir do NetBeans**
    6. **Acessar a web atráves da seguinte URL:** `http://localhost:8080/CRUDViagens`
    7. **Desfrute do sistema !!**
