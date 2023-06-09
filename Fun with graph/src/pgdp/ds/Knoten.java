package pgdp.ds;

public class Knoten {

	private int id;
	private SimpleSet nachbarn = new SimpleSet();

	public Knoten(int id) {
		this.id = id;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleSet getNachbarn() {
		return nachbarn;
	}

	public void setNachbarn(SimpleSet nachbarn) {
		this.nachbarn = nachbarn;
	}

}
