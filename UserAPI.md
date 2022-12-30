# **User Table and API**
## **Table**
Description: User table with 3 simple string-based columns; saved password with plaintext for easy to use;

- string userId; (uuid)

- string userName;

- string password;
## **API**
Summary:

1. createAccount POST: “/account”;
1. getAccountById GET: “/account/id/{id}”;
1. getAccountByName GET: “/account/name/{name}”;
1. updatePassword PUT: “account/password”;
1. login POST: “account/login”
## **1. userDto createAccount**
#### **Path**
POST: “/account”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|userName|true|unique|
|password|true| |

#### **Request body**
```javascript
{

    "userName": "Lily",

    "password": "123456lily"

}
```
#### **Responses**
```javascript
200
{

  "result": {

  "userId": "e7d74074-48e5-11ed-b878-0242ac120002",

  "userName": "Lily",

  },

  msg: null

}
```
```javascript
400

{

  "result": null,

  msg: "User already exists"

}
```
#### **Postman Tests**
1)    create new user 

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495613-8ac92b99-30ea-4f0e-88e0-91b0b7c4d885.png">

2)    username already exists

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495673-179b3379-85b5-4a37-83d9-1df599e34dc9.png">


#### **Frontend Tests**
1)    create new user

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495692-fe610de3-6eaa-49bc-be73-6f467e670864.png">


2)    username already exists

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495703-58d52f2d-156e-4053-950e-5e2dbc2effa2.png">

## **2. userDto getUserById**
#### **Path**
GET: “/account/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|
#### **Request body**
#### **Responses**
```javascript
200

{

    "result": {

      "userId": "e7d74074-48e5-11ed-b878-0242ac120002",

      "userName": "Lily"

    },

    msg: null

}
```
```javascript
400

{

    "result": null,

    msg: "UserId: %s, is wrong."

}
```


#### **Postman Tests**
1)    get user by id

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495742-17251543-148c-4fc8-9846-2bd610b58caf.png">

2)    wrong id

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495763-9a1963ee-39f4-4666-b99e-25940e0b3291.png">

## **3. userDto getUserByName**
#### **Path**
GET: “/account/name/{name}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|name|true|path variable|
#### **Request body**
#### **Responses**
```javascript
200

{

    "result": {

      "userId": "e7d74074-48e5-11ed-b878-0242ac120002",

      "userName": "Lily"

    },

    msg: null

}
```
```javascript
400

{

    "result": null,

    msg: "UserName: %s, is wrong."

}
```
#### **Postman Tests**
1)    get user by name

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495788-226136c0-8015-4baa-b752-eb414afd26ab.png">


2)    wrong name 

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495799-e757e902-6fbc-45c2-9ab9-89d4a79361e3.png">

## **4. userDto updatePassword**
#### **Path**
PUT: “account/password”;
#### **Parameters**

|**Name**|**Required**|**Description**|
| :-: | :-: | :-: |
|userId|true| |
|oldPassword|true| |
|newPassword|true| |
#### **Request body**
```javascript
200

{

    "userId": "e7d74074-48e5-11ed-b878-0242ac120002"

    "oldPassword": "123456",

    "newPassword": "5211314"

}
```
#### **Responses**
```javascript
200

{

    "result": {

      "userId": "e7d74074-48e5-11ed-b878-0242ac120002",

      "userName": "Lily"

    },

    msg: null

}
```
```javascript
400

{

    "result": null,

    msg: "UserName: %s, is wrong."

}
```
```javascript
400

{

    "result": null,

    msg: "Password provided not match."

}
```
#### **Postman Tests**
1)    wrong id

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495815-a755e281-cf89-4d29-b711-09e1c67a156e.png">


2)    wrong old password 

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495827-5373fd2d-102e-4bae-9d08-01772896d8a8.png">


3)    update password

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495834-748143fb-537e-4b4c-896b-6e77a8cec68b.png">


## **5. userDto login**
#### **Path**
POST: “/account/login”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|userName|true|unique|
|password|true| |
#### **Request body**
```javascript
{

    "userName": "Lily",

    "password": "123456lily"

}
```
#### **Responses**
```javascript
200

{

    "result": {

      "userId": "e7d74074-48e5-11ed-b878-0242ac120002",

      "userName": "Lily"

    },

    msg: null

}
```
```javascript

400

{

    "result": null,

    msg: "UserName: %s, is wrong."

}
```
```javascript
400

{

    "result": null,

    msg: "Password provided not match."

}
```
#### **Postman Tests**
1)    wrong name

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495849-3899a5e7-cb8e-4fb7-ac04-ca168fbe3236.png">
 

2)    wrong password

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495866-27a18525-1a22-4525-9be2-932b4a848741.png">


3)    successful login

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495890-bc719d3e-6dda-4f3d-9faa-7b566382701b.png">

4)    system time

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495916-b69b7124-d0c6-4efb-b263-0c556e902783.png">


#### **Frontend Tests**
1)    wrong name 

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495922-8de273b3-f2f3-4ba5-a8e4-017eb2a57dc0.png">


2)    wrong password

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495927-c2d6888b-3f74-455c-80e9-05c363cbe3ab.png">


3)    successful login

<img width="500" alt="image" src="https://user-images.githubusercontent.com/105135459/209495934-7121dec9-96e9-4405-9af0-8f70db19afe8.png">















