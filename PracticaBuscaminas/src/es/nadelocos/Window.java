package es.nadelocos;

import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.*;

public class Window extends JFrame{

    JButton[][] mapa;
    int 
    infoWidht,
    maxWidth = 700,
    maxHeight = 700;

    public Window(){
        
    }
    
    public void iniciar(Buscaminas buscaminas){
        int 
        twidth  = buscaminas.mapa.length,
        theight = buscaminas.mapa[0].length,
        width = maxWidth/twidth,
        height = maxHeight/theight;
        mapa = new JButton[twidth][theight];
        Window window = this;

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);

        for(int x = 0; x < twidth;x++){
            for(int y = 0; y < theight;y++){
                final int _x = x,_y = y;
                mapa[x][y] = new JButton();
                JButton boton = mapa[x][y];
                boton.setSize(new Dimension(width,height));
                //boton.setMaximumSize(boton.getSize());
                boton.setMinimumSize(boton.getSize());
                boton.setLocation(height*x, width*y);
                boton.setHideActionText(false);
                if(buscaminas.mapa[x][y] == '*'){
                    //mina
                    boton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("mina pulsada");
                            boton.removeActionListener(this);
                            for(int x = 0; x < twidth;x++){
                                for(int y = 0; y < theight;y++){
                                    mapa[x][y].setVisible(false);
                                    window.remove(mapa[x][y]);
                                }
                            }
                        }
                    });
                }else{
                    //libre
                    boton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if(buscaminas.mapaNum[_x][_y] == 0){
                                minasEn0(_x,_y,buscaminas,this);
                            }else{
                                boton.setSelected(true);
                                boton.setText(""+buscaminas.mapaNum[_x][_y]);
                                buscaminas.mapa[_x][_y] = '.';
                                boton.removeActionListener(this);
                            }
                        }
                    });
                }
                add(boton);
                
            }
        }

        setSize(new Dimension(700,700));
        setVisible(true);
    }

    private void minasEn0(int x, int y,Buscaminas buscaminas, ActionListener action){
         
        if(x < buscaminas.ancho && x >= 0 && y < buscaminas.alto && y >= 0 && buscaminas.obtenerPuntoEnMapa(x, y) == '_' && buscaminas.obtenerEnMapaNum(x, y) == 0){
            JButton boton = this.mapa[x][y];
            System.out.println("si");
            boton.setSelected(true);
            boton.setText(""+buscaminas.mapaNum[x][y]);
            buscaminas.mapa[x][y] = '.';
            boton.removeActionListener(action);
            minasEn0(x-1, y, buscaminas, action);
            minasEn0(x-1, y+1, buscaminas, action);  
            minasEn0(x-1, y-1, buscaminas, action);
            minasEn0(x, y, buscaminas, action);
            minasEn0(x, y+1, buscaminas, action);
            minasEn0(x, y-1, buscaminas, action);
            minasEn0(x+1, y, buscaminas, action);
            minasEn0(x+1, y+1, buscaminas, action);
            minasEn0(x+1, y-1, buscaminas, action);
        }
    }
    
}
