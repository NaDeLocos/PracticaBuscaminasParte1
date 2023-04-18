package es.rgmf;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    char[] tablero;
        int columnas;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Bienvenid@ al Buscaminas");
        System.out.println("==================================================================");
        columnas = pedirTamTablero(entrada, 5, 20);

        tablero = crearTablero(columnas);
        mostrarTablero(tablero);

        entrada.close();
    }

    /**
     * Pide un tamaño de tablero (número entero) entre tamMin y tamMax.
     *
     * @param entrada objeto Scanner para pedir el tamño del tablero al usuario.
     * @param tamMin mínimo tamaño de tablero válido.
     * @param tamMax máximo tamaño de tablero válido.
     * @return el número de columnas del tablero, es decir, el número entre tamMin y tamMax dado por el usuario.
     */
    // TODO escribe el método pedirTamTablero descrito en el comentario de arriba.
    private static int pedirTamTablero(Scanner entrada, int tamMin, int tamMax){
        System.out.print("Introduce un tamaño del tablero(min:"+tamMin+" max:"+tamMax+"):");
        int numElegido = entrada.nextInt();
        if(numElegido < tamMin){
            numElegido = tamMin;
        }else if(numElegido > tamMax){
            numElegido = tamMax;
        }

        return numElegido;
    }

    /**
     * Crea un array de caracteres de un tamaño dado por columnas.
     *
     * @param columnas tamaño del tablero, es decir, número de elementos del array de caracteres.
     * @return el tablero, es decir, el array de char inicializado con * o números.
     */
    // TODO escribe el método crearTablero descrito en el comentario de arriba.
    private static char[] crearTablero(int columnas){
        char[] tabla = new char[columnas];
        for(int i = 0;i < columnas;i++){
            double rang = Math.random();
            if(rang > 0.7){
                tabla[i] = '_';
            }else{
                tabla[i] = '*';
            }
        }
        for(int i = 0;i < columnas;i++){
            if(tabla[i] == '_'){
                tabla[i] = (""+calcularBombasAlrededor(tabla, i)).charAt(0);
            }
        }
        return tabla;
    }

    /**
     * Este método lo puedes usar en el método crearTablero para calcular el número que se debe poner donde no hay
     * bombas.
     *
     * @param tablero tablero.
     * @param indice posición del array en el que se quiere calcular el número de bombas que hay alrededor.
     * @return número de bombas que hay alrededor del indice en el tablero.
     */
    // TODO escribe el método calcularBombasAlrededor descrito en el comentario de arriba.
    static private int calcularBombasAlrededor(char[] tablero, int indice){
        int
        puntero = indice,
        cantidadBombas = 0;

        //calcular a izquierda
        puntero--;
        if(!(puntero == -1)){
            while(tablero[puntero] == '*'){
                cantidadBombas++;
                puntero--;
                if(puntero == -1){break;}
            }
        }

        //calcular a derecha
        puntero = indice + 1;
        if(!(puntero >= tablero.length - 1)){
            while(tablero[puntero] == '*'){
                cantidadBombas++;
                puntero++;
                if(puntero == tablero.length){break;}
            }
        }
        
        
        return cantidadBombas;
    }

    /**
     * Método que muestra el tablero usando un formato comprensible por el usuario.
     *
     * @param tablero tablero a mostrar.
     */
    private static void mostrarTablero(char[] tablero) {
        System.out.print("+");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("---+");
        }
        System.out.println();

        System.out.print("| ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(tablero[i] + " | ");
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("---+");
        }
        System.out.println();
    }
}
