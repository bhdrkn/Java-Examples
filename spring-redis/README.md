Spring - Redis
=========================

Nothing special if you already have Docker installed:

Building the Redis Container
---------------------

    $ docker build -t spring-redis/redis .

Running the Redis Container
---------------------
To run this container:

    $ docker run --name redis -d spring-redis/redis

If you want to use Redis Container,
Modify the redis hostname in application.properties with the redis container hostname.
