# **Promotion Table and API**
## **Table**
Description: User table with 3 simple string-based columns; saved password with plaintext for easy to use;

- String promotionId;

- String promotionName;

- String commodityId;

- Integer originalPrice;

- Integer promotionalPrice;

- LocalDateTime startTime;

- LocalDateTime endTime;

- Integer status;

- Long totalStock;

- Long availableStock;

- Long lockStock;

- String imageUrl;



~~~~sql
CREATE TABLE promotion
(
    promotion_id                  VARCHAR(36)         NOT NULL,
    promotion_name                VARCHAR(128)         NOT NULL,
    commodity_id        VARCHAR(36)         NOT NULL,
    original_price      INTEGER             NOT NULL,
    promotion_price     INTEGER             NOT NULL,
    start_time          TIMESTAMP           NOT NULL,
    end_time            TIMESTAMP           NOT NULL,
    status              INTEGER,
    total_stock         BIGINT              NOT NULL,
    available_stock     BIGINT              NOT NULL,
    lock_stock          BIGINT              NOT NULL,
    image_url           VARCHAR(64)         NOT NULL,
    PRIMARY KEY(promotion_id)
)   ENGINE = InnoDB     CHARSET = UTF8mb4;

CREATE INDEX idx_promotion_commodity_id ON promotion(commodity_id);
CREATE INDEX idx_promotion_start_time ON promotion(start_time);
CREATE INDEX idx_promotion_end_time ON promotion(end_time);

INSERT INTO promotion(promotion_id, promotion_name, commodity_id, original_price, promotion_price, start_time, end_time,
status, total_stock, available_stock, lock_stock, image_url)
VALUES  (UUID(), 'Iphone13 low price', 'b859ee94-20f1-11ed-861d-0242ac120002', 999, 899, CURRENT_TIMESTAMP(), '2023-12-26 00:00:00', 1, 500, 500, 0, 'Iphone13'),
        (UUID(), 'MacBook Pro Black Friday Special', 'b859ee94-20f1-11ed-861d-0242ac120003', 1699, 1499, CURRENT_TIMESTAMP(), '2023-12-26 00:00:00', 1, 5000, 5000, 0, 'MacBookPro');

~~~~
## **API**
Summary:
1. createPromotion POST: “/promotion”;

2. getPromotionById GET: “/promotion/id/{id}”;

3. getPromotionByStatus GET: “/promotion/status/{status}”;

4. lockStock POST: “/promotion/lock/id/{id}”;

5. deductStock POST: “/promotion/deduct/id/{id}”;

6. revertStock POST: “/promotion/revert/id/{id}”

## **1. PromotionInDto createPromotion PromotionOutDto**
#### **Path**
POST: “/promotion”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|promotionName|true| |
|commodityId|true| |
|originalPrice|true| |
|promotionalPrice|true| |
|startTime|true| |
|endTime|true| |
|status|true| |
|totalStock|true| |
|availableStock|true| |
|lockStock|true| |
|imageUrl|true| |

#### **Request body**
```javascript
{
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

<img width="597" alt="image" src="https://user-images.githubusercontent.com/105135459/209602754-21caabc9-374d-4d31-a1b9-802a7acb03a2.png">


#### **Frontend Tests**

<img width="798" alt="image" src="https://user-images.githubusercontent.com/105135459/209602768-32d85a08-1414-4737-a6b3-9ca0d88b4cd1.png">

<img width="779" alt="image" src="https://user-images.githubusercontent.com/105135459/209735885-eed4d648-346e-4101-bcce-3f79d794e555.png">



## **2. getPromotionById CommodityOutDto**
#### **Path**
GET: “/promotion/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|
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
1)    get user by id

<img width="645" alt="image" src="https://user-images.githubusercontent.com/105135459/209602828-48cbf30e-9374-464d-ac98-a7c08c8d1f79.png">

2)    wrong id

<img width="645" alt="image" src="https://user-images.githubusercontent.com/105135459/209602877-9aad14ab-87e9-41c5-95c8-bfdbc8502a33.png">



## **3. getPromotionByStatus List<CommodityOutDto>**
#### **Path**
GET: “/promotion/status/{status}”;
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
|userId|true|path variable|

#### **Request body**

#### **Responses**
```javascript
200
true/flase


```

#### **Postman Tests**

<img width="1238" alt="image" src="https://user-images.githubusercontent.com/105135459/209602969-e9668885-9622-4881-a1d6-b00d1f9d8bea.png">
<img width="667" alt="image" src="https://user-images.githubusercontent.com/105135459/209602974-9d2d6b51-50ea-4aa4-821f-c9694298dda0.png">
<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209602976-b6ccc0c7-108d-4c0b-aa0d-fdec7cc72006.png">




## **5. boolean deductPromotionStock**
#### **Path**
POST: “/promotion/deduct/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|

#### **Request body**
```javascript
200
true/false

```

#### **Postman Tests**
1) true
    
<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209603003-0f2a9547-ab4e-4881-b902-c6904b1795cb.png">

<img width="637" alt="image" src="https://user-images.githubusercontent.com/105135459/209603019-b44088ba-f7ef-4ac9-b120-6a2b79ed42b4.png">

<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209603031-4c6f0c87-f665-4b26-a96c-ce37b5ef1842.png">

2) false
    
<img width="1238" alt="image" src="https://user-images.githubusercontent.com/105135459/209603060-2e0afbf2-7c81-41ca-adf5-d00d5c1fa27e.png">
<img width="660" alt="image" src="https://user-images.githubusercontent.com/105135459/209603092-d92ac716-8668-4a9b-bea0-ee9b717c751e.png">



    
    
## **6. boolean revertPromtionStock**
#### **Path**
POST: “/promotion/revert/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|

#### **Request body**
```javascript
200
true
```

#### **Postman Tests**

<img width="1238" alt="image" src="https://user-images.githubusercontent.com/105135459/209603108-71af9d7a-b3d9-432c-a242-9fb1538507c4.png">

<img width="666" alt="image" src="https://user-images.githubusercontent.com/105135459/209603117-409a15a1-659f-4034-820b-0fe6e67328d9.png">

<img width="1244" alt="image" src="https://user-images.githubusercontent.com/105135459/209603128-7cfaeb46-36ee-4dc1-a88b-80fd8413fd2d.png">

<img width="736" alt="image" src="https://user-images.githubusercontent.com/105135459/209603133-ecbaaaba-1c20-4f12-a925-57df55c72a1d.png">

<img width="1243" alt="image" src="https://user-images.githubusercontent.com/105135459/209603135-c662cc5a-af45-4c3f-ae7c-c272491ed434.png">

  












