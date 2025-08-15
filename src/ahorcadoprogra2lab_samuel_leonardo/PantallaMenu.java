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

    public PantallaMenu() {
        setTitle("AHORCADO GAME");
        setSize(600, 600);
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

        JButton btnAzar = new JButton("Ahorcado Azar");
        btnAzar.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnAzar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 20));
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

}