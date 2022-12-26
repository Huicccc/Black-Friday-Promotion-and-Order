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
create new promotion 


#### **Frontend Tests**


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


2)    wrong id



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
1)    get promotion


2)    wrong status 


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
??


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
??
  
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
??
  












