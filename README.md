# Barberstown (Backend)

Barberstown es el backend de un sistema de gestión de citas para barberías, desarrollado con Spring Boot.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Instalación](#instalación)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Contribución](#contribución)
- [Licencia](#licencia)

## Descripción

Este proyecto proporciona una API REST para gestionar clientes, citas y otros recursos relacionados con la operación de una barbería.

## Características

- **Gestión de citas:** Creación, modificación y cancelación de citas.
- **Gestión de clientes:** Registro y consulta de información de clientes.
- **Autenticación y autorización:** (Opcional, ajusta según tu implementación)
- **Validación de datos:** Validación de formularios y datos de entrada.
- **Integración con frontend:** Soporte para CORS y comunicación con aplicaciones frontend[1].

## Tecnologías

- **Backend:** Spring Boot
- **Base de datos:** (Especifica aquí tu base de datos, por ejemplo: PostgreSQL, MySQL, H2, etc.)
- **Lenguaje:** Java
- **Herramientas:** Maven o Gradle (ajusta según tu proyecto)

## Instalación

1. Clona el repositorio:
git clone https://github.com/olarte91/barberstown.git

2. Accede al directorio del proyecto:
cd barberstown

3. Configura la base de datos:
- Crea y configura el archivo `application.properties`.
4. Ejecuta el proyecto:
- Con Maven:
  ```
  mvn spring-boot:run
  ```

## Uso

El backend está diseñado para ser consumido por una aplicación frontend (por ejemplo, Angular[1]). Proporciona endpoints REST para gestionar clientes, citas y otros recursos.

## Endpoints

*(Añade aquí los endpoints principales de tu API, por ejemplo:)*

- **GET /api/barberos**  - Lista todos los barberos
-**POST /api/barberos** - Crea un nuevo barbero 
- **GET /api/citas** – Lista todas las citas
- **POST /api/citas** – Crea una nueva cita
- **GET /api/clientes** – Lista todos los clientes
- **POST /api/clientes** – Crea un nuevo cliente



## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.