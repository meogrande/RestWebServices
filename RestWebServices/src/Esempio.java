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
	 * http://localhost:8080/RestWebServices/rest/esempio/testo
	 * @return
	 */
	@GET
	@Path("testo")
	@Produces(MediaType.TEXT_PLAIN)
	public String testo() {
		return "Ciao Mondo";
	}

	@GET
	@Path("xml")
	@Produces(MediaType.TEXT_XML)
	public String xml() {
		return "<ciao>Ciao Mondo</ciao>";
	}

	@GET
	@Path("persona")
	@Produces(MediaType.TEXT_XML)
	public Persona persona() {
		// return "<ciao>Ciao Mondo</ciao>";
		return new Persona("fabio", "biscaro");
	}

	@GET
	@Path("jsonpersona")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona jsonPersona() {
		// return "<ciao>Ciao Mondo</ciao>";
		return new Persona("fabio", "biscaro");
	}

	@GET
	@Path("persone")
	@Produces(MediaType.TEXT_XML)
	public ArrayList<Persona> persone() {
		// return "<ciao>Ciao Mondo</ciao>";
		ArrayList<Persona> persone = new ArrayList<Persona>();
		persone.add(new Persona("fabio", "biscaro"));
		persone.add(new Persona("giulio", "biscaro"));
		return persone;
	}

	@GET
	@Path("json")
	@Produces(MediaType.APPLICATION_JSON)
	public String json() {
		return "{\"age\":10}";
	}

	/**
	 * Restituisce un testo html
	 * http://localhost:8080/RestWebServices/rest/esempio/html
	 * @return
	 */
	@GET
	@Path("html")
	@Produces(MediaType.TEXT_HTML)
	public String html() {
		return "<p>Ciao Mondo</p>";
	}

	/**
	 * Riceve un json e lo stampa
	 * http://localhost:8080/RestWebServices/rest/esempio/post
	 * @param uno
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
		return "ciao";
	}

}
