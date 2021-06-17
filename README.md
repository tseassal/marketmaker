# marketmaker
This project is a simple marketmaker server application

Technical choices :
- In order to facilitate manual testing, the server accepts http and tcp connection. For good performance tcp is recommended
- tcp server is implemented with java NIO. This choice was made to allow a large ammounts of connections without big memory consumption (in comparison of threaded sockets)
- Reference prices are stored in memory. For parallel computation a shared memory (cache or database) should be implemented
- Clients are sending well formatted requests

Non Technical assumption :
- Request on unknown securityId returns a price = 0

Room of improvment
- Implement a cache for unit price. As caculation can take some time to be computed, unit price should be kept in memory
- Exception management for request format


How to build and run the server

At the root of the project run : 
- mvn clean install
- java -jar target/marketmaker-0.0.1-SNAPSH.jar com.example.marketmaker.MarketmakerApplication to run the application

The server listen to :
- 127.0.0.1:1111 for tcp connection
- 127.0.0.1:8080 for http connection
