## Wishlist Project

**Velkommen til vores Wishlist projekt**

**Denne webapplikation er blevet lavet af studerende på KEAs Datamatiker uddannelse**

**Det er en simpel og let løsning til at gemme, ønske, dele og reservere dine egne personlige ønsker**

**Til dette projekt har vi brugt Spring Boot til backend logikken og til frontend brugt Thymeleaf, html og CSS - og til sidst MySSQL til databasestyringen.
Vi bruger Microsofts Azure servere til at deploye vores projekt som er forbundet vha. secrets and variables til Github actions i et CI/CD workflow**

### Teknologier 

**Backend: Springboot(Java)** 

**Frontend: Thymeleaf, CSS**

**Database: MySQL**

**Deployment: Azure Web Apps**

**Vores projekt er offentlig tilgengæligt til at klone og selv arbejder videre med ved behov.
For at kunne klone dette projekt skal du have flg:**

- Java JDK 17 eller nyere
- Maven
- MySQL server
- Azure for web-applikation og kommunikation til MySQL og github-actions

  
### Installation

1. Klon repositoriet: https://github.com/UsmanSharif001/WIshlist.git
2. Hent SQL scripts fra src/main/resources/sql for at lave tabeller der bliver brugt
3. Konfigurer applikationsindstillinger Rediger src/main/resources/application.properties for at indstille databasetilslutninger:

`spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL;DATABASE_TO_LOWER=TRUE;INIT=runscript from 'src/main/resources/h2init.sql';`

`spring.datasource.username=`

`spring.datasource.password=`

`spring.datasource.driverClassName=org.h2.Driver`

4. `Byg og kør projektet med Maven: mvn packagejava -jar target/WIshlist-0.0.1-SNAPSHOT.jar`

### Testing og Deployment med GitHub Actions
**Dette projekt bruger GitHub Actions for kontinuerlig integration og deployment. Workflowet for CI/CD processerne er defineret i main.ymlfilen, som inkluderer opgaver som automatiske tests, bygninger, og deployment til Azure Web Apps.**

### Kontakt

**Vi er klar til at svare og hjælpe på eventuelle spørgsmål på** (Numt@stud.kea.dk)


![Dreams](https://github.com/UsmanSharif001/WIshlist/assets/144384291/82328ce5-2f86-4b3c-84b0-5d4facb6848f)
