[![CircleCI](https://circleci.com/gh/Kamil-IT/do-everyday/tree/master.svg?style=svg)](https://circleci.com/gh/Kamil-IT/do-everyday/tree/master)
# do-everyday
Getting and showing needed information every day

## Parts of project
- Weather(forecast is getting from Dark Sky, it's actual)
- Todo list
- API for Todo list
- Budget manager
- Spotify seacher
- In future more...

## How to run

### Use h2 database in memory

#### Windows:

Open cmd in web module directory('.../do-everyday/do-everyday-web') and run:

```mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=default```

#### Linux:

Open terminal in web module directory('.../do-everyday/do-everyday-web') and run:

```mvnw spring-boot:run -Dspring-boot.run.profiles=default```

### Use MySql database in memory

Scripts to configure MySQL(database and users) are in '.../do-everyday/do-everyday-web/src/main/scripts/*'. 
Information about MySQL users are keeping in '.../do-everyday/do-everyday-web/src/main/resources/application-mysql.yml'

#### Windows:

Open cmd in web module directory('.../do-everyday/do-everyday-web') and run:

```mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql```

#### Linux:

Open terminal in web module directory('.../do-everyday/do-everyday-web') and run:

```mvnw spring-boot:run -Dspring-boot.run.profiles=mysql```



