# Black-Friday-Promotion-and-Order

- Completed migrating and separating Order service to Promotion and Order microservices for Black Friday project, peak load shifting to deal with high concurrency scenarios by Redis and RocketMQ.
- Implemented cache preheating, lock inventory and purchase limit functions by Redis with Lua script. 
- Optimization query and update by MySQL optimistic locking.
- Released the stress of Order service by asynchronously creating orders by RocketMQ. 
- Implemented check order status, and cancel orders by RocketMQ delay message queue.

## Tables & API & Tests
[Promotion Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/Promotion%20API.md) </br>
[Order Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/OrderService.md) </br>
[User Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/User%20API.md) </br>
[Commodity Service](https://github.com/Huicccc/Black-Friday-Promotion-and-Order/blob/main/Commodity%20API.md) </br>

 </br>

<img width="1075" alt="Screen Shot 2022-12-16 at 10 25 34 PM" src="https://user-images.githubusercontent.com/105135459/208221618-dcedf44f-d0d9-4af2-979d-36059f3409df.png">


## Demo

<img width="1141" alt="image" src="https://user-images.githubusercontent.com/105135459/210035789-9ce7aa42-9332-476e-bd5a-472ef13bdbf0.png">

<img width="1141" alt="image" src="https://user-images.githubusercontent.com/105135459/210035918-9be7150e-9894-4c72-bc29-34ed853ad0ad.png">

<img width="997" alt="image" src="https://user-images.githubusercontent.com/105135459/210035779-48bea030-e7a7-4432-bde2-790b2057ba11.png">

<img width="1020" alt="image" src="https://user-images.githubusercontent.com/105135459/210035784-2ddad566-3047-444a-b808-7a350a1b1211.png">

<img width="1357" alt="Screen Shot 2022-12-15 at 8 52 07 PM" src="https://user-images.githubusercontent.com/105135459/208010050-6d50a02a-bd56-4944-863d-72ca70c15e52.png">
