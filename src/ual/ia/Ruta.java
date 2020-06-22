package ual.ia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Ruta {
	
	private double fitness = 0;
	private ArrayList<Ciudad> ciudades =  new ArrayList<Ciudad>();
	
	public Ruta(AlgoritmoGenetico geneticAlgorithm) { 
    	geneticAlgorithm.getRutaInicial().forEach(x -> ciudades.add(null));
    }
	
	public Ruta(ArrayList<Ciudad> cities) { 
		this.ciudades.addAll(cities);
		Collections.shuffle(this.ciudades);  
	}
	
	public ArrayList<Ciudad> getCiudades() {
		return ciudades;
	}
	
	public double getFitness() {  
		fitness = (1/calcularDistanciaTotal())*10000;
		return fitness;
	}
	
	public double calcularDistanciaTotal() {
		int citiesSize = this.ciudades.size();
		return (int) (this.ciudades.stream().mapToDouble(x -> {
			int cityIndex = this.ciudades.indexOf(x);
			double returnValue = 0;
			if (cityIndex < citiesSize - 1) 
				returnValue = x.measureDistance(this.ciudades.get(cityIndex + 1));
			return returnValue;
		}).sum() + this.ciudades.get(0).measureDistance(this.ciudades.get(citiesSize - 1)));
	}
	
	public String toString() { 
		return Arrays.toString(ciudades.toArray()); 
	}
}
