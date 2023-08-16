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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

    private List<String> leitura, leituraRegistro;
    private String nome, registro, cep, bairro, rua, num, complemento, valor, cliente;
    private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Vendas.txt");
    private File arquivoRegistro = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Registros.txt");
    private FileReader fr;
    private BufferedReader br;
    private PrintWriter pw;

    public VendasHistorico() {
        new VendasRelation().setVisible(true);
    }

    public VendasHistorico(String nome, String registro) {
        this.nome = nome;
        this.registro = registro;
    }

    public VendasHistorico(String nome, String registro, String cep, String bairro, String rua, String num, String complemento, String valor, String produtos) {
        this.nome = nome;
        this.registro = registro;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.num = num;
        this.complemento = complemento;
        this.valor = valor;
        if (!complemento.equals("")) {
            this.cliente = "Nome: " + nome + "||" + registro + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Complemento: " + complemento + "||" + "Valor: " + valor + "||" + produtos;
        } else {
            this.cliente = "Nome: " + nome + "||" + registro + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Valor: " + valor + "||" + produtos;

        }

        armazena();
        armazenaRegistro();

    }

    public VendasHistorico(String nome, String cep, String bairro, String rua, String num, String complemento, String valor, String produtos) {
        this.nome = nome;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.num = num;
        this.complemento = complemento;
        this.valor = valor;
        if (!complemento.equals("")) {

            this.cliente = "Nome: " + nome + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Complemento: " + complemento + " " + "Valor: " + valor + "||" + produtos;
        } else {
            this.cliente = "Nome: " + nome + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Valor: " + valor + "||" + produtos;

        }
        armazena();

    }

    public VendasHistorico(String nome, String Registro, String valor, String produtos) {
        this.nome = nome;
        this.registro = Registro;
        this.valor = valor;
        this.cliente = "Nome: " + nome + "||" + registro + "||" + "Valor: " + valor + "||" + produtos;
        armazena();
        armazenaRegistro();

    }

    public VendasHistorico(String nome, String valor, String produtos) {
        this.nome = nome;
        this.cliente = "Nome: " + nome + "||" + "Valor: " + valor + "||" + produtos;
        armazena();
    }

    private void armazenaRegistro() {
        String registro = (this.registro.length() == 25) ? this.registro.replace(";CNPJ: ", "") : this.registro.replace(";CPF: ", "");
        try {
            if (!this.arquivoRegistro.exists()) {
                System.out.println("Criando");
                this.arquivoRegistro.createNewFile();
            }
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
            if (!this.arquivo.exists()) {
                System.out.println("CRIANDO");
                this.arquivo.createNewFile();
            }

            this.pw = new PrintWriter(new BufferedWriter(new FileWriter(this.arquivo, true)));
            this.pw.println(this.cliente);
            this.pw.close();
        } catch (IOException ex) {
            System.out.println("ERRO NO ARQUIVO");
        }

    }

    private void lendoRegistros() {
        try {
            leituraRegistro = new ArrayList<>();
            fr = new FileReader(this.arquivoRegistro);
            br = new BufferedReader(fr);
            while (br.ready()) {
                this.leituraRegistro.add(br.readLine());
            }
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
        } catch (IOException e) {
        }
    }

    public int teste() {
        lendo();
        lendoRegistros();
        int validade = 0;
        int i = 0;
        System.out.println(registro);
        for (String registro : this.leituraRegistro) {
            validade = (registro.equals(this.registro)) ? 1 : validade;
        }
            for (String lendo : leitura) {
                String teste[] = lendo.split("Valor: "); // tratamento dos dados extraidos do arquivo para se obter a comparação;
                String nome = teste[0];
                String teste2[] = nome.split("Nome: ");
                nome = teste2[1].replace("||", "");
                String teste3[] = nome.split(";");
                nome = teste3[0];
//                System.out.println(nome);
                validade = (nome.equals(this.nome)) ? 1 : validade;
            }
        return validade;
    }

    private class VendasRelation extends TelaInicial {

        private JPanel jpProdutosMaisVendidos, jpMaioresCompradores,jpVolta;
        private JScrollPane jsGraficoProdutos, jsGraficoVendas;
        private DefaultCategoryDataset graficoProdutos, graficoVendas;
        private List<String> produtosVendidos, produtosNome, produtos10, maioresCompradores; // produtosVendidos:todos os produtos,produtosNome:só os nomes,produtos10:entre os 10 mais vendidos
        private List<Integer> produtosQuantidade; // só os valores em ordem ;
        private List<JLabel> lblProdutosMaisVendidos, lblMaioresCompradores;
        private List<Float> maioresCompras;
        private ImageIcon iconVolta;
        private JButton bntVolta;
        
        public VendasRelation() {
           
            configurarGraficoProdutos();
            configurarPane();
            configurarGraficoVendas();
        }

        private void configurarPane() {
            this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 25));

            jpProdutosMaisVendidos = new JPanel();
            jsGraficoProdutos = new JScrollPane();
            jpMaioresCompradores = new JPanel();
            jsGraficoVendas = new JScrollPane();
            jpVolta = new JPanel();
            iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));

            bntVolta = new JButton(iconVolta);
            
            this.bntVolta.setPreferredSize(new Dimension(60,60));
            this.bntVolta.setBackground(Color.darkGray);
            this.bntVolta.setOpaque(false);
            this.bntVolta.setBorder(null);
            this.bntVolta.setFocusPainted(false);
            this.bntVolta.addActionListener(volta -> voltar(volta));
            
            this.jpVolta.setPreferredSize(new Dimension(61,600));
            this.jpVolta.setBackground(Color.darkGray);
            this.jpVolta.setLayout(new BorderLayout());
            this.jpVolta.add(this.bntVolta,BorderLayout.NORTH);
            this.jpVolta.setOpaque(false);
            
            this.jpProdutosMaisVendidos.setLayout(new BoxLayout(this.jpProdutosMaisVendidos, BoxLayout.Y_AXIS));
            this.jpProdutosMaisVendidos.setBackground(Color.darkGray);
            this.jpProdutosMaisVendidos.setOpaque(false);

            this.jsGraficoProdutos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            this.jsGraficoProdutos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.jsGraficoProdutos.setPreferredSize(new Dimension(250, 200));

            this.jsGraficoProdutos.setOpaque(false);
            this.jsGraficoProdutos.getViewport().setOpaque(false);
            this.jsGraficoProdutos.setViewportView(this.jpProdutosMaisVendidos);
            this.jsGraficoProdutos.setBorder(null);
            
            this.jpProdutosMaisVendidos.add(Box.createVerticalStrut(20));
            this.lblProdutosMaisVendidos.stream().forEach(add -> jpProdutosMaisVendidos.add(add));
            this.painel.add(this.jsGraficoProdutos);

            this.jpMaioresCompradores.setLayout(new BoxLayout(this.jpMaioresCompradores, BoxLayout.Y_AXIS));
            this.jpMaioresCompradores.setBackground(Color.darkGray);
            this.jpMaioresCompradores.setOpaque(false);

            this.jsGraficoVendas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            this.jsGraficoVendas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.jsGraficoVendas.setPreferredSize(new Dimension(250, 200));

            this.jsGraficoVendas.setOpaque(false);
            this.jsGraficoVendas.getViewport().setOpaque(false);
            this.jsGraficoVendas.setViewportView(this.jpMaioresCompradores);
            this.jsGraficoVendas.setBorder(null);

            this.jpShop.removeAll();
            this.jpShop.setPreferredSize(new Dimension(700, 200));
            this.jpShop.setOpaque(true);
            this.jpShop.setVisible(false);

        }

        private void configurarGraficoProdutos() {
            relation();
            int i = 0;
            graficoProdutos = new DefaultCategoryDataset();
            lblProdutosMaisVendidos = new ArrayList<>();

            JFreeChart grafico = ChartFactory.createBarChart3D("PRODUTOS MAIS  VENDIDOS", "", "", graficoProdutos, PlotOrientation.HORIZONTAL, true, true, false);
            var trans = new Color(0xFF, 0xFF, 0xFF, 0);
            grafico.getPlot().setBackgroundPaint(trans);
            grafico.setBackgroundPaint(trans);
            for (Integer produt : produtosQuantidade) {
                this.lblProdutosMaisVendidos.add(new JLabel((i + 1) + "° " + produtos10.get(i) + ": " + produt));
                this.lblProdutosMaisVendidos.get(i).setFont(new Font("Arial Black", Font.BOLD, 14));
                this.lblProdutosMaisVendidos.get(i).setForeground(Color.GREEN);
                graficoProdutos.setValue(produt, produtos10.get(i), "");
                i++;
                if (i == 10) {
                    break;
                }
            }
            ChartPanel panel = new ChartPanel(grafico);
            panel.setPreferredSize(new Dimension(700, 200));
            panel.setOpaque(false);
            this.painel.add(panel);
        }

        private void configurarGraficoVendas() {
            graficoVendas = new DefaultCategoryDataset();
            lblMaioresCompradores = new ArrayList<>();
            int i = maioresCompradores.size();
            int k = 0;
            var trans = new Color(0xFF, 0xFF, 0xFF, 0);
            Iterator<String> f = maioresCompradores.iterator();

            JFreeChart grafico2 = ChartFactory.createBarChart3D("MAIORES CLIENTES", "", "", graficoVendas, PlotOrientation.HORIZONTAL, true, true, false);
            grafico2.getPlot().setBackgroundPaint(trans);
            grafico2.setBackgroundPaint(trans);

            for (String nomes : this.maioresCompradores) {
                i--; // como a lista foi ordenada pelo sort no formato de ordem crescente,eu realizo o inverso para utilzar o formato decrescente
                var lbl = new JLabel((k + 1) + "° " + this.maioresCompradores.get(i) + ": " + "$" + this.maioresCompras.get(i)); // acrescentando ao lbl
                lbl.setFont(new Font("Arial Black", Font.BOLD, 14));
                lbl.setForeground(Color.black);
                this.jpMaioresCompradores.add(lbl);
                graficoVendas.setValue(this.maioresCompras.get(i), this.maioresCompradores.get(i), "");
                k++;
                if (k == 10) {
                    break;
                }
            }
            ChartPanel panel2 = new ChartPanel(grafico2);
            panel2.setPreferredSize(new Dimension(700, 200));
            panel2.setOpaque(false);
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            panel2.add(this.jpVolta);

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
                String separaOsProdutos[] = lendo.split("Produtos:"); // separa os produtos dos demais registro 
                String todosProdutos = separaOsProdutos[1];                        // passa para uma string
                String separaTodosOsProdutos[] = todosProdutos.split(","); // separa todos os produtos 
                produtosPosition = separaTodosOsProdutos.length;        // pega a quantidade de produtos do vetor 
                for (int i = 0; i < produtosPosition; i++) {
                    String produtosEspecificos = separaTodosOsProdutos[i];       // pega todos os produtos separados na linha 
                    String separa[] = produtosEspecificos.split(";");
                    String quantidade = separa[1];
                    String nome = separa[0];
                    produtosVendidos.add(nome);
                    quanto.add(quantidade);
                    nombre.add(nome);
                }
                String valor[] = lendo.split("Valor:"); //1 split para pegar os valores
                String valor1[] = valor[1].split("Produtos:"); // 2 split para pegar os valores
                String valor2 = valor1[0].replace("||", ""); // 3 split para pegar os valores
                String valor3 = valor2.replace(",", "."); // replace para evitar a exception 
                numbers.add(Float.parseFloat(valor3));
                maioresCompras.add(Float.parseFloat(valor3)); // adição a lsita 
                valor2 = valor[0].replace("Nome: ", "");
                valor2 = valor2.replace("||", "");
                compradores.add(valor2);

            }
            List<Integer> h = new ArrayList<>();
            Collections.sort(maioresCompras);
            System.out.println(compradores.size());
            for (Float ma : maioresCompras) {
                for (int i = 0; i < maioresCompras.size(); i++) {
                    k = 0;
                    for (Integer ppo : h) {
                        k += (ppo == i) ? 1 : k;
                    } // testa todos os que ja foram 
                    if (numbers.get(i).equals(ma) && k == 0) {
                        h.add(i); // adiciona a lista de valores para evitar repetição de elementos
                        maioresCompradores.add(compradores.get(i)); ///Adiciona a ordem correta os maiores valores 
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
                    if (this.produtosNome.get(i).equals(produtos)) {
                        resultado += Integer.parseInt(quanto.get(f));
                    }
                    f++;
                }
                if (k <= produtosPosition - 1 && resultado != 0) {
                    produtos10.add(produtosNome.get(i));
                    produtosQuantidade.add(k, resultado);
                    k++;
                }
            }
            for (Integer qua : produtosQuantidade) {
            }

            int j = produtosQuantidade.size() - 1;
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
            if(this.bntVolta.equals(volta.getSource())){
                this.dispose();
                new TelaInicial().setVisible(true);
            }
        }
    }
}
