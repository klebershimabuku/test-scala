## Para implementar

O `PaymentsServiceImpl` já implementa uma versão do do serviço de pagamentos onde a operação falha para qualquer cliente que *não* tenha o ID *42*,
é necessário amarrar essa operação com o controller que já está lá.
Implemente algo que faça um auditoria assíncrona dessa operação de receber pagamentos: esta implementação deve usar o `logger` para escrever uma mensagem que o usuário autenticado (disponível no controller via o método `currentUser`)
Garanta que as respostas do webservice pareçam com os exemplos esperados abaixo.

## compilando e iniciando o container/app


    ./sbt
    > compile
    > container:start



## Happy path

    $ curl -i -XPOST http://localhost:8080/payments -d '{"customerId": 42, amount: 19.90}' -H "Content-type: application/json"


    HTTP/1.1 201 Created
    Content-Type: application/json;charset=UTF-8
    Content-Length: 21
    Server: Jetty(8.1.13.v20130916)
    
    {"status": "created"}


STDOUT deve conter `Received a payment of 19.90 for customer 42`


## com errors


    $ curl -i -XPOST http://localhost:8080/payments -d '{"customerId": 47, amount: 19.90}' -H "Content-type: application/json"


    HTTP/1.1 500 Internal server error
    Content-Type: application/json;charset=UTF-8
    Content-Length: 54
    Server: Jetty(8.1.13.v20130916)
    
    {"error": "Failed to process payment for customer 47"}
