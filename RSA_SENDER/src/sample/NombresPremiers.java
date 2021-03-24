package sample;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;

public class NombresPremiers implements Serializable {
    BigInteger numero1;
    BigInteger numero2;

    public NombresPremiers() {
    }

    public NombresPremiers(int a) {
        this.numero1=NombrePremier(a);

        this.numero2=NombrePremier(a);
    }

    public NombresPremiers(BigInteger numero1) {
        this.numero1 = numero1;
    }

    public NombresPremiers(BigInteger numero1, BigInteger numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public BigInteger NombrePremier(int  numero){
        SecureRandom rnd=new SecureRandom();
        BigInteger num2=BigInteger.probablePrime(numero,rnd);
        return num2;
    }



}
