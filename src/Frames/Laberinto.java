package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Laberinto extends javax.swing.JFrame{
    public Laberinto(){
        this.setTitle("Memorama");
        this.setSize(wFrame, hFrame);
        this.setResizable(false);
        this.setUndecorated(false);
        this.addKeyListener(new Teclado());
        try{
            Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/LBIco.png"));
            setIconImage(miIcono);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un errror al cargar la imagen del ícono. Error "+e);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  //Guarda el tamaño de la pantalla donde se ejecuta
        this.setLocation(dim.width/2-wFrame/2,dim.height/2-hFrame/2); //Establece la ubicación del Frame en el centro de la pantalla 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLayout(new BorderLayout());
        this.add(new FondoArriba(),BorderLayout.NORTH);
        this.add(fondoBajo,BorderLayout.CENTER);
       
        
//        Ciclo ciclo =new Ciclo();
//        Thread hilo =new Thread(ciclo);
//        hilo.start();
        
    }
    
    class FondoArriba extends javax.swing.JPanel{
        FondoArriba(){
            this.setBackground(java.awt.Color.ORANGE);
            ImageIcon titleimg = new ImageIcon(getClass().getResource("/Images/LBtitle.png"));
            titulo=new JLabel("",titleimg,JLabel.CENTER);
            ImageIcon dudasimg = new ImageIcon(getClass().getResource("/Images/LBdudas.png"));
            dudas=new JLabel("",dudasimg,JLabel.CENTER);
            Dimension dudasTam=new Dimension(90,60);
            dudas.setPreferredSize(dudasTam);
            java.awt.Font mifuente = new java.awt.Font("Consolas",Font.BOLD,20);
            nivel=new JLabel("NIVEL",JLabel.CENTER);
            nivel.setFont(mifuente);
            nivel2=new JLabel("1",JLabel.CENTER);
            nivel2.setFont(mifuente);
            score=new JPanel(new BorderLayout());
            score.setBackground(Color.ORANGE);
            Dimension scoreTam=new Dimension(90,50);
            score.setPreferredSize(scoreTam);
            score.add(nivel,BorderLayout.NORTH);
            score.add(nivel2,BorderLayout.CENTER);
            this.add(titulo);
            this.add(dudas);
            this.add(score);
        }
    }
    
    
    ////////----------------------------------------------------------------------------------------------------------------------------------
     class FondoAbajo extends javax.swing.JPanel{
          FondoAbajo(){
              this.setBackground(java.awt.Color.ORANGE);
              enredadera.estableceLaberinto();
          }
        public void paint(Graphics g){
            super.paint(g);
            new Bounce().paint(g);
            enredadera.paint(g);
        }
        
    }
    
     
     
    public static void main(String[] args) {
        new Laberinto().setVisible(true);
    }
    private Enredadera enredadera=new Enredadera();
    private  FondoAbajo fondoBajo = new FondoAbajo();
    private JPanel score;
    private int wFrame=1004, hFrame=657;
    private JLabel titulo, dudas,nivel,nivel2;
    private static int Bouncex=40,Bouncey=40,Bouncetamx=38,Bouncetamy=38;
    
    
    
    class Bounce {
         Teclado teclas=new Teclado();
       
        public void paint(Graphics g){
            g.setColor(Color.RED);
            g.fillOval(Bouncex, Bouncey, Bouncetamx, Bouncetamy);
//            g.drawOval(x, y, tamx, tamy);
        }
    }
    
     class Enredadera{
        
        public void paint(Graphics g){
            g.setColor(Color.blue);
//            int[][] laberinto= regresaLaberinto();
            for(int i=0;i<14;i++){
                for(int j=0;j<25;j++){
                    if(laberintoActual[i][j]==1){
                       g.fillRect(j*Bloquex, i*Bloquey, Bloquetamx, Bloquetamy);
//                       g.drawRect(j*40, i*40, 30, 30);
                       
                    } 
                            
                }
            }
        }
        
        public boolean esMeta(){
            if (Bouncex==Bloquex*bloquemetax && Bouncey==Bloquey*bloquemetay)return true;
            else return false;
        }
        
        public void esBloque(){
            System.out.println(Bouncex/40 +" -> "+ Bouncey/40);
//            if(laberintoActual[Bouncex/40][Bouncey/40]==1){ 
//                System.out.println("mamo");
//                
//            }
        }
       
        public void setMeta(int coordenadaX, int coordenadaY){
            bloquemetax=coordenadaX;
            bloquemetay=coordenadaY;
        }
        
        public void estableceLaberinto(){
        laberintoActual=laberinto1;
        }
        
       
        private int bloquemetax=23,bloquemetay=12;
        private int Bloquex=40,Bloquey=40,Bloquetamx=38,Bloquetamy=38;
        private int[][] laberintoActual;
        private int[][] laberinto2={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,0,1,0,1,1,1,0,1,0,0,0,0,1,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,1,0,0,1,1,1,1,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto1={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,1,1,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto3={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto4={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        
        
        
    } 
    
    
    class Teclado extends java.awt.event.KeyAdapter{
        
        public void keyPressed (KeyEvent tecla){
            presionada=tecla.getKeyCode();
            if(presionada== KeyEvent.VK_ESCAPE){
                int respuesta=JOptionPane.showConfirmDialog(null,"-- SALIR --","¿Estás seguro?",0,3);
                if (respuesta==0)System.exit(0);
            }
            else if(presionada==KeyEvent.VK_DOWN){
                Bouncey=Bouncey+40;
                fondoBajo.repaint();
                enredadera.esBloque();
                if(enredadera.esMeta())JOptionPane.showMessageDialog(null, "Meta alcanzada","  -- MUY BIEN --  ",1);
//                System.out.println("Abajo");
            }else if (presionada==KeyEvent.VK_UP){
                 Bouncey=Bouncey-40;
                 fondoBajo.repaint();
                 enredadera.esBloque();
                 if(enredadera.esMeta())JOptionPane.showMessageDialog(null, "Meta alcanzada","  -- MUY BIEN --  ",1);
//                System.out.println("Arriba");
            }else if (presionada==KeyEvent.VK_RIGHT){
                Bouncex=Bouncex+40;
                fondoBajo.repaint();
                enredadera.esBloque();
                if(enredadera.esMeta())JOptionPane.showMessageDialog(null, "Meta alcanzada","  -- MUY BIEN --  ",1);
//                System.out.println("Derecha");
            }else if (presionada==KeyEvent.VK_LEFT){
                Bouncex=Bouncex-40;
                fondoBajo.repaint();
                enredadera.esBloque();
                if(enredadera.esMeta())JOptionPane.showMessageDialog(null, "Meta alcanzada","  -- MUY BIEN --  ",1);
//                System.out.println("Izquierda");
            }
//            
        }
           int presionada; 
        }
}
