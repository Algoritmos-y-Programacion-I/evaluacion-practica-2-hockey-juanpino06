package model;

public class JugadorHockey extends Persona implements IDesplazarseEnPistaConPalo {
    protected String nombre;
    protected int numero;
    protected Posicion posicion;

    public JugadorHockey(String nombre, int edad, Posicion posicion) {
        super(nombre, edad);
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public String getNombrePosicion() {
        return posicion.toString();
    }


    public String desplazarseConPalo() {
        if (posicion != Posicion.PORTERO) {
            return nombre + " se desplaza en la pista con el palo.";
        } else {
            return nombre + " es el portero y no se desplaza más allá de media pista.";
        }
    }

    public String pasarDisco(JugadorHockey receptor) {
        if (this.posicion == Posicion.PORTERO) {
            return this.nombre + " no puede pasar el disco, es el portero."; 
        } else if (receptor == null) {
            return this.nombre + " intenta pasar el disco, ¡pero no hay nadie ahí!";
        } else {
            return this.nombre + " pasa el disco a " + receptor.getNombre() + ".";
        }
    }
}

