package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import static original.Constants.*;

public class RankingTable extends JTable {

    RankingTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);

        // DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        // dtcr.setHorizontalAlignment(SwingConstants.CENTER);

        // for(String columnName: columnNames) {
            // getColumn(columnName).setCellRenderer(dtcr);
        // }
        setBackground(BLACK);
        setForeground(WHITE);
        setGridColor(MINT);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        setRowHeight(30);
        setOpaque(true);

        JTableHeader header = getTableHeader();
        header.setBackground(BLACK);
        header.setForeground(MINT);
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
    }
}
