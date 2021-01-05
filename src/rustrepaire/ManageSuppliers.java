/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rustrepaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class ManageSuppliers extends javax.swing.JFrame {

    //declare 5 private global String variables
    private String supplierRegNo, Name, Address, LandlineNo, MobileNo;

    //initialize a private boolean variable
    boolean flag = false;

    //declare 2 private Object type global variables
    private Statement st;
    private ResultSet rs;
    private Connection con;

    //declare a global DefaultTableModel variable
    private final DefaultTableModel dtm;

    /**
     * Creates new form ManageSuppliers
     */
    public ManageSuppliers() throws SQLException {
        initComponents();
        con = DriverManager.getConnection("jdbc:mysql://localhost/rustrepaire", "root", "");
        this.dtm = (DefaultTableModel) tblAllSuppliers.getModel();
        viewAllSuppliers();
    }

    private void displayMessage(String messageType, String message) {
        if (messageType.equals("Success")) {
            //displays success message when operation is successful
            JOptionPane.showMessageDialog(this, "" + message);
        } else {
            //displays error message when operation is unsuccessful
            JOptionPane.showMessageDialog(rootPane, "" + message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validatePrimaryKey(String executionType) {
        flag = false;
        //checks whether the Supplier Registration Number is null
        if (!txtregno.getText().equals("")) {
            try {
                st = con.createStatement();//initialize Statement object
                rs = st.executeQuery("select COUNT(*) from supplier where RegNo='" + txtregno.getText() + "'");//Initialize ResultSet object.Checks whether the Supplier exists.

                int supplierCount = 0;
                if (rs.next()) {
                    supplierCount = rs.getInt("COUNT(*)");
                }
                if (supplierCount == 1) {
                    switch (executionType) {
                        case "Save":
                            //prints error message if Supplier exists
                            displayMessage("Error", "Registration Number already exists. Please re-type the Registration Number.");
                            txtregno.setText("");
                            break;
                        case "Update":
                            validateData();
                            break;
                        default:
                            flag = true;
                            break;
                    }
                } else {
                    switch (executionType) {
                        case "Save":
                            validateData();
                            break;
                        default:
                            //prints error message if Supplier doesn't exist
                            displayMessage("Error", "Registration Number does not exist. Please re-type the Registration Number.");
                            txtregno.setText("");
                            break;
                    }

                }
            } catch (Exception e) {

            }
        } else {

            String primaryKeyArticle = "the";//initialize primaryKeyArticle variable
            if (executionType.equals("Save")) {
                primaryKeyArticle = "a";
            }
            //displays error message if the Registration Number is null
            displayMessage("Error", "Please enter " + primaryKeyArticle + " Registration Number.");
        }
    }

    //Method to validate data
    //Modifier-private
    //Return type-void
    //No parameters
    private void validateData() {

        //checks whether the Supplier Name is null
        if (!txtname.getText().equals("")) {
            //checks whether the Supplier Name is valid
            if (txtname.getText().length() >= 2 && txtname.getText().length() <= 64) {
                //checks whether the Supplier Address is null
                if (!txtaddress.getText().equals("")) {
                    //checks whether the Supplier Address is valid
                    if (txtaddress.getText().length() >= 2 && txtaddress.getText().length() <= 64) {
                        //checks whether the Supplier Landline Number is null
                        if (!txtland.getText().equals("")) {
                            //checks whether the Supplier Landline Number is valid
                            if (txtland.getText().length() == 10) {
                                //checks whether the Supplier Mobile Number is null
                                if (!txtmobile.getText().equals("")) {
                                    //checks whether the Supplier Mobile Number is valid
                                    if (txtmobile.getText().length() == 10) {
                                        flag = true;
                                    } else {
                                        //displays error message if mobile number is invalid
                                        displayMessage("Error", "Invalid Mobile No.");
                                    }
                                } else {
                                    //displays error message if mobile number is null
                                    displayMessage("Error", "Please enter the Mobile No.");
                                }
                            } else {
                                //displays error message if landline number is invalid
                                displayMessage("Error", "Invalid Landline Telephone No.");

                            }
                        } else {
                            //displays error message if landline number is null
                            displayMessage("Error", "Please enter the Landline Telephone No.");
                        }
                    } else {
                        //displays error message if address is invalid
                        displayMessage("Error", "Please enter a number of characters ranging from 2 to 64 for the Address.");

                    }
                } else {
                    //displays error message if address is null
                    displayMessage("Error", "Please enter the Address.");
                }
            } else {
                //displays error message if name is invalid
                displayMessage("Error", "Please enter a number of characters ranging from 2 to 64 for the Name.");

            }
        } else {
            //displays error message if name is null   
            displayMessage("Error", "Please enter the Name.");
        }

    }

    private void saveSupplier() {
        try {
            st = con.createStatement();//initialize Statement object
            st.executeUpdate("insert into supplier(RegNo, SupplierName, Address, LandlineNo, MobileNo) values('" + supplierRegNo + "', '" + Name + "', '" + Address + "', '" + LandlineNo + "', '" + MobileNo + "')");//save Supplier to database
            displayMessage("Success", "Supplier saved successfully.");
            System.out.println("Saved supplier:true");

        } catch (Exception e) {
            displayMessage("Error", "The Supplier cannot be saved. More details: " + e);
            System.out.println("Saved supplier:false");
        }
    }

    private void searchSupplier() {

        try {
            st = con.createStatement();//initialize Statement object
            rs = st.executeQuery("select * from supplier where RegNo='" + supplierRegNo + "'");//Initialize ResultSet object.Search Supplier from the database.
            if (rs.next()) {
                //set values
                Name = rs.getString("SupplierName");
                Address = rs.getString("Address");
                LandlineNo = rs.getString("LandlineNo");
                MobileNo = rs.getString("MobileNo");

                //set textfield values
                txtname.setText("" + Name);
                txtaddress.setText("" + Address);
                txtland.setText("" + LandlineNo);
                txtmobile.setText("" + MobileNo);
            }
            System.out.println("Searched supplier:true");
        } catch (Exception e) {
            System.out.println("Searched supplier:false");
        }
    }

    private void updateSupplier() {
        try {
            st = con.createStatement();//initialize Statement object
            st.executeUpdate("UPDATE supplier SET SupplierName='" + Name + "', Address='" + Address + "', LandlineNo='" + LandlineNo + "', MobileNo='" + MobileNo + "' WHERE RegNo='" + supplierRegNo + "'");//update Supplier details
            displayMessage("Success", "Supplier updated successfully.");
            System.out.println("Updated supplier:true");

        } catch (Exception e) {
            displayMessage("Error", "The Supplier cannot be updated. More details: " + e);
            System.out.println("Updated supplier:false");
        }
    }

    private void viewAllSuppliers() {
         dtm.setRowCount(0);   
        try {
            st = con.createStatement();//initialize Statement object
            rs = st.executeQuery("select * from supplier");//Initialize ResultSet object.Search Suppliers from the database.
            while (rs.next()) {
                Vector v = new Vector();//create Vector object
                //add data to the vector
                v.add(rs.getString("RegNo"));
                v.add(rs.getString("SupplierName"));
                v.add(rs.getString("Address"));
                v.add(rs.getString("LandlineNo"));
                v.add(rs.getString("MobileNo"));

                //add row to the table
                dtm.addRow(v);

            }
        } catch (Exception e) {
            System.out.println("Viewed all suppliers:false");
        }
    }

    private void deleteSupplier() {
        try {
            st = con.createStatement();//initialize Statement object
            st.executeUpdate("delete from supplier where RegNo='" + supplierRegNo + "'");//delete Supplier from the database
            displayMessage("Success", "Supplier deleted successfully.");
            System.out.println("Deleted supplier:true");

            txtregno.setText("");
            txtname.setText("");
            txtaddress.setText("");
            txtland.setText("");
            txtmobile.setText("");
            
        } catch (Exception e) {
            displayMessage("Error", "The Supplier cannot be deleted from the system. More details: " + e);
            System.out.println("Deleted supplier:false");
        }
    }

    private void setValues() {
        supplierRegNo = txtregno.getText();
        Name = txtname.getText();
        Address = txtaddress.getText();
        LandlineNo = txtland.getText();
        MobileNo = txtmobile.getText();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregno = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtland = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtmobile = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAllSuppliers = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Reg No :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Address :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Land No :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Mobile No :");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setText("Insert");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Suppliers");

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchKeyPressed(evt);
            }
        });

        tblAllSuppliers.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblAllSuppliers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reg No", "Name", "Address", "Landline No", "Mobile No"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAllSuppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllSuppliersMouseClicked(evt);
            }
        });
        tblAllSuppliers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblAllSuppliersKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblAllSuppliers);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jButton1))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(75, 75, 75)
                                .addComponent(jButton3)
                                .addContainerGap(558, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtmobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtaddress, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtland, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearch)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtland, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtmobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(138, Short.MAX_VALUE))
        );

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        validatePrimaryKey("Save");
        if (flag) {
            setValues();
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this Supplier?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                saveSupplier();

                txtregno.setText("");
                txtname.setText("");
                txtaddress.setText("");
                txtland.setText("");
                txtmobile.setText("");

                viewAllSuppliers();

            } else if (dialogResult == JOptionPane.NO_OPTION) {
                //do nothing
            } else {
                txtregno.setText("");

                //set the text-fields to null
                txtname.setText("");
                txtaddress.setText("");
                txtland.setText("");
                txtmobile.setText("");

            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        validatePrimaryKey("Delete");
        if (flag) {
            setValues();
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Supplier?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                deleteSupplier();
                viewAllSuppliers();
            } else if (dialogResult == JOptionPane.NO_OPTION) {
                //do nothing
            } else {
                txtregno.setText("");
                txtname.setText("");
                txtaddress.setText("");
                txtland.setText("");
                txtmobile.setText("");
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        validatePrimaryKey("Update");
        if (flag) {
            setValues();
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this Supplier?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                updateSupplier();
                txtname.setText("");
                txtaddress.setText("");
                txtland.setText("");
                txtmobile.setText("");

                viewAllSuppliers();
            } else if (dialogResult == JOptionPane.NO_OPTION) {
                //do nothing
            } else {
                txtregno.setText("");
                txtname.setText("");
                txtaddress.setText("");
                txtland.setText("");
                txtmobile.setText("");

            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        txtname.setText("");
        txtaddress.setText("");
        txtland.setText("");
        txtmobile.setText("");

        supplierRegNo = txtregno.getText();
        searchSupplier();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSearchKeyPressed

    private void tblAllSuppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllSuppliersMouseClicked
        // TODO add your handling code here:
        txtregno.getText();

        txtname.setText("");
        txtaddress.setText("");
        txtland.setText("");
        txtmobile.setText("");

        supplierRegNo = dtm.getValueAt(tblAllSuppliers.getSelectedRow(), 0).toString();
        txtregno.setText("" + supplierRegNo);
        searchSupplier();
    }//GEN-LAST:event_tblAllSuppliersMouseClicked

    private void tblAllSuppliersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAllSuppliersKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblAllSuppliersKeyPressed

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
            java.util.logging.Logger.getLogger(ManageSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageSuppliers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ManageSuppliers().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ManageSuppliers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAllSuppliers;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtland;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtregno;
    // End of variables declaration//GEN-END:variables
}
