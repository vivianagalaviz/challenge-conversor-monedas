package com.alura.conversor;
import java.util.Scanner;

public class Conversor {

    public static void mostrarMenu() {

        Scanner lectura = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {

            System.out.println("""
                *******************************
                Sea bienvenido/a al Conversor de Moneda

                1) Dólar -> Peso argentino
                2) Peso argentino -> Dólar
                3) Dólar -> Real brasileño
                4) Real brasileño -> Dólar
                5) Dólar -> Peso colombiano
                6) Peso colombiano -> Dólar
                7) Salir
                *******************************
                """);

            System.out.print("Elija una opción válida: ");
            try {
                opcion = lectura.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                lectura.nextLine();
                continue;
            }

            String base = "";
            String destino = "";

            switch (opcion) {
                case 1:
                    base = "USD";
                    destino = "ARS";
                    break;
                case 2:
                    base = "ARS";
                    destino = "USD";
                    break;
                case 3:
                    base = "USD";
                    destino = "BRL";
                    break;
                case 4:
                    base = "BRL";
                    destino = "USD";
                    break;
                case 5:
                    base = "USD";
                    destino = "COP";
                    break;
                case 6:
                    base = "COP";
                    destino = "USD";
                    break;
                case 7:
                    System.out.println("Finalizando programa...");
                    continue;
                default:
                    System.out.println("Opción inválida.");
                    continue;
            }

            System.out.print("Ingrese el valor que desea convertir: ");

            double cantidad;

            try {
                cantidad = lectura.nextDouble();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                lectura.nextLine();
                continue;
            }

            ConsultaAPI consulta = new ConsultaAPI();

            try {
                double tasa = consulta.obtenerTasa(base, destino);
                double resultado = cantidad * tasa;

                System.out.printf("Tasa actual: %.4f%n", tasa);
                System.out.printf("El valor convertido es: %.2f%n", resultado);

            } catch (Exception e) {
                System.out.println("Error al obtener la tasa de cambio.");
            }
        }

        lectura.close();
    }
}