/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
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

    private float valores;
    private Float teste;
    private List<Float> compras;
    private List<JLabel> lblCompras = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;
    private JPanel jpValor;

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
        System.out.println("");
        System.out.println("");
        for (JSpinner spin : this.spinner) {
            this.teste = Float.parseFloat(spin.getValue().toString());
            System.out.println(teste);
            spin.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent carrinho) {
                    if (Float.parseFloat(spin.getValue().toString()) < teste) {
                        valores -= 20f;

                    } else if (Float.parseFloat(spin.getValue().toString()) >= teste) {
                        valores += 20f;
                        System.out.println("entrei");
                    }
                    teste = Float.parseFloat(spin.getValue().toString());
                    System.out.print(teste + " ");

                    System.out.println(valores);
                }

            });

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
