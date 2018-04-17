package auto;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

	
	public static void main(String[] args) {
		File dir = new File("G:\\My Drive\\Malware-Corpus\\Android\\Infected\\Androzoo");
		File outputDir = new File("G:\\My Drive\\Android Classification\\Disassembly\\Baksmali\\malware");
		File[] files = dir.listFiles();
		
		for (File f : files) {
			Process p;
	        try {
	        	p = Runtime.getRuntime().exec("java -jar"
	        			+ " C:\\Users\\colby\\Desktop\\SCHOOL\\AndroidCT\\Tools\\baksmali-2.2.2.jar d -o "
	            + "D:\\Disassembly\\malware"
	            + File.separator + f.getName() + " " 
	            + "\"G:\\My Drive\\Malware-Corpus\\Android\\Infected\\Androzoo"
	            + File.separator
	            + f.getName() + "\"");
	            
	        	BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        	
	        	// read any errors from the attempted command
	        	String s;
	        	while ((s = stdError.readLine()) != null) {
	        	    System.out.println(s);
	        	}
	        	
	        	p.waitFor();
	            p.destroy();
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

}