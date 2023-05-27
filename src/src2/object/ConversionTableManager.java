package src2.object;

import java.util.LinkedList;
import java.util.List;

public class ConversionTableManager {
    private List<ConversionTable> conversionTables;

    public ConversionTableManager() {
        conversionTables = new LinkedList<ConversionTable>();
    }

    // Getter
    public List<ConversionTable> getConversionTables() {
        return this.conversionTables;
    }

    public List<String> getConversionTableNames() {
        List<String> names = new LinkedList<String>();
        for (ConversionTable conversionTable : conversionTables) {
            names.add(conversionTable.getName());
        }
        return names;
    }

    public String[] getArrayConversionNames() {
        String[] names = new String[this.conversionTables.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = this.conversionTables.get(i).getName();
        }
        return names;
    }

    // Setter
    public void setConversionTables(List<ConversionTable> lst) {
        this.conversionTables = lst;
    }

    public void addConversionTable(ConversionTable conversionTable) {
        this.conversionTables.add(conversionTable);
    }

}
