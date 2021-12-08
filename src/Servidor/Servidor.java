package Servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread {

    //Atributos do servidor
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;

    //Construtor do servidor
    public Servidor(Socket con) {
        this.con = con;
        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Toda vez que um cliente novo chega ao servidor esse método é acionado e alocado numa Thread e também fica verificando se existe alguma mensagem nova
    public void run() {
        
        try {
            String msg;
            OutputStream ou = this.con.getOutputStream();
            Writer ouw = new OutputStreamWriter(ou);
            BufferedWriter bfw = new BufferedWriter(ouw);
            clientes.add(bfw);
            nome = msg = bfr.readLine();

            while (!"Sair".equalsIgnoreCase(msg) && msg != null) {
                msg = bfr.readLine();
                enviarTodos(bfw, msg);
                System.out.println(msg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Envia a mensagem para todos os clientes no servidor
    public void enviarTodos(BufferedWriter bwSaida, String msg) throws IOException {
        BufferedWriter bwS;
        
        for (BufferedWriter bw : clientes) {
            
            bwS = (BufferedWriter) bw;
            if (!(bwSaida == bwS)) {
                bw.write(nome + " -> " + msg + "\r\n");
                bw.flush();
            }
        }
    }

    //Instâncias do server
    public static void main(String[] args) {
        try {
            int inputPorta = 4030;
            server = new ServerSocket(inputPorta);
            clientes = new ArrayList<BufferedWriter>();

            while (true) {
                System.out.println("Aguardando conexão...");
                Socket con = server.accept();
                System.out.println("Cliente conectado...");
                Thread t = new Servidor(con);
                t.start();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
