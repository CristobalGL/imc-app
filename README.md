# IMC App
Aplicación Spring Boot para registro de usuarios y cálculo/registro de IMC.
- Backend + web con Thymeleaf
- REST API: GET /api/users/{username}/history

## Requisitos
- Java 17, Maven
- MySQL (o usar H2 para pruebas)

## Ejecutar local
1. Clonar repo
2. Ajustar src/main/resources/application.properties (credenciales DB)
3. mvn clean package
4. java -jar target/imc-app-0.0.1-SNAPSHOT.jar

## Deploy a railweb.app
1. Subir repositorio a GitHub (push).
2. En railweb.app, crear nueva app -> elegir 'Java / Spring Boot' -> apuntar a tu repo de GitHub.
3. Configurar variables de entorno:
   - SPRING_DATASOURCE_URL
   - SPRING_DATASOURCE_USERNAME
   - SPRING_DATASOURCE_PASSWORD
   - SERVER_PORT (si aplica)
4. Build (Maven) y deploy. railweb.app ejecutará `mvn package` y correrá el .jar.
5. En caso de usar MySQL externo, configurar firewall y host.

## Notas de seguridad
- Actualmente la contraseña se almacena en claro: **en producción usar hashing (BCrypt) y Spring Security**.