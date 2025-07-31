import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Map;

public class ReportGenerator {
    public static void generatePDF(Student student, String path) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();
        doc.add(new Paragraph("Report Card for: " + student.getName()));
        doc.add(new Paragraph(" "));
        for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
            doc.add(new Paragraph(entry.getKey() + ": " + entry.getValue()));
        }
        doc.add(new Paragraph("Average: " + student.getAverage()));
        doc.add(new Paragraph("Highest: " + student.getHighest()));
        doc.add(new Paragraph("Lowest: " + student.getLowest()));
        doc.close();
    }
}

