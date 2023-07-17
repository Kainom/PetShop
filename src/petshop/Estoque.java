/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author User
 */
public class Estoque extends JFrame {

    private static List<Integer> produtos = new ArrayList<>();
    private static Integer qtdampicilina, qtdcoleira, qtddontral, qtdgranplus, qtdprediderm,
            qtdracaoExtrusada, qtdcheval, qtdmax, qtdnutrilus, qtdqueranon, qtdsacaMilho, qtdwhiskas;

    public Estoque() {
        new InsereProdutos();
    }

    public static List<Integer> getProdutos() {
        return produtos;
    }

    public static void produtos() {
        produtos.add(qtdnutrilus = 2);
        produtos.add(qtdwhiskas = 0);
        produtos.add(qtdcheval = 32);
        produtos.add(qtdsacaMilho = 0);
        produtos.add(qtdmax = 10);
        produtos.add(qtdqueranon = 95);
        produtos.add(qtdampicilina = 32);
        produtos.add(qtdcoleira = 20);
        produtos.add(qtddontral = 60);
        produtos.add(qtdprediderm = 15);
        produtos.add(qtdgranplus = 0);
        produtos.add(qtdracaoExtrusada = 56);
    }

    private static class InsereProdutos<T> extends JFrame implements ActionListener {

        private Fundo painel;
        private JSpinner spinner;
        private JComboBox<String> jcTipo;
        private ImageIcon iconPet;
        private JPanel jpAdiciona, jpConfirma;

        public InsereProdutos() {
            configurarJanela();
            configurarPanel();
            this.setVisible(true);

        }

        private void configurarJanela() {
            iconPet = new ImageIcon(getClass().getResource("/imagens/dog.jpg"));
            painel = new Fundo();
            painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
            this.add(painel);
            this.setTitle("ESTOQUE");
            this.setSize(1000, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(this);

        }

        private void configurarPanel() {
            jpAdiciona = new JPanel();
            jpConfirma = new JPanel();

            this.jpAdiciona.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 40));
            this.jpAdiciona.setPreferredSize(new Dimension(490, 400));
            this.jpAdiciona.setBackground(Color.darkGray);

            this.jpAdiciona.setOpaque(false);
            this.jpConfirma.setPreferredSize(new Dimension(440, 300));
            //this.jpConfirma.setOpaque(false);

            this.painel.add(this.jpAdiciona);
            configurarDados();

            this.jpAdiciona.add(this.spinner);
            this.jpAdiciona.add(this.jcTipo);

        }

        private void configurarDados() {
            jcTipo = new JComboBox<>();
            SpinnerModel value = new SpinnerNumberModel(1, 1, 100, 1);
            spinner = new JSpinner(value);

            spinner.setPreferredSize(new Dimension(120, 20));
            jcTipo.addItem("Nutrilus" + " " + 0);
            jcTipo.addItem("Whiskas" + " " + 1);
            jcTipo.addItem("Cheval" + " " + 2);
            jcTipo.addItem("Milho" + " " + 3);
            jcTipo.addItem("Max" + " " + 4);
            jcTipo.addItem("Queranon" + " " + 5);
            jcTipo.addItem("Ampicilina" + " " + 6);
            jcTipo.addItem("Coleira" + " " + 7);
            jcTipo.addItem("Dontral" + " " + 8);
            jcTipo.addItem("Prediderm" + " " + 9);
            jcTipo.addItem("Granplus" + " " + 10);
            jcTipo.addItem("Ração Extrusada" + " " + 11);
           

        }

        public void inserir() {
            
            int t = jcTipo.getSelectedIndex(); // com esse indice sabemos o produtos e o escrevemos no arquivo

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        protected class Fundo extends javax.swing.JPanel {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // extendo de painel para utilizar o comando super // 
                Image img = iconPet.getImage();
                g.drawImage(img, 0, 0, this);
            }

        }
    }

}
