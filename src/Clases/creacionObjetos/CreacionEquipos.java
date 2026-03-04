package Clases.creacionObjetos;

import Clases.equipos.Equipo;
import Clases.equipos.Estadio;
import Clases.personas.Entrenador;
import Clases.personas.Jugador;
import Clases.personas.Persona;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreacionEquipos {

    protected static List<Estadio> estadios = new ArrayList<Estadio>();
    public static List<Equipo> equipos = new ArrayList<Equipo>();

    public static List<Estadio> getEstadios() {
        return estadios;
    }

    public static List<Equipo> getEquipos() {
        return equipos;
    }

    public static void CreacionEstadios() {

        try{

            InputStream lectura = CreacionEquipos.class.getResourceAsStream("/datos/estadios.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(lectura)));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] informacion = linea.split(",");

                String nombre = informacion[0];
                String ubicacion = informacion[1];
                int capacidad = Integer.parseInt(informacion[2]);

                estadios.add(new Estadio(nombre, ubicacion, capacidad));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void CreacionEquipos() {

        try{

            InputStream lectura = CreacionEquipos.class.getResourceAsStream("/datos/equipos.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(lectura));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] informacion = linea.split(",");

                String nombre = informacion[0];
                String estadio = informacion[1];
                String entrenador = informacion[2];

                Estadio estadioEquipo = null;
                for (Estadio estadio1 : estadios) {
                    if (estadio1.getNombre().equals(estadio)) {
                        estadioEquipo = estadio1;
                    }
                }

                List<Persona> entrenadores = CreacionPersonas.entrenadores;

                Entrenador entrenadorEquipo = null;
                for (int i = 0; i < entrenadores.size(); i++) {
                    if (entrenadores.get(i).getNombre().equals(entrenador)) {
                        entrenadorEquipo = (Entrenador) entrenadores.get(i);
                    }
                }

                equipos.add(new Equipo(nombre, estadioEquipo, entrenadorEquipo));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RellenarEquipos() {

        List<Jugador> jugadores = CreacionPersonas.jugadores;
        for (Equipo equipo : equipos) {
            for (Jugador jugador : jugadores) {
                if (jugador.getEquipo() != null &&
                        jugador.getEquipo().getNombre().trim().equalsIgnoreCase(equipo.getNombre().trim())) {
                    equipo.agregarJugador(jugador);
                }
            }
        }

    }

}
