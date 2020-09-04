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
        this.setUndecorated(true);
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
        this.setVisible(true);
            enredadera.nivel();
        
       
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
            nivel2=new JLabel("0",JLabel.CENTER);
            nivel2.setFont(mifuente);
            score=new JPanel(new BorderLayout());
            score.setBackground(Color.ORANGE);
            Dimension scoreTam=new Dimension(90,50);
            score.setPreferredSize(scoreTam);
            score.add(nivel,BorderLayout.NORTH);
            score.add(nivel2,BorderLayout.CENTER);
            dudas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(null,"<html>¿Tienes dudas? El juego es muy sencillo jaja<br>"
                        + "Para salir del juego pulsa la tecla 'ESC'</html>");
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dudas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LBdudasOsc.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                dudas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LBdudas.png")));
            }
        });
            this.add(titulo);
            this.add(dudas);
            this.add(score);
            
            
            
        }
    }
    
    
    ////////----------------------------------------------------------------------------------------------------------------------------------
     class FondoAbajo extends javax.swing.JPanel{
          FondoAbajo(){
              this.setBackground(java.awt.Color.ORANGE);
//              enredadera.estableceLaberinto(0);
//              enredadera.nivel();
              
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
    private FondoAbajo fondoBajo = new FondoAbajo();
    private JPanel score;
    private int wFrame=999, hFrame=629, presionada ;
    private JLabel titulo, dudas,nivel,nivel2;
    private static int Bouncex=40,Bouncey=40,Bouncetamx=38,Bouncetamy=38;
    
    
    
    class Bounce {
//         Teclado teclas=new Teclado();
       
        public void paint(Graphics g){
            g.setColor(new Color(160,30,144));
            g.fillOval(Bouncex, Bouncey, Bouncetamx, Bouncetamy);
//            g.drawOval(x, y, tamx, tamy);
        }
    }
    
    
     class Enredadera{
        
        public void paint(Graphics g){
            
//            int[][] laberinto= regresaLaberinto();
            for(int i=0;i<14;i++){
                for(int j=0;j<25;j++){
                    if(laberintoActual[i][j]==1){
                        g.setColor(new Color(192,57,43));
                       g.fillRect(j*Bloquex, i*Bloquey, Bloquetamx, Bloquetamy);
//                     g.drawRect(j*40, i*40, 30, 30);
                    }else if (laberintoActual[i][j]==2){
                        g.setColor(new Color(253,12,224));
                        g.fillRect(j*Bloquex,i*Bloquey,Metatamx,Metatamy);
                    }else if (laberintoActual[i][j]==3){
                        g.setColor(new Color(147,51,44));
                        g.fillRect(j*Bloquex,i*Bloquey,Metatamx,Metatamy);
                    }
                            
                }
            }
        }
        
        public void nivel(){
            if (Nivel==0){
                enredadera.estableceLaberinto(0);
//                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"<html>Tú eres la bolita morada,la meta es el cuadro rosita.<br> Muévete con las flechas de dirección."
                        + "Para salir pulsa 'ESC'<br>¡Ya sabes que hacer! </html>","Bienvenid@",1);
            }else if(Nivel==1){
                nivel2.setText("1");
                enredadera.estableceLaberinto(1);
                enredadera.ubicaBounce(1,1);
                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"Vale! Nivel 1");
                
            }else if(Nivel==2){
                nivel2.setText("2");
                enredadera.estableceLaberinto(2);
                enredadera.ubicaBounce(1,1);
                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"Bienvenido al nivel 2");
                
            }else if(Nivel==3){
                nivel2.setText("3");
                enredadera.estableceLaberinto(3);
                enredadera.ubicaBounce(1,1);
                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"Bienvenido al nivel 3");
                
            }else if(Nivel==4){
                
                nivel2.setText("4");
                enredadera.estableceLaberinto(4);
                enredadera.ubicaBounce(1,1);
                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"Bienvenido al nivel 4");
            }else if(Nivel==5){
                nivel2.setText("5");
                enredadera.estableceLaberinto(5);
                enredadera.ubicaBounce(1,1);
                fondoBajo.repaint();
                JOptionPane.showMessageDialog(null,"Bienvenido al nivel 5");
            }else if(Nivel==6){
                nivel2.setText("6");
            }else if(Nivel==7){
                nivel2.setText("7");
            }                      
            
            
            
        }
        
        public void ubicaBounce(int x, int y){
        Bouncex=x*40;
        Bouncey=y*40;
        }
        
        public void esMeta(){
            if (Bouncex==Bloquex*bloquemetax && Bouncey==Bloquey*bloquemetay){
                if(primeraVezMeta){
                    JOptionPane.showMessageDialog(null, "Meta alcanzada","  -- MUY BIEN --  ",1);
                    primeraVezMeta=false;
                }
                Nivel++;
                nivel();
//                return true;
            }
//            else return false;
        }
        
        public void esBomba(){
            
            if(laberintoActual[(Bouncey)/40][(Bouncex)/40]==3){
                if(primeraVezBomba==0){
                    primeraVezBomba++;
                    JOptionPane.showMessageDialog(null, "Caíste en una bomba, debes empezar de nuevo","  --      ¡¡¡¡¡¡ BOMBA !!!!!! --     ",0);
                }
                ubicaBounce(1,1);
            }
            
        }
        
        public boolean esBloque(){
            if(presionada==KeyEvent.VK_DOWN){
                if(laberintoActual[(Bouncey+40)/40][(Bouncex)/40]==1)return true;
                    else return false;
            }else if (presionada==KeyEvent.VK_UP){
                if(laberintoActual[(Bouncey-40)/40][(Bouncex)/40]==1)return true;
                    else return false;
            }else if (presionada==KeyEvent.VK_RIGHT){
                if(laberintoActual[(Bouncey)/40][(Bouncex+40)/40]==1)return true;
                    else return false;
            }else if (presionada==KeyEvent.VK_LEFT){
                if(laberintoActual[(Bouncey)/40][(Bouncex-40)/40]==1)return true;
                    else return false;
            }else return false;
        }
        
       
        public void estableceMeta(int coordenadaX, int coordenadaY){
            bloquemetax=coordenadaX;
            bloquemetay=coordenadaY;
        }
        
        public void estableceLaberinto(int numero){
            if(numero==0)laberintoActual=laberinto0;
            else if (numero==1)laberintoActual=laberinto1;
            else if (numero==2)laberintoActual=laberinto2;
            else if (numero==3)laberintoActual=laberinto3;
            else if (numero==4)laberintoActual=laberinto4;
            else if (numero==5)laberintoActual=laberinto5;
            else if (numero==6)laberintoActual=laberinto6;
            else if (numero==7)laberintoActual=laberinto7;
            else JOptionPane.showMessageDialog(null,"Hubo un error con @estableceLaberinto","   --- ERROR ---",0);
        }
        private boolean primeraVezMeta=true;
        private int Metatamx=38,Metatamy=38,Nivel=0,primeraVezBomba=0; 
        private Graphics banderaMeta;
        private int bloquemetax=23,bloquemetay=12;
        private int Bloquex=40,Bloquey=40,Bloquetamx=38,Bloquetamy=38;
        private int[][] laberintoActual;
        private int[][] laberinto7={
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
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto6={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,0,1,0,1,0,1,0,1,0,0,0,0,1,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto5={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,3,0,0,0,0,0,1,1,1,3,0,0,0,0,0,0,3,0,0,0,1},
            {1,3,3,0,1,0,3,0,3,0,1,0,0,0,0,3,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,3,0,1,0,1,0,3,1,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,3,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,3,0,1,0,3,0,1},
            {1,0,1,1,1,1,3,0,3,1,1,3,0,1,0,1,1,1,1,0,3,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0,1},
            {1,0,3,0,0,0,0,0,0,0,0,1,0,3,1,1,0,0,3,1,1,0,1,0,1},
            {1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,0,1,0,3,0,1},
            {1,0,1,0,0,0,1,0,0,1,0,1,0,1,1,1,3,0,1,0,0,0,0,0,1},
            {1,0,3,0,1,0,1,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,3,0,0,0,0,0,0,0,0,1,3,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto4={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,0,1,0,1,0,1,0,1,0,0,0,0,1,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,1,1,1,1,1,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1},
            {1,0,1,0,0,0,1,0,0,1,0,1,0,1,1,1,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto3={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,0,0,3,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,3,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,3,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto1={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1},
            {1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto2={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        private int[][] laberinto0={
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
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        
        
        
    } 
    
    
    class Teclado extends java.awt.event.KeyAdapter{
        
        public void keyPressed (KeyEvent tecla){
//            if (primerMovimiento==0) {
//                enredadera.nivel();
//                primerMovimiento++;
//            }else{
            presionada=tecla.getKeyCode();
            if(presionada== KeyEvent.VK_ESCAPE){
                int respuesta=JOptionPane.showConfirmDialog(null,"-- SALIR --","¿Estás seguro?",0,3);
                if (respuesta==0)System.exit(0);
            }
            else if(presionada==KeyEvent.VK_DOWN){
                if (!enredadera.esBloque()){
                    Bouncey=Bouncey+40;
                    enredadera.esBomba();
                    enredadera.esMeta();
                    fondoBajo.repaint();
                }
                
            }else if (presionada==KeyEvent.VK_UP){
                 if (!enredadera.esBloque()){
                     Bouncey=Bouncey-40;
                     enredadera.esBomba();
                     enredadera.esMeta();
                fondoBajo.repaint();
                 }
                
//                 enredadera.esMeta();
//                System.out.println("Arriba");
            }else if (presionada==KeyEvent.VK_RIGHT){
                if (!enredadera.esBloque()){
                Bouncex=Bouncex+40;
                enredadera.esBomba();
                enredadera.esMeta();
                fondoBajo.repaint();
                }
                
//                enredadera.esMeta();
//                System.out.println("Derecha");
            }else if (presionada==KeyEvent.VK_LEFT){
                 if (!enredadera.esBloque()){
                 Bouncex=Bouncex-40;
                 enredadera.esBomba();
                 enredadera.esMeta();
                fondoBajo.repaint();
                 }
                
//                enredadera.esMeta();
//                System.out.println("Izquierda");
            }else{
                
            }
            
//            
        }
          
//    }
        int primerMovimiento=0;
    }
}
