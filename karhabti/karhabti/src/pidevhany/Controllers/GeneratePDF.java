/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.util.Date;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
/**
 *
 * @author HANY
 */
public class GeneratePDF {
    public GeneratePDF(String ReponsesCorretes,String ReponsesFausses) throws FileNotFoundException, IOException {
        Document document = new Document();
        OutputStream outputStream = new FileOutputStream(new File("D:\\Esprit\\Atelier Java\\piweb\\pidevHany\\src\\pidevhany\\TestFile.pdf"));
        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph("Resultat du test :"));
            PdfPTable table = new PdfPTable(2); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f};
            table.setWidths(columnWidths);
            
            
            

            PdfPCell cell1 = new PdfPCell(new Paragraph(ReponsesCorretes));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph(ReponsesFausses));
            cell2.setBorderColor(BaseColor.GREEN);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(cell1);
            table.addCell(cell2);
             
            document.add(table);
            document.close();
            outputStream.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
