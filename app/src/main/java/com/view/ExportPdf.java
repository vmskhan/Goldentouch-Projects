package com.view;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.entities.Question;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.util.Report;

public class ExportPdf {

	public static ByteArrayInputStream reportsReport(List<Report> reports) {
		System.out.println("reportsreport method called");
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(255, 0, 0, 0));

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(80);
			table.setWidths(new int[] { 4, 4, 4, 4, 4 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
	

			hcell = new PdfPCell(new Phrase("QUESTION", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CORRECT ANSWER", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("YOUR CHOICE", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("STATUS", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("MARK", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(hcell);
			int i=1;
			for (Report report : reports) {

				PdfPCell cell;


				cell = new PdfPCell(new Phrase(report.getQuestion().getQuestionText()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(report.getQuestion().getAnswerText())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getChoice())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getState())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getMark())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			Paragraph para=new Paragraph("SUBMISSION",blueFont);
			para.setSpacingAfter(50);
			//document.add(new Paragraph("Submission",blueFont));
			document.add(para);
			document.add(table);
			
			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	public static byte[] toMail(List<Report> reports) {
		System.out.println("tomail method called");
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(255, 0, 0, 0));

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(80);
			table.setWidths(new int[] { 4, 4, 4, 4, 4 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("QUESTION", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GRAY);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("CORRECT ANSWER", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("YOUR CHOICE", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("STATUS", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GRAY);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("MARK", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.GRAY);
			table.addCell(hcell);
			int i=1;
			for (Report report : reports) {

				PdfPCell cell;


				cell = new PdfPCell(new Phrase(report.getQuestion().getQuestionText()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(report.getQuestion().getAnswerText())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getChoice())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getState())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(report.getSubmission().getMark())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			Paragraph para=new Paragraph("SUBMISSION",blueFont);
			para.setSpacingAfter(50);
			document.add(para);
			document.add(table);

			document.close();

		} catch (DocumentException ex) {

		}

		return out.toByteArray();
	}

}
