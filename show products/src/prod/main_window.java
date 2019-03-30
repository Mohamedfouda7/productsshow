/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prod;

import com.sun.xml.internal.bind.v2.model.core.ID;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Properties;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author aaa
 */
public class main_window extends javax.swing.JFrame {

    
    /**
     * Creates new form main_window
     */
    
    //declare attriputes
    String imgpath=null;
    int tar=0;
    public main_window() {
        initComponents();
      show_data_jtable();
         
    }
    //connection to DB
    public Connection getconnection(){
    
     Connection con=null;
    
        try {
           
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db2","root","");
        //    JOptionPane.showMessageDialog(null, "connected");
               return con;
                    
        } catch (SQLException ex) {
            //Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, ex);
           // System.out.println("Error" + ex);
            return null;
        }
    
    
    }
      //resizeimage method
    
      public ImageIcon ResizeImage(String imagepath,byte []pic){
                   ImageIcon myImage=null;
                   if(imagepath!=null){
                   myImage=new ImageIcon(imagepath);
                   }
                   else{
                   myImage=new ImageIcon(pic);
                   }
                   
                   Image img1=myImage.getImage();
                   Image img2=img1.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
                   
                   ImageIcon image=new ImageIcon(img2);
                   return image;
                   
      }
    //// check method  
        public boolean checkInput(){
            
             if(txt_name.getText()==null||txt_price.getText()==null||txt_adddate.getDate()==null){
             
             return false;
             
             }
             else{
                  try{
                  
                  Float.parseFloat(txt_price.getText());
                  return true;
                  }catch(Exception ex){
                  return false;
                  }
             }
        
        }
        
    
        // display attribute from database
        public ArrayList<product> getproductlist() {
            
            ArrayList <product> productlist=new ArrayList <product>();
              
            String  Rquery;
            Connection con;
            Statement st;
             ResultSet rs;
            con=getconnection();
            Rquery="SELECT * FROM products";
        try {
            st=con.createStatement();
            rs=st.executeQuery(Rquery);
            product product;
            while(rs.next()){
            product=new product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getBytes("image"),rs.getString("add_date"));
            productlist.add(product);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            
            
            
            return productlist;
}
     ///display in jtable
        public void show_data_jtable(){
        ArrayList<product>list=getproductlist();
            DefaultTableModel model=(DefaultTableModel)JTable_products.getModel();
            model.setRowCount(0);
             Object []row=new Object[4];
             
             for(int i=0;i<list.size();i++){
             row[0]=list.get(i).getId();
             row[1]=list.get(i).getName();
             row[2]=list.get(i).getPrice(); 
             row[3]=list.get(i).getAddDate(); 
               model.addRow(row);
             }
        
        }
        // this method for show data in the form 
        public void showItem(int index){
        txt_id.setText(Integer.toString(getproductlist().get(index).getId()));
        txt_name.setText(getproductlist().get(index).getName());
        txt_price.setText(Float.toString(getproductlist().get(index).getPrice()));
        
        try {
            Date AddDate =new SimpleDateFormat("yyyy-MM-dd").parse((String)getproductlist().get(index).getAddDate());
            txt_adddate.setDate(AddDate);
        } catch (ParseException ex) {
            Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_image.setIcon(ResizeImage(null,getproductlist().get(index).getPicture()));
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
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_products = new javax.swing.JTable();
        btn_chooseimage = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_privios = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        txt_adddate = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("price:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Add date :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("image:");

        txt_name.setPreferredSize(new java.awt.Dimension(69, 50));

        txt_price.setPreferredSize(new java.awt.Dimension(69, 50));

        txt_id.setPreferredSize(new java.awt.Dimension(69, 50));

        lbl_image.setBackground(new java.awt.Color(204, 255, 255));
        lbl_image.setOpaque(true);

        JTable_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "price", "addDate"
            }
        ));
        JTable_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_productsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_products);

