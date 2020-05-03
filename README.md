[![CircleCI](https://circleci.com/gh/Kamil-IT/do-everyday/tree/master.svg?style=svg)](https://circleci.com/gh/Kamil-IT/do-everyday/tree/master)
# do-everyday
Getting and presenting necessary information every day. 
This project uses friendly GUI which you can apply after running this project on your machine. The default main page is available on http://localhost:8080/.

## Parts of project
- Weather
- Todo list
- API for Todo list
- Budget manager
- Spotify searcher
- In future more...

## How to run

### Use h2 database in memory

#### Windows

Open cmd in web module directory(`.../do-everyday/do-everyday-web`) and run:

```mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=default```

#### Linux

Open terminal in web module directory(`.../do-everyday/do-everyday-web`) and run:

```mvnw spring-boot:run -Dspring-boot.run.profiles=default```

### Use MySql database

Scripts to configure MySQL(database and users) are in `.../do-everyday/do-everyday-web/src/main/scripts/*`. 
Information about MySQL users are keeping in '`.../do-everyday/do-everyday-web/src/main/resources/application-mysql.yml`

#### Windows

Open cmd in web module directory(`.../do-everyday/do-everyday-web`) and run:

```mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=mysql```

#### Linux:

Open terminal in web module directory(`.../do-everyday/do-everyday-web`) and run:

```mvnw spring-boot:run -Dspring-boot.run.profiles=mysql```

## Descripe parts of project

### Weather

This application takes information about actual weather conditions and forecast from [DarkSky](https://darksky.net/dev).
To convert geographical coordinates for particular country and city names the module exploits [OpenCage Geocoder](https://opencagedata.com/).

### Todo list

This module handles stuff referring to tasks and boards. 
It allows you to create e.g. a shopping list, manage task todo and more. 
All boards and tasks are stored in database (including the done ones).

### API for Todo list

Base API path is `api/v1/*`. It supports JSON format.

#### GET

Get all boards, tasks, priorities, tasks managers are in `single_name_of_stuff` e.g. get all bords GET `api/v1/board`
get by id boards, tasks, tasks managers are in `single_name_of_stuff/id` e.g. get all bords GET `api/v1/board/5`
get by name priorities are in `single_name_of_stuff/name` e.g. get all bords GET `api/v1/board/important`

#### POST

To create boards, tasks and tasks managers you have to add body which is in JSON format and compatible with boardDto, taskDto. TaskManagersDto 
e.g. POST `api/v1/single_name_of_stuff` id is creared by Hibernate

#### PUT

To update boards, tasks, tasks managers you have to add body which is in JSON format and compatible with boardDto, taskDto. TaskManagersDto e.g.
e.g. POST `api/v1/single_name_of_stuff/id`

#### DELETE

Delete boards, tasks, tasks managers e.g. DELETE `api/v1/single_name_of_stuff/id`

## Budget manager

Budget manager stores all your transfers in one place and can generate PDF documentation along with aggregates of spent money which are segregated as regards their currency

## Spotify searcher

Spotify searcher is used for search artist, albums and tracks which are available on spotify
