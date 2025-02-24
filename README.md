# API REST con Spring Boot

Este proyecto es una API REST desarrollada con Spring Boot.

## Requisitos

- Java 17 o superior
- Maven 3.x
- Base de datos (MySQL, PostgreSQL, H2, etc.)

## Instalación

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/usuario/repo.git
   ```
2. Ingresar al directorio del proyecto:
   ```sh
   cd nombre-del-proyecto
   ```
3. Construir el proyecto con Maven:
   ```sh
   mvn clean install
   ```

## Configuración

Modificar el archivo `application.properties` o `application.yml` en `src/main/resources/` según la base de datos y configuración deseada.

### Base de Datos
- `RDS-END-POINT` - Url de base de datos PostgreSQL.
- `RDS-PORT` - Puerto de base de datos.
- `DATABASE-NAME` - Nombre de base de datos .
- `RDS-USERNAME` - Nombre de usuario de base de datos .
- `RDS-PASSWORD` - Contrasena usuario de base de datos .

### AWS
- `REGION` - Region en la que se encuentra el servicio de SES .
- `SECRET-AWS` - Secreto de AWS.
- `ACCESS-AWS` - Key de AWS.
  
Ejemplo para MySQL en `application.properties`:
```properties
#database
spring.datasource.url=jdbc:postgresql://${RDS-END-POINT}:${RDS-PORT}/${DATABASE-NAME}
spring.datasource.username=${RDS-USERNAME}
spring.datasource.password=${RDS-PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

#aws
cloud.aws.region.static=${REGION:us-east-1}
cloud.aws.credentials.secret-key=${SECRET-AWS}
cloud.aws.credentials.access-key=${ACCESS-AWS}
```

## Ejecución

Para ejecutar la aplicación, usa el siguiente comando:
- Configure previamente las variables de entorno o cambielas en el archivo  `application.properties`.
```sh
mvn spring-boot:run
```

## Endpoints

### Autenticación
- `POST /api/auth/login` - Autenticación de usuarios.
- `POST /api/auth/register` - Registro de nuevos usuarios.
  
### Otros endpoints
Puedes revisar la documentación completa con Swagger accediendo a:
```
http://localhost:8080/swagger-ui.html
```

## Tecnologías utilizadas

- Spring Boot
- Spring Data JPA
- Spring Security
- Jwt
- Swagger para documentación
- Base de datos relacional (PostgreSQL (AWS RDS))
- Email Service (AWS SES)
