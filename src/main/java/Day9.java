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
        List<List<String>> fileSystem = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < num.length(); i++) {
            int size = Integer.parseInt(String.valueOf(num.charAt(i)));
            List<String> localSystem = new ArrayList<>();
            if (i % 2 == 0) {
                for (int j = 0; j < size; j++) {
                    localSystem.add(String.valueOf(k));
                }
                k++;
            } else {
                for (int j = 0; j < size; j++) {
                    localSystem.add(String.valueOf('.'));
                }
            }
            fileSystem.add(localSystem);
        }
        for (int i = fileSystem.size() - 1; i > -1; i--) {
            for (int j = 0; j < fileSystem.size(); j++) {
                if (fileSystem.get(j).contains(".") && !fileSystem.get(i).isEmpty() && !fileSystem.get(i).contains(".") && !fileSystem.get(j).isEmpty() && j < i) {
                    if (fileSystem.get(j).size() >= fileSystem.get(i).size()) {
                        List<String> dots = new ArrayList<>();
                        List<String> dots2 = new ArrayList<>();
                        for (int l = 0; l < fileSystem.get(j).size() - fileSystem.get(i).size(); l++) {
                            dots.add(".");
                        }
                        fileSystem.set(j, fileSystem.get(i));
                        for (int l = 0; l < fileSystem.get(j).size(); l++) {
                            dots2.add(".");
                        }
                        fileSystem.set(i, dots2);
                        fileSystem.add(j + 1, dots);
                        j--;
                    }
                }
                if (j > 0) {
                    if (fileSystem.get(j).contains(".") && fileSystem.get(j - 1).contains(".")) {
                        for (int l = 0; l < fileSystem.get(j - 1).size(); l++) {
                            fileSystem.get(j).add(".");
                        }
                        fileSystem.remove(j - 1);
                    }
                }
            }
        }
        Long total = 0L;
        k = 0;
        for (int i = 0; i < fileSystem.size(); i++) {
            for (int j = 0; j < fileSystem.get(i).size(); j++) {
                if (!fileSystem.get(i).contains(".")) {
                    total += Long.parseLong(fileSystem.get(i).get(j)) * k;
                }
                k++;
            }
        }
        System.out.println(total);
    }
}
