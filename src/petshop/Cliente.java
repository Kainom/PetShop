/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class Cliente extends TelaInicial {

    private ImageIcon iconLogin, iconCadastro;
    private JLabel lblLogin, lblCadastro;
    private JButton bntLogin, bntCadastro;

    public Cliente() {
        this.setTitle("Cliente");
        reutilizandoDados();
        
    }
    
    private void reutilizandoDados(){
        iconLogin = new ImageIcon(getClass().getResource("/screnn/Login.png"));
        iconCadastro = new ImageIcon(getClass().getResource("/screnn/Cadastro.png"));
        lblLogin = lblVenda;
        lblCadastro = lblEstoque;
        for (ActionListener list : btnEstoque.getActionListeners()) {
            btnEstoque.removeActionListener(list);
            for (ActionListener lis : btnVenda.getActionListeners()) {
                btnVenda.removeActionListener(lis);

            }
        }
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
        this.jpShop.remove(this.lblRelation);
        this.jpShop.remove(this.bntRelation);
        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 36));

        this.lblCadastro.setText("CADASTRO");
        this.lblLogin.setText(" LOGIN       ");

        this.bntCadastro = this.btnEstoque;
        this.bntLogin = this.btnVenda;
        this.bntLogin.setIcon(iconLogin);
        this.bntCadastro.setIcon(iconCadastro);

        this.bntCadastro.addActionListener(action -> evento(action));
        this.bntLogin.addActionListener(action -> evento(action));
    }

    private void evento(ActionEvent action) {
        if (this.bntCadastro.equals(action.getSource())) {
            this.dispose();
            new Usuario().setVisible(true);
        } else if (this.bntLogin.equals(action.getSource())) {
            this.dispose();
            new Logon().setVisible(true);
        }

    }
}
