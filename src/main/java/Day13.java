import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay13");
        Scanner scanner = new Scanner(file);
        long tokens = 0;
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            int aX = Integer.parseInt(a.substring(12, 14));
            int aY = Integer.parseInt(a.substring(18));
            String b = scanner.nextLine();
            int bX = Integer.parseInt(b.substring(12, 14));
            int bY = Integer.parseInt(b.substring(18));
            String p = scanner.nextLine();
            int pX = Integer.parseInt(p.substring(9, p.indexOf(',')));
            int pY = Integer.parseInt(p.substring(p.lastIndexOf('=') + 1));
            scanner.nextLine();
            for (int i = 0; i < pX / aX + 1; i++) {
                int x = 0;
                int y = 0;
                int c = 0;
                int d = 0;
                for (int j = 0; x < pX; j++) {
                    if (j < i) {
                        x += aX;
                        y += aY;
                        d++;
                    } else {
                        x += bX;
                        y += bY;
                        c++;
                    }
                }
                //System.out.println(x + " " + pX + " " + y + " " + pY);
                if (x == pX && y == pY) {
                    //System.out.println(c + d * 3);
                    tokens += c + d * 3;
                    i = pX / aX;
                }
            }
        }
        System.out.println(tokens);
        scanner.close();
    }
}