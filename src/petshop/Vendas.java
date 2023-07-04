/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private List<Float> compras;
    private List<Float> precos;

    public Vendas(List<Float> carrinho) {
        this.compras = carrinho;
        this.precos = valores;
        super.configurarJanela();
        this.setTitle("VENDAS");
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

    

}
