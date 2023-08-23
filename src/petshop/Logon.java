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

    private static final int TIPO_ARMAZENA = 2;

    public Logon() {
        this.setTitle("LOGON");
        reutilizandoDados();
    }

    public void reutilizandoDados() {
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 25));
        this.jpShop.remove(this.bntNenhum);
        for (ActionListener bnt : this.bntConfirm.getActionListeners()) { // retirando o Action anterior do bnt 
            this.bntConfirm.removeActionListener(bnt);
            for (ActionListener volta : this.bntVolta.getActionListeners()) { // retirando o Action anterior do bnt 
                this.bntVolta.removeActionListener(volta);
            }
        }
        this.bntConfirm.addActionListener(evento -> action(evento));
        this.bntVolta.addActionListener(evento -> action(evento));
    }

    private void action(ActionEvent evento) {
        testarCampos();
        System.out.println((this.txtNome.getText() + " " + this.registro));
        VendasHistorico venda = new VendasHistorico(this.txtNome.getText(), this.registro);
        Integer teste = venda.teste(TIPO_ARMAZENA);
        System.out.println(teste + " oka");
        if (this.bntConfirm.equals(evento.getSource()) && teste == 1) {
            this.dispose();
            new Produtos(this.registro, this.txtNome.getText(), TIPO_ARMAZENA).setVisible(true);
        } else if(this.bntVolta.equals(evento.getSource())){
            this.dispose();
            new TelaInicial().setVisible(true);
        }

    }
}
