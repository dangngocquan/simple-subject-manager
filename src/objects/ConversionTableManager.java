package src.objects;

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

    // Setter
    public void setConversionTables(List<ConversionTable> lst) {
        this.conversionTables = lst;
    }

    public void addConversionTable(ConversionTable conversionTable) {
        this.conversionTables.add(conversionTable);
    }

}
