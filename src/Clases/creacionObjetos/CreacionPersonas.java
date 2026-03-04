package Clases.creacionObjetos;

import Clases.enumeradores.Posiciones;
import Clases.equipos.Equipo;
import Clases.personas.Arbitro;
import Clases.personas.Entrenador;
import Clases.personas.Jugador;
import Clases.Tienda;
import Clases.personas.Persona;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CreacionPersonas {

    protected static List<Persona> entrenadores = new ArrayList<Persona>();
    public static List<Jugador>  jugadores = new ArrayList<Jugador>();
    public static List<Persona> arbitros = new ArrayList<Persona>();

    public static List<Persona> getEntrenadores() {
        return entrenadores;
    }

    public static List<Jugador> getJugadores() {
        return jugadores;
    }

    public static void CrearEntrenador(){

        try{

            InputStream lectura = CreacionPersonas.class.getResourceAsStream("/datos/entrenadores.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(lectura));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] informacion = linea.split(",");

                String nombre = informacion[0];
                int edad =  Integer.parseInt(informacion[1]);
                String personalidad = informacion[2];

                Persona entrenador = new Entrenador(nombre,edad,personalidad);

                entrenadores.add(entrenador);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void CrearJugadores(Tienda tienda){

        try{

            InputStream lectura = CreacionPersonas.class.getResourceAsStream("/datos/jugadores.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(lectura));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] informacion = linea.split(",");

                String nombre = informacion[0];
                int edad =  Integer.parseInt(informacion[1]);
                String personalidad = informacion[2];
                Posiciones posicion = Posiciones.valueOf(informacion[3].trim().toUpperCase());
                int precio = Integer.parseInt(informacion[4]);
                String equipo = informacion[5];

                List<Equipo> equipos = CreacionEquipos.equipos;

                Equipo equipoJugador = null;

                for (Equipo e : CreacionEquipos.equipos) {
                    if (e.getNombre().trim().equalsIgnoreCase(equipo)) {
                        equipoJugador = e;
                        break;
                    }
                }

                Jugador nuevo = new Jugador(nombre, edad, personalidad, posicion, precio, equipoJugador);
                CreacionPersonas.jugadores.add(nuevo);
                tienda.add(nuevo);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void CrearArbitros(){

        try{

            InputStream lectura = CreacionPersonas.class.getResourceAsStream("/datos/arbitros.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(lectura));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] informacion = linea.split(",");


                String nombre = informacion[0];
                int edad =  Integer.parseInt(informacion[1]);
                String personalidad = informacion[2];

                Persona arbitro = new Arbitro(nombre, edad, personalidad);

                arbitros.add( arbitro);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

