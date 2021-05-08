# Guestbook Application

**Users' Details:**
| Username  | Password | Type |
| ------------- | ------------- |------|
| user1@gmail.com | test1  | Normal|
| admin1@gmail.com  | test2  | Admin |
| user2@gmail.com | test3 | Normal |


A Spring application using an H2 database for convenience.
Persistence can be added by installed MySQL, modifying
the dependencies in `pom.xml` by removing the commenting
lines of mysql dependency and tweaking
the settings in the `application.properties` file (example
settings are commented-out in that file).

Spring version 2 and Java 11

Run with Maven:
1. The GuestBookAppApplication class' main method or
2. $ mvn spring-boot:run

The app will then be available on port 3297, that is
http://localhost:3297/
