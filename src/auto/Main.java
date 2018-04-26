package auto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {


	public static void main(String[] args) throws IOException {
		//Benign
		File dir = new File("C:\\users\\colbyadmin\\desktop\\applications\\benign");

		File[] files = dir.listFiles();
		
		BufferedWriter error = new BufferedWriter(new FileWriter("D:/ErrorLog.txt"));
		
		for (File f : files) {
			Process p;
			try {
				p = Runtime.getRuntime().exec("java -jar"
						+ " C:\\Users\\colbyadmin\\Desktop\\baksmali.jar d -o "
						+ "D:\\disassembly\\original\\Benign"
						+ File.separator + f.getName() + " " 
						+ "C:\\Users\\colbyAdmin\\desktop\\applications\\benign"
						+ File.separator
						+ f.getName());

				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				// read any errors from the attempted command
				String s;
				while ((s = stdError.readLine()) != null) {
					error.write(s);
					error.write(f.getName());
				}

				p.waitFor();
				p.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//Malware
		Random rand = new Random();

		File dir1 = new File("C:\\users\\colbyadmin\\desktop\\applications\\malware");

		File[] files1 = dir1.listFiles();
		ArrayList<File> list = new ArrayList<File>(Arrays.asList(files1));
		
		while(list.size() > 0) {
		//for (File f : files1) {
			int  n = rand.nextInt(list.size());
			File f = list.get(n);
			list.remove(f);
			Process p;
			try {
				BufferedWriter errorlog = new BufferedWriter(new FileWriter("D:/ErrorFiles/" + f.getName() + "_ErrorLog.txt"));
				p = Runtime.getRuntime().exec("java -jar"
						+ " C:\\Users\\colbyadmin\\Desktop\\baksmali.jar d -o "
						+ "D:\\Disassembly\\original\\malware"
						+ File.separator + f.getName() + " " 
						+ "C:\\Users\\colbyAdmin\\desktop\\applications\\malware"
						+ File.separator
						+ f.getName());

				BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				// read any errors from the attempted command
				String s;
				while ((s = stdError.readLine()) != null) {
					errorlog.write(s);
				}

				p.waitFor();
				p.destroy();
				errorlog.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*for (File f : files) {
			Process p;
	        try {
	        	System.out.println(f.getName() + " started");
	        	p = Runtime.getRuntime().exec("C:\\Users\\colby\\Desktop\\radare2-w32-1.3.0\\rasm2 -a dalvik -d -Bf "
	            + dir.getAbsolutePath()
	            + File.separator + f.getName() + " -O " + outputDir.getAbsolutePath() + File.separator
	            + f.getName() + ".txt");
	            p.waitFor();
	            p.destroy();
	            System.out.println(f.getName() + " done");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		}*/
	}

	private static ArrayList<File> newArrayList(List<File> asList) {
		// TODO Auto-generated method stub
		return null;
	}

}
