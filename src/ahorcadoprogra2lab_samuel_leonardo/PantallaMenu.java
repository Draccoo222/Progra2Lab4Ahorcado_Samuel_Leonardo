package ahorcadoprogra2lab_samuel_leonardo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hnleo
 */

public class PantallaMenu extends JFrame {
    
    AdminPalabrasSecretas adm = AdminPalabrasSecretas.getInstance();

    public PantallaMenu() {
        setTitle("AHORCADO GAME");
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("AHORCADO GAME", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 0, 20));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));
        JButton btnFijo = new JButton("Ahorcado Fijo");
        btnFijo.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnFijo);
        
        btnFijo.addActionListener(e->{
            this.dispose();
             new AhorcadoFijoPantalla().setVisible(true);
        });

        JButton btnAzar = new JButton("Ahorcado Azar");
        btnAzar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnAzar);
        btnAzar.addActionListener(e->{
            this.dispose();
            new AhorcadoAzarPantalla().setVisible(true);
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnSalir);

        JButton btnpal = new JButton("Cambiar Palabras");
        btnpal.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnpal);

        add(panelBotones, BorderLayout.CENTER);
        
        btnpal.addActionListener(e ->{
            adm.crearPalabras();
        });
        

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

}