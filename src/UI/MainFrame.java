/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import services.SalleService;
import entities.Salle;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    public MainFrame() {

        super("Gestion des salles et Machines");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        JTabbedPane tabbed = new JTabbedPane();
        tabbed.setBounds(10, 10, 560, 440);

        
        JPanel sallePanel = new JPanel(null);

        JLabel salleLabel = new JLabel("Salle");
        salleLabel.setBounds(50, 20, 120, 30);
        sallePanel.add(salleLabel);

        JTextField txt = new JTextField();
        txt.setBounds(180, 20, 200, 30);
        sallePanel.add(txt);

        JButton bcreate = new JButton("Create");
        JButton bupdate = new JButton("Update");
        JButton bdelete = new JButton("Delete");
        JButton bfindall =new JButton("Find All");
        JButton bFindBetweenDates = new JButton("Find Between Dates");

        bcreate.setBounds(50, 100, 100, 30);
        bupdate.setBounds(160, 100, 100, 30);
        bdelete.setBounds(270, 100, 100, 30);
        bfindall.setBounds(380, 100, 100, 30);
        bFindBetweenDates.setBounds(50, 140, 200, 30);

        
        bcreate.setBackground(new java.awt.Color(76, 175, 80)); 
        bupdate.setBackground(new java.awt.Color(33, 150, 243));
        bdelete.setBackground(new java.awt.Color(244, 67, 54));   
        bfindall.setBackground(new java.awt.Color(255, 193, 7)); 
        bFindBetweenDates.setBackground(new java.awt.Color(255, 152, 0));

        sallePanel.add(bcreate);
        sallePanel.add(bupdate);
        sallePanel.add(bdelete);
        sallePanel.add(bfindall);
        sallePanel.add(bFindBetweenDates);

        JTextArea txtArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(50, 150, 430, 200);
        sallePanel.add(scroll);

        JTextField txtNewCode = new JTextField();
        txtNewCode.setBounds(180, 70, 200, 30);
        txtNewCode.setVisible(false);
        sallePanel.add(txtNewCode);

        JLabel lblNewCode = new JLabel("Salle Updated");
        lblNewCode.setBounds(50, 60, 120, 30);
        txtNewCode.setBounds(180, 60, 200, 30);
        lblNewCode.setVisible(false);
        sallePanel.add(lblNewCode);

        SalleService salleService = new SalleService();


        bcreate.addActionListener(e -> {
            lblNewCode.setVisible(false);
            txtNewCode.setVisible(false);

            String code = txt.getText().trim();
            if (!code.isEmpty()) {
                salleService.create(new Salle(code));
                txtArea.setText("Salle created: " + code);
                txt.setText("");
            } else {
                txtArea.setText("Please enter a code!");
            }
        });

        bupdate.addActionListener(e -> {
            lblNewCode.setVisible(true);
            txtNewCode.setVisible(true);

            String oldCode = txt.getText().trim();
            String newCode = txtNewCode.getText().trim();

            if (!oldCode.isEmpty() && !newCode.isEmpty()) {
                Salle s = salleService.findAll().stream()
                        .filter(sal -> sal.getCode().equals(oldCode))
                        .findFirst()
                        .orElse(null);
                if (s != null) {
                    s.setCode(newCode);
                    salleService.update(s);
                    txtArea.setText("Salle updated: " + oldCode + " → " + newCode);
                    txt.setText("");
                    txtNewCode.setText("");
                } else {
                    txtArea.setText("Salle not found!");
                }
            } else {
                txtArea.setText("Enter both old and new code!");
            }
        });

        bdelete.addActionListener(e -> {
            lblNewCode.setVisible(false);
            txtNewCode.setVisible(false);

            String code = txt.getText().trim();
            if (!code.isEmpty()) {
                Salle s = salleService.findAll().stream()
                        .filter(sal -> sal.getCode().equals(code))
                        .findFirst()
                        .orElse(null);
                if (s != null) {
                    salleService.delete(s);
                    txtArea.setText("Salle deleted: " + code);
                    txt.setText("");
                } else {
                    txtArea.setText("Salle not found!");
                }
            }
        });
        
        
        bfindall.addActionListener(e -> {
            txtArea.setText("");
            salleService.findAll().forEach(s -> txtArea.append(s.getCode() + "\n"));   
        });
        
        JTextField txtStartDate = new JTextField();
        txtStartDate.setBounds(270, 140, 100, 30);
        txtStartDate.setToolTipText("Start Date: yyyy-MM-dd");
        sallePanel.add(txtStartDate);
        
        
        JTextField txtEndDate = new JTextField();
        txtEndDate.setBounds(380, 140, 100, 30);
        txtEndDate.setToolTipText("End Date: yyyy-MM-dd");
        sallePanel.add(txtEndDate);
        scroll.setBounds(50, 190, 430, 200);
        
        bFindBetweenDates.addActionListener(e -> {
            txtArea.setText("");
            try {
                LocalDate start = LocalDate.parse(txtStartDate.getText().trim());
                LocalDate end = LocalDate.parse(txtEndDate.getText().trim());
                salleService.findAll().stream().filter(s -> s.getDateCreation() != null && 
                         !s.getDateCreation().isBefore(start) && 
                         !s.getDateCreation().isAfter(end))
            .forEach(s -> txtArea.append(s.getCode() + " - " + s.getDateCreation() + "\n"));
                
                        
                
                
            } catch (Exception ex) {
                txtArea.setText("Please enter valid dates in yyyy-MM-dd format");
            }
        });

        
        
        
        
        
        
        tabbed.add("Salles", sallePanel);
        

        JPanel machinePanel = new JPanel(null);
        tabbed.add("Machines", machinePanel);

        add(tabbed);
        setVisible(true);
    }
}
