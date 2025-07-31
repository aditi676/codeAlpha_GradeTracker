import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class GradeTracker extends JFrame {
    private JTextField nameField;
    private JTextField subjectField;
    private JTextField markField;
    private JButton addSubjectBtn, saveStudentBtn, exportBtn;
    private JTable table;
    private DefaultTableModel tableModel;
    private java.util.List<Student> students = new ArrayList<>();
    private Map<String, Integer> tempMarks = new LinkedHashMap<>();

    public GradeTracker() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("Smart Grade Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        nameField = new JTextField();
        subjectField = new JTextField();
        markField = new JTextField();

        addSubjectBtn = new JButton("Add Subject");
        saveStudentBtn = new JButton("Save Student");

        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Subject:"));
        formPanel.add(subjectField);
        formPanel.add(new JLabel("Mark:"));
        formPanel.add(markField);
        formPanel.add(addSubjectBtn);
        formPanel.add(saveStudentBtn);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Subjects", "Average", "Highest", "Lowest"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        exportBtn = new JButton("Generate PDF Report");
        add(exportBtn, BorderLayout.SOUTH);

        addSubjectBtn.addActionListener(e -> {
            String subject = subjectField.getText();
            int mark;
            try {
                mark = Integer.parseInt(markField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid mark value.");
                return;
            }
            if (!subject.isEmpty()) {
                tempMarks.put(subject, mark);
                subjectField.setText("");
                markField.setText("");
            }
        });

        saveStudentBtn.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty() || tempMarks.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter name and at least one subject.");
                return;
            }
            Student s = new Student(name);
            for (Map.Entry<String, Integer> entry : tempMarks.entrySet()) {
                s.addMark(entry.getKey(), entry.getValue());
            }
            students.add(s);
            tableModel.addRow(new Object[]{
                s.getName(),
                s.getSubjectMarks().toString(),
                String.format("%.2f", s.getAverage()),
                s.getHighest(),
                s.getLowest()
            });
            nameField.setText("");
            tempMarks.clear();
        });

        exportBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student from the table.");
                return;
            }
            Student s = students.get(selectedRow);
            try {
                ReportGenerator.generatePDF(s, "report_" + s.getName() + ".pdf");
                JOptionPane.showMessageDialog(this, "PDF Generated: report_" + s.getName() + ".pdf");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to generate PDF.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new GradeTracker();
    }
}

class Student {
    private String name;
    private Map<String, Integer> subjectMarks;

    public Student(String name) {
        this.name = name;
        this.subjectMarks = new LinkedHashMap<>();
    }

    public void addMark(String subject, int mark) {
        subjectMarks.put(subject, mark);
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public double getAverage() {
        return subjectMarks.values().stream().mapToInt(i -> i).average().orElse(0);
    }

    public int getHighest() {
        return subjectMarks.values().stream().mapToInt(i -> i).max().orElse(0);
    }

    public int getLowest() {
        return subjectMarks.values().stream().mapToInt(i -> i).min().orElse(0);
    }
}

class ReportGenerator {
    public static void generatePDF(Student student, String path) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();
        doc.add(new Paragraph("Report Card"));
        doc.add(new Paragraph("Name: " + student.getName()));
        doc.add(new Paragraph(" "));
        for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
            doc.add(new Paragraph(entry.getKey() + ": " + entry.getValue()));
        }
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Average: " + student.getAverage()));
        doc.add(new Paragraph("Highest: " + student.getHighest()));
        doc.add(new Paragraph("Lowest: " + student.getLowest()));
        doc.close();
    }
}
