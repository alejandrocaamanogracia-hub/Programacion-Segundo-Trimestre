package Clases;

import Clases.creacionObjetos.CreacionPersonas;
import Clases.equipos.Equipo;
import Clases.personas.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tienda {
    private List<Jugador> jugadoresMercado = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public Tienda(){}

    public void addJugadores(List<Jugador> jugadores){
        this.jugadoresMercado.addAll(jugadores);
    }

    public List<Jugador> getJugadores() {
        return jugadoresMercado;
    }

    public void add(Jugador jugador){
        this.jugadoresMercado.add(jugador);
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadoresMercado = jugadores;
    }

    /** CAMBIAR PLANTILLA **/
    public void cambiarPlantilla(Player player, List<Equipo> equipos) {

        jugadoresMercado.clear();

        Random rand = new Random();
        int random = 0;

        for (Equipo equipo : equipos){

            if (!equipo.getNombre().equalsIgnoreCase("Xtart")){
                int intentos = 0;
                for (int i = 0; i < 2; i++){
                    if (equipo.getJugadores().isEmpty()) break;
                    random = rand.nextInt(equipo.getJugadores().size());
                    while (jugadoresMercado.contains(equipo.getJugadores().get(random)) && intentos < 10){
                        random = rand.nextInt(equipo.getJugadores().size());
                        intentos++;
                    }
                    if (!jugadoresMercado.contains(equipo.getJugadores().get(random))) {
                        jugadoresMercado.add(equipo.getJugadores().get(random));
                    }
                }
            }

        }

        while (true) {
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║                                  ║");
            System.out.println("║       MERCADO DE FICHAJES        ║");
            System.out.println("║                                  ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.println("Dinero disponible: " + player.getDinero() + "€");
            System.out.println("0. Salir del mercado");
            System.out.println("1. Ver plantilla actual y liberar jugadores");
            System.out.println("2. Fichar jugadores del mercado (" + this.jugadoresMercado.size() + " disponibles)");

            int opcion;
            while (true) {
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida, introduce un número");
                }
            }

            if (opcion == 0) {
                if (player.cantidadJugadores() > 11 || player.cantidadJugadores() < 11 ) {
                    System.out.println("Debes tener en tu equipo 11");
                }else {

                    int porteros = 0;
                    int defensas = 0;
                    int extremos = 0;
                    int delanteros = 0;
                    int centrocampista = 0;

                    for (int i = 0; i < player.cantidadJugadores(); i++) {
                        if (player.getJugadores().get(i).getPosicion().toString().toUpperCase().equalsIgnoreCase("PORTERO")){
                            porteros++;
                        }else if (player.getJugadores().get(i).getPosicion().toString().toUpperCase().equalsIgnoreCase("DEFENSA")){
                            defensas++;
                        }else if (player.getJugadores().get(i).getPosicion().toString().toUpperCase().equalsIgnoreCase("EXTREMO")){
                            extremos++;
                        }else if (player.getJugadores().get(i).getPosicion().toString().toUpperCase().equalsIgnoreCase("DELANTERO")){
                            delanteros++;
                        }else if (player.getJugadores().get(i).getPosicion().toString().toUpperCase().equalsIgnoreCase("CENTROCAMPISTA")){
                            centrocampista++;
                        }
                    }

                    if (porteros != 1) {
                        System.out.println("Debes tener en tu equipo 1 portero");
                        System.out.println("Ahora tienes " + porteros + " porteros");
                    }else if (defensas != 4){
                        System.out.println("Debes tener en tu equipo 4 defensas");
                        System.out.println("Ahora tienes " + defensas + " defensas");
                    }else if (extremos != 2){
                        System.out.println("Ahora tienes " + extremos + " extremos");
                    }else if (delanteros != 1){
                        System.out.println("Debes tener en tu equipo 1 delantero");
                        System.out.println("Ahora tienes " + delanteros + " delanteros");
                    }else if (centrocampista != 3){
                        System.out.println("Debes tener en tu equipo 3 centrocampistas");
                        System.out.println("Ahora tienes " + centrocampista + " centrocampistas");
                    }else {
                        System.out.println("Saliendo del mercado...");
                        comprobarEquipos(equipos);
                        break;
                    }

                }

            } else if (opcion == 1) {
                List<Jugador> plantillaPlayer = player.getJugadores();
                if (plantillaPlayer.isEmpty()) {
                    System.out.println("Tu equipo no tiene jugadores.");
                } else {
                    System.out.println("--- TU PLANTILLA ---");
                    for (int i = 0; i < plantillaPlayer.size(); i++) {
                        System.out.println((i + 1) + "." + Colores.NEGRITA + plantillaPlayer.get(i).getNombre() + ". " + plantillaPlayer.get(i) + Colores.RESET);
                    }
                    System.out.println("0. Volver");
                    System.out.println("Elige un jugador para liberarlo (50% de su valor):");

                    int opLiberar;
                    while (true) {
                        try {
                            opLiberar = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Opción inválida");
                        }
                    }

                    if (opLiberar == 0) continue;
                    if (opLiberar < 1 || opLiberar > plantillaPlayer.size()) {
                        System.out.println("Opción fuera de rango");
                        continue;
                    }

                    Jugador liberado = plantillaPlayer.get(opLiberar - 1);
                    int reembolso = liberado.getPrecio() / 2;
                    player.setDinero(player.getDinero() + reembolso);
                    this.jugadoresMercado.add(liberado);
                    plantillaPlayer.get(opLiberar - 1).setEquipo(null);
                    plantillaPlayer.remove(opLiberar - 1);
                    System.out.println("Has liberado a " + liberado.getNombre() + ". Recibes " + reembolso + "€.");
                }
            } else if (opcion == 2) {
                if (this.jugadoresMercado.isEmpty()) {
                    System.out.println("No hay jugadores disponibles en el mercado.");
                    continue;
                }

                System.out.println("--- MERCADO DE JUGADORES ---");
                System.out.println("0. Volver");
                for (int i = 0; i < this.jugadoresMercado.size(); i++) {
                    System.out.println((i + 1) + ". " + Colores.NEGRITA + this.jugadoresMercado.get(i).getNombre() + Colores.RESET + "." + this.jugadoresMercado.get(i));
                }

                int opFichar;
                while (true) {
                    try {
                        opFichar = Integer.parseInt(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Opción inválida");
                    }
                }

                if (opFichar == 0) continue;
                if (opFichar < 1 || opFichar > this.jugadoresMercado.size()) {
                    System.out.println("Opción fuera de rango");
                    continue;
                }

                Jugador elegido = this.jugadoresMercado.get(opFichar - 1);
                if (elegido.getPrecio() > player.getDinero()) {
                    System.out.println("No tienes suficiente dinero. Necesitas " + elegido.getPrecio() + "€.");
                } else {
                    player.setDinero(player.getDinero() - elegido.getPrecio());
                    player.añadirJugador(elegido);
                    this.jugadoresMercado.remove(opFichar - 1);
                    System.out.println("¡Has fichado a " + elegido.getNombre() + " por " + elegido.getPrecio() + "€!");
                    System.out.println("Dinero restante: " + player.getDinero() + "€");
                }
            } else {
                System.out.println("Opción inválida");
            }
        }
    }

    public void comprarJugador(Player player){
        while(true) {
            System.out.println("\n======TIENDA=====");
            System.out.println("Tu dinero: " + player.getDinero() + "€");
            System.out.println("0. Salir");
            for (int i = 0; i < this.jugadoresMercado.size(); i++) {
                System.out.println((i+1) + ". " + jugadoresMercado.get(i));
            }

            int opcion;
            while(true) {
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida, introduce un número");
                }
            }
            opcion -= 1;

            if(opcion == -1) break;
            if(opcion < 0 || opcion >= jugadoresMercado.size()) continue;

            Jugador seleccionado = jugadoresMercado.get(opcion);
            if(seleccionado.getPrecio() > player.getDinero()){
                System.out.println("No tienes dinero suficiente");
            } else {
                player.setDinero(player.getDinero() - seleccionado.getPrecio());
                System.out.println("Has comprado a " + seleccionado.getNombre());
                player.añadirJugador(seleccionado);
                jugadoresMercado.remove(opcion);
                System.out.println(player);
                break;
            }
        }
        this.jugadoresMercado.clear();
    }

    public void comprobarEquipos(List<Equipo> equipos){

        for (Equipo equipo : equipos) {

            int porteros = 0;
            int defensas = 0;
            int extremos = 0;
            int delanteros = 0;
            int centrocampista = 0;

            for (int i = 0; i < equipo.getJugadores().size(); i++) {
                if (equipo.getJugadores().get(i).getPosicion().toString().toUpperCase() == "PORTERO"){
                    porteros++;
                }else if (equipo.getJugadores().get(i).getPosicion().toString().toUpperCase() == "DEFENSA"){
                    defensas++;
                }else if (equipo.getJugadores().get(i).getPosicion().toString().toUpperCase() == "EXTREMO"){
                    extremos++;
                }else if (equipo.getJugadores().get(i).getPosicion().toString().toUpperCase() == "DELANTERO"){
                    delanteros++;
                }else if (equipo.getJugadores().get(i).getPosicion().toString().toUpperCase() == "CENTROCAMPISTA"){
                    centrocampista++;
                }
            }

            if (porteros == 0){
                for (int i = 0; i < CreacionPersonas.jugadores.size(); i++) {
                    if (CreacionPersonas.jugadores.get(i).getPosicion().toString().toUpperCase() == "PORTERO" && CreacionPersonas.jugadores.get(i).getEquipo() == null){
                        CreacionPersonas.jugadores.get(i).setEquipo(equipo);
                    }
                }
            }else if (defensas < 4){

                while (defensas < 4){

                    for (int i = 0; i < CreacionPersonas.jugadores.size(); i++) {
                        if (CreacionPersonas.jugadores.get(i).getPosicion().toString().toUpperCase() == "DEFENSA" && CreacionPersonas.jugadores.get(i).getEquipo() == null){
                            CreacionPersonas.jugadores.get(i).setEquipo(equipo);
                        }
                    }

                    defensas++;

                }

            }else if (extremos < 2){

                while (extremos < 2){

                    for (int i = 0; i < CreacionPersonas.jugadores.size(); i++) {
                        if (CreacionPersonas.jugadores.get(i).getPosicion().toString().toUpperCase() == "EXTREMO" && CreacionPersonas.jugadores.get(i).getEquipo() == null){
                            CreacionPersonas.jugadores.get(i).setEquipo(equipo);
                        }
                    }

                    extremos++;

                }

            }else if (delanteros < 1){

                for (int i = 0; i < CreacionPersonas.jugadores.size(); i++) {
                    if (CreacionPersonas.jugadores.get(i).getPosicion().toString().toUpperCase() == "DELANTERO" && CreacionPersonas.jugadores.get(i).getEquipo() == null){
                        CreacionPersonas.jugadores.get(i).setEquipo(equipo);
                    }
                }

            }else if (centrocampista < 3){

                while (centrocampista < 3){

                    for (int i = 0; i < CreacionPersonas.jugadores.size(); i++) {
                        if (CreacionPersonas.jugadores.get(i).getPosicion().toString().toUpperCase() == "CENTROCAMPISTA" && CreacionPersonas.jugadores.get(i).getEquipo() == null){
                            CreacionPersonas.jugadores.get(i).setEquipo(equipo);
                        }
                    }

                    centrocampista++;

                }
            }
        }
    }
}
