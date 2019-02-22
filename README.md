# Paging and Sorting

Demo project showcasing how to paginate, sort and search using Spring Data. Backing stores are `PostgreSQL` and `ElasticSearch`.
This demo is containerized in docker to ease setting up the backing stores on a development machine.

## Quick Start

To be able to run this project locally, you need

1. Docker
2. Docker Compose (This is already bundled with Docker Desktop)
3. Git

Clone the repository:

```posh
> C:\> git clone https://github.com/juliuskrah/paging-and-sorting.git
> C:\> cd paging-and-sorting
```

Next, start up all the containers. I have provided a `docker-compose` file for this.

```posh
> C:\> docker-compose -f docker-compose.nobuild.yml up -d
```

Once all the containers are up navigate to [http://localhost:5050](http://localhost:5050) to view:

> You can run `docker-compose -f docker-compose.nobuild.yml ps` to ensure all containers are running

When you are done testing do a cleanup with:

```posh
> C:\> docker-compose -f docker-compose.nobuild.yml down --remove-orphans
```

Enjoy :wink: