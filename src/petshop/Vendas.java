/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private ImageIcon iconCarrinho;
    private String registro;
    private JLabel lblCarrinho, lblValor, lblRegistro;
    private Float total = 0f;
    private List<Float> compras;
    private List<JLabel> lblCompras = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;
    private JPanel jpCarrinho, jpDados;

    public Vendas(List<Float> carrinho, List<Float> prec, String registro) {
        super(registro);
        this.registro = registro;
        this.compras = carrinho;
        this.precos = prec;
        System.out.println(registro + "c");
        super.configurarJanela();
        this.setTitle("VENDAS");
        configurarDads(carrinho, prec);
        configurarPane();

    }

    protected void configurarPane() {
        int k = 0;
        jpCarrinho = new JPanel();
        jpDados = new JPanel();

        super.configurarPanel();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.painel.removeAll();

        this.jpShop.removeAll();
        this.jsPane.setPreferredSize(new Dimension(800, 100));

        this.jpCarrinho.setPreferredSize(new Dimension(800, 100));
        this.jpCarrinho.setBackground(Color.white);
        this.jpCarrinho.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.jpCarrinho.setOpaque(false);
        this.jpCarrinho.add(this.lblCarrinho);
        this.jpCarrinho.add(this.lblValor);

        this.jpDados.setPreferredSize(new Dimension(800, 262));
        this.jpDados.setBackground(Color.darkGray);
        this.jpDados.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.jpDados.add(this.lblRegistro);
        //this.jpDados.setOpaque(false);

        this.painel.add(jsPane);
        this.painel.add(this.jpCarrinho);
        this.painel.add(this.jpDados);

        for (JLabel adiciona : this.lblCompras) {
            this.jpShop.add(adiciona);
            this.jpShop.add(this.spinner.get(k));
            k++;
        }

        for (JSpinner spin : this.spinner) {
            spin.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent carrinho) {
                    Float cont = new Float(0);
                    int i = 0;
                    for (JSpinner soma : spinner) {
                        cont = compras.get(i) * Float.parseFloat(soma.getValue().toString());
                        total += cont;
                        i++;
                    }
                    System.out.println(total);
                    lblValor.setText(total.toString());
                    total = 0f;
                }

            });
        }

    }

    protected void configurarDads(List<Float> carrinho, List<Float> prec) {
        super.configurarDados();
        iconCarrinho = new ImageIcon(getClass().getResource("/imagens/carrinho.png"));
        lblCarrinho = new JLabel(iconCarrinho);
        lblValor = new JLabel();
        lblRegistro = new JLabel();

        this.lblValor.setForeground(new Color(234, 61, 39));
        this.lblValor.setFont(new Font("Arial Black", Font.BOLD, 16));

        this.lblRegistro.setForeground(new Color(234, 61, 39));
        this.lblRegistro.setFont(new Font("Arial Black", Font.BOLD, 16));
        
        if(regist.length() == 14 && !regist.endsWith(" ")){this.lblRegistro.setText("CPF: " + regist);}
        else if(regist.length() > 14){this.lblRegistro.setText("CNPJ: " + regist);}
        else {this.lblRegistro.setVisible(false);}
        
        for (Float compara : carrinho) {
            for (int k = 0; k < 12; k++) {
                if (compara.equals(prec.get(k))) {

                    this.lblCompras.add(this.lblProdutos.get(k));

                }
            }
        }
        for (JLabel adiciona : this.lblCompras) {
            SpinnerModel value = new SpinnerNumberModel(1, 1, 200, 1);
            JSpinner spinn = new JSpinner(value);
            spinn.setPreferredSize(new Dimension(50, 20));
            this.spinner.add(spinn);

        }

    }

}
