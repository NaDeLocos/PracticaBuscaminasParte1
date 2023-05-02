package es.nadelocos;

import java.util.Scanner;

/**
 * @author abderra
 * @version 1.0
 */
public class MainVdos {

    Scanner scan = new Scanner(System.in);

    public void main(String[] args) {
        Buscaminas buscaminas = new Buscaminas();

        buscaminas.iniciarBuscaminas(scan);
        Window window = new Window();
        window.iniciar(buscaminas);

        //buscaminas.mostrarBuscaminasEnConsola();
        buscaminas.mostrarBuscaminasConNumerosEnConsola();

        System.out.println("partida terminada");
    }
}
