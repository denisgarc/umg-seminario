/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.consultas.entities;

import app.consultas.util.DateHandler;
import com.itextpdf.text.BadElementException;
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
public class ConsultaImpresion {
    public static ByteArrayOutputStream getResume(Consulta consulta) throws BadElementException, IOException{
        Document document = new Document(PageSize.LETTER, 20, 20, 20, 20);
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
            //cell.setPaddingBottom(10);
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
            
            cell = new PdfPCell(new Paragraph("Resumen Consulta Médica", title));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleTable.addCell(cell);
            
            // Titulo Consulta
            PdfPTable titleConsultaTable = new PdfPTable(1);
            titleConsultaTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Datos de la Consulta", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleConsultaTable.addCell(cell);
            
            // Datos de la consulta
            PdfPTable tableConsulta = new PdfPTable(4);
            tableConsulta.setWidthPercentage(100);
            tableConsulta.setWidths(new int[]{2,3,2,3});
            
            // 0
            cell = new PdfPCell(new Phrase("Código de Consulta"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdConsulta().toString()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Fecha de Consulta:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consulta.getFechaConsulta(), "dd/MM/yyyy")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            // 2
            cell = new PdfPCell(new Phrase("Médico:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdEmpleado().getIdPersona().getNomberCompleto()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Motivo de Consulta:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableConsulta.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdTipoConsulta().getDescripcion()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tableConsulta.addCell(cell);
            
            // Titulo Paciente
            PdfPTable titlePacienteTable = new PdfPTable(1);
            titlePacienteTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("1. Datos de Identificación", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titlePacienteTable.addCell(cell);
            
            PdfPTable tablePaciente = new PdfPTable(4);
            tablePaciente.setWidthPercentage(100);
            tablePaciente.setWidths(new int[]{2,3,2,3});
            
            // 1
            cell = new PdfPCell(new Phrase("Nombres:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersona().getNombres()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Apellidos:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersona().getApellidos()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("DPI:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersona().getDocumentoId()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Tipo de Sangre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tablePaciente.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getTipoSangre()));
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
            
            cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(consulta.getIdCita().getIdPaciente().getFecAlta(), "dd/MM/yyyy")));
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
            
            cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersona().getDireccion()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tablePaciente.addCell(cell);
            
            // Titulo Contacto
            PdfPTable titleContactoTable = new PdfPTable(1);
            titleContactoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("1.1 Datos del Familiar", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleContactoTable.addCell(cell);
            
            PdfPTable tableContacto = new PdfPTable(4);
            tableContacto.setWidthPercentage(100);
            tableContacto.setWidths(new int[]{2,3,2,3});
            
            cell = new PdfPCell(new Phrase("Nombre:"));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            tableContacto.addCell(cell);
            
            if(consulta.getIdCita().getIdPaciente().getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersonaContacto().getNombres()));
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
            
            if(consulta.getIdCita().getIdPaciente().getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersonaContacto().getApellidos()));
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
            
            if(consulta.getIdCita().getIdPaciente().getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersonaContacto().getTelefonos()));
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
            
            if(consulta.getIdCita().getIdPaciente().getIdPersonaContacto() != null){
                cell = new PdfPCell(new Phrase(consulta.getIdCita().getIdPaciente().getIdPersonaContacto().getDireccion()));
            } else {
                cell = new PdfPCell(new Phrase(""));
            }
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            tableContacto.addCell(cell);
            
            // Descripcion
            PdfPTable titleDescripcionTable = new PdfPTable(1);
            titleDescripcionTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("2. Motivo de la Consulta", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleDescripcionTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getDescripcion()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleDescripcionTable.addCell(cell);
            
            // Observaciones
            PdfPTable titleObservacionTable = new PdfPTable(1);
            titleObservacionTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("3. Antecedentes de Enfermedad Actual", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleObservacionTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getObservaciones()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleObservacionTable.addCell(cell);
            
            // Antecedentes de Interes
            PdfPTable titleAntecedenteTable = new PdfPTable(1);
            titleAntecedenteTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("4. Antecedentes de Interés Como Hábitos Tóxicos, Fisiológicos, Enfermedades de Infancia, Heredofamiliares, etc.", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleAntecedenteTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getAntecedentes()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleAntecedenteTable.addCell(cell);
            
            // Anamnesis
            PdfPTable titleAnamnesisTable = new PdfPTable(1);
            titleAnamnesisTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("5. Anamnesis y Exploración Física", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleAnamnesisTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getAnamnesis()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleAnamnesisTable.addCell(cell);
            
            // Examenes complementarios
            PdfPTable titleExamenesComplementariosTable = new PdfPTable(1);
            titleExamenesComplementariosTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("6. Exámenes Complementarios de Laboratorio", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleExamenesComplementariosTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getExamenesComplementarios()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleExamenesComplementariosTable.addCell(cell);
            
            // Encabezado de dianosticos
            PdfPTable titleDiagnosticoTable = new PdfPTable(1);
            titleDiagnosticoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("7. Diagnóstico", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleDiagnosticoTable.addCell(cell);
            
            // Para los diagnosticos
            PdfPTable tableDiagnostico = new PdfPTable(2);
            tableDiagnostico.setWidthPercentage(100);
            tableDiagnostico.setWidths(new int[]{2,4});
            
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
            
            for(Integer iDiagnostico = 0; iDiagnostico < consulta.getConsultaDiagnosticoList().size(); iDiagnostico++){
                ConsultaDiagnostico diagnostico = consulta.getConsultaDiagnosticoList().get(iDiagnostico);

                cell = new PdfPCell(new Phrase(diagnostico.getIdTipoDiagnostico().getDescripcion()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                tableDiagnostico.addCell(cell);

                cell = new PdfPCell(new Phrase(diagnostico.getDescripcion()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                tableDiagnostico.addCell(cell);
            }
            
            // Examenes complementarios
            PdfPTable titleEvaluacionClinicaTable = new PdfPTable(1);
            titleEvaluacionClinicaTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("8. Datos Sobre Evaluación Clínica de la Enfermedad", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleEvaluacionClinicaTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getEvaluacionClinica()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleEvaluacionClinicaTable.addCell(cell);
            
            // Ordenes Medicas
            PdfPTable titleOrdenesMedicasTable = new PdfPTable(1);
            titleOrdenesMedicasTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("9. Ordenes médicas", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleOrdenesMedicasTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase(consulta.getOrdenesMedicas()));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            titleOrdenesMedicasTable.addCell(cell);
            
            // Encabezado de tratamientos
            PdfPTable titleTratamientoTable = new PdfPTable(1);
            titleTratamientoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("10. Tratamiento Farmacológico", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setBackgroundColor(new BaseColor(144, 71, 153));
            cell.setPadding(5);
            titleTratamientoTable.addCell(cell);
            
            // Para los Tratamientos
            PdfPTable tableTratamiento = new PdfPTable(2);
            tableTratamiento.setWidthPercentage(100);
            tableTratamiento.setWidths(new int[]{2,4});
            
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
            
            for(Integer iTratamiento = 0; iTratamiento < consulta.getConsultaTratamientoList().size(); iTratamiento++){
                ConsultaTratamiento tratamiento = consulta.getConsultaTratamientoList().get(iTratamiento);

                cell = new PdfPCell(new Phrase(tratamiento.getIdTipoTratamiento().getDescripcion()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(Rectangle.NO_BORDER);
                tableTratamiento.addCell(cell);

                cell = new PdfPCell(new Phrase(tratamiento.getDescripcion()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
                tableTratamiento.addCell(cell);
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
            
            for(Integer iReceta = 0; iReceta < consulta.getRecetaList().size(); iReceta++){
                Receta receta = consulta.getRecetaList().get(iReceta);
                for(Integer iMedicamento = 0; iMedicamento < receta.getRecetaDetalleList().size(); iMedicamento ++){
                    RecetaDetalle detalle = receta.getRecetaDetalleList().get(iMedicamento);

                    cell = new PdfPCell(new Phrase(new DateHandler().getStringFromDate(receta.getFechaVencimiento(),"dd/MM/yyyy")));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
            
            //document.add(titleConsultaTable);
            document.add(tableConsulta);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titlePacienteTable);
            document.add(tablePaciente);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleContactoTable);
            document.add(tableContacto);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleDescripcionTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleObservacionTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleAntecedenteTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleAnamnesisTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleExamenesComplementariosTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleDiagnosticoTable);
            document.add(tableDiagnostico);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleEvaluacionClinicaTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleOrdenesMedicasTable);
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
