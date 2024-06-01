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

public class VentanaAltas extends JFrame {
    RasLayout ras;
    AlumnoDAO alo;// = new AlumnoDAO();
    JPanel panelAltas;
    JLabel lblAltas;
    JTextField txtNumControl = new JTextField(10),
            txtNombre = new JTextField(10),
            txtPrimerAp = new JTextField(10),
            txtSegundoAp = new JTextField(10),
            txtEdad = new JTextField(10);

    JButton btnAgregar, btnBorrar, btnCancelar;

    JComboBox<String> comboCarrera;
    JComboBox<Byte> comboSemestre;

    Wrap wPanelAltas, wLblPanelALtas;
    ArrayList<Wrap> partes;

    JTable tablaConsultas;
    DefaultTableModel model;
    JScrollPane scroller;
    int cw = 20, ch = 20;

    public VentanaAltas(AlumnoDAO d){
        alo = d;
        ras = new RasLayout(this, "Altas", 840, 680);
        ras.ch = ch;
        ras.cw = cw;
        partes = new ArrayList<Wrap>();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tablaConsultas = new JTable();

        scroller = new JScrollPane(tablaConsultas);

        panelAltas = new JPanel();
        panelAltas.setBackground(new Color(0,255,0));
        wPanelAltas = new Wrap(panelAltas);

        lblAltas = new lblFont("ALTAS ALUMNOS", " ", Font.ITALIC+Font.BOLD, 20, 255, 255, 255);
        btnAgregar = new JButton("AGREGAR");
        btnBorrar = new JButton("BORRAR");
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

        wLblPanelALtas = new Wrap(lblAltas);
        ras.offset(wLblPanelALtas, 0, 50);

        //======AGUAS===============
        //que raslayout pueda trabajar con las dimensiones de ventana
        //y moverlos a wraps

        //es posible iterar en partes para agregar todos los datos en automatico
        partes.add(ras.encuadrarRelativo(new JLabel("NUMERO DE CONTROL"), 2, 8, 8, 1));
        partes.add(ras.encuadrarRelativo(txtNumControl, 2+8+1, 8, 8, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("NOMBRES"), 2, 10, 6, 1));
        partes.add(ras.encuadrarRelativo(txtNombre, 2+6+1, 10, 10, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("APELLIDO PATERNO"), 2, 12, 8, 1));
        partes.add(ras.encuadrarRelativo(txtPrimerAp, 2+8+1, 12, 8, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("APELLIDO MATERNO"), 2, 14, 8, 1));
        partes.add(ras.encuadrarRelativo(txtSegundoAp, 2+8+1, 14, 8, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("EDAD"), 2, 16, 8, 1));
        partes.add(ras.encuadrarRelativo(txtEdad, 2+8+1, 16, 8, 1));

        partes.add(ras.encuadrarRelativo(new JLabel("SEMESTRE"), 2, 18, 5, 1));
        partes.add(ras.encuadrarRelativo(comboSemestre, 2+8+1, 18, 8, 1));
        partes.add(ras.encuadrarRelativo(new JLabel("CARRERA"), 2, 19, 5, 1));
        partes.add(ras.encuadrarRelativo(comboCarrera, 2+8+1, 19, 8, 1));

        partes.add(ras.encuadrarRelativo(btnAgregar, 2+8+1+ 8 + 2, 9, 7, 1));
        partes.add(ras.encuadrarRelativo(btnBorrar, 2+8+1+ 8 + 2, 11, 7, 1));
        partes.add(ras.encuadrarRelativo(btnCancelar, 2+8+1+ 8 + 2, 13, 7, 1));

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

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtNumControl.setText("");
                txtNombre.setText("");
                txtPrimerAp.setText("");
                txtSegundoAp.setText("");
                txtEdad.setText("");
                comboCarrera.setSelectedIndex(0);
                comboSemestre.setSelectedIndex(0);

            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
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
                    nc = txtNumControl.getText();
                    nom = txtNombre.getText();
                    pa = txtPrimerAp.getText();
                    sa = txtSegundoAp.getText();
                    car = comboCarrera.getSelectedItem().toString();

                    ed = Byte.parseByte(txtEdad.getText());
                    sem = (byte)(comboSemestre.getSelectedIndex()+1);

                    if(ed < 0) throw new NumberFormatException();
                }catch (NumberFormatException n){
                    JOptionPane.showMessageDialog(null, "la edad debe ser un entero positivo");
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
                if(alo.agregarAlumno(a)){
                    JOptionPane.showMessageDialog(null, "registro exitoso");
                    model.addRow(datos);
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

        partes.add(wPanelAltas);
        partes.add(wLblPanelALtas);

        ras.agregarRelativo(wLblPanelALtas, wPanelAltas.xFrom+50, wPanelAltas.yFrom, 200, 20);
        ras.agregarRelativo(wPanelAltas, 0, 0, getWidth(), getHeight()/6);

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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaAltas(new AlumnoDAO());
            }
        });
    }
}
