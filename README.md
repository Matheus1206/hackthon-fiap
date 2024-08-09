# hackthon-fiap

Projeto desenvolvido com o objetivo de criar um sistema de processamento de pagamentos de operadoras de cartão de crédito.

## Quais foram as tecnologias e ferramentas utilizadas:
  - Java 17
  - Spring
  - Docker
  - Mongo
  - MySQL
  - Mockito
  - JUnit

## Desafios encontrados e soluções implementadas
Implementar um sistema de pagamentos para operadoras de cartão de crédito usando Spring apresenta diversos desafios. Primeiro, a segurança dos dados financeiros é crítica, exigindo criptografia robusta e conformidade com normas como PCI DSS. A integração com múltiplos gateways de pagamento traz complexidade, exigindo mecanismos para lidar com diferentes APIs e garantir transações seguras e eficientes. Além disso, a aplicação deve ser altamente disponível e escalável para suportar picos de transações. Gerenciar processos como autorização, captura e reembolsos requer uma lógica de negócios complexa, com tratamento de erros e conciliações financeiras em tempo real.

## Como utilizar o projeto:
1 - Efetue o clone do projeto para uma pasta de sua preferência

2 - Abra o terminal de sua preferência e navegue até o projeto

3 - Execute o comando "dokcer compose build" e depois "docker compose up"

4 - Abra um navegador da sua preferência e acesse a url: http://localhost:8080/swagger-ui/index.html para conseguir acessar o swagger onde estão especificadas todas as apis e chamadas.
