# 🎟️ API de Gestión de Tickets

## 📌 Descripción
Esta API permite la gestión de usuarios y tickets con autenticación JWT, filtrado avanzado y caché.  
Desarrollado con **Java + Spring Boot**, aplicando **buenas prácticas** de desarrollo.

---

## 🚀 Tecnologías Usadas
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA (H2)**
- **MapStruct (DTOs)**
- **Caffeine (Caché)**
- **JUnit + Testcontainers (Pruebas)**
- **Swagger (Documentación OpenAPI)**

---

## 🔧 Instalación y Ejecución

### **1️ Clonar el Repositorio**
```bash
git clone https://github.com/Mulato96/doublev-bk.git
cd doublev-bk
```

### **2️ Construir el Proyecto**
```bash
mvn clean install
```

### **3️ Ejecutar la Aplicación**
```bash
mvn spring-boot:run
```

📌 **Por defecto, la aplicación corre en el puerto `8081`**.  
Si necesitas cambiar el puerto, edita `src/main/resources/application.properties`:  
```properties
server.port: 8081
```

---

## 🔑 **Autenticación con JWT**

### **📌 Obtener Token de Autenticación**
Para acceder a los endpoints protegidos, primero debes autenticarte.

1️ **Hacer login** y obtener un **token JWT** enviando un JSON con email y contraseña:  
```bash
curl -X POST "http://localhost:8081/api/v1/auth/login" \
     -H "Content-Type: application/json" \
     -d '{ "email": "a@b.com", "password": "password" }'
```
📌 **Respuesta esperada:**  
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsIn..."
}
```

2️ **Usar el token en las peticiones HTTP**  
Agrega el **token JWT** en el **header `Authorization`**:  
```bash
curl -H "Authorization: Bearer <TU_TOKEN>" http://localhost:8081/api/usuarios
```

---


## 📝 **Acceder a la API**

### 📌 **Documentación Swagger**
Puedes explorar y probar los endpoints desde **Swagger UI**:  
🔗 **[http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)**

### 📌 **Base de Datos (H2 Console)**
Si necesitas consultar la base de datos en memoria **H2**, accede aquí:  
🔗 **[http://localhost:8081/h2-console](http://localhost:8081/h2-console)**  
📌 **Credenciales por defecto:**
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Usuario:** `sa`
- **Contraseña:** *(vacío)*

---


## ✅ **Pruebas Unitarias y de Integración**
📌 Para ejecutar las pruebas automatizadas, usa:  
```bash
mvn test
```
📌 **Tecnologías usadas:**  
✔ **JUnit 5**  
✔ **Mockito**  
✔ **Testcontainers (pruebas de integración)**  

---


## 📝 **Licencia**
Este proyecto está bajo la licencia **MIT**.

---


