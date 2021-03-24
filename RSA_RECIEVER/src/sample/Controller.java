package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    public NombresPremiers Onumero=null;
    public Cles cle =null;
    public BigInteger[] msgChifr;

    @FXML Label etatConnex;
    @FXML TextField numero;
    @FXML Label etat;
    @FXML Label messageDecrypt;

    ServerSocket serverSocket;
    Socket socket;


    public void Connecter() {

        try {
            etatConnex.setText("En attendant le recepteur connecte ... ");
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            etatConnex.setText("Connexion Etabliée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Deconnecter(){
        try {
            socket.close();
            serverSocket.close();
            etatConnex.setText("Connexion Terminée");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void GenererNombresPremiers(){
            int num =Integer.valueOf(numero.getText().toString());
            NombresPremiers numberO=new NombresPremiers(num);
            Onumero=numberO;
            etat.setText(" 2 Nombres premiers générés");
              }



    public void GenererClesPubPriv(){
        Cles clef=new Cles(Onumero);
        this.cle=clef;
        etat.setText("Cle PUBLIC et PRIVE generes");

    }


    public void EnvoyerClePub(){
        try {
            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            ClePublique clepub=new ClePublique(this.cle);

            outputStream.writeObject(clepub);
            etat.setText("Cle publique envoye");


        }catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void RecevoirMsgCrypte(){
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            BigInteger[] a = (BigInteger[])inStream.readObject();
            msgChifr=a;
            etat.setText("Message chiffre Recu");

        }catch(IOException e){
            e.printStackTrace();

        }catch(Exception e){
            System.out.println(e.toString());
        }
    }


    public void Dechiffrer(){
        String result="";
        int[] msgDecrypt=new int[msgChifr.length];
        for(int i=0;i<msgChifr.length;i++){
            //msgCrypt2[i]=BigInteger.valueOf((msg.charAt(i))).modPow(this.cle.e,this.cle.n);
            msgDecrypt[i]=(msgChifr[i].modPow(this.cle.d,this.cle.n)).intValue();
            result+=(char)msgDecrypt[i];
            messageDecrypt.setText(result);
            etat.setText("Dechiffrement terminé");
        }
        /*
        for (int i=0;i<msgDecrypt.length;i++){
            System.out.println(msgDecrypt[i]);
        }
        */
    }






    /*
    public void envoyer(){

        try {

            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            Number t=new Number(Integer.valueOf(numero.getText()));

            outputStream.writeObject(t);

            etatConnex.setText("message crypté envoye");


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void AfficherMsg(){
        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            Number a = (Number) inStream.readObject();
            etatConnex.setText(String.valueOf(a.numero1));


        }catch(IOException e){
            e.printStackTrace();

        }catch(Exception e){
            System.out.println(e.toString());
        }


    }

*/
}
