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

    public Estoque() {
        new InsereProdutos();
    }

    public static List<Integer> getProdutos() {
        return produtos;
    }

    public static void produtos() {
        produtos.add(qtdnutrilus = 2);
        produtos.add(qtdwhiskas = 0);
        produtos.add(qtdcheval = 32);
        produtos.add(qtdsacaMilho = 0);
        produtos.add(qtdmax = 10);
        produtos.add(qtdqueranon = 95);
        produtos.add(qtdampicilina = 32);
        produtos.add(qtdcoleira = 20);
        produtos.add(qtddontral = 60);
        produtos.add(qtdprediderm = 15);
        produtos.add(qtdgranplus = 0);
        produtos.add(qtdracaoExtrusada = 56);
    }

    private static class InsereProdutos<T> extends JFrame {

        private Fundo painel;
        private JSpinner spinner;
        private JComboBox<String> jcTipo;
        private ImageIcon iconPet, iconShiba;
        private JPanel jpAdiciona, jpConfirma;
        private JButton bntConfirm;
        private File arquivo;
        private FileWriter fw;
        private FileReader fr;
        private BufferedReader br;
        private PrintWriter pw;
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
            jpConfirma = new JPanel();

            this.jpAdiciona.setLayout(new FlowLayout(FlowLayout.LEFT, 60, 40));
            this.jpAdiciona.setPreferredSize(new Dimension(490, 400));
            this.jpAdiciona.setBackground(Color.darkGray);

            //this.jpAdiciona.setOpaque(false);
            this.jpConfirma.setPreferredSize(new Dimension(440, 300));
            //this.jpConfirma.setOpaque(false);

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

        private void inserir() throws IOException {
            arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/Estoque.txt");
            int quantidade = Integer.parseInt(this.spinner.getValue().toString());
            int position = jcTipo.getSelectedIndex();
            List<String> leitura = new ArrayList<>();

            if (!arquivo.exists()) {
                System.out.println("VAZIO");
                arquivo.createNewFile();

            }

            fr = new FileReader(arquivo);
            br = new BufferedReader(fr);
            int c = 0;
            while (br.ready()) {
                leitura.add(br.readLine());

            }
            fr.close();
            br.close();

            fw = new FileWriter(arquivo);
            pw = new PrintWriter(fw);


            for (String linha : leitura) {
                c++;
                if (c == position) {
                    String[] linho = linha.split(" ");
                    String teste = "" + linho[0];
                    teste = teste + " " + quantidade;
                    System.out.println(teste);
                    pw.println(teste);
                } else {
                    System.out.println(linha);
                    pw.println(linha);

                }

            }
            fw.close();
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
