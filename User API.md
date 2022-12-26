# **User Table and API**
## **Table**
Description: User table with 3 simple string-based columns; saved password with plaintext for easy to use;

string userId; (uuid)

string userName;

string password;
## **API**
Summary:

1. createAccount POST: “/account”;
1. getAccountById GET: “/account/id/{id}”;
1. getAccountByName GET: “/account/name/{name}”;
1. updatePassword PUT: “account/password”;
1. login POST: “account/login”
### **1. userDto createAccount**
#### **Path**
POST: “/account”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|userName|true|unique|
|password|true| |
#### **Request body**
{

`  `"userName": "Lily",

`  `"password": "123456lily"

}
#### **Responses**
200

{

`  `"result": {

`    `"userId": "e7d74074-48e5-11ed-b878-0242ac120002",

`    `"userName": "Lily",

`  `},

`  `msg: null

}

400

{

`  `"result": null,

`  `msg: "User already exists"

}
#### **Postman Tests**
\1)    create new user

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.001.png)

\2)    username already exists

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.002.png)
#### **Frontend Tests**
\1)    create new user

![Graphical user interface

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.003.png)

\2)    username already exists

![Graphical user interface, text

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.004.png)


### **2. userDto getUserById**
#### **Path**
GET: “/account/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|
#### **Request body**
#### **Responses**
200

{

`  `"result": {

`    `"userId": "e7d74074-48e5-11ed-b878-0242ac120002",

`    `"userName": "Lily"

`  `},

`  `msg: null

}

400

{

`  `"result": null,

`  `msg: "UserId: %s, is wrong."

}


#### **Postman Tests**
\1)    get user by id

` `![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.005.png)

\2)    wrong id

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.006.png)
### **3. userDto getUserByName**
#### **Path**
GET: “/account/name/{name}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|name|true|path variable|
#### **Request body**
#### **Responses**
200

{

`  `"result": {

`    `"userId": "e7d74074-48e5-11ed-b878-0242ac120002",

`    `"userName": "Lily"

`  `},

`  `msg: null

}

400

{

`  `"result": null,

`  `msg: "UserName: %s, is wrong."

}
#### **Postman Tests**
\1)    get user by name

` `![](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.007.png)

\2)    wrong name 

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.008.png)
### **4. userDto updatePassword**
#### **Path**
PUT: “account/password”;
#### **Parameters**

|**Name**|**Required**|**Description**|
| :-: | :-: | :-: |
|userId|true| |
|oldPassword|true| |
|newPassword|true| |
#### **Request body**
200

{

`  `"userId": "e7d74074-48e5-11ed-b878-0242ac120002"

`  `"oldPassword": "123456",

`  `"newPassword": "5211314"

}
#### **Responses**
200

{

`  `"result": {

`    `"userId": "e7d74074-48e5-11ed-b878-0242ac120002",

`    `"userName": "Lily"

`  `},

`  `msg: null

}

400

{

`  `"result": null,

`  `msg: "UserName: %s, is wrong."

}

400

{

`  `"result": null,

`  `msg: "Password provided not match."

}
#### **Postman Tests**
\1)    wrong id

` `![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.009.png)

\2)    wrong old password 

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.010.png)

\3)    update password

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.011.png)
### **5. userDto login**
#### **Path**
POST: “/account/login”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|userName|true|unique|
|password|true| |
#### **Request body**
{

`  `"userName": "Lily",

`  `"password": "123456lily"

}
#### **Responses**
200

{

`  `"result": {

`    `"userId": "e7d74074-48e5-11ed-b878-0242ac120002",

`    `"userName": "Lily"

`  `},

`  `msg: null

}

400

{

`  `"result": null,

`  `msg: "UserName: %s, is wrong."

}

400

{

`  `"result": null,

`  `msg: "Password provided not match."

}
#### **Postman Tests**
\1)    wrong name

` `![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.012.png) 

\2)    wrong password

` `![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.013.png) 

\3)    successful login

![Graphical user interface, text, application, email

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.014.png)

![Graphical user interface, application

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.015.png)
#### **Frontend Tests**
\1)    wrong name 

` `![Graphical user interface, text

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.016.png)

\2)    wrong password

` `![Graphical user interface, text

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.017.png)

\3)    successful login

![Graphical user interface

Description automatically generated](Aspose.Words.180ebcd1-69b4-4ce5-86b8-3c774b0d5876.018.png)













