import dao.FileHandler;
import java.util.Scanner;
import view.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        FileHandler arquivo = new FileHandler();
        
        Menu menuzinho = new Menu();
        menuzinho.mostrarMenu(sc, arquivo);
        sc.close();
    }
}