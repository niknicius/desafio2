
# PaymentAPI

Projeto simulador de operações de saque e depósito em uma conta de pagamentos.

Para fins de redução de código boillerplate, foram criadas classes reutilizáveis, que agrupam desde configurações, até processamentos compartilhados do código.
## Documentação

Após o start do sistema, basta acessar http://localhost:8080/swagger-ui para navegar na documentação da api.

Lista de endpoints e jsons para realizar operações

```bash
// GET all persons
GET http://localhost:8080/persons/
Accept: application/json

###

// GET all accounts
GET http://localhost:8080/accounts/
Accept: application/json

###

// GET one account
GET http://localhost:8080/accounts/1
Accept: application/json

###

// Create a new account
POST http://localhost:8080/accounts/1
Content-Type: application/json

{
  "person": {
    "id": 1
  },
  "withdrawLimit": 100,
  "accountType": 1
}

###

// Deposit money in the account
POST http://localhost:8080/accounts/1/deposit
Content-Type: application/json

{
  "amount": 4000
}

###

// GET all transactions
GET http://localhost:8080/accounts/1/transactions
Accept: application/json

###

// GET all transactions in a time interval
GET http://localhost:8080/accounts/1/transactions?startDate=2022-11-01&endDate=2022-11-04
Accept: application/json

###

// Withdraw money from the account
POST http://localhost:8080/accounts/1/withdraw
Content-Type: application/json

{
  "amount": 100
}
###

// Deactivate account
POST http://localhost:8080/accounts/1/deactivate
Content-Type: application/json

{}
###


```
## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/niknicius/desafio2.git
```

Entre no diretório do projeto

```bash
  cd desafio2
```

Build o jar

```bash
  mvn package
```

Execute a aplicação

```bash
  java -jar generatedjar.jar
```


## Funcionalidades

A aplicação consiste em 3 entidades básicas [Pessoa, Conta e Transação].

As seguintes funcionalidades foram implementadas.

- CRUD de Pessoa
- CRUD de Conta
- Depósito
- Saque (respeitando o limite diário)
- Lista de todas as transações
- Lista de transações entre periodo específico)
- Bloqueio de Conta
- Documentação Javadoc
- Testes

