package controller;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baseclasses.Cart;
import com.baseclasses.Product;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import dao.CartDAO;
import dao.ProductDAO;

@Component("invoice")
public class CreateInvoice {
	@Autowired
	CartDAO cs;
	@Autowired
	ProductDAO ps;
	public void getInvoice() throws Exception
	{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));

		document.open();
		//Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		final Font SUBFONT = new Font(Font.getFamily("TIMES_ROMAN"), 26,    Font.BOLD|Font.UNDERLINE);
		
		Chunk chunk = new Chunk("Invoice", SUBFONT);
		//chunk.setHorizontalScaling(2.0f);
		Phrase phrase=new Phrase();
		phrase.add(chunk);
		Paragraph para=new Paragraph();
		para.add(phrase);
		para.setAlignment(Element.ALIGN_CENTER);
		document.add(para);
		
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		
		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		addRows(table);
		//addCustomRows(table);

		document.add(table);
		Path path = Paths.get(ClassLoader.getSystemResource("meta.jpg").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		img.scaleAbsolute(200f, 200f);
		img.setAlignment(Element.ALIGN_CENTER);
		document.add(img);
		document.close();
		encrypter();
	}
	private void addTableHeader(PdfPTable table) {
	    Stream.of("S.NO.", "Name", "Description","Qty","Price")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	private void addRows(PdfPTable table) {
		List<Cart> crt=cs.findAll();
		int i=0;
		int t=0;
		for(Cart c:crt) {
			Optional<Product> prodList=ps.findById(c.getPid());
			Product prod=prodList.get();
			i++;
			table.addCell(Integer.valueOf(i).toString());
			table.addCell(prod.getPname());
			table.addCell(prod.getPdes());
			table.addCell(Integer.valueOf(c.getQty()).toString());
			table.addCell(Integer.valueOf(prod.getPrice()).toString());
			t=t+prod.getPrice()*c.getQty();
			
		}
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell("total price");
		table.addCell(Integer.valueOf(t).toString());

	}
	
	
	private void encrypter() throws Exception {
		PdfReader pdfReader = new PdfReader("invoice.pdf");
		PdfStamper pdfStamper 
		  = new PdfStamper(pdfReader, new FileOutputStream("encInvoice.pdf"));

		pdfStamper.setEncryption(
		  "userpass".getBytes(),
		  "ownerpass".getBytes(),
		  0,
		  PdfWriter.ENCRYPTION_AES_256
		);

		pdfStamper.close();
	}
//	private void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
//			    Path path = Paths.get(ClassLoader.getSystemResource("meta.jpg").toURI());
//			    Image img = Image.getInstance(path.toAbsolutePath().toString());
//			    img.scalePercent(10);
//
//			    PdfPCell imageCell = new PdfPCell(img);
//			    table.addCell(imageCell);
//
//			    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//			    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			    table.addCell(horizontalAlignCell);
//
//			    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//			    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//			    table.addCell(verticalAlignCell);
//			    table.addCell("");
//			    table.addCell("");
//			}
}
