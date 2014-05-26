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
