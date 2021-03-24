package sample;

import java.io.Serializable;
import java.math.BigInteger;

public class ClePublique implements Serializable{
    BigInteger n;
    BigInteger e;

    public ClePublique(Cles cle) {
        this.n=cle.n;
        this.e=cle.e;
    }
}
