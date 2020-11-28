package com.example;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        String command = args[0];

        if (command.equals("search")) {
            if (args.length != 3) {
                System.out.println("Invalid Arguments");
            }

            String pattern = args[1];
            String file = args[2];

            search(pattern, file);

        } else {
            System.out.println("Unknown Command");
        }
    }

    // searches lines containg the string "pattern" in file with path "file"
    public static void search(String word, String filePath) {
        InputStream inputStream = readFile(filePath);

        if(inputStream == null){
            System.out.println("Cannot Read File");
            return;
        }

        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(word))
                    System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns inputstream of file or null if file does not exists or cannot be read.
    private static InputStream readFile(String fileString) {
        File file = new File(fileString);

        if(file.exists()){
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                return null;
            }
        }

        return null;
    }
}
