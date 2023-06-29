package Projeto;
import javax.swing.SwingUtilities;

import java.io.IOException;
import java.util.Scanner;

public class main{

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o começo do path para o arquivo do projeto (ex: c:/Users/lucca/): ");
        String path = scanner.nextLine();
        
		//Abas de ações
		SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                try {
					new GuiaComAbas(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		/* Imagem
		Imagem asfa = new Imagem();
		asfa.Mob();
		*/
		scanner.close();
	}
}



