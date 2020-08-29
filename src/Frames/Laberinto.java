package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.Image;
import javax.swing.JPanel;

public class Laberinto extends javax.swing.JFrame{
    public Laberinto(){
        this.setTitle("Memorama");
        this.setSize(wFrame, hFrame);
        this.setResizable(false);
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
            Dimension cuadroarriba= new Dimension(wFrame,100);
//            this.setSize(cuadroarriba);
            this.setPreferredSize(cuadroarriba);
            
            
            
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
    
    
    private int wFrame=1000, hFrame=650;
    
    
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
}
