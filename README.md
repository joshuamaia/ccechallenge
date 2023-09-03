# Desafio - Sistema Web para Processamento de Arquivos XML

Este é um sistema web composto por um Front-end SPA (Single Page Application) desenvolvido em Angular e um Back-end em Java Spring Boot. O sistema permite o upload de arquivos XML, o processamento desses arquivos e o armazenamento dos dados em um banco de dados. Também é possível recuperar dados consolidados por região.

## Requisitos

- Interface Web para upload de arquivos com extensão .xml.
- Utilização do tema Indigo do Angular Material.
- Exibição de um loader durante o envio do arquivo para informar ao usuário que o arquivo está sendo processado.
- Os arquivos XML devem seguir um formato específico (consulte os exemplos em anexo) e não precisam ser validados.
- Os dados confidenciais nos arquivos XML devem ser removidos ou substituídos por valores em branco antes do processamento.
- Upload de arquivos sequencialmente.
- Possibilidade de lidar com arquivos grandes.

## Tecnologias Utilizadas

### Front-end
- Angular 12+
- Angular Material 12+
- Typescript 4+
- RxJS 6+
- NodeJS 14+

### Back-end
- Spring Boot 2+
- Maven 3+
- JPA
- Hibernate

## Executando o Projeto

Siga estas etapas para executar o projeto:

1. Clone este repositório Git:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. Na raiz do projeto, execute

```bash
docker-compose up --build
```

- A aplicação será executada completamente, com backend, frontend e banco de dados.
- Ela estará disponível no endereço http://localhost:4200 do seu navegador.
