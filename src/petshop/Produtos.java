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
import java.util.List;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    private int tipoArmazena;
    private javax.swing.JPanel jpAlerta, jpEsquerda, jpDireita;
    private ImageIcon setaEsquerda, setaDireita, perigo;
    private JLabel lblPerigo, lblEsquerda, lblDireita;
    protected String registro;
    protected String nome;
    protected JScrollPane jsPane, jsAlerta;
    private List<JCheckBox> checkProdutos;
    protected List<JLabel> lblProdutos, lblCopia;
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

    public Produtos() {
        
    }

    public Produtos(String registro, String nome, int tipoArmazena) {
        this.tipoArmazena = tipoArmazena;
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
        jpVolta = new javax.swing.JPanel();
        jpAlerta = new javax.swing.JPanel();
        jpEsquerda = new javax.swing.JPanel();
        jpDireita = new javax.swing.JPanel();
        int teste = 0;

        this.jpShop.setBackground(Color.darkGray);
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.jpShop.setOpaque(false);

        this.jpConfirma.setPreferredSize(new Dimension(50, 97));
        this.jpConfirma.setBackground(Color.darkGray);
        this.jpConfirma.setLayout(new BorderLayout());
        this.jpConfirma.setOpaque(false);

        this.jpVolta.setPreferredSize(new Dimension(70, 100));
        this.jpVolta.setBackground(Color.darkGray);
        this.jpVolta.setLayout(new BorderLayout());
        this.jpVolta.setOpaque(false);

        this.jpAlerta.setBackground(Color.darkGray);
        this.jpAlerta.setLayout(new BoxLayout(this.jpAlerta, BoxLayout.Y_AXIS));
        this.jpAlerta.setOpaque(false);

        jsPane = new JScrollPane();
        this.jsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.jsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.jsPane.setPreferredSize(new Dimension(800, 145));

        this.jsPane.setOpaque(false);
        this.jsPane.getViewport().setOpaque(false);
        this.jsPane.setViewportView(this.jpShop);
        this.jsPane.setBorder(null);

        jsAlerta = new JScrollPane();
        this.jsAlerta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.jsAlerta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.jsAlerta.setPreferredSize(new Dimension(400, 300));

        this.jsAlerta.setOpaque(false);
        this.jsAlerta.getViewport().setOpaque(false);
        this.jsAlerta.setViewportView(this.jpAlerta);
        this.jsAlerta.setBorder(null);

        this.jpEsquerda.setPreferredSize(new Dimension(100, 200));
        this.jpEsquerda.setBackground(Color.darkGray);
        this.jpEsquerda.setLayout(new BorderLayout());
        this.jpEsquerda.setOpaque(false);

        this.jpDireita.setPreferredSize(new Dimension(100, 200));
        this.jpDireita.setBackground(Color.darkGray);
        this.jpDireita.setLayout(new BorderLayout());
        this.jpDireita.setOpaque(false);

        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        this.painel.add(this.jpVolta);
        this.painel.add(this.jsPane);
        this.painel.add(this.jpConfirma);

        configurarDados();

        this.jpAlerta.add(this.lblPerigo);

        for (int i = 0; i < QUANTIDADE_PRODUTOS; i++) {
            if (Estoque.getProdutos().get(i) > 0) { // se o produto for menor que 0 ele se encontra indisponivel //
                this.jpShop.add(this.lblProdutos.get(i));
                this.jpShop.add(this.checkProdutos.get(i));
                this.checkProdutos.get(i).addActionListener(this);

            }
            if (Estoque.getProdutos().get(i) <= 10) {
                this.jpAlerta.add(Box.createRigidArea(new Dimension(130, 10)));
                this.jpAlerta.add(this.lblCopia.get(i));
                teste++;
            }

        }
        if (teste > 0) {
            this.painel.add(this.jpEsquerda);
            this.painel.add(this.jsAlerta);
            this.painel.add(this.jpDireita);
            this.jpDireita.add(this.lblDireita);
            this.jpEsquerda.add(this.lblEsquerda);
        }
        super.configurarDados(); //reutilizando os bnts volta e confirma         
        this.jpVolta.add(this.bntVolta, BorderLayout.SOUTH);
        this.jpConfirma.add(this.bntConfirm, BorderLayout.SOUTH); // bnt confirm reutilizado da classe Pai
        this.bntConfirm.addActionListener(evento -> action(evento));
        this.bntVolta.addActionListener(evento -> action(evento));

    }

    @Override
    protected void configurarDados() {
        int k = 0;
        List<ImageIcon> produtosImage = new ArrayList<>();
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/nutrilus.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/whiskas.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/cheval.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/sacaMilho.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/max.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/queranon.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Ampicilina.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Coleira Simples.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Dontral Vermifugo.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Prediderm.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Granplus.png")));
        produtosImage.add(new ImageIcon(getClass().getResource("/imagens/Ração Extrusada.png")));

        setaDireita = new ImageIcon(getClass().getResource("/screnn/setaDireita.png"));
        setaEsquerda = new ImageIcon(getClass().getResource("/screnn/setaEsquerda.png"));
        perigo = new ImageIcon(getClass().getResource("/screnn/perigo.png"));

        lblPerigo = new JLabel(perigo);
        lblEsquerda = new JLabel(setaEsquerda);
        lblDireita = new JLabel(setaDireita);
        lblProdutos = new ArrayList<>();
        lblCopia = new ArrayList<>();
        checkProdutos = new ArrayList<>();

        for (int i = 0; i < QUANTIDADE_PRODUTOS; i++) {
            JLabel lblP = new JLabel();
            JLabel lblP2 = new JLabel();

            JCheckBox check = new JCheckBox();
            check.setPreferredSize(new Dimension(147, 20));
            check.setForeground(new Color(75, 0, 30));
            check.setOpaque(false);
            this.checkProdutos.add(check);
            this.lblProdutos.add(lblP);
            this.lblCopia.add(lblP2);

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

        for (ImageIcon icon : produtosImage) {
            lblProdutos.get(k).setIcon(icon);
            lblCopia.get(k).setIcon(icon);
            k++;
        }

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

    private void action(ActionEvent evento) {
        if (!(Produtos.this.carrinho.isEmpty()) && evento.getSource().equals(this.bntConfirm)) {
            Produtos.this.dispose();
            new Vendas(Produtos.this.carrinho, Produtos.this.valores, registro, nome, tipoArmazena).setVisible(true);
        } else if (evento.getSource().equals(this.bntVolta)) {
            new Usuario().setVisible(true);
            this.dispose();
        }
    }

}
