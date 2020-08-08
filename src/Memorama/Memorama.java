package Memorama;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Memorama extends JFrame {
        public Memorama(){
        this.setTitle("Memorama");
        this.setSize(wFrame, hFrame);
        this.setResizable(false);
        Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Memorama/images/memoramaIco.png"));
	setIconImage(miIcono);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         this.setLocation(dim.width/2-wFrame/2,dim.height/2-hFrame/2);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         Fondo fondo=new Fondo();
         fondo.add(new fondoArriba(),BorderLayout.NORTH);
         fondo.add(new fondoAbajo(),BorderLayout.CENTER);
         Teclado teclado=new Teclado();
         this.addKeyListener(teclado);
         this.getContentPane().add(fondo);
    }

    class Fondo extends JPanel{
        public Fondo(){
            this.setLayout(new BorderLayout());
            this.setBackground(Color.blue);
        }
    }

    class fondoArriba extends JPanel{
        public fondoArriba(){
            this.setBackground(Color.blue);
            titulo= new JLabel();
            titulo.setText("");
            titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Memorama/Images/LogoMemorama.png")));
            this.add(titulo);
        
        }
        
    }
    
    class fondoAbajo extends JPanel{
        public fondoAbajo(){
            this.setLayout(new GridLayout(4,4));
            
            
                img1=new ImageIcon(getClass().getResource("/Memorama/Images/c1.png"));
                img2=new ImageIcon(getClass().getResource("/Memorama/Images/c2.png"));
                img3=new ImageIcon(getClass().getResource("/Memorama/Images/c3.png"));
                img4=new ImageIcon(getClass().getResource("/Memorama/Images/c4.png"));
                img5=new ImageIcon(getClass().getResource("/Memorama/Images/c5.png"));
                img6=new ImageIcon(getClass().getResource("/Memorama/Images/c6.png"));
                img7=new ImageIcon(getClass().getResource("/Memorama/Images/c7.png"));
                img8=new ImageIcon(getClass().getResource("/Memorama/Images/c8.png"));
            
                revolver();
        }
        
        public void revolver(){
            for(int i=0;i<16;i++){
                int azar=(int)(Math.random()*8)+1;
                    if(azar==1){
                        if(a<2){
                        this.add(new carta(img1,1));
                        a++;
                            System.out.println("1");
                        }else{
                          azar=azar+1;
                          i--;
                        }
                    }else if(azar==2){
                        if(b<2){
                        this.add(new carta(img2,2));
                        b++;
                        System.out.println("2");
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==3){
                        if(c<2){
                        this.add(new carta(img3,3));
                        c++;
                        System.out.println("3");
                        
                        
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==4){
                        if(d<2){
                        this.add(new carta(img4,4));
                        d++;
                        System.out.println("4");
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==5){
                        if(e<2){
                        this.add(new carta(img5,5));
                        e++;
                        System.out.println("5");
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==6){
                        if(f<2){
                        this.add(new carta(img6,6));
                        f++;
                        System.out.println("6");
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==7){
                        if(g<2){
                        this.add(new carta(img7,7));
                        g++;
                        System.out.println("7");
                        }else{
                           azar=azar+1;
                           i--;

                        }
                        
                    }else if(azar==8){
                        if(h<2){
                        this.add(new carta(img8,8));
                        h++;
                        System.out.println("8");
                        
                        }else{
                           azar=1;
                           i--;

                        }
                        
                    }
                
          
                
            }
        }
        
        
       

    }
    
    
    class carta extends JLabel {
       public carta(ImageIcon imagen, int Id){
           this.Id=Id;
           imagenFront=imagen;
           this.setText("");
           this.back();
           
           
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                
                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    System.out.println("clicked");
                    
                        
                       comparar();   
                        
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
//                    System.out.println("pressed");
                    
                        front();
//                       comparar();   
                        
                } 
            });  
       }
       public void esperar(int segundos){
           try {
                        System.out.println("hilo pausado");
            Thread.sleep(segundos*500);
                        System.out.println("termina espera");
            
            } catch (Exception e) {
            System.out.println(e);
            }
       }
       
       public String toString (){
        String mensaje="Carta Numero"+Id;
        return mensaje;
       }

        public int getId() {
            return Id;
        }
        
        
                 
        

        public void setId(int Id) {
            this.Id = Id;
        }
       
       public void comparar(){  /////////////////////////////////////////////////////////////////aquí me quedé
           int  igual=0;
           
           System.out.println("empieza comparar");
//           volteados.add(this);
           
           
           for(int i=0;i<volteados.size();i++){
//               System.out.println(" -> "+volteados.get(i));
               
             if(volteados.get(i).toString().equals(this.toString())){
                 igual=1;
                 System.out.println("ya hay igual");
                 volteados.remove(0);
                 intento--;
                 pares++;
                 victoria();
             }  else{
                 System.out.println("No se encontró igual");
             }
             
           }
              if(igual==0){
                  if(intento%2==0){
                  volteados.add(this);
                      System.out.println("no hay par. Una carta agregada: "+this.Id);
                  
                  
                  intento++;
                  }else{
                      esperar(1);
                      this.back();
                      carta cartaAnterior=volteados.get(0);
                      cartaAnterior.back();
                      volteados.remove(0);
                      //poss--;
                      
                      intento++;
                  }
                 
              }
           
           
           
       }
       public void victoria(){
           if(pares==8){
               int joder=JOptionPane.showConfirmDialog(null,"Ganaste", "Felicidades", -1, 1);
               if(joder==0){
                   System.exit(0);
               }
           }
       }
       
       public void front(){
           this.setIcon(imagenFront);
           this.revalidate();
           
       }
       
        public void back(){
           this.setIcon(imagen);
       }
        int Id;
        ImageIcon imagenFront;
        ImageIcon imagen=new ImageIcon(getClass().getResource("/Memorama/Images/cardBAck.png"));
        
    }
    
    class Teclado extends KeyAdapter{
        public void keyPressed (KeyEvent tecla){
        presionada=tecla.getKeyCode();
        if(presionada== KeyEvent.VK_ESCAPE){
            System.exit(0);
        
        }
       
    }
    int presionada, anterior;
    
    }

//    public static void main(String[] args){
//        new Memorama().setVisible(true);
//    } 
   
   int hFrame=560,wFrame=500,a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,intento=0;
   JLabel titulo;
   ImageIcon img1,img2,img3,img4,img5,img6,img7,img8;
   ArrayList<carta> volteados= new ArrayList<carta>(10);
   private int pares=0;
}


