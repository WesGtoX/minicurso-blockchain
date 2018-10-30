/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicursoblockchain;

/**
 *
 * @author Wesley
 */
import java.util.Date;

public class Bloco {
    
    public String hash; //save the digital signature
    public String hashAnterior;
    private String dados; 
    private long timeStamp; //system date and time
    private int nonce; //unique number of the block
    
    //Construtor
    
    public Bloco (String dados, String hashAnterior){
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.timeStamp = new Date().getTime();
        this.hash = calcularHash();
    }
    
    public String calcularHash(){

        //First part
//        String hashCalculado = StringUtil.applySha256(
//        hashAnterior + Long.toString(timeStamp) + dados);
//        return hashCalculado;

        String hashCalculado = StringUtil.applySha256(
        hashAnterior + Long.toString(timeStamp) + Integer.toString(nonce) + dados);
        return hashCalculado;
    }
    
    public void minerarBloco( int dificuldade ){
        String target = new String(new char[dificuldade]).replace('\0','0');
        while(!hash.substring(0, dificuldade).equals(target)) {
            nonce++;
            hash = calcularHash();
        }
        System.out.println("Bloco minerado: " + hash);
    }
    
}