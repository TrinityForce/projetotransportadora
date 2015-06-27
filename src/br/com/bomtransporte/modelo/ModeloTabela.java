package br.com.bomtransporte.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author JhonattanSouza_
 */
public class ModeloTabela extends AbstractTableModel{
    private ArrayList linhas = null;
    private String[] colunas = null; 
    
    /**
     *
     * @param lin
     * @param col
     */
    public ModeloTabela(ArrayList lin, String[] col) {
        this.linhas = lin;
        this.colunas= col;
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] linha = (Object[])linhas.get(rowIndex);
        return linha[columnIndex];
    }
    
    @Override
    public String getColumnName(int numCol) {  
        return colunas[numCol];
    }
}
