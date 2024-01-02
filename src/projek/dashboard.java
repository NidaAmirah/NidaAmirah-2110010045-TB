/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projek;

import java.awt.Color;
import java.io.File;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrator
 */
public class dashboard extends javax.swing.JFrame {
  public  Connection koneks = new koneksi().koneksikita();
    public DefaultTableModel tabmodel;
    
    
    
   
  
   
    
    
    protected void bersihkan(){
        tfkdaset.setText(null);
        tfnamaaset.setText(null);
        tfkdLokasi.setText(null);
        tfnamaLokasi.setText(null);
        tfkdmasuk.setText(null);
        cbbxkdAset.setSelectedIndex(0);
        tfNamaAsetTransaksi.setText(null);
        dctnggalmasuk.setDate(null);
        tfkodePenempatan.setText("");
        cbbxkdAsetPenempatan.setSelectedIndex(0);
        tfnamalokasiPenempatan.setText("");
        tfNamaAsetPenempatan.setText("");
        cbbxkdlokasipenempatan.setSelectedIndex(0);
        tfkdkeluar.setText("");
        cbbxkdasetkeluar.setSelectedIndex(0);
        dctnggalkeluar.setDate(null);
        tfNamaAsetTransaksikeluar.setText(null);
        
        
    }
    
    public void dataAsetcbbx(){
       
         
        try {
             String sql = "select * from aset";
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while (hasil.next()){
             cbbxkdAset.addItem(hasil.getString("kd_aset"));
             
            }
            
        } catch (Exception e) {
        }
        
        
    }
     public void dataAsetcbbxKeluar(){
       
         
        try {
             String sql = "select * from aset";
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while (hasil.next()){
             cbbxkdasetkeluar.addItem(hasil.getString("kd_aset"));
             
            }
            
        } catch (Exception e) {
        }
        
        
    }
    
    private void dataAsetcbbxPenempatan(){
       
         
        try {
             String sql = "select * from aset";
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while (hasil.next()){
             cbbxkdAsetPenempatan.addItem(hasil.getString("kd_aset"));
             
            }
            
        } catch (Exception e) {
        }
        
        
    }
    public void dataLokasicbbxPenempatan(){
       
         
        try {
             String sql = "select * from lokasi";
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while (hasil.next()){
             cbbxkdlokasipenempatan.addItem(hasil.getString("kd_lokasi"));
             
            }
            
        } catch (Exception e) {
        }
        
        
    }
    
    public void tabelkosong(){
        int Baris = tabmodel.getRowCount();
        for (int a=0; a<Baris; a++)
        {
            String nomor = String.valueOf(a+1);
            tabmodel.setValueAt(nomor +".",a,0);
        }
    }
    
