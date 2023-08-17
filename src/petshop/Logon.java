/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author User
 */
public class Logon extends Usuario {

    public Logon() {
        super();
        this.setTitle("LOGON");
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 25));
        this.jpShop.remove(this.bntNenhum);
 for(ActionListener bnt : this.bntConfirm.getActionListeners()){
            this.bntConfirm.removeActionListener(bnt);
      }
        this.bntConfirm.addActionListener(evento -> action(evento));
    }

    
    private void configurarDads() {
        super.configurarDados();
     
    }

    private int teste() {

        return 0;

    }

    private void action(ActionEvent evento) {
        testarCampos();
        System.out.println((this.txtNome.getText() + " " + this.registro));
        VendasHistorico venda = new VendasHistorico(this.txtNome.getText(),this.registro);
        Integer teste = venda.teste();
        System.out.println(teste + " oka");
            if (this.bntConfirm.equals(evento.getSource()) && teste==1) {
                this.dispose();
                new Produtos(this.registro,this.txtNome.getText()).setVisible(true);
            } else{
                System.out.println("NOT");
            }
        
    }
}
