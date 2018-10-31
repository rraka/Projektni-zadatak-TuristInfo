/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turistInfo.admin;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author raka
 */
public class IzmjeniTuristickuAtrakcijuGUI extends javax.swing.JFrame {

    /**
     * Creates new form IzmjeniTuristickuAtrakcijuGUI
     */
    private GlavnaAdminFormaGUI glavnaForma;
    private int atrakcijaZaIzmjenu;
    private JTable tabelaAtrakcija;
    private ArrayList<TuristickaAtrakcija> sveTuristickeAtrakcije;
    private String odabraniDokument;

    public IzmjeniTuristickuAtrakcijuGUI(GlavnaAdminFormaGUI glavnaForma, int atrakcijaZaIzmjenu, JTable tabelaAtrakcija) {
        initComponents();
        this.glavnaForma = glavnaForma;
        this.tabelaAtrakcija = tabelaAtrakcija;
        this.atrakcijaZaIzmjenu = atrakcijaZaIzmjenu;
        this.sveTuristickeAtrakcije = glavnaForma.getSveTuristickeAtrakcije();

        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinerCijena.getEditor()).getTextField();
        NumberFormatter formatter = (NumberFormatter) tf.getFormatter();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        formatter.setFormat(decimalFormat);
        formatter.setAllowsInvalid(false);
        SpinnerNumberModel snm = (SpinnerNumberModel) spinerCijena.getModel();
        snm.setMinimum(0);
        /*  JFormattedTextField tf = ((JSpinner.DefaultEditor) spinerCijena.getEditor()).getTextField();
        NumberFormatter formatter = (NumberFormatter) tf.getFormatter();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        formatter.setFormat(decimalFormat);
        formatter.setAllowsInvalid(false);*/

