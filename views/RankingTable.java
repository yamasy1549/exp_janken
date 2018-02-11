package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import static original.Constants.*;

public class RankingTable extends JTable {

    RankingTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        for(String columnName: (String[])columnNames) {
            getColumn(columnName).setCellRenderer(dtcr);
        }
        setBackground(BLACK);
        setForeground(WHITE);
        setGridColor(GRAY);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        setRowHeight(40);
        setOpaque(true);

        JTableHeader header = getTableHeader();
        header.setBackground(BLACK);
        header.setForeground(WHITE);
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    }
}
