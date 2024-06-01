package vista;

import controlador.AlumnoDAO;
import modelo.Alumno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class VentanaCambios extends JFrame {
    RasLayout ras;
    AlumnoDAO alo;// = new AlumnoDAO();
    JPanel panelCambios;
    JLabel lblCambios;
    JTextField txtNumControl = new JTextField(10),
            txtNombre = new JTextField(10),
            txtPrimerAp = new JTextField(10),
            txtSegundoAp = new JTextField(10),
            txtEdad = new JTextField(10),
            txtBuscarNum = new JTextField(10);

    JButton btnBusqueda, btnBorrar, btnModificar, btnCancelar;

    JComboBox<String> comboCarrera;
    JComboBox<Byte> comboSemestre;

    Wrap wPanelCambios, wLblCambios;
    ArrayList<Wrap> partes;

    JTable tablaConsultas;
    DefaultTableModel model;
    JScrollPane scroller;
    int cw = 20, ch = 20;

    public VentanaCambios(AlumnoDAO d) {
        alo = d;
        ras = new RasLayout(this, "Cambios", 840, 700);
        ras.ch = ch;
        ras.cw = cw;
        partes = new ArrayList<Wrap>();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tablaConsultas = new JTable();

        scroller = new JScrollPane(tablaConsultas);

        panelCambios = new JPanel();
        panelCambios.setBackground(new Color(255, 115,0));
        wPanelCambios = new Wrap(panelCambios);

        lblCambios = new lblFont("MODIFICACIONES ALUMNOS", " ", Font.ITALIC+Font.BOLD, 20, 255, 255, 255);
        btnBusqueda = new JButton("BUSCAR");
        btnBorrar = new JButton("BORRAR");
        btnModificar = new JButton("MODIFICAR");
        btnCancelar = new JButton("CANCELAR");

        comboCarrera = new JComboBox<String>();
        comboSemestre = new JComboBox<Byte>();
        comboCarrera.addItem("Ingeniería en Sistemas Computacionales");
        comboCarrera.addItem("Ingeniería Mecatrónica");
        comboCarrera.addItem("Administración de Empresas");
        comboCarrera.addItem("Industrias Alimentarias");
        for(byte i = 1; i < 10; i++){
            comboSemestre.addItem(i);
        }
        partes.add(wPanelCambios);
        wLblCambios = new Wrap(lblCambios);
        //ras.offset(wLblCambios, 0, 50);
        ras.prepararRelativo(wPanelCambios, 0, 0, getWidth(), getHeight()/6);
        //lblCambios.setHorizontalAlignment(SwingConstants.CENTER);
        lblCambios.setVerticalAlignment(SwingConstants.CENTER);
        ras.agregarRelativo(wLblCambios, 50, 0, panelCambios.getWidth(), panelCambios.getHeight());
        //System.out.println(wLblCambios.widthFrom);
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(new Color(250, 250, 250));
        lblFont lblNumControl = new lblFont("NUMERO DE CONTROL: ", "Calibri", Font.BOLD, 15, 0,0,0);

        Wrap wPanel = new Wrap(panelBusqueda),
                wLblNumControl = new Wrap(lblNumControl),
                wTxtBuscarNum = new Wrap(txtBuscarNum),
                wBtnBuscar = new Wrap(btnBusqueda),
                wBtnBorrar = new Wrap(btnBorrar);


        partes.add(wPanel);

        ras.prepararRelativo(wPanel, wPanelCambios.xFrom, wPanelCambios.yFrom+getHeight()/6, getWidth(), getHeight()/6);

        ras.agregarRelativo(wLblNumControl, wPanel.xFrom+80, wPanel.yFrom+50, 160, 15);
        wLblNumControl.centerOffset((byte) 0, (byte) 1);
        wLblNumControl.calcularCoordenadas(this);

        ras.agregarRelativo(wTxtBuscarNum, wLblNumControl.xFinal+10+lblNumControl.getWidth(), wPanel.yFrom+50, 120, 30);
        wTxtBuscarNum.centerOffset((byte) 0, (byte) 1);
        wTxtBuscarNum.calcularCoordenadas(this);
        //ras.offset(wTxtBuscarNum, wLblNumControl.xFrom+wLblNumControl.xLatchDiff+wLblNumControl.componente.getWidth()+10, 50);

        ras.agregarRelativo(wBtnBuscar, wTxtBuscarNum.xFinal+10+txtBuscarNum.getWidth(), wPanel.yFrom+50, 100, 35);
        wBtnBuscar.centerOffset((byte) 0, (byte) 1);
        wBtnBuscar.calcularCoordenadas(this);
        //ras.offset(wBtnBuscar, wTxtBuscarNum.xFrom+wTxtBuscarNum.xLatchDiff+wTxtBuscarNum.componente.getWidth()+50, 50);

        ras.agregarRelativo(wBtnBorrar, wBtnBuscar.xFinal+10+btnBusqueda.getWidth(), wPanel.yFrom+50, 100, 20);
        wBtnBorrar.centerOffset((byte) 0, (byte) 1);
        wBtnBorrar.calcularCoordenadas(this);
        //ras.offset(wBtnBorrar, wBtnBuscar.xFrom+wBtnBuscar.xLatchDiff+wBtnBuscar.componente.getWidth()+20, 50);

        partes.add(wPanel);
        partes.add(wLblNumControl);
        partes.add(wTxtBuscarNum);
        partes.add(wBtnBuscar);
        partes.add(wBtnBorrar);

        add(panelCambios);
        add(panelBusqueda);

        partes.add(ras.encuadrarRelativo(new JLabel("NOMBRES"), 5, 13, 6, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(txtNombre, 5+6+1, 13, 10, 1.5).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("APELLIDO PATERNO"), 5, 15, 8, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(txtPrimerAp, 5+8+1, 15, 8, 1.5).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("APELLIDO MATERNO"), 5, 17, 8, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(txtSegundoAp, 5+8+1, 17, 8, 1.5).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("EDAD"), 5, 19, 8, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(txtEdad, 5+8+1, 19, 8, 1.5).chainCenterOffset(0, 1));

        partes.add(ras.encuadrarRelativo(new JLabel("SEMESTRE"), 5, 21, 5, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(comboSemestre, 5+8+1, 21, 8, 1.5).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("CARRERA"), 5, 23, 5, 1).chainCenterOffset(0, 1));
        partes.add(ras.encuadrarRelativo(comboCarrera, 5+8+1, 23, 8, 1.5).chainCenterOffset(0, 1));


        partes.add(ras.encuadrarRelativo(btnModificar, 5+8+1+ 8 + 2, 16, 7, 1));
        partes.add(ras.encuadrarRelativo(btnCancelar, 5+8+1+ 8 + 2, 18, 7, 1));

        //setear la tabla
        String[] headers = Alumno.getAtributos();
        model = new DefaultTableModel(null, headers);
        tablaConsultas = new JTable(model);

        Wrap wScroller = new Wrap(scroller);
        ras.agregarRelativo(wScroller, 0, 0, 700, 150);
        wScroller.resize = false;
        wScroller.xRelative = 0.5;
        wScroller.yRelative = 0.7;
        wScroller.centerOffset((byte)1, (byte)0);
        partes.add(wScroller);

        scroller.setViewportView(tablaConsultas);
        add(scroller);


        btnBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(model.getRowCount() > 0) model.removeRow(0);
                String inp = txtBuscarNum.getText();
                if(!inp.isBlank()) inp = "Num_Control='"+inp+"'";
                ArrayList<Alumno> as = alo.mostrarAlumnos(inp);
                if(as == null) return;
                setearTabla(as);
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBuscarNum.setText("");
                txtNombre.setText("");
                txtPrimerAp.setText("");
                txtSegundoAp.setText("");
                txtEdad.setText("");
                comboCarrera.setSelectedIndex(0);
                comboSemestre.setSelectedIndex(0);
            }
        });


        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] datos = new Object[headers.length];
                String nc = "",
                        nom = "",
                        pa = "",
                        sa = "",
                        car = "";

                byte ed = -1,
                        sem = -1;
                try{
                    nc = txtBuscarNum.getText();
                    nom = txtNombre.getText();
                    pa = txtPrimerAp.getText();
                    sa = txtSegundoAp.getText();
                    car = comboCarrera.getSelectedItem().toString();

                    ed = Byte.parseByte(txtEdad.getText());
                    sem = (byte)(comboSemestre.getSelectedIndex()+1);

                    if(ed < 0) throw new NumberFormatException();
                }catch (NumberFormatException n){
                    JOptionPane.showMessageDialog(null, "la edad debe ser un entero positivo no mayor a 128");
                    return;
                }

                datos[0] = nc;
                datos[1] = nom;
                datos[2] = pa;
                datos[3] = sa;
                datos[4] = ed;
                datos[5] = sem;
                datos[6] = car;

                byte i = 0; boolean retornar = false;
                for(Object o : datos){
                    String d = o.toString();
                    if(d.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Campo: " + headers[i] + " vacio");
                        retornar = true;
                    }
                    i++;
                }
                if(retornar) return;

                Alumno a = new Alumno(nc, nom, pa, sa, ed, sem, car);
                if(alo.actualizarAlumno(a)){
                    JOptionPane.showMessageDialog(null, "registro exitoso");
                    while (model.getRowCount() > 0) model.removeRow(0);
                    setearTabla(d.mostrarAlumnos(""));
                }else{
                    JOptionPane.showMessageDialog(null, "Los datos son incorrectos");
                };

            }
        });

        //falta la jtabla
        //dimensionar(txtNumControl, 2+8+1, 8, 8, 1);
        //dimensionar(new JLabel("NOMBRES"), 2, 10, 6, 1);
        //dimensionar(txtNombre, 2+6+1, 10, 10, 1);
        //dimensionar(new JLabel("APELLIDO PATERNO"), 2, 12, 8, 1);
        //dimensionar(txtPrimerAp, 2+8+1, 12, 8, 1);
        //dimensionar(new JLabel("APELLIDO MATERNO"), 2, 14, 8, 1);
        //dimensionar(txtSegundoAp, 2+8+1, 14, 8, 1);

        partes.add(wPanelCambios);
        partes.add(wLblCambios);

        //parece que esto hace algo cuando resizeas la ventana
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(partes, ras);
            }
        });
    }

    public void setearTabla(ArrayList<Alumno> als){
        Object[][] tuplas = new Object[als.size()][7];
        int i = 0;
        for (Alumno al : als) {

            tuplas[i][0] = als.get(i).getNumControl();
            tuplas[i][1] = als.get(i).getNombre();
            tuplas[i][2] = als.get(i).getPrimerAp();
            tuplas[i][3] = als.get(i).getSegundoAp();
            tuplas[i][4] = als.get(i).getEdad();
            tuplas[i][5] = als.get(i).getSemestre();
            tuplas[i][6] = als.get(i).getCarrera();
            model.addRow(tuplas[i]);
            i++;
        }
    }
}
