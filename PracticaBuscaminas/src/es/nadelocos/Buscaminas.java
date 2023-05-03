package es.nadelocos;

import java.util.Scanner;

public class Buscaminas {

    int 
    anchoMax = 15,
    altoMax = 15,
    anchoMin = 3,
    altoMin = 3,
    ancho,
    alto;

    char[][] mapa;
    int[][] mapaNum;
    
    public Buscaminas(){

    }

    public void iniciarBuscaminas(Scanner scan){
        System.out.print("Escribe el ancho(max:"+anchoMax+"min:"+anchoMin+"): ");
        ancho = scan.nextInt();
        if(ancho < anchoMin){
            ancho = anchoMin;
        }else if(ancho > anchoMax){
            ancho = anchoMax;
        }
        System.out.print("Escribe el largo(max:"+altoMax+"min:"+altoMin+"): ");
        alto = scan.nextInt();
        if(alto < altoMin){
            alto = altoMin;
        }else if(alto > altoMax){
            alto = altoMax;
        }

        mapa = new char[ancho][alto];
        for(int x = 0; x < ancho;x++){
            for(int y = 0; y < alto;y++){
                if(Math.random() < 0.75){
                    mapa[x][y] = '_';
                }else{
                    mapa[x][y] = '*';
                }
            }
        }

        mapaNum = new int[ancho][alto];
        for(int x = 0; x < ancho;x++){
            for(int y = 0; y < alto;y++){
                int xaux = x,yaux = y;
                if(mapa[x][y] == '*'){
                    mapaNum[x][y] = 9;
                }else{
                    int cantidad = 0;
                    if(existeMinaEnPunto(x-1,y-1)){cantidad++;}
                    if(existeMinaEnPunto(x-1,y)){cantidad++;}
                    if(existeMinaEnPunto(x-1,y+1)){cantidad++;}
                    if(existeMinaEnPunto(x,y-1)){cantidad++;}
                    if(existeMinaEnPunto(x,y)){cantidad++;}
                    if(existeMinaEnPunto(x,y+1)){cantidad++;}
                    if(existeMinaEnPunto(x+1,y-1)){cantidad++;}
                    if(existeMinaEnPunto(x+1,y)){cantidad++;}
                    if(existeMinaEnPunto(x+1,y+1)){cantidad++;}
                    mapaNum[x][y] = cantidad;
                }
            }
        }
    }

    public int obtenerMinasAlrededorDeCasilla(int x, int y){
        return mapaNum[x][y];
    }

    public boolean hayMina(int x, int y){
        if(mapa[x][y] == '*'){
            return true;
        }
        return false;
    }

    public char obtenerPuntoEnMapa(int x,int y){
        return mapa[x][y];
    }

    public int obtenerEnMapaNum(int x,int y){
        return mapaNum[x][y];
    }

    public void mostrarBuscaminasEnConsola(){
        for(int y = 0; y < alto;y++){
            for(int x = 0; x < ancho;x++){
                System.out.print(mapa[x][y]);
            }
            System.out.println();
        }
    }

    public void mostrarBuscaminasConNumerosEnConsola(){
        for(int y = 0; y < alto;y++){
            for(int x = 0; x < ancho;x++){
                System.out.print("|" + mapaNum[x][y]);
            }
            System.out.print("|");
            System.out.println();
        }
    }

    private boolean existeMinaEnPunto(int x, int y){
        if(x < ancho && x >= 0 && y < alto && y >= 0){
            if(mapa[x][y] == '*'){return true;}
        }

        return false;
    }
}