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

SELECT * FROM items WHERE owner = 'wiley' AND itemname = 'name' **OR 'a'='a'**;

### Attack Intent

**Identifying injectable parameters**

**Determining database schema**

**Extracting data**

**Performing denial of service**

**Evading detection**

**Bypassing authentication**

**Executing remote commands**

**Performing privilege escalation**

**Performing database finger-printing**


### Injection Mechanisms

##### Injection through user input 

In this case, attackers inject SQL commands by providing suitably crafted user input. [4]

##### Injection through cookies

When a client returns to a Web application, cookies can be used to restore the client’s state information. Since the client
has control over the storage of the cookie, a malicious client could tamper with the cookie’s contents. If a Web application uses the
cookie’s contents to build SQL queries, an attacker could easily submit an attack by embedding it in the cookie.

##### Injection through server variables
Server variables are a collection of variables that contain HTTP, network headers, and environmental variables. Web applications use these server variables in a variety of ways, such as logging usage statistics and identifying browsing trends. If these variables are logged to a database without sanitization, this could create an SQL injection vulnerability. [1]

##### Second-order injection

In second-order injections, attackers seed malicious inputs into a system or database to indirectly trigger an SQLIA when that input is used at a later time.

---------------

##### CWE-943: Improper Neutralization of Special Elements in Data Query Logic

The application generates a query intended to access or manipulate data in a data store such as a database, but it does not neutralize or incorrectly neutralizes special elements that can modify the intended logic of the query.

##### CWE-74: Improper Neutralization of Special Elements in Output Used by a Downstream Component ('Injection')

The software constructs all or part of a command, data structure, or record using externally-influenced input from an upstream component, but it does not neutralize or incorrectly neutralizes special elements that could modify how it is parsed or interpreted when it is sent to a downstream component.

---------------
#### Defensive Coding Practices

**Input type checking**
Encoding of inputs
Positive pattern matching
Identification of all input sources
Pseudo remedies


### Resources
---

**Reading**

https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet

https://www.acunetix.com/blog/articles/in-depth-sql-injection/

https://www.cc.gatech.edu/fac/Alex.Orso/papers/halfond.viegas.orso.ISSSE06.pdf

https://www.owasp.org/index.php/SQL_Injection_Bypassing_WAF

https://medium.com/bugbountywriteup/sql-injection-with-load-file-and-into-outfile-c62f7d92c4e2

https://medium.com/@tomnomnom/making-a-blind-sql-injection-a-little-less-blind-428dcb614ba8

https://medium.com/bugbountywriteup/sql-injection-time-and-boolean-based-27239b6a55e8



**Bug Bounty Reports**

[1] https://hackerone.com/reports/297478

[2] https://hackerone.com/reports/531051

[3] https://hackerone.com/reports/446293

[4] https://hackerone.com/reports/363815

[5] https://hackerone.com/reports/374027

[6] https://hackerone.com/reports/353784

[7] https://hackerone.com/reports/319279

[8] https://hackerone.com/reports/384397

[9] https://hackerone.com/reports/419017

[10] https://hackerone.com/reports/435066

[11] https://hackerone.com/reports/358570

[12] https://hackerone.com/reports/506654

[13] https://hackerone.com/reports/549355

[14] https://hackerone.com/reports/310031

[15] https://hackerone.com/reports/381758

[16] https://hackerone.com/reports/179751

[17] https://hackerone.com/reports/164739

[18] https://hackerone.com/reports/311244

[19] https://hackerone.com/reports/150156

[20] https://hackerone.com/reports/374748

[21] https://hackerone.com/reports/200818

[22] https://hackerone.com/reports/178057

[23] https://hackerone.com/reports/381771


-------------------

## 1. Command Injection



Command injection is an attack in which the goal is execution of arbitrary commands on the host operating system via a vulnerable application. Command injection attacks are possible when an application passes unsafe user supplied data (forms, cookies, HTTP headers etc.) to a system shell.

#### Blind Command Injection

Many instances of OS command injection are blind vulnerabilities. This means that the application does not return the output from the command within its HTTP response. Blind vulnerabilities can still be exploited, but different techniques are required

#### Detecting Blind Command Injection 

You can use an injected command that will trigger a time delay, allowing you to confirm that the command was executed based on the time that the application takes to respond. The ping command is an effective way to do this, as it lets you specify the number of ICMP packets to send, and therefore the time taken for the command to run:

& ping -c 10 127.0.0.1 &

This command will cause the application to ping its loopback network adapter for 10 seconds.

http://sensitive/cgi-bin/userData.pl?doc=user1.txt
http://sensitive/cgi-bin/userData.pl?doc=/bin/ls|
http://sensitive/something.php?dir=%3Bcat%20/etc/passwd

```
POST http://www.example.com/public/doc HTTP/1.1
Host: www.example.com
User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1) Gecko/20061010 FireFox/2.0
Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5
Accept-Language: it-it,it;q=0.8,en-us;q=0.5,en;q=0.3
Accept-Encoding: gzip,deflate
Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
Keep-Alive: 300
Proxy-Connection: keep-alive
Referer: http://127.0.0.1/WebGoat/attack?Screen=20
Cookie: JSESSIONID=295500AD2AAEEBEDC9DB86E34F24A0A5
Authorization: Basic T2Vbc1Q9Z3V2Tc3e=
Content-Type: application/x-www-form-urlencoded
Content-length: 33

Doc=Doc1.pdf+|+Dir c:\
```

#### Code Review Dangerous API

#####Java

Runtime.exec()

getParameter

getRuntime.exec()

ProcessBuilder.start()

setAttribute putValue getValue

java.net.Socket java.io.fileInputStream java.io.FileReader

#####C/C++

system

exec

ShellExecute

execlp

#####Python

exec

eval

os.system

os.popen

subprocess.popen

subprocess.call

#####PHP

system

shell_exec

exec

proc_open

eval

passthru

proc_open

expect_open

ssh2_exec

popen

#####Perl

CGI.pm

referer

cookie

ReadParse

#####ASP.NET

HttpRequest.Params
HttpRequest.Url
HttpRequest.Item

Tool : https://github.com/commixproject/commix


### Resources
---

**Reading**

https://www.owasp.org/index.php/Command_Injection
https://portswigger.net/web-security/os-command-injection
https://www.checkmarx.com/knowledge/knowledgebase/OS-Command_Injection
https://medium.com/bugbountywriteup/command-injection-poc-72cc3743f10d
https://medium.com/hackernoon/nodejs-security-issue-javascript-node-example-tutorial-vulnerabilities-hack-line-url-command-injection-412011924d1b
https://medium.com/@malcomvetter/the-legendary-command-injection-via-password-a57ace31d88

**Reports**

https://hackerone.com/reports/685447
https://hackerone.com/reports/497312
https://hackerone.com/reports/449482
https://hackerone.com/reports/495382
https://hackerone.com/reports/389561
https://hackerone.com/reports/654888
https://hackerone.com/reports/388936
https://hackerone.com/reports/340208
https://hackerone.com/reports/654888
https://hackerone.com/reports/390848
https://hackerone.com/reports/532667
https://hackerone.com/reports/330957
https://hackerone.com/reports/494979
https://hackerone.com/reports/327512
https://hackerone.com/reports/546753

