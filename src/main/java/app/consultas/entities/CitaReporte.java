/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.transform.Rotate;

/**
 *
 * @author DOxlaj
 */

public class CitaReporte {
    public static ByteArrayOutputStream getPdfFile(){
        Document document = new Document(PageSize.A5.rotate(), 32, 32, 32, 32);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PdfPCell cell;
        Rotate rotation = new Rotate();
        
        try {
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1,3});
            
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD);
            
            // Titulo
            Paragraph title = new Paragraph("Confirmación de Cita", headFont);
            cell = new PdfPCell(title);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPaddingBottom(10);
            titleTable.addCell(cell);
          
            // Detalle
            cell = new PdfPCell(new Phrase("No."));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("1"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fecha de Cita"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("01/01/2020"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Hora de Cita"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("15:00 hrs"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Paciente"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Dennis Florencio Oxlaj García"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Hospital"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Hospital Hermano Pedro"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Clinica"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Pedriatría"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPaddingBottom(10);
            table.addCell(cell);
            
            PdfWriter.getInstance(document, bout);
            
            document.open();
            document.add(titleTable);
            document.add(new Phrase("\n"));
            document.add(table);
            
            document.close();
            
        } catch(DocumentException ex){
            Logger.getLogger(CitaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bout;
    }
}
