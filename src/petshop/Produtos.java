/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author User
 */
public class Produtos extends Usuario {

    public static final int QUANTIDADE_PRODUTOS = Estoque.getProdutos().size(); // constante referente a quantidade de produtos 
    protected String registro;
    protected String nome;
    protected JScrollPane jsPane;
    private List<JCheckBox> checkProdutos;
    protected List<JLabel> lblProdutos;
    private List<Float> carrinho = new ArrayList<>();
    protected List<Float> valores = new ArrayList<>();
    protected float ampicilina, coleira, dontral, granplus, prediderm, //
            racaoExtrusada, cheval, max, nutrilus, queranon, sacaMilho, whiskas;

    {                

        valores.add(nutrilus = 100.55f);
        valores.add(whiskas = 30.54f);
        valores.add(cheval = 50.3f);
        valores.add(sacaMilho = 120.88f);
        valores.add(max = 10.32f);
        valores.add(queranon = 95.4f);
        valores.add(ampicilina = 32.54f);
        valores.add(coleira = 20.32f);
        valores.add(dontral = 60.12f);
        valores.add(prediderm = 15.3f);
        valores.add(granplus = 40.3f);
        valores.add(racaoExtrusada = 56.65f);

    }

    public Produtos(String registro, String nome) {

        this.registro = registro;
        this.nome = nome;
        super.configurarJanela();
        this.setTitle("PRODUTOS");
        configurarPanel();

    }

    
    @Override
    protected void configurarPanel() {
        jpShop = new javax.swing.JPanel();
        jpConfirma = new javax.swing.JPanel();

        this.jpShop.setBackground(Color.darkGray);
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.jpShop.setOpaque(false);

        this.jpConfirma.setPreferredSize(new Dimension(50, 97));
        this.jpConfirma.setBackground(Color.darkGray);
        this.jpConfirma.setLayout(new BorderLayout());
        this.jpConfirma.setOpaque(false);

        jsPane = new JScrollPane();
        jsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.jsPane.setPreferredSize(new Dimension(800, 100));

        this.jsPane.setOpaque(false);
        this.jsPane.getViewport().setOpaque(false);
        this.jsPane.setViewportView(this.jpShop);

        this.painel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        this.painel.add(this.jsPane);
        this.painel.add(this.jpConfirma);

        super.configurarDados(); // chama da classe pai com alguns elementos ja prontos para reutilização 
        configurarDados();

        for (int i = 0; i < QUANTIDADE_PRODUTOS; i++) {
            if (Estoque.getProdutos().get(i) > 0) { // se o produto for menor que 0 ele se encontra indisponivel //
                this.jpShop.add(this.lblProdutos.get(i));
                this.jpShop.add(this.checkProdutos.get(i));
                this.checkProdutos.get(i).addActionListener(this);
            }
        }
        this.jpConfirma.add(this.bntConfirm, BorderLayout.SOUTH); // bnt confirm reutilizado da classe Pai
        this.bntConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent confirma) {
                if (!(Produtos.this.carrinho.isEmpty())) {
                    Produtos.this.dispose();
                    new Vendas(Produtos.this.carrinho, Produtos.this.valores, registro, nome).setVisible(true);
                }
            }
        });

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
        checkProdutos = new ArrayList<>();
        

        for (int i = 0; i < QUANTIDADE_PRODUTOS; i++) {
            JLabel lblP = new JLabel();
            JCheckBox check = new JCheckBox();
            check.setPreferredSize(new Dimension(147, 20));
            check.setForeground(new Color(75, 0, 30));
            check.setOpaque(false);
            this.checkProdutos.add(check);
            this.lblProdutos.add(lblP);

        }

        this.checkProdutos.get(0).setText("Ração Nutrilus");
        this.checkProdutos.get(1).setText("Whiskas");
        this.checkProdutos.get(2).setText("Cheval");
        this.checkProdutos.get(3).setText("Saca de Milho 25kg");
        this.checkProdutos.get(4).setText("Shampoo MAX 500ml");
        this.checkProdutos.get(5).setText("Queranon");
        this.checkProdutos.get(6).setText("Ampicilina");
        this.checkProdutos.get(7).setText("Coleira Simples");
        this.checkProdutos.get(8).setText("Vermifugo");
        this.checkProdutos.get(9).setText("Prediderm");
        this.checkProdutos.get(10).setText("Gran Plus");
        this.checkProdutos.get(11).setText("Ração Extrusada");

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
        for (int i = 0; i < QUANTIDADE_PRODUTOS; i++) {
            if (this.checkProdutos.get(i).isSelected() && check.getSource() == this.checkProdutos.get(i)) {

                this.carrinho.add(this.valores.get(i));
                break;

            } else if (!(this.checkProdutos.get(i).isSelected()) && check.getSource() == this.checkProdutos.get(i)) {
                this.carrinho.remove(this.valores.get(i));
                if (this.carrinho.isEmpty()) {
                    System.out.println("vazio");
                    break;
                }
            }
        }

    }

}
