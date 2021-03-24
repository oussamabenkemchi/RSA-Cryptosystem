package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Controller {
    public ClePublique cle=null;
    BigInteger[] msgCrypt=null;

    @FXML Label etat;
    @FXML Label etatConnex;
    @FXML TextField text;
    @FXML Label AfficherClePub;



    Socket socket;

    public void Connecter(){
        try {
            etatConnex.setText("Tentative de connexion");
            socket = new Socket(InetAddress.getLocalHost(), 5000);
            etatConnex.setText("Connexion Etabliée");

        }catch (SocketException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void Deconnecter() {
        try {
            socket.close();

            etatConnex.setText("connexion Terminée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void RecevoirClePub() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            ClePublique a = (ClePublique) inStream.readObject();
            this.cle=a;
            etat.setText("Cle public recu");
            //AfficherClePub.setText(String.valueOf(a.n));


        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }



    public void Chiffrer(){
           String msg = text.getText().toString();
           char[] array=new char[msg.length()];

           BigInteger[] msgCrypt2=new BigInteger[msg.length()];


            for(int i=0;i<msg.length();i++){
                msgCrypt2[i]=BigInteger.valueOf((msg.charAt(i))).modPow(this.cle.e,this.cle.n);
            }

        this.msgCrypt=msgCrypt2;
            etat.setText("Message Chifre");
            /*
        for(int i=0;i<msg.length();i++){
            System.out.println(this.msgCrypt[i]+"   ");
        }

            */

    }



    public void envoyer() {

        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            BigInteger[] bi=this.msgCrypt;
            //NombresPremiers t = new NombresPremiers(Integer.valueOf(text.getText()));

            outputStream.writeObject(bi);

            etat.setText("message chifre envoye");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}