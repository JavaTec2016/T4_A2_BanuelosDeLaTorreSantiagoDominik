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

public class VentanaConsultas extends JFrame implements ActionListener {

    RasLayout ras;
    AlumnoDAO alo;// = new AlumnoDAO();
    JPanel panelConsultas;
    JLabel lblConsultas;
    JTextField txtNombre = new JTextField(10),
            txtPrimerAp = new JTextField(10),
            txtSegundoAp = new JTextField(10),
            txtEdad = new JTextField(10);
    JRadioButton radioNombre = new JRadioButton("Nombre ".toUpperCase()),
            radioPrimerAp = new JRadioButton("Apellido Paterno ".toUpperCase()),
            radioSegundoAp = new JRadioButton("APELLIDO MATERNO"),
            radioEdad = new JRadioButton("EDAD"),
            radioSemestre = new JRadioButton("SEMESTRE"),
            radioCarrara = new JRadioButton("CARRERA"),
            radioTodos = new JRadioButton("TODOS");

    JButton btnBuscar, btnBorrar, btnCancelar;

    JComboBox<String> comboCarrera;
    JComboBox<Byte> comboSemestre;

    Wrap wPanelConsultas, wLblPanelALtas;
    ArrayList<Wrap> partes;

    JTable tablaConsultas;
    DefaultTableModel model;
    JScrollPane scroller;
    int cw = 20, ch = 20;
    JComponent res;
    String filtro = "";
    public VentanaConsultas(AlumnoDAO d) {
        alo = d;
        ras = new RasLayout(this, "Buscar Alumno", 840, 700);
        ras.ch = ch;
        ras.cw = cw;
        partes = new ArrayList<Wrap>();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tablaConsultas = new JTable();

        btnBorrar = new JButton("Borrar");
        btnCancelar = new JButton("Cancelar");
        btnBuscar = new JButton("Buscar");
        scroller = new JScrollPane(tablaConsultas);
        comboCarrera = new JComboBox<String>();
        comboSemestre = new JComboBox<Byte>();
        comboCarrera.addItem("Ingeniería en Sistemas Computacionales");
        comboCarrera.addItem("Ingeniería Mecatrónica");
        comboCarrera.addItem("Administración de Empresas");
        comboCarrera.addItem("Industrias Alimentarias");
        for(byte i = 1; i < 10; i++){
            comboSemestre.addItem(i);
        }

        panelConsultas = new JPanel();
        panelConsultas.setBackground(new Color(0,0,255));
        wPanelConsultas = new Wrap(panelConsultas);

        lblConsultas = new lblFont("CONSULTAS ALUMNOS", " ", Font.ITALIC+Font.BOLD, 20, 255, 255, 255);

        Wrap    wLblConsultas = new Wrap(lblConsultas);


        partes.add(wPanelConsultas);
        partes.add(wLblConsultas);
        ras.prepararRelativo(wPanelConsultas, 0, 0, getWidth(), getHeight()/6);
        //ras.prepararRelativo(wPanelCambios, wPanelCambios.xFrom, wPanelCambios.yFrom+getHeight()/6, getWidth(), getHeight()/6);

        lblConsultas.setVerticalAlignment(SwingConstants.CENTER);
        lblConsultas.setHorizontalAlignment(SwingConstants.CENTER);
        ras.agregarRelativo(wLblConsultas, 0, 0, 2* panelConsultas.getWidth()/3, panelConsultas.getHeight());



        String[] headers = Alumno.getAtributos();
        model = new DefaultTableModel(null, headers);
        tablaConsultas = new JTable(model);

        Wrap wScroller = new Wrap(scroller);
        ras.agregarRelativo(wScroller, 0, 0, 700, 150);
        wScroller.resize = false;
        wScroller.xRelative = 0.5;
        wScroller.yRelative = 0.7;
        wScroller.centerOffset((byte)1, (byte)0);

        ButtonGroup gp = new ButtonGroup();
        int xi = 6;
        //partes.add(ras.encuadrarRelativo(new JLabel("NUMERO DE CONTROL"), 2, 8, 8, 1));
        //partes.add(ras.encuadrarRelativo(txtNumControl, 2+8+1, 8, 8, 1));
        partes.add(ras.encuadrarRelativo(radioTodos, 2, 9, 6, 1));
        partes.add(ras.encuadrarRelativo(radioNombre, xi, 10, 6, 1));
        partes.add(ras.encuadrarRelativo(txtNombre, xi+6+1, 10, 10, 1));
        partes.add(ras.encuadrarRelativo(radioPrimerAp, xi, 12, 8, 1));
        partes.add(ras.encuadrarRelativo(txtPrimerAp, xi+8+1, 12, 8, 1));
        partes.add(ras.encuadrarRelativo(radioSegundoAp, xi, 14, 8, 1));
        partes.add(ras.encuadrarRelativo(txtSegundoAp, xi+8+1, 14, 8, 1));
        partes.add(ras.encuadrarRelativo(radioEdad, xi, 16, 8, 1));
        partes.add(ras.encuadrarRelativo(txtEdad, xi+8+1, 16, 8, 1));

        partes.add(ras.encuadrarRelativo(radioSemestre, xi, 18, 5, 1));
        partes.add(ras.encuadrarRelativo(comboSemestre, xi+8+1, 18, 8, 1));
        partes.add(ras.encuadrarRelativo(radioCarrara, xi, 19, 5, 1));
        partes.add(ras.encuadrarRelativo(comboCarrera, xi+8+1, 19, 8, 1));

        partes.add(ras.encuadrarRelativo(btnBuscar, xi+8+1+ 8 + 2, 11, 7, 1));
        partes.add(ras.encuadrarRelativo(btnBorrar, xi+8+1+ 8 + 2, 13, 7, 1));
        partes.add(ras.encuadrarRelativo(btnCancelar, xi+8+1+ 8 + 2, 15, 7, 1));

        gp.add(radioTodos);
        gp.add(radioNombre);
        gp.add(radioPrimerAp);
        gp.add(radioSegundoAp);
        gp.add(radioEdad);
        gp.add(radioSemestre);
        gp.add(radioCarrara);

        radioTodos.addActionListener(this);
        radioNombre.addActionListener(this);
        radioPrimerAp.addActionListener(this);
        radioSegundoAp.addActionListener(this);
        radioEdad.addActionListener(this);
        radioSemestre.addActionListener(this);
        radioCarrara.addActionListener(this);

        partes.add(wScroller);


        scroller.setViewportView(tablaConsultas);
        add(scroller);
        add(panelConsultas);

        JPanel p = new JPanel();
        p.setBackground(getBackground());
        Wrap wP = new Wrap(p);
        ras.agregarRelativo(wP, 10, panelConsultas.getHeight()+20, getWidth()-25, getHeight()- panelConsultas.getHeight()-40);
        p.setBorder(BorderFactory.createTitledBorder("Selecciona criterio de busqueda"));
        partes.add(wP);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(partes, ras);
            }
        });
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while(model.getRowCount() > 0) model.removeRow(0);
                if(res == null){
                    setearTabla(alo.mostrarAlumnos(""));
                    return;
                }else{
                    obtenerDatos();
                    setearTabla(alo.mostrarAlumnos(filtro));
                }
            }
        });
        habilitar(null);
    }
    public void limpiar(){
        txtNombre.setText("");
        txtPrimerAp.setText("");
        txtSegundoAp.setText("");
        txtEdad.setText("");
        comboCarrera.setSelectedIndex(0);
        comboSemestre.setSelectedIndex(0);

        radioTodos.setSelected(false);
        radioNombre.setSelected(false);
        radioPrimerAp.setSelected(false);
        radioSegundoAp.setSelected(false);
        radioCarrara.setSelected(false);
        radioSemestre.setSelected(false);
    }
    public void obtenerDatos(){
        if(res == txtEdad) {
            filtro += txtEdad.getText(); return;
        }
        if(res == comboCarrera) {
            filtro += "'"+comboCarrera.getSelectedItem().toString()+"'"; return;
        }
        if(res == comboSemestre) {
            filtro += comboSemestre.getSelectedItem().toString(); return;
        }
        filtro += "'" + ((JTextField)(res)).getText()+"'";
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(radioTodos.isSelected()) {
            habilitar(null);
            res = null;
            filtro = null;
        }
        if(radioNombre.isSelected()) {
            habilitar(txtNombre);
            res = txtNombre;
            filtro = "Nombre=";
            //setearTabla(alo.mostrarAlumnos("Nombre='" + txtNombre + "'"));
        }
        if(radioPrimerAp.isSelected()) {
            res = txtPrimerAp;
            filtro = "Primer_Ap=";
            //setearTabla(alo.mostrarAlumnos("Primer_Ap='" + txtPrimerAp + "'"));
        }
        if(radioSegundoAp.isSelected()) {
            res = txtSegundoAp;
            filtro = "Segundo_Ap=";
        }
        if(radioEdad.isSelected()) {
            res = txtEdad;
            filtro = "Edad=";
        }
        if(radioSemestre.isSelected()){
            res = comboSemestre;
            filtro = "SemestreActual=";
        }
        if(radioCarrara.isSelected()){
            res = comboCarrera;
            filtro = "Carrera=";
        }
        habilitar(res);
    }
    void habilitar(JComponent c){
        txtNombre.setEnabled(false);
        txtPrimerAp.setEnabled(false);
        txtSegundoAp.setEnabled(false);
        txtEdad.setEnabled(false);
        comboSemestre.setEnabled(false);

        if(c == null) return;
        c.setEnabled(true);
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
