## Cassandra 

Apache Cassandra is one of the most popular NoSQL databases. It offers great performance and scalability without sacrificing availability.  [src](https://blog.softwaremill.com/7-mistakes-when-using-apache-cassandra-51d2cf6df519)

It’s a great tool and we like it, but too often we see teams run into trouble using it. We recommend using Cassandra carefully. Teams often misunderstand the use case for Cassandra, attempting to use it as a general-purpose data store when in fact it is optimized for fast reads on large data sets based on predefined keys or indexes [src](https://www.thoughtworks.com/radar/platforms/cassandra-carefully)



[Designing data models for Cassandra](https://www.oreilly.com/ideas/cassandra-data-modeling)

Design Differences Between RDBMS and Cassandra

1. No joins
2. No referential integrity
3. Denormalization :  This is not an advantage when working with Cassandra because it performs best when the data model is denormalized. Cassandra provides a feature known as materialized views which allows us to create multiple denormalized views of data based on a base table design
4. Query-first design: Relational modeling, in simple terms, means that you start from the conceptual domain and then represent the nouns in the domain in tables... By contrast, in Cassandra you don’t start with the data model; you start with the query model
5. Designing for optimal storage: Cassandra tables are each stored in separate files on disk, it’s important to keep related columns defined together in the same table.
6. Sorting is a design decision
7. In an RDBMS, you can easily change the order in which records are returned to you by using ORDER BY in your query. The CQL SELECT statement does support ORDER BY semantics, but only in the order specified by the clustering columns.
8. 

Defining Application Queries https://www.oreilly.com/ideas/cassandra-data-modeling

i'll continue later ...

https://towardsdatascience.com/top-5-reasons-to-use-the-apache-cassandra-database-d541c6448557
https://blog.softwaremill.com/7-mistakes-when-using-apache-cassandra-51d2cf6df519
https://www.guru99.com/cassandra-tutorial.html
https://www.google.com/search?q=Codd%27s+normal+forms&oq=Codd%27s+normal+forms&aqs=chrome..69i57&sourceid=chrome&ie=UTF-8
https://www.webcomand.com/docs/language/cql/cql_vs_sql/index.html

Cassandra Config 

@Configuration
extends AbstractCassandraConfiguration
CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9142);
return cluster;

Notice the new bean – BasicCassandraMappingContext – with a default implementation. This is required to map the persistent entities between their object and their persistent formats.

We have to setup the host name that the Cassandra server running as contactPoints. Port is simply the listening port for request in server. KeyspaceName is the namespace that defines the data replication on nodes, which is based on a Cassandra related concept.

@Repository
public interface BookRepository extends CassandraRepository<Book> {
}

@Configuration
@EnableCassandraRepositories(
  basePackages = "org.baeldung.spring.data.cassandra.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {
}