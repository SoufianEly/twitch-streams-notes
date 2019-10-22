[src](https://www.owasp.org/index.php/Top_10-2017_A1-Injection)

# INJECTIONS

### Security Weakness	

Injection flaws are very prevalent, particularly in legacy code. Injection vulnerabilities are often found in SQL, LDAP, XPath, or NoSQL queries, OS commands, XML parsers, SMTP headers, expression languages, and ORM queries.

Injection flaws are easy to discover when examining code. Scanners and fuzzers can help attackers find injection flaws.

### Impacts 

Injection can result in data loss, corruption, or disclosure to unauthorized parties, loss of accountability, or denial of access. 

Injection can sometimes lead to complete host takeover.

The business impact depends on the needs of the application and data.

## 1. SQL Injection

SQL injection attacks are one of the most common techniques hackers use to access secure information from web servers to carry out illegitimate activities.

##### Basic Logic

string query = "SELECT * FROM items WHERE owner = '" + userName + "' AND itemname = '" + ItemName.Text + "'";
SELECT * FROM items WHERE owner = &lt;userName&gt; AND itemname = &lt;itemName&gt;;
name' OR 'a'='a
SELECT * FROM items WHERE owner = 'wiley' AND itemname = 'name' **OR 'a'='a'**;

#### Attack Intent

**Identifying injectable parameters**
**Determining database schema**
**Extracting data**
**Performing denial of service**
**Evading detection**
**Bypassing authentication**
**Executing remote commands**
**Performing privilege escalation**
**Performing database finger-printing**

#### Injection Mechanisms

##### Injection through user input 

In this case, attackers inject SQL commands by providing suitably crafted user input

##### Injection through cookies

When a client returns to a Web application, cookies can be used to restore the client’s state information. Since the client
has control over the storage of the cookie, a malicious client could tamper with the cookie’s contents. If a Web application uses the
cookie’s contents to build SQL queries, an attacker could easily submit an attack by embedding it in the cookie.

##### Injection through server variables
Server variables are a collection of variables that contain HTTP, network headers, and environmental variables. Web applications use these server variables in a variety of ways, such as logging usage statistics and identifying browsing trends. If these variables are logged to a database without sanitization, this could create an SQL injection vulnerability.

##### Second-order injection

In second-order injections, attackers seed malicious inputs into a system or database to indirectly trigger an SQLIA when that input is used at a later time.



SQL injection can be resultant from special character mismanagement, MAID, or blacklist/whitelist problems. It can be primary to authentication errors.


##### CWE-943: Improper Neutralization of Special Elements in Data Query Logic

The application generates a query intended to access or manipulate data in a data store such as a database, but it does not neutralize or incorrectly neutralizes special elements that can modify the intended logic of the query.

##### CWE-74: Improper Neutralization of Special Elements in Output Used by a Downstream Component ('Injection')

The software constructs all or part of a command, data structure, or record using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify how it is parsed or interpreted when it is sent to a downstream component.




Src

https://www.acunetix.com/blog/articles/in-depth-sql-injection/
https://www.cc.gatech.edu/fac/Alex.Orso/papers/halfond.viegas.orso.ISSSE06.pdf