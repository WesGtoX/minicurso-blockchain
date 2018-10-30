/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicursoblockchain;
//https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/gson-2.6.2.jar
/**
 *
 * @author Wesley
 */
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class MinicursoBlockchain {

    public static ArrayList<Bloco> blockchain = new ArrayList<Bloco>();
    public static int dificuldade = 5;
    public static void main(String[] args) {
        
        //First part
//        Bloco genesisBlock = new Bloco("Primeiro bloco", "0"); //hash = 0
//        System.out.println("Hash do bloco 1: " + genesisBlock.hash);
                
        //Second part
        blockchain.add(new Bloco("Primeiro bloco", "0"));
        System.out.println("Minerando bloco 1...");
        blockchain.get(0).minerarBloco(dificuldade);
        
        blockchain.add(new Bloco("Segundo bloco", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Minerando bloco 2...");
        blockchain.get(1).minerarBloco(dificuldade);
        
        System.out.println("\nValidade do blockchain: " + validarBlockchain());
        
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("A blockchain: ");
        System.out.println(blockchainJson);
    }
    
    public static Boolean validarBlockchain() {
        Bloco blocoAtual;
        Bloco blocoAnterior;
        String hashTarget = new String(new char[dificuldade]).replace('\0', '0');
        
        //loop through blockchain to check hashes
        for (int i=1; i < blockchain.size(); i++) {
            blocoAtual = blockchain.get(i);
            blocoAnterior = blockchain.get(i-1);
            
            //compare previus hash and calculated hash
            if(!blocoAnterior.hash.equals(blocoAtual.calcularHash())){
                System.out.println("Hashes atuais não são iguais");
                return false;
            }
            
            //compare previus hash and hash registered in blockchain
            if(!blocoAnterior.hash.equals(blocoAtual.hashAnterior)){
                System.out.println("Hashes anteriores não são iguais");
                return false;
            }
            
            //Check if the hash has already been mined
            if(!blocoAtual.hash.substring(0, dificuldade).equals(hashTarget)){
                System.out.println("O bloco não foi minerado");
                return false;
            }
        }
        return true;
    }
}