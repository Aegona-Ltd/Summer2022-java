# CONTACT MANAGEMENT
## SETUP
- STEP 1: pull git `git clone https://github.com/Aegona-Ltd/Summer2022-java.git`
- STEP 2: Open folder `[TranQuocHieu]-Java-BaiTap2` with application (EclipseIDE, IntelliJ) installed Spring Initializr
- STEP 3: Upload Maven
  - EclipseIDE: Auto
  - IntelliJ: in `pom.xml`
- STEP 4: Create database MySQL by name `contactdb` in localhost (Configuration instructions are below)
- STEP 5: Run application by file `ContactsManagementApplication` in `src\main\java\com.example\ContactsManagementApplication`
- STEP 6: (START WEB) Go to the link: localhost:8080/login
### NOTE
- Project use:
  - Maven Project
  - JDK: 17
  - Java: 11
  - Spring Boot - Spring MVC
- Spring security:
  - Use permissions and prevent unauthorized access
  - Account Admin:
    - Email: `abc@abc.com`
    - Password: `123`
- Settings connect: File `application` in `src\main\resources\application.properties`
  - MySQL:
    - Link connect object `spring.datasource.url`
    - Object username `spring.datasource.username`
    - Object password `spring.datasource.password`
  - MongoDB
    - Database name object `spring.data.mongodb.database`
    - Object port `spring.data.mongodb.port`
    - Object host `spring.data.mongodb.host`
  - Redis
    - Object host: `redis.host`
    - Object port: `redis.port`
  - JWT
    - Object secret of Token access: `jwt.secret`
    - Object expiration of Token access: `jwt.expirationDateInMs`
    - Object expiration of Refresh Token: `jwt.refreshExpirationDateInMs`
- SQL
  - MySQL: Users, Contacts (migration)
  - MongoDB: Contacts
## IMG
### Login
![screencapture-localhost-8080-login-2022-09-06-08_09_32](https://user-images.githubusercontent.com/90615521/188526464-bee6ae2b-4717-4ac8-9da0-2633c1a282b7.png)

![screencapture-localhost-8080-login-2022-09-06-08_09_32](https://user-images.githubusercontent.com/90615521/188526399-c2073027-4a1a-4f81-ae4f-345e7e238000.png)
### Register
![screencapture-localhost-8080-login-2022-09-06-08_09_32](https://user-images.githubusercontent.com/90615521/188526540-9ec9e4c0-41c7-4a2c-b28d-438f8ed24f74.png)

![screencapture-localhost-8080-login-2022-09-06-08_09_32](https://user-images.githubusercontent.com/90615521/188527719-27b61b03-2caa-44e3-91f9-94ac43be7aa9.png)
### Contact us
![image](https://user-images.githubusercontent.com/90615521/188527795-b6aeb595-e24a-4b3c-a630-aafed667b014.png)

![image](https://user-images.githubusercontent.com/90615521/188527823-d4dd5046-808e-4c1b-bf20-aca406fb291e.png)
### Dashboard
![image](https://user-images.githubusercontent.com/90615521/188527897-cdcf00d5-1b42-4143-86ed-1f8aa044c111.png)
### Contact List
![image](https://user-images.githubusercontent.com/90615521/188527950-13a19fce-4730-4185-bf7b-731b75c420f3.png)

![image](https://user-images.githubusercontent.com/90615521/188528028-8ab95bfa-c7b6-471e-a11e-f59511ff2bc1.png)
### User List
![image](https://user-images.githubusercontent.com/90615521/188528092-55b74063-1b53-4ea7-977c-6d4d7cae3bb7.png)

![image](https://user-images.githubusercontent.com/90615521/188528115-23ff4246-2035-4baf-9379-42281c7385a5.png)
### Profile account
![image](https://user-images.githubusercontent.com/90615521/188528213-16b09871-c5e4-4005-bb06-12153b73e0bb.png)
![image](https://user-images.githubusercontent.com/90615521/188528238-1de4d8fa-205b-4015-a5d1-e27e9a18890c.png)
![image](https://user-images.githubusercontent.com/90615521/188528259-4c31abbd-49d0-4e00-80ee-1575504ea792.png)

