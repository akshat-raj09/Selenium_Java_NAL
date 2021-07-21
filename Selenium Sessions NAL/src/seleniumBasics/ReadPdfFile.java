package seleniumBasics;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadPdfFile {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		// create an object of URL & pass the url into it.
		// URL can of: file:// type or http:// type
		URL url = new URL("https://file-examples-com.github.io/uploads/2017/10/file-sample_150kB.pdf");
		
		InputStream in = url.openStream();
		BufferedInputStream fileParse = new BufferedInputStream(in);
		
		PDDocument document = PDDocument.load(fileParse);
		
		String pdfContent = new PDFTextStripper().getText(document);
		
		System.out.println(pdfContent);
		
	}

}
