/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.BorderLayout;
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

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private List<Float> compras;
    private List<JLabel> compras1 = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;

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
        super.configurarPanel();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.jpShop.setLayout(new BorderLayout());
        //this.jpShop.setOpaque(true);
        this.painel.removeAll();
        this.jpShop.removeAll();
        
        this.jsPane = new JScrollPane(this.jpShop);
        this.jsPane.setOpaque(false);
        this.jsPane.getViewport().setOpaque(false);
        this.jsPane.setPreferredSize(new Dimension(800, 200));
        this.painel.add(jsPane);
        
        for (JLabel adiciona : this.compras1) {
            this.jpShop.add(adiciona);
            this.jpShop.add(this.spinner.get(k));
            k++;
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
                    this.compras1.add(this.lblProdutos.get(k));

                }
            }
        }
        for (JLabel adiciona : this.compras1) {
            SpinnerModel value = new SpinnerNumberModel(1, 1, 200, 1);
            JSpinner spinn = new JSpinner(value);
            spinn.setPreferredSize(new Dimension(30, 20));
            this.spinner.add(spinn);

        }

        if (this.compras1.isEmpty()) {
            System.out.println("false");
        }
    }

}
