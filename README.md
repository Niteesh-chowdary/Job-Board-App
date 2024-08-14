# Job-Board-App
![Monolithic System Desing of JobBoard Application](https://github.com/Niteesh-chowdary/Job-Board-App/blob/main/SPRING%20BOOT%20MONOLITHIC%20DESIGN-2.png)

# TESTING instructions

1. git clone the repo
2. Install JDK22
3. Install Docker or Docker Desktop to run the docker-compose file to get the postgres server running.
4. cd into the cloned repo
5. Run docker compose up -d (to run docker containers in detach mode)
6. The above step will bring up the postgres server postgres admin services up
7. Open a web-browser navigate to localhost://5050
8. Click add new server
   1. In General, name = postgres
   2. Navigate to connection
   3. Host name/address = postgres
   4. username = niteesh
   5. password = 123456789
  
9. After creating the server. Create a database in the server.
10. databse name = jobBoardApp
11. now cd into src/main/java/com/Niteesh/JobBoard
12. run the command java JobBoardApplication.java
13. It will bring up the job board application
14. Install postman to test the server with various APIS
    
