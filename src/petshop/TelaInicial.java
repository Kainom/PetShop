package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class TelaInicial extends JFrame {

    protected ImageIcon iconPet = new ImageIcon(getClass().getResource("/screnn/dog.jpg"));
    protected Fundo painel;
    protected JPanel jpConfirma, jpShop,jpVolta;
    private JLabel lblEstoque, lblVenda;
    private ImageIcon iconEstoque, iconVenda;
    private JButton btnEstoque, btnVenda;

    public TelaInicial() {
        configurarJanela();
        configurarPanel();
    }

    protected void configurarJanela() {
        painel = new TelaInicial.Fundo();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
        this.add(painel);
        this.setTitle("USUARIO");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(this);

    }

    protected void configurarPanel() {
        jpShop = new JPanel();
        jpConfirma = new JPanel();
        jpVolta = new JPanel();

        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 35));
        this.jpShop.setPreferredSize(new Dimension(490, 400));
        this.jpShop.setBackground(Color.darkGray);
        this.jpShop.setOpaque(false);

        this.painel.add(this.jpShop);

        configurarDados();
        this.jpShop.add(this.lblVenda);
        this.jpShop.add(this.btnVenda);
        this.jpShop.add(this.lblEstoque);
        this.jpShop.add(this.btnEstoque);
        
        this.btnEstoque.addActionListener(event -> escolha(event));
        this.btnVenda.addActionListener(event -> escolha(event));
        

    }

    private void configurarDados() {
        lblEstoque = new JLabel("ESTOQUE");
        lblVenda = new JLabel("VENDAS  ");
        iconEstoque = new ImageIcon(getClass().getResource("/screnn/estoque.png"));
        iconVenda = new ImageIcon(getClass().getResource("/screnn/sale.png"));
        btnEstoque = new JButton(iconEstoque);
        btnVenda = new JButton(iconVenda);

        this.lblEstoque.setFont(new Font("Arial Black", Font.BOLD, 14));
        this.lblEstoque.setForeground(Color.CYAN);

        this.lblVenda.setFont(new Font("Arial Black", Font.BOLD, 14));
        this.lblVenda.setForeground(Color.CYAN);

        this.btnEstoque.setPreferredSize(new Dimension(100, 90));
        this.btnEstoque.setBackground(Color.black);
        this.btnEstoque.setBorder(null);
        this.btnEstoque.setOpaque(false);
        this.btnEstoque.setFocusPainted(false);

        this.btnVenda.setPreferredSize(new Dimension(100, 90));
        this.btnVenda.setBackground(Color.black);
        this.btnVenda.setBorder(null);
        this.btnVenda.setOpaque(false);
        this.btnVenda.setFocusPainted(false);

    }
    
    private void escolha(ActionEvent event ){
        if(event.getSource().equals(this.btnEstoque)){
            new Estoque("Inserção");
            this.dispose();
        } else if(event.getSource().equals(this.btnVenda)){
            new Usuario().setVisible(true);
            this.dispose();
        }
    }

    protected class Fundo extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // extendo de painel para utilizar o comando super //
            Image img = TelaInicial.this.iconPet.getImage();
            g.drawImage(img, 0, 0, this);
        }

    }

}
