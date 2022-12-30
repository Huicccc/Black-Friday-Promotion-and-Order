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
    
<img width="551" alt="image" src="https://user-images.githubusercontent.com/105135459/209602890-f17630f4-4f5f-4738-9698-5f7f4c0cf3d2.png">



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






## **5. orderStatusInDtopayBuyNowOrder orderOutDto**
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
1) true
    
<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209603003-0f2a9547-ab4e-4881-b902-c6904b1795cb.png">

<img width="637" alt="image" src="https://user-images.githubusercontent.com/105135459/209603019-b44088ba-f7ef-4ac9-b120-6a2b79ed42b4.png">

<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209603031-4c6f0c87-f665-4b26-a96c-ce37b5ef1842.png">

2) false
    
<img width="1238" alt="image" src="https://user-images.githubusercontent.com/105135459/209603060-2e0afbf2-7c81-41ca-adf5-d00d5c1fa27e.png">
<img width="660" alt="image" src="https://user-images.githubusercontent.com/105135459/209603092-d92ac716-8668-4a9b-bea0-ee9b717c751e.png">




  











