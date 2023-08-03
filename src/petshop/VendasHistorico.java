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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
            int produtosPosition;
            String todosProdutos = "";
            String produtosEspecificos = "";
            String nome, quantidade;
            List<String> produT = new ArrayList<>();

            List<Integer> valores = new ArrayList<>();
            List<String> quanto = new ArrayList<>();
            Set<String> nombre = new HashSet<>();
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
                String separaOsProdutos[] = lendo.split("Produtos:"); // separa os produtos dos demais registro 
                todosProdutos = separaOsProdutos[1];                        // passa para uma string
                String separaTodosOsProdutos[] = todosProdutos.split(","); // separa todos os produtos 
                produtosPosition = separaTodosOsProdutos.length;        // pega a quantidade de produtos do vetor 
                for (int i = 0; i < produtosPosition; i++) {
                    produtosEspecificos = separaTodosOsProdutos[i];       // pega todos os produtos separados na linha 
                    String separa[] = produtosEspecificos.split(";");
                    quantidade = separa[1];
                    nome = separa[0];
                    produtosVendidos.add(nome);
                    quanto.add(quantidade);
                    nombre.add(nome);

                }

//                System.out.println(todosProdutos);
//                System.out.println(produtosEspecificos);
            }
            int k = 0;
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
                    produT.add(produtosNome.get(i));
                    valores.add(k, resultado);

                    k++;
                }
            }
for (Integer qua : valores) {
                System.out.println(qua);
            }

            
            k = 0;
            int j = valores.size() - 1;
            for (int n = 0; n <=j; n++) {
                for (int i = 0; i <= j; i++) {
                    if (valores.get(n) < valores.get(j - i) && valores.get(n) != valores.get(j - i)) {
                        System.out.print(n + " ");
                        String aux = produT.get(n);
                        int aux2 = valores.get(j - i);
                        produT.add(i, produT.get(j - i));
                        produT.add(j - i, aux);
                        valores.add(j - i,valores.get(n));
                        valores.add(n, aux2);
                        
                    } 
                }
            }
            for (String qua : produT) {
                System.out.println(qua);
            }
            
//            }
//            for (int i = 0; i < this.produtosNome.size(); i++) {
//                int l = 0;
//                for (String qua : quanti) {
//                    String separa[] = qua.split(";");
//                    quantidade = separa[1];
//                    if(l==12)break;
//                    if (nombre.get(l).equals(this.produtosNome.get(i))) {
//                        this.produtosValores.add(i,Integer.parseInt(quantidade));
//                    }
//                    l++;
//                }
//            }
        }
    }
}
