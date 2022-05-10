package playas;

public class PlayaRestringida extends Playa {

	private int porcentaje;

	public PlayaRestringida(String n, String l, double lat, double longi, int aforo) {
		super(n, l, lat, longi, aforo);
		porcentaje = 0;
	}

	public void setPorcentaje(int p) {
		if (p < 0 || p > 100)
			throw new PlayaException("ERROR. El porcentaje no se encuentra en el rango apropiado: " + porcentaje);

		porcentaje = p;
	}

		@Override
	public void setNumPersonas(int n) {
		super.setNumPersonas(n);

		if (n == 0)
			estado = "VACIO";
		else if (n > 0 && n < super.getAforoMaximo() * 0.25 * porcentaje/100)
			estado = "LEVE";
		else if (n > super.getAforoMaximo() * 0.25 * porcentaje/100 && n < super.getAforoMaximo() * 0.75 * porcentaje/100)
			estado = "MODERADO";
		else if (n > super.getAforoMaximo() * 0.75 * porcentaje/100 && n < super.getAforoMaximo() * porcentaje/100)
			estado = "SATURADO";
		else if (n >= super.getAforoMaximo() * porcentaje/100)
			estado = "SIN ESPACIO";
	}

	@Override
	public String toString() {
		return "[" + super.toString() + ", " + porcentaje + "%]";
	}
}
