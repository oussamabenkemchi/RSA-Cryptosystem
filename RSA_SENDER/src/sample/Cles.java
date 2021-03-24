package sample;

import java.math.BigInteger;

public class Cles {
    public BigInteger n;
    public BigInteger PHIpq;
    public BigInteger e;
    public BigInteger d;

    public Cles() {
    }

    public Cles(NombresPremiers nombres) {
        Productpq(nombres);
        ProductPHI(nombres);
        this.e = BigInteger.valueOf(65537);
        GenerateD();

    }

    public void Productpq(NombresPremiers nombres){
        this.n=nombres.numero1.multiply(nombres.numero2);
    }

    public void ProductPHI(NombresPremiers nombres){
        this.PHIpq=nombres.numero1.subtract(BigInteger.valueOf(1)).multiply(nombres.numero2.subtract(BigInteger.valueOf(1)));

    }

    public void GenerateD(){
        this.d=this.e.modInverse(this.PHIpq);

    }



}
