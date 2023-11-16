import java.util.Scanner;
import br.com.devrmv.programs.Game;
import br.com.devrmv.programs.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(Main.class);
        Scanner sc = new Scanner(System.in);
        int option = 0;

        System.out.println("Bem vindo ao AT de Fundamentos de Java!");
        System.out.print("\nO que deseja?\n1-Jogar\n2-Ver relatório\nOpcão: ");
        try {
            option = sc.nextInt();
            LOGGER.debug("Foi inserido um numero inteiro nas opcoes");
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
        sc.nextLine();
        switch (option) {
            case 1: {
                Game game = new Game();
                game.run();
                break;
            }
            case 2: {
                Report report =new Report();
                report.run();
                break;
            }
            default: {
                LOGGER.debug("O switch foi percorrido sem entrar nas opcoes 1 ou 2");
            }
        }
    }
}