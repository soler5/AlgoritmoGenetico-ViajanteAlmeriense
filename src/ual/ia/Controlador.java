package ual.ia;
import java.util.ArrayList;
import java.util.Arrays;
public class Controlador {
	public ArrayList<Ciudad> rutaInicial = new ArrayList<Ciudad>(Arrays.asList(
			new Ciudad("Vera", 37.2457489, -1.8837392), 
			new Ciudad("Garrucha", 37.1879379, -1.821454), 
			new Ciudad("Almería", 36.8415917, -2.4921362), 
			new Ciudad("Níjar", 36.9631185, -2.2145378), 
			new Ciudad("Adra", 36.7496405, -3.0275531), 
			new Ciudad("Carboneras", 37.0003494, -1.9032242), 
			new Ciudad("Berja", 36.8435015, -2.9598815), 
			new Ciudad("Antas", 37.2451581,-1.9227126)
	));

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Poblacion poblacion = new Poblacion(AlgoritmoGenetico.TAMANO_POBLACION, controlador.rutaInicial);
		poblacion.ordenarRutasPorFitness();
		AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(controlador.rutaInicial);
		int numeroGeneracion = 0;
		controlador.pintarCabecera(numeroGeneracion++);
		controlador.pintarPoblacion(poblacion); 
		while (numeroGeneracion < AlgoritmoGenetico.NUMERO_GENERACIONES) {
			controlador.pintarCabecera(numeroGeneracion++);
			poblacion = algoritmoGenetico.evolucion(poblacion);
			poblacion.ordenarRutasPorFitness();
			controlador.pintarPoblacion(poblacion);
		}
		System.out.println("La mejor ruta es: " + poblacion.getRutas().get(0));
		System.out.println("con una distancia de: "+String.format("%.2f",poblacion.getRutas().get(0).calcularDistanciaTotal())+ " km");
	}
	public void pintarPoblacion(Poblacion poblacion) {
		poblacion.getRutas().forEach(x -> { 
    		System.out.println(Arrays.toString(x.getCiudades().toArray()) + " |  "+ 
    				String.format("%.4f", x.getFitness()) +"   |  "+ String.format("%.2f", x.calcularDistanciaTotal()));
    	});
		System.out.println("");
	}
	public void pintarCabecera(int numeroGeneracion) {
    	System.out.println("> Generacion # "+numeroGeneracion);
    	String cabeceraColumna1 = "Ruta";
    	String ultimasCabeceras = "Fitness   | Distancia (en km)";
    	int ciudadesLength = 0;
    	
    	for (int x = 0; x < rutaInicial.size(); x++) 
    		ciudadesLength += rutaInicial.get(x).getNombre().length();
    	
    	int arrayLength = ciudadesLength + rutaInicial.size()*2;
    	int partialLength = (arrayLength - cabeceraColumna1.length())/2;
    	
    	for (int x=0; x < partialLength; x++)
    		System.out.print(" ");
    	
    	System.out.print(cabeceraColumna1);
    	
    	for (int x=0; x < partialLength; x++)
    		System.out.print(" ");
    	
    	if ((arrayLength % 2) == 0)
    		System.out.print(" ");
    	
    	System.out.println(" | "+ ultimasCabeceras);
    	ciudadesLength += ultimasCabeceras.length() + 3;
    	
    	for (int x=0; x < ciudadesLength+rutaInicial.size()*2; x++)
    		System.out.print("-");
    	System.out.println("");
    }
}
