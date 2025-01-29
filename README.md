## 📝 Formulário para envio de currículo

Este teste técnico, proposto pela UGTSIC, é um mini projeto que implementa um formulário de inscrição para vagas.   
Possui validação dos campos, upload de arquivo (currículo), e integração com banco de dados PostgreSQL. Ele é renderizado utilizando a biblioteca Thymeleaf e estilizado com Bootstrap 5.

### 🛠️ Tecnologias utilizadas
🎨 **Frontend**
- Bootstrap 5: Biblioteca para estilização dos componentes.
- jQuery: Biblioteca para validação do formulário no lado do cliente.
- jQuery Validation Plugin: Biblioteca que extende o jQuery para a validação dos campos.

🗄️ **Backend**
- PostgreSQL: Banco de dados relacional utilizado para armazenar e gerenciar os dados recebidos.
- Spring Boot:
    - Thymeleaf: Biblioteca utilizada para a renderização de templates HTML no servidor.
    - Spring Starter Pack:
        - Starter Data JPA: Integração com o banco de dados PostgreSQL e mapeamento objeto-relacional (ORM).
        - Starter Web: Para a criação dos endpoints REST e integração com Spring MVC.
        - Starter Validation: Para validação dos dados em formulários e entidades.
        - Starter Mail: Para envio de E-mails.
    - Flyway Core: Utilizado para a o versionamento e migração do banco de dados.
    - PostgreSQL Driver: Para conexão com o banco de dados PostgreSQL.
    - Spring Dotenv: Para carregar as variáveis de ambiente a partir de um arquivo `.env` .

### 📋 Pré-Requisitos

Para rodar o projeto, é necessário que você tenha instalado:
- Java 17 (ou superior);
- Maven para gerenciamento das dependências;
- Docker para rodar o PostgreSQL em um contêiner; ou
- PostgreSQL (caso não queria usar o Docker).

### 🚀 Como Executar

**1. Configuração do Banco de Dados**
- Usando o Docker
    - Inicie um contêiner PostgreSQL com Docker:

```bash
docker run --name my-postgres -e POSTGRES_USER=docker -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=ugtsic -p 5432:5432 -d postgres
```

- Verifique se o docker ta rodando

```bash
docker ps
```

- Sem Docker
    - Instale o PostgreSQL localmente e crie um banco de dados chamado mydatabase.
    - Configure as credenciais no arquivo `application.properties` ou `application.yml`.

**2. Configuração do Projeto**

1. Clone este repositório

```bash
git clone https://github.com/MayraPerpetua/ugtsic-challenge.git
cd ugtsic-challenge
```

2. Configure o arquivo application.properties ou application.yml com as credenciais do banco de dados:
```env
spring.datasource.url=jdbc:postgresql://localhost:5432/ugtsic  
spring.datasource.username=docker  
spring.datasource.password=docker  
spring.jpa.hibernate.ddl-auto=update
```

**3. Execute o projeto**
1. Compile e execute o projeto com Maven

```bash
./mvnw spring-boot:run
```
2. Acesse o formulário localmente pelo navegador: http://localhost:8080

### 🎥 Demonstração

Assista ao vídeo demonstração através [desse link](https://drive.google.com/file/d/1cwriqNaQut3aYElw7E4Fmy7ruz4Xbr64/view?usp=sharing).
