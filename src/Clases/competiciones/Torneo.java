package Clases.competiciones;

import Clases.Colores;
import Clases.creacionObjetos.CreacionEquipos;
import Clases.equipos.Equipo;
import Clases.Partido;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Torneo {
    private Scanner sc = new Scanner(System.in);
    private List<Equipo> equipos = new ArrayList<Equipo>();
    private List<Partido> partidos = new ArrayList<Partido>();

    public Torneo() {
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void generarPartidos() {
        partidos.clear();
        List<Equipo> lista = new ArrayList<>(equipos);
        java.util.Collections.shuffle(lista);

        boolean esImpar = false;

        if (lista.size() % 2 != 0) {
            lista.add(new Equipo("Temporal"));
        }

        int numEquipos = lista.size();
        int numJornadas = numEquipos - 1;


        for (int jornada = 0; jornada < numJornadas; jornada++) {
            for (int i = 0; i < numEquipos / 2; i++) {
                Equipo local = lista.get(i);
                Equipo visitante = lista.get(numEquipos - 1 - i);

                if (jornada % 2 != 0 && i == 0) {
                    Equipo temp = local;
                    local = visitante;
                    visitante = temp;
                }

                if (!local.getNombre().equals("Temporal") && !visitante.getNombre().equals("Temporal")) {
                    partidos.add(new Partido(local, visitante));
                }
            }
            Equipo ultimo = lista.remove(numEquipos - 1);
            lista.add(1, ultimo);
        }

        int cantidadPartidosIda = partidos.size();
        for (int i = 0; i < cantidadPartidosIda; i++) {
            Partido partidoIda = partidos.get(i);
            partidos.add(new Partido(partidoIda.getEquipoVisitante(), partidoIda.getEquipoLocal()));
        }
    }

    public void jugarPartidos(Clases.Player player, Clases.Tienda tienda, Torneo torneo) {
        int numEquipos = equipos.size();
        int numJornadas = numEquipos - 1;
        int totalJornadas = numJornadas * 2;
        int partidosPorJornada = numEquipos / 2;

        int indice = 0;
        //Bucle de jornadas
        for (int jornada = 1; jornada <= totalJornadas; jornada++) {
            System.out.println(Colores.NEGRITA+Colores.MORADO_BRILLANTE+"=======================");
            System.out.println("     | JORNADA " + jornada + " |    ");
            System.out.println("======================="+Colores.RESET);
            // Bucle de partidos de cada jornada
            for (int i = 0; i < partidosPorJornada; i++) {
                Partido partido = partidos.get(indice);
                partido.jugarPartido();
                System.out.println(partido);
                indice++;
            }

            if (jornada == numJornadas) {
                System.out.println(Colores.AZUL + Colores.NEGRITA +"\n  ***********************************");
                System.out.println("❄️ ¡SE ABRE EL MERCADO DE INVIERNO! ❄️");
                System.out.println("  ***********************************\n"+Colores.RESET);

                tienda.cambiarPlantilla(player, equipos);
            }

            if (jornada == totalJornadas) {
                System.out.println(Colores.AMARILLO + Colores.NEGRITA +"\n  ***********************************");
                System.out.println("☀️  ¡SE ABRE EL MERCADO DE VERANO!  ☀️");
                System.out.println("  ***********************************\n"+Colores.RESET);

                tienda.cambiarPlantilla(player, equipos);
            }

            System.out.println();
            System.out.println("JUGAR SIGUIENTE JORNADA");
            System.out.println("Pulsa 1 ->");
            System.out.println("Simular el resto de las jornadas");
            System.out.println("Pulsa 2 ->");
            System.out.println("Ver clasificación");
            System.out.println("Pulsa 3 ->");

            while (true) {
                String opcion = sc.nextLine();
                if (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")) {
                    System.out.println("JUGAR SIGUIENTE JORNADA");
                    System.out.println("Pulsa 1 ->");
                    System.out.println("Simular el resto de las jornadas");
                    System.out.println("Pulsa 2 ->");
                    System.out.println("Ver clasificación");
                    System.out.println("Pulsa 3 ->");
                } else {
                    if (opcion.equals("2")) {
                        simularJornadas(player, tienda);
                        jornada = totalJornadas + 1;
                        break;
                    }else if (opcion.equals("3")) {
                        List<Equipo> clasificacion = new ArrayList<>(torneo.getEquipos());
                        clasificacion.sort((e1, e2) -> Integer.compare(e2.getPuntos(), e1.getPuntos()));
                        System.out.println(Colores.VERDE+"--- CLASIFICACIÓN ----");
                        int pos = 1;
                        for (Equipo e : clasificacion) {
                            System.out.println(pos + ". " + e.getNombre() + ": " + e.getPuntos() + " ptos");
                            pos++;
                        }
                        System.out.println();
                        System.out.println("JUGAR SIGUIENTE JORNADA");
                        System.out.println("Pulsa 1 ->");
                        System.out.println("Simular el resto de las jornadas");
                        System.out.println("Pulsa 2 ->");
                    }
                    else if (opcion.equals("1")) {
                        break;
                    }
                }
            }
        }
    }

    public void simularJornadas(Clases.Player player, Clases.Tienda tienda) {

        int numEquipos = equipos.size();
        int numJornadas = numEquipos - 1;
        int totalJornadas = numJornadas * 2;
        int partidosPorJornada = numEquipos / 2;

        int indice = 0;

        for (int jornada = 1; jornada <= totalJornadas; jornada++) {
            System.out.println(Colores.NEGRITA+Colores.MORADO_BRILLANTE+"=======================");
            System.out.println("     | JORNADA " + jornada + " |    ");
            System.out.println("======================="+Colores.RESET);
            // Bucle de partidos de cada jornada
            for (int i = 0; i < partidosPorJornada; i++) {
                Partido partido = partidos.get(indice);
                partido.jugarPartido();
                System.out.println(partido);
                indice++;
            }

            if (jornada == numJornadas) {
                System.out.println(Colores.AZUL + Colores.NEGRITA +"\n  ***********************************");
                System.out.println("❄️ ¡SE ABRE EL MERCADO DE INVIERNO! ❄️");
                System.out.println("  ***********************************\n"+Colores.RESET);

                tienda.cambiarPlantilla(player, equipos);
            }

            if (jornada == totalJornadas) {
                System.out.println(Colores.AMARILLO + Colores.NEGRITA +"\n  ***********************************");
                System.out.println("☀️  ¡SE ABRE EL MERCADO DE VERANO!  ☀️");
                System.out.println("  ***********************************\n"+Colores.RESET);

                tienda.cambiarPlantilla(player, equipos);
            }

        }

    }
}
