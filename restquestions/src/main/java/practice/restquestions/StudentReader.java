package practice.restquestions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentReader {
	public static Students[] readStudentsFromFile(String filename) {
		Students[] students = new Students[10];
		int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                students[index++] = new Students(id,name,age);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
