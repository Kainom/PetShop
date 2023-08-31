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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author User
 */
public class VendasHistorico {

    private boolean existeEndereco;
    private int tipoArmazena, contagem;
    private List<String> leitura, leituraRegistro;
    private String nome, registro, cep, bairro, rua, num, complemento, valor, cliente, produtos;
    private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Vendas.txt");
    private File arquivoReserva = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/VendasReserva.txt");
    private File arquivoRegistro = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Registros.txt");
    private File arquivoRegistroReserva = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/RegistrosReserva.txt");
    private FileReader fr;
    private BufferedReader br;
    private PrintWriter pw;

    public VendasHistorico() {
        new Relation().setVisible(true);
    }

    public VendasHistorico(String nome, String registro) { // utilizado na instância  para testar o login
        this.nome = nome;
        this.registro = registro;
    }

    public VendasHistorico(String registro) { // utilizado na instância para testar o endereço
        this.registro = registro;
    }

    public VendasHistorico(String nome, String registro, String cep, String bairro, String rua, String num, String complemento, String valor, String produtos, int tipoArmazena, boolean existeEndereco) {
        this.nome = nome;
        this.registro = registro;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.num = num;
        this.complemento = complemento;
        this.valor = valor;
        this.tipoArmazena = tipoArmazena;
        this.produtos = produtos;
        this.existeEndereco = existeEndereco;

        if (!complemento.equals("")) {
            this.cliente = "Nome: " + this.nome + "||" + this.registro + "||" + "Cep: " + this.cep + "||" + "Bairro: " + this.bairro + "||" + "Rua: " + this.rua + "||" + "Num: " + this.num + "||" + "Complemento: " + this.complemento + "||" + "Valor: " + this.valor + "||" + this.produtos;
        } else {
            this.cliente = "Nome: " + this.nome + "||" + this.registro + "||" + "Cep: " + this.cep + "||" + "Bairro: " + this.bairro + "||" + "Rua: " + this.rua + "||" + "Num: " + this.num + "||" + "Valor: " + this.valor + "||" + this.produtos;

        }

        armazena();
        if (this.tipoArmazena == 1) {
            armazenaRegistro(); // armazena o registro apenas no cadastro do usuário;
        }
    }

    public VendasHistorico(String nome, String Registro, String valor, String produtos, int tipoArmazena, boolean existeEndereco) {

        this.nome = nome;
        this.registro = Registro;
        this.valor = valor;
        this.tipoArmazena = tipoArmazena;
        this.produtos = produtos;
        this.existeEndereco = true; // o endereço existe ou o usuário não deseja registrá-lo.Portando,true;
        this.cliente = "Nome: " + this.nome + "||" + registro + "||" + "Valor: " + this.valor + "||" + this.produtos;

        armazena();
        if (this.tipoArmazena == 1) {
            armazenaRegistro(); // armazena o registro apenas no cadastro do usuário;   
        }
    }

    public VendasHistorico(String nome, String valor, String produtos, int tipoArmazena) { // cliente avulso
        lendo();
        this.nome = (this.getContagem() > 0) ? nome + (this.getContagem() + 1) : nome; // método de contagem lê a quantidade de clientes avulsos que ja existem e setta uma posição para este novo 
        this.valor = valor;
        this.produtos = produtos;
        this.tipoArmazena = tipoArmazena;
        this.cliente = "Nome: " + this.nome + "||" + "Valor: " + this.valor + "||" + this.produtos;

        armazena();
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }

    public int getContagem() {
        return contagem;
    }

