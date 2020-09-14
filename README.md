
## Bid Application with Spring Boot and Postgres  
### Overview  
Bid application server to make bids for items by logged in user.  
  
Bids will be successfully placed on following condition:  
  
1. If new auction then,
    - current_base_price > base_price + step_rate
2. If existing bid for a auction then,
	- current_base_price > last_bid_price + step_rate

### Scope
1. Fetch running auctions API 
2. Place the bid for running auctions 
3. Only logged in user can place the bid

### Technology in use
1. Spring Boot 2 
	* Spring framework for mvc development and container jars building RestFul End points
2. Postgres 10.14
	* Relational Database for storing user details and bid request data
3. In-memory Active MQ
	* Queue server in memory to store bid request for particular request and generate a request id for status. To keep user workflow asynchronous 
4. Docker
	* Containerised java application to easily deploy on Production / Dev environment

### Instruction to start application 
1. git clone <url>
2. cd bid-application/
3. docker-compose up -d -V --build

To Stop 
docker-compose down -v

Alternative :
1. mvn clean install -DskipTests -P prod
2. docker build -t bid-application .
3. docker build -t bid-postgres2 --file Dockerfile.postgres .
4. change image name in docker-compose.yml accordingly to local images

Once application running

import postman BidServer.postman_collection.json 

# API 
1. localhost:8080/api/auth/signup - POST - Register User 
2. localhost:8080/api/v1/auth/signin - POST - Login User
3. localhost:8080/api/v1/auctions/?status=RUNNING - GET - Running auctions 
4. localhost:8080/api/v1/auctions/<auction-id>/<item-code>/bid - POST - Submit a bid to auction id and item code

### Future scope improvements 
1. Create isolated Queue server ( RabbitMq ) for populating the bid request in a cluster 
2. Bid server should be separate application server to handle high load and scaling easily
3. Bid-application-server only reads real time using Server Side Events from database
4. Slave database to allow cluster of bid-application-server read only from slave db server and bid-server writes to master db server 
