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
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class Insere extends JFrame {

    private Fundo painel;
    private JTextField txtProdutos = new JTextField();
    private ImageIcon iconPet;
    private JPanel jpAdiciona, jpConfirma;
    private List<Integer> produtos = new ArrayList<>();
    private Integer qtdampicilina, qtdcoleira, qtddontral, qtdgranplus, qtdprediderm, //
            qtdracaoExtrusada, qtdcheval, qtdmax, qtdnutrilus, qtdqueranon, qtdsacaMilho, qtdwhiskas;

    public Insere() {
        configurarJanela();
        configurarPanel();

    }

    public  List<Integer> getProdutos() {
        return produtos;
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

        this.jpAdiciona.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 25));
        this.jpAdiciona.setPreferredSize(new Dimension(490, 400));
        this.jpAdiciona.setBackground(Color.darkGray);
        this.txtProdutos.setPreferredSize(new Dimension(300,30));
       // this.jpAdiciona.setOpaque(false);
       
//        this.jpConfirma.setPreferredSize(new Dimension(440, 300)); 
//        this.jpConfirma.setBackground(Color.red);
//        this.jpConfirma.setOpaque(false);

        this.painel.add(this.jpAdiciona);
        this.jpAdiciona.add(this.txtProdutos);
//        this.painel.add(this.jpConfirma);

    }

    private void produtos() {
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

    
    protected class Fundo extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // extendo de painel para utilizar o comando super // 
            Image img = Insere.this.iconPet.getImage();
            g.drawImage(img, 0, 0, this);
        }

    }
    

}