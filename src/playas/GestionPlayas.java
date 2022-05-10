package playas;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;

public class GestionPlayas {

	private ArrayList<Playa> playas;
	private String provincia;

	public GestionPlayas(String p) {
		provincia = p;
		playas = new ArrayList<>();
	}

	public GestionPlayas(String p, String[] array) {
		provincia = p;
		procesarPlayas(array);
	}

	private void procesarPlayas(String[] array) {
		playas = new ArrayList<>();

		for (String linea : array) {
			try (Scanner sc = new Scanner(linea)) {
				sc.useDelimiter("\\s*[;]\\s*");
				sc.useLocale(Locale.ENGLISH);

				incluye(new Playa(sc.next(), sc.next(), sc.nextDouble(), sc.nextDouble(), sc.nextInt()));
			} catch (PlayaException e) {
				e.getMessage();

			} catch (InputMismatchException e) {
				System.out.println("ERROR. Valor no numerico");

			} catch (NoSuchElementException e) {
				System.out.println("ERROR. Faltan datos");
			}
		}
	}

	private int posicion(Playa p) {

		for (int i = 0; i < playas.size(); i++) {
			if (playas.get(i).equals(p))
				return i;
		}

		return -1;
	}

	public void incluye(Playa p) {
		if (posicion(p) != -1)
			playas.set(posicion(p), p);
		else
			playas.add(p);
	}

	private Playa buscar(double lat, double longi) {

		if (posicion(new Playa("nombre", "localidad", lat, longi, 0)) == -1)
			throw new PlayaException("La playa no se encuentra en la lista");
		else
			return playas.get(posicion(new Playa("nombre", "localidad", lat, longi, 0)));

	}

	public void registrarOcupacion(String nomFich) {
		Path entrada = Path.of(nomFich);

		try (Scanner sc = new Scanner(entrada)) {
			sc.useDelimiter("\\s*[;]\\s*");
			sc.useLocale(Locale.ENGLISH);

			while (sc.hasNextLine()) {

				String[] split = sc.nextLine().split(";");

				playas.get(posicion(buscar(Double.valueOf(split[0]), Double.valueOf(split[1]))))
						.setNumPersonas(Integer.valueOf(split[2]));

			}

		} catch (IOException e) {

			System.out.println("ERROR: Archivo no encontrado");

		} catch (InputMismatchException e) {

			System.out.println("ERROR. Valor no numerico");

		} catch (NoSuchElementException e) {

			System.out.println("ERROR. Faltan datos");
		}
	}

	public Playa primeraPlayaConEstado(String estado) {
		for (Playa p : playas) {
			if (p.getEstado().equals(estado))
				return p;
		}

		throw new PlayaException("ERROR: No existe ninguna playa con el siguiente estado: " + estado);
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");

		for (Playa p : playas) {
			sj.add(p.toString());
		}

		return provincia + " Playas: " + sj.toString();
	}
}
