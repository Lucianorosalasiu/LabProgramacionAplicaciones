# Proyecto distribuido Java #
_Desarrollo de una aplicacion distribuida utilizando variedad de tecnologias pertenecientes al ecosistema de desarrollo de Java._

### 🫂Responsables:
* [Alexis Velazquez](https://github.com/AVelazquez97)
* [Diego Villalba](https://github.com/diegoVillalba5)
* [Ignacio Fernández](https://github.com/ignfer)
* [Luciano Rosa](https://github.com/cocoamaker)

### ✔️Features:

### 💻Tech Stack:
* [Java 11](https://docs.oracle.com/javase/8/docs/api/) Como lenguaje de desarrollo principal del equipo.
* [Maven](https://maven.apache.org/) Para gestionar dependendencias y versiones a lo largo del desarrollo.
* [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) Para el desarrollo de Interfaces de usuario incrementando la accesibilidad y la facilidad de uso.
* [MySQL](https://www.mysql.com/) Para la gestion y el manejo de la base de datos.
* [JPA](https://www.oracle.com/technical-resources/articles/java/jpa.html) Para el mapeo de objetos en la base de datos dandoles persistencia.
* [EclipseLink](https://eclipse.dev/eclipselink/#jpa) Como proveedor de servicios de persistencia.
* [Tomcat](https://tomcat.apache.org/) Como webserver.
* [JSP](https://en.wikipedia.org/wiki/Jakarta_Server_Pages) Para lograr Server-Side Rendering de datos dinamicos obtenidos por el servidor.
* [Servlets](https://www.geeksforgeeks.org/introduction-java-servlets/) Manejo y control de peticiones y respuestas HTTP.
* [Bootstrap](https://getbootstrap.com/) Estilos pre-diseñados logrando un front end moderno y responsive en un corto periodo de tiempo.

### 🛠️Requisitos para la ejecucion
* JRE 11 o superior

  ```sudo apt update```
  
  ```sudo apt install openjdk-11-jre```
  
* MySQL v5.7 o superior

  ```sudo apt install mysql-server-5.7```
  
* Crear una base de datos llamada turismouy

  ``` CREATE DATABASE turismouy;```
  
* Crear un usuario MySQL llamado tecnologo en el localhost

  ```CREATE USER tecnologo@localhost IDENTIFIED BY "tecnologo";```
  
* Brindarle permisos

  ```GRANT ALL PRIVILEGES ON turismouy.* TO tecnologo@localhost WITH GRANT OPTION;```
  
 
