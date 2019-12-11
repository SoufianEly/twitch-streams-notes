##IDOR (INSECURE DIRECT OBJECT REFERENCE)

**IDOR occurs when a user supplied input is unvalidated and direct access to the object requested is provided.**


While browsing the web you may have seen lots of parameters like 'uid','id','pid' and many more with some sort of numeric (like id=6672,user=90087).

##### Impact

1. Unauthorized information disclosure

2. Modification or destruction of data.

3. Performing a function outside of the limits of the user


*An object reference map is first populated with a list of authorized values which are temporarily stored in the session. When the user requests a field (ex: color=654321), the application does a lookup in this map from the session to determine the appropriate column name. If the value does not exist in this limited map, the user is not authorized. Reference maps should not be global (i.e. include every possible value), they are temporary maps/dictionaries that are only ever populated with authorized values. A direct object reference occurs when a developer exposes a reference to an internal implementation object, such as a file, directory, database record, or key, as a URL or form parameter.*


#### Proposition

Using a hash allow the following properties:

1. Do not require to maintain a mapping table (real ID vs front end ID) in user session or application level cache.

2. Makes creation of a collection a enumeration values more difficult to achieve because, even if attacker can guess the hash algorithm from the      ID size, it cannot reproduce value due to the salt that is not tied to the hidden value.



#### Find & Exploit 

Run Burpsuite's proxy and start surfing around the webapp. Let the burpsuite's spider fuzz the possible directories and parameters. At the same time, go through the each web pages and always check the parameters which has numbers or username in it. When you find it you are ready to goto next step.

When you find the parameters which have some kind of reference to the internal objects, then you can start play around it. Whenever you see a cookie value from the header or parameter with username in it, try to change that to  other username and see the behaviour. Similarly, check the parameters which has userid, usernumbers and so on then try to modify those numbers and check if you can gain access to other user's data or modify other's data. It is always recommended to create two user accounts and test for it as you will know whether the data is being modified/accessed even if the web app shows error or success message.


Resources : 

https://medium.com/@woj_ciech/explaining-idor-in-almost-real-life-scenario-in-bug-bounty-program-c214008f8378
https://cheatsheetseries.owasp.org/cheatsheets/Insecure_Direct_Object_Reference_Prevention_Cheat_Sheet.html
https://www.owasp.org/images/4/4a/IDOR.pdf
https://www.secjuice.com/idor-insecure-direct-object-reference-definition/
https://www.bugcrowd.com/blog/how-to-find-idor-insecure-direct-object-reference-vulnerabilities-for-large-bounty-rewards/

Reports : 

https://hackerone.com/reports/717716
https://hackerone.com/reports/287789
https://hackerone.com/reports/228383
https://hackerone.com/reports/318751
https://hackerone.com/reports/262661
https://hackerone.com/reports/254151
https://hackerone.com/reports/227522
https://hackerone.com/reports/291721
https://hackerone.com/reports/661978
https://hackerone.com/reports/390346
https://hackerone.com/reports/510759
https://hackerone.com/reports/265284
https://hackerone.com/reports/194594
https://hackerone.com/reports/498351
https://hackerone.com/reports/302485
https://hackerone.com/reports/404797
https://hackerone.com/reports/245872