    private void armazenaRegistro() {
        String registro = (this.registro.length() == 25) ? this.registro.replace(";CNPJ: ", "") : this.registro.replace(";CPF: ", "");
        registro = this.nome + ";" + registro;
        try {
            if (!this.arquivoRegistro.exists()) {
                System.out.println("Criando");
                this.arquivoRegistro.createNewFile();
            }
            copyFile(this.arquivoRegistro, this.arquivoRegistroReserva);
            this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.arquivoRegistro, true)));
            this.pw.println(registro);
            this.pw.flush();
            this.pw.close();
        } catch (Exception err) {
            System.out.println("ERRO NO ARQUIVO");
        }
    }

    private void armazena() {
        try {
            lendo();
            if (!this.arquivo.exists()) {
                System.out.println("CRIANDO");
                this.arquivo.createNewFile();
            }
            copyFile(this.arquivo, this.arquivoReserva); // antes de alterar o arquivo cria uma cópia do mesmo para que não haja perde caso haja um eventual erro 
            if (!this.existeEndereco && this.tipoArmazena == 2) { // se o endereço não existe e o usuário deseja colocar
                this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.arquivo)));
                for (String le : this.leitura) { // splits que moldam um novo registro de armazenação,remodelando os campos que antes não possuiam o endereço
                    if (le.contains(this.registro)) { // altera somente o perfil selecionado.Concede o valor existente no armazenamento com o endereço colocado nesse novo login
                        String split1[] = le.split("Valor:");
                        String split2[] = this.cliente.split("Valor:");
                        this.cliente = split2[0] + "Valor:" + split1[1];
                        this.pw.println(this.cliente);
                        this.pw.flush();
                        //System.out.println(this.cliente);
                    } else { // reescreve os que não serão modificados 
                        this.pw.println(le);
                        this.pw.flush();

                    }
                }
                this.pw.close();
            }
            if (this.tipoArmazena == 1) { // referente ao cliente avulso
                this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.arquivo, true)));
                this.pw.println(this.cliente);
                this.pw.flush();
                this.pw.close();

            } else { // referente ao cliente cadastrado.
                lendo();
                this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.arquivo)));
                for (String armazena : this.leitura) {
                    String split1[] = armazena.split("Valor:");
                    String replace1 = split1[0].replace("Nome:", "");
                    String documento = replace1.replace("||", "");
                    String registro = "";
                    String valor = split1[1];
                    String valorSplit[] = valor.split("Produtos:");

                    if (documento.contains("CPF:")) {
                        if (this.registro.contains(";CPF:")) {
                            this.registro = this.registro.replace(";CPF:", ""); // retira a nomenclatura para realizar comparações
                        }
                        documento = documento.replace("Cep", "");
                        String cpf[] = documento.split(":");
                        registro = cpf[1];

                    } else if (documento.contains("CNPJ:")) {
                        if (this.registro.contains(";CNPJ:")) {
                            this.registro = this.registro.replace(";CNPJ:", "");
                        }
                        documento = documento.replace("Cep", "");
                        String cnpj[] = documento.split(":");
                        registro = cnpj[1];
                    }

                    if (registro.equals(this.registro)) { // compara para ver se o registro escolhido é o mesmo 
                        valor = valorSplit[0].replace("||", ""); // replaces para formatar o dado armazenado 
                        valor = valor.replace(",", ".");
                        valor = valor.replace(" ", "");
                        this.valor = this.valor.replace(",", ".");
                        this.produtos = produtos.replace("Produtos:", ",");
                        float novoValor = (Float.parseFloat(valor) + Float.parseFloat(this.valor)); // acréscimo do valor armazenado com o valor colocado

                        String novoValor2 = novoValor + "";
                        valor = valor.replace(".", ",");

                        novoValor2 = novoValor2.replace(".", ",");
                        armazena = armazena.replace((valor), (novoValor2)); // troca apenas o numero a ser armazenado 
                        pw.println(armazena + this.produtos);
                        this.pw.flush();
                    } else { // sobreescreve os não alterados 
                        this.pw.flush();
                        pw.println(armazena);
                    }
                }

                pw.close();
            }
        } catch (IOException ex) {
            System.out.println("ERRO NO ARQUIVO");
        }

    }

    public boolean teste() { // teste sobrecarregado que analisa se há um endereço armazenado.Utilizado no momento de oferece ou não a possibilidade de armazenação 
        lendo();
        for (String le : this.leitura) {
            if (le.contains(this.registro) && le.contains("Cep:")) {
                return true;
            }
        }
        return false;

    }

    private void lendoRegistros() {
        try {
            leituraRegistro = new ArrayList<>();
            fr = new FileReader(this.arquivoRegistro);
            br = new BufferedReader(fr);
            while (br.ready()) {
                this.leituraRegistro.add(br.readLine());
            }
            fr.close();
            br.close();
        } catch (Exception err) {
        }
    }

    private void lendo() {
        try {
            leitura = new ArrayList<>();
            fr = new FileReader(this.arquivo);
            br = new BufferedReader(fr);
            while (br.ready()) {
                this.leitura.add(br.readLine());
            }
            for (String le : leitura) { // Concede ao cliente avulso uma numeração
                if (le.contains("CLIENTE") && !(le.contains("CPF:")) && (!le.contains("CNPJ:"))) {
                    this.setContagem(this.getContagem() + 1);
                }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
        }

    }

    public int teste(int tipoArmazena) { // testes para verificar a validade do cadastro.Se o mesmo exise ou não.
        lendo();
        lendoRegistros();
        int existencia = 0;
        int validade = 0;

        for (String registro : this.leituraRegistro) { // se o registro e o nome já estiverem armazenados,retorna 1.Do contrário retorna 0 //
            String documento[] = registro.split(";");
            String nome[] = registro.split(";");
            System.out.println(nome[0].length());
            System.out.println(nome[0]);
            System.out.println(documento[1]);
            existencia = (documento[1].equals(this.registro) && nome[0].equals(this.nome)) ? 1 : existencia; // verifica se pelo menos há um perfil
            validade = (documento[1].equals(this.registro)) ? 1 : validade; //Como o nome pode se repetir,o critério valido é  apenas o registro 
        }

        if (tipoArmazena == 1) { // verifica se o teste é para o login ou para o cadastro //
            return validade;
        } else {
            return existencia;
        }
    }

    public static void copyFile(File origem, File destino) throws IOException {
        Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    private class Relation extends TelaInicial {

        private JLabel lblRelationProdutos;
        private JLabel lblRelationCompradores;
        private JButton btnRelationProdutos, btnRelationCompradores, bntVolta;
        private ImageIcon iconProdutos, iconCompradores, iconVolta;

        public Relation() {
            this.setTitle("RELATION");
            reutilizandoDados();
        }

        private void reutilizandoDados() { // reutilizando componentes semelhantes,apenas alterando as diferenças 
            iconProdutos = new ImageIcon(getClass().getResource("/screnn/Produtos.png"));
            iconCompradores = new ImageIcon(getClass().getResource("/screnn/Compradores.png"));
            iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));
            bntVolta = new JButton(iconVolta);
            this.btnRelationProdutos = this.btnVenda;
            this.btnRelationCompradores = this.btnEstoque;
            this.lblRelationProdutos = this.lblVenda;
            this.lblRelationCompradores = this.lblEstoque;

            this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 200));
            this.jpVolta.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 200));
            this.jpVolta.setPreferredSize(new Dimension(250, 500));
            this.jpVolta.setBackground(Color.darkGray);
            this.jpVolta.setOpaque(false);

            this.painel.remove(this.jpShop);
            this.painel.add(this.jpVolta);
            this.painel.add(this.jpShop);

            this.jpShop.remove(this.bntRelation);
            this.jpShop.remove(this.lblRelation);
            this.jpShop.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 35));

            for (ActionListener bntProdutos : this.btnVenda.getActionListeners()) {
                this.btnVenda.removeActionListener(bntProdutos);
                for (ActionListener bntCompradores : this.btnEstoque.getActionListeners()) {
                    this.btnEstoque.removeActionListener(bntCompradores);
                }
            }
            this.lblRelationCompradores.setText("COMPRADORES");
            this.lblRelationProdutos.setText("PRODUTOS        ");

            this.bntVolta.setPreferredSize(new Dimension(60, 60));
            this.bntVolta.setBackground(Color.red);
            this.bntVolta.setOpaque(false);
            this.bntVolta.setBorder(null);
            this.bntVolta.setFocusPainted(false);
            this.jpVolta.add(this.bntVolta);

            this.btnRelationCompradores.setIcon(this.iconCompradores);
            this.btnRelationProdutos.setIcon(this.iconProdutos);
            this.btnRelationProdutos.addActionListener(evento -> escolha(evento));
            this.btnRelationCompradores.addActionListener(evento -> escolha(evento));
            this.bntVolta.addActionListener(evento -> escolha(evento));

        }

        private void escolha(ActionEvent evento) {
            if (this.btnRelationProdutos.equals(evento.getSource())) {
                this.dispose();
                new VendasRelation(1).setVisible(true);
            } else if (this.btnRelationCompradores.equals(evento.getSource())) {
                this.dispose();;
                new VendasRelation(2).setVisible(true);

            } else if (this.bntVolta.equals(evento.getSource())) {
                this.dispose();
                new TelaInicial().setVisible(true);
            }
        }

        private class VendasRelation extends TelaInicial {

            private JPanel jpProdutosMaisVendidos, jpMaioresCompradores, jpVolta;
            private JScrollPane jsGraficoProdutos, jsGraficoVendas;
            private DefaultCategoryDataset graficoProdutos, graficoVendas;
            private List<String> produtosVendidos, produtosNome, produtos10, maioresCompradores; // produtosVendidos:todos os produtos,produtosNome:só os nomes,produtos10:entre os 10 mais vendidos
            private List<Integer> produtosQuantidade; // só os valores em ordem ;
            private List<JLabel> lblProdutosMaisVendidos, lblMaioresCompradores;
            private List<Float> maioresCompras;
            private ImageIcon iconVolta;
            private JButton bntVolta;

            public VendasRelation(int escolha) {
                relation();
                if (escolha == 1) { // escolha que divide os gráficos
                    configurarGraficoProdutos();
                } else if (escolha == 2) {
                    configurarGraficoVendas();
                } else {
                    throw new NumberFormatException();
                }
                configurarPane();

            }

            private void configurarPane() {
                this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 25));
                jpVolta = new JPanel();
                iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));
                bntVolta = new JButton(iconVolta);

                this.bntVolta.setPreferredSize(new Dimension(60, 60));
                this.bntVolta.setBackground(Color.darkGray);
                this.bntVolta.setOpaque(false);
                this.bntVolta.setBorder(null);
                this.bntVolta.setFocusPainted(false);
                this.bntVolta.addActionListener(volta -> voltar(volta));

                this.jpVolta.setPreferredSize(new Dimension(250, 500));
                this.jpVolta.setBackground(Color.darkGray);
                this.jpVolta.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 50));
                this.jpVolta.add(this.bntVolta);
                this.jpVolta.setOpaque(false);

                this.jpShop.removeAll();
                this.jpShop.setPreferredSize(new Dimension(700, 200));
                this.jpShop.setOpaque(true);
                this.jpShop.setVisible(false);
                this.painel.add(this.jpVolta);

            }

            private void configurarGraficoProdutos() {
                int i = 0;
                graficoProdutos = new DefaultCategoryDataset();
                lblProdutosMaisVendidos = new ArrayList<>();
                jpProdutosMaisVendidos = new JPanel();
                jsGraficoProdutos = new JScrollPane();

                JFreeChart grafico = ChartFactory.createBarChart3D("PRODUTOS MAIS  VENDIDOS", "", "", graficoProdutos, PlotOrientation.HORIZONTAL, true, true, false);
                var trans = new Color(0xFF, 0xFF, 0xFF, 0);
                grafico.getPlot().setBackgroundPaint(trans);
                grafico.setBackgroundPaint(trans);
                for (Integer produt : produtosQuantidade) {
                    this.lblProdutosMaisVendidos.add(new JLabel((i + 1) + "° " + produtos10.get(i) + ": " + produt));
                    this.lblProdutosMaisVendidos.get(i).setFont(new Font("Arial Black", Font.BOLD, 14));
                    this.lblProdutosMaisVendidos.get(i).setForeground(Color.GREEN);
                    this.graficoProdutos.setValue(produt, produtos10.get(i), ((i + 1) + "°"));
                    i++;
                    if (i == 10) {
                        break;
                    }
                }
                ChartPanel panel = new ChartPanel(grafico);
                panel.setPreferredSize(new Dimension(700, 300));
                panel.setOpaque(false);
                this.painel.add(panel);

                this.jpProdutosMaisVendidos.setLayout(new BoxLayout(this.jpProdutosMaisVendidos, BoxLayout.Y_AXIS));
                this.jpProdutosMaisVendidos.setBackground(Color.darkGray);
                this.jpProdutosMaisVendidos.setOpaque(false);

                this.jsGraficoProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                this.jsGraficoProdutos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                this.jsGraficoProdutos.setPreferredSize(new Dimension(250, 200));
                this.jsGraficoProdutos.setOpaque(false);
                this.jsGraficoProdutos.getViewport().setOpaque(false);
                this.jsGraficoProdutos.setViewportView(this.jpProdutosMaisVendidos);
                this.jsGraficoProdutos.setBorder(null);

                this.jpProdutosMaisVendidos.add(Box.createVerticalStrut(20));
                this.lblProdutosMaisVendidos.stream().forEach(add -> jpProdutosMaisVendidos.add(add));
                this.painel.add(this.jsGraficoProdutos);
            }

            private void configurarGraficoVendas() {
                graficoVendas = new DefaultCategoryDataset();
                lblMaioresCompradores = new ArrayList<>();
                jpMaioresCompradores = new JPanel();
                jsGraficoVendas = new JScrollPane();
                int i = maioresCompradores.size();
                int k = 0;
                var trans = new Color(0xFF, 0xFF, 0xFF, 0);

                JFreeChart grafico2 = ChartFactory.createBarChart3D("MAIORES CLIENTES", "", "", graficoVendas, PlotOrientation.HORIZONTAL, true, true, false);
                grafico2.getPlot().setBackgroundPaint(trans);
                grafico2.setBackgroundPaint(trans);

                for (String nomes : this.maioresCompradores) {
                    i--; // como a lista foi ordenada pelo sort no formato de ordem crescente,eu realizo o inverso para utilzar o formato decrescente
                    var lbl = new JLabel((k + 1) + "° " + this.maioresCompradores.get(i) + ": " + "$" + this.maioresCompras.get(i)); // acrescentando ao lbl
                    lbl.setFont(new Font("Arial Black", Font.BOLD, 14));
                    lbl.setForeground(Color.black);
                    this.jpMaioresCompradores.add(lbl);
                    graficoVendas.setValue(this.maioresCompras.get(i), this.maioresCompradores.get(i), ((k + 1) + "°"));
                    k++;
                    if (k == 10) {
                        break;
                    }
                }
                ChartPanel panel2 = new ChartPanel(grafico2);
                panel2.setPreferredSize(new Dimension(700, 300));
                panel2.setOpaque(false);
                panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

                this.jpMaioresCompradores.setLayout(new BoxLayout(this.jpMaioresCompradores, BoxLayout.Y_AXIS));
                this.jpMaioresCompradores.setBackground(Color.darkGray);
                this.jpMaioresCompradores.setOpaque(false);

                this.jsGraficoVendas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                this.jsGraficoVendas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                this.jsGraficoVendas.setPreferredSize(new Dimension(250, 200));
                this.jsGraficoVendas.setOpaque(false);
                this.jsGraficoVendas.getViewport().setOpaque(false);
                this.jsGraficoVendas.setViewportView(this.jpMaioresCompradores);
                this.jsGraficoVendas.setBorder(null);

                this.painel.add(panel2);
                this.painel.add(this.jsGraficoVendas);

            }

            private void relation() {
                int k = 0;
                int produtosPosition;
                maioresCompradores = new ArrayList<>();
                List<String> compradores = new ArrayList<>();
                List<Float> numbers = new ArrayList<>();
                List<String> quanto = new ArrayList<>();
                Set<String> nombre = new HashSet<>();
                produtosQuantidade = new ArrayList<>();
                produtosNome = new ArrayList<>();
                produtosVendidos = new ArrayList<>();
                produtos10 = new ArrayList<>();
                maioresCompras = new ArrayList<>();

                this.produtosNome.add(" Nutrilus ");
                this.produtosNome.add(" Whiskas ");
                this.produtosNome.add(" Cheval ");
                this.produtosNome.add(" Milho ");
                this.produtosNome.add(" Max ");
                this.produtosNome.add(" Queranon ");
                this.produtosNome.add(" Ampicilina ");
                this.produtosNome.add(" Coleira ");
                this.produtosNome.add(" Dontral ");
                this.produtosNome.add(" Prediderm ");
                this.produtosNome.add(" Granplus ");
                this.produtosNome.add(" Ração Extrusada ");

                lendo();
                for (String lendo : leitura) {
                    String separaOsProdutos[] = lendo.split("Produtos:"); // separa os produtos dos demais registros
                    String todosProdutos = separaOsProdutos[1];                        // passa para uma string
                   String separaTodosOsProdutos[] = todosProdutos.split(","); // separa todos os produtos da linha(afim de manipulá-los separadamente ) 
                    produtosPosition = separaTodosOsProdutos.length;        // pega a quantidade de produtos do vetor 
                    for (int i = 0; i < produtosPosition; i++) {
                        String produtosEspecificos = separaTodosOsProdutos[i];       // pega todos os produtos separados na linha 
                        String separa[] = produtosEspecificos.split(";");
                        String quantidade = separa[1];
                        String nome = separa[0];
                        produtosVendidos.add(nome); // pega o nome de todos os produtos(repetidos,o desejo é as unidades em si)
                        quanto.add(quantidade);
                        nombre.add(nome);         // set que pega o apenas os nomes,sem repetição.(utilizado para se pegar a posição);
                    }
                    String valor[] = lendo.split("Valor:"); //1 split para pegar os valores
                    String valor1[] = valor[1].split("Produtos:"); // 2 split para pegar os valores
                    String valor2 = valor1[0].replace("||", ""); // 3 split para pegar os valores
                    String valor3 = valor2.replace(",", "."); // replace para evitar a exception 
                    numbers.add(Float.parseFloat(valor3));
                    maioresCompras.add(Float.parseFloat(valor3)); // adição a lsita 
                    valor2 = valor[0].replace("Nome: ", "");
                    valor2 = valor2.replace("||", "");
                    String nomenclatura[] = valor2.split(";");;
                    compradores.add(nomenclatura[0]);

                }
                List<Integer> h = new ArrayList<>();
                Collections.sort(maioresCompras); // reordena os valores em ordem crescente 
                for (Float comprasValores : maioresCompras) {
                    for (int i = 0; i < maioresCompras.size(); i++) {
                        k = 0;
                        for (Integer numb : h) {// testa todas as posições dos valores que ja foram ordenadas com os respectivos compradores 
                            k += (numb == i) ? 1 : k;
                        } 
                        if (numbers.get(i).equals(comprasValores) && k == 0) { // testa se o valor da lista inicial é igual ao valor da lista ordenada.O k é apenas a soma que indica se há ou não repetição
                            h.add(i); // Lista que armazena a pos dos valores que ja foram adicionados a ordem correta.Evita repetições
                            maioresCompradores.add(compradores.get(i)); ///Adiciona a ordem correta de compradores 
                            break;
                        }
                    }

                }
                k = 0;
                produtosPosition = nombre.size();

                for (int i = 0; i < this.produtosNome.size(); i++) {
                    int f = 0;
                    int resultado = 0;
                    for (String produtos : this.produtosVendidos) {
                        if (this.produtosNome.get(i).equals(produtos)) { // compara o primeiro produto com todas as unidades vendidas do mesmo 
                            resultado += Integer.parseInt(quanto.get(f)); // acrescenta a variavel resultado a quantidade  das unidades vendidas
                        }
                        f++;
                    }
                    if (k <= produtosPosition - 1 && resultado != 0) {
                        produtos10.add(produtosNome.get(i)); // adiciona o nome desse produtos vendidos 
                        produtosQuantidade.add(k, resultado);  // quantidade desses produtos vendidos
                        k++;
                    }
                }
                

                int j = produtosQuantidade.size() - 1; // ordena dos produtos mais vendidos para o menos vendidos 
                for (int n = 0; n <= j; n++) { 
                    for (int i = (0 + n); i <= j; i++) {
                        if (produtosQuantidade.get(n) < produtosQuantidade.get(i)) {
                            String aux = produtos10.get(n);
                            int aux2 = produtosQuantidade.get(n);
                            produtos10.set(n, produtos10.get(i));
                            produtos10.set(i, aux);
                            produtosQuantidade.set(n, produtosQuantidade.get(i));
                            produtosQuantidade.set(i, aux2);
                        }
                    }
                }

            }

            private void voltar(ActionEvent volta) {
                if (this.bntVolta.equals(volta.getSource())) {
                    this.dispose();
                    new Relation().setVisible(true);
                }
            }
        }
    }
}
