package Clases.personas;

public final class Entrenador extends Persona{

    public Entrenador(){}

    public Entrenador(String nombre, int edad, String personalidad){
        super(nombre, edad, personalidad);
    }

    @Override
    public void saludar() {
        System.out.print("Buenas, soy el entrenador " + getNombre() + " y he entrenado al ");
    }

    @Override
    public String toString() {
        return "";
    }
}
