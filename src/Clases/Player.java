package Clases;

import Clases.creacionObjetos.CreacionEquipos;
import Clases.equipos.Equipo;
import Clases.personas.Jugador;

import java.util.List;

public class Player {
    private int dinero=10000;

    private Equipo equipoPlayer;

    public Player() {
        for (Equipo e : CreacionEquipos.getEquipos()) {
            if (e.getNombre().equalsIgnoreCase("Xtart")) {
                equipoPlayer = e;
                break;
            }
        }
    }

    public void setEquipoPlayer(Equipo equipoPlayer) {
        this.equipoPlayer = equipoPlayer;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void añadirJugador(Jugador jugador){
        equipoPlayer.agregarJugador(jugador);
        jugador.setEquipo(equipoPlayer);
    }

    public List<Jugador> getJugadores() {
        return equipoPlayer.getJugadores();
    }

    public Equipo getEquipoPlayer() {
        return equipoPlayer;
    }

    public int cantidadJugadores(){
        return equipoPlayer.getJugadores().size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Colores.AZUL + "╔══════════════════════════════════╗\n");
        sb.append("║            XTART FC              ║\n");
        sb.append("╚══════════════════════════════════╝\n");
        sb.append("Dinero: ").append(dinero).append("€\n");
        sb.append("Jugadores (").append(equipoPlayer.getJugadores().size()).append("):\n");
        sb.append("----------------------------------\n" + Colores.RESET);

        if (equipoPlayer.getJugadores().isEmpty()) {
            sb.append("  Sin jugadores en el equipo.\n");
        } else {
            int i = 1;
            for (Clases.personas.Jugador j : equipoPlayer.getJugadores()) {
                sb.append("  ").append(i).append(". ").append(j.getNombre().toUpperCase())
                    .append(" | ").append(j.getPosicion())
                    .append(" | ").append(j.getEdad()).append(" años")
                    .append(" | ").append(j.getPrecio()).append("€")
                    .append(" | Goles: ").append(j.getGolesAnotados()).append("\n");
                i++;
            }
        }

        sb.append("----------------------------------");
        return sb.toString();
    }
}
