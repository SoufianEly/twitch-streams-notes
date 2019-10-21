## Caching 

[caching-types](https://dzone.com/articles/introducing-amp-assimilating-caching-quick-read-fo) 

A cache is a memory buffer used to temporarily store frequently accessed data. It improves performance since data does not have to be retrieved again from the original source. 

1. Vertical Scalability

This can be achieved by upgrading a single machine with more efficient resources (CPU, RAM, HDD, and SSD) and implementing caching. 

2. Horizontal Scalability

This can be achieved by adding more machines and implementing caching at the application level on each machine. But is still has limitations in regards to communicating with downstream applications where additional servers cannot be added. 

3. In-Process Caching 

In-process caching enables objects to be stored in the same instance as the application, i.e. the cache is locally available to the application and shares the same memory space.

4. In-Memory Distributed Caching

Distributed caches (key/value objects) can be built externally to an application that supports read/write to/from data repositories, keeps frequently accessed data in RAM, and avoid continuous fetching data from the data source. Such caches can be deployed on a cluster of multiple nodes, forming a single logical view. Caching clients use hashing algorithms to determine the location of an object in a cluster node.

5. 

[Spring Cache](https://www.baeldung.com/spring-cache-tutorial)

Interestingly, there is another module named spring-context-support, which sits on top of the spring-context module and provides a few more CacheManagers backed by the likes of EhCache or Caffeine. If you're going to use those as your cache storage, then use the spring-context-support module instead:

Ehcache is a JVM caching library, famous for being used as the default 2nd level cache for the Hibernate ORM (memcached-redis)

Caffeine is a high performance Java 8 based caching library providing a near optimal hit rate. It provides an in-memory cache very similar to the Google Guava API. Spring Boot Cache starters auto-configured a CaffeineCacheManager if it finds the Caffeine in the classpath. The Spring Framework provides support for transparently adding Caching to an application. Let’s see how to integrate Spring Boot with Caffeine Cache.


cache2k focuses on providing a well engineered in-memory object cache implementation for Java applications. ++
Caffeine and cache2k have significant better hit rates by about 4%, cache2k is slightly better than Caffeine by about 0.15%.
[cache2k](https://cruftex.net/2017/09/01/Java-Caching-Benchmarks-Part-3.html)



The Guava project contains several of Google's core libraries that we rely on in our Java-based projects: collections, caching, primitives support, concurrency libraries, common annotations, string processing, I/O, and so forth. Each of these tools really do get used every day by Googlers, in production services.


Simply put, JCache is the standard caching API for Java. In this tutorial, we're going to see what JCache is and how we can use it. (Default)


Infinispan is a distributed cache and key-value NoSQL data store software developed by Red Hat. Java applications can embed it as library, use it as a service in WildFly[1] or any non-java applications[2] can use it as remote service through TCP/IP.[3] [wiki](https://en.wikipedia.org/wiki/Infinispan)

1. Available as an embedded Java library or as a language-independent service accessed remotely over a variety 2. of protocols (Hot Rod, REST, Memcached)
3. Use it as a cache or a data grid.
4. Advanced functionality such as persistence, transactions, events, querying, distributed processing,      off-heap 5. and geographical failover.
6.Monitor and manage it through JMX, a CLI and a web-based console.
7.Integrates with JPA, JCache, Spring, Spark and many more.
8. Works on bare-metal, containerized and virtualized environments.
9.Works on AWS, Azure, Google Cloud, Kubernetes and OpenShift.


Caffeine vs Guava 

The main difference is because Caffeine uses ring buffers to record & replay events, whereas Guava uses ConcurrentLinkedQueue. The intent was always to migrate Guava over and it made sense to start simpler, but unfortunately there was never interest in accepting those changes. The ring buffer approach avoids allocation, is bounded (lossy), and cheaper to operate against.

Caffeine => Ring Buffers 
A ring buffer is a data structure that is treated as circular although it its implementation is linear. A circular buffer is typically used as a data queue. A circular buffer is a popular way to implement a data stream because the code can be compact.

Guava => ConcurrentLinkedQueue 

@Cacheable => This annotation, Spring will create a proxy of the NumberService to intercept calls to the square method and call Ehcache
