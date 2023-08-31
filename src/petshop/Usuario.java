/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class Usuario extends TelaInicial implements ActionListener {

    private static final int TIPO_ARMAZENA = 1;
    protected String registro;
    protected ImageIcon iconDog, iconVolta;
    private JLabel lblNome, lblCnpj, lblCpf, lblEscolha;
    protected JTextField txtNome;
    protected JFormattedTextField txtfCnpj, txtfCpf;
    private MaskFormatter cnpj, cpf;
    protected JRadioButton bntCnpj, bntCpf, bntNenhum;
    private ButtonGroup grupo;
    protected JButton bntConfirm, bntVolta;

    public Usuario() {
        Estoque.produtos(); // Método estático que preenche uma lista estática com todos os produtos.O método sendo estático permite a utilização por todas as classes
        super.configurarJanela();
        this.setTitle("USUARIO");
        configurarPanel();

    }

    protected void configurarPanel() {
        super.configurarPanel();
        this.jpShop.removeAll(); // remove os componetes ja existentes da classe mãe para reutilizar o panel 

        this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 25));

        this.jpConfirma.setPreferredSize(new Dimension(440, 300));
        this.jpConfirma.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 60));
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

        this.jpConfirma.add(this.bntVolta);
        this.jpConfirma.add(this.bntConfirm);

        this.txtfCnpj.setVisible(false);
        this.txtfCpf.setVisible(false);
        this.lblCnpj.setVisible(false);
        this.lblCpf.setVisible(false);

        this.bntCnpj.addActionListener(this);
        this.bntCpf.addActionListener(this);
        this.bntNenhum.addActionListener(this);
        this.bntConfirm.addActionListener(evento -> action(evento));
        this.bntVolta.addActionListener(evento -> action(evento));

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
        iconDog = new ImageIcon(getClass().getResource("/screnn/shiba.png"));
        iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));
        bntConfirm = new JButton(iconDog);
        bntVolta = new JButton(iconVolta);

        try {
            cnpj = new MaskFormatter("##.###.###/####-##");  // máscara do cnpj 
            cpf = new MaskFormatter("###.###.###-##");     // máscara do cpf
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

        this.bntConfirm.setPreferredSize(new Dimension(60, 60));
        this.bntConfirm.setBackground(Color.darkGray);
        this.bntConfirm.setOpaque(false);
        this.bntConfirm.setBorder(null);
        this.bntConfirm.setFocusPainted(false);

        this.bntVolta.setPreferredSize(new Dimension(60, 60));
        this.bntVolta.setBackground(Color.darkGray);
        this.bntVolta.setOpaque(false);
        this.bntVolta.setBorder(null);
        this.bntVolta.setFocusPainted(false);

        this.grupo.add(this.bntCnpj);
        this.grupo.add(this.bntCpf);
        this.grupo.add(this.bntNenhum);

    }

    protected int testarCampos(String texto) {
        Integer teste = new Integer(0);
        char palavras[] = texto.toCharArray();
        try {
            Long soNumeros = Long.parseLong(texto); // se houver apenas números a exception não é acionada,configurando então em um erro no nome 
            return ++teste;

        } catch (NumberFormatException erroNumero) {
            teste = 0;
        }

        if (texto.length() < 5) { // nome precisa ao menos ter 5 caracteres 
            return ++teste;
        } else if (palavras[0] >= 32 && palavras[0] <= 64 || palavras[1] >= 32 && palavras[1] <= 64 || palavras[2] >= 32 && palavras[2] <= 64) { // precisa ter ausência desses caracteres 
            return ++teste;
        }

        for (char caracter : palavras) {
            if (caracter >= 59 && caracter <= 64 && caracter != 63 || caracter >= 91 && caracter <= 96) { // != de 63 para permitir ?
                teste++;
            } else if (caracter >= 34 && caracter <= 44 && caracter != 38 || caracter >= 123 && caracter <= 126) { // != de 38 para permitir &
                teste++;
            }
        }

        return teste;

    }

    protected int testarCampos() {  // uma versão  sobrecarregada que permite testar o registro separadamente.O intuito e a modularização,contudo tudo pode ser realizado em um só método
        int teste = testarCampos(this.txtNome.getText());

        if (bntCnpj.isSelected() && txtfCnpj.getText().equals("  .   .   /    -  ")) { // botôes selecionados sem o devido preenchimento em seus respectivos campos
            teste++;
        } else if (bntCpf.isSelected() && txtfCpf.getText().equals("   .   .   -  ")) {
            teste++;
        } else {
            if (txtfCnpj.getText().equals("  .   .   /    -  ")) { // se o cnpj estiver vazio o cpf recebe o valor 
                registro = txtfCpf.getText();
            } else {
                registro = txtfCnpj.getText();                    // se o cpf estiver vazio  o cnpj recebe o valor 
            }
        }
        if (!(this.bntCnpj.isSelected() || this.bntCpf.isSelected() || this.bntNenhum.isSelected())) { // concede erro caso nenhum botão seja selecionado
            teste++;
        }

        return teste;
    }

    private void action(ActionEvent event) {
        Integer teste = new Integer(testarCampos());
        VendasHistorico venda = new VendasHistorico(this.txtNome.getText(), this.registro); // Instânciando o objeto para se testar a existência dos campos
        Integer teste2 = venda.teste(TIPO_ARMAZENA); // concede a constante TIPO_ARMAZENA referente ao teste para o cadastro
        if (event.getSource().equals(this.bntConfirm)) {
            if (teste == 0 && teste2 == 0) {  // se os campos estiverem corretos e perfil não existir o mesmo permite o cadastro
                Usuario.this.dispose();
                new Produtos(this.registro, this.txtNome.getText(), TIPO_ARMAZENA).setVisible(true);
            }
        } else if (event.getSource().equals(this.bntVolta)) {
            new Cliente().setVisible(true);
            this.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // utiliza para apagar campos e definir o campo padrâo para clientes genéricos
        this.jpConfirma.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 5));
        this.bntConfirm.setVisible(true);
        String bom;
        if (this.bntCnpj.isSelected()) {
            this.lblCnpj.setVisible(true);
            this.txtfCnpj.setVisible(true);
            this.txtfCpf.setText(""); // se o cnpj foi selecionado apagamos  cpf
            this.txtNome.setText(bom = (this.txtNome.getText().equals("CLIENTE")) ? "" : this.txtNome.getText()); //Apaga a nomenclatura padrão do cliente avulso
            this.txtNome.setEditable(true);

            this.lblCpf.setVisible(false);
            this.txtfCpf.setVisible(false);
        } else if (this.bntCpf.isSelected()) {
            this.lblCpf.setVisible(true);
            this.txtfCpf.setVisible(true);
            this.txtfCnpj.setText(""); // apagamos o cnpj
            this.txtNome.setText(bom = (this.txtNome.getText().equals("CLIENTE")) ? "" : this.txtNome.getText());
            this.txtNome.setEditable(true);

            this.lblCnpj.setVisible(false);
            this.txtfCnpj.setVisible(false);
        } else if (this.bntNenhum.isSelected()) {
            this.txtfCnpj.setText("");
            this.txtfCpf.setText("");
            this.lblCpf.setVisible(false);
            this.txtfCpf.setVisible(false);
            this.lblCnpj.setVisible(false);
            this.txtfCnpj.setVisible(false);
            this.txtNome.setText("CLIENTE");
            this.txtNome.setEditable(false);
            this.txtNome.setBackground(Color.white);
            this.jpConfirma.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 60));
            this.registro = null;

        }
    }

}
