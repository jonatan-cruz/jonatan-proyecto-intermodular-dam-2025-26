package OtrosEjercicios;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//PREGUNTAR COMO COÑO LIMPIAR LA CONSOLA EN X ZONAS DEL CODIGO
public class AhorcadoGame {
    public static void main(String[] args) {
        ArrayList<String> palabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("recursos/palabras.txt")) ) {
            String p;
            while ((p = br.readLine()) != null){
                palabras.add(p.trim());
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Declaración de variables
        Scanner sc = new Scanner(System.in);
        ArrayList<Character> palabraOculta = new ArrayList<>();
        String palabra = palabras.get(new Random().nextInt(palabras.size()));
        int intentosFallidos = 0;
        boolean adivinada = false;

        //Según la longitud de la palabra agregamos barras bajas
        for (int i = 0; i < palabra.length(); i++) {
            palabraOculta.add('_');
        }

        mostrarInicio();
        while (intentosFallidos < 6 && !adivinada) {
            mostrarMenu(palabraOculta);
            char letra = sc.next().toLowerCase().trim().charAt(0);  
            limpiarConsola();
//            sc.nextLine();
            //verificamos si la letra se encuentra en la palabra
            if (palabra.indexOf(letra) >= 0) {
                System.out.println("👻 ACERTASTE CAPO 👻");
                //Si está:
                //Reemplazamos todas las barra bajas por las apariciones de esa letra
                for (int i = 0; i < palabra.length(); i++) {
                    if (palabra.charAt(i) == letra) {
                        palabraOculta.set(i, letra);
                    }
                }

                if (!palabraOculta.contains('_')) {
                    System.out.printf("""
                            GANASTE 🐐
                            La palabra era: %s%n""", palabra);
                    adivinada = true;
                }
            } else {
                intentosFallidos++;
                System.out.println("TE EQUIVOCASTE, CABEZA E HUEVO 😦🥱");
            }
            System.out.println(obtenerDibujoAhorcado(intentosFallidos));
            System.out.println("Intentos fallidos: " + intentosFallidos);
        }
        //Comprobamos si salimos del while porque se acabaron los intentos
        if (intentosFallidos >= 6){
            System.out.printf("""
                    FIN DEL JUEGO, PERDISTE 💩
                    La palabra era: %s%n""", palabra);
        }
        sc.close();
    }
    public static void limpiarConsola() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static void mostrarInicio(){
        System.out.println("""
        ************************************************************
        *                                                          *
        *         🥶 Bienvenido al juego del ahorcado 🥶          *
        *                                                          *
        *  Adivina la palabra, puedes fallar un máximo de 6 veces  *
        *                                                          *
        ************************************************************
        """);
    }
    private static void mostrarMenu(ArrayList<Character> palabra) {
        System.out.print("\nPalabra: ");
        for (char c: palabra) {
            System.out.print(c + " ");
        }
        System.out.print("\nAdivina una letra: ");
    }

    private static String obtenerDibujoAhorcado(int numIntentos) {
        return switch (numIntentos){
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                      +---+
                      |   |
                          |
                          |
                          |
                    =========
                      """;
            case 2 -> """
                      +---+
                      |   |
                      O   |
                          |
                          |
                    =========
                      """;
            case 3 -> """
                      +---+
                      |   |
                      O   |
                      |   |
                          |
                    =========
                      """;
            case 4 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                          |
                    =========
                      """;
            case 5 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     /    |
                    =========
                     """;
            case 6 -> """
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     / \\  |
                    =========
                      """;
            default -> "";
        };
    }

}
