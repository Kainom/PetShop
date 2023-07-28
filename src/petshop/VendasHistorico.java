/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petshop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class VendasHistorico {

    private String nome, registro, cep, bairro, rua, num, complemento, valor;
    private String cliente;
    private File arquivo = new File("C:/Users/User/Documents/NetBeansProjects/PetShop/src/arquivos/Vendas.txt");
    private PrintWriter pw;
    private FileReader fr;

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

            this.cliente = "Nome: " + nome + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Complemento: " + complemento + " " + "Valor: " + valor+ "||" + produtos;
        } else {
            this.cliente = "Nome: " + nome + "||" + "Cep: " + cep + "||" + "Bairro: " + bairro + "||" + "Rua: " + rua + "||" + "Num: " + num + "||" + "Valor: " + valor+ "||" + produtos;

        }
        armazena();

    }

    public VendasHistorico(String nome, String Registro, String valor, String produtos) {
        this.nome = nome;
        this.registro = Registro;
        this.valor = valor;
        this.cliente = "Nome: " + nome + "||" + registro + "||" + "Valor: " + valor+ "||" + produtos;
        armazena();

    }

    public VendasHistorico(String nome, String valor, String produtos) {
        this.nome = nome;
        this.cliente = "Nome: " + nome + "||" + "Valor: " + valor+ "||" + produtos;
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
}
