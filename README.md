# Tyler Cadena's Revature PubHub Project

## Setting up database with Docker Compose
* Open the command line and run `docker-compose up -d`.
* Wait for few minutes for the database to initialize.

## Setting up database with Docker CLI
* Open the command line and run `docker container run -dit --rm --name dbsrv -p 3306:3306 -e 'MYSQL_ROOT_PASSWORD=synaodev' -e 'MYSQL_USER=webapp' -e 'MYSQL_PASSWORD=password' -e 'MYSQL_DATABASE=pubhub' mysql:latest`
