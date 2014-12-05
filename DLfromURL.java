package DL_tools;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DLfromURL {
	
	/**
	 * written to save a zip file, but works with other file types.
	 *  
	 * @param zipURL - String version of the URL at which the file is located
	 * @param outfile - String name for the filename
	 */
	public static void save_Zip(String zipURL,String outfile){
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		
		try {
			URL url = new URL(zipURL);
		    rbc = Channels.newChannel(url.openStream());
		    fos = new FileOutputStream(outfile);
		    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
		catch (IOException e){
			System.out.println(e.toString());
		}
		finally{
			close(rbc);
			close(fos);
		}
	}
	/**
	 * This saves a zip from a URL using save_Zip, decompresses "subfile" (writing it to a file).
	 * Might be expensive time-wise, as it downloads the whole zip and copies the file in question
	 * using InputStream/OutputStream.
	 * 
	 * For more direct access with less reading/writing to disk, use read_one_from_zip. 
	 * 
	 * @param zipURL - URL from which you would like to download a zip file
	 * @param subfile - Name of the file within the zip
	 */
	public static void save_one_from_Zip(String zipURL, String subfile){
		String r = String.valueOf(Math.random()).substring(2,6);
		String temp_filename = "temp_zip_from_URL_"+r+".zip";
		save_Zip(zipURL,temp_filename);
		ZipFile zf = null;
		FileOutputStream fos = null;
		try {
			zf = new ZipFile (temp_filename);
			ZipEntry E = zf.getEntry(subfile);
			InputStream in = zf.getInputStream(E);
			fos = new FileOutputStream(subfile);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
			    fos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(zf);
			File F = new File(temp_filename);
			if(F.delete())
				System.out.println("Temporary Zip Deleted");
		}
		
	}
	
	//Implement "close" function that will not throw exceptions;
	public static void close(Closeable c) {
		if (c == null) return; 
			try {
				c.close();
			} catch (IOException e) {
				System.out.println(e.toString());
	    }
	}
	

}

