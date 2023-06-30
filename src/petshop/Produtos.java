/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author User
 */
public class Produtos extends Usuario {

    private JScrollPane jsPane;
    private List<JCheckBox> produtos;
    private List<JLabel> lblProdutos;
    private List<Float> carrinho;

    public Produtos() {
        configurarJanela();
        this.setTitle("PRODUTOS");
        configurarPanel();
    }

    @Override
    public void configurarPanel() {
        jpShop = new javax.swing.JPanel();
        this.jpShop.setPreferredSize(new Dimension(800, 800));
        this.jpShop.setBackground(Color.darkGray);
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 50));
        this.jpShop.setOpaque(false);

        jsPane = new JScrollPane(this.jpShop);
        this.jsPane.setOpaque(false);
        this.jsPane.getViewport().setOpaque(false);

        this.painel.setLayout(new FlowLayout());
        this.painel.add(this.jsPane);
        configurarDados();

        for (int i = 0; i < 12; i++) {
            this.jpShop.add(this.lblProdutos.get(i));

            this.jpShop.add(this.produtos.get(i));
            this.produtos.get(i).addActionListener(this);
        }

    }

    @Override
    protected void configurarDados() {
        ImageIcon ampicilina = new ImageIcon(getClass().getResource("/imagens/Ampicilina.jpg"));
        ImageIcon coleira = new ImageIcon(getClass().getResource("/imagens/Coleira Simples.jpg"));
        ImageIcon Dontral = new ImageIcon(getClass().getResource("/imagens/Dontral Vermifugo.jpg"));
        ImageIcon granplus = new ImageIcon(getClass().getResource("/imagens/Granplus.jpg"));
        ImageIcon prediderm = new ImageIcon(getClass().getResource("/imagens/Prediderm.jpg"));
        ImageIcon extrusada = new ImageIcon(getClass().getResource("/imagens/Ração Extrusada.jpg"));
        ImageIcon cheval = new ImageIcon(getClass().getResource("/imagens/cheval.jpg"));
        ImageIcon max = new ImageIcon(getClass().getResource("/imagens/max.jpg"));
        ImageIcon queranon = new ImageIcon(getClass().getResource("/imagens/queranon.jpg"));
        ImageIcon sacaMilho = new ImageIcon(getClass().getResource("/imagens/sacaMilho.jpg"));
        ImageIcon whiskas = new ImageIcon(getClass().getResource("/imagens/whiskas.jpg"));
        ImageIcon nutrilus = new ImageIcon(getClass().getResource("/imagens/nutrilus.jpg"));

        lblProdutos = new ArrayList<>();
        produtos = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            JLabel lblP = new JLabel();
            JCheckBox check = new JCheckBox();
            check.setPreferredSize(new Dimension(147, 20));
            check.setForeground(new Color(75, 0, 30));
            check.setOpaque(false);
            this.produtos.add(check);
            this.lblProdutos.add(lblP);

        }

        this.produtos.get(0).setText("Ração Nutrilus");
        this.produtos.get(1).setText("Whiskas");
        this.produtos.get(2).setText("Cheval");
        this.produtos.get(3).setText("Saca de Milho 25kg");
        this.produtos.get(4).setText("Shampoo MAX 500ml");
        this.produtos.get(5).setText("Queranon");
        this.produtos.get(6).setText("Ampicilina");
        this.produtos.get(7).setText("Coleira Simples");
        this.produtos.get(8).setText("Vermifugo");
        this.produtos.get(9).setText("Prediderm");
        this.produtos.get(10).setText("Gran Plus");
        this.produtos.get(11).setText("Ração Extrusada");

        this.lblProdutos.get(0).setIcon(nutrilus);
        this.lblProdutos.get(1).setIcon(whiskas);
        this.lblProdutos.get(2).setIcon(cheval);
        this.lblProdutos.get(3).setIcon(sacaMilho);
        this.lblProdutos.get(4).setIcon(max);
        this.lblProdutos.get(5).setIcon(queranon);
        this.lblProdutos.get(6).setIcon(ampicilina);
        this.lblProdutos.get(7).setIcon(coleira);
        this.lblProdutos.get(8).setIcon(Dontral);
        this.lblProdutos.get(9).setIcon(prediderm);
        this.lblProdutos.get(10).setIcon(granplus);
        this.lblProdutos.get(11).setIcon(extrusada);

    }

    @Override
    public void actionPerformed(ActionEvent check) {
        carrinho = new ArrayList<>();
        if (this.produtos.get(0).isSelected()) {
            this.carrinho.add(20.32f);
            System.out.println(this.carrinho.get(0));

        } else if(!this.produtos.get(0).isSelected()){
                        System.out.println(this.carrinho.get(0));

        }
    }

}
