# Black-Friday-Promotion-and-Order

- Completed migrating and separating Order service to Promotion and Order microservices for Black Friday project, peak load shifting to deal with high concurrency scenarios by Redis and RocketMQ.
- Implemented cache preheating, lock inventory and purchase limit functions by Redis with Lua script. 
- Optimization query and update by MySQL optimistic locking.
- Released the stress of Order service by asynchronously creating orders by RocketMQ. 
- Implemented check order status, and cancel orders by RocketMQ delay message queue.

<img width="1541" alt="Screen Shot 2022-12-16 at 10 21 20 PM" src="https://user-images.githubusercontent.com/105135459/208221518-8b1f5c09-b072-4024-b2eb-10716b013549.png">


<img width="1376" alt="Screen Shot 2022-12-15 at 8 51 48 PM" src="https://user-images.githubusercontent.com/105135459/208010043-3d2aa2cc-32b5-4b71-b26c-724b1d448108.png">
<img width="1357" alt="Screen Shot 2022-12-15 at 8 52 07 PM" src="https://user-images.githubusercontent.com/105135459/208010050-6d50a02a-bd56-4944-863d-72ca70c15e52.png">
