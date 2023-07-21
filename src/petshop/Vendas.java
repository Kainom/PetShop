/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private Float total = 0f;
    private JLabel lblCarrinho, lblValor, lblRegistro, lblNome, lblCep, lblRua, lblBairro, lblNum, lblAdicional;
    private JTextField txtBairro, txtRua, txtNum, txtAdicional;
    private JFormattedTextField txtfCep;
    private MaskFormatter cp;
    private ImageIcon iconCarrinho;
    private List<Float> compras;
    private List<JLabel> lblCompras = new ArrayList<>();
    private List<JSpinner> spinner = new ArrayList<>();
    private List<Float> precos;
    private JPanel jpCarrinho, jpDados;

    public Vendas(List<Float> carrinho, List<Float> prec, String registro, String nome) {
        super(registro, nome);
        this.compras = carrinho; // valor dos produtos escolhidos  
        this.precos = prec; // valor de todos os produtos 
        System.out.println(registro + "c");
        super.configurarJanela();
        this.setTitle("VENDAS");
        
        super.configurarPanel();
        configurarDads(carrinho, prec);
        configurarPane();

    }

    protected void configurarPane() {
        int k = 0;
        jpCarrinho = new JPanel();
        jpDados = new JPanel();

        this.painel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        this.painel.removeAll();
        this.jpShop.removeAll();
        this.jsPane.setPreferredSize(new Dimension(800, 100));

        this.jpCarrinho.setPreferredSize(new Dimension(800, 100));
        this.jpCarrinho.setBackground(Color.white);
        this.jpCarrinho.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.jpCarrinho.setOpaque(false);
        this.jpCarrinho.add(this.lblCarrinho);
        this.jpCarrinho.add(this.lblValor);

        this.jpDados.setPreferredSize(new Dimension(745, 262));
        this.jpDados.setBackground(Color.darkGray);
        this.jpDados.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 25));        
        this.jpDados.setOpaque(false);
        this.jpDados.add(this.lblNome);
        this.jpDados.add(this.lblRegistro);
        this.jpDados.add(this.lblCep);
        this.jpDados.add(this.txtfCep);
        this.jpDados.add(this.lblBairro);
        this.jpDados.add(this.txtBairro);
        this.jpDados.add(this.lblRua);
        this.jpDados.add(this.txtRua);
        this.jpDados.add(this.lblNum);
        this.jpDados.add(this.txtNum);
        this.jpDados.add(this.lblAdicional);
        this.jpDados.add(this.txtAdicional);
        this.jpDados.add(this.bntConfirm);
        
        this.jpVolta.removeAll();
        this.jpVolta.add(this.bntVolta,BorderLayout.SOUTH   );
        this.painel.add(this.jpVolta);
        this.painel.add(this.jsPane);
        this.painel.add(this.jpCarrinho);
        this.painel.add(this.jpDados);

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
                    lblValor.setText(total.toString());
                    total = 0f;
                }

            });
        }

        this.bntConfirm.addActionListener(evento -> action(evento));
        this.bntVolta.addActionListener(evento -> action(evento));
    }

    protected void configurarDads(List<Float> carrinho, List<Float> prec) {
        super.configurarDados();
        Float i = new Float(0);
        List<JLabel> color = new ArrayList<>();
        iconCarrinho = new ImageIcon(getClass().getResource("/imagens/carrinho.png"));
        lblCarrinho = new JLabel(iconCarrinho);
        color.add(lblValor = new JLabel());
        color.add(lblRegistro = new JLabel());
        color.add(lblNome = new JLabel("NOME: " + super.nome));
        color.add(lblCep = new JLabel("CEP:"));
        color.add(lblRua = new JLabel("RUA:"));
        color.add(lblBairro = new JLabel("BAIRRO:"));
        color.add(lblNum = new JLabel("NUM:"));
        color.add(lblAdicional = new JLabel("COMPLEMENTO:"));
        txtBairro = new JTextField();
        txtRua = new JTextField();
        txtNum = new JTextField();
        txtAdicional = new JTextField();
        
        this.bntVolta = new JButton(this.iconVolta);
        this.bntVolta.setPreferredSize(new Dimension(60, 60));
        this.bntVolta.setBackground(Color.darkGray);
        this.bntVolta.setOpaque(false);
        this.bntVolta.setBorder(null);
        this.bntVolta.setFocusPainted(false);
        
        try {
            cp = new MaskFormatter("#####-###");
        } catch (Exception erro) {
        }
        txtfCep = new JFormattedTextField(cp);

        color.stream().forEach(cor -> cor.setForeground(new Color(255, 117, 24)));
        color.stream().forEach(cor -> cor.setFont(new Font("Arial Black", Font.BOLD, 12)));

        for (JLabel cor : color) {
            cor.setForeground(new Color(255, 117, 24));
            cor.setFont(new Font("Arial Black", Font.BOLD, 12));
        } // seta a cor da maioria dos lbls e a font 
        this.compras.stream().map(var -> var += 2);

        for (Float soma : this.compras) {
            i += soma;
            this.lblValor.setText("" + i);
        } // seta o valor inicial do carrinho

        this.lblNome.setFont(new Font("Arial Black", Font.BOLD, 16));
        this.lblNome.setPreferredSize(new Dimension(470, 30));

        this.lblRegistro.setFont(new Font("Arial Black", Font.BOLD, 16));

        this.txtBairro.setPreferredSize(new Dimension(225, 30));
        this.txtBairro.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.txtRua.setPreferredSize(new Dimension(225, 30));
        this.txtRua.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.txtAdicional.setPreferredSize(new Dimension(180, 30));
        this.txtAdicional.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.txtAdicional.setText("OPCIONAL");

        class Mouse extends MouseAdapter { // implementa evento do mouse 

            public void adiconaEvent() {
                txtAdicional.addMouseListener(this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (txtAdicional.getText().equals("OPCIONAL")) {
                    txtAdicional.setText("");
                }
            }
        }
        new Mouse().adiconaEvent();

        this.txtfCep.setPreferredSize(new Dimension(100, 30));
        this.txtfCep.setFont(new Font("Arial Black", Font.PLAIN, 12));

        this.txtNum.setPreferredSize(new Dimension(50, 30));
        this.txtNum.setFont(new Font("Arial Black", Font.PLAIN, 12));

        if (super.registro.length() == 14 && !super.registro.endsWith(" ")) { // referente ao cpf
            this.lblRegistro.setText("CPF: " + super.registro);
            this.lblNome.setPreferredSize(new Dimension(500, 30));
        } else if (super.registro.length() > 14) { // referente ao cnpj 
            this.lblRegistro.setText("CNPJ: " + super.registro);
        } else {
            this.lblRegistro.setVisible(false);
            this.lblNome.setPreferredSize(new Dimension(800, 30));
        }

        for (Float compara : carrinho) {  //compara valor dos  produtos selecionados com todos os produtos afim de descobri qual lbl  definir o lbl
            for (int k = 0; k < QUANTIDADE_PRODUTOS; k++) {
                if (compara.equals(prec.get(k))) {
                    this.lblCompras.add(this.lblProdutos.get(k));
                    int produtos = Estoque.getProdutos().get(k);

                    SpinnerModel value = new SpinnerNumberModel(1, 1, produtos, 1); // adiciona os spinner referente aos produtos selecionados 
                    JSpinner spinn = new JSpinner(value);
                    spinn.setPreferredSize(new Dimension(50, 20));
                    this.spinner.add(spinn);

                }
            }
        }
        for (JLabel adiciona : this.lblCompras) {

        }
    }

    private void action(ActionEvent evento) {
        long num;
        if (evento.getSource().equals(this.bntConfirm)) {
            int teste = testarCampos(txtBairro.getText());

            teste += testarCampos(txtRua.getText());
            try {
                num = Long.parseLong(txtNum.getText());
            } catch (NumberFormatException erro) {
                teste++;
            }
            if (teste == 0) {
                Vendas.this.dispose();
            }
        } else if (evento.getSource().equals(this.bntVolta)) {
            new Produtos(this.lblRegistro.getText(), this.lblNome.getText()).setVisible(true);
            this.dispose();
        }
    }
}
