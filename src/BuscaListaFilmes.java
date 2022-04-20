import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class BuscaListaFilmes {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Buscador de Filmes no IMDB");
		
		String apiKey = "suaAPI";
			
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
			      .header("Content-Type", "application/json")
			      .GET()
			      .build();
		
		HttpClient client = HttpClient.newBuilder().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		System.out.println(response.body());
	}

}
