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
    private String fileName;

    public PdfGenerator() throws DocumentException, IOException {
        fileName = "fileName.pdf";
        document = new Document();
        os = new FileOutputStream(fileName);
        PdfWriter.getInstance(document, os);
        document.open();

//        Set basic info about document
        setDefaultFonts();

        closeAndSaveDocument();
    }

    public PdfGenerator(String fileName) throws DocumentException, FileNotFoundException {
        this.fileName = fileName;
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

//        Set basic info about document
        setDefaultFonts();
    }

    public void closeAndSaveDocument() throws IOException {
        document.close();
//        os.close();
    }

    private void setDefaultFonts(){
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 26, BaseColor.BLACK);
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
            Phrase phrase = new Phrase(columnTitle);
            phrase.setFont(fontTitle);
            header.setPhrase(phrase);
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

        Chunk titleChunk = new Chunk(title, fontTitle);

        Phrase phrase = new Phrase();
        phrase.add(titleChunk);

        Paragraph para = new Paragraph();
        para.add(phrase);
        para.setAlignment(Element.ALIGN_CENTER);

        document.add(para);
        document.add(new Chunk());
    }

    public void generateNewDocument(String fileName) throws FileNotFoundException, DocumentException {
        this.fileName = fileName;
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
    }

    public void generateNewDocument() throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
    }

    public void addSummary() throws DocumentException {
        Chunk chunk = new Chunk("Summary: ", font);

        Phrase phrase = new Phrase();
        phrase.add(chunk);

        Paragraph para = new Paragraph();
        para.add(phrase);
        para.setAlignment(Element.ALIGN_RIGHT);

        document.add(para);
        document.add(new Chunk());
    }

    public void addTextAlignRight(String text) throws DocumentException {
        Chunk chunk = new Chunk(text, font);

        Phrase phrase = new Phrase();
        phrase.add(chunk);

        Paragraph para = new Paragraph();
        para.add(phrase);
        para.setAlignment(Element.ALIGN_RIGHT);

        document.add(para);
        document.add(new Chunk());
    }
}
