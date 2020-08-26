package Frames;   

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
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
        try{
            Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/memoramaIco.png"));
            setIconImage(miIcono);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un errror al cargar la imagen del ícono. Error "+e);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  //Guarda el tamaño de la pantalla donde se ejecuta
        this.setLocation(dim.width/2-wFrame/2,dim.height/2-hFrame/2); //Establece la ubicación del Frame en el centro de la pantalla 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Fondo fondo=new Fondo();  //En este "fondo" se pegan los dos fondos de abajo
        fondo.add(new fondoArriba(),BorderLayout.NORTH);
        fondo.add(new fondoAbajo(),BorderLayout.CENTER);
        this.addKeyListener( new Teclado()); //Se pone a la escucha del teclado con las configuraciones de la clase "Teclado"
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
            try{
                titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoMemorama.png")));
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error al cargar el logo del panel Superior. Error: "+e);
            }
            this.add(titulo);
        }
    }
    
    class fondoAbajo extends JPanel{
        public fondoAbajo(){
            this.setLayout(new GridLayout(4,4));  //Se divide en panel en 4 columnas y 4 filas
            try{
                img1=new ImageIcon(getClass().getResource("/Images/c1.png"));
                img2=new ImageIcon(getClass().getResource("/Images/c2.png"));
                img3=new ImageIcon(getClass().getResource("/Images/c3.png"));
                img4=new ImageIcon(getClass().getResource("/Images/c4.png"));
                img5=new ImageIcon(getClass().getResource("/Images/c5.png"));
                img6=new ImageIcon(getClass().getResource("/Images/c6.png"));
                img7=new ImageIcon(getClass().getResource("/Images/c7.png"));
                img8=new ImageIcon(getClass().getResource("/Images/c8.png"));
                revolver();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "No se pudieron cargar correctamente todas las imagenes. Error "+e);
            }
        }
        
        public final void revolver(){ //revolver reparte aleatoriamente todas las cartas sobre la mesa
            for(int i=0;i<16;i++){
                int azar=(int)(Math.random()*8)+1;  //Según el número asignado aquí, se le agregará una imagen u otra, es decir, una carta u otra
                if(azar==1){
                    if(a<2){
                    this.add(new carta(img1,1,i));
                    a++;
                    }else{
                      azar+=1;
                      i--;
                    }
                }else if(azar==2){
                    if(b<2){
                    this.add(new carta(img2,2,i));
                    b++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==3){
                    if(c<2){
                    this.add(new carta(img3,3,i));
                    c++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==4){
                    if(d<2){
                    this.add(new carta(img4,4,i));
                    d++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==5){
                    if(e<2){
                    this.add(new carta(img5,5,i));
                    e++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==6){
                    if(f<2){
                    this.add(new carta(img6,6,i));
                    f++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==7){
                    if(g<2){
                        this.add(new carta(img7,7,i));
                        g++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }else if(azar==8){
                    if(h<2){
                    this.add(new carta(img8,8,i));
                    h++;
                    }else{
                       azar+=1;
                       i--;
                    }
                }
            }
        }
    }
    
    class carta extends JLabel {
        public carta(ImageIcon imagen, int Id, int Id2){
            this.Id=Id;
            this.Id2=Id2;
            imagenFront=imagen;
            this.setText("");
            this.back();
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseReleased(java.awt.event.MouseEvent evt) { //mouseReleased se ejecuta al soltar el "click" y  mousePressed cuando apenas se presiona                 
                    comparar();   
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    front();
                } 
            });  
        }
        
        public void esperar(int segundos){ //Este método pausa la ejecución durante los segundos asignados. Así podemos ver la segunda carta elegida antes de que se volteé
            try {
                Thread.sleep(segundos*500);
            }catch (Exception e) {
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
        
        public int getId2() {
            return Id2;
        }

        public void setId2(int Id2) {
            this.Id2 = Id2;
        }
        
        public void comparar(){  //Esta es el método propio de la carta que le permite compararse con otra ya volteada, para ver si es la misma.
            if(this.encontrada)return; //Esta linea impide que se seleccionen las mismas cartas ya seleccionadas anteriormente
            int  igual=0;
            for(int i=0;i<volteados.size();i++){
                if(volteados.get(i).toString().equals(this.toString())){
                    if(mismaCarta()) {
                        this.back();
                    }else{
                        igual=1;
                        this.encontrada=true;
                        volteados.get(0).encontrada=true;
                        volteados.remove(0);
                        intento--;
                        pares++;
                        victoria();
                    }
                }
            }
            if(igual==0){
                if(intento%2==0){ //Esta condición permite saber si es la primera o segunda elección de carta.
                    volteados.add(this);
                    intento++;
                }else{
                    esperar(1);
                    this.back();
                    carta cartaAnterior=volteados.get(0);
                    cartaAnterior.back();
                    volteados.remove(0);
                    intento++;
                }
                 
            }
        }
        
        public void victoria(){ //Este método revisa cuantos pares se han encontrado
            if(pares==8){
                int joder=JOptionPane.showConfirmDialog(null,"¡¡Ganaste!!", "Felicidades", -1, 1);
                if(joder==0){
                   System.exit(0);
               }
           }
       }
       
        public void front(){ //Se "voltea" la carta, o dicho mejor, se le asigna el ícono frontal
           this.setIcon(imagenFront);
        }
       
        public void back(){ //Se le asigna el ícono por defecto, osea el de la parte trasera de la carta.
            this.setIcon(imagen);
        }
        
         public boolean mismaCarta(){ //Este método impide que cuente como acierto el tocar varias veces la misma carta
            if (this.Id2==volteados.get(0).getId2()){
                return true;
            }else{
               return false;
            }
        }
        
        private int Id,Id2; //Identificadores: 1 que será igual por pareja y otro individual para cada carta
        boolean encontrada=false; //Por defecto ninguna carta ha sido encontrada
        private ImageIcon imagenFront, imagen=new ImageIcon(getClass().getResource("/Images/cardBAck.png")); //Cada carta tiene dos imágenes o íconos, el frontal y el trasero
        private fondoAbajo fondoCartas;
    }
    
    class Teclado extends KeyAdapter{ //Esta clase es la encargada del teclado y sus funciones. 
        public void keyPressed (KeyEvent tecla){
        presionada=tecla.getKeyCode();
        if(presionada== KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
        int presionada;
    }

    public static void main(String[] args){ 
        Frames.Inicio.main(args);
    } 
   
   private int hFrame=560,wFrame=500,a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,intento=0; //hFrame y wFrame son las dimensiones del frame
   private JLabel titulo;
   private ImageIcon img1,img2,img3,img4,img5,img6,img7,img8;
   private ArrayList<carta> volteados= new ArrayList<>(1); //Arreglo con cartas volteadas
   private int pares=0; //Pares de cartas encontrados
}