import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay13");
        Scanner scanner = new Scanner(file);
        long tokens = 0;
        while (scanner.hasNextLine()) {
            String a = scanner.nextLine();
            long aX = Integer.parseInt(a.substring(12, 14));
            long aY = Integer.parseInt(a.substring(18));
            String b = scanner.nextLine();
            long bX = Integer.parseInt(b.substring(12, 14));
            long bY = Integer.parseInt(b.substring(18));
            String p = scanner.nextLine();
            long pX = Integer.parseInt(p.substring(9, p.indexOf(','))) + 10000000000000L;
            long pY = Integer.parseInt(p.substring(p.lastIndexOf('=') + 1)) + 10000000000000L;
            scanner.nextLine();

            long delta = aX * bY - aY * bX;

            if (delta == 0) {
                continue;
            }

            long deltaI = pX * bY - pY * bX;
            long deltaJ = aX * pY - aY * pX;

            if (deltaI % delta != 0 || deltaJ % delta != 0) {
                continue;
            }

            long i = deltaI / delta;
            long j = deltaJ / delta;

            if (i >= 0 && j >= 0) {
                tokens += j + i * 3;
            }
        }
        System.out.println(tokens);
        scanner.close();
    }
}