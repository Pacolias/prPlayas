package playas;

public class Playa {

	private String nombre;
	private String localidad;
	private final double latitud;
	private final double longitud;
	private final int aforoMax;
	private int numPersonas;
	protected String estado;

	public Playa(String n, String l, double lat, double longi, int aforo) {
		if (aforo < 0)
			throw new PlayaException("ERROR: Aforo negativo");

		nombre = n;
		localidad = l;
		latitud = lat;
		longitud = longi;
		aforoMax = aforo;
		estado = "VACIO";
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public int getAforoMaximo() {
		return aforoMax;
	}

	public String getEstado() {
		return estado;
	}

	public void setNumPersonas(int n) {
		if (n < 0)
			throw new PlayaException("ERROR: Personas negativas");

		numPersonas = n;

		if (n == 0)
			estado = "VACIO";
		else if (n > 0 && n < aforoMax * 0.25)
			estado = "LEVE";
		else if (n > aforoMax * 0.25 && n < aforoMax * 0.75)
			estado = "MODERADO";
		else if (n > aforoMax * 0.75 && n < aforoMax)
			estado = "SATURADO";
		else
			estado = "SIN ESPACIO";

	}

	@Override
	public boolean equals(Object o) {
		boolean res = false;

		if (o instanceof Playa) {
			Playa p = (Playa) o;
			res = (p.latitud == this.latitud) && (p.longitud == this.longitud);
		}

		return res;
	}

	@Override
	public int hashCode() {
		return Double.valueOf(latitud).hashCode() + Double.valueOf(longitud).hashCode();
	}

	@Override
	public String toString() {
		return "(" + nombre + ", " + localidad + ", " + latitud + ", " + longitud + ", " + aforoMax + ", " + numPersonas
				+ " - Nivel de aforo: " + estado + ")";
	}

}