        btn_chooseimage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_chooseimage.setText("chooseImage");
        btn_chooseimage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chooseimageActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_first.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_first.setText("First");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_privios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_privios.setText("Privious");
        btn_privios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_priviosActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_last.setText("Last");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        txt_adddate.setDateFormatString("yyyy-MM-dd");
        txt_adddate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel4))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chooseimage, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_adddate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                        .addComponent(txt_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_price, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_privios, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_adddate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_chooseimage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_privios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        if(checkInput()&&txt_id!=null){
                    Connection con =getconnection();
                    String updatequary;
                    PreparedStatement ps ;
                    //with out image 
                    if(imgpath==null){
                    
                   
                        try { updatequary=("UPDATE  products SET name=?,price=?,add_date=? where id=?");
                            ps=con.prepareStatement(updatequary);
                            ps.setString(1, txt_name.getText());
                            ps.setString(2, txt_price.getText());
                            SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                          String  AddDate=dateformat.format(txt_adddate.getDate());
                           ps.setString(3, AddDate);
                         ps.setInt(4,Integer.parseInt(txt_id.getText()) );
                                 ps.executeUpdate();
                              show_data_jtable();
                        } catch (SQLException ex) {
                            Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    }
                    //with image
                    else{
                    
                     try {
                         updatequary=("UPDATE  products SET name=?,price=?,add_date=?,image=? WHERE id=?");
                            ps=con.prepareStatement(updatequary);
                            ps.setString(1, txt_name.getText());
                            ps.setString(2, txt_price.getText());
                            SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                          String  AddDate=dateformat.format(txt_adddate.getDate());
                           ps.setString(3, AddDate);
                           InputStream img=new FileInputStream(imgpath);
                           ps.setBlob(4, img);
                         
                           
                         ps.setInt(5,Integer.parseInt(txt_id.getText()) );
                         
                         ps.executeUpdate();
                           show_data_jtable();
                    JOptionPane.showMessageDialog(null, "updated");
                            
                        } catch (SQLException | FileNotFoundException ex) {
                            Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    
                    
                    
                    } 
        
        
        
        
        
        
        
        }else 
                JOptionPane.showMessageDialog(null, "no values to update ");
                
       
    
       

        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_chooseimageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chooseimageActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".*images", "jpg", "png");

        file.addChoosableFileFilter(filter);
        int resimage = file.showSaveDialog(null);
        if (resimage == JFileChooser.APPROVE_OPTION) {
            File selectedfile = file.getSelectedFile();
            String path = selectedfile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
           imgpath=path;

        
        }else{System.out.println("no file selected");}
    }//GEN-LAST:event_btn_chooseimageActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        if(checkInput()&&imgpath!=null){
           
            try {
                 Connection con=getconnection();
                  
                PreparedStatement ps=con.prepareStatement("INSERT INTO products(name,price,add_date,image) values(?,?,?,?)");
                       
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
             
                SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                String addDate=dateformat.format(txt_adddate.getDate());
                ps.setString(3, addDate);
                 InputStream img=new FileInputStream(new File(imgpath));
                 
                 ps.setBlob(4, img);
                  ps.executeUpdate();
                 show_data_jtable();
                 JOptionPane.showMessageDialog(null,"Date inserted");
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }else{
        
        JOptionPane.showMessageDialog(null, "one or more field empty");
        
        }
//        System.out.println("name=>"+txt_name.getText());
//          System.out.println("price=>"+txt_price.getText()); 
//          System.out.println("date=>"+txt_adddate.getDate()); 
//          System.out.println("img=>"+imgpath);
          
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
         if(!txt_id.getText().equals("")){
         Connection con=null;
         String deletquery;
         PreparedStatement ps=null;
              
         
             try {
                 con=getconnection();
                 deletquery=("DELETE FROM products WHERE id=?");
                 ps=con.prepareStatement(deletquery);
                 ps.setInt(1, Integer.parseInt(txt_id.getText()));
               int succes=  ps.executeUpdate();
                 show_data_jtable();
               if (succes>0){JOptionPane.showMessageDialog(null, "deleted successfully");}
               else {JOptionPane.showMessageDialog(null, " not deleted successfully");}
             } catch (SQLException ex) {
                 Logger.getLogger(main_window.class.getName()).log(Level.SEVERE, null, ex);
                 JOptionPane.showMessageDialog(null, " not deleted successfully");
             }
         
         }else 
         {JOptionPane.showMessageDialog(null,"empty field");}
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void JTable_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_productsMouseClicked
      int index=JTable_products.getSelectedRow();
      showItem(index);
    }//GEN-LAST:event_JTable_productsMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
       tar=0;
        showItem(tar);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        int tar=getproductlist().size()-1;
        showItem(tar);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
      tar++;
      if (tar>=getproductlist().size()){
        tar=getproductlist().size()-1;
        showItem(tar);
      }
      else {showItem(tar);}
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_priviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_priviosActionPerformed
        tar--;
        if(tar<=0){
        tar=0;
            showItem(tar);
        }
        else {
        
         showItem(tar);
        }
    }//GEN-LAST:event_btn_priviosActionPerformed

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
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_products;
    private javax.swing.JButton btn_chooseimage;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_privios;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_adddate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
