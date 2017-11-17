package ws;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement(name = "persona")
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1772950919187867502L;
	String nome;
	String cognome;
	ArrayList<String> telefoni;

	public Persona() {
		// TODO Auto-generated constructor stub
	}
	
	public Persona(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		telefoni = new ArrayList<String>();
		telefoni.add("samsung");
		telefoni.add("iphone");
	}

	public String getCognome() {
		return cognome;
	}

	@XmlElement
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@XmlElement
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	@XmlElement
	public void setTelefoni(ArrayList<String> telefoni) {
		this.telefoni = telefoni;
	}
	
	public ArrayList<String> getTelefoni() {
		return telefoni;
	}
}