    public void isiTabledataAset(){
        
        Object[] Baris = {"No","Kode Asset","Nama Asset"};
        tabmodel = new DefaultTableModel(null, Baris);
        tbldataasset.setModel(tabmodel);
        String sql = "select * from aset order by kd_aset asc";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_aset = hasil.getString("kd_aset");
                String nama_aset = hasil.getString("nama_aset");                
                String[] data = {"",kd_aset,nama_aset};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
     public void isiTabledataLokasi(){
        
        Object[] Baris = {"No","Kode Lokasi","Nama Lokasi"};
        tabmodel = new DefaultTableModel(null, Baris);
        tblDataLokasi.setModel(tabmodel);
        String sql = "select * from lokasi order by kd_lokasi asc";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_lokasi = hasil.getString("kd_lokasi");
                String nama_lokasi = hasil.getString("nama_lokasi");                
                String[] data = {"",kd_lokasi,nama_lokasi};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
     
      public void isiTableAsetmasuk(){
        
        Object[] Baris = {"No","Kode Masuk","Tanggal" ,"Kode Aset"};
        tabmodel = new DefaultTableModel(null, Baris);
        tblTransaksiAsetMasuk.setModel(tabmodel);
        String sql = "select * from transaksi_masuk order by kd_masuk";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_masuk = hasil.getString("kd_masuk");
                String tanggal = hasil.getString("tanggal");
                String kd_aset = hasil.getString("kd_aset");                 
                String[] data = {"",kd_masuk,tanggal,kd_aset};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
      
      public void isiTableAsetkeluar(){
        
        Object[] Baris = {"No","Kode Keluar","Tanggal" ,"Kode Aset"};
        tabmodel = new DefaultTableModel(null, Baris);
        tblTransaksiAsetkeluar.setModel(tabmodel);
        String sql = "select * from transaksi_keluar order by kd_keluar";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_keluar = hasil.getString("kd_keluar");
                String tanggal = hasil.getString("tanggal");
                String kd_aset = hasil.getString("kd_aset");                 
                String[] data = {"",kd_keluar,tanggal,kd_aset};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
       public void isiTableLaporan(){
        
        Object[] Baris = {"No","Kode Keluar","Tanggal" ,"Kode Aset"};
        tabmodel = new DefaultTableModel(null, Baris);
        tblLaporan.setModel(tabmodel);
          String sql;
//         sql = "select * from transaksi_masuk order by kd_masuk";
              sql = "select * from transaksi_keluar order by kd_keluar";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_keluar = hasil.getString("kd_keluar");
                String tanggal = hasil.getString("tanggal");
                String kd_aset = hasil.getString("kd_aset");
//                String kd_masuk = hasil.getString("kd_masuk");
//                String tanggal2 = hasil.getString("tanggal");
//                String kd_aset2 = hasil.getString("kd_aset");
                String[] data = {"",kd_keluar,tanggal,kd_aset};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
      
      public void isiTablePenempatan(){
        
        Object[] Baris = {"No","Kode Penempatan","Kode Aset" ,"Kode Lokasi"};
        tabmodel = new DefaultTableModel(null, Baris);
        tblPenempatannn.setModel(tabmodel);
        String sql = "select * from penempatan order by kd_penempatan";
        try{
            java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
               
                String kd_penempatan = hasil.getString("kd_penempatan");
                String kd_aset = hasil.getString("kd_aset");
                String kd_lokasi = hasil.getString("kd_lokasi");                 
                String[] data = {"",kd_penempatan,kd_aset,kd_lokasi};
                tabmodel.addRow(data);
                tabelkosong();
            }
        } catch (Exception e){
        }
    }
     
     
     
    /**
     * Creates new form dashboard
     */

    public dashboard()  {
        
        initComponents();
        isiTablePenempatan();
        isiTabledataAset();
        isiTabledataLokasi();
        isiTableAsetmasuk();
        isiTableAsetkeluar();
        isiTableLaporan();
        
        dataAsetcbbx();
        dataAsetcbbxKeluar();
        dataAsetcbbxPenempatan();
        dataLokasicbbxPenempatan();
        
        
       
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        appbarpanel = new javax.swing.JPanel();
        btndashboard = new javax.swing.JButton();
        btndata = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        submenuDataPanel = new javax.swing.JPanel();
        btndataasset = new javax.swing.JButton();
        btnLokasi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnTransaksi = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        submenuTransaksi = new javax.swing.JPanel();
        btnAsetmasuk = new javax.swing.JButton();
        btnAsetkeluar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnpenempatan = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnLaporan = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        dashboardpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dataLokasiPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDataLokasi = new javax.swing.JTable();
        tfkdLokasi = new javax.swing.JTextField();
        tfnamaLokasi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnubahdatalokasi = new javax.swing.JButton();
        btnsimpandatalokasi = new javax.swing.JButton();
        btnhapusdatalokasi = new javax.swing.JButton();
        btnbersihkan1 = new javax.swing.JButton();
        dataasetpanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldataasset = new javax.swing.JTable();
        tfkdaset = new javax.swing.JTextField();
        tfnamaaset = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnubahAset = new javax.swing.JButton();
        btnsimpanAset = new javax.swing.JButton();
        btnhapusAset = new javax.swing.JButton();
        btnbersihkan = new javax.swing.JButton();
        TransaksiAsetMasukPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTransaksiAsetMasuk = new javax.swing.JTable();
        tfkdmasuk = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnsimpantransaksimasuk = new javax.swing.JButton();
        btnhapustransaksimasuk = new javax.swing.JButton();
        btnbersihkan2 = new javax.swing.JButton();
        dctnggalmasuk = new com.toedter.calendar.JDateChooser();
        cbbxkdAset = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        tfNamaAsetTransaksi = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        PenempatanPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        tfkodePenempatan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnsimpanPenempatan = new javax.swing.JButton();
        btnhapusPenempatan = new javax.swing.JButton();
        btnbersihkan3 = new javax.swing.JButton();
        cbbxkdlokasipenempatan = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        tfNamaAsetPenempatan = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cbbxkdAsetPenempatan = new javax.swing.JComboBox<>();
        tfnamalokasiPenempatan = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPenempatannn = new javax.swing.JTable();
        TransaksiAsetKeluarPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTransaksiAsetkeluar = new javax.swing.JTable();
        tfkdkeluar = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnsimpantransaksikeluar = new javax.swing.JButton();
        btnhapustransaksikeluar = new javax.swing.JButton();
        btnbersihkan4 = new javax.swing.JButton();
        dctnggalkeluar = new com.toedter.calendar.JDateChooser();
        cbbxkdasetkeluar = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        tfNamaAsetTransaksikeluar = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        LaporanPanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        btncETAK = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        appbarpanel.setBackground(new java.awt.Color(255, 204, 204));

        btndashboard.setBackground(new java.awt.Color(255, 204, 255));
        btndashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_home_100px_1.png"))); // NOI18N
        btndashboard.setBorder(null);
        btndashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndashboardActionPerformed(evt);
            }
        });

        btndata.setBackground(new java.awt.Color(255, 204, 255));
        btndata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_database_100px.png"))); // NOI18N
        btndata.setBorder(null);
        btndata.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndata.setIconTextGap(0);
        btndata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndataMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btndataMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btndataMouseReleased(evt);
            }
        });
        btndata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndataActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Data");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Dashboard");

        submenuDataPanel.setBackground(new java.awt.Color(255, 204, 204));
        submenuDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        btndataasset.setBackground(new java.awt.Color(255, 204, 255));
        btndataasset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_data_in_both_directions_70px.png"))); // NOI18N
        btndataasset.setBorder(null);
        btndataasset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndataassetActionPerformed(evt);
            }
        });

        btnLokasi.setBackground(new java.awt.Color(255, 204, 204));
        btnLokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_marker_70px_1.png"))); // NOI18N
        btnLokasi.setBorder(null);
        btnLokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLokasiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Data Aset");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Data Lokasi");

        javax.swing.GroupLayout submenuDataPanelLayout = new javax.swing.GroupLayout(submenuDataPanel);
        submenuDataPanel.setLayout(submenuDataPanelLayout);
        submenuDataPanelLayout.setHorizontalGroup(
            submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submenuDataPanelLayout.createSequentialGroup()
                .addGroup(submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btndataasset, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(submenuDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(submenuDataPanelLayout.createSequentialGroup()
                        .addComponent(btnLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        submenuDataPanelLayout.setVerticalGroup(
            submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submenuDataPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLokasi)
                    .addGroup(submenuDataPanelLayout.createSequentialGroup()
                        .addComponent(btndataasset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(submenuDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        btnTransaksi.setBackground(new java.awt.Color(255, 204, 255));
        btnTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_sorting_arrows_horizontal_100px.png"))); // NOI18N
        btnTransaksi.setBorder(null);
        btnTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTransaksi.setIconTextGap(0);
        btnTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTransaksiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTransaksiMouseReleased(evt);
            }
        });
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Transaksi");

        submenuTransaksi.setBackground(new java.awt.Color(255, 204, 204));
        submenuTransaksi.setBorder(javax.swing.BorderFactory.createTitledBorder("Transaksi"));

        btnAsetmasuk.setBackground(new java.awt.Color(255, 204, 255));
        btnAsetmasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_arrow_pointing_left_70px_1.png"))); // NOI18N
        btnAsetmasuk.setBorder(null);
        btnAsetmasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsetmasukActionPerformed(evt);
            }
        });

        btnAsetkeluar.setBackground(new java.awt.Color(255, 204, 204));
        btnAsetkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_arrow_70px.png"))); // NOI18N
        btnAsetkeluar.setBorder(null);
        btnAsetkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsetkeluarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Aset Masuk");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Aset Keluar");

        btnpenempatan.setBackground(new java.awt.Color(255, 204, 255));
        btnpenempatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_point_objects_70px.png"))); // NOI18N
        btnpenempatan.setBorder(null);
        btnpenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpenempatanActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Penempatan");

        javax.swing.GroupLayout submenuTransaksiLayout = new javax.swing.GroupLayout(submenuTransaksi);
        submenuTransaksi.setLayout(submenuTransaksiLayout);
        submenuTransaksiLayout.setHorizontalGroup(
            submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, submenuTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(submenuTransaksiLayout.createSequentialGroup()
                        .addComponent(btnAsetmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnpenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(submenuTransaksiLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)))
                .addGap(12, 12, 12)
                .addGroup(submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(submenuTransaksiLayout.createSequentialGroup()
                        .addComponent(btnAsetkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        submenuTransaksiLayout.setVerticalGroup(
            submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(submenuTransaksiLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAsetmasuk)
                    .addComponent(btnpenempatan)
                    .addComponent(btnAsetkeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(submenuTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnLaporan.setBackground(new java.awt.Color(255, 204, 255));
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_report_card_100px.png"))); // NOI18N
        btnLaporan.setBorder(null);
        btnLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLaporan.setIconTextGap(0);
        btnLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLaporanMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLaporanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLaporanMouseReleased(evt);
            }
        });
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Laporan");

        javax.swing.GroupLayout appbarpanelLayout = new javax.swing.GroupLayout(appbarpanel);
        appbarpanel.setLayout(appbarpanelLayout);
        appbarpanelLayout.setHorizontalGroup(
            appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appbarpanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(btndashboard))
                .addGap(60, 60, 60)
                .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btndata)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appbarpanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(40, 40, 40)))
                .addGap(12, 12, 12)
                .addComponent(submenuDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnTransaksi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appbarpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)))
                .addComponent(submenuTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnLaporan))
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel33)))
                .addContainerGap(1880, Short.MAX_VALUE))
        );
        appbarpanelLayout.setVerticalGroup(
            appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appbarpanelLayout.createSequentialGroup()
                .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnTransaksi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btndata)
                            .addComponent(btndashboard))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(appbarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submenuDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submenuTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(appbarpanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnLaporan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        dashboardpanel.setBackground(new java.awt.Color(204, 255, 255));
        dashboardpanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("APLIKASI INVENTARIS ASET");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_product_300px.png"))); // NOI18N

        javax.swing.GroupLayout dashboardpanelLayout = new javax.swing.GroupLayout(dashboardpanel);
        dashboardpanel.setLayout(dashboardpanelLayout);
        dashboardpanelLayout.setHorizontalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardpanelLayout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addGroup(dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardpanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(156, 156, 156)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardpanelLayout.setVerticalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardpanelLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        dataLokasiPanel.setBackground(new java.awt.Color(255, 255, 255));
        dataLokasiPanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Data Lokasi");

        tblDataLokasi.setBackground(new java.awt.Color(255, 255, 255));
        tblDataLokasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "KODE ", "NAMA "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDataLokasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataLokasiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDataLokasi);

        tfkdLokasi.setBackground(new java.awt.Color(255, 255, 255));

        tfnamaLokasi.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Kode Lokasi");

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nama Lokasi");

        btnubahdatalokasi.setBackground(new java.awt.Color(255, 255, 255));
        btnubahdatalokasi.setForeground(new java.awt.Color(51, 51, 51));
        btnubahdatalokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_edit_40px_1.png"))); // NOI18N
        btnubahdatalokasi.setText("Ubah");
        btnubahdatalokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahdatalokasiActionPerformed(evt);
            }
        });

        btnsimpandatalokasi.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpandatalokasi.setForeground(new java.awt.Color(51, 51, 51));
        btnsimpandatalokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_save_40px.png"))); // NOI18N
        btnsimpandatalokasi.setText("Simpan");
        btnsimpandatalokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpandatalokasiActionPerformed(evt);
            }
        });

        btnhapusdatalokasi.setBackground(new java.awt.Color(255, 255, 255));
        btnhapusdatalokasi.setForeground(new java.awt.Color(51, 51, 51));
        btnhapusdatalokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_sign_40px.png"))); // NOI18N
        btnhapusdatalokasi.setText("Hapus");
        btnhapusdatalokasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusdatalokasiActionPerformed(evt);
            }
        });

        btnbersihkan1.setBackground(new java.awt.Color(255, 255, 255));
        btnbersihkan1.setForeground(new java.awt.Color(51, 51, 51));
        btnbersihkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_clear_formatting_40px.png"))); // NOI18N
        btnbersihkan1.setText("Bersihkan");
        btnbersihkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkan1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataLokasiPanelLayout = new javax.swing.GroupLayout(dataLokasiPanel);
        dataLokasiPanel.setLayout(dataLokasiPanelLayout);
        dataLokasiPanelLayout.setHorizontalGroup(
            dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(jLabel10))
                    .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                                .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfkdLokasi)
                                    .addComponent(tfnamaLokasi, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addComponent(btnubahdatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                                .addComponent(btnhapusdatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnbersihkan1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1791, Short.MAX_VALUE))
            .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                    .addGap(300, 300, 300)
                    .addComponent(btnsimpandatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1733, Short.MAX_VALUE)))
        );
        dataLokasiPanelLayout.setVerticalGroup(
            dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel10)
                .addGap(71, 71, 71)
                .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                        .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfkdLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfnamaLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(63, 63, 63)
                        .addComponent(btnubahdatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapusdatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbersihkan1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(421, Short.MAX_VALUE))
            .addGroup(dataLokasiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataLokasiPanelLayout.createSequentialGroup()
                    .addGap(307, 307, 307)
                    .addComponent(btnsimpandatalokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(585, Short.MAX_VALUE)))
        );

        dataasetpanel.setBackground(new java.awt.Color(255, 255, 255));
        dataasetpanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Data Aset");

        tbldataasset.setBackground(new java.awt.Color(255, 255, 255));
        tbldataasset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "KODE ASET", "NAMA ASET"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldataasset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldataassetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldataasset);

        tfkdaset.setBackground(new java.awt.Color(255, 255, 255));

        tfnamaaset.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Kode Aset");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Nama Aset");

        btnubahAset.setBackground(new java.awt.Color(255, 255, 255));
        btnubahAset.setForeground(new java.awt.Color(51, 51, 51));
        btnubahAset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_edit_40px_1.png"))); // NOI18N
        btnubahAset.setText("Ubah");
        btnubahAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahAsetActionPerformed(evt);
            }
        });

        btnsimpanAset.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpanAset.setForeground(new java.awt.Color(51, 51, 51));
        btnsimpanAset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_save_40px.png"))); // NOI18N
        btnsimpanAset.setText("Simpan");
        btnsimpanAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanAsetActionPerformed(evt);
            }
        });

        btnhapusAset.setBackground(new java.awt.Color(255, 255, 255));
        btnhapusAset.setForeground(new java.awt.Color(51, 51, 51));
        btnhapusAset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_sign_40px.png"))); // NOI18N
        btnhapusAset.setText("Hapus");
        btnhapusAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusAsetActionPerformed(evt);
            }
        });

        btnbersihkan.setBackground(new java.awt.Color(255, 255, 255));
        btnbersihkan.setForeground(new java.awt.Color(51, 51, 51));
        btnbersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_clear_formatting_40px.png"))); // NOI18N
        btnbersihkan.setText("Bersihkan");
        btnbersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataasetpanelLayout = new javax.swing.GroupLayout(dataasetpanel);
        dataasetpanel.setLayout(dataasetpanelLayout);
        dataasetpanelLayout.setHorizontalGroup(
            dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataasetpanelLayout.createSequentialGroup()
                .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataasetpanelLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(jLabel5))
                    .addGroup(dataasetpanelLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dataasetpanelLayout.createSequentialGroup()
                                .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfkdaset)
                                    .addComponent(tfnamaaset, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                            .addComponent(btnubahAset, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dataasetpanelLayout.createSequentialGroup()
                                .addComponent(btnhapusAset, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnbersihkan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataasetpanelLayout.createSequentialGroup()
                    .addGap(300, 300, 300)
                    .addComponent(btnsimpanAset, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1739, Short.MAX_VALUE)))
        );
        dataasetpanelLayout.setVerticalGroup(
            dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataasetpanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addGap(71, 71, 71)
                .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataasetpanelLayout.createSequentialGroup()
                        .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfkdaset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(29, 29, 29)
                        .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfnamaaset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(63, 63, 63)
                        .addComponent(btnubahAset, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapusAset, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbersihkan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(413, Short.MAX_VALUE))
            .addGroup(dataasetpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(dataasetpanelLayout.createSequentialGroup()
                    .addGap(307, 307, 307)
                    .addComponent(btnsimpanAset, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(577, Short.MAX_VALUE)))
        );

        TransaksiAsetMasukPanel.setBackground(new java.awt.Color(255, 255, 255));
        TransaksiAsetMasukPanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Transkasi Aset Masuk");

        tblTransaksiAsetMasuk.setBackground(new java.awt.Color(255, 255, 255));
        tblTransaksiAsetMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "KODE ", "Tanggal ", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransaksiAsetMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiAsetMasukMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTransaksiAsetMasuk);

        tfkdmasuk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Kode Masuk");

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Tanggal");

        btnsimpantransaksimasuk.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpantransaksimasuk.setForeground(new java.awt.Color(51, 51, 51));
        btnsimpantransaksimasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_save_40px.png"))); // NOI18N
        btnsimpantransaksimasuk.setText("Simpan");
        btnsimpantransaksimasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpantransaksimasukActionPerformed(evt);
            }
        });

        btnhapustransaksimasuk.setBackground(new java.awt.Color(255, 255, 255));
        btnhapustransaksimasuk.setForeground(new java.awt.Color(51, 51, 51));
        btnhapustransaksimasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_sign_40px.png"))); // NOI18N
        btnhapustransaksimasuk.setText("Hapus");
        btnhapustransaksimasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapustransaksimasukActionPerformed(evt);
            }
        });

        btnbersihkan2.setBackground(new java.awt.Color(255, 255, 255));
        btnbersihkan2.setForeground(new java.awt.Color(51, 51, 51));
        btnbersihkan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_clear_formatting_40px.png"))); // NOI18N
        btnbersihkan2.setText("Bersihkan");
        btnbersihkan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkan2ActionPerformed(evt);
            }
        });

        dctnggalmasuk.setBackground(new java.awt.Color(255, 255, 255));
        dctnggalmasuk.setForeground(new java.awt.Color(255, 255, 255));

        cbbxkdAset.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Aset" }));
        cbbxkdAset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbxkdAsetActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("kd Aset");

        tfNamaAsetTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        tfNamaAsetTransaksi.setFocusable(false);
        tfNamaAsetTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaAsetTransaksiActionPerformed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Nama Aset");

        javax.swing.GroupLayout TransaksiAsetMasukPanelLayout = new javax.swing.GroupLayout(TransaksiAsetMasukPanel);
        TransaksiAsetMasukPanel.setLayout(TransaksiAsetMasukPanelLayout);
        TransaksiAsetMasukPanelLayout.setHorizontalGroup(
            TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(jLabel17))
                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                        .addComponent(btnsimpantransaksimasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btnhapustransaksimasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel18)
                                            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel20)
                                                    .addComponent(jLabel19)
                                                    .addComponent(jLabel21))
                                                .addGap(13, 13, 13)))
                                        .addGap(18, 18, 18)
                                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfNamaAsetTransaksi)
                                            .addComponent(cbbxkdAset, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfkdmasuk)
                                            .addComponent(dctnggalmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnbersihkan2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TransaksiAsetMasukPanelLayout.setVerticalGroup(
            TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfkdmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(TransaksiAsetMasukPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(dctnggalmasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbxkdAset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addGap(28, 28, 28)
                                .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfNamaAsetTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGap(32, 32, 32)))
                        .addGroup(TransaksiAsetMasukPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpantransaksimasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhapustransaksimasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbersihkan2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(443, Short.MAX_VALUE))
        );

        PenempatanPanel.setBackground(new java.awt.Color(255, 255, 255));
        PenempatanPanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Data Penempatan Aset");

        tfkodePenempatan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Kode Penempatan");

        jLabel24.setBackground(new java.awt.Color(0, 0, 0));
        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Kode Aset");

        btnsimpanPenempatan.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpanPenempatan.setForeground(new java.awt.Color(51, 51, 51));
        btnsimpanPenempatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_save_40px.png"))); // NOI18N
        btnsimpanPenempatan.setText("Simpan");
        btnsimpanPenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanPenempatanActionPerformed(evt);
            }
        });

        btnhapusPenempatan.setBackground(new java.awt.Color(255, 255, 255));
        btnhapusPenempatan.setForeground(new java.awt.Color(51, 51, 51));
        btnhapusPenempatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_sign_40px.png"))); // NOI18N
        btnhapusPenempatan.setText("Hapus");
        btnhapusPenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusPenempatanActionPerformed(evt);
            }
        });

        btnbersihkan3.setBackground(new java.awt.Color(255, 255, 255));
        btnbersihkan3.setForeground(new java.awt.Color(51, 51, 51));
        btnbersihkan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_clear_formatting_40px.png"))); // NOI18N
        btnbersihkan3.setText("Bersihkan");
        btnbersihkan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkan3ActionPerformed(evt);
            }
        });

        cbbxkdlokasipenempatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Lokasi" }));
        cbbxkdlokasipenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbxkdlokasipenempatanActionPerformed(evt);
            }
        });

        jLabel25.setBackground(new java.awt.Color(0, 0, 0));
        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Kode Lokasi");

        tfNamaAsetPenempatan.setBackground(new java.awt.Color(255, 255, 255));
        tfNamaAsetPenempatan.setFocusable(false);
        tfNamaAsetPenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaAsetPenempatanActionPerformed(evt);
            }
        });

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Nama Aset");

        cbbxkdAsetPenempatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Aset" }));
        cbbxkdAsetPenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbxkdAsetPenempatanActionPerformed(evt);
            }
        });

        tfnamalokasiPenempatan.setBackground(new java.awt.Color(255, 255, 255));
        tfnamalokasiPenempatan.setFocusable(false);
        tfnamalokasiPenempatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnamalokasiPenempatanActionPerformed(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Nama Lokasi");

        tblPenempatannn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPenempatannn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenempatannnMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblPenempatannn);

        javax.swing.GroupLayout PenempatanPanelLayout = new javax.swing.GroupLayout(PenempatanPanel);
        PenempatanPanel.setLayout(PenempatanPanelLayout);
        PenempatanPanelLayout.setHorizontalGroup(
            PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenempatanPanelLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PenempatanPanelLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(tfnamalokasiPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PenempatanPanelLayout.createSequentialGroup()
                                .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel26))
                                .addGap(18, 18, 18)
                                .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNamaAsetPenempatan, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(tfkodePenempatan)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PenempatanPanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(cbbxkdAsetPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PenempatanPanelLayout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jLabel25)
                            .addGap(18, 18, 18)
                            .addComponent(cbbxkdlokasipenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PenempatanPanelLayout.createSequentialGroup()
                        .addComponent(btnsimpanPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbersihkan3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnhapusPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGap(40, 40, 40)
                .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap(1979, Short.MAX_VALUE))
        );
        PenempatanPanelLayout.setVerticalGroup(
            PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenempatanPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PenempatanPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfkodePenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addGap(21, 21, 21)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(cbbxkdAsetPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNamaAsetPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(26, 26, 26)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbxkdlokasipenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(27, 27, 27)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfnamalokasiPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(39, 39, 39)
                        .addGroup(PenempatanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapusPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsimpanPenempatan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbersihkan3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PenempatanPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        TransaksiAsetKeluarPanel.setBackground(new java.awt.Color(255, 255, 255));
        TransaksiAsetKeluarPanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Transkasi Aset Keluar");

        tblTransaksiAsetkeluar.setBackground(new java.awt.Color(255, 255, 255));
        tblTransaksiAsetkeluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "KODE ", "Tanggal ", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransaksiAsetkeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiAsetkeluarMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTransaksiAsetkeluar);

        tfkdkeluar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Kode Keluar");

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Tanggal");

        btnsimpantransaksikeluar.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpantransaksikeluar.setForeground(new java.awt.Color(51, 51, 51));
        btnsimpantransaksikeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_save_40px.png"))); // NOI18N
        btnsimpantransaksikeluar.setText("Simpan");
        btnsimpantransaksikeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpantransaksikeluarActionPerformed(evt);
            }
        });

        btnhapustransaksikeluar.setBackground(new java.awt.Color(255, 255, 255));
        btnhapustransaksikeluar.setForeground(new java.awt.Color(51, 51, 51));
        btnhapustransaksikeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_sign_40px.png"))); // NOI18N
        btnhapustransaksikeluar.setText("Hapus");
        btnhapustransaksikeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapustransaksikeluarActionPerformed(evt);
            }
        });

        btnbersihkan4.setBackground(new java.awt.Color(255, 255, 255));
        btnbersihkan4.setForeground(new java.awt.Color(51, 51, 51));
        btnbersihkan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_clear_formatting_40px.png"))); // NOI18N
        btnbersihkan4.setText("Bersihkan");
        btnbersihkan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbersihkan4ActionPerformed(evt);
            }
        });

        dctnggalkeluar.setBackground(new java.awt.Color(255, 255, 255));
        dctnggalkeluar.setForeground(new java.awt.Color(255, 255, 255));

        cbbxkdasetkeluar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kode Aset" }));
        cbbxkdasetkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbxkdasetkeluarActionPerformed(evt);
            }
        });

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("kd Aset");

        tfNamaAsetTransaksikeluar.setBackground(new java.awt.Color(255, 255, 255));
        tfNamaAsetTransaksikeluar.setFocusable(false);
        tfNamaAsetTransaksikeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaAsetTransaksikeluarActionPerformed(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(0, 0, 0));
        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Nama Aset");

        javax.swing.GroupLayout TransaksiAsetKeluarPanelLayout = new javax.swing.GroupLayout(TransaksiAsetKeluarPanel);
        TransaksiAsetKeluarPanel.setLayout(TransaksiAsetKeluarPanelLayout);
        TransaksiAsetKeluarPanelLayout.setHorizontalGroup(
            TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                        .addGap(611, 611, 611)
                        .addComponent(jLabel28))
                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                        .addComponent(btnsimpantransaksikeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btnhapustransaksikeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel29)
                                            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel31)
                                                    .addComponent(jLabel30)
                                                    .addComponent(jLabel32))
                                                .addGap(13, 13, 13)))
                                        .addGap(18, 18, 18)
                                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfNamaAsetTransaksikeluar)
                                            .addComponent(cbbxkdasetkeluar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfkdkeluar)
                                            .addComponent(dctnggalkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnbersihkan4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1321, Short.MAX_VALUE))
        );
        TransaksiAsetKeluarPanelLayout.setVerticalGroup(
            TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfkdkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(TransaksiAsetKeluarPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(dctnggalkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbxkdasetkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addGap(28, 28, 28)
                                .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfNamaAsetTransaksikeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32))
                                .addGap(32, 32, 32)))
                        .addGroup(TransaksiAsetKeluarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpantransaksikeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhapustransaksikeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbersihkan4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );

        LaporanPanel.setBackground(new java.awt.Color(255, 255, 255));
        LaporanPanel.setPreferredSize(new java.awt.Dimension(1200, 859));

        jLabel34.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Laporan");

        btncETAK.setBackground(new java.awt.Color(255, 255, 255));
        btncETAK.setForeground(new java.awt.Color(51, 51, 51));
        btncETAK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_print_50px.png"))); // NOI18N
        btncETAK.setText("Cetak");
        btncETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncETAKActionPerformed(evt);
            }
        });

        tblLaporan.setBackground(new java.awt.Color(255, 255, 255));
        tblLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "KODE ", "Tanggal ", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLaporanMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblLaporan);

        javax.swing.GroupLayout LaporanPanelLayout = new javax.swing.GroupLayout(LaporanPanel);
        LaporanPanel.setLayout(LaporanPanelLayout);
        LaporanPanelLayout.setHorizontalGroup(
            LaporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LaporanPanelLayout.createSequentialGroup()
                .addGroup(LaporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LaporanPanelLayout.createSequentialGroup()
                        .addGap(638, 638, 638)
                        .addComponent(jLabel34))
                    .addGroup(LaporanPanelLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addGroup(LaporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncETAK, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1626, Short.MAX_VALUE))
        );
        LaporanPanelLayout.setVerticalGroup(
            LaporanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LaporanPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addGap(40, 40, 40)
                .addComponent(btncETAK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(441, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appbarpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dashboardpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 3128, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dataasetpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 3128, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(dataLokasiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 3122, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TransaksiAsetMasukPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 3128, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(PenempatanPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 3122, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(TransaksiAsetKeluarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 2640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(482, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(LaporanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 2884, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 244, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(appbarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboardpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(191, 191, 191)
                    .addComponent(dataasetpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(273, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(191, 191, 191)
                    .addComponent(dataLokasiPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(265, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(205, 205, 205)
                    .addComponent(TransaksiAsetMasukPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(261, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(198, 198, 198)
                    .addComponent(PenempatanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(266, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(209, 209, 209)
                    .addComponent(TransaksiAsetKeluarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(233, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(202, 202, 202)
                    .addComponent(LaporanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(221, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndataActionPerformed

     submenuDataPanel.setVisible(true);
     submenuTransaksi.setVisible(false);
     
       btndata.setBackground(Color.cyan);
       btndashboard.setBackground(Color.pink);
       btnTransaksi.setBackground(Color.pink);
       btnAsetmasuk.setBackground(Color.pink);
       btnAsetkeluar.setBackground(Color.pink);
       btnpenempatan.setBackground(Color.pink);
       btnLaporan.setBackground(Color.pink);
       btnLaporan.setBackground(Color.pink);
    }//GEN-LAST:event_btndataActionPerformed

    private void btndashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndashboardActionPerformed
        dashboardpanel.show(true);
        submenuDataPanel.setVisible(false);
       submenuTransaksi.setVisible(false);
       LaporanPanel.show(false);
      dataasetpanel.show(false);
      dataLokasiPanel.show(false);
      PenempatanPanel.show(false);
      TransaksiAsetKeluarPanel.show(false);
              
       
       btndashboard.setBackground(Color.CYAN);
       btnLaporan.setBackground(Color.pink);
       btnTransaksi.setBackground(Color.pink);
       btnAsetmasuk.setBackground(Color.pink);
       btnAsetkeluar.setBackground(Color.pink);
       btnpenempatan.setBackground(Color.pink);
       
       btndata.setBackground(Color.pink);
       btndataasset.setBackground(Color.pink);
       btnLokasi.setBackground(Color.pink);
    }//GEN-LAST:event_btndashboardActionPerformed

    private void btndataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndataMousePressed
    
    }//GEN-LAST:event_btndataMousePressed

    private void btndataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndataMouseReleased
      
    }//GEN-LAST:event_btndataMouseReleased

    private void tbldataassetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldataassetMouseClicked
    int bar = tbldataasset.getSelectedRow();
    String no = tabmodel.getValueAt(bar, 0).toString();
        String kd = tabmodel.getValueAt(bar, 1).toString();
        String nama = tabmodel.getValueAt(bar, 2).toString();
        
        tfkdaset.setText(kd);
        tfnamaaset.setText(nama);        // TODO add your handling code here:
    }//GEN-LAST:event_tbldataassetMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
   LaporanPanel.show(false);
        dashboardpanel.show(true);
        dataasetpanel.show(false);
       dataLokasiPanel.show(false);
       PenempatanPanel.show(false);
       TransaksiAsetKeluarPanel.show(false);
       TransaksiAsetMasukPanel.show(false);
submenuTransaksi.setVisible(false);
submenuDataPanel.setVisible(false);
       btndashboard.setBackground(Color.CYAN);
    }//GEN-LAST:event_formWindowOpened

    private void btnsimpanAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanAsetActionPerformed
       String sql = "insert into aset values (?,?)";
            
           

            try {
                PreparedStatement stat = koneks.prepareStatement(sql);
                stat.setString(1, tfkdaset.getText());
                stat.setString(2, tfnamaaset.getText());               
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");                
                bersihkan();
                isiTabledataAset();
                tfkdaset.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
            }
    }//GEN-LAST:event_btnsimpanAsetActionPerformed

    private void btnubahAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahAsetActionPerformed
        String sql = "update aset set kd_aset=?,nama_aset=? where kd_aset='"+tfkdaset.getText()+"'";
       
       
        try {
            PreparedStatement stat = koneks.prepareStatement(sql);
           
            stat.setString(1, tfkdaset.getText());
            stat.setString(2, tfnamaaset.getText());            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            bersihkan();
            isiTabledataAset();
           
            tfkdaset.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
    }//GEN-LAST:event_btnubahAsetActionPerformed

    private void btnhapusAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusAsetActionPerformed
     int hapus = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin Menghapus Data","Konfirmasi ", JOptionPane.YES_NO_OPTION);
        if (hapus==0){
            String sql="delete from aset where kd_aset='"+tfkdaset.getText()+"'";
            try {
                PreparedStatement stat=koneks.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                bersihkan();
                isiTabledataAset();                
                tfkdaset.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_btnhapusAsetActionPerformed

    private void btnbersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkanActionPerformed
      bersihkan();
    }//GEN-LAST:event_btnbersihkanActionPerformed

    private void btndataassetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndataassetActionPerformed
        dataasetpanel.show(true);
        dashboardpanel.show(false);
        dataLokasiPanel.show(false);
        LaporanPanel.show(false);
       TransaksiAsetMasukPanel.show(false);
        TransaksiAsetKeluarPanel.show(false);
       PenempatanPanel.show(false);
       
        btndataasset.setBackground(Color.cyan);
        btnLokasi.setBackground(Color.pink);
    }//GEN-LAST:event_btndataassetActionPerformed

    private void btndataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndataMouseClicked
      submenuDataPanel.show(true);
    }//GEN-LAST:event_btndataMouseClicked

    private void btnLokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLokasiActionPerformed
     
        dataLokasiPanel.show(true);
     dataasetpanel.show(false);
     LaporanPanel.show(false);
     LaporanPanel.show(false);
        dashboardpanel.show(false);
        PenempatanPanel.show(false);
        TransaksiAsetMasukPanel.show(false);
        TransaksiAsetKeluarPanel.show(false);
        
        
        btnLokasi.setBackground(Color.cyan);
        btndataasset.setBackground(Color.pink);
      
    }//GEN-LAST:event_btnLokasiActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void tblDataLokasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataLokasiMouseClicked
         int bar = tblDataLokasi.getSelectedRow();
    String no = tabmodel.getValueAt(bar, 0).toString();
        String kd = tabmodel.getValueAt(bar, 1).toString();
        String nama = tabmodel.getValueAt(bar, 2).toString();
        
        tfkdLokasi.setText(kd);
        tfnamaLokasi.setText(nama);
    }//GEN-LAST:event_tblDataLokasiMouseClicked

    private void btnubahdatalokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahdatalokasiActionPerformed
       String sql = "update lokasi set kd_lokasi=?,nama_lokasi=? where kd_lokasi='"+tfkdLokasi.getText()+"'";
       
       
        try {
            PreparedStatement stat = koneks.prepareStatement(sql);
           
            stat.setString(1, tfkdLokasi.getText());
            stat.setString(2, tfnamaLokasi.getText());            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            bersihkan();
            isiTabledataLokasi();
           
            tfkdLokasi.requestFocus();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
    }//GEN-LAST:event_btnubahdatalokasiActionPerformed

    private void btnsimpandatalokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpandatalokasiActionPerformed
      String sql = "insert into lokasi values (?,?)";
            
           

            try {
                PreparedStatement stat = koneks.prepareStatement(sql);
                stat.setString(1, tfkdLokasi.getText());
                stat.setString(2, tfnamaLokasi.getText());               
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");                
                bersihkan();
                isiTabledataLokasi();
                tfkdLokasi.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
            }
    }//GEN-LAST:event_btnsimpandatalokasiActionPerformed

    private void btnhapusdatalokasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusdatalokasiActionPerformed
     int hapus = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin Menghapus Data","Konfirmasi ", JOptionPane.YES_NO_OPTION);
        if (hapus==0){
            String sql="delete from lokasi where kd_lokasi='"+tfkdLokasi.getText()+"'";
            try {
                PreparedStatement stat=koneks.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                bersihkan();
                isiTabledataLokasi();                
                tfkdLokasi.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_btnhapusdatalokasiActionPerformed

    private void btnbersihkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkan1ActionPerformed
       bersihkan();
    }//GEN-LAST:event_btnbersihkan1ActionPerformed

    private void btnTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaksiMouseClicked

    private void btnTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaksiMousePressed

    private void btnTransaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTransaksiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaksiMouseReleased

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaksiActionPerformed
       submenuTransaksi.setVisible(true);
        submenuDataPanel.setVisible(false);
        
          
        
         btnTransaksi.setBackground(Color.cyan);
         btnLaporan.setBackground(Color.pink);
         btndata.setBackground(Color.pink);
         btndataasset.setBackground(Color.pink);
       btnLokasi.setBackground(Color.pink);
       btndashboard.setBackground(Color.pink);
    }//GEN-LAST:event_btnTransaksiActionPerformed

    private void btnAsetmasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsetmasukActionPerformed
     TransaksiAsetMasukPanel.show(true);
      dataLokasiPanel.setVisible(false);
      dataasetpanel.setVisible(false);
      LaporanPanel.show(false);
        submenuDataPanel.setVisible(false);
        dashboardpanel.setVisible(false);
        PenempatanPanel.setVisible(false);
        TransaksiAsetKeluarPanel.show(false);
        
       
        
        
        btnAsetmasuk.setBackground(Color.cyan);
       btnAsetkeluar.setBackground(Color.pink);
        btnpenempatan.setBackground(Color.pink);
    }//GEN-LAST:event_btnAsetmasukActionPerformed

    private void btnAsetkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsetkeluarActionPerformed
        TransaksiAsetKeluarPanel.show(true);
        TransaksiAsetMasukPanel.show(false);
        LaporanPanel.show(false);
        
      dataLokasiPanel.setVisible(false);
      dataasetpanel.setVisible(false);
        submenuDataPanel.setVisible(false);
        dashboardpanel.setVisible(false);
        PenempatanPanel.setVisible(false);
       
        
        btnLaporan.setBackground(Color.pink);
        btnAsetkeluar.setBackground(Color.cyan);
       btnAsetmasuk.setBackground(Color.pink);
        btnpenempatan.setBackground(Color.pink);
    }//GEN-LAST:event_btnAsetkeluarActionPerformed

    private void btnpenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpenempatanActionPerformed
       PenempatanPanel.show(true);
       TransaksiAsetMasukPanel.show(false);
       TransaksiAsetKeluarPanel.show(false);
      dataLokasiPanel.setVisible(false);
      dataasetpanel.setVisible(false);
        submenuDataPanel.setVisible(false);
        dashboardpanel.setVisible(false);
       
        btnpenempatan.setBackground(Color.cyan);
       btnAsetkeluar.setBackground(Color.pink);
        btnAsetmasuk.setBackground(Color.pink);
    }//GEN-LAST:event_btnpenempatanActionPerformed

    private void tblTransaksiAsetMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiAsetMasukMouseClicked
      int bar = tblTransaksiAsetMasuk.getSelectedRow();
    String no = tabmodel.getValueAt(bar, 0).toString();
        String kd = tabmodel.getValueAt(bar, 1).toString();
        String tgl = tabmodel.getValueAt(bar, 2).toString();
         String kdaset = tabmodel.getValueAt(bar, 3).toString();
        
        tfkdmasuk.setText(kd);
      
        cbbxkdAset.setSelectedItem(kdaset);
    }//GEN-LAST:event_tblTransaksiAsetMasukMouseClicked

    private void btnsimpantransaksimasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpantransaksimasukActionPerformed
       String sql = "insert into transaksi_masuk values (?,?,?)";
            
           

            try {
                PreparedStatement stat = koneks.prepareStatement(sql);
                stat.setString(1, tfkdmasuk.getText());
                stat.setString(2, dctnggalmasuk.getDate().toString()); 
                stat.setString(3, cbbxkdAset.getItemAt(1));
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");                
                bersihkan();
                isiTableAsetmasuk();
                tfkdmasuk.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
            }
    }//GEN-LAST:event_btnsimpantransaksimasukActionPerformed

    private void btnhapustransaksimasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapustransaksimasukActionPerformed
     int hapus = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin Menghapus Data","Konfirmasi ", JOptionPane.YES_NO_OPTION);
        if (hapus==0){
            String sql="delete from transaksi_masuk where kd_masuk='"+tfkdmasuk.getText()+"'";
            try {
                PreparedStatement stat=koneks.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                bersihkan();
                isiTableAsetmasuk();                
                tfkdmasuk.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_btnhapustransaksimasukActionPerformed

    private void btnbersihkan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkan2ActionPerformed
bersihkan();        // TODO add your handling code here:
    }//GEN-LAST:event_btnbersihkan2ActionPerformed

    private void cbbxkdAsetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbxkdAsetActionPerformed
        try {
            String sql="select * from aset where kd_aset='"+cbbxkdAset.getSelectedItem()+"'";
             java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {                
                tfNamaAsetTransaksi.setText(hasil.getString("nama_aset"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbxkdAsetActionPerformed

    private void tfNamaAsetTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaAsetTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaAsetTransaksiActionPerformed

    private void btnsimpanPenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanPenempatanActionPerformed
      String sql = "insert into penempatan values (?,?,?)";
            
           

            try {
                PreparedStatement stat = koneks.prepareStatement(sql);
                stat.setString(1, tfkodePenempatan.getText());
                stat.setString(2, cbbxkdAsetPenempatan.getItemAt(1)); 
                stat.setString(3, cbbxkdlokasipenempatan.getItemAt(1));
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");                
                bersihkan();
                isiTablePenempatan();
                tfkodePenempatan.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
            }
    }//GEN-LAST:event_btnsimpanPenempatanActionPerformed

    private void btnhapusPenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusPenempatanActionPerformed
       int hapus = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin Menghapus Data","Konfirmasi ", JOptionPane.YES_NO_OPTION);
        if (hapus==0){
            String sql="delete from penempatan where kd_penempatan='"+tfkodePenempatan.getText()+"'";
            try {
                PreparedStatement stat=koneks.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                bersihkan();
                isiTablePenempatan();                
                tfkodePenempatan.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_btnhapusPenempatanActionPerformed

    private void btnbersihkan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkan3ActionPerformed
       bersihkan();
    }//GEN-LAST:event_btnbersihkan3ActionPerformed

    private void cbbxkdlokasipenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbxkdlokasipenempatanActionPerformed
      try {
            String sql="select * from lokasi where kd_lokasi='"+cbbxkdlokasipenempatan.getSelectedItem()+"'";
             java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {                
                tfnamalokasiPenempatan.setText(hasil.getString("nama_lokasi"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbxkdlokasipenempatanActionPerformed

    private void tfNamaAsetPenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaAsetPenempatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaAsetPenempatanActionPerformed

    private void cbbxkdAsetPenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbxkdAsetPenempatanActionPerformed
       try {
            String sql="select * from aset where kd_aset='"+cbbxkdAsetPenempatan.getSelectedItem()+"'";
             java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {                
                tfNamaAsetPenempatan.setText(hasil.getString("nama_aset"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbxkdAsetPenempatanActionPerformed

    private void tfnamalokasiPenempatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnamalokasiPenempatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnamalokasiPenempatanActionPerformed

    private void tblPenempatannnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenempatannnMouseClicked
    int bar = tblPenempatannn.getSelectedRow();
    
    String no = tabmodel.getValueAt(bar, 0).toString();
        String kd = tabmodel.getValueAt(bar, 1).toString();
        String cb1 = tabmodel.getValueAt(bar, 2).toString();
        String cb2 = tabmodel.getValueAt(bar, 3).toString();
        
        tfkodePenempatan.setText(kd);
        cbbxkdAsetPenempatan.setSelectedItem(cb1);
        cbbxkdlokasipenempatan.setSelectedItem(cb2);
    }//GEN-LAST:event_tblPenempatannnMouseClicked

    private void tblTransaksiAsetkeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiAsetkeluarMouseClicked
          int bar = tblTransaksiAsetkeluar.getSelectedRow();
    String no = tabmodel.getValueAt(bar, 0).toString();
        String kd = tabmodel.getValueAt(bar, 1).toString();
        String tgl = tabmodel.getValueAt(bar, 2).toString();
         String kdaset = tabmodel.getValueAt(bar, 3).toString();
        
        tfkdkeluar.setText(kd);
      
        cbbxkdasetkeluar.setSelectedItem(kdaset);
    }//GEN-LAST:event_tblTransaksiAsetkeluarMouseClicked

    private void btnsimpantransaksikeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpantransaksikeluarActionPerformed
        String sql = "insert into transaksi_keluar values (?,?,?)";
            
           

            try {
                PreparedStatement stat = koneks.prepareStatement(sql);
                stat.setString(1, tfkdkeluar.getText());
                stat.setString(2, dctnggalkeluar.getDate().toString()); 
                stat.setString(3, cbbxkdasetkeluar.getItemAt(1));
                
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");                
                bersihkan();
                isiTableAsetkeluar();
                tfkdkeluar.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan"+e);
            }
    }//GEN-LAST:event_btnsimpantransaksikeluarActionPerformed

    private void btnhapustransaksikeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapustransaksikeluarActionPerformed
       int hapus = JOptionPane.showConfirmDialog (null," Apakah Anda Yakin Ingin Menghapus Data","Konfirmasi ", JOptionPane.YES_NO_OPTION);
        if (hapus==0){
            String sql="delete from transaksi_keluar where kd_keluar='"+tfkdkeluar.getText()+"'";
            try {
                PreparedStatement stat=koneks.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                bersihkan();
                isiTableAsetkeluar();                
                tfkdkeluar.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus"+e);
            }
        }
    }//GEN-LAST:event_btnhapustransaksikeluarActionPerformed

    private void btnbersihkan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbersihkan4ActionPerformed
bersihkan();        // TODO add your handling code here:
    }//GEN-LAST:event_btnbersihkan4ActionPerformed

    private void cbbxkdasetkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbxkdasetkeluarActionPerformed
       try {
            String sql="select * from aset where kd_aset='"+cbbxkdasetkeluar.getSelectedItem()+"'";
             java.sql.Statement stat = koneks.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {                
                tfNamaAsetTransaksikeluar.setText(hasil.getString("nama_aset"));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbxkdasetkeluarActionPerformed

    private void tfNamaAsetTransaksikeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaAsetTransaksikeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaAsetTransaksikeluarActionPerformed

    private void btnLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLaporanMouseClicked

    private void btnLaporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLaporanMousePressed

    private void btnLaporanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLaporanMouseReleased

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
   LaporanPanel.show(true);
        TransaksiAsetKeluarPanel.show(false);
        TransaksiAsetMasukPanel.show(false);
      dataLokasiPanel.setVisible(false);
      dataasetpanel.setVisible(false);
      submenuDataPanel.setVisible(false);
       submenuTransaksi.setVisible(false);
       
        dashboardpanel.setVisible(false);
        PenempatanPanel.setVisible(false);
        btnLaporan.setBackground(Color.cyan);
        btndashboard.setBackground(Color.pink);
       
       btnTransaksi.setBackground(Color.pink);
       btnAsetmasuk.setBackground(Color.pink);
       btnAsetkeluar.setBackground(Color.pink);
       btnpenempatan.setBackground(Color.pink);
       
       btndata.setBackground(Color.pink);
       btndataasset.setBackground(Color.pink);
       btnLokasi.setBackground(Color.pink);
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btncETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncETAKActionPerformed
   try {
            Connection konn = new koneksi().koneksikita();
            File file = new File("src/Laporan/Laporan.jrxml");
           
           
       JasperDesign JasDes = JRXmlLoader.load(file);
       JasperReport JasRep = JasperCompileManager.compileReport(JasDes);
       JasperPrint JasPri = JasperFillManager.fillReport(JasRep, null, konn);
            JasperViewer.viewReport(JasPri, false);
           
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            
            jasperViewer.setVisible(true);
           
            
      
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membuka Laporan", "Cetak laporan", JOptionPane.ERROR_MESSAGE);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_btncETAKActionPerformed

    private void tblLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLaporanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLaporanMouseClicked

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
                if ("FlatLaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LaporanPanel;
    private javax.swing.JPanel PenempatanPanel;
    private javax.swing.JPanel TransaksiAsetKeluarPanel;
    private javax.swing.JPanel TransaksiAsetMasukPanel;
    private javax.swing.JPanel appbarpanel;
    private javax.swing.JButton btnAsetkeluar;
    private javax.swing.JButton btnAsetmasuk;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLokasi;
    private javax.swing.JButton btnTransaksi;
    private javax.swing.JButton btnbersihkan;
    private javax.swing.JButton btnbersihkan1;
    private javax.swing.JButton btnbersihkan2;
    private javax.swing.JButton btnbersihkan3;
    private javax.swing.JButton btnbersihkan4;
    private javax.swing.JButton btncETAK;
    private javax.swing.JButton btndashboard;
    private javax.swing.JButton btndata;
    private javax.swing.JButton btndataasset;
    private javax.swing.JButton btnhapusAset;
    private javax.swing.JButton btnhapusPenempatan;
    private javax.swing.JButton btnhapusdatalokasi;
    private javax.swing.JButton btnhapustransaksikeluar;
    private javax.swing.JButton btnhapustransaksimasuk;
    private javax.swing.JButton btnpenempatan;
    private javax.swing.JButton btnsimpanAset;
    private javax.swing.JButton btnsimpanPenempatan;
    private javax.swing.JButton btnsimpandatalokasi;
    private javax.swing.JButton btnsimpantransaksikeluar;
    private javax.swing.JButton btnsimpantransaksimasuk;
    private javax.swing.JButton btnubahAset;
    private javax.swing.JButton btnubahdatalokasi;
    private javax.swing.JComboBox<String> cbbxkdAset;
    private javax.swing.JComboBox<String> cbbxkdAsetPenempatan;
    private javax.swing.JComboBox<String> cbbxkdasetkeluar;
    private javax.swing.JComboBox<String> cbbxkdlokasipenempatan;
    private javax.swing.JPanel dashboardpanel;
    private javax.swing.JPanel dataLokasiPanel;
    private javax.swing.JPanel dataasetpanel;
    private com.toedter.calendar.JDateChooser dctnggalkeluar;
    private com.toedter.calendar.JDateChooser dctnggalmasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel submenuDataPanel;
    private javax.swing.JPanel submenuTransaksi;
    public static javax.swing.JTable tblDataLokasi;
    public static javax.swing.JTable tblLaporan;
    private javax.swing.JTable tblPenempatannn;
    public static javax.swing.JTable tblTransaksiAsetMasuk;
    public static javax.swing.JTable tblTransaksiAsetkeluar;
    public static javax.swing.JTable tbldataasset;
    private javax.swing.JTextField tfNamaAsetPenempatan;
    private javax.swing.JTextField tfNamaAsetTransaksi;
    private javax.swing.JTextField tfNamaAsetTransaksikeluar;
    private javax.swing.JTextField tfkdLokasi;
    private javax.swing.JTextField tfkdaset;
    private javax.swing.JTextField tfkdkeluar;
    private javax.swing.JTextField tfkdmasuk;
    private javax.swing.JTextField tfkodePenempatan;
    private javax.swing.JTextField tfnamaLokasi;
    private javax.swing.JTextField tfnamaaset;
    private javax.swing.JTextField tfnamalokasiPenempatan;
    // End of variables declaration//GEN-END:variables
}
