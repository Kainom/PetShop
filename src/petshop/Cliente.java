/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
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

    private ImageIcon iconLogin, iconCadastro, iconVolta;
    private JLabel lblLogin, lblCadastro;
    private JButton bntLogin, bntCadastro, bntVolta;

    public Cliente() {
        this.setTitle("Cliente");
        reutilizandoDados();

    }

    private void reutilizandoDados() { // reutiliza componenetes semelhantes ja definidos na classe pai,acrescentando apenas algumas modoficações
        iconLogin = new ImageIcon(getClass().getResource("/screnn/Login.png"));
        iconCadastro = new ImageIcon(getClass().getResource("/screnn/Cadastro.png"));
        iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));
        lblLogin = lblVenda; // concede referencia apenas para tornar legível o código 
        lblCadastro = lblEstoque;
        bntVolta = new JButton(iconVolta);
        
        for (ActionListener list : btnEstoque.getActionListeners()) { // remove qualquer ActionListener referente a estes botôes afim de conceder novas ações
            btnEstoque.removeActionListener(list);
            for (ActionListener lis : btnVenda.getActionListeners()) {
                btnVenda.removeActionListener(lis);

            }
        }
        this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 200));
        this.jpVolta.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 200));
        this.jpVolta.setPreferredSize(new Dimension(250, 500));
        this.jpVolta.setBackground(Color.darkGray);
        this.jpVolta.setOpaque(false);
        this.painel.remove(this.jpShop);
        this.painel.add(this.jpVolta);
        this.painel.add(this.jpShop);

        this.bntVolta.setPreferredSize(new Dimension(60, 60));
        this.bntVolta.setBackground(Color.red);
        this.bntVolta.setOpaque(false);
        this.bntVolta.setBorder(null);
        this.bntVolta.setFocusPainted(false);
        this.jpVolta.add(this.bntVolta);

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
        this.bntVolta.addActionListener(action -> evento(action));
    }

    private void evento(ActionEvent action) {
        if (this.bntCadastro.equals(action.getSource())) {
            this.dispose();
            new Usuario().setVisible(true);
        } else if (this.bntLogin.equals(action.getSource())) {
            this.dispose();
            new Logon().setVisible(true);
        } else if(this.bntVolta.equals(action.getSource())){
            this.dispose();
            new TelaInicial().setVisible(true);
        }

    }
}
