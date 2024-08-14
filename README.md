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
15. In postman Try the following commands

## Company
API request to GET the list of companies in JSON
```bash
GET http://localhost:8080/companies
```
API request to POST a company
```bash
POST http://localhost:8080/companies
1. Click Body
2. select raw
3. select json
4. paste the following in json body
{
    "name": "Microsoft",
    "description":"Software Industry company",
    "location":"WA"
}
5. click send
```
API request to PUT(update) a company
```bash
POST http://localhost:8080/companies
1. Click Body
2. select raw
3. select json
4. paste the following in json body
{
    "name": "updated-name",
    "description":"updated-description",
    "location":"updated-location"
}
5. click send
```
API request to DELETE a company (place a company id in the place of companyId to delete the company from database.
```bash
POST http://localhost:8080/companies/{companyId}
```
    
