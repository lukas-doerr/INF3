/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commanddp.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author le
 */
public class EditorModel extends AbstractTableModel
{
  private ArrayList<ArrayList<String>> daten;  
  private ArrayList<String> adressEintraegeDaten;
  private ArrayList<String> adressEintraegeNamen;
  
  public EditorModel()
  {
    adressEintraegeDaten = new ArrayList<>();
    adressEintraegeNamen = new ArrayList<>();
    daten = new ArrayList<>();
    adressEintraegeNamen.add("Name");
    adressEintraegeDaten.add("Lehner");
    adressEintraegeNamen.add("Telefon");
    adressEintraegeDaten.add("122345");
    daten.add(adressEintraegeDaten);
  }

  @Override
  public int getRowCount()
  {
    return daten.size();
  }

  @Override
  public int getColumnCount()
  {
    return adressEintraegeDaten.size();
  }

  @Override
  public Object getValueAt(int row, int col)
  {
    return daten.get(row).get(col);
  }
  @Override
  public void setValueAt(Object value, int row, int col)
  {
    daten.get(row).set(col, (String)value);
  }
  
  @Override
  public boolean isCellEditable(int row, int col)
  {
    return true;
  }
  
  @Override
  public String getColumnName(int col)
  {
    return adressEintraegeNamen.get(col);
  }
  
  public ArrayList<String> getRowData(int row)
  {
    return daten.get(row);
  }
  
  public void insertRowData(int row, ArrayList<String> rowData)
  {
    daten.add(row, rowData);
    this.fireTableDataChanged();
  }
  
  public void eintragHinzufuegen()
  {
    adressEintraegeDaten = new ArrayList<>();
    adressEintraegeNamen.forEach(s -> adressEintraegeDaten.add(s));
    daten.add(adressEintraegeDaten);
    this.fireTableDataChanged();
  }
  
  public void eintragLoeschen(int row)
  {
    daten.remove(row);
    this.fireTableDataChanged();
  }
  
  public void spalteHinzufuegen(String name)
  {
    adressEintraegeNamen.add(name);
  }
  
  public void datenSpeichern(File datei) throws FileNotFoundException, IOException
  {
    FileOutputStream fos = new FileOutputStream(datei);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(daten);
    oos.writeObject(adressEintraegeNamen);
    oos.flush();
    oos.close();
  }
  
  public void datenLesen(File datei) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    FileInputStream fis = new FileInputStream(datei);
    BufferedInputStream bis = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(bis);
    daten = (ArrayList<ArrayList<String>>)ois.readObject();
    adressEintraegeNamen = (ArrayList<String>)ois.readObject();
    adressEintraegeDaten = daten.get(daten.size()-1);
    ois.close();
    this.fireTableDataChanged();
  }

    public void datenLesen(JFileChooser fc)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
}