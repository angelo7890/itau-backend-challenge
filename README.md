# 🧾 API de Transações – Desafio Itaú

Esta API foi desenvolvida como **desafio de backend do Itaú**. O objetivo era construir uma aplicação capaz de:

1. Receber transações financeiras.
2. Calcular estatísticas em tempo real das transações realizadas no último minuto.
3. Permitir a exclusão de transações.
4. Expor métricas e informações via **Spring Actuator**.
5. Ter documentação interativa via **Swagger/OpenAPI**.

## 🔹 Tecnologias Utilizadas

- Java 21  
- Spring Boot
- Spring Web / Spring Actuator  
- Spring Validation  
- Springdoc OpenAPI (Swagger)  
- JUnit 5 + Mockito + AssertJ  
- Maven
  
---

## 🔹 Requisitos do Desafio

O desafio pedia que a API fosse capaz de:

1. **Criar Transações** (`POST /transacao`)  
   - Receber valor (`double`) e data da transação (`OffsetDateTime`).  
   - Retornar **HTTP 201 Created**.  

2. **Excluir Transações** (`DELETE /transacao`)  
   - Apagar todas as transações armazenadas.  
   - Retornar **HTTP 200 OK**.  

3. **Gerar Estatísticas** (`GET /estatistica`)  
   - Resumir as transações do último minuto, calculando:
     - `count` (quantidade)  
     - `sum` (soma dos valores)  
     - `min` (menor valor)  
     - `max` (maior valor)  
     - `average` (média dos valores)  

4. **Documentação via Swagger** (`/swagger-ui.html`)  
5. **Expor métricas e informações do sistema** (`/actuator` endpoints)  

---

## 🔹 Implementação

- **Armazenamento:** as transações foram mantidas em uma **lista em memória**, considerando o escopo do desafio.  
- **Filtragem por tempo:** uso de `OffsetDateTime` e `isAfter()` para calcular transações válidas do último minuto.  
- **Estatísticas:** `DoubleSummaryStatistics` para gerar `sum`, `count`, `min`, `max` e `average`.  
- **Testes unitários:** com **JUnit 5, Mockito e AssertJ** para validar os cenários principais, incluindo lista vazia, múltiplas transações e exclusão.  
- **Swagger/OpenAPI:** documentação automática de endpoints com exemplos de request e response.  
- **Actuator:** endpoints `/health`, `/info` e `/metrics` configurados para monitoramento.  

---

## 🛠️ Como rodar o projeto 

### 🔹 Passo 1 – Clonar o repositório
```bash
git clone https://github.com/angelo7890/itau-backend-challenge.git
cd itau-backend-challenge
```

---

## 📖 Documentação da API
A documentação Swagger estará disponível em:  

👉 `http://localhost:8080/swagger-ui.html`  

---

## 👨‍💻 Autor
**Angelo Rodrigues**    
📎 [GitHub](https://github.com/angelo7890)
