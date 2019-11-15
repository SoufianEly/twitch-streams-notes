package com.java.networking;

import java.io.*;
import java.net.*;


public class App {

	
	
	
	
	public static void main(String []args)  {
		
		try {

			String inputL;
			
			/*URL url = new URL("https://www.bbc.com/news");
			url = new URL("https", "bbc.com",443,"/");
			
			
			
			URL main_url = new URL("https://www.bbc.com/");
			URL page1 = new URL(main_url,"news");
			URL page2 = new URL(main_url,"news/world");
			
			URI uri = new URI("http", "bbc.com", "/news", "");
			URL url_ = uri.toURL();

			System.out.println(url_);
			
			
			URL url3 = new URL("https://www.bbc.com:443/news?page=4&r=3#bottom");
			
			System.out.println("Port : " + url3.getPort());
			
			System.out.println("Host : " + url3.getHost());
			System.out.println("File : " + url3.getFile());
			System.out.println("Query :" + url3.getQuery());
			System.out.println("Content : " + url3.getContent());
			System.out.println("Authority : " + url3.getAuthority());
			System.out.println("Ref : " + url3.getRef());*/
			
			/*
			URL url = new URL("https://www.bbc.com");
			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
			bf.readLine();
			
			while((inputL = bf.readLine()) != null)
				System.out.println(inputL);
			
			bf.close();*/
			
			URL url = new URL("https://www.bbc.com/news");
			URLConnection connection = url.openConnection();
			
			connection.connect();
			System.out.println(connection.getHeaderField("Server"));
			System.out.println(connection.getHeaderField("Date"));

			System.out.println(connection.getHeaderField("Content-Length"));

			
			
			
		}catch(MalformedURLException  e) {
			
			
		}catch(IOException e) {
			
		}
		
		
		
		
	}
	
	
	
	
}
