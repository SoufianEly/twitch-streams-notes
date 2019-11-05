# DNS Cache Snooping & Poisoning
[src](https://www.acunetix.com/vulnerabilities/web/dns-cache-snooping/)

The remote DNS server is vulnerable to DNS cache snooping attacks. DNS cache snooping is the process of determining whether a given Resource Record (RR) is (or not) present on a given DNS cache.
The most effective way to snoop a DNS cache is using iterative queries. One asks the cache for a given resource record of any type (A, MX,CNAME, PTR, etc.) setting the RD (recursion desired) bit in the query to zero.

**3 types of DNS queries—** recursive, iterative, and non-recursive
**3 types of DNS servers—** DNS Resolver DNS Root Server and Authoritative Name Server
**10 types of common DNS records—including** A, AAAA, CNAME, MX and NS
[src](https://ns1.com/resources/dns-types-records-servers-and-queries)

**recursive:** In a recursive query, a DNS client provides a hostname, and the DNS Resolver “must” provide an answer—it responds with either a relevant resource record, or an error message if it can't be found.

**iterative:** In an iterative query, a DNS client provides a hostname, and the DNS Resolver returns the best answer it can. If the DNS resolver has the relevant DNS records in its cache, it returns them. If not, it refers the DNS client to the Root Server, or another Authoritative Name Server which is nearest to the required DNS zone.The DNS client must then repeat the query directly against the DNS server it was referred to.

**non-recursive:** A non-recursive query is a query in which the DNS Resolver already knows the answer. It either immediately returns a DNS record because it already stores it in local cache, or queries a DNS Name Server which is authoritative for the record, meaning it definitely holds the correct IP for that hostname.

[DNS-Root-Servers](https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTqZEqOXK5loFL9NMGuSubY8TNo0zhPq-pnIzRzDFXA0J2lyM3C)

DNS servers create a DNS record to provide important information about a domain or hostname, particularly its current IP address. The most common DNS record types are:

[src](https://www.cs.unc.edu/~fabian/course_papers/cache_snooping.pdf) ++
[src](https://ns1.com/resources/dns-zones-explained) 

[src](https://www.cloudflare.com/learning/dns/dns-cache-poisoning/)

DNS cache poisoning is the act of entering false information into a DNS cache, so that DNS queries return an incorrect response and users are directed to the wrong websites. DNS cache poisoning is also known as 'DNS spoofing.' IP addresses are the 'room numbers' of the Internet, enabling web traffic to arrive in the right places. DNS resolver caches are the 'campus directory,' and when they store faulty information, traffic goes to the wrong places until the cached information is corrected. (Note that this does not actually disconnect the real websites from their real IP addresses.)

#### How do attackers poison DNS caches?

Attackers can poison DNS caches by impersonating DNS nameservers, making a request to a DNS resolver, and then forging the reply when the DNS resolver queries a nameserver. This is possible because DNS servers use UDP instead of TCP, and because currently there is no verification for DNS information.

[Protection](https://www.globalsign.com/en/blog/what-is-dns-cache-poisoning/)

[src](https://www.veracode.com/security/cache-poisoning)

In order to further prevent cache poisoning attacks, IT teams should configure their DNS name servers to:

1. Limit recursive queries.
2. Store only data related to the requested domain.
3. Restrict query responses to only provide information about the requested domain.

### Reports 

[1](https://hackerone.com/reports/487)
[2](https://hackerone.com/reports/492841)
[3](https://hackerone.com/reports/409370) * 
[4](https://hackerone.com/reports/303730)
[5](https://hackerone.com/reports/137756)
[6](https://hackerone.com/reports/394016)
[7](https://hackerone.com/reports/182670)




