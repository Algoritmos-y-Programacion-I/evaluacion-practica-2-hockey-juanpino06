package model;
import java.util.Random;

public class Controller {

    private Equipo[] equipos;
    private Arbitro[] arbitros;

    private final int CANTIDAD_EQUIPOS = 4;
    private final int CANTIDAD_ARBITROS = 4;
    private int contadorPases = 0; 
    private boolean[] jugadoresQueTocaronDisco;
    private int contadorEquipos = 0;

    /**
     * Constructor de la clase Controller para inicializar variables globales.
     *
     * @pre No se requieren precondiciones específicas.
     * @post Se crea una instancia de Controller con un arreglo de personas vacío.
     */
    public Controller() {
        equipos = new Equipo[CANTIDAD_EQUIPOS];
        arbitros = new Arbitro[CANTIDAD_ARBITROS];
        
    }

    public String fixture() {
        String fixture = "";
        Random random = new Random();
        int equipo1 = random.nextInt(4);
        int equipo2;
        do {
            equipo2 = random.nextInt(4);
        } while (equipo2 == equipo1);

        fixture += "Partido 1: Equipo " + equipo1 + " vs Equipo " + equipo2;
        fixture += "\n";

        do {
            equipo1 = random.nextInt(4);
            equipo2 = random.nextInt(4);
        } while (equipo2 == equipo1);

        fixture += "Partido 2: Equipo " + equipo1 + " vs Equipo " + equipo2;
        return fixture;
    }

    public void preloadInfo() {
        Equipo equipo1 = new Equipo();
        equipo1.nombreEquipo = "Equipo ejemplo 1";
        equipo1.agregarJugador(new JugadorHockey("Roberto Alvez", 25, Posicion.PORTERO), 0); 
        equipo1.agregarJugador(new JugadorHockey("Mario Sanchez", 28, Posicion.DEFENSA), 1);
        equipo1.agregarJugador(new JugadorHockey("Hernando Ortiz", 22, Posicion.DEFENSA), 2);
        equipo1.agregarJugador(new JugadorHockey("Carlos Guerra", 27, Posicion.ALA), 3);
        equipo1.agregarJugador(new JugadorHockey("Juan Miguel", 30, Posicion.CENTRO), 4);
        equipo1.agregarJugador(new JugadorHockey("Jose Guitierrez", 29, Posicion.ALA), 5);

        Equipo equipo2 = new Equipo();
        equipo2.nombreEquipo = "Equipo Ejemplo 2";
        equipo2.agregarJugador(new JugadorHockey("Lucas Diaz", 30, Posicion.CENTRO), 0);
        equipo2.agregarJugador(new JugadorHockey("Martin Dominguez", 32, Posicion.PORTERO), 0);
        equipo2.agregarJugador(new JugadorHockey("Hernan Cortez", 29, Posicion.DEFENSA), 0);
        equipo2.agregarJugador(new JugadorHockey("Juan Pablo Ramirez", 23, Posicion.DEFENSA), 0);
        equipo2.agregarJugador(new JugadorHockey("Manuel Alarcon", 29, Posicion.ALA), 0);
        equipo2.agregarJugador(new JugadorHockey("Juan Esteban Rojas", 31, Posicion.CENTRO), 0);
    
        equipos[0] = equipo1;
        equipos[1] = equipo2;
      
        arbitros[0] = new ArbitroPrincipal("Juan Perez", 40);
        arbitros[1] = new JuezDeLinea("Maria Gonzalez", 35);
    }

    
    public void simularJugada(JugadorHockey jugador1, JugadorHockey jugador2) {
        if (jugador1 != null && jugador2 != null) {
            String resultadoPase = jugador1.pasarDisco(jugador2);
            System.out.println(resultadoPase);
            }
        }

    private int contarJugadoresQueTocaronDisco() {
        int contador = 0;
        for (boolean tocoDisco : jugadoresQueTocaronDisco) {
            if (tocoDisco) {
                    contador++;
            }
        }
        return contador;
        }
  
    public String realizarPartido() {
        String resultadoPartido = "";

        int[] secuenciaPases = {0, 1, 2, 3, 4, 5}; 

        for (int i = 0; i < secuenciaPases.length - 1; i++) {
            JugadorHockey jugadorConDisco = equipos[0].getJugadores()[secuenciaPases[i]];
            JugadorHockey jugadorReceptor = equipos[0].getJugadores()[secuenciaPases[i + 1]];

            if (jugadorConDisco != null && jugadorReceptor != null) {
                String resultadoPase = jugadorConDisco.pasarDisco(jugadorReceptor);
                resultadoPartido += resultadoPase + "\n";

                contadorPases++;
                jugadoresQueTocaronDisco[jugadorConDisco.numero] = true;

                for (Arbitro arbitro : arbitros) {
                    if (arbitro != null) {
                        arbitro.desplazarse();
                    }
                }
            }
        }
        if (contadorPases >= 5 && contarJugadoresQueTocaronDisco() >= 3) {
            resultadoPartido += "¡Gol de " + equipos[0].getJugadores()[secuenciaPases[secuenciaPases.length - 1]].getNombre() + "!\n";
        }

        return resultadoPartido;
    }
        
    }

