import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BuscaListaFilmes {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("Buscador de Filmes no IMDB");
		
		String json = buscarFilmes();
		
		
		String listaFilmesJunto = json.substring(json.indexOf('[') + 1, json.indexOf(']'));
		String [] listaFilmes = listaFilmesJunto.split("(?<=}),");
	

		
		//System.out.println(listaFilmesJunto);
		System.out.println(listaFilmes[0]);
		System.out.println(listaFilmes[1]);
		
		
		List<String> titles = new ArrayList<String>();
		
		for (int i = 0; i < listaFilmes.length; i++) {
			titles.add(listaFilmes[i].substring(listaFilmes[i].indexOf("\"title\":") + 8, 
					listaFilmes[i].indexOf(",", listaFilmes[i].indexOf("\"title\":"))));
		}
		
		titles.forEach(System.out::println);
		
		List<String> images = new ArrayList<String>();
		
		for (int i = 0; i < listaFilmes.length; i++) {
			images.add(listaFilmes[i].substring(listaFilmes[i].indexOf("\"image\":") + 9, 
					listaFilmes[i].indexOf("\",", listaFilmes[i].indexOf("\"image\":"))));
		}
		
		images.forEach(System.out::println);
		
	}

	private static String buscarFilmes() throws IOException, InterruptedException {
		// A chave da Api ficou na classe ApiKey que não está no github:
		// Caso prefira, apague ou comente a linha do getApikey e faça uma atribuição direta:
		// String apiKey = "suaApiKey";
		String apiKey = new ApiKey().getApiKey();
		
			
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
			      .header("Content-Type", "application/json")
			      .GET()
			      .build();
		
		HttpClient client = HttpClient.newBuilder().build();
		
		// HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		return client.send(request, BodyHandlers.ofString()).body();
	}

}
