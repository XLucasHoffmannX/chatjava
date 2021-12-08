package Servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente  {
    
    //Atributos do Cliente
    private Socket socket;
    private OutputStream ou;
    private Writer ouw;
    private BufferedWriter bfw;
    private String inputIP;
    private int inputPorta;
    private String inputNome;

    //Construtor do cliente
    public Cliente() throws IOException {
        Scanner scan = new Scanner(System.in);
        inputIP = "127.0.0.1";
        inputPorta = 12345;
        System.out.println("Digite o nome de usuário");
        inputNome = scan.nextLine();
        System.out.println("");
    }

    //Método para conectar no server
    public void conectar() throws IOException {
        socket = new Socket("127.0.0.1", 12345);
        ou = socket.getOutputStream();
        ouw = new OutputStreamWriter(ou);
        bfw = new BufferedWriter(ouw);
        bfw.write(inputNome + "\r\n");
        bfw.flush();
    }

    //Método que envia mensagens para o server
    public void enviarMensagem(String msg) throws IOException {

        if (msg.equals("Sair")) {
            bfw.write("Desconectado \r\n");
        } else {
            bfw.write(msg + "\r\n");
        }
        bfw.flush();
    }

    //Método que recebe as mensagens do server
    public void escutar() throws IOException {

        Scanner scan = new Scanner(System.in);

        InputStream in = socket.getInputStream();
        InputStreamReader inr = new InputStreamReader(in);
        
        String msg = "";

        int delay = 5000;   // tempo de espera antes da 1ª execução da tarefa.
        int interval = 1000;  // intervalo no qual a tarefa será executada.
        Timer timer = new Timer();

        while (!"Sair".equalsIgnoreCase(msg)) {
            
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    try {
                        String msg;
                        BufferedReader bfr = new BufferedReader(inr);
                        if (bfr.ready()) {
                            msg = bfr.readLine();
                            if (msg.equals("Sair")) {
                                System.out.println("Servidor off");
                            } else {
                                System.out.println(msg);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, delay, interval);

            this.enviarMensagem(scan.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
        Cliente app = new Cliente();
        app.conectar();
        app.escutar();
    }
}
