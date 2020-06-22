package ual.ia;
public class Ciudad {
	 private static final double RADIO_TERRESTRE_ECUATORIAL = 6378.1370D;
     private static final double CONVERTIR_GRADOS_RADIANES = Math.PI/180D;
    
	 private double longitud;
	 private double latitud;
	 private String nombre;
	
	 public Ciudad(String nombre, double latitud, double longitud) { 
		 this.nombre = nombre;
	     this.longitud = longitud * CONVERTIR_GRADOS_RADIANES;
	     this.latitud = latitud * CONVERTIR_GRADOS_RADIANES;
	 }
	 
	 public String getNombre() { return nombre; }
	 
	 public double getLongitud() { return this.longitud;}
	 
	 public double getLatitud() { return this.latitud; }
	 
	 public String toString(){ return getNombre(); }
	 
	 public double measureDistance(Ciudad city) { 
		 double deltaLongitude = city.getLongitud() - this.getLongitud();
	     double deltaLatitude = city.getLatitud() - this.getLatitud();
	     double a = Math.pow(Math.sin(deltaLatitude / 2D), 2D) + 
	  		   Math.cos(this.getLatitud()) * Math.cos(city.getLatitud()) * Math.pow(Math.sin(deltaLongitude / 2D), 2D);
	     return RADIO_TERRESTRE_ECUATORIAL * 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
	 }
}
