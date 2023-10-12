/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import app.consultas.util.DateHandler;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DOxlaj
 */
public class FichaMedica {
    public static ByteArrayOutputStream getFichaMedica(Paciente paciente) throws IOException{
        Document document = new Document(PageSize.LETTER, 10, 10, 10, 10);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PdfPCell cell;
        
        try {
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL);
            Font headFont = FontFactory.getFont(FontFactory.getFont("Calibri").getFamilyname(), 14, Font.NORMAL);
            Font headTableFont = FontFactory.getFont(FontFactory.getFont("Calibri").getFamilyname(), 12, Font.BOLD);
            Font title = FontFactory.getFont(FontFactory.getFont("Calibri").getFamilyname(), 16, Font.BOLD);
            Font headerTitleFont = FontFactory.getFont(FontFactory.getFont("Calibri").getFamilyname(), 18, Font.BOLD);
            Font subHeaderTitleFont = FontFactory.getFont(FontFactory.getFont("Calibri").getFamilyname(), 15, Font.BOLD);
            headFont.setColor(BaseColor.WHITE);
            headerTitleFont.setColor(new BaseColor(144, 71, 153));
            subHeaderTitleFont.setColor(new BaseColor(96, 180, 210));
            
            // Encabezado
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("ASOCIACION GRUPO ERMITA", headerTitleFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("ALZHEIMER DE GUATEMALA", subHeaderTitleFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPaddingBottom(10);
            headerTable.addCell(cell);
            
            // Titulo
            PdfPTable titleTable = new PdfPTable(2);
            titleTable.setWidthPercentage(100);
            titleTable.setWidths(new int[]{1, 7});
            
            String imagepath = "/media/denox/D2DCA259DCA2381D/Proyectos/Universidad/Seminario/umg-seminario/src/main/webapp/images/logo.png";
            Image img = Image.getInstance(imagepath);
            img.setAbsolutePosition(0f, 0f);
            cell.addElement(img);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(1);
            cell.setPaddingBottom(5);
            titleTable.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("Ficha Médica", title));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleTable.addCell(cell);
            
            // Titulo Paciente
            PdfPTable titlePacienteTable = new PdfPTable(1);
            titlePacienteTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Datos del Paciente", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titlePacienteTable.addCell(cell);
            
            PdfPTable tablePaciente = new PdfPTable(4);
            tablePaciente.setWidthPercentage(100);
            tablePaciente.setWidths(new int[]{3,3,3,3});
            
            // 1
            cell = new PdfPCell(new Phrase("Nombres:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getNombres()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Apellidos:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getApellidos()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getIdTipoDocumento().getAbreviatura() + ":"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getDocumentoId()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tipo de Sangre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getTipoSangre()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fecha de Nacimiento:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(paciente.getIdPersona().getFecNacimiento(), "dd/MM/yyyy")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Género:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getSexo()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            // 3
            cell = new PdfPCell(new Phrase("Fecha de Alta:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(paciente.getFecAlta(), "dd/MM/yyyy")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Dirección:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(paciente.getIdPersona().getDireccion()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablePaciente.addCell(cell);
            
            // Titulo Contacto
            PdfPTable titleContactoTable = new PdfPTable(1);
            titleContactoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Datos del Familiar", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleContactoTable.addCell(cell);
            
            PdfPTable tableContacto = new PdfPTable(4);
            tableContacto.setWidthPercentage(100);
            tableContacto.setWidths(new int[]{3,3,3,3});
            
            cell = new PdfPCell(new Phrase("Nombre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            if(paciente.getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(paciente.getIdPersonaContacto().getNombres()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Apellidos:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            if(paciente.getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(paciente.getIdPersonaContacto().getApellidos()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Teléfonos:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            if(paciente.getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(paciente.getIdPersonaContacto().getTelefonos()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tableContacto.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Dirección:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            if(paciente.getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(paciente.getIdPersonaContacto().getDireccion()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tableContacto.addCell(cell);
            
            // Encabezado de Citas
            PdfPTable titleConsultaTable = new PdfPTable(1);
            titleConsultaTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Consultas", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleConsultaTable.addCell(cell);
            
            // Para las consultas
            PdfPTable tableConsulta = new PdfPTable(3);
            tableConsulta.setWidthPercentage(100);
            tableConsulta.setWidths(new int[]{2,3,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableConsulta.addCell(cell);

            cell = new PdfPCell(new Phrase("Motivo de Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableConsulta.addCell(cell);

            cell = new PdfPCell(new Phrase("Médico Que Atendió", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableConsulta.addCell(cell);
           
            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableConsulta.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getIdTipoConsulta().getDescripcion()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableConsulta.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getIdEmpleado().getIdPersona().getNomberCompleto()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableConsulta.addCell(cell);
                }
            }
            
            // Encabezado de Motivos de Consultas
            PdfPTable titleDescripcionTable = new PdfPTable(1);
            titleDescripcionTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Motivos de Consultas", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleDescripcionTable.addCell(cell);
            
            // Para las Observaciones
            PdfPTable tableDescripcion = new PdfPTable(2);
            tableDescripcion.setWidthPercentage(100);
            tableDescripcion.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableDescripcion.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableDescripcion.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableDescripcion.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getDescripcion()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableDescripcion.addCell(cell);
                }
            }
            
            // Encabezado de Observaciones
            PdfPTable titleObservacionesTable = new PdfPTable(1);
            titleObservacionesTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Antecedentes de Enfermedad Actual", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleObservacionesTable.addCell(cell);
            
            // Para las Observaciones
            PdfPTable tableObservaciones = new PdfPTable(2);
            tableObservaciones.setWidthPercentage(100);
            tableObservaciones.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableObservaciones.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableObservaciones.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableObservaciones.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getObservaciones()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableObservaciones.addCell(cell);
                }
            }
            
            // Encabezado de Antecedentes
            PdfPTable titleAntecedentesTable = new PdfPTable(1);
            titleAntecedentesTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Antecedentes de Interés", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleAntecedentesTable.addCell(cell);
            
            // Para las Observaciones
            PdfPTable tableAntecedentes = new PdfPTable(2);
            tableAntecedentes.setWidthPercentage(100);
            tableAntecedentes.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableAntecedentes.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableAntecedentes.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableAntecedentes.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getAntecedentes()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableAntecedentes.addCell(cell);
                }
            }
            
            // Encabezado de Anamnesis
            PdfPTable titleAnamnesisTable = new PdfPTable(1);
            titleAnamnesisTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Anamnesis y Exploraciones Físicas", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleAnamnesisTable.addCell(cell);
            
            // Para las Anamnesis
            PdfPTable tableAnamnesis = new PdfPTable(2);
            tableAnamnesis.setWidthPercentage(100);
            tableAnamnesis.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableAnamnesis.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableAnamnesis.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableAnamnesis.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getAnamnesis()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableAnamnesis.addCell(cell);
                }
            }
            
            // Encabezado de Examenes Complementarios
            PdfPTable titleExamenesComplementariosTable = new PdfPTable(1);
            titleExamenesComplementariosTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Exámenes Complementarios", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleExamenesComplementariosTable.addCell(cell);
            
            // Para las Examenes Complementarios
            PdfPTable tableExamenesComplementarios = new PdfPTable(2);
            tableExamenesComplementarios.setWidthPercentage(100);
            tableExamenesComplementarios.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableExamenesComplementarios.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableExamenesComplementarios.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableExamenesComplementarios.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getExamenesComplementarios()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableExamenesComplementarios.addCell(cell);
                }
            }
            
            // Encabezado de dianosticos
            PdfPTable titleDiagnosticoTable = new PdfPTable(1);
            titleDiagnosticoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Diagnosticos", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleDiagnosticoTable.addCell(cell);
            
            // Para los diagnosticos
            PdfPTable tableDiagnostico = new PdfPTable(3);
            tableDiagnostico.setWidthPercentage(100);
            tableDiagnostico.setWidths(new int[]{2,3,3});
            
            cell = new PdfPCell(new Phrase("Fecha Diagnostico", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableDiagnostico.addCell(cell);

            cell = new PdfPCell(new Phrase("Tipo de Diagnostico", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableDiagnostico.addCell(cell);

            cell = new PdfPCell(new Phrase("Detalle", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableDiagnostico.addCell(cell);
            
            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);
                    for(Integer iDiagnostico = 0; iDiagnostico < consult.getConsultaDiagnosticoList().size(); iDiagnostico++){
                        ConsultaDiagnostico diagnostico = consult.getConsultaDiagnosticoList().get(iDiagnostico);
                        
                        cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableDiagnostico.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(diagnostico.getIdTipoDiagnostico().getDescripcion()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableDiagnostico.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(diagnostico.getDescripcion()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableDiagnostico.addCell(cell);
                    }
                }
            }
            
            // Encabezado de Evaluaciones Clinicas
            PdfPTable titleEvaluacionClinicaTable = new PdfPTable(1);
            titleEvaluacionClinicaTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Exámenes Complementarios", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleEvaluacionClinicaTable.addCell(cell);
            
            // Para las Evaluaciones Clinicas
            PdfPTable tableEvaluacionClinica = new PdfPTable(2);
            tableEvaluacionClinica.setWidthPercentage(100);
            tableEvaluacionClinica.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableEvaluacionClinica.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableEvaluacionClinica.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableEvaluacionClinica.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getExamenesComplementarios()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableEvaluacionClinica.addCell(cell);
                }
            }
            
            // Encabezado de Ordenes Médicas
            PdfPTable titleOrdenesMedicasTable = new PdfPTable(1);
            titleOrdenesMedicasTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Exámenes Complementarios", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleOrdenesMedicasTable.addCell(cell);
            
            // Para las Examenes Complementarios
            PdfPTable tableOrdenesMedicas = new PdfPTable(2);
            tableOrdenesMedicas.setWidthPercentage(100);
            tableOrdenesMedicas.setWidths(new int[]{1,3});
            
            cell = new PdfPCell(new Phrase("Fecha Consulta", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableOrdenesMedicas.addCell(cell);

            cell = new PdfPCell(new Phrase("Descripción", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableOrdenesMedicas.addCell(cell);

            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableOrdenesMedicas.addCell(cell);

                    cell = new PdfPCell(new Phrase(consult.getExamenesComplementarios()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(Rectangle.NO_BORDER);
                    tableOrdenesMedicas.addCell(cell);
                }
            }
            
            // Encabezado de tratamientos
            PdfPTable titleTratamientoTable = new PdfPTable(1);
            titleTratamientoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Tratamientos", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleTratamientoTable.addCell(cell);
            
            // Para los Tratamientos
            PdfPTable tableTratamiento = new PdfPTable(3);
            tableTratamiento.setWidthPercentage(100);
            tableTratamiento.setWidths(new int[]{2,3,3});
            
            cell = new PdfPCell(new Phrase("Fecha Tratamiento", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableTratamiento.addCell(cell);

            cell = new PdfPCell(new Phrase("Tipo de Tratamiento", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableTratamiento.addCell(cell);

            cell = new PdfPCell(new Phrase("Detalle", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableTratamiento.addCell(cell);
            
            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);
                    for(Integer iTratamiento = 0; iTratamiento < consult.getConsultaTratamientoList().size(); iTratamiento++){
                        ConsultaTratamiento tratamiento = consult.getConsultaTratamientoList().get(iTratamiento);
                        
                        cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consult.getFechaConsulta(),"dd/MM/yyyy")));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableTratamiento.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(tratamiento.getIdTipoTratamiento().getDescripcion()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableTratamiento.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase(tratamiento.getDescripcion()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorder(Rectangle.NO_BORDER);
                        tableTratamiento.addCell(cell);
                    }
                }
            }
            
            // Encabezado para medicamentos
            PdfPTable titleMedicamentoTable = new PdfPTable(1);
            titleMedicamentoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Medicamentos Recetados", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleMedicamentoTable.addCell(cell);
            
            // Para los Tratamientos
            PdfPTable tableMedicamento = new PdfPTable(3);
            tableMedicamento.setWidthPercentage(100);
            tableMedicamento.setWidths(new int[]{2,3,3});
            
            cell = new PdfPCell(new Phrase("Fecha Vencimiento", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableMedicamento.addCell(cell);

            cell = new PdfPCell(new Phrase("Medicamento", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableMedicamento.addCell(cell);

            cell = new PdfPCell(new Phrase("Indicaciones", headTableFont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableMedicamento.addCell(cell);
            
            for(Integer iCita = 0; iCita < paciente.getCitaList().size(); iCita++){
                Cita cita = paciente.getCitaList().get(iCita);
                for(Integer iConsulta = 0; iConsulta < cita.getConsultaList().size(); iConsulta++){
                    Consulta consult = cita.getConsultaList().get(iConsulta);
                    for(Integer iReceta = 0; iReceta < consult.getRecetaList().size(); iReceta++){
                        Receta receta = consult.getRecetaList().get(iReceta);
                        for(Integer iMedicamento = 0; iMedicamento < receta.getRecetaDetalleList().size(); iMedicamento ++){
                            RecetaDetalle detalle = receta.getRecetaDetalleList().get(iMedicamento);
                            
                            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(receta.getFechaVencimiento(),"dd/MM/yyyy")));
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setBorder(Rectangle.NO_BORDER);
                            tableMedicamento.addCell(cell);

                            cell = new PdfPCell(new Phrase(detalle.getMedicamento().getNombreComercial()));
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setBorder(Rectangle.NO_BORDER);
                            tableMedicamento.addCell(cell);

                            cell = new PdfPCell(new Phrase( detalle.getCantidad().toString() + " " + detalle.getIndicaciones() + " " + detalle.getDuracion()));
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setBorder(Rectangle.NO_BORDER);
                            tableMedicamento.addCell(cell);
                        }
                    }
                }
            }
            
            // Pie de Página
            PdfPTable footerTable = new PdfPTable(1);
            footerTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("\"SI TE OLVIDO NO ME OLVIDES\"", title));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            footerTable.addCell(cell);
            
            PdfWriter.getInstance(document, bout);
            
            document.open();
            document.add(headerTable);
            
            document.add(titleTable);
            
            document.add(titlePacienteTable);
            document.add(tablePaciente);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleContactoTable);
            document.add(tableContacto);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleConsultaTable);
            document.add(tableConsulta);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleDescripcionTable);
            document.add(tableDescripcion);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleObservacionesTable);
            document.add(tableObservaciones);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleAntecedentesTable);
            document.add(tableAntecedentes);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleAnamnesisTable);
            document.add(tableAnamnesis);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleExamenesComplementariosTable);
            document.add(tableExamenesComplementarios);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleDiagnosticoTable);
            document.add(tableDiagnostico);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleEvaluacionClinicaTable);
            document.add(tableEvaluacionClinica);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleOrdenesMedicasTable);
            document.add(tableOrdenesMedicas);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleTratamientoTable);
            document.add(tableTratamiento);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleMedicamentoTable);
            document.add(tableMedicamento);
            document.add(new Phrase("\n\n", normalFont));
            
            document.add(footerTable);
            
            document.close();
            
        } catch(DocumentException ex){
            Logger.getLogger(CitaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bout;
    }
}
