
# poc
Spring POC Project using Spring Security and Liquibase with Swagger API Documentation integration  
It's maven project.

Steps to run the application:
1. Change the applicarion properties eg. Database Name, User Name and Password.
2. Security is enabled so all the end points are restricted only Login,Register and Health API is allow without token.
3. In Order to access all the cars APIs please register by following steps:
        http://localhost:8080/authentication/register    POST Method
        Request Body:
        [{
            "userName":"akash",
            "userEmail":"akash@gmail.com",
            "password":"12345678"
        }]
4. Once succefully register please login by following step:
      http://localhost:8080/authentication/login   POST Method
      Request Body: 
      {
	      "userEmail":"akash@gmail.com",
	      "password":"12345678"
      }
5. After Successfully login you will get Access_Token in login response please use same access token for all the APIs.
  eg Please add Bearer before access token 
  Add Authorization header and value will be Bearer <Token>
  eg.
  Bearer  eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTg2ODE0NDI1LCJpYXQiOjE1ODY4MDcyMjUsIm9yZ0lkIjp7InVzZXJJZCI6MSwibmFtZSI6ImFrYXNoIiwidXNlckVtYWlsIjoiYWthc2hAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmEkMTAkdVh2bUlKMXVPTkxsNGlBQVN5RUxndVVieFBIVFVnQVd3UVEzZm1OSkJGVlUxUHU3VDJTbnUiLCJjcmVhdGVkQnkiOjF9fQ.oQyl4Azo6P-HiCv0YsP84SDmbai-l7arCg0pQVmIXrDEy9vli4G8AGq1j9NixF2Xmb77GLpo5Y5cGtWSjcBWCg.
  
6.All the APIs are documented using swagger ui to check the API documentation please use beloew URL
   http://localhost:8080/swagger-ui.html



-THANKS
Akash Patel


