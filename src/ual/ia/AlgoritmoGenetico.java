package ual.ia;
import java.util.ArrayList;
import java.util.stream.IntStream;
public class AlgoritmoGenetico {
	public static final double RATIO_MUTACION = 0.25;
	public static final int TAMANO_SELECCION_TORNEO = 3;
	public static final int TAMANO_POBLACION = 8;
	public static final int NUMERO_RUTAS_ELITE = 1;
	public static final int NUMERO_GENERACIONES = 30;
	private ArrayList<Ciudad> rutaInicial = null;
	
	public AlgoritmoGenetico(ArrayList<Ciudad> rutaInicial) { 
		this.rutaInicial = rutaInicial; 
	}
	
	public ArrayList<Ciudad> getRutaInicial() { 
		return rutaInicial; 
	}
	
	public Poblacion evolucion(Poblacion poblacion) { 
		return mutarPoblacion(cruzarPoblacion(poblacion)); 
	}
	
	Poblacion cruzarPoblacion(Poblacion poblacion) {
		Poblacion cruzarPoblacion = new Poblacion(poblacion.getRutas().size(), this);
		IntStream.range(0, NUMERO_RUTAS_ELITE).forEach(x -> cruzarPoblacion.getRutas().set(x, poblacion.getRutas().get(x)));
		IntStream.range(NUMERO_RUTAS_ELITE, cruzarPoblacion.getRutas().size()).forEach(x -> {
			Ruta ruta1 = seleccionarPoblacionTorneo(poblacion).getRutas().get(0);
            Ruta ruta2 = seleccionarPoblacionTorneo(poblacion).getRutas().get(0);
            cruzarPoblacion.getRutas().set(x, cruzarRuta(ruta1, ruta2));
		});
		return cruzarPoblacion;
	}
	
	Poblacion mutarPoblacion(Poblacion poblacion) {
		poblacion.getRutas().stream().filter(x -> poblacion.getRutas().indexOf(x) >= NUMERO_RUTAS_ELITE).forEach(x -> mutarRuta(x));
		return poblacion;
	}
	
	Ruta cruzarRuta(Ruta ruta1, Ruta ruta2) {
		Ruta cruzarRuta = new Ruta(this);
		Ruta RutaTemporal1 = ruta1;
		Ruta RutaTemporal2 = ruta2;
		if (Math.random() < 0.5) {
			RutaTemporal1 = ruta2;
			RutaTemporal2 = ruta1;
		} 
		for (int x = 0; x < cruzarRuta.getCiudades().size()/2; x++)
			cruzarRuta.getCiudades().set(x, RutaTemporal1.getCiudades().get(x));
		return quitarNulosCruceRutas(cruzarRuta, RutaTemporal2);
	}
	
	private Ruta quitarNulosCruceRutas(Ruta cruzarRuta, Ruta ruta) {
		ruta.getCiudades().stream().filter(x -> !cruzarRuta.getCiudades().contains(x)).forEach(cityX -> {
    		for (int y = 0; y < ruta.getCiudades().size(); y++) {
    			if (cruzarRuta.getCiudades().get(y) == null) {
    				cruzarRuta.getCiudades().set(y, cityX);
                    break;
                }
            }
    	});
		return cruzarRuta;
	}
	
	Ruta mutarRuta(Ruta ruta) {
		ruta.getCiudades().stream().filter(x -> Math.random() < RATIO_MUTACION).forEach(cityX -> {
    		int y = (int) (ruta.getCiudades().size() * Math.random());
            Ciudad cityY = ruta.getCiudades().get(y);
            ruta.getCiudades().set(ruta.getCiudades().indexOf(cityX), cityY);
            ruta.getCiudades().set(y, cityX);
    	});
        return ruta;
	}
	
	Poblacion seleccionarPoblacionTorneo(Poblacion poblacion) {
		Poblacion tournamentPopulation = new Poblacion(TAMANO_SELECCION_TORNEO, this);
		IntStream.range(0, TAMANO_SELECCION_TORNEO).forEach(x -> tournamentPopulation.getRutas().set(
        		x, poblacion.getRutas().get((int) (Math.random() * poblacion.getRutas().size()))));
		tournamentPopulation.ordenarRutasPorFitness();
		return tournamentPopulation;
	}
}
