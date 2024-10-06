# product-management

Home Assignment (Backend Engineer Intern) - I Gede Ari Wisnu Sanjaya


## Prasyarat

Sebelum memulai, pastikan Anda memiliki komponen berikut yang sudah diinstal di sistem Anda:

- **Java 17** atau lebih tinggi
- **Maven** 
- **Docker** (untuk menjalankan MySQL menggunakan kontainer

## Instalasi


### 1. Clone repositori / extract product-management-I Gede Ari Wisnu-Assigment-BE-Inter .zip

Clone repositori ini dengan perintah berikut:

```bash
git clone https://github.com/dewisnu/product-management.git
cd product-management
```

atau extract file extract product-management-I Gede Ari Wisnu-Assigment-BE-Inter .zip yang sebelumnya dikirimkan


### 2. Jalankan docker compose (bila menggunakan docker compose )(optional)
```bash
docker-compose up
```
kemudian sesuaikan datasource url , username dan password pada file <code>src/main/resources/application.properties</code>
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=user
spring.datasource.password=user_password
```

lalu jalankan perintah

```bash
mvn clean install
```
untuk menginstal dependensi,


kemudian jalankan

```bash
mvn spring-boot:run
```

untuk menjalankan aplikasi

setelah itu service dapat diakses pada http://localhost:8080,
untuk dokumentasi mengenai API bisa diakses pada http://localhost:8080/swagger-ui.html


### 2. Instalasi mysql (tanpa menggunakan docker compose dan docker file)
sebelumnya lakukan instalasi mysql sebagai database yang akan digunakan , bisa menggunakan 
perintah docker  seperti ini 

```bash
docker run --name mysql-database -e MYSQL_ROOT_PASSWORD=root_password -e MYSQL_DATABASE=productdb -e MYSQL_USER=user -e MYSQL_PASSWORD=user_password -p 3306:3306 -d mysql:latest
```

kemudian sesuaikan datasource url , username dan password pada file <code>src/main/resources/application.properties</code>
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=user
spring.datasource.password=user_password
```

lalu jalankan perintah 

```bash
mvn clean install
```
untuk menginstal dependensi,


kemudian jalankan 

```bash
mvn spring-boot:run
```

untuk menjalankan aplikasi

setelah itu service dapat diakses pada http://localhost:8080, 
untuk dokumentasi mengenai API bisa diakses pada http://localhost:8080/swagger-ui.html