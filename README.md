Ordenaris Risk Engine
Motor de Evaluación de Riesgo Crediticio Empresarial para Ordenaris Capital.

Este proyecto fue creado como parte de una prueba técnica enfocada en el desarrollo backend. Utiliza tecnologías modernas y buenas prácticas para construir una base sólida de servicios y lógica empresarial.


Tecnologías usadas:
Java 21

Spring Boot 3

Spring Data JPA

Maven

Github (repositorio)

RESTAPI

H2 Database(incluida por defecto)

Liquibase (gestión de versiones de la base de datos).

Swagger para pruebas

Instalación Clona el repositorio:

    git clone https://github.com/GeorgeVega18/Ordenaris_Capital.git
    cd Ordenaris_Capital
    
    Compila el proyecto:
    
    ./mvnw clean install
    
    Ejecuta la aplicación:
    
    ./mvnw spring-boot:run
    
    Acceder a la aplicación:
    
    Swagger UI: http://localhost:8080/swagger-ui.html
    
    H2 Console: http://localhost:8080/h2-console
    
    JDBC URL: jdbc:h2:file:~/riskengine-db
    
    User: sa
    
    Password: (vacío)
    

Este módulo tiene como propósito demostrar habilidades técnicas en el desarrollo de servicios backend, incluyendo diseño de APIs, manejo de dependencias, pruebas automatizadas y estructura de proyecto profesional.

Características Principales:

Todos los repositorios extienden JpaRepository proporcionando:

-Operaciones CRUD básicas
-Queries personalizadas con @Query
-Métodos de búsqueda por convención de nombres
-Soporte para paginación y ordenamiento
     


Ejemplos de Uso con cURL:

# Evaluar empresa con riesgo bajo

curl -X 'GET' \
  'http://localhost:8080/riskengine?claveEmpresa=EMP001&montoSolicitado=1000000&producto=LINEA_OPERATIVA&fechaSolicitud=2025-10-28' \
  -H 'accept: */*'

# Evaluar empresa con riesgo alto
curl -X 'GET' \
  'http://localhost:8080/riskengine?claveEmpresa=EMP002&montoSolicitado=2500000&producto=CREDITO_REVOLVENTE&fechaSolicitud=2025-10-28' \
  -H 'accept: */*'

# Evaluar empresa rechazada
curl -X 'GET' \
  'http://localhost:8080/riskengine?claveEmpresa=EMP003?montoSolicitado=400000&producto=LINEA_OPERATIVA&fechaSolicitud=2025-10-28' \
  -H 'accept: */*'



Autor Proyecto desarrollado por: Jorge Vega