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

        if (lista.size() % 2 != 0) {
            lista.add(new Equipo("Temporal"));
        }

        int numEquipos = lista.size();
        int numJornadas = numEquipos - 1;


        for (int jornada = 0; jornada < numJornadas; jornada++) {
            for (int i = 0; i < numEquipos / 2; i++) {
                //El primero juega contra el último
                Equipo local = lista.get(i);
                Equipo visitante = lista.get(numEquipos - 1 - i);

                //Evita que el primer equipo siempre este de local
                if (jornada % 2 != 0 && i == 0) {
                    Equipo temp = local;
                    local = visitante;
                    visitante = temp;
                }
                //Se añaden a la lista si no juga contra temporal
                if (!local.getNombre().equals("Temporal") && !visitante.getNombre().equals("Temporal")) {
                    partidos.add(new Partido(local, visitante));
                }
            }
            //Rota los equipos
            //Elimina el último equipo
            Equipo ultimo = lista.remove(numEquipos - 1);
            //Lo añade delante
            lista.add(1, ultimo);
        }

        int cantidadPartidosIda = partidos.size();
        //Recorre los partidos ida
        for (int i = 0; i < cantidadPartidosIda; i++) {
            //De cada partido de la ida
            Partido partidoIda = partidos.get(i);
            //Pone al equipo visitante en el equipo local y viceversa
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
                        List<Equipo> provisional = new ArrayList<Equipo>();
                        provisional.addAll(torneo.getEquipos());

                        for (int i = 0; i < provisional.size() - 1; i++) {
                            for (int j = 0; j < provisional.size() - 1 - i; j++) {
                                if (provisional.get(j).getPuntos() < provisional.get(j + 1).getPuntos()) {
                                    Equipo temporal = provisional.get(j);
                                    provisional.set(j, provisional.get(j + 1));
                                    provisional.set(j + 1, temporal);
                                }
                            }
                        }

                        System.out.println(Colores.VERDE+"--- CLASIFICACIÓN ----"+Colores.RESET);

                        int i = 1;
                        for(Equipo equipo : provisional){
                            System.out.println(i+". "+equipo.getNombre()+": "+equipo.getPuntos()+"ptos");
                            i++;
                        }

                        System.out.println();
                        System.out.println("JUGAR SIGUIENTE JORNADA");
                        System.out.println("Pulsa 1 ->");
                        System.out.println("Simular el resto de las jornadas");
                        System.out.println("Pulsa 2 ->");
                        System.out.println("Ver clasificación");
                        System.out.println("Pulsa 3 ->");
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
