# **OrderV2 API**
## **3. orderInDto createBuyNowOrder OrderOutDto**
#### **Path**
POST: “/order/buynow”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|status|true|path variable|
#### **Request body**
#### **Responses**
```javascript
200
{
    "promotionId": "xxxxxxxx",
    "promotionName": "Corsair Keyboard Special",
    "commodityId": "b1844be1-3df6-4442-a6a6-4e3e81036934",
    "originalPrice": 129,
    "promotionalPrice": 99,
    "status": 1,
    "startTime": "2022-10-26T00:00:00",
    "endTime": "2023-12-26T00:00:01",
    "totalStock": 100,
    "availableStock": 100,
    "lockStock": 0,
    "imageUrl": "CorsairKeyboard"
}
```

#### **Postman Tests**
<img width="1391" alt="image" src="https://user-images.githubusercontent.com/105135459/212352958-2b9f9b4a-ea08-459a-8b79-e553a27c40d3.png">
<img width="700" alt="image" src="https://user-images.githubusercontent.com/105135459/212352856-2fff87af-a5ba-405a-8b91-f9888526427d.png">
<img width="1090" alt="image" src="https://user-images.githubusercontent.com/105135459/212352887-e94825c4-9d32-4c8a-b499-fb95833fc3a2.png">
<img width="1094" alt="image" src="https://user-images.githubusercontent.com/105135459/212352907-a27b0b5e-ae37-4f01-94fd-cd9ec16d057f.png">


## **4. orderStatusInDto payBuyNowOrder orderOutDto**
#### **Path**
Patch: “order/pay”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|orderNumber|true| |
|expectStatus|true| |
|existStatus|true| |

#### **Request body**
```javascript
200
{
  "orderNumber": "e7d74074-48e5-11ed-b878-0242ac120002"
  "existStatus": 1,
  "expectStatus": 2
}



```

#### **Responses**
```javascript
200
{
    "orderNumber": "xxxxx",
    "orderStatus": 2,
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99,
    "createTime": "2022-11-14T13:56:01",
    "payTime": "2022-11-14T13:58:01"
}




```

#### **Postman Tests**
<img width="1394" alt="image" src="https://user-images.githubusercontent.com/105135459/212353427-0e4b8dc0-f5fe-4a0b-80ee-9d9b555d6bec.png">
<img width="813" alt="image" src="https://user-images.githubusercontent.com/105135459/212353271-24faab2f-7ed9-4054-ace8-7a11bbff7f95.png">
<img width="812" alt="image" src="https://user-images.githubusercontent.com/105135459/212353327-f73bc6dd-c54b-4ebb-8a8e-c2e052b91de3.png">
<img width="1054" alt="image" src="https://user-images.githubusercontent.com/105135459/212353346-bd63bf83-bcac-43ca-9284-4cb3a649096d.png">
<img width="779" alt="image" src="https://user-images.githubusercontent.com/105135459/212353359-324e1f4c-8b41-44a9-bfc5-aa9d62210b01.png">
<img width="1394" alt="image" src="https://user-images.githubusercontent.com/105135459/212353397-bda8cc0f-356e-4aff-bb14-3ff9f825a47e.png">

#### **Jmeter Tests**
<img width="1392" alt="image" src="https://user-images.githubusercontent.com/105135459/212353575-3fd915ad-b46e-4a3d-801f-a8132039018e.png">
<img width="1227" alt="image" src="https://user-images.githubusercontent.com/105135459/212353592-d5650c31-6f93-40af-9c0b-677bdd9eba49.png">
<img width="1584" alt="image" src="https://user-images.githubusercontent.com/105135459/212353626-ab0a105e-f7f6-449f-aedb-091170ec9bdc.png">
