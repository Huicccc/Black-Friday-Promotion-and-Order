# **Order Table and API**
## **Table**
Description: 

   - private LoNG orderNumber;

  -  private Integer orderStatus;

  -  private String promotionId;

  -  private String promotionName;

  -  private String userId;

 -   private Integer orderAmount;

 -   private LocalDateTime createTime;

  -  private LocalDateTime payTime;




~~~~sql
CREATE TABLE orders(
    order_number            BIGINT              NOT NULL,
    order_status            INTEGER             NOT NULL,
    promotion_id            VARCHAR(36)         NOT NULL,
    promotion_name          VARCHAR(128)        NOT NULL,
    user_id                 VARCHAR(36)         NOT NULL,
    order_amount            INTEGER             NOT NULL,
    create_time             TIMESTAMP           NOT NULL,
    pay_time                TIMESTAMP,
    PRIMARY KEY(order_number)
) ENGINE = InnoDB   CHARSET = utf8mb4;

CREATE INDEX idx_user_id ON orders(user_id);


~~~~
## **API**
Summary:
1. createOrder POST: “/order”;

2. getOrderById GET: “/order/id/{id}”;
3. orderInDto createBuyNowOrder OrderOutDto POST: “/order/buynow”;
4. orderStatusInDtopayBuyNowOrder orderOutDto Patch: “order/pay”;

## **1. orderInDto createOrder OrderOutDto**
#### **Path**
POST: “/order”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|promotionId|true| |
|promotionName|true| |
|orderAmount|true| |
|userId|true| |

#### **Request body**
```javascript
{
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99
}


```
#### **Responses**
```javascript
200
{
    "orderNumber": "xxxxx",
    "orderStatus": -1/0/1
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99,
    "createTime": "2022-11-14T13:56:01",
    "payTime": null
}


```

#### **Postman Tests**

1)    success, order_status = 1

<img width="944" alt="image" src="https://user-images.githubusercontent.com/105135459/210108651-ef011e21-fef8-41c3-8229-ee1957c9cab1.png">

<img width="473" alt="image" src="https://user-images.githubusercontent.com/105135459/210108659-8e47c371-1c71-4111-bf01-5a2497a34e7e.png">

<img width="933" alt="image" src="https://user-images.githubusercontent.com/105135459/210108662-4bfac655-ffe0-4450-9d2a-5f13f10b3cc6.png">

2)    out of stock, order_status: -1

<img width="932" alt="image" src="https://user-images.githubusercontent.com/105135459/210108688-3da20303-6ad0-4f62-8c72-46e9948aa599.png">

<img width="493" alt="image" src="https://user-images.githubusercontent.com/105135459/210108694-ec4057e5-c5cb-40c2-a8fa-7394fb6eee6d.png">


## **2. orderInDto getOrderById**
#### **Path**
GET: “/order/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|
#### **Request body**
#### **Responses**
```javascript
200
{
    "orderNumber": "xxxxx",
    "orderStatus": -1/0/1
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99,
    "createTime": "2022-11-14T13:56:01",
    "payTime": null
}




```

#### **Postman Tests**
<img width="744" alt="image" src="https://user-images.githubusercontent.com/105135459/210108833-798e6633-821a-4ede-8267-51e0ec9eef2c.png">

<img width="528" alt="image" src="https://user-images.githubusercontent.com/105135459/210108844-390a12ed-73cb-425a-99c6-76a3169fe6ce.png">





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




## **4. boolean lockPromotionStock**
#### **Path**
POST: “/promotion/lock/id/{id}”;
#### **Parameters**

|**Name**|**Required**|**Description**|
| :-: | :-: | :-: |
|promotionId|true| |
|promotionName|true| |
|orderAmount|true| |
|userId|true| |

#### **Request body**
```javascript
{
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99
}


```

#### **Responses**
```javascript
200
{
    "orderNumber": "xxxxx",
    "orderStatus": -1/0/1,
    "promotionId" : "802d4bf6-532f-48de-88e8-65b4d9a58ee2",
    "promotionName": "Iphone 13 Free",
    "userId": "052889e6-0920-4919-a870-b098d27b6a04",
    "orderAmount" : 99,
    "createTime": "2022-11-14T13:56:01",
    "payTime": null
}


```

#### **Postman Tests**






## **5. orderStatusInDto payBuyNowOrder orderOutDto**
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




  











