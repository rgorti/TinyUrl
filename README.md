# TinyUrl / URLShortner 

URLShortner(TinyURL) API - Design and implement URL shortening service, service that takes long url and generates short and unique url. Tiny url is extremely handy and less error prone to share through sms/slack/tweets. 
 <br />
1. Returns a URL that is shorter than the original.<br /> 
2. Must store the original URL.<br /> 
3. Newly generated URL must be able to link to the stored original. <br />
4. Shortened URL should allow redirects. <br />
5. Must support custom short URLs. <br />
6. Must support many requests at once. <br />

# Build Spring Boot TinyUrl API with features:

1. User can create TinyUrl for the original/source URL
2. Users can create CustomUrl for the original/soruce URL
3. User are redirected to the original/source url by providing TinyUrl link
4. User are redirected to the original/source url by providing CustomUrl link

# Technology used

Spring Boot<br />
MySQL<br />
Google Guava Library<br />

# APIs
```
1. POST /tinyurl/create                 --- Create a shortURL for the original/source URL. 
2. POST /tinyurl/create  	              --- Creates a shortURL and custom URL for the original/source URL (by providing customUrl in the Request Body). 
3. GET /tinyurl/{shortUrl}              --- Redirects to the original URL by getting shortURL. 
4. GET /tinyurl/{shortUrl}/{customUrl}  --- Redirects the original URL by providing shortUrl and customUrl. 
```

# To Setup the application Locally
1. Clone the git Repo. 
2. Use ID Intellij/STS/Eclipse. 
3. Setup MySql DB. - Both Server and client MySqlWorkbench
4. Run the script to setup db locally (/src/main/resources/setupdb.sql). 
5. Modify application.properties as per your db. 
6. Start SpringBoot application on port 8080. 
 ``` 
 mvn clean install --settings /{userHome}/tinyURLCraft/tinyURL/settings.xml
 
 Start SpringBoot application on port 8080. (start application directly using IDE or command prompt: mvn spring-boot:run application name)
```
# To Test the Application Locally 
1. Use Postman/ Browser.  
2. Set Content-type: application/json. 
3. Provide Json Request Body for the POST requests. 

1. POST /tinyurl/create - Creates TinyUrl for the sourceUrl
```
URL: http://localhost:8080/create
RequestBody: 
{
"sourceUrl": "https://www.google.com"
}
Response: A TinyUrl gets created by giving complete path. 
```

2. POST /tinyurl/create : Creates Custom URl
```
URL: http://localhost:8080/tinyurl/create
RequestBody: {
"sourceUrl": "https://www.amazon.com",
"customUrl": "amazon1"
}
Response: A TinyUrl and CustomUrl gets generated by giving complete Path
```
3. GET /tinyurl/{shortnerid} <br /> 
Open Postman / Browser and provide the shortURL
```
GET http://localhost:8080/tinyUrl/{shortUrl}
Response: User gets redirected to the original sourceUrl
on Error: Respective error messages shown to the user
```
4. GET /tinyurl/{shortnerid}/{customid}<br /> 
Open Postman / Browser and provide the customURL
```
GET http://localhost:8080/tinyurl/{shorturl}/{customurl}
Response: User gets redirected to the original sourceUrl
on Error: Respective error messages shown to the user
```
# To run the load test
1. install locust locally (https://docs.locust.io/en/0.12.2/installation.html)
2. Modify src/resources/locustfile.py for valid inputs
3. Open a terminal and run the command $locust --host="http://localhost:8080" --locustfile locustfile.py
4. Open Browser  http://0.0.0.0:8089/ - users can provide inputs (No of Users: 100, Spawn Rate: 1, host: http://localhost:8080)

