package comp3702.practical;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class modify {
	public static void main(String[] args) {
		System.out.println("input format:");
		System.out.println("<inputFileName> "
				+ "<outputFileName> <letterToRemove>");
		String inputFileName, 
		outputFileName;
		char letterToRemove;
		
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String line = sc.nextLine();
			String[] tokens = line.split(" ");
			inputFileName = tokens[0];
			outputFileName = tokens[1];
			letterToRemove = tokens[2].toCharArray()[0];
		}
		finally {
			sc.close();
		}
		
		try {
			File file = 
					new File("C:\\Users\\mariu\\git\\COMP3702\\src\\comp3702\\practical\\" + outputFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(
					new FileReader("C:\\Users\\mariu\\git\\COMP3702\\src\\comp3702\\practical\\" + inputFileName));
			String text = "";
			String line;
			while ((line = br.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == letterToRemove) {
						line = line.substring(0, i) + 
								line.substring(i + 1);
					}
				}
				text += line + "\n";
			}
			bw.write(text);
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
