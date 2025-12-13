## Desafio: Sistema de Agendamentos Hospitalares.

>  Descrição: 

Agendamento de consultas hospitalares permitindo registrar data, horário, médico, paciente, status e descrição do atendimento.
O sistema organiza os compromissos, evita conflitos de horário e facilita o acompanhamento das consultas marcadas.

## 💻 Tecnologias Utilizadas

* *Linguagens:* [Java]
* *Frameworks/Bibliotecas:* [Spring Boot]
* *Banco de Dados:* [PostgreSQL,]
* *Outras Ferramentas:* [Git/GitHub]

---

## ✨ Funcionalidades

* ✅ *CRUD de Paciente.
* ✅ *CRUD de Medicos.
* ✅ *CRUD de agendamento de consultas.
* ✅ *Agendamento de Consultas:* Permitir que pacientes agendem consultas.
* ✅ *Cadastro de Entidades:* Gerenciamento de pacientes, médicos e especialidades.
* ✅ *Cancelamento/Remarcação:* Funcionalidade para modificar agendamentos.
* ✅ *Relatórios (Opcional):* Geração de listas de agendamentos por médico/dia.

---
**Padroes de arquitetura**

📄 PacienteRequest — DTO de Cadastro de Paciente

O record PacienteRequest representa os dados necessários para cadastrar um paciente no sistema.
Ele utiliza anotações de validação do Jakarta Validation para garantir a integridade das informações recebidas.

🔍 Explicação das Validações

✔️ Nome
@NotBlank: impede nome vazio ou composto apenas por espaços.

Mensagem exibida: "Nome é obrigatório".

✔️ Cpf
@NotBlank: o campo não pode ser vazio.

@Pattern(regexp = "\d{11}"): exige que o CPF tenha exatamente 11 dígitos numéricos.

Mensagem exibida: "CPF deve ter 11 dígitos".

✔️ Telefone
@NotBlank: o telefone é obrigatório e não pode ficar vazio.

Mensagem exibida: "Telefone é obrigatório".

## 🚀 Como Executar o Projeto

### Pré-requisitos

O que é necessário ter instalado na máquina antes de começar.

* [ Java versao 21 ]
* [ banco de dados PostgreSQL,Maven 3.6]
* [ IDE Eclipse]

### Acessar a aplicaçao

http://localhost:8080

### Instalação e Inicialização

1.  *Clone o repositório:*
    bash
    git clone [https://github.com/Samuel-Lorran/Desafio-Agendamentos-Hospitalares.git)
    cd Desafio-Agendamentos-Hospitalares
    

2.  *Configuração do Banco de Dados:*

    * Configure as credenciais no arquivo de propriedades (src/main/resources/application.properties).

3.  *Instale as dependências e rode o projeto:*
    bash
    mvn install
    mvn spring-boot:run
    

4.  *Acesso:* O serviço estará disponível em http://localhost:[PORTA:8080]


## ✒️ Equipe

Seu nome e como entrar em contato.

| Nome           | GitHub                                                      |
| :------------- | :---------------------------------------------------------- |
| *Samuel Lorran* | https://github.com/Samuel-Lorran |
| *Antonio Lucielton* | https://github.com/eltinholopesplay-cyber |
| *Jairo Rocha* | https://github.com/jairsantos123 |



## 📄 Licença

Licenca para uso livre educacional


## Relatório técnico do projeto
**link:https://docs.google.com/document/d/1USpNzOM0-ZeN3AhXooiyaiZpbeuwT-GA8uzzmqUreJs/edit?usp=sharing
