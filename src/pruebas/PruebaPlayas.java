package pruebas;

import java.io.FileNotFoundException;

import playas.*;

public class PruebaPlayas {

	static final String[] playas = { "El Candado;M�laga;36.715;-4.344;65", "La Ara�a;M�laga;36.712;-4.321;75",
			"Los �lamos;Torremolinos;36.635;-4.486;400", "De la V�bora;Marbella;36.490;-4.775;130" };

	public static void main(String[] args) throws FileNotFoundException {
		try {
			GestionPlayas gp = new GestionPlayas("M�laga", playas);
			System.out.println(gp);
			System.out.println("----");

			PlayaRestringida p3 = new PlayaRestringida("La carihuela", "Torremolinos", 36.607, -4.504, 200);
			p3.setPorcentaje(50);
			gp.incluye(p3);
			System.out.println(gp);
			System.out.println("----");

			gp.registrarOcupacion("datos.txt");
			System.out.println(gp);
			System.out.println("----");

			Playa p1 = gp.primeraPlayaConEstado("VACIO");

			System.out.println("--- Primera playa encontrada ---");
			System.out.println(p1);
			Playa p2 = gp.primeraPlayaConEstado("SATURADO");
			System.out.println(p2);
		} catch (PlayaException e) {
			System.err.println(e.getMessage());
		}
	}
}

/*
 * SALIDA se han a�adido retornos de carro para facilitar la legibilidad
 * 
 * M�laga [(El Candado, M�laga, 36.715, -4.344, 65, 0 - Nivel de aforo: VACIO),
 * (La Ara�a, M�laga, 36.712, -4.321, 75, 0 - Nivel de aforo: VACIO), (Los
 * �lamos, Torremolinos, 36.635, -4.486, 400, 0 - Nivel de aforo: VACIO), (De la
 * V�bora, Marbella, 36.49, -4.775, 130, 0 - Nivel de aforo: VACIO)] ---- M�laga
 * [(El Candado, M�laga, 36.715, -4.344, 65, 0 - Nivel de aforo: VACIO), (La
 * Ara�a, M�laga, 36.712, -4.321, 75, 0 - Nivel de aforo: VACIO), (Los �lamos,
 * Torremolinos, 36.635, -4.486, 400, 0 - Nivel de aforo: VACIO), (De la V�bora,
 * Marbella, 36.49, -4.775, 130, 0 - Nivel de aforo: VACIO), [(La carihuela,
 * Torremolinos, 36.607, -4.504, 200, 0 - Nivel de aforo: VACIO), 50%]] ----
 * M�laga [(El Candado, M�laga, 36.715, -4.344, 65, 32 - Nivel de aforo:
 * MODERADO), (La Ara�a, M�laga, 36.712, -4.321, 75, 0 - Nivel de aforo: VACIO),
 * (Los �lamos, Torremolinos, 36.635, -4.486, 400, 250 - Nivel de aforo:
 * MODERADO), (De la V�bora, Marbella, 36.49, -4.775, 130, 13 - Nivel de aforo:
 * LEVE), [(La carihuela, Torremolinos, 36.607, -4.504, 200, 120 - Nivel de
 * aforo: SIN ESPACIO), 50%]] ---- --- Primera playa encontrada --- (La Ara�a,
 * M�laga, 36.712, -4.321, 75, 0 - Nivel de aforo: VACIO) No se ha encontrado
 * ninguna playa con estado SATURADO
 */
