package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
   

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();
        solicitarDatos();  

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion no valida, intente nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Descripcion: Este metodo se encarga de solicitar el precio y la cantidad de unidades vendidas de cada referencia
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pre: El Scanner reader debe estar inicializado
     * pos: Los arreglos precios y unidades quedan llenos con los datos ingresados por el usuario
     */
    
    public static void solicitarDatos( ){
    
        for (int i = 0; i < precios.length; i++) {
            
            System.out.println("Ingresa el precio de la referencia " + (i + 1) + ":");
            precios[i] = reader.nextDouble();

            System.out.println("Ingresa la cantidad de unidades vendidas de la referencia " + (i + 1) + ":");
            unidades[i] = reader.nextInt();
        }

    }

    /**
     * Descripcion: Este metodo calcula la cantidad total de unidades vendidas en el dia
     * pre: Los arreglos unidades deben estar inicializados
     * pos: Retorna la cantidad total de unidades vendidas
     */
    public static int calcularTotalUnidadesVendidas(){
        int totalUnidades = 0;
        for (int i = 0; i < unidades.length; i++) {
            totalUnidades += unidades[i];
        }
        return totalUnidades;
    }

    /**
     * Descripcion: Este metodo calcula el precio promedio de las referencias de producto vendidas en el dia
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pos: Retorna el precio promedio de las referencias de producto
     */
    public static double calcularPrecioPromedio(){
        double sumaPrecios = 0;
        int totalUnidades = calcularTotalUnidadesVendidas();

        for (int i = 0; i < precios.length; i++) {
            sumaPrecios += precios[i] * unidades[i];
        }

        if (totalUnidades > 0) {
            return sumaPrecios / totalUnidades;
        } else {
            return 0;
        }
    }

    /**
     * Descripcion: Este metodo calcula las ventas totales (dinero recaudado) durante el dia
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pos: Retorna el total de ventas durante el dia
     */
    public static double calcularVentasTotales(){
        double totalVentas = 0;
        for (int i = 0; i < precios.length; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

    /**
     * Descripcion: Este metodo consulta el numero de referencias de productos que han superado un limite minimo de ventas
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pre: El limite debe ser un valor positivo
     * pos: Retorna el numero de referencias que superan el limite
     */
    public static int consultarReferenciasSobreLimite(double limite){
        int conteo = 0;
        for (int i = 0; i < precios.length; i++) {
            if (precios[i] * unidades[i] > limite) {
                conteo++;
            }
        }
        return conteo;
    }
}
