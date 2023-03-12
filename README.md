# TinyUrl / URLShortner

URLShortner(TinyURL) API - Allows users to store/retrieve tinyURLs for the original/source URLS<br />

(-) Returns a URL that is shorter than the original.<br /> 
(-) Must store the original URL.<br /> 
(-) Newly generated URL must be able to link to the stored original. <br />
(-) Shortened URL should allow redirects. <br />
(-) Must support custom short URLs. <br />
(-) Must support many requests at once. <br />

#Build a Craft Spring Boot TinyUrl API with features:

1. User can create TinyUrl for the original/source URL
2. Users can create CustomUrl for the original/soruce URL
3. User are redirected to the original/source url by providing TinyUrl link
4. User are redirected to the original/source url by providing CustomUrl link

#Technology used

Spring Boot
MySQL
Google Guava Library

#APIs
```
1. POST /tinyurl/create  	--- Create a shortURL for the original/source URL. 
2. POST /tinyurl/create  	---Creates a shortURL and custom URL for the original/source URL (by providing customUrl in the Request Body). 
3. GET /tinyurl/{shortUrl} --- Redirects to the original URL by getting shortURL. 
4. GET /tinyurl/{shortUrl}/{customUrl} --- Redirects the original URL by providing shortUrl and customUrl. 
```

#To Setup the application Locally
1. Clone the git Repo. 
2. Use ID Intellij/STS/Eclipse. 
3. Setup MySql DB. 
4. Run the script to setup db locally (/src/resources/setupdb.sql). 
5. Modify application.properties as per your db. 
6. Start SpringBoot application on port 8080.  

#To Test the Application Locally 
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
Response: A TinyUrl gets created by giving complete path
```

2. POST /tinyurl/create : Creates Custom URl
```
URL: http://localhost:8080/tinyurl/create
RequestBody: {
"sourceUrl": "https://www.amazon.com",
"customUrl": "amazon1"
}
Response: A TinyUrl and CustomUrl gets generated with by giving complete Path 
```

#To Test Get Requests
```
1. Open browser (Chrome/safari) and provide the link for the TinyUrl
Response: It should get redirected to the original sourceUrl
on Error: Respective error messages shown to the user
2. Open Postman and create a Get Request by providing shortUrl
GET http://localhost:8080/tinyUrl/{shortUrl}
Response: User gets redirected to the original sourceUrl
on Error: Respective error messages shown to the user
3. Open Postman and create a Get Request by providing customUrl
GET http://localhost:8080/tinyurl/{shorturl}/{customurl}
Response: User gets redirected to the original sourceUrl
on Error: Respective error messages shown to the user
```
