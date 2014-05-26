## Para implementar

O {{PaymentsServiceImpl}} já implementa uma versão do do serviço de pagamentos onde a operação falha para qualquer cliente que não tenha o ID 42,
é necessário amarrar essa operação com o controller que já está lá.
Implemente algo que faça um auditoria assíncrona dessa operação de receber pagamentos: esta implementação simplesmente deve usar o {{logger}} para escrever uma mensagem que o usuário autenticado (usar o método {{currentUser}} já disponível no controller)
Garanta que as respostas do webservice pareçam com os exemplos esperados abaixo.

## compiling and starting


    ./sbt
    > compile
    > container:start



## Happy path

    $ curl -i -XPOST http://localhost:8080/payments -d '{"customerId": 42, amount: 19.90}'


    HTTP/1.1 201 Created
    Content-Type: application/json;charset=UTF-8
    Content-Length: 21
    Server: Jetty(8.1.13.v20130916)
    
    {"status": "created"}


STDOUT should contain "Received a payment of 19.90 for customer 42"


## Error handling


    $ curl -i -XPOST http://localhost:8080/payments -d '{"customerId": 47, amount: 19.90}'


    HTTP/1.1 500 Internal server error
    Content-Type: application/json;charset=UTF-8
    Content-Length: 54
    Server: Jetty(8.1.13.v20130916)
    
    {"error": "Failed to process payment for customer 47"}
