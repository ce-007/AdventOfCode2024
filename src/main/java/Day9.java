import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputDay9");
        Scanner scanner = new Scanner(file);
        String num = scanner.nextLine();
        List<String> fileSystem = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < num.length(); i++) {
            int size = Integer.parseInt(String.valueOf(num.charAt(i)));
            if (i % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    fileSystem.add(String.valueOf(k));
                }
                k++;
            } else {
                for (int j = 0; j < size; j++) {
                    fileSystem.add(String.valueOf('.'));
                }
            }
        }
        for (int i = 0; i < fileSystem.size(); i++) {
            if (fileSystem.get(i).contains(".")) {
                fileSystem.set(i, fileSystem.getLast());
                fileSystem.removeLast();
                i--;
            }
        }
        Long total = 0L;
        for (int i = 0; i < fileSystem.size(); i++) {
            total += Long.parseLong(fileSystem.get(i)) * i;
        }
        System.out.println(total);
    }
}
