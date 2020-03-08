package com.doeveryday.doeverydaybudgetmanager.pdfgenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
public class PdfGenerator {

    private Document document;
    private Font font;
    private Font fontTitle;
    private FileOutputStream os;

    public PdfGenerator() throws DocumentException, IOException {
        document = new Document();
        os = new FileOutputStream("fileName.pdf");
        PdfWriter.getInstance(document, os);
        document.open();

//        Set basic info about document
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 26, BaseColor.BLACK);

        closeAndSaveDocument();
    }

    public PdfGenerator(String fileName) throws DocumentException, FileNotFoundException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

//        Set basic info about document
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
    }

    public void closeAndSaveDocument() throws IOException {
        document.close();
//        os.close();
    }

    public void addTextToDocument(String text) throws DocumentException {
        document.add(new Chunk(text, font));
    }

    public void addTable(List<String> headers, List<String> rows) throws DocumentException {
        PdfPTable table = new PdfPTable(headers.size());
        addTableHeader(table, headers);
        addRows(table, rows);

        document.add(table);
    }

    private void addTableHeader(PdfPTable table, List<String> headers) {
        headers.forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<String> rows) {
        for (String row :
                rows) {
            table.addCell(row);
        }
    }

    public void setTitle(String title) throws DocumentException {
        document.addTitle(title);

        Chunk titleChunk = new Chunk(title, font);

        Phrase phrase = new Phrase();
        phrase.add(title);

        Paragraph para = new Paragraph();
        para.add(phrase);
        para.setAlignment(Element.ALIGN_CENTER);

        document.add(para);
        document.add(new Chunk());
    }
}
