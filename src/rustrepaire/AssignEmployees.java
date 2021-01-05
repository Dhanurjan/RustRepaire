/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rustrepaire;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class AssignEmployees extends javax.swing.JFrame {

    Connection con;
    ResultSet rs;
    Statement st;

    boolean flag;
    boolean itemExist;
    DefaultTableModel dtm;

    double newQuan, oldQuan;

    int printInvoiceNo;

    Date currentDate = new Date();
    String printDate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm a");

    /**
     * Creates new form AssignEmployees
     */
    public AssignEmployees() throws SQLException {
        initComponents();
        this.dtm = (DefaultTableModel) tblInvoice.getModel();
        con = DriverManager.getConnection("jdbc:mysql://localhost/rustrepaire", "root", "");
        EmployeeLoadCombo();
        STLoadCombo();
        // autogen();
        //showDate();
    }

    private void setPrintDateAndTime() {
        printDate = sdf.format(currentDate);

    }

    private void showDate() {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        txtInvoiceDate.setText(sd.format(d));

    }

    private void EmployeeLoadCombo() {
        try {

            st = con.createStatement();
            rs = st.executeQuery("Select name from employee");

            Vector v = new Vector();
            //v.add("");
            while (rs.next()) {

                v.add(rs.getString("Name"));
            }
            comboEmploy.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void STLoadCombo() {
        try {

            st = con.createStatement();
            rs = st.executeQuery("Select rjid from repairjobs");

            Vector v = new Vector();
            //v.add("");
            while (rs.next()) {

                v.add(rs.getString("rjid"));
            }
            cmbJid.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchEmp() {
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Employee WHERE name = '" + comboEmploy.getSelectedItem() + "'");

            if (rs.next()) {
                txtphone.setText(rs.getString("Phone"));
            } else {
                txtphone.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void itemsearch() {
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM repairjobs I WHERE I.rjid = '" + cmbJid.getSelectedItem() + "'");

            if (rs.next()) {
                String Owner = rs.getString("I.ownername");
                String Contact = rs.getString("I.contactno");
                String Vno = rs.getString("I.vechicleno");
                String Date = rs.getString("I.date");

                txtdate.setText(Date);
                txtowner.setText(Owner);
                txtcontact.setText(Contact);
                txtvehicle.setText(Vno);
            } else {

                txtowner.setText("");
                txtcontact.setText("");
                txtvehicle.setText("");
                txtdate.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   

    

    private void AddToTable() {

        if (!cmbJid.getSelectedItem().equals("")) {
            int rows = dtm.getRowCount();
            itemExist = false;
            for (int r = 0; r < rows; r++) {
                String itId = (String) dtm.getValueAt(r, 0);
                if (cmbJid.getSelectedItem().toString().equals(itId)) {
                    itemExist = true;
                }

            }
            if (!itemExist) {
                System.out.println("D");

                try {

                    Vector v = new Vector();

                    v.add(cmbJid.getSelectedItem());
                    v.add(txtowner.getText());
                    v.add(txtcontact.getText());
                    v.add(txtvehicle.getText());
                    v.add(txtdate.getText());

                    dtm.addRow(v);

                    txtowner.setText("");
                    txtcontact.setText("");
                    txtvehicle.setText("");
                    txtdate.setText("");

                    cmbJid.setSelectedIndex(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Job Already Exist.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select a Job", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtInvoiceNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtInvoiceDate = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtRemarks = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboEmploy = new javax.swing.JComboBox();
        txtphone = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcontact = new javax.swing.JTextField();
        cmbJid = new javax.swing.JComboBox();
        txtvehicle = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        txtowner = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        lblSaveShortcut = new javax.swing.JLabel();
        btnAddToTable = new javax.swing.JButton();
        btnRemoveFromTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel8.setText("Invoice No");

        txtInvoiceNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInvoiceNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInvoiceNoKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel9.setText("Date");

        txtInvoiceDate.setEditable(false);
        txtInvoiceDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInvoiceDateActionPerformed(evt);
            }
        });
        txtInvoiceDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInvoiceDateKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel13.setText("Remarks");

        txtRemarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRemarksKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInvoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtInvoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-7, 0, 880, -1));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel5.setText("Employee Name");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel6.setText("Phone");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 67, -1, -1));

        comboEmploy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEmployItemStateChanged(evt);
            }
        });
        comboEmploy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboEmployMouseClicked(evt);
            }
        });
        comboEmploy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEmployActionPerformed(evt);
            }
        });
        comboEmploy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboEmployKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboEmployKeyReleased(evt);
            }
        });
        jPanel2.add(comboEmploy, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 120, -1));

        txtphone.setEditable(false);
        txtphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtphoneKeyPressed(evt);
            }
        });
        jPanel2.add(txtphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 60, 330, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 870, 100));

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel3KeyPressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel1.setText("JobID");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 46, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel2.setText("Owner");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel3.setText("Contact");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, -1, 30));

        txtcontact.setEditable(false);
        txtcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontactKeyPressed(evt);
            }
        });
        jPanel3.add(txtcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 170, -1));

        cmbJid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJidItemStateChanged(evt);
            }
        });
        cmbJid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJidActionPerformed(evt);
            }
        });
        cmbJid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJidKeyPressed(evt);
            }
        });
        jPanel3.add(cmbJid, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 160, -1));

        txtvehicle.setEditable(false);
        txtvehicle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtvehicleKeyPressed(evt);
            }
        });
        jPanel3.add(txtvehicle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 132, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel14.setText("Vehicle Number");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, -1, 10));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel15.setText("Date");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 55, -1, 20));

        txtdate.setEditable(false);
        txtdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdateKeyTyped(evt);
            }
        });
        jPanel3.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 106, -1));

        txtowner.setEditable(false);
        txtowner.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtownerKeyPressed(evt);
            }
        });
        jPanel3.add(txtowner, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 290, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 870, 130));

        jPanel5.setBackground(new java.awt.Color(153, 153, 255));
        jPanel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel5KeyPressed(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInvoice.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Description", "Make", "Unit Selling Price", "Quantity", "Units", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInvoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblInvoiceKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInvoice);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 825, 70));

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnSave.setText("Save");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveMouseExited(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        btnSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSaveKeyPressed(evt);
            }
        });
        jPanel5.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        lblSaveShortcut.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jPanel5.add(lblSaveShortcut, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 60, 20));

        btnAddToTable.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnAddToTable.setText("Add to Table");
        btnAddToTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToTableActionPerformed(evt);
            }
        });
        btnAddToTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddToTableKeyPressed(evt);
            }
        });
        jPanel5.add(btnAddToTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 171, -1));

        btnRemoveFromTable.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnRemoveFromTable.setText("Remove from Table");
        btnRemoveFromTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromTableActionPerformed(evt);
            }
        });
        btnRemoveFromTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRemoveFromTableKeyPressed(evt);
            }
        });
        jPanel5.add(btnRemoveFromTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 171, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 870, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtInvoiceNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvoiceNoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtInvoiceNoKeyPressed

    private void txtInvoiceNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvoiceNoKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtInvoiceNoKeyTyped

    private void txtInvoiceDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInvoiceDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInvoiceDateActionPerformed

    private void txtInvoiceDateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInvoiceDateKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtInvoiceDateKeyPressed

    private void txtRemarksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRemarksKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtRemarksKeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_jPanel4KeyPressed

    private void comboEmployItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEmployItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_comboEmployItemStateChanged

    private void comboEmployMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboEmployMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEmployMouseClicked

    private void comboEmployActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEmployActionPerformed
        // TODO add your handling code here:
        searchEmp();
    }//GEN-LAST:event_comboEmployActionPerformed

    private void comboEmployKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboEmployKeyPressed
        // TODO add your handling code here:


    }//GEN-LAST:event_comboEmployKeyPressed

    private void comboEmployKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboEmployKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEmployKeyReleased

    private void txtphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtphoneKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtphoneKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel2KeyPressed

    private void txtcontactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontactKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtcontactKeyPressed

    private void cmbJidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJidItemStateChanged

    }//GEN-LAST:event_cmbJidItemStateChanged

    private void cmbJidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJidActionPerformed
        // TODO add your handling code here

        itemsearch();
    }//GEN-LAST:event_cmbJidActionPerformed

    private void cmbJidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJidKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbJidKeyPressed

    private void txtvehicleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvehicleKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtvehicleKeyPressed

    private void txtdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdateKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtdateKeyPressed

    private void txtdateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdateKeyReleased

        
    }//GEN-LAST:event_txtdateKeyReleased

    private void txtdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdateKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') | c == '.')) {
            if (!(c == KeyEvent.VK_BACK_SPACE && c == KeyEvent.VK_DELETE) && c != KeyEvent.VK_SPACE) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtdateKeyTyped

    private void txtownerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtownerKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtownerKeyPressed

    private void jPanel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel3KeyPressed

    private void tblInvoiceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblInvoiceKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblInvoiceKeyPressed

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSaveMouseEntered

    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited
        // TODO add your handling code here:
      
    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSaveKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSaveKeyPressed

    private void btnAddToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToTableActionPerformed
        // TODO add your handling code here:
        //dtm.setRowCount(0);
        jPanel4.grabFocus();
        AddToTable();
    }//GEN-LAST:event_btnAddToTableActionPerformed

    private void btnAddToTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddToTableKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnAddToTableKeyPressed

    private void btnRemoveFromTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromTableActionPerformed
        // TODO add your handling code here:
        jPanel4.grabFocus();

        //comboItemID.setSelectedIndex(0);
    }//GEN-LAST:event_btnRemoveFromTableActionPerformed

    private void btnRemoveFromTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRemoveFromTableKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnRemoveFromTableKeyPressed

    private void jPanel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel5KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AssignEmployees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssignEmployees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssignEmployees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssignEmployees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AssignEmployees().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AssignEmployees.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToTable;
    private javax.swing.JButton btnRemoveFromTable;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbJid;
    private javax.swing.JComboBox comboEmploy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSaveShortcut;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtInvoiceDate;
    private javax.swing.JTextField txtInvoiceNo;
    private javax.swing.JTextField txtRemarks;
    private javax.swing.JTextField txtcontact;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtowner;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtvehicle;
    // End of variables declaration//GEN-END:variables
}
