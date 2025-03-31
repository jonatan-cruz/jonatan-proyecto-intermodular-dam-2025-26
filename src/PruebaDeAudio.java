package OtrosEjercicios;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PruebaDeAudio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cancion1 = "recursos/cancion1.wav";
        String cancion2 = "recursos/cancion2.wav";
        File file = null;

        mostrarMenuCanciones();
        int opcion = comprobarOpcion(sc);
        limpiar();
        //Después de leer el int vaciamos el buffer
        sc.nextLine();

        switch(opcion) {
            case 1 -> file = new File(cancion1);
            case 2 -> file = new File(cancion2);
            default -> System.out.println("Opcion no valida");
        }

        try (AudioInputStream audio = AudioSystem.getAudioInputStream(file)) {

            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            String respuesta = "";
            while (!respuesta.equals("Q")) {
                mostrarMenu();
                respuesta = sc.nextLine().toUpperCase();
                switch (respuesta) {
                    case "P" -> clip.start();
                    case "S" -> clip.stop();
                    case "R" -> clip.setMicrosecondPosition(0);
                    case "Q" -> clip.close();
                    default -> System.out.println("Opción inválida");
                }
            }
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("FIN DE LA REPRODUCCIÓN");
            sc.close();
        }

    }

    private static int comprobarOpcion(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("No es un número lo que estás eligiendo, gil");
            sc.nextLine();
            mostrarMenuCanciones();
        }
        int opcion = sc.nextInt();
        if (opcion < 1 || opcion > 2) {
            System.out.println("Opcion no valida, elige un número de canción que esté en la biblioteca");
            mostrarMenuCanciones();
            opcion = comprobarOpcion(sc);
        }
        return opcion;
    }

    private static void mostrarMenuCanciones() {
        System.out.println("\nCANCIONES DE LA BIBLIOTECA");
        System.out.println("-------------------------");
        System.out.println("\t1. Why Are Sundays So Depressing? - The Strokes");
        System.out.println("\t2. It Was A Good Day - Ice Cube");
        System.out.println("-------------------------");
        System.out.print("Elige el número de la canción que quieres reproducir: ");
    }

    public static void mostrarMenu() {
        System.out.println("\nREPRODUCTOR TO SIMPLE");
        System.out.println("-------------------------");
        System.out.println("\tP = Play");
        System.out.println("\tS = Stop");
        System.out.println("\tR = Reset");
        System.out.println("\tQ = Quit");
        System.out.println("-------------------------");
        System.out.print("Elige una opción: ");
    }
    public static void limpiar(){

    }
}
