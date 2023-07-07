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
import javax.swing.JSpinner;
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
    private Float total = 0f;
    private List<Float> compras;
    private List<JLabel> lblCompras = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;
    private JPanel jpValor;

    public Vendas(List<Float> carrinho, List<Float> prec) {
        this.compras = carrinho;
        this.precos = prec;

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
                    Float cont = new Float(0);
                    int i = 0;
                    for (JSpinner soma : spinner) {
                        cont = compras.get(i) * Float.parseFloat(soma.getValue().toString());
                        total += cont;
                        i++;
                    }
                    System.out.println(total);
                    total = 0f;
                }

            });
        }

    }

    protected void configurarDads(List<Float> carrinho, List<Float> prec) {
        super.configurarDados();
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
            spinn.setPreferredSize(new Dimension(30, 20));
            this.spinner.add(spinn);

        }

    }

}
