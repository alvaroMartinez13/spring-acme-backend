
# 🍀 Spring Acme Backend

Este es el backend de la aplicación Social Leaf, una red social donde los usuarios pueden compartir publicaciones, interactuar con comentarios y reacciones, y gestionar su red de seguidores.

## 📖 Video Presentación: 
- https://youtu.be/0aVjp4okyFU

## 🚀 Tecnologías Utilizadas
- **Spring Boot**
- **Spring Security** (Autenticación)
- **JWT (JSON Web Token)**
- **BCrypt** (Cifrado de contraseñas)
- **PostgreSQL** (Base de datos)
- **Postman** (Pruebas de API)

## 🛠 Instalación y Configuración

### 1️⃣ Clonar el repositorio:
```sh
git clone https://github.com/alvaroMartinez13/spring-acme-backend.git
cd spring-acme-backend
```
### 2️⃣ Configurar variables de entorno:
```sh
spring.application.name=spring-acme-backend

# Configuration database PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/spring-acme
spring.datasource.username=postgres
spring.datasource.password=campus2023
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Configuration JPA
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true

# Configuration JWT
jwt.secret=VGhpcyBpcyBhIG5vdGUgdG8gdGV4dC4gVGhpcyBlbmNvZGluZyBpcyBhIG5lZWQgdGV4dC4gVGhpcyBpcyBhIG5lZWQgdGV4dC4=
jwt.expiration=86400000 

#Spring Security 
spring.security.user.name=springAcme
spring.security.user.password=1234
```
### 3️⃣ Iniciar el servidor:
```sh
mvn spring-boot:run

```
## 🔑 Autenticación y Seguridad
El sistema usa JWT para autenticar a los usuarios. Los tokens deben enviarse en la cabecera Authorization en cada solicitud protegida.

## 📌 Funcionalidades Principales
- Registro e inicio de sesión de usuarios.
- Creación, edición y eliminación de publicaciones.
- Comentarios y reacciones a publicaciones.
- Seguimiento y dejar de seguir usuarios.
- Notificaciones en tiempo real.

## 📌 Enlaces Relacionados
**Repositorio Backend**
- Frontend: https://github.com/alvaroMartinez13/spring-acme-frontend
- Hash: dd1b46820f02d11770a663b40e39596c90ceff3a
- 
## 📒 Creado Por
- Alvaro martinez

