package productions.darthplagueis.googlenowfeed.api;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Token {

    public static String findAPiKey(){

        String apiKey = "api_token.txt";
        File file = new File(apiKey);
        System.out.println(file.getAbsolutePath());

        try {
            Scanner scanner = new Scanner(file);
            apiKey = scanner.next();
            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return apiKey;
    }
}
