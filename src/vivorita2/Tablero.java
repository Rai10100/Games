package vivorita2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JLabel;


public class Tablero extends javax.swing.JFrame{
    public Tablero(){
        setTitle("Vivorita");
        setSize(widht,height);
        setResizable(false);
        setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dimArriba= new Dimension(495,60);
        this.setLocation(dim.width/2-widht/2,dim.height/2-height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/vivorita2/images/vivoIco.png"));
	setIconImage(miIcono);
        punto = new Point (widht/2-(LaVivorita.puntox/2),height/2-(LaVivorita.puntoy/2));
        ponercomida();
        panelVivorita= new LaVivorita();
        Teclado teclado=new Teclado();
        this.addKeyListener(teclado);
        getContentPane().setLayout(new BorderLayout(0,0));
        cerrar=new JLabel();
        titulo=new JLabel();
        cerrar.setText("");
        titulo.setFont(miletra);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vivorita2/images/vivoIco.png")));
        titulo.setForeground(new Color(14,102,85));
        titulo.setText("Vivorita Game");
        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vivorita2/images/borrar.png")));
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vivorita2/images/borrarOsc.png")));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vivorita2/images/borrar.png")));
            }
        });
        
        
        
        fondo= new javax.swing.JPanel();
        fondo.setBackground(new Color(40,180,99));
        fondo.setPreferredSize(dimArriba);
        fondo.setLayout(new BorderLayout());
        fondo.add(cerrar, BorderLayout.EAST);
        fondo.add(titulo, BorderLayout.WEST);

        this.getContentPane().add(fondo,BorderLayout.NORTH);
        this.add(panelVivorita, BorderLayout.CENTER);
        this.setVisible(true); 
        Ciclo ciclo = new Ciclo();
        Thread hilo =new Thread (ciclo);
        hilo.start();
        this.getContentPane().setBackground(Color.yellow);
        
    }
    public void ponercomida(){
        rdmx=((int) ((Math.random()*33)+1))*15;
        rdmy=((int) ((Math.random()*33)+1))*15;
        comida=new Point(rdmx,rdmy);
    }
//    public static void main(String[] args) throws Exception{
//        Tablero juego= new Tablero();
//    }
    static ArrayList puntos=new ArrayList(50);
    public static long registro=0,frecuencia=50;
    public static Point punto, comida;
    static int widht = 495,height=555,rdmx,rdmy;
    public static LaVivorita panelVivorita;
    private javax.swing.JPanel fondo;
    private JLabel cerrar, titulo;
    private Font miletra=new Font("Charlemagne Std",Font.ITALIC,25);
    ActionListener accionCerrar=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
        class Ciclo extends Thread{
            public void run(){
                while(true){
                    if(java.lang.System.currentTimeMillis()-registro > frecuencia){
                        if(Teclado.anterior==KeyEvent.VK_UP){
                            Tablero.punto.y=Tablero.punto.y - LaVivorita.puntoy;
                            if(Tablero.punto.y<0) Tablero.punto.y=LaVivorita.tamy-LaVivorita.puntoy;
                            if(Tablero.punto.y>=LaVivorita.tamy) Tablero.punto.y=0;
                        }else if(Teclado.anterior==KeyEvent.VK_DOWN){
                            Tablero.punto.y=Tablero.punto.y + LaVivorita.puntoy;
                            if(Tablero.punto.y<0) Tablero.punto.y=LaVivorita.tamy-LaVivorita.puntoy;
                            if(Tablero.punto.y>=LaVivorita.tamy) Tablero.punto.y=0;
                        }else if(Teclado.anterior==KeyEvent.VK_LEFT){
                            Tablero.punto.x=Tablero.punto.x - LaVivorita.puntox;
                            if(Tablero.punto.x<0) Tablero.punto.x=LaVivorita.tamx-LaVivorita.puntox;
                            if(Tablero.punto.x>=Tablero.widht) Tablero.punto.x=0;
                        }else if(Teclado.anterior==KeyEvent.VK_RIGHT){
                            Tablero.punto.x=Tablero.punto.x + LaVivorita.puntox;
                            if(Tablero.punto.x<0) Tablero.punto.x=LaVivorita.tamx-LaVivorita.puntox;
                            if(Tablero.punto.x>=Tablero.widht) Tablero.punto.x=0;
                        }
                        //---------------------------------------------------------------------------Aquí falta
                        if(Tablero.punto.x==Tablero.comida.x && Tablero.punto.y==Tablero.comida.y){
                            ponercomida();


                        }

                        Tablero.panelVivorita.repaint();

                        registro=java.lang.System.currentTimeMillis();
                    }
                }
            }
        }
    
}
class LaVivorita extends javax.swing.JPanel {
    public void paintComponent(Graphics cuadrito){
        super.paintComponent(cuadrito);
        cuadrito.setColor(new Color(53,35,165));
        cuadrito.fillRect(Tablero.punto.x, Tablero.punto.y, puntox, puntoy);
        setSize(tamx,tamy);
        cuadrito.setColor(Color.BLUE);
        cuadrito.fillRect(Tablero.comida.x, Tablero.comida.y, puntox, puntoy);
        
    }
  static int  puntox=15, puntoy=15;
  static int tamx=495, tamy=495;
}


class Teclado extends java.awt.event.KeyAdapter{
    public void keyPressed (KeyEvent tecla){
        presionada=tecla.getKeyCode();
        if(presionada== KeyEvent.VK_ESCAPE){
            System.exit(0);
        }else if(presionada==KeyEvent.VK_UP){
            if (anterior!=KeyEvent.VK_DOWN) anterior=KeyEvent.VK_UP; 
        }else if(tecla.getKeyCode()==KeyEvent.VK_DOWN){
            if (anterior!=KeyEvent.VK_UP) anterior=KeyEvent.VK_DOWN;            
        }else if(tecla.getKeyCode()==KeyEvent.VK_LEFT){
            if (anterior!=KeyEvent.VK_RIGHT) anterior=KeyEvent.VK_LEFT;            
        }else if(tecla.getKeyCode()==KeyEvent.VK_RIGHT){
            if (anterior!=KeyEvent.VK_LEFT)anterior=KeyEvent.VK_RIGHT;
        }else{
            
        }
       
    }
   static int presionada, anterior;   
}

