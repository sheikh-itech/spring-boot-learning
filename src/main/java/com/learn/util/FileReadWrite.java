package com.learn.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

@Service
public class FileReadWrite {

	public String readFile(File file) {
		String body = null;
		String charset = "UTF-8"; // You should determine it based on response header.

		try (InputStream gzippedResponse = new FileInputStream(file);
				Reader reader = new InputStreamReader(gzippedResponse, charset);
				Writer writer = new StringWriter();) {
			char[] buffer = new char[10240];
			for (int length = 0; (length = reader.read(buffer)) > 0;) {
				writer.write(buffer, 0, length);
			}
			body = writer.toString();
			return body;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}
	
	public String zipFileReader(File zipFile) throws Exception {
		String str="";
		
		try (FileInputStream fis = new FileInputStream(zipFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ZipInputStream stream = new ZipInputStream(bis)) {
			
            ZipEntry entry;
            FileOutputStream out=null;
            BufferedOutputStream outStream = null;
            while ((entry = stream.getNextEntry()) != null) {
            	
                Path filePath = Paths.get("files").resolve(entry.getName());
                out = new FileOutputStream(filePath.toFile());
                outStream = new BufferedOutputStream(out);
            	int len;
                
            	while((len=stream.read())>0) {
                	str+=(char)len;
                	outStream.write((char)len);
                }
            	outStream.flush();
            }
            out.close();
		};
		return str;
	}
	
	public void zipFileReader(ZipFile zipFile) throws Exception {
		
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
        FileOutputStream out=null;
        BufferedOutputStream outStream = null;
        
	    while(entries.hasMoreElements()){
	        ZipEntry entry = entries.nextElement();
	        InputStream stream = zipFile.getInputStream(entry);
	        
	        Path filePath = Paths.get("files").resolve(entry.getName());
            out = new FileOutputStream(filePath.toFile());
            outStream = new BufferedOutputStream(out);
	        int len;
	        while((len=stream.read())>0) {
	        	outStream.write((char)len);
	        }
	        outStream.flush();
	    }
	    outStream.close();
	}

	public static void main(String []args) {
		try {
			FileReadWrite reader = new FileReadWrite();
			//String str = reader.zipFileReader(new File("files/files.zip"));
			reader.zipFileReader(new ZipFile(new File("files/files.zip")));
			//System.out.println(str);
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
