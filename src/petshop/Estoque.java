/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author User
 */
public class Estoque {

    private static List<Integer> produtos = new ArrayList<>();
    private static Integer qtdampicilina, qtdcoleira, qtddontral, qtdgranplus, qtdprediderm,
            qtdracaoExtrusada, qtdcheval, qtdmax, qtdnutrilus, qtdqueranon, qtdsacaMilho, qtdwhiskas;
    private PrintWriter pw;
    private FileReader fr;

    public Estoque() {
        new InsereProdutos();
    }

    public static List<Integer> getProdutos() {
        return produtos;
    }

    public static void produtos() {
        File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/Estoque.txt");
        List<String> linha = new ArrayList<>();
        int i = 0;
        produtos.clear();  // limpa a lista para o recebimento de uma nova armazenagem
        try {
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                linha.add(br.readLine()); // adiciona a lista linha a leitura 
                System.out.println(linha.get(i));
                String[] lendo = linha.get(i).split(":", 2); // split para pegar apenas o valor numérico
                System.out.println(lendo[1]);
                produtos.add(Integer.parseInt(lendo[1])); // adiciona a parte numerica ao produtos //
                i++;
            }

            fr.close();
            br.close();
        } catch (Exception err) {
            System.out.println("OPS!");
        }

        
    }

    private class InsereProdutos<T> extends JFrame {  

        private Fundo painel;
        private JSpinner spinner;
        private JComboBox<String> jcTipo;
        private List<String> leitura;
        private ImageIcon iconPet, iconShiba;
        private JPanel jpAdiciona, jpVolta;
        private JButton bntConfirm;
        private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/Estoque.txt");
        private PrintWriter pw;
        private FileReader fr;
        private BufferedReader br;

        public InsereProdutos() {
            configurarJanela();
            configurarPanel();
            this.setVisible(true);

        }

        private void configurarJanela() {
            iconPet = new ImageIcon(getClass().getResource("/imagens/dog.jpg"));
            painel = new Fundo();
            painel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
            this.add(painel);
            this.setTitle("ESTOQUE");
            this.setSize(1000, 500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setLocationRelativeTo(this);

        }

        private void configurarPanel() {
            jpAdiciona = new JPanel();
            jpVolta = new JPanel();

            this.jpAdiciona.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 40));
            this.jpAdiciona.setPreferredSize(new Dimension(490, 400));
            this.jpAdiciona.setBackground(Color.darkGray);

            this.jpAdiciona.setOpaque(false);
            this.jpVolta.setPreferredSize(new Dimension(300, 300));
            this.jpVolta.setOpaque(false);

            this.painel.add(this.jpAdiciona);

            configurarDados();
            this.jpAdiciona.add(this.spinner);
            this.jpAdiciona.add(this.jcTipo);
            this.jpAdiciona.add(this.bntConfirm);

            this.bntConfirm.addActionListener(action -> armazena(action));

        }

        private void configurarDados() {
            iconShiba = new ImageIcon(getClass().getResource("/imagens/shiba.jpg"));

            bntConfirm = new JButton(iconShiba);
            this.bntConfirm.setPreferredSize(new Dimension(50, 50));
            this.bntConfirm.setBackground(Color.darkGray);

            SpinnerModel value = new SpinnerNumberModel(1, 1, 100, 1);
            this.spinner = new JSpinner(value);
            this.spinner.setPreferredSize(new Dimension(120, 20));

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
                c++;
                if (c == position) { // confere quando produto  foi selecionado para alterá-lo
                    String[] produto = linha.split(":"); //split que pega apenas o nome do produto 
                    String novoProduto = produto[0] + ":" + quantidade; // armazena o nome e a nova  quantidade
                    System.out.println(novoProduto);
                    pw.println(novoProduto);
                } else { // reescreve os produtos que não foram alterados 
                    System.out.println(linha);
                    pw.println(linha);

                }

            }

            pw.close();

        }

        private void armazena(ActionEvent action) {
            if (!this.jcTipo.getSelectedItem().equals("")) {
                try {
                    System.out.println("opa");
                    inserir();
                } catch (IOException err) {
                }
            }
        }

        protected class Fundo extends javax.swing.JPanel {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // extendo de painel para utilizar o comando super // 
                Image img = iconPet.getImage();
                g.drawImage(img, 0, 0, this);
            }

        }
    }

}
