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

    private static final int TIPO_ARMAZENA = 2; // constante que sinaliza para a classe de armazenamento que estamos armazenando um usuário já cadastrado

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
        testarCampos(); // método da classe pai que testa e atribui o registro escolhido 
        VendasHistorico venda = new VendasHistorico(this.txtNome.getText(), this.registro); // instância da classe responsável por testar a existência do perfil
        Integer teste = venda.teste(TIPO_ARMAZENA); // método da classe que testa.A constante tipo_armazena sinaliza para o método que o teste será feito para o login
        if (this.bntConfirm.equals(evento.getSource()) && teste == 1) { // se o retorno do método for == 1 significa que o perfil existe //
            this.dispose();
            new Produtos(this.registro, this.txtNome.getText(), TIPO_ARMAZENA).setVisible(true);
        } else if(this.bntVolta.equals(evento.getSource())){ // simples bnt de volta 
            this.dispose();
            new Cliente().setVisible(true);
        }

    }
}
