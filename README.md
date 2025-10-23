# Plataforma de E-commerce Adaptável

Este é um protótipo de uma plataforma de e-commerce desenvolvida como parte de um Trabalho de Conclusão de Curso (TCC). O objetivo é fornecer uma solução escalável e personalizável para pequenos negócios, com foco em um catálogo de produtos, carrinho de compras, gerenciamento de pedidos e um painel administrativo básico.

## Stack de Tecnologias

- **Frontend:** React (com Vite)
- **Backend:** Spring Boot (com Maven)
- **Banco de Dados:** MongoDB

## Pré-requisitos

- Java 17
- Node.js 18+
- MongoDB (rodando localmente na porta padrão `27017`)

## Como Rodar o Backend

1.  **Navegue até a raiz do projeto.**
2.  **Execute o seguinte comando para iniciar o servidor Spring Boot:**

    ```bash
    ./mvnw spring-boot:run
    ```

O servidor backend estará disponível em `http://localhost:8080`.

## Como Rodar o Frontend

1.  **Navegue até o diretório `frontend`:**

    ```bash
    cd frontend
    ```

2.  **Instale as dependências do projeto:**

    ```bash
    npm install
    ```

3.  **Inicie o servidor de desenvolvimento:**

    ```bash
    npm run dev
    ```

O frontend estará disponível em `http://localhost:5173`.

## Endpoints da API

A documentação completa da API está disponível via Swagger UI no seguinte endereço:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Seed de Produtos

O banco de dados é populado com um conjunto inicial de produtos na primeira vez que o backend é iniciado. O arquivo de dados está localizado em `src/main/resources/data/products.json`.

## Configuração da URL da API no Frontend

A URL base da API no frontend está definida em `frontend/src/services/api.js`. Para alterar o endereço do backend, modifique a constante `API_BASE_URL` neste arquivo.

## Simulação de Usuário

Para fins de prototipagem, um `userId` fixo ("demo-user") é utilizado para simular um usuário logado. Este valor é armazenado no `localStorage` do navegador e é utilizado para operações de carrinho e pedidos. Em uma versão de produção, este sistema deve ser substituído por um mecanismo de autenticação seguro.
