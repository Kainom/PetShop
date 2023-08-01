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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author User
 */
public class Estoque {

    private static List<Integer> produtos = new ArrayList<>();
    private static List<String> nomeProdutos = new ArrayList<>();
    private PrintWriter pw;
    private FileReader fr;

    public Estoque(String insere) {
        new InsereProdutos("Inserção");
    }

    public Estoque() {
    }

    public static List<Integer> getProdutos() {
        return produtos;
    }

    public static List<String> getNomeProdutos() {
        return nomeProdutos;
    }

    public static void produtos() {
        File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Estoque.txt");
        List<String> linha = new ArrayList<>();
        int i = 0;
        produtos.clear();  // limpa a lista para o recebimento de uma nova armazenagem
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                linha.add(br.readLine()); // adiciona a lista linha a leitura 
                //System.out.println(linha.get(i));
                String[] nome = linha.get(i).split(":");
                nomeProdutos.add(nome[0]);
                String[] lendo = linha.get(i).split(":", 2); // split para pegar apenas o valor numérico
                // System.out.println("Leia" + lendo[1]);
                produtos.add(Integer.parseInt(lendo[1])); // adiciona a parte numerica ao produtos //
                if (i == 11) {
                    break;//o arquivo possui 12 linhas e as le assim ,por isso o break em 11 para não gerar exceção                
                }
                i++;
            }

