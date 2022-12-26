# **Commodity Table and API**
## **Table**
Description

- String commodityId;(uuid)

- String commodityName;

- String description;

- Integer price;

- String imageUrl;

~~~~sql
CREATE TABLE commodity (
    commodity_id            VARCHAR(36)         NOT NULL,
    commodity_name          VARCHAR(64)         NOT NULL,
    description             TEXT(256)           NOT NULL,
    price                   INTEGER             NOT NULL,
    image_url               VARCHAR(64),
    PRIMARY KEY(commodity_id)
) ENGINE = InnoDB   CHARSET = utf8mb4;

CREATE INDEX uni_commodity_name ON  commodity(commodity_name);

INSERT INTO commodity(commodity_id, commodity_name, description, price, image_url)
VALUES ('b859ee94-20f1-11ed-861d-0242ac120002', 'Iphone13', 'Apple\'s iPhone is one of the most popular smartphones on the market, and for good reason. It\'s powerful, it\'s sleek, and it has a huge selection of apps. If you\'re looking for a new mobile phone, the iPhone X is a great option. Browse the top-ranked list of iPhone 10 below along with associated reviews and opinions.', 999, 'Iphone13'),
                ('b859ee94-20f1-11ed-861d-0242ac120003', 'MacBook Pro', 'Power to change everything. Say hello to a Mac that is extreme in every way. With the greatest performance, expansion and configurability yet, it is a system created to let a wide range of professionals push the limits of what is possible.', 1699, 'MacBookPro');

~~~~
## **API**
Summary:

1. createCommodity POST:”/commodity”;

2. getCommodityById GET:”/commodity/id/{id}”;
## **1. CommodityInDto createCommodity CommodityOutDto**
#### **Path**
POST: “/commodity”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|commodityName|true| |
|description|true| |
|price|true| |
|image_url|false| |

#### **Request body**
```javascript
{
    "commodityName": "Corsair K58 RGB Gaming Keyboard",
    "description": "Take your game play to new levels with the Corsair K57 RGB Bluetooth backlit gaming keyboard. Equipped with anti-ghosting technology, it delivers superior accuracy with every key press. Dedicated volume and multimedia controls make it easy to adjust your audio without having to take your hands off the keyboard so you can stay focused on the game.",
    "price":129,
    "imageUrl": "CorsairK58"
}

```
#### **Responses**
```javascript
200
{
    "commodityId": "fasdfasdf-dsewfas-asdaff-sd-fasd",
    "commodityName": "Corsair K58 RGB Gaming Keyboard",
    "description": "Take your game play to new levels with the Corsair K57 RGB Bluetooth backlit gaming keyboard. Equipped with anti-ghosting technology, it delivers superior accuracy with every key press. Dedicated volume and multimedia controls make it easy to adjust your audio without having to take your hands off the keyboard so you can stay focused on the game.",
    "price":129,
    "imageUrl": "CorsairK58"
}

```

#### **Postman Tests**
create new commodity 

<img width="600" alt="image" src="https://user-images.githubusercontent.com/105135459/209504979-16e44147-2bac-47d6-af0f-84f16f2c8d8b.png">



## **2. getCommodityById CommodityOutDto**
#### **Path**
GET: “/commodity/id/{id}”;
#### **Parameters**

|**Name**|**Reqiured**|**Description**|
| :-: | :-: | :-: |
|id|true|path variable|
#### **Request body**
#### **Responses**
```javascript
200
{
    "commodityId": "fasdfasdf-dsewfas-asdaff-sd-fasd",
    "commodityName": "Corsair K58 RGB Gaming Keyboard",
    "description": "Take your game play to new levels with the Corsair K57 RGB Bluetooth backlit gaming keyboard. Equipped with anti-ghosting technology, it delivers superior accuracy with every key press. Dedicated volume and multimedia controls make it easy to adjust your audio without having to take your hands off the keyboard so you can stay focused on the game.",
    "price":129,
    "imageUrl": "CorsairK58"
}

```

#### **Postman Tests**
1)    get commodity by id

<img width="600" alt="image" src="https://user-images.githubusercontent.com/105135459/209504869-bb8b47e1-b6a6-4cf8-a9c3-37e100afdfe1.png">

2)    wrong id

<img width="600" alt="image" src="https://user-images.githubusercontent.com/105135459/209505181-9a368b27-398c-4269-814e-5da39f93569b.png">


