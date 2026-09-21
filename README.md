# ServiceFlow

Sistema web para gestão de ordens de serviço de uma assistência técnica.

O projeto foi pensado como um produto de pequeno/médio porte para portfólio, com um problema de negócio simples e um fluxo de trabalho que permite demonstrar fundamentos de backend Java, persistência, modelagem relacional, regras de negócio, segurança, testes e desenvolvimento de frontend.

## Status

Em desenvolvimento.

## Problema

Pequenas assistências técnicas podem ter dificuldade para controlar a entrada de equipamentos, diagnóstico, orçamento, aprovação do cliente e andamento do reparo quando essas informações ficam distribuídas em planilhas, mensagens ou registros manuais.

O ServiceFlow centraliza esse fluxo em uma aplicação web.

## Objetivo

Permitir o gerenciamento do atendimento desde o cadastro do cliente e do equipamento até a abertura, execução e encerramento de uma ordem de serviço, mantendo histórico das mudanças de status e informações do orçamento.

## Stack

### Backend

- **Java 25**
- **Spring Boot 4.1.1**
- **Spring Web**
- **Spring Data JPA**
- **JPA (Jakarta Persistence)**
- **Hibernate ORM**
- **Maven**
- **PostgreSQL**
- **H2 Database** para testes e cenários controlados

### Frontend

- **Angular**
- **TypeScript**
- **Angular Material**

### Planejadas

- Spring Validation
- Spring Security
- JWT
- Flyway
- OpenAPI/Swagger
- JUnit e testes de integração do ecossistema Spring

### Infraestrutura

- Docker
- Docker Compose

## Configuração inicial do Spring Initializr

O backend foi criado seguindo esta configuração inicial:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.1.1 |
| Group | `com.thiagoferreira` |
| Artifact | `ServiceFlow` |
| Package name | `com.thiagoferreira.ServiceFlow` |
| Packaging | JAR |
| Configuration | Properties (`application.properties`) |
| Java | 25 |

Dependências selecionadas no Spring Initializr:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- H2 Database

O PostgreSQL será o banco principal do projeto. O H2 será utilizado para testes e cenários específicos de desenvolvimento.

## Arquitetura

A aplicação será organizada como um **monólito modular em camadas**.

```text
Angular
   │
   │ HTTP / JSON
   ▼
Spring Boot
   │
   ├── Controller
   │       ↓
   ├── Service
   │       ↓
   ├── Repository
   │       ↓
   ├── Spring Data JPA
   │       ↓
   ├── JPA (Jakarta Persistence)
   │       ↓
   └── Hibernate ORM
           ↓
       PostgreSQL
```

### Responsabilidade das camadas

**Controller**

Recebe requisições HTTP, valida os DTOs, chama os serviços e monta as respostas da API.

**Service**

Concentra casos de uso, regras de negócio e fronteiras transacionais.

**Repository**

Utiliza Spring Data JPA para acesso e consultas ao banco.

**Entity / Domain**

Representa o modelo persistente utilizando Jakarta Persistence, com Hibernate ORM como implementação.

**DTO**

Define os contratos de entrada e saída da API sem expor diretamente as entidades JPA quando isso não for apropriado.

**Security**

Responsável por autenticação e autorização.

**Exception Handler**

Padroniza respostas de erro da API.

## Modelo principal

```text
Cliente
   │
   └── 1:N ── Equipamento
                 │
                 └── 1:N ── OrdemServico
                                  ├── N:1 ── Tecnico
                                  ├── 1:N ── Orcamento
                                  └── 1:N ── HistoricoStatusOS
```

Entidades principais:

- Usuario
- Cliente
- Tecnico
- Equipamento
- OrdemServico
- Orcamento
- HistoricoStatusOS

## Regras de negócio de destaque

A ordem de serviço possui um fluxo controlado de status:

```text
ABERTA
  ↓
EM_ANALISE
  ↓
AGUARDANDO_APROVACAO
  ↓
EM_EXECUCAO
  ↓
PRONTA
  ↓
ENTREGUE
```

Transições inválidas devem ser rejeitadas pelo backend.

Cada mudança de status gera um registro em `HistoricoStatusOS`.

A entidade `OrdemServico` utilizará `@Version` para controle otimista de concorrência.

Orçamentos possuirão versões e somente um orçamento pendente poderá existir para uma mesma ordem de serviço.

## JPA e Hibernate

O projeto utiliza **JPA (Jakarta Persistence) como especificação de persistência**, **Hibernate ORM como implementação** e **Spring Data JPA como abstração para repositories**.

Exemplo conceitual:

```text
Spring Data JPA
      ↓
     JPA
      ↓
  Hibernate ORM
      ↓
  PostgreSQL
```

Os relacionamentos serão mapeados explicitamente com anotações como `@ManyToOne` e `@OneToMany`, com carregamento `LAZY` como padrão para os relacionamentos definidos no domínio.

## Frontend

O frontend será desenvolvido separadamente em Angular e consumirá exclusivamente a API REST.

Principais telas previstas:

- Login
- Dashboard
- Clientes
- Cadastro/edição de clientes
- Detalhes do cliente
- Equipamentos
- Ordens de serviço
- Abertura de ordem de serviço
- Detalhes da ordem de serviço
- Diagnóstico e execução
- Orçamentos
- Histórico da ordem
- Técnicos
- Usuários

## Estrutura do repositório

```text
serviceflow/
├── backend/
├── frontend/
├── docs/
│   ├── requisitos/
│   └── arquitetura/
├── docker-compose.yml
├── .env.example
├── .gitignore
├── LICENSE
└── README.md
```

## Desenvolvimento

A implementação seguirá uma progressão incremental:

```text
Spring Initializr
      ↓
PostgreSQL / Docker
      ↓
JPA + Hibernate
      ↓
Entidades
      ↓
Repositories
      ↓
Services
      ↓
Controllers / REST
      ↓
CRUD
      ↓
Regras de negócio
      ↓
Exceções
      ↓
Segurança / JWT
      ↓
Testes
      ↓
Swagger / OpenAPI
      ↓
Angular
      ↓
Integração frontend + API
      ↓
Docker / publicação
```

## Documentação

A especificação detalhada do sistema fica em `docs/requisitos/`, incluindo requisitos funcionais e não funcionais, modelo de dados, relacionamentos JPA/Hibernate, API, telas do frontend, regras de negócio, testes e critérios de aceite.

## Licença

Este projeto está licenciado sob a licença MIT.