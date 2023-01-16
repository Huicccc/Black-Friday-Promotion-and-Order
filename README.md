# Black-Friday-Promotion-and-Order

- Completed migrating and separating Order service to Promotion and Order microservices for Black Friday project, peak load shifting to deal with high concurrency scenarios by Redis and RocketMQ.
- Implemented cache preheating, lock inventory and purchase limit functions by Redis with Lua script. 
- Optimization query and update by MySQL optimistic locking.
- Released the stress of Order service by asynchronously creating orders by RocketMQ. 
- Implemented check order status, and cancel orders by RocketMQ delay message queue. 



https://user-images.githubusercontent.com/105135459/212686805-6c761c5d-07cd-4a07-8ba0-f25c4b4d992a.mov




## System Design
<img width="900" alt="Screen Shot 2022-12-16 at 10 25 34 PM" src="https://user-images.githubusercontent.com/105135459/208221618-dcedf44f-d0d9-4af2-979d-36059f3409df.png">

## Version Update

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/210188251-687198e3-3db0-40d3-b748-0de9aeb4a29a.png">

## Version1

- Tables & API </br>
[Promotion Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/Promotion%20API.md) </br>
[Order Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/OrderService.md) </br>
[User Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/User%20API.md) </br>
[Commodity Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/Commodity%20API.md) </br>

- Promotion Oversell Strategies: Oversell, Synchronized and Optimistic Lock
![Picture1](https://user-images.githubusercontent.com/105135459/210188109-ff83d6bf-f180-4567-b1fe-5c2441736111.png)

## Version2 - Decouple OrderProcessing 
- Redis & Lua 
[PromotionCache](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/promotionServiceV2.md) </br>

- RocketMQ
[OrderServiceV2](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/orderServiceV2.md) </br>

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/211179108-12fad797-939a-4374-88c3-c3b9da72483e.png">
<img width="1584" alt="image" src="https://user-images.githubusercontent.com/105135459/212355201-3b71df75-28fe-4ee0-a239-52cd865177f6.png">



## Version3 - Microservice
![c9f1f7a2-b654-49e3-aece-feda3dfee8d6](https://user-images.githubusercontent.com/105135459/211178762-c2f63fcc-2775-4507-8f02-c6edb9c6ccb1.png)



## Frontend Demo

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/210035789-9ce7aa42-9332-476e-bd5a-472ef13bdbf0.png">

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/210035918-9be7150e-9894-4c72-bc29-34ed853ad0ad.png">

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/210035779-48bea030-e7a7-4432-bde2-790b2057ba11.png">

<img width="900" alt="image" src="https://user-images.githubusercontent.com/105135459/210035784-2ddad566-3047-444a-b808-7a350a1b1211.png">

<img width="900" alt="Screen Shot 2022-12-15 at 8 52 07 PM" src="https://user-images.githubusercontent.com/105135459/208010050-6d50a02a-bd56-4944-863d-72ca70c15e52.png">
