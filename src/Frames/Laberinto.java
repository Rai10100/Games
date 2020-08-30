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
            Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/memoramaIco.png"));
            setIconImage(miIcono);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un errror al cargar la imagen del ícono. Error "+e);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  //Guarda el tamaño de la pantalla donde se ejecuta
        this.setLocation(dim.width/2-wFrame/2,dim.height/2-hFrame/2); //Establece la ubicación del Frame en el centro de la pantalla 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLayout(new BorderLayout());
        this.add(new FondoArriba(),BorderLayout.NORTH);
        this.add(new FondoAbajo(),BorderLayout.CENTER);
        
    }
    
    public class FondoArriba extends javax.swing.JPanel{
        FondoArriba(){
            this.setBackground(java.awt.Color.ORANGE);
//            Dimension cuadroarriba= new Dimension(wFrame,100);
//            this.setPreferredSize(cuadroarriba);
            
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
    
    public class FondoAbajo extends javax.swing.JPanel{
          FondoAbajo(){
              this.setBackground(java.awt.Color.ORANGE);
              
          }
            
        public void paint(Graphics g){
            new Enredadera().paint(g);
        }
        
    }
    
    public static void main(String[] args) {
        new Laberinto().setVisible(true);
    }
    
    private JPanel score;
    private int wFrame=1000, hFrame=650;
    private JLabel titulo, dudas,nivel,nivel2;
    
    
    
    public class Bounce {
       
        public void paint(Graphics g){
            
        }
        
    }
    
    public class Enredadera{
        
        public void paint(Graphics g){
            g.setColor(Color.blue);
            g.fillRect(40, 100, 100, 10);
            g.drawRect(40, 100, 100, 10);
        }
        
    }
    
    class Teclado extends java.awt.event.KeyAdapter{
        public void keyPressed (KeyEvent tecla){
            presionada=tecla.getKeyCode();
            if(presionada== KeyEvent.VK_ESCAPE){
                int respuesta=JOptionPane.showConfirmDialog(null,"-- SALIR --","¿Estás seguro?",0,3);
                if (respuesta==0)System.exit(0);
            }else if(presionada==KeyEvent.VK_UP){

            }
        }
            int presionada, anterior; 
        }
}
