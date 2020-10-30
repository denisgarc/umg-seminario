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
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD);
            Font headTableFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
            Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD);
            headFont.setColor(BaseColor.WHITE);
            headTableFont.setColor(BaseColor.WHITE);
            
            // Titulo
            PdfPTable titleTable = new PdfPTable(1);
            titleTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Ficha Médica", title));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setPaddingBottom(10);
            titleTable.addCell(cell);
            
            // Titulo Paciente
            PdfPTable titlePacienteTable = new PdfPTable(1);
            titlePacienteTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Datos del Paciente", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
            titlePacienteTable.addCell(cell);
            
            // Datos del Paciente
            PdfPTable tablePaciente = new PdfPTable(4);
            tablePaciente.setWidthPercentage(100);
            tablePaciente.setWidths(new int[]{2,3,2,3});
            
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
            
            cell = new PdfPCell(new Phrase(paciente.getNumeroSeguro()));
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
            
            // Encabezado de Citas
            PdfPTable titleConsultaTable = new PdfPTable(1);
            titleConsultaTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Consultas", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
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

            cell = new PdfPCell(new Phrase("Tipo de Consulta", headTableFont));
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
            
            // Encabezado de dianosticos
            PdfPTable titleDiagnosticoTable = new PdfPTable(1);
            titleDiagnosticoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Diagnosticos", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
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
            
            // Encabezado de tratamientos
            PdfPTable titleTratamientoTable = new PdfPTable(1);
            titleTratamientoTable.setWidthPercentage(100);
            
            cell = new PdfPCell(new Paragraph("Tratamientos", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
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
            
            cell = new PdfPCell(new Paragraph("Medicamentos", headFont));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(Rectangle.BOTTOM);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setPaddingBottom(10);
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
            
            PdfWriter.getInstance(document, bout);
            
            document.open();
            document.add(titleTable);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titlePacienteTable);
            document.add(tablePaciente);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleConsultaTable);
            document.add(tableConsulta);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleDiagnosticoTable);
            document.add(tableDiagnostico);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleTratamientoTable);
            document.add(tableTratamiento);
            document.add(new Phrase("\n", normalFont));
            
            document.add(titleMedicamentoTable);
            document.add(tableMedicamento);
            
            document.close();
            
        } catch(DocumentException ex){
            Logger.getLogger(CitaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bout;
    }
}
