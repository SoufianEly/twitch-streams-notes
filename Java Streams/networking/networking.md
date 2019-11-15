# NETWORKING

```java
URL myURL = new URL("http://example.com/");
```

```java
	
URL main_url = new URL("https://www.bbc.com/");
URL page1 = new URL(main_url,"news");
URL page2 = new URL(main_url,"news/world");


URL url = new URL("http://example.com/pages/page1.html");


URI uri = new URI("http", "bbc.com", "/news", "");
URL url_ = uri.toURL();

```

**MalformedURLException**

Each of the four URL constructors throws a MalformedURLException if the arguments to the constructor refer to a null or unknown protocol. Typically, you want to catch and handle this exception by embedding your URL constructor statements in a try/catch pair, like this:

Note: URLs are "write-once" objects. Once you've created a URL object, you cannot change any of its attributes (protocol, host name, filename, or port number).

```java

			URL url3 = new URL("https://www.bbc.com:443/news?page=4&r=3#bottom");
			System.out.println("Port : " + url3.getPort());
			System.out.println("Host : " + url3.getHost());
			System.out.println("File : " + url3.getFile());
			System.out.println("Query :" + url3.getQuery());
			System.out.println("Content : " + url3.getContent());
			System.out.println("Authority : " + url3.getAuthority());
			System.out.println("Ref : " + url3.getRef());

        Output    
            Port : 443
            Host : www.bbc.com
            File : /news?page=4&r=3
            Query :page=4&r=3
            Content : sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@69d9c55
            Authority : www.bbc.com:443
            Ref : bottom
```

```java

	URL url = new URL("https://www.bbc.com");
	BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
	bf.readLine();
			
	while((inputL = bf.readLine()) != null)
		System.out.println(inputL);
			
	bf.close();
			
```

```java
		URL url = new URL("https://www.bbc.com");
		URLConnection connection = url.openConnection();
	    connection.connect();
        System.out.println(connection.getHeaderField("Server"));
		System.out.println(connection.getHeaderField("Date"));
		System.out.println(connection.getHeaderField("Content-Length"));

```

A new URLConnection object is created every time by calling the openConnection method of the protocol handler for this URL.

## Sockets

URLs and URLConnections provide a relatively high-level mechanism for accessing resources on the Internet. Sometimes your programs require lower-level network communication, for example, when you want to write a client-server application.

