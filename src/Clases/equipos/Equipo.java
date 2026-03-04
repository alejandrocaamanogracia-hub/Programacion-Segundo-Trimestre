package Clases.equipos;

import Clases.Colores;
import Clases.enumeradores.Posiciones;
import Clases.personas.Entrenador;
import Clases.personas.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores = new ArrayList<Jugador>();
    private int puntos;
    private int golesFavor;
    private int golesContra;
    private Estadio estadio;
    private Entrenador entrenador;

    private int puntosAtaque;
    private int puntosDefensa;

    private int falta;

    public Equipo(){}

    public Equipo(String nombre){
        this.nombre = nombre;
    }

    public Equipo(String nombre, Estadio estadio, Entrenador entrenador) {
        this.nombre = nombre;
        this.estadio = estadio;
        this.entrenador = entrenador;
        actualizarPuntosEstadisticas();
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public void agregarJugador(Jugador jugador){
        jugadores.add(jugador);
    }

    public int  getFalta() { return falta; }

    public void setFalta(int falta) { this.falta = falta; }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void marcarGoles(int goles){
        Random rand = new Random();
        List<Jugador> jugadoresDeCampo = new ArrayList<>();

        for (Jugador jugador : jugadores) {
            if (!jugador.getPosicion().toString().equals("PORTERO") || !jugador.isUltimaFalta()) {
                jugadoresDeCampo.add(jugador);
            }
        }

        for (int i = 0; i < goles; i++) {
            golesFavor++;
            if (!jugadoresDeCampo.isEmpty()) {
                int index =  rand.nextInt(jugadoresDeCampo.size());
                jugadoresDeCampo.get(index).anotarGol();
            } else if (!jugadores.isEmpty()) {
                int index = rand.nextInt(jugadores.size());
                jugadores.get(index).anotarGol();
            } else {
                System.out.println("¡¡ NO FUNCIONA: EL EQUIPO NO TIENE JUGADORES !!");
            }
        }
    }

    public void recibirGoles(){
        golesContra++;
    }

    public String getPortero() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getPosicion().toString().toUpperCase() == "PORTERO"){
                return jugadores.get(i).getNombre();
            }
        }
        return null;
    }

    public void actualizarPuntosEstadisticas(){
        puntosAtaque = 0;
        puntosDefensa = 0;
        for(Jugador jugador : jugadores) {
            switch (jugador.getPersonalidad().toLowerCase()) {
                case "irascible" -> {
                    puntosAtaque += 2;
                    puntosDefensa -= 2;
                }
                case "normal" -> {
                    puntosAtaque += 1;
                    puntosDefensa += 1;
                }
                case "calmado" -> {
                    puntosDefensa += 2;
                    puntosAtaque -= 2;
                }
            }
            switch (entrenador.getPersonalidad().toLowerCase()) {
                case "irascible" ->  {
                    puntosAtaque -= 2;
                    puntosDefensa -= 2;
                }
                case "normal" -> {
                    puntosAtaque += 1;
                    puntosDefensa += 1;
                }
                case "calmado" -> {
                    puntosAtaque += 2;
                    puntosDefensa += 2;
                }
            }
        }
    }

    public void hacerFalta(){
        if (!jugadores.isEmpty()) {
            Random rand = new Random();
            int index = rand.nextInt(jugadores.size());
            Jugador jugadorFaltoso = jugadores.get(index);
            jugadorFaltoso.setFaltas(jugadorFaltoso.getFaltas() + 1);

            if (jugadorFaltoso.getFaltas() >= 4) {
                if (jugadorFaltoso.getPosicion() != Posiciones.PORTERO) {
                    if (jugadorFaltoso.getAmarilla() == 2) {
                        jugadorFaltoso.tarjetaRoja();
                    }
                    else{
                        jugadorFaltoso.tarjetaAmarilla();
                    }
                }
            }
            else{
                System.out.println(Colores.NEGRITA+Colores.ROJO_BRILLANTE +jugadorFaltoso.getNombre() + " ha cometido una falta." + Colores.RESET);
            }
        } else {
            System.out.println("Falta pitada, pero no hay jugadores en " + this.nombre);
        }
    }


    public void getInfo(){
        System.out.println();
        System.out.println(this.getNombre().toUpperCase());
        System.out.println("--------------------------------------------------");
        System.out.println(this.getGolesFavor()+" goles a favor.");
        System.out.println(this.getGolesContra()+" goles en contra");
        System.out.println(this.getPuntos()+" puntos");
        for(Jugador jugador : jugadores){
            System.out.println(jugador.getNombre() + " " + jugador.getPosicion());;
        }
    }
}
