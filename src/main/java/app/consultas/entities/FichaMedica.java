/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import app.consultas.util.DateHandler;
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

/**
 *
 * @author DOxlaj
 */
public class FichaMedica {
    public static ByteArrayOutputStream getFichaMedica(Paciente paciente){
        Document document = new Document(PageSize.LETTER, 10, 10, 10, 10);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PdfPCell cell;
        
        try {
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            
            PdfPTable titlePacienteTable = new PdfPTable(1);
            titlePacienteTable.setWidthPercentage(100);
            
            PdfPTable tablePaciente = new PdfPTable(4);
            tablePaciente.setWidthPercentage(100);
            tablePaciente.setWidths(new int[]{2,3,2,3});
            
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD);
            
            // Titulo
            cell = new PdfPCell(new Paragraph("Ficha Médica", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPaddingBottom(10);
            titleTable.addCell(cell);
            
            // Titulo Paciente
            cell = new PdfPCell(new Paragraph("Datos del Paciente", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPaddingBottom(10);
            titlePacienteTable.addCell(cell);
            
            // Datos del Paciente
            // 1
            cell = new PdfPCell(new Phrase("Nombre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getNomberCompleto()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fuma:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getFuma()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            //2
            cell = new PdfPCell(new Phrase("No. Seguro:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getNumeroSeguro().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tipo de Sangre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getTipoSangre()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            // 3
            cell = new PdfPCell(new Phrase("Fecha de Alta:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(paciente.getFecAlta(), "dd/MM/yyyy")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Contacto:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            if(paciente.getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(paciente.getIdPersonaContacto().getNomberCompleto()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            //cell.setPaddingBottom(10);
            tablePaciente.addCell(cell);
            
            
            PdfWriter.getInstance(document, bout);
            
            document.open();
            document.add(titleTable);
            document.add(new Phrase("\n"));
            document.add(titlePacienteTable);
            document.add(new Phrase("\n"));
            document.add(tablePaciente);
            
            document.close();
            
        } catch(DocumentException ex){
            Logger.getLogger(CitaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bout;
    }
}
