package vista;

import controlador.AlumnoDAO;
import modelo.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class ventanaInicio extends JFrame{
    RasLayout ras;
    ArrayList<Wrap> salida;
    int celw = 20;
    int celh = 20;
    int w = 700;
    int h = 700;
    String title;

    VentanaAltas altas;
    JButton btnAltas;
    VentanaBajas bajas;
    JButton btnBajas;

    VentanaCambios cambios;
    JButton btnCambios;

    VentanaConsultas consultas;
    JButton btnConsultas;

    AlumnoDAO alo = new AlumnoDAO();

    public ventanaInicio() {
        title = "Opciones de base de datos";
        ras = new RasLayout(this, title, w, h);
        salida = new ArrayList<Wrap>();
        ras.cw = celw;
        ras.ch = celh;
        btnAltas = new JButton("Agregar alumno");
        btnBajas = new JButton("Eliminar alumno");
        btnCambios = new JButton("Modificar alumno");
        btnConsultas = new JButton("Buscar alumnos");
        Wrap wAltas = ras.encuadrarRelativo(btnAltas, (int)(w/celw*0.25), (int)(h/celh*0.25), 10, 3);
        wAltas.centerOffset(1,1);
        salida.add(wAltas);
        btnAltas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(altas != null) altas.dispose();
                altas = new VentanaAltas(alo);
                ArrayList<Alumno> ts = alo.mostrarAlumnos("");
                if(ts == null) return;
                altas.setearTabla(ts);
            }
        });
        Wrap wBajas = ras.encuadrarRelativo(btnBajas, (int)(w/celw*0.75), (int)(h/celh*0.25), 10, 3);
        wBajas.centerOffset(1,1);
        salida.add(wBajas);

        btnBajas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bajas != null) bajas.dispose();
                bajas = new VentanaBajas(alo);
                ArrayList<Alumno> ts = alo.mostrarAlumnos("");
                if(ts == null) return;
                bajas.setearTabla(ts);
            }
        });
        Wrap wCambios = ras.encuadrarRelativo(btnCambios, (int)(w/celw*0.25), (int)(h/celh*0.75), 10, 3);
        wCambios.centerOffset(1,1);
        salida.add(wCambios);

        btnCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cambios != null) cambios.dispose();
                cambios = new VentanaCambios(alo);
                ArrayList<Alumno> ts = alo.mostrarAlumnos("");
                if(ts == null) return;
                cambios.setearTabla(ts);
            }
        });

        Wrap wConsultas = ras.encuadrarRelativo(btnConsultas, (int)(w/celw*0.75), (int)(h/celh*0.75), 10, 3);
        wConsultas.centerOffset(1,1);
        salida.add(wConsultas);

        btnConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(consultas != null) consultas.dispose();
                consultas = new VentanaConsultas(alo);
                ArrayList<Alumno> ts = alo.mostrarAlumnos("");
                if(ts == null) return;
                consultas.setearTabla(ts);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RasLayout.refrescar(salida, ras);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ventanaInicio();
            }
        });
    }
    void prueba() {
        //suponiendo que la GUI de altas existe

        //String nc = cajaNumControl.getText();

        Alumno a = new Alumno("443", "Pedro", "A", "B", (byte)55, (byte)2, "ISC");

        AlumnoDAO alumnoDAO = new AlumnoDAO();
        if(alumnoDAO.agregarAlumno(a)) JOptionPane.showMessageDialog(null, "Registro Agregado");
        else JOptionPane.showMessageDialog(null, "ERROR en la instruccion");

        ArrayList<Alumno> lista = alumnoDAO.mostrarAlumnos("");

        for (Alumno mos : lista){
            System.out.println(mos);
        }
        //if(alumnoDAO.eliminarAlumno("443")) JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        //else JOptionPane.showMessageDialog(null, "Error en la eliminacion");
    }
}
