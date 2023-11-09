package radio.client.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class TextHandler {
    private Scanner sn;
    private String PATH = "./lib/preferedLanguage.txt";   

    public void abreArquivo(){
        try{
            sn = new Scanner(new File(PATH));
        } catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }

        }
    
    public String leArquivo(){
        String lang = "";
        try{
            while(sn.hasNext()){
                lang = sn.nextLine();
            }
        } catch(Exception e){
            System.out.println("Erro ao ler o arquivo!");
        }

        return lang;
    }

    public void fechaArquivo(){
        if (sn != null){
            sn.close();
        }
    }

    public void escreveArquivo(String conteudo){
        try{
            FileWriter fw = new FileWriter(PATH);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(conteudo);
            bw.close();
            fw.close();
        } catch(Exception e){
            System.out.println("Erro ao escrever no arquivo!");
        }
    }
}
