/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class Usuario extends JFrame implements ActionListener {

    ImageIcon iconPet;
    private String registro;
    protected ImageIcon iconDog;
    private JLabel lblNome, lblCnpj, lblCpf, lblEscolha;
    protected JPanel jpShop, jpConfirma;
    protected Fundo painel;
    protected JTextField txtNome;
    protected JFormattedTextField txtfCnpj, txtfCpf;
    private MaskFormatter cnpj, cpf;
    private JRadioButton bntCnpj, bntCpf, bntNenhum;
    private ButtonGroup grupo;
    protected JButton bntConfirm;

    public Usuario() {
        configurarJanela();
        configurarPanel();

    }

    protected void configurarJanela() {

        iconPet = new ImageIcon(getClass().getResource("/imagens/dog.jpg"));
        painel = new Fundo();
        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));

        this.add(painel);
        this.setTitle("USUARIO");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(this);

    }

    protected void configurarPanel() {
        jpShop = new JPanel();
        jpConfirma = new JPanel();

        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 25));
        this.jpShop.setPreferredSize(new Dimension(490, 400));
        this.jpShop.setBackground(Color.darkGray);
        this.jpShop.setOpaque(false);

        this.jpConfirma.setPreferredSize(new Dimension(440, 300));
        this.jpConfirma.setBackground(Color.red);
        this.jpConfirma.setOpaque(false);

        this.painel.add(this.jpShop);

        configurarDados();
        this.jpShop.add(this.lblNome);
        this.jpShop.add(this.txtNome);
        this.jpShop.add(this.lblEscolha);
        this.jpShop.add(this.bntCnpj);
        this.jpShop.add(this.bntCpf);
        this.jpShop.add(this.bntNenhum);

        this.jpShop.add(this.lblCnpj);
        this.jpShop.add(this.txtfCnpj);
        this.jpShop.add(this.lblCpf);
        this.jpShop.add(this.txtfCpf);
        this.jpShop.add(this.jpConfirma);

        this.jpConfirma.add(this.bntConfirm);

        this.txtfCnpj.setVisible(false);
        this.txtfCpf.setVisible(false);
        this.lblCnpj.setVisible(false);
        this.lblCpf.setVisible(false);
        this.bntConfirm.setVisible(false);
        this.jpConfirma.setVisible(false);

        this.bntCnpj.addActionListener(this);
        this.bntCpf.addActionListener(this);
        this.bntNenhum.addActionListener(this);

        this.bntConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer teste = new Integer(testarCampos());

                System.out.println(registro);
                if (teste == 0) {
                    Usuario.this.dispose();
                    new Produtos(registro, txtNome.getText()).setVisible(true);
                }
            }
        });
    }

    protected void configurarDados() {
        txtNome = new JTextField();
        lblNome = new JLabel();
        lblCnpj = new JLabel();
        lblCpf = new JLabel();
        lblEscolha = new JLabel();
        bntCnpj = new JRadioButton();
        bntCpf = new JRadioButton();
        bntNenhum = new JRadioButton();
        grupo = new ButtonGroup();
        iconDog = new ImageIcon(getClass().getResource("/imagens/shiba.jpg"));
        bntConfirm = new JButton(iconDog);

        try {
            cnpj = new MaskFormatter("##.###.###/####-##");  //04.128.563/0001-10
            cpf = new MaskFormatter("###.###.###-##");
        } catch (ParseException err) {
            System.out.println("Problema de formatação");
        }
        txtfCnpj = new JFormattedTextField(cnpj);
        txtfCpf = new JFormattedTextField(cpf);
        this.lblNome.setText("CLIENTE:");
        this.lblNome.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.lblNome.setForeground(Color.black);

        this.txtNome.setPreferredSize(new Dimension(270, 30));
        this.txtNome.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.lblCnpj.setText("CNPJ:     ");
        this.lblCnpj.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.lblCnpj.setForeground(Color.black);

        this.lblCpf.setText("CPF:       ");
        this.lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.lblCpf.setForeground(Color.black);

        this.txtfCnpj.setPreferredSize(new Dimension(270, 30));
        this.txtfCnpj.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.txtfCpf.setPreferredSize(new Dimension(270, 30));
        this.txtfCpf.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.lblEscolha.setText("CNPJ/CPF");
        this.lblEscolha.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.lblEscolha.setForeground(Color.black);

        this.bntCnpj.setText("CNPJ          ");
        this.bntCnpj.setForeground(Color.black);
        this.bntCnpj.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.bntCnpj.setOpaque(false);
        this.bntCnpj.setBorder(null);

        this.bntCpf.setText("CPF");
        this.bntCpf.setForeground(Color.black);
        this.bntCpf.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.bntCpf.setOpaque(false);
        this.bntCpf.setBorder(null);

        this.bntNenhum.setText("Nenhum");
        this.bntNenhum.setForeground(Color.black);
        this.bntNenhum.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.bntNenhum.setOpaque(false);
        this.bntNenhum.setBorder(null);

        this.bntConfirm.setPreferredSize(new Dimension(50, 50));
        this.bntConfirm.setBackground(Color.darkGray);

        this.grupo.add(this.bntCnpj);
        this.grupo.add(this.bntCpf);
        this.grupo.add(this.bntNenhum);

    }

    private int testarCampos() {
        Integer teste = new Integer(0);
        char nome[] = this.txtNome.getText().toCharArray();

        if (this.txtNome.getText().length() < 5) {
            return ++teste;
        } else if (nome[0] == 32 && nome[1] == 32 && nome[2] == 32) {
            return ++teste;
        }
        for (char caracter : nome) {
            if (caracter >= 48 && caracter <= 57) {
                teste++;
            } else if (caracter >= 58 && caracter <= 64 || caracter >= 91 && caracter <= 96) {
                teste++;
            } else if (caracter > 32 && caracter <= 47 || caracter >= 123 && caracter <= 126) {
                teste++;
            }
        }

        if (bntCnpj.isSelected() && txtfCnpj.getText().equals("  .   .   /    -  ")) {
            teste++;
        } else if (bntCpf.isSelected() && txtfCpf.getText().equals("   .   .   -  ")) {
            teste++;
        } else {
            if (txtfCnpj.getText().equals("  .   .   /    -  ")) {
                registro = txtfCpf.getText();
            } else {
                registro = txtfCnpj.getText();
            }
        }

        return teste;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jpConfirma.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 33));

        if (this.bntCnpj.isSelected()) {
            this.lblCnpj.setVisible(true);
            this.txtfCnpj.setVisible(true);
            this.bntConfirm.setVisible(true);
            this.txtfCpf.setText(""); // se o cnpj foi selecionado apagamos  cpf

            this.lblCpf.setVisible(false);
            this.txtfCpf.setVisible(false);
            this.jpConfirma.setVisible(true);
        } else if (this.bntCpf.isSelected()) {
            this.lblCpf.setVisible(true);
            this.txtfCpf.setVisible(true);
            this.bntConfirm.setVisible(true);
            this.txtfCnpj.setText(""); // apagamos o cnpj

            this.lblCnpj.setVisible(false);
            this.txtfCnpj.setVisible(false);
            this.jpConfirma.setVisible(true);
        } else if (this.bntNenhum.isSelected()) {
            this.txtfCnpj.setText("");
            this.txtfCpf.setText("");
            this.lblCpf.setVisible(false);
            this.txtfCpf.setVisible(false);
            this.lblCnpj.setVisible(false);
            this.txtfCnpj.setVisible(false);

            this.jpConfirma.setVisible(true);
            this.bntConfirm.setVisible(true);
            this.jpConfirma.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 88));
            this.registro = null;

        }
    }

    protected class Fundo extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // extendo de painel para utilizar o comando super // 
            Image img = Usuario.this.iconPet.getImage();
            g.drawImage(img, 0, 0, this);
        }

    }
}
