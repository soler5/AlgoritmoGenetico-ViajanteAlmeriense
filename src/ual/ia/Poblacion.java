package ual.ia;
import java.util.ArrayList;
import java.util.stream.IntStream;
public class Poblacion {
	
	private ArrayList<Ruta> rutas = new ArrayList<Ruta>(AlgoritmoGenetico.TAMANO_POBLACION); 
	
	public Poblacion(int tamanoPoblacion, AlgoritmoGenetico algoritmoGenetico) { 
		IntStream.range(0, tamanoPoblacion).forEach(x -> rutas.add(new Ruta(algoritmoGenetico.getRutaInicial())));
    }
	
	public Poblacion(int tamanoPoblacion, ArrayList<Ciudad> ciudades) { 
		IntStream.range(0, tamanoPoblacion).forEach(x -> rutas.add(new Ruta(ciudades)));
	}
	
	public ArrayList<Ruta> getRutas() { 
		return rutas;
	}
	
	public void ordenarRutasPorFitness() {
		rutas.sort((ruta1, ruta2) -> {
			int flag = 0;
			if (ruta1.getFitness() > ruta2.getFitness()) flag = -1;
			else if (ruta1.getFitness() < ruta2.getFitness()) flag = 1;
			return flag;
		});
	}
}
