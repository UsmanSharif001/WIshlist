# Wishlist

Velkommen til Wishlist!

Projektet er skabt af 4 studerende på 2. semester af datamatiker uddannelsen på KEA.

Wishlist er en CRUD-webapplikationer, hvor brugeren har mulighed for at oprette, læse, opdatere og slette ønsker og ønskelister. Projektet benytter Spring Boot for backend logik, Thymeleaf for frontend templating, og MySQL for databasestyring. Projektet er designet til at blive deployed på Microsoft Azure ved brug af GitHub Actions for automatiseret testing og deployment.

## Teknologier
Backend: Spring Boot (Java)

Frontend: Thymeleaf, CSS

Database: MySQL

CI/CD: GitHub Actions

Deployment: Azure Web Apps


## Forudsætninger
●Java JDK 17 eller nyere

●Maven

●MySQL Server

●Azure-konto (for deployment)

## Installation
1.Klon repositoriet:git clone https://github.com/UsmanSharif0001/WIshlist.git

2.Konfigurer MySQL database

3.Opret en database og kør SQL scripts fra Docs for at oprette tabeller.

4.Konfigurer applikationsindstillinger
Rediger src/main/resources/application.properties for at indstille databasetilslutninger

## Testing og deployment med Github Actions
### CI/CD
Dette projekt bruger GitHub Actions for kontinuerlig integration og deployment. Workflowet for CI/CD processerne er defineret i main.yml filen, som inkluderer opgaver som automatiske tests, bygninger, og deployment til Azure Web Apps.

For mere detaljeret information, se 
 github/workflows/main.yml.

### Bidrag
Vi byder bidrag velkomne fra alle!
Læs venligst CONTRIBUTE.md for retningslinjerne om, hvordan du kan indsende bugs, forslag til forbedringer, og pull requests. 
### Licens
Dette projekt er udgivet under MIT licensen, som tillader meget brede anvendelser. Se LICENSE filen for mere detaljer. 
### Kontakt 
Hvis du har spørgsmål, er du velkommen til at sende en email til mala0007@stud.kea.dk
