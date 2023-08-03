/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
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

        private JPanel jpProdutosVendidos;
        private DefaultCategoryDataset graficoProdutos, graficoVendas;
        private FileReader fr;
        private BufferedReader br;
        private List<String> leitura, produtosVendidos, produtosNome;
        private List<Integer> produtosValores;

        public VendasRelation() {
            configurarPane();
        }

        private void configurarPane() {
            this.jpShop.removeAll();
            this.painel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
            this.jpShop.setPreferredSize(new Dimension(800, 200));
            this.jpShop.setOpaque(true);
            produtosMaisVendidos();
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

        private void configurarGrafico() {
            graficoProdutos = new DefaultCategoryDataset();
            graficoVendas = new DefaultCategoryDataset();

            JFreeChart grafico1 = ChartFactory.createBarChart3D("PRODUTOS VENDIDOS", "", "", graficoProdutos, PlotOrientation.HORIZONTAL, true, true, false);
            JFreeChart grafico2 = ChartFactory.createBarChart3D("MAIORES CLIENTES", "", "", graficoVendas, PlotOrientation.HORIZONTAL, true, true, false);

        }

        private void produtosMaisVendidos() {
            String teste = "";
            String testeq = "";
            String testev = "";
            String nome, quantidade;
            List<Integer> produtosPosition = new ArrayList<>();
            List<String> quanti = new ArrayList<>();
            List<String> nombre = new ArrayList<>();

            produtosNome = new ArrayList<>();
            produtosVendidos = new ArrayList<>();
            produtosValores = new ArrayList<>();

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
                String le[] = lendo.split("Produtos:");
                teste = le[1];
                String du[] = teste.split(",");
                testeq = du[0];
                String mu[] = testeq.split(";");
                nombre.add(mu[0]);
                produtosVendidos.add(teste);
                produtosPosition.add(du.length);
                System.out.println(du.length);
                System.out.println(teste);
                System.out.println(testeq);
                System.out.println(testev + "v");

            }
            int k = 0;
            for (String produtos : this.produtosVendidos) {
                for (int i = 0; i < produtosPosition.get(k); i++) {
                    String separa[] = produtos.split(",");
                    quanti.add(separa[i]);
                }
                k++;
            }
            for (int i = 0; i < this.produtosNome.size(); i++) {
                int l = 0;
                for (String qua : quanti) {
                    String separa[] = qua.split(";");
                    quantidade = separa[1];
                    if(l==12)break;
                    if (nombre.get(l).equals(this.produtosNome.get(i))) {
                        System.out.println(nombre.get(l));
                        this.produtosValores.add(i,Integer.parseInt(quantidade));
                        System.out.println(i);
                    }
                    l++;
                }
            }
            for (Integer des : produtosValores) {
                System.out.println(des + "g");
            }

        }
    }
}
