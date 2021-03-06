# MobileSubscribers
REST Web-Service responsible of maintaining a database of mobile numbers, that are assigned to clients, along with some related information.

Swagger OAS 3 definition of the designed / implemented API is located in the `SWAGGER Folder` in the root of the application

## Setting Up

1. **Install mysql**
2. **Replace the connection string in the application.yaml file as well as the credentials to the installed mysql database credentials**
```properties
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/databasename?useUnicode=true&characterEncoding=utf8&useSSL=false
    username: username
    password: password
```

3. **Run project as spring boot project**

## All Available Requests
All requests and response are sent via `JSON`. 

**Base Access URL**
`http://localhost:8087/api`

1. **Create Mobile Subscribers**

`POST /saveMobileSubscriber`

_Request_: 

```json
{
	"msisdn": "4343kolo",
	"customerIdOwner": 1,
	"customerIdUser": 1,
	"serviceType": "MOBILE_PREPAID"     		
 }
``` 
_Response_:
```json
{
    "id": 27,
    "msisdn": "4787765678733",
    "customerIdOwner": 1,
    "customerIdUser": 1,
    "serviceType": "MOBILE_PREPAID",
    "serviceStartDate": "2021-04-20T17:49:45.3753744Z"
}
``` 
2. **Get all mobile scribers**

`GET /getMobileSubscribers`

_Response_:
```json
[
    {
        "id": 1,
        "msisdn": "233244988371",
        "customerIdOwner": 1,
        "customerIdUser": 1,
        "serviceType": "MOBILE_PREPAID",
        "serviceStartDate": "2021-03-26T14:14:31Z"
    }
]
``` 

3. **Get mobile numbers of mobile subscribers**

`GET /getMobileNumbers` 

_Response_: 
```json
[
    "233244988371",
    "234255986372"
]
``` 

4. **Get Mobile Subscribers matching search criteria**

`GET /getMobileSubscribers/{search}`

_Response_:  
```json
[
    {
        "id": 27,
        "msisdn": "4787765678733",
        "customerIdOwner": 1,
        "customerIdUser": 1,
        "serviceType": "MOBILE_PREPAID",
        "serviceStartDate": "2021-04-20T17:49:45Z"
    }
]
``` 

5. **Get Mobile numbers matching search criteria** 

`GET /getMobileNumbers/{search}` 

_Response_: 

```json
[
    "4787765678733"
]
```
6.  **Update mobile number plan, assign different owners or users**

`PUT /updateMobileSubscriber`

_Request_:  
```json
   {
        
      "msisdn": "4787765678733",
      "customerIdOwner": 2,
      "customerIdUser": 2,
      "serviceType": "MOBILE_POSTPAID"
       
   }
```

_Response_:  
```json
{
    "id": 27,
    "msisdn": "4787765678733",
    "customerIdOwner": 2,
    "customerIdUser": 2,
    "serviceType": "MOBILE_POSTPAID",
    "serviceStartDate": "2021-04-20T17:59:48.456043Z"
}
```

7. **Delete Mobile Subscriber** 

`DELETE /deleteSubscriber/{mobilenumber}`

_Response_: 

```
HTTP/1.1 200 OK
Date: Thu, 24 Feb 2011 12:36:31 GMT
Status: 200 OK
```