        if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof Muzej) {
            Muzej muzej = (Muzej) sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu);
            this.setTitle("Izmjena muzeja");
            labelaNaslov1.setText("IZMJENI");
            labelaNaslov2.setText("MUZEJ");
            labelaOpis.setVisible(false);
            //  tekstAreaOpis.setVisible(false);
            panelOpis.setVisible(false);
            labelaZaSliku.setVisible(false);
            labelaIzabranaSFotografija.setVisible(false);
            Calendar datum = Calendar.getInstance();
            int dan = datum.get(Calendar.DAY_OF_WEEK);
            tekstFieldNaziv.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getNaziv());
            tekstFieldLokacija.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getLokacija());
            File fajl = new File(muzej.getLetak());
            odabraniDokument = muzej.getLetak();
            labelaNazivOdabranogDokumenta.setText(fajl.getName());
            if (dan % 2 == 0) {
                labelaNePlacaSe.setText("Ne placa se!");
                labelaNePlacaSe.setForeground(Color.BLACK);
                JFormattedTextField tf1 = ((JSpinner.DefaultEditor) spinerCijena.getEditor()).getTextField();
                tf1.setEditable(false);
                tf1.setEnabled(false);
                spinerCijena.setEnabled(false);

            } else {
                JFormattedTextField tf1 = ((JSpinner.DefaultEditor) spinerCijena.getEditor()).getTextField();

                spinerCijena.setValue((muzej.getCijenaUlaznice()));
                // tf1.setEditable(true);
                //  tf1.setEnabled(true);
                // spinerCijena.setEnabled(true);
            }

        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof Crkva) {
            labelaNaslov1.setText("IZMJENI");
            labelaNaslov2.setText("CRKVU");
            this.setTitle("Izmjena crkve");
            labelaOpis.setVisible(false);
            //  tekstAreaOpis.setVisible(false);
            panelOpis.setVisible(false);
            labelaZaSliku.setVisible(false);
            labelaIzabranaSFotografija.setVisible(false);
            labelaCIjenaUlaznice.setVisible(false);
            spinerCijena.setVisible(false);
            labelaDokument.setVisible(false);
            dugmeIzaberiDokument.setVisible(false);
            tekstFieldNaziv.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getNaziv());
            tekstFieldLokacija.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getLokacija());

        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof IstorijskiSpomenik) {
            IstorijskiSpomenik is = (IstorijskiSpomenik) sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu);
            labelaNaslov1.setText("IZMJENI");
            labelaNaslov2.setText("ISTORIJSKI SPOEMNIK");
            this.setTitle("Izmjena istorijskog spomenika");
            labelaCIjenaUlaznice.setVisible(false);
            spinerCijena.setVisible(false);
            tekstFieldNaziv.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getNaziv());
            tekstFieldLokacija.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getLokacija());
            //labelaDokument.setText(is.getFotografija());
            tekstAreaOpis.setText(is.getOpis());
            File fajl = new File(is.getFotografija());

            odabraniDokument = is.getFotografija();
            labelaNazivOdabranogDokumenta.setText(fajl.getName());
            //prikaz slike
            ImageIcon icon = new ImageIcon(new ImageIcon(fajl.getAbsolutePath()).getImage().getScaledInstance(labelaZaSliku.getWidth(), labelaZaSliku.getHeight(), Image.SCALE_DEFAULT));
            labelaZaSliku.setIcon(icon);

        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof ZabavniPark) {
            labelaNaslov1.setText("IZMJENI");
            labelaNaslov2.setText("ZABAVNI PARK");
            this.setTitle("Izmjena zabavnog parka");
            labelaOpis.setVisible(false);
            //  tekstAreaOpis.setVisible(false);
            panelOpis.setVisible(false);
            labelaZaSliku.setVisible(false);
            labelaIzabranaSFotografija.setVisible(false);
            labelaDokument.setVisible(false);
            dugmeIzaberiDokument.setVisible(false);
            tekstFieldNaziv.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getNaziv());
            tekstFieldLokacija.setText(sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu).getLokacija());
            ZabavniPark zp = (ZabavniPark) sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu);
            spinerCijena.setValue(zp.getCijenaUlaznice());

        }
        // String putanja = ".\\src\\hotelPalas.jpg";
        // String putanja = ".\\src\\hotelPalas.jpg" ;  //"C:\\Users\\raka\\Desktop\\JAVA PROJEKTNI JUN 2017\\PROJEKTNI - Turist info\\Turist info\\hotelPalas.jpg";
        // System.out.println("iz konstruktora: " + putanja);
        //  ImageIcon icon = new ImageIcon(putanja);//PUTANJA
        //labelaNova = new JLabel("TEKST", icon, JLabel.CENTER);
        //labelaZaSliku.setText("dasdadadadadasdas");
        // labelaZaSliku.setIcon(icon);

        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelaNaslov1 = new javax.swing.JLabel();
        labelaNaslov2 = new javax.swing.JLabel();
        labelaNazivAtrakcije = new javax.swing.JLabel();
        labelaNazivLokacija = new javax.swing.JLabel();
        labelaCIjenaUlaznice = new javax.swing.JLabel();
        labelaDokument = new javax.swing.JLabel();
        labelaOpis = new javax.swing.JLabel();
        tekstFieldNaziv = new javax.swing.JTextField();
        tekstFieldLokacija = new javax.swing.JTextField();
        dugmeIzaberiDokument = new javax.swing.JButton();
        labelaNazivOdabranogDokumenta = new javax.swing.JLabel();
        panelOpis = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tekstAreaOpis = new javax.swing.JTextArea();
        labelaZaSliku = new javax.swing.JLabel();
        labelaIzabranaSFotografija = new javax.swing.JLabel();
        spinerCijena = new javax.swing.JSpinner();
        labelaNePlacaSe = new javax.swing.JLabel();
        dugmeIzmjeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        labelaNaslov1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelaNaslov1.setText("jLabel1");

        labelaNaslov2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelaNaslov2.setText("jLabel2");

        labelaNazivAtrakcije.setText("Naziv:");

        labelaNazivLokacija.setText("Lokacija:");

        labelaCIjenaUlaznice.setText("Cijena ulaznice:");

        labelaDokument.setText("Dokument:");

        labelaOpis.setText("Opis:");

        dugmeIzaberiDokument.setText("Izaberi");
        dugmeIzaberiDokument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dugmeIzaberiDokumentActionPerformed(evt);
            }
        });

        tekstAreaOpis.setColumns(20);
        tekstAreaOpis.setLineWrap(true);
        tekstAreaOpis.setRows(5);
        jScrollPane1.setViewportView(tekstAreaOpis);

        javax.swing.GroupLayout panelOpisLayout = new javax.swing.GroupLayout(panelOpis);
        panelOpis.setLayout(panelOpisLayout);
        panelOpisLayout.setHorizontalGroup(
            panelOpisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpisLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpisLayout.setVerticalGroup(
            panelOpisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpisLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        labelaZaSliku.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelaIzabranaSFotografija.setText("Izabrana fotografija: ");

        dugmeIzmjeni.setText("Izmjeni");
        dugmeIzmjeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dugmeIzmjeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelaNaslov1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelaNaslov2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dugmeIzmjeni, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelaNazivAtrakcije)
                                        .addComponent(labelaNazivLokacija))
                                    .addGap(62, 62, 62)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tekstFieldNaziv)
                                        .addComponent(tekstFieldLokacija, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelaCIjenaUlaznice)
                                    .addGap(25, 25, 25)
                                    .addComponent(spinerCijena, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelaNePlacaSe, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(labelaOpis)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelaDokument)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelaNazivOdabranogDokumenta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dugmeIzaberiDokument, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelaZaSliku, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelaIzabranaSFotografija))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelaNaslov1)
                    .addComponent(labelaNaslov2))
                .addGap(41, 41, 41)
                .addComponent(labelaIzabranaSFotografija)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelaZaSliku, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tekstFieldNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelaNazivAtrakcije))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tekstFieldLokacija)
                            .addComponent(labelaNazivLokacija))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelaNePlacaSe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelaCIjenaUlaznice)
                                .addComponent(spinerCijena, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelaNazivOdabranogDokumenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelaDokument, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dugmeIzaberiDokument)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelaOpis))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(panelOpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addComponent(dugmeIzmjeni, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );

        getAccessibleContext().setAccessibleName("jFrameNovaAtrakcija");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dugmeIzaberiDokumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dugmeIzaberiDokumentActionPerformed
        JFileChooser fc = new JFileChooser(new File("C:\\Users\\raka\\Desktop"));
        fc.setVisible(true);

        int returnVal = fc.showOpenDialog(IzmjeniTuristickuAtrakcijuGUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fajl = fc.getSelectedFile();
            odabraniDokument = fajl.getAbsolutePath();
            labelaNazivOdabranogDokumenta.setText(fajl.getName());
            if (fajl.getName().contains(".jpg") || fajl.getName().contains(".png")) {
                ImageIcon icon = new ImageIcon(new ImageIcon(fajl.getAbsolutePath()).getImage().getScaledInstance(labelaZaSliku.getWidth(), labelaZaSliku.getHeight(), Image.SCALE_DEFAULT));
                labelaZaSliku.setIcon(icon);
            } else {
                labelaZaSliku.setIcon(null);
            }

        } else {

            // System.out.println("Ponisteno biranje fajla");
        }
        // FileDialog fd = new FileDialog(jFrame3, "Choose a file", FileDialog.LOAD);
        // fd.setDirectory("C:\\Users\\raka\\Desktop\\JAVA PROJEKTNI JUN 2017\\PROJEKTNI - Turist info\\Turist info\\src\\");
        //fd.setFile("*.jpg");
        // fd.setVisible(true);

        // String filename = new File(fd.getFile()).getPath();
        //String filename = "C:/Users/raka/Desktop/JAVA PROJEKTNI JUN 2017/PROJEKTNI - Turist info/Turist info/hotelPalas.jpg";
        //  File odabraniFajl = new File(fd.getFile());
        // String putanjaFajla = odabraniFajl.getPath();
        //labelaZaSliku.setIcon(new ImageIcon("C:\\Users\\raka\\Desktop\\JAVA PROJEKTNI JUN 2017\\PROJEKTNI - Turist info\\Turist info\\hotelPalas.jpg"));
        // System.out.println("iz biranja:: " + putanjaFajla);
        // ImageIcon icon = new ImageIcon(putanjaFajla);
        // labelaZaSliku.setIcon(icon);
        //   panelZaSliku.add(labelaZaSliku);
        //  add(panelZaSliku);
        // validate();
        // panelZaSliku.add(labelaZaSliku, BorderLayout.CENTER);
        // Image image = ImageIO.read(new File(filename));
        // panelZaSliku = new JPanel((LayoutManager) new ImageIcon(image));
    }//GEN-LAST:event_dugmeIzaberiDokumentActionPerformed

    private void dugmeIzmjeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dugmeIzmjeniActionPerformed
        if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof Muzej) {
            String naziv = tekstFieldNaziv.getText();
            String lokacija = tekstFieldLokacija.getText();
            String letak = odabraniDokument;
            double cijenaUlaznice;

            if (naziv.equals("") || lokacija.equals("")) {
                JOptionPane.showMessageDialog(null, " Polja naziv i lokacija moraju biti popunjena!");
            } else {
                Calendar datum = Calendar.getInstance();
                int dan = datum.get(Calendar.DAY_OF_WEEK);
                if (dan % 2 == 0) {
                    cijenaUlaznice = 0;

                } else if ((Integer) spinerCijena.getValue() == 0) {
                    Random rand = new Random();
                    cijenaUlaznice = rand.nextInt(71) + 10;
                } else {
                    cijenaUlaznice = (Integer) spinerCijena.getValue();
                }

                Muzej muzej = new Muzej(letak, naziv, lokacija, cijenaUlaznice);
                sveTuristickeAtrakcije.remove(atrakcijaZaIzmjenu);
                sveTuristickeAtrakcije.add(atrakcijaZaIzmjenu, muzej);
                glavnaForma.setSveTuristickeAtrakcije(sveTuristickeAtrakcije);
                PomocnaKlasa.serijalizacija(sveTuristickeAtrakcije);
                glavnaForma.popuniTabeluAtrakcija(glavnaForma.getSveTuristickeAtrakcije(), tabelaAtrakcija);
                this.dispose();
            }

        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof Crkva) {

            String naziv = tekstFieldNaziv.getText();
            String lokacija = tekstFieldLokacija.getText();
            if (naziv.equals("") || lokacija.equals("")) {
                JOptionPane.showMessageDialog(null, " Polja naziv i lokacija moraju biti popunjena!");
            } else {
                Crkva crkva = new Crkva(naziv, lokacija);
                sveTuristickeAtrakcije.remove(atrakcijaZaIzmjenu);
                sveTuristickeAtrakcije.add(atrakcijaZaIzmjenu, crkva);
                glavnaForma.setSveTuristickeAtrakcije(sveTuristickeAtrakcije);
                PomocnaKlasa.serijalizacija(sveTuristickeAtrakcije);
                glavnaForma.popuniTabeluAtrakcija(glavnaForma.getSveTuristickeAtrakcije(), tabelaAtrakcija);
                this.dispose();
            }
        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof IstorijskiSpomenik) {

            String naziv = tekstFieldNaziv.getText();
            String lokacija = tekstFieldLokacija.getText();
            String opis = tekstAreaOpis.getText();
            String fotografija = odabraniDokument;
            if (naziv.equals("") || lokacija.equals("")) {
                JOptionPane.showMessageDialog(null, " Polja naziv i lokacija moraju biti popunjena!");
            } else {
                IstorijskiSpomenik is = new IstorijskiSpomenik(opis, fotografija, naziv, lokacija);
                sveTuristickeAtrakcije.remove(atrakcijaZaIzmjenu);
                sveTuristickeAtrakcije.add(atrakcijaZaIzmjenu, is);
                glavnaForma.setSveTuristickeAtrakcije(sveTuristickeAtrakcije);
                PomocnaKlasa.serijalizacija(sveTuristickeAtrakcije);
                glavnaForma.popuniTabeluAtrakcija(glavnaForma.getSveTuristickeAtrakcije(), tabelaAtrakcija);
                this.dispose();
            }
        } else if (sveTuristickeAtrakcije.get(atrakcijaZaIzmjenu) instanceof ZabavniPark) {

            String naziv = tekstFieldNaziv.getText();
            String lokacija = tekstFieldLokacija.getText();
            double cijenaUlaznice;
            if (naziv.equals("") || lokacija.equals("")) {
                JOptionPane.showMessageDialog(null, " Polja naziv i lokacija moraju biti popunjena!");
            } else {
                cijenaUlaznice = (Integer) spinerCijena.getValue();
                ZabavniPark zp = new ZabavniPark(naziv, lokacija, cijenaUlaznice);
                sveTuristickeAtrakcije.remove(atrakcijaZaIzmjenu);
                sveTuristickeAtrakcije.add(atrakcijaZaIzmjenu, zp);
                glavnaForma.setSveTuristickeAtrakcije(sveTuristickeAtrakcije);
                PomocnaKlasa.serijalizacija(sveTuristickeAtrakcije);
                glavnaForma.popuniTabeluAtrakcija(glavnaForma.getSveTuristickeAtrakcije(), tabelaAtrakcija);
                this.dispose();
            }
        }

        /* else if (vrstaTuristickeAtrakcije.equals("Crkva")) {
            labelaNaslov1.setText("NOVA");
            labelaNaslov2.setText("CRKVA");
            labelaOpis.setVisible(false);
            //  tekstAreaOpis.setVisible(false);
            panelOpis.setVisible(false);
            labelaZaSliku.setVisible(false);
            labelaIzabranaSFotografija.setVisible(false);
            labelaCIjenaUlaznice.setVisible(false);
            spinerCijena.setVisible(false);
            labelaDokument.setVisible(false);
            dugmeIzaberiDokument.setVisible(false);
        } else if (vrstaTuristickeAtrakcije.equals("Istorijski spomenik")) {
            labelaNaslov1.setText("NOVI");
            labelaNaslov2.setText("ISTORIJSKI SPOEMNIK");
            labelaCIjenaUlaznice.setVisible(false);
            spinerCijena.setVisible(false);
        } else if (vrstaTuristickeAtrakcije.equals("Zabavni park")) {
            labelaNaslov1.setText("NOVI");
            labelaNaslov2.setText("ZABAVNI PARK");
            labelaOpis.setVisible(false);
            //  tekstAreaOpis.setVisible(false);
            panelOpis.setVisible(false);
            labelaZaSliku.setVisible(false);
            labelaIzabranaSFotografija.setVisible(false);
            labelaDokument.setVisible(false);
            dugmeIzaberiDokument.setVisible(false);
        }*/

    }//GEN-LAST:event_dugmeIzmjeniActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dugmeIzaberiDokument;
    private javax.swing.JButton dugmeIzmjeni;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelaCIjenaUlaznice;
    private javax.swing.JLabel labelaDokument;
    private javax.swing.JLabel labelaIzabranaSFotografija;
    private javax.swing.JLabel labelaNaslov1;
    private javax.swing.JLabel labelaNaslov2;
    private javax.swing.JLabel labelaNazivAtrakcije;
    private javax.swing.JLabel labelaNazivLokacija;
    private javax.swing.JLabel labelaNazivOdabranogDokumenta;
    private javax.swing.JLabel labelaNePlacaSe;
    private javax.swing.JLabel labelaOpis;
    private javax.swing.JLabel labelaZaSliku;
    private javax.swing.JPanel panelOpis;
    private javax.swing.JSpinner spinerCijena;
    private javax.swing.JTextArea tekstAreaOpis;
    private javax.swing.JTextField tekstFieldLokacija;
    private javax.swing.JTextField tekstFieldNaziv;
    // End of variables declaration//GEN-END:variables
}
