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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import petshop.Estoque.InsereProdutos;

/**
 *
 * @author User
 */
public class Vendas extends Produtos {

    private boolean escolha;
    private int tipoArmazena;
    private Estoque estoq = new Estoque();
    private InsereProdutos armazena = estoq.new InsereProdutos();
    private List<Integer> quantidadeReduzida = new ArrayList<>();
    private List<Integer> position = new ArrayList<>();
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
    private JRadioButton bntSim, bntNao;
    private ButtonGroup grupo;
    private JPanel jpCarrinho, jpDados;
    private boolean existeEndereco;

    public Vendas() {
    }

    public Vendas(List<Float> carrinho, List<Float> prec, String registro, String nome, int tipoArmazena) {
        super(registro, nome, tipoArmazena); // inicializa os campos da classe pais com os valores recebidos da mesma.Uma espécia de relação
        this.compras = carrinho; // valor dos produtos escolhidos  
        this.precos = prec; // valor de todos os produtos 
        this.tipoArmazena = tipoArmazena; // tipo de Armazenamento
        this.existeEndereco = new VendasHistorico(this.registro).teste(); // testa se o perfil cadastrado possui endereço
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
        this.jsPane.setPreferredSize(new Dimension(800, 145));

        this.jpCarrinho.setPreferredSize(new Dimension(800, 100));
        this.jpCarrinho.setBackground(Color.white);
        this.jpCarrinho.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.jpCarrinho.setOpaque(false);
        this.jpCarrinho.add(this.lblCarrinho);
        this.jpCarrinho.add(this.lblValor);

        this.jpDados.setPreferredSize(new Dimension(745, 262));
        this.jpDados.setBackground(Color.darkGray);
        this.jpDados.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 25));
        this.jpDados.setOpaque(true);
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
        this.jpDados.add(this.bntSim);
        this.jpDados.add(this.bntNao);
        this.jpDados.add(this.bntConfirm);

        if (this.tipoArmazena == 1 && this.nome.contains("CLIENTE") && !(this.escolha)) { // não permite o cliente avulso a escolha de colocar seu endereço
            this.jpCarrinho.add(this.bntConfirm);
            this.jpDados.setVisible(false);
        }

        if (this.existeEndereco) { // esconde os campos caso o cliente ja tenha seu endereço cadastrado
            this.jpCarrinho.add(this.bntConfirm);
            this.jpDados.setVisible(false);
            this.bntNao.setSelected(true);
        }

        this.jpVolta.removeAll();
        this.jpVolta.add(this.bntVolta, BorderLayout.SOUTH);
        this.painel.add(this.jpVolta);
        this.painel.add(this.jsPane);
        this.painel.add(this.jpCarrinho);
        this.painel.add(this.jpDados);

        for (JLabel adiciona : this.lblCompras) { // adiciona os spinners referente aos  produtos escolhidos anteriormente
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
                    quantidadeReduzida.removeAll(quantidadeReduzida);
                    for (JSpinner soma : spinner) {
                        cont = compras.get(i) * Float.parseFloat(soma.getValue().toString()); // multiplica a quantidade de produtos pelo preço do mesmo 
                        total += cont; // adiciona ao demais produtos a soma 
                        quantidadeReduzida.add((Estoque.getProdutos().get(position.get(i)) - Integer.parseInt(soma.getValue().toString()))); // quantidade dos produtos a ser reduzido do estoque
                        i++;
                    }
                    lblValor.setText(String.format("%.2f", total));
                    total = 0f;

                }

            });
        }

        this.bntConfirm.addActionListener(evento -> action(evento));
        this.bntVolta.addActionListener(evento -> action(evento));
    }

    protected void configurarDads(List<Float> carrinho, List<Float> prec) {
        super.configurarDados();
        List<JSpinner> spin = new ArrayList<>();
        List<JLabel> color = new ArrayList<>();
        Float valorLBLInicial = new Float(0);
        iconCarrinho = new ImageIcon(getClass().getResource("/screnn/carrinho.png"));
        lblCarrinho = new JLabel(iconCarrinho);
        bntVolta = new JButton(this.iconVolta);
        txtBairro = new JTextField();
        txtRua = new JTextField();
        txtNum = new JTextField();
        txtAdicional = new JTextField();
        bntSim = new JRadioButton();
        bntNao = new JRadioButton();
        grupo = new ButtonGroup();

        try {
            cp = new MaskFormatter("#####-###");
        } catch (Exception erro) {
        }
        txtfCep = new JFormattedTextField(cp);

        color.add(lblValor = new JLabel());
        color.add(lblRegistro = new JLabel());
        color.add(lblNome = new JLabel("NOME: " + super.nome));
        color.add(lblCep = new JLabel("CEP:"));
        color.add(lblRua = new JLabel("RUA:"));
        color.add(lblBairro = new JLabel("BAIRRO:"));
        color.add(lblNum = new JLabel("NUM:"));
        color.add(lblAdicional = new JLabel("COMPLEMENTO:"));

        this.bntVolta.setPreferredSize(new Dimension(60, 60));
        this.bntVolta.setBackground(Color.darkGray);
        this.bntVolta.setOpaque(false);
        this.bntVolta.setBorder(null);
        this.bntVolta.setFocusPainted(false);

        this.bntSim.setText("SIM");
        this.bntSim.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.bntSim.setForeground(Color.black);
        this.bntSim.setOpaque(false);
        this.bntSim.setBorder(null);
        this.grupo.add(bntSim);

        this.bntNao.setText("NÃO");
        this.bntNao.setFont(new Font("Arial Black", Font.PLAIN, 12));
        this.bntNao.setForeground(Color.black);
        this.bntNao.setOpaque(false);
        this.bntNao.setBorder(null);
        this.grupo.add(bntNao);

        this.bntConfirm.setPreferredSize(new Dimension(50, 50));

        color.stream() // seta a cor e a fonte paddrão dos lbls afim de economizar código
                .forEach(cor -> cor.setForeground(new Color(255, 117, 24)));
        color.stream()
                .forEach(cor -> cor.setFont(new Font("Arial Black", Font.BOLD, 12)));

        valorLBLInicial = (float) compras.stream()
                .mapToDouble(var -> var).sum(); // seta o valor inicial do lbl

        this.lblValor.setText(String.format("%.2f", valorLBLInicial));

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

        class Mouse extends MouseAdapter { // implementa evento do mouse // Como eu ja havia extendido de outra classe,necessitei de uma classe interna a esse método

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

        if (super.registro.length() == 14 && !super.registro.endsWith(" ")) { // referente ao cpf //  Analisa qual tipo de registro é para conceder a nomemclatura adequada
            escolha = true; // referente a presença de um registro 
            this.lblRegistro.setText("CPF: " + super.registro);
            this.lblNome.setPreferredSize(new Dimension(500, 30));
        } else if (super.registro.length() > 14) { // referente ao cnpj 
            escolha = true;
            this.lblRegistro.setText("CNPJ: " + super.registro);
        } else {
            escolha = false; // referente a ausência  de um registro 
            this.lblRegistro.setVisible(false);
            this.lblNome.setPreferredSize(new Dimension(800, 30));
        }

        for (Float compara : carrinho) {  //compara valor dos  produtos selecionados com todos os produtos afim de selecionar o lbl correspondente ao produto 
            for (int k = 0; k < QUANTIDADE_PRODUTOS; k++) {
                if (compara.equals(prec.get(k))) {
                    position.add(k);
                    int produtos = Estoque.getProdutos().get(k);
                    SpinnerModel value = new SpinnerNumberModel(1, 1, produtos, 1); // adiciona os spinner referente aos produtos selecionados 
                    JSpinner spinn = new JSpinner(value);
                    spinn.setPreferredSize(new Dimension(50, 20));
                    spin.add(spinn);
                }
            }
        }
        for (int j = 0; j < QUANTIDADE_PRODUTOS; j++) { // reorganiza os lbs e os spinners na ordem dos produtos,afim de obter uma organização e um melhor funcionamento do estoque
            for (int f = 0; f < position.size(); f++) {
                if (j == position.get(f)) {
                    this.lblCompras.add(lblProdutos.get(position.get(f)));
                    this.spinner.add(spin.get(f));
                    System.out.println(j + " " + position.get(f));
                    break;
                }

            }
        }
        Collections.sort(position); // reordena a position em ordem crescente afim de armazenar a nova quantidade de produtos de forma eficiente,pois os produtos possuem sua ordenação natural
        int m = 0;

        for (JSpinner soma : spinner) { // passa para a lista redution a quantidade a ser reduzida dos respectivos produtos no estoque quando o usuario não utiliza o spinner // "Redução padrão"
            quantidadeReduzida.add((Estoque.getProdutos().get(position.get(m)) - Integer.parseInt(soma.getValue().toString())));
            m++;
        }

    }

    private void action(ActionEvent evento) {
        String registro;
        String produtos = "";
        long num;
        int teste = 0, k = 0;
        boolean oferecer = true;
        List<String> le = Estoque.getNomeProdutos();
        for (Integer pos : this.position) {
            produtos += (this.position.size() > 1 && this.position.size() != (k + 1)) ? le.get(pos) + " " + ";" + spinner.get(k).getValue() + "," + " " : le.get(pos) + " " + ";" + spinner.get(k).getValue();
            k++;
            System.out.println(produtos);
        }
        produtos = "Produtos: " + produtos;

        if (evento.getSource().equals(this.bntConfirm)) {
            registro = (this.registro.length() == 14) ? ";CPF: " + this.registro : ";CNPJ: " + this.registro; // ternário que concende uma nomenclatura com um caracter de separação  para ser utilizado posteriormente
            if (this.bntSim.isSelected()) { // bnt que configura a vontade do cliente em oferecer seu enredereço
                oferecer = true; // referente a vontade do usuário de oferecer seu endereço
                teste += testarCampos(txtBairro.getText()); // usando esse método herdado de usuário para eliminar certos caracteres especiais
                teste += testarCampos(txtRua.getText()); // usando esse método herdado de usuário para eliminar certos caracteres especiais
                try {
                    num = Long.parseLong(txtNum.getText()); // se gerar exceção é porque não há só numeros,logo um erro
                } catch (NumberFormatException erro) {
                    teste++;
                }
            } else if (this.bntNao.isSelected()) { // bnt que configura a vontade do cliente não oferecer o endereço
                oferecer = false;
                teste = 0;
            }
            if (this.tipoArmazena == 1 && !this.escolha) { // armazenamento  de um cliente avulso genérico 
                new VendasHistorico(this.nome, this.lblValor.getText(), produtos, this.tipoArmazena);
                this.dispose();
                new TelaInicial().setVisible(true);
            } else if (teste == 0 && this.bntSim.isSelected() || this.bntNao.isSelected()) {
                try {
                    armazena.inserir(position, quantidadeReduzida); // passa para o estoque a nova quantidade de produtos e a posição equivalente de cada produtos
                    if (oferecer && this.escolha) { // as duas opções ao qual o cliente pode escolher no momento de sua compra
                        new VendasHistorico(this.nome, registro, this.txtfCep.getText(), this.txtBairro.getText(), this.txtRua.getText(), this.txtNum.getText(), this.txtAdicional.getText(), this.lblValor.getText(), produtos, this.tipoArmazena, this.existeEndereco);
                    } else if (!oferecer && this.escolha) {
                        new VendasHistorico(this.nome, registro, this.lblValor.getText(), produtos, this.tipoArmazena, this.existeEndereco);
                    }
                    Vendas.this.dispose();
                    new TelaInicial().setVisible(true);

                } catch (IOException ex) {
                }
            }
        } else if (evento.getSource().equals(this.bntVolta)) {
            new Produtos(this.registro, this.nome, this.tipoArmazena).setVisible(true);
            Vendas.this.dispose();

        }
    }
}
