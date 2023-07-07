/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private List<Float> valores = new ArrayList<>();
    private Float teste = 0f;
    private List<Float> compras;
    private List<JLabel> lblCompras = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;
    private JPanel jpValor;
         int l = 0;

    public Vendas(List<Float> carrinho, List<Float> prec) {
        this.compras = carrinho;
        this.precos = prec;
        for (Float des : precos) {
            System.out.println(des);
        }
        super.configurarJanela();
        this.setTitle("VENDAS");
        configurarDads(carrinho, prec);
        configurarPane();

    }

    protected void configurarPane() {
        int k = 0;
        jpValor = new JPanel();

        super.configurarPanel();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.painel.removeAll();
        this.jpShop.removeAll();

        this.jsPane.setPreferredSize(new Dimension(800, 100));
        this.painel.add(jsPane);

        for (JLabel adiciona : this.lblCompras) {
            this.jpShop.add(adiciona);
            this.jpShop.add(this.spinner.get(k));
            k++;
        }
        
        for (JSpinner spin : this.spinner) {
            spin.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent carrinho) {
                    //Set<Float> set = new HashSet();
                    Float cont = new Float(0);
                    cont = 20 * Float.parseFloat(spin.getValue().toString());
                    //set.add(teste);
                    valores.add(cont);
                    System.out.println(valores);
                    Float resultado = new Float(0);
                    for (Float soma : valores) {
                        teste += soma;
                    }
                      valores.removeAll(valores);

                    System.out.println(teste);
                    l++;
                }

            });
            ;
        }

    }

    protected void configurarDads(List<Float> carrinho, List<Float> prec) {
        super.configurarDados();
        for (Float compara : carrinho) {
            for (int k = 0; k < 12; k++) {
                if (compara.equals(prec.get(k))) {
                    if (this.lblProdutos.isEmpty()) {
                        System.out.println("fde");
                    }
                    this.lblCompras.add(this.lblProdutos.get(k));

                }
            }
        }
        for (JLabel adiciona : this.lblCompras) {
            SpinnerModel value = new SpinnerNumberModel(1, 1, 200, 1);
            JSpinner spinn = new JSpinner(value);
            spinn.setPreferredSize(new Dimension(30, 20));
            this.spinner.add(spinn);

        }

        if (this.lblCompras.isEmpty()) {
            System.out.println("false");
        }
    }

}
