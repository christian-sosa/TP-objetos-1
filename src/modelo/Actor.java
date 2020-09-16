package modelo;

public abstract class Actor {

    private int id;
    private Contacto contacto;

    public Actor(int id, Contacto contacto) {
        this.id = id;
        this.contacto = contacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "Actor [id=" + id + ", contacto=" + contacto + "]";
	}

}
