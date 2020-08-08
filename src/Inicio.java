
import Memorama.Memorama;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vivorita2.Tablero;


public class Inicio {

    public static void main(String[] args) {
        new Principal().setVisible(true);
    }
    
}

class Principal extends javax.swing.JFrame{
    
    public Principal(){
        setTitle("GAMES");
        setSize(widht,height);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        dimBtn=new Dimension(100,45);
        this.setLocation(dim.width/2-widht/2,dim.height/2-height/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image miIcono=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/vivorita2/images/gameIco.png"));
	setIconImage(miIcono);
        this.setLayout(new BorderLayout());
        
        
       
        
        
        btnVivorita=new JButton("VIVORITA");
        btnMemorama=new JButton("MEMORAMA");
        
         btnMemorama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 new Memorama().setVisible(true); 
                  ocultar();
            }
        });
         btnVivorita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 new Tablero().setVisible(true); 
                 ocultar();
                 }

           
        });
        
        
        
        //btnMemorama.setPreferredSize(dimBtn);
        

        titulo=new JLabel("¿Qué quieres jugar?");
        mifuente=new Font("Century Gothic",2,20);
        titulo.setFont(mifuente);
        
        titulo.setOpaque(true);
        titulo.setBackground(new Color(129, 135, 132));
        this.getContentPane().add(new PanelNorth(),BorderLayout.NORTH);
        this.getContentPane().add(new PanelSouth(),BorderLayout.CENTER);
        
    }
    
     public void ocultar(){
             this.dispose();
         }
    Font mifuente;
    Dimension dimBtn;
    JButton btnVivorita,btnMemorama;
    JLabel titulo;
    private int widht=300,height=130;
    
    class PanelNorth extends JPanel{
        public PanelNorth(){
            this.setBackground(new Color(154, 163, 158));
            this.add(titulo);
            this.setPreferredSize(dimBtn);
            
        }
    }
    class PanelSouth extends JPanel{
        public PanelSouth(){
            this.setBackground(new Color(129, 135, 132));
            this.setLayout(new FlowLayout(FlowLayout.CENTER,20,15));
            this.add(btnVivorita);
            this.add(btnMemorama);
        }
    }
    
}