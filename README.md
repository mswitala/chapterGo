#running db:
    1: starting:
        docker-compose up -d --build dbpost
    2: check if it is running:
        docker ps
    3: if you need to stop db:
        docker kill dbpost
    4: if you need to remove db image:
        docker rm dbpost


#running Java app:
    1: 
        # it will run in a detached mode (-d)
        docker-compose up -d --build webjava
        # it will run in an interactive mode, so the logs will be print out directly to the console
        docker-compose up --build webjava
    2. 
        test when the java app is running:
            http://localhost:8082/sh -> you should get something like: "Java response: d3c9a7fd351a147b263c68495449ab8cbac5abf3fca272cccd732835125d7509"
        or if you want to test db endpoint (run db first):
            http://localhost:8082/db -> you should get something like: 
                a) "Java response: chapter_time_818" 
                b) and the response should be added to the file: /results/javaData.txt

#running Go app:
    1: 
        # it will run in a detached mode (-d)
        docker-compose up -d --build webgo
        # it will run in an interactive mode, so the logs will be print out directly to the console
        docker-compose up --build webgo
    2. 
        test when the java app is running:
            http://localhost:8081/sh -> you should get something like: "xxx"
        or if you want to test db endpoint (run db first):
            http://localhost:8081/db -> you should get something like: 
            a) "Java response: chapter_time_818" 
            b) and the response should be added to the file: /results/goData.txt

#running Gatling test:
    1:
        #in the docker-compose file uncomment correct endpoint that you want to tes (line 41-44)
    2:
        run gatling test
            docker-compose up --build gatling

#proxy settings
    - to be able to connect to db we need an external lib and for that Internet connection from your docker is required
    - if you are working behind a proxy: add/unncoment env proxy setting in go/dockerfile at line 13 and 14
    - for downloading the lib, unncoment line 17   

# less important but you might still need that
- stopping all containers:
    docker-compose stop
- connecting to the docker:
    docker exec -it webgo /bin/sh
    docker exec -it webjava /bin/sh
- connecting to the docker db:
    - docker exec -it dbpost bash
    - psql --host=dbpost --username=chaptertime --dbname=chaptertime
    - provide password: chaptertime
    - select count(*) from visitors;
- remove volumes:
    - docker-compose down --volumes