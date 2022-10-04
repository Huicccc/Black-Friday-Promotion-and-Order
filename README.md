# Black-Friday-Promotion-and-Order

- Completed migrating and separating Order service to Promotion and Order microservices for Black Friday project, peak load shifting to deal with high concurrency scenarios by Redis and RocketMQ.
- Implemented cache preheating, lock inventory and purchase limit functions by Redis with Lua script. 
- Optimization query and update by MySQL optimistic locking.
- Released the stress of Order service by asynchronously creating orders by RocketMQ. 
- Implemented check order status, and cancel orders by RocketMQ delay message queue.