            fr.close();
            br.close();
        } catch (IOException | NumberFormatException err) {
            System.out.println("OPS!");
        }

    }

    public class InsereProdutos extends TelaInicial {

        private DefaultCategoryDataset produtosGrafico;
        private JPanel jpQuantidade;
        private JScrollPane jsPane;
        private JSpinner spinner;
        private JLabel lblQuantidade, lblProdutos;
        private List<JLabel> lblProdutosEstoque;
        private JComboBox<String> jcTipo;
        private List<String> leitura;
        private ImageIcon iconShiba, iconVolta;
        private JButton bntConfirm, bntVolta;
        private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Estoque.txt");
        private PrintWriter pw;
        private FileReader fr;
        private BufferedReader br;

        public InsereProdutos(String insere) {
            super.configurarJanela();
            this.setTitle("ESTOQUE");
            configurarPane();
            this.setVisible(true);
        }

        public InsereProdutos() { // construtor que não chama a parte gráfica,utilizada somente para inserir os produtos debitados da venda

        }

        private void configurarPane() {
            super.configurarPanel();
            jsPane = new JScrollPane();
            jpQuantidade = new JPanel();
            this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));

            this.jpShop.removeAll();
            this.jpShop.setPreferredSize(new Dimension(800, 400));
            this.jpShop.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 32));
            this.jpShop.setBackground(Color.darkGray);
            this.jpShop.setOpaque(false);

            this.jpQuantidade.setLayout(new BoxLayout(this.jpQuantidade, BoxLayout.Y_AXIS));
            this.jpQuantidade.setBackground(Color.darkGray);
            this.jpQuantidade.setOpaque(false);

            this.jsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            this.jsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.jsPane.setPreferredSize(new Dimension(250, 250));

            this.jsPane.setOpaque(false);
            this.jsPane.getViewport().setOpaque(false);
            this.jsPane.setViewportView(this.jpQuantidade);
            this.jsPane.setBorder(null);

            configurarGrafico();
            this.painel.add(this.jsPane);

            this.painel.add(this.jpShop);

            configurarDados();
            this.jpShop.add(this.lblQuantidade);
            this.jpShop.add(this.spinner);

            this.jpShop.add(this.lblProdutos);
            this.jpShop.add(this.jcTipo);

            this.jpShop.add(this.bntVolta);
            this.jpShop.add(this.bntConfirm);

            this.jpQuantidade.add(Box.createVerticalStrut(30));
            for (JLabel produtos : this.lblProdutosEstoque) {
                System.out.println(produtos.getText() + " produt");
                this.jpQuantidade.add(produtos);
            }

            this.bntConfirm.addActionListener(evento -> action(evento));
            this.bntVolta.addActionListener(evento -> action(evento));
        }

        private void configurarGrafico() {
            produtosGrafico = new DefaultCategoryDataset();

            JFreeChart grafico = ChartFactory.createBarChart3D("Quantidade No Estoque", "PRODUTOS", "", produtosGrafico, PlotOrientation.HORIZONTAL, true, true, false);
            grafico.getPlot().setBackgroundPaint(Color.white);
            grafico.getPlot().setOutlineVisible(false);
            var trans = new Color(0xFF, 0xFF, 0xFF, 0);
            grafico.setBackgroundPaint(trans);
            grafico.getPlot().setBackgroundPaint(trans);

            ChartPanel panel = new ChartPanel(grafico);
            panel.setPreferredSize(new Dimension(620, 250));
            panel.setOpaque(false);
            this.painel.add(panel);
        }

        private void configurarDados() {
            iconShiba = new ImageIcon(getClass().getResource("/screnn/shiba.png"));
            iconVolta = new ImageIcon(getClass().getResource("/screnn/volta.png"));
            lblQuantidade = new JLabel("QUANTIDADE");
            lblProdutos = new JLabel("  PRODUTOS  ");
            bntConfirm = new JButton(iconShiba);
            bntVolta = new JButton(iconVolta);
            lblProdutosEstoque = new ArrayList<>();

            this.lblQuantidade.setFont(new Font("Arial Black", Font.BOLD, 14));
            this.lblQuantidade.setForeground(Color.CYAN);

            this.lblProdutos.setFont(new Font("Arial Black", Font.BOLD, 14));
            this.lblProdutos.setForeground(Color.CYAN);

            this.bntConfirm.setPreferredSize(new Dimension(129, 60));
            this.bntConfirm.setBackground(Color.darkGray);
            this.bntConfirm.setOpaque(false);
            this.bntConfirm.setBorder(null);
            this.bntConfirm.setFocusPainted(false);

            this.bntVolta.setPreferredSize(new Dimension(129, 60));
            this.bntVolta.setBackground(Color.darkGray);
            this.bntVolta.setOpaque(false);
            this.bntVolta.setBorder(null);
            this.bntVolta.setFocusPainted(false);

            SpinnerModel value = new SpinnerNumberModel(1, 1, 1000, 1);
            this.spinner = new JSpinner(value);
            this.spinner.setPreferredSize(new Dimension(122, 20));

            this.jcTipo = new JComboBox<>();
            this.jcTipo.addItem("");
            this.jcTipo.addItem("Nutrilus");
            this.jcTipo.addItem("Whiskas");
            this.jcTipo.addItem("Cheval");
            this.jcTipo.addItem("Milho");
            this.jcTipo.addItem("Max");
            this.jcTipo.addItem("Queranon");
            this.jcTipo.addItem("Ampicilina");
            this.jcTipo.addItem("Coleira");
            this.jcTipo.addItem("Dontral");
            this.jcTipo.addItem("Prediderm");
            this.jcTipo.addItem("Granplus");
            this.jcTipo.addItem("Ração Extrusada");
            try {
                lendo();
            } catch (IOException err) {
            }
            int k = 0;

            for (String produto : leitura) {
                System.out.println(k);
                if (k == 12) {
                    break; // evita exceção;
                }
                JLabel lbl = new JLabel(produto);
                String[] lendo = produto.split(":", 2);
                String[] nome = produto.split(":");
                if (Integer.parseInt(lendo[1]) <= 10) {
                    lbl.setForeground(Color.red);
                } else {
                    lbl.setForeground(Color.green);
                }
                produtosGrafico.setValue(Integer.parseInt(lendo[1]), nome[0], "");
                lbl.setFont(new Font("Arial Black", Font.BOLD, 14));
                this.lblProdutosEstoque.add(lbl);
                System.out.println(lbl.getText());
                k++;
            }

        }

        private List<String> lendo() throws FileNotFoundException, IOException {
            leitura = new ArrayList<>();
            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            while (br.ready()) {
                leitura.add(br.readLine());

            }
            fr.close();
            br.close();
            return leitura;

        }

        private void inserir() throws IOException {
            int quantidade = Integer.parseInt(this.spinner.getValue().toString());
            int position = jcTipo.getSelectedIndex();
            int c = 0;

            if (!arquivo.exists()) {
                System.out.println("VAZIO");
                arquivo.createNewFile();

            }
            lendo();
            pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivo)));

            for (String linha : leitura) {
                String[] grafico = linha.split(":");
                String[] valor = linha.split(":", 2);
                produtosGrafico.setValue(Integer.parseInt(valor[1]), grafico[0], "");
                c++; // começa incrementando por causa da posição  0 do check estar vazia;
                if (c == position) { // confere quando produto  foi selecionado para alterá-lo
                    String[] produto = linha.split(":"); //split que pega apenas o nome do produto 
                    String[] lendo = linha.split(":", 2);
                    quantidade += Integer.parseInt(lendo[1]);
                    String novoProduto = produto[0] + ":" + quantidade; // armazena o nome e a nova  quantidade
                    //System.out.println(novoProduto);
                    pw.println(novoProduto);
                } else { // reescreve os produtos que não foram alterados 
                    //System.out.println(linha);
                    pw.println(linha);

                }

            }

            pw.close();

        }

        public void inserir(List<Integer> position, List<Integer> quantidade) throws IOException {
            if (!arquivo.exists()) {
                System.out.println("VAZIO");
                arquivo.createNewFile();

            }
            lendo();
            pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivo)));
            int c = 0;
            System.out.println("Quantidade" + quantidade.size());
            int k = 0;
            for (String linha : leitura) {
                for (int f = 0; f < position.size(); f++) {
                    if (c == position.get(f)) { // confere quando produto  foi selecionado para alterá-lo
                        System.out.print(f + " ");
                        String[] produto = linha.split(":"); //split que pega apenas o nome do produto 
                        String novoProduto = produto[0] + ":" + quantidade.get(f); // armazena o nome e a nova  quantidade
                        pw.println(novoProduto);
                        k = 0;
                        break;
                    } else {
                        k = 1;
                    }
                }
                if (k == 1) {
                    pw.println(linha);
                }
                c++;
            }
            pw.close();
        }

        private void action(ActionEvent evento) {
            int k = 0;
            if (!this.jcTipo.getSelectedItem().equals("") && evento.getSource().equals(this.bntConfirm)) {
                try {
                    System.out.println("opa");
                    inserir(); // insere os valores novos no estoque e nos lbls de demonstração
                    lendo();
                    for (String linha : leitura) {
                        if (k == 12)break;  // a leitura por ler do arquivo acaba por possuir 12 index de elemento,desse modo,o break evita execption
                        this.lblProdutosEstoque.get(k).setText(linha);
                        k++;
                        String[] grafico = linha.split(":");
                        String[] valor = linha.split(":", 2);
                        produtosGrafico.setValue(Integer.parseInt(valor[1]), grafico[0], "");
                    }
                } catch (IOException err) {
                }
            } else if (evento.getSource().equals(this.bntVolta)) {
                new TelaInicial().setVisible(true);
                this.dispose();
            }
        }

    }

}
