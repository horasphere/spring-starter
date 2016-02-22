# spring-starter


## Includes

- Actuator (system metrics)
- MyBatis persistence
- JPA for readmodel
- Flyway migrations
- Spring boot devtools
- Handlebars

## Template engine [Handlebars](https://github.com/allegro/handlebars-spring-boot-starter)

- Large community
- Efficient
- Can use same template engine on client
- Logic less templates

## Maven run with `mysql`

```
mvn spring-boot:run -Drun.arguments=--spring.profiles.active=mysql,--spring.datasource.url=jdbc:mysql://127.0.0.1/dbname,--spring.datasource.username=username,--spring.datasource.password=pass"
```