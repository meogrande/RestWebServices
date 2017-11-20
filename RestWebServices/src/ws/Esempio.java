package ws;
import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("esempio")
public class Esempio {
	/**
	 * Restituisce del testo normale
	 * <a href="/RestWebServices/rest/esempio/testo">qui</a>
	 * @return a String
	 */
	@GET
	@Path("testo")
	@Produces(MediaType.TEXT_PLAIN)
	public String testo() {
		return "Ciao Mondo";
	}

	/**
	 * 
	 * @return Restituisce un xml di prova
	 */
	@GET
	@Path("xml")
	@Produces(MediaType.TEXT_XML)
	public String xml() {
		return "<ciao>Ciao Mondo</ciao>";
	}

	/**
	 * Restituisce un xml generato automaticamente da un oggetto "Persona" con nome e cognome <br>
	 * 
	 * @return
	 */
	@GET
	@Path("persona")
	@Produces(MediaType.TEXT_XML)
	public Persona persona() {
		// return "<ciao>Ciao Mondo</ciao>";
		return new Persona("fabio", "biscaro");
	}

	/**
	 * <Restituisce una stringa json generata automaticamente da un oggetto java
	 * <br><a href="/RestWebServices/rest/esempio/jsonpersona">prova</a>
	 * @return una strina json
	 */
	@GET
	@Path("jsonpersona")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona jsonPersona() {
		// return "<ciao>Ciao Mondo</ciao>";
		return new Persona("Andrea", "Rossi");
	}

	@GET
	@Path("persone")
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Persona> persone() {
		// return "<ciao>Ciao Mondo</ciao>";
		ArrayList<Persona> persone = new ArrayList<Persona>();
		persone.add(new Persona("Andrea", "Rossi"));
		persone.add(new Persona("Mario", "Rossi"));
		return persone;
	}

	/**
	 * <a href="/RestWebServices/rest/esempio/json">Restituisce una stringa json</a>
	 * @return una stringa json di prova
	 */
	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public String json() {
		return "{\"age\":10, \"name\":\"fabio\", \"address\": {\"street\":\"roma\", \"number\":7}}";
	}

	/**
	 * <a href="/RestWebServices/rest/esempio/html">Restituisce un testo html</a>
	 * @return am html snippet
	 */
	@GET
	@Path("html")
	@Produces(MediaType.TEXT_HTML)
	public String html() {
		return "<p>Ciao Mondo</p>";
	}

	/**
	 * <a href="http://RestWebServices/rest/esempio/post">Riceve un json e lo stampa</a>
	 * http://localhost:8080/RestWebServices/rest/esempio/post
	 * @param uno input post data
	 * @return una stringa tanto per restituire qualcosa
	 */
	@POST
	@Path("post")
	public String post(String uno) {
		// Uno è un json
		System.out.println("E' arrivato: " + uno);
		// Faccio il parsing
		JsonParser parser = Json.createParser(new StringReader(uno));
		String key = null;
		String value = null;
		while (parser.hasNext()) {
			final Event event = parser.next();
			switch (event) {
			case KEY_NAME:
				key = parser.getString();
				System.out.println("Name: " + key);
				break;
			case VALUE_NUMBER:
				int i = parser.getInt();
				System.out.println("Integer: " + i);
				break;
			case VALUE_STRING:
				value = parser.getString();
				System.out.println("String: " + value);
				break;
			}
		}
		parser.close();
		return "Hai inviato con successo: " + uno;
	}

}
