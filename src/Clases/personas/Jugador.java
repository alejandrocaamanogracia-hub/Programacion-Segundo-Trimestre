package Clases.personas;

import Clases.Colores;
import Clases.enumeradores.Posiciones;
import Clases.equipos.Equipo;

import java.awt.*;

public class Jugador extends Persona{
    private Posiciones posicion;
    private int golesAnotados;
    private int precio;
    private Equipo equipo;
    private int faltas;
    private int amarilla;
    private int roja;
    private boolean ultimaFalta;

    public Jugador(String nombre, int edad, String personalidad ,Posiciones posicion, int precio,  Equipo equipo) {
        super(nombre, edad, personalidad);
        this.posicion = posicion;
        this.precio = precio;
        this.equipo = equipo;
    }


    public int getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public Posiciones getPosicion() {
        return posicion;
    }

    public void setPosicion(Posiciones posicion) {
        this.posicion = posicion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getAmarilla() { return amarilla; }

    public void setAmarilla(int amarilla) { this.amarilla = amarilla; }

    public int getRoja() { return roja; }

    public void setRoja(int roja) { this.roja = roja; }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public boolean isUltimaFalta() {
        return ultimaFalta;
    }

    public void setUltimaFalta(boolean ultimaFalta) {
        this.ultimaFalta = ultimaFalta;
    }


    public void anotarGol(){
        this.golesAnotados++;
        System.out.println(Colores.NEGRITA+Colores.CIAN_BRILLANTE +this.getNombre()+" ha marcado un gol" + Colores.RESET);
    }

    public void tarjetaAmarilla(){
        this.amarilla++;
        System.out.println(Colores.NEGRITA+Colores.AMARILLO_BRILLANTE +  this.getNombre()+" ha hecho una falta, recibe una tarjeta amarilla"+Colores.RESET);
    }

    public void tarjetaRoja(){
        this.roja++;
        this.ultimaFalta=true;
        System.out.println(Colores.NEGRITA+Colores.ROJO_BRILLANTE + this.getNombre() + " ha hecho otra falta, se gana la tarjeta roja."+Colores.RESET);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "equipo=" + equipo +
                ", posicion=" + posicion +
                ", golesAnotados=" + golesAnotados +
                ", precio=" + precio +
                ", faltas=" + faltas +
                ", amarilla=" + amarilla +
                ", roja=" + roja +
                "} " + super.toString();
    }
}
