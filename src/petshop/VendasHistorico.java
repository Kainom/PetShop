/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

    private String nome, registro, cep, bairro, rua, num, complemento, valor;
    private String cliente;
    private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Vendas.txt");
    private PrintWriter pw;

    public VendasHistorico() {
        new VendasRelation().setVisible(true);
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
            this.cliente = "Nome: " + nome + "||" + "Registro: " + registro + " ||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Complemento: " + complemento + "||" + "Valor: " + valor + "||" + produtos;
        } else {
            this.cliente = "Nome: " + nome + "|| " + "Registro: " + registro + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Valor: " + valor + "||" + produtos;

        }
        armazena();

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

    }

    public VendasHistorico(String nome, String valor, String produtos) {
        this.nome = nome;
        this.cliente = "Nome: " + nome + "||" + "Valor: " + valor + "||" + produtos;
        armazena();
    }

    private void armazena() {
        try {
            if (!arquivo.exists()) {
                System.out.println("CRIANDO");
                arquivo.createNewFile();
            }

            pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivo, true)));
            pw.println(this.cliente);
            pw.close();
        } catch (IOException ex) {
            System.out.println("ERRO NO ARQUIVO");
        }

    }

    private class VendasRelation extends TelaInicial {

        private JPanel jpProdutosMaisVendidos, jpMaioresCompradores;
        private JScrollPane jsGraficoProdutos, jsGraficoVendas;
        private DefaultCategoryDataset graficoProdutos, graficoVendas;
        private FileReader fr;
        private BufferedReader br;
        private List<String> leitura, produtosVendidos, produtosNome, produtos10; // leitura:linha toda,produtosVendidos:todos os produtos,produtosNome:só os nomes,produtos10:entre os 10 mais vendidos
        private List<Integer> produtosQuantidade; // só os valores em ordem ;
        private List<JLabel> lblProdutosMaisVendidos, lblMaioresCompradores;
        private List<Float> maioresCompras;
        private Set<String> maioresCompradores;

        public VendasRelation() {
            configurarGraficoProdutos();
            configurarPane();
            configurarGraficoVendas();
        }

        private void configurarPane() {
                        this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 25));

            jpProdutosMaisVendidos = new JPanel();
            jsGraficoProdutos = new JScrollPane();
            jpMaioresCompradores = new JPanel();
            jsGraficoVendas = new JScrollPane();

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

        private void lendo() {
            try {
                leitura = new ArrayList<>();
                fr = new FileReader(arquivo);
                br = new BufferedReader(fr);
                while (br.ready()) {
                    leitura.add(br.readLine());
                }
            } catch (IOException e) {
            }
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
            relation();
            graficoVendas = new DefaultCategoryDataset();
            lblMaioresCompradores = new ArrayList<>();
            int i = maioresCompradores.size();
            int k = 0;
            var trans = new Color(0xFF, 0xFF, 0xFF, 0);
            Iterator<String> f = maioresCompradores.iterator();

            JFreeChart grafico2 = ChartFactory.createBarChart3D("MAIORES CLIENTES", "", "", graficoVendas, PlotOrientation.HORIZONTAL, true, true, false);
            grafico2.getPlot().setBackgroundPaint(trans);
            grafico2.setBackgroundPaint(trans);

            while (f.hasNext()) {
                i--;
                String nomes = f.next();
                var lbl = new JLabel((k + 1) + "° " + nomes + ": " + "$" + this.maioresCompras.get(i));
                lbl.setFont(new Font("Arial Black", Font.BOLD, 14));
                lbl.setForeground(Color.black);
                this.jpMaioresCompradores.add(lbl);
                graficoVendas.setValue(this.maioresCompras.get(i), nomes, "");
                k++;
                if (k == 10) {
                    break;
                }
            }
            ChartPanel panel2 = new ChartPanel(grafico2);
            panel2.setPreferredSize(new Dimension(700, 200));
            panel2.setOpaque(false);
            this.painel.add(panel2);
            this.painel.add(this.jsGraficoVendas);

        }

        private void relation() {
            int k = 0;
            int produtosPosition;
            maioresCompradores = new HashSet<>();
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
                // System.out.println(valor3);

            }
            Collections.sort(maioresCompras);

            for (Float ma : maioresCompras) {
                for (int i = k; i < maioresCompras.size(); i++) {
                    if (numbers.get(i).equals(ma)) {
                        k++;
                        System.out.println(i);
                        maioresCompradores.add(compradores.get(i));
                    }
                }
            }

            maioresCompradores.stream().forEach(i -> System.out.println(i));
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
    }
}
