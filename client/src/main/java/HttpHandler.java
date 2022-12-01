package main.java;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpHandler {

	public static void main(String[] args) {
		try {
			
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:3000/watchlist")).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
