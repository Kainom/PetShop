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

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private List<Float> compras;
    private List<JLabel> compras1 = new ArrayList<>();

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
        configurarPanel();

    }

    @Override
    protected void configurarPanel() {
        super.configurarPanel();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.jpShop.setPreferredSize(new Dimension(600, 800));
        this.jpShop.setOpaque(true);
        this.painel.removeAll();
        this.jpShop.removeAll();
        this.painel.add(this.jpShop);
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
        if (this.compras1.isEmpty()) {
            System.out.println("false");
        }
    }

}
