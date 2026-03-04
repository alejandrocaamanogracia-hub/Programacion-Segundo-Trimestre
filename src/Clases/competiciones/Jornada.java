package Clases.competiciones;

import Clases.Partido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Jornada {

    List<Partido> partidos  = new ArrayList<>();
    private int numeroJornada;
    LocalDate fechaJornada;

    public Jornada(LocalDate fechaJornada, int numeroJornada, List<Partido> partidos) {
        this.fechaJornada = fechaJornada;
        this.numeroJornada = numeroJornada;
        this.partidos = partidos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public int getNumeroJornada() {
        return numeroJornada;
    }

    public void setNumeroJornada(int numeroJornada) {
        this.numeroJornada = numeroJornada;
    }

    public LocalDate getFechaJornada() {
        return fechaJornada;
    }

    public void setFechaJornada(LocalDate fechaJornada) {
        this.fechaJornada = fechaJornada;
    }

    @Override
    public String toString() {
        return "Jornada{" +
                "partidos=" + partidos +
                ", numeroJornada=" + numeroJornada +
                ", fechaJornada=" + fechaJornada +
                '}';
    }
}

