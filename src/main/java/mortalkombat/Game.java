package mortalkombat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Game {
    public GameActions action = new GameActions();
    public Fight fight = new Fight();
    private final ArrayList<Result> results =  new ArrayList<>();
    private final Enemy enemies[] = new Enemy[5];

    public void setEnemies() {
        enemies[0] = new Enemy("Baraka", 1, 100, 12);
        enemies[1] = new Enemy("Sub Zero", 1, 60, 16);
        enemies[2] = new Enemy("Liu Kang", 1, 70, 20);
        enemies[3] = new Enemy("Sonya Blade", 1, 80, 16);
        enemies[4] = new Enemy("Boss", 3, 100, 30);
    }

    public Enemy[] getEnemies() {
        return this.enemies;
    }

    public Player newPlayer(Mediator mediator, Items[] items) {
        Player player = new Player(0, 80, 16);
        mediator.setHealthBar(player);
        mediator.setPlayerMaxHealthBar(player);
        player.setItems(items);
        return player;
    }

    public void endGameTop(Player player, JTextField text, JTable table) throws IOException {
        String finalName = text.getText().trim();
        if(finalName.isEmpty()) {
            finalName = "Неизвестный игрок";
        }
        results.add(new Result(finalName, player.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        writeToTable(table);
        writeToExcel();
    }

    public void writeToExcel() throws IOException {
        try (XSSFWorkbook book = new XSSFWorkbook()) {
            XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
            XSSFRow r = sheet.createRow(0);
            r.createCell(0).setCellValue("№");
            r.createCell(1).setCellValue("Имя");
            r.createCell(2).setCellValue("Количество баллов");
            for (int i = 0; i < results.size(); i++) {
                if (i < 10) {
                    XSSFRow r2 = sheet.createRow(i + 1);
                    r2.createCell(0).setCellValue(i + 1);
                    r2.createCell(1).setCellValue(results.get(i).getName());
                    r2.createCell(2).setCellValue(results.get(i).getPoints());
                }
            }
            File f = new File("src/main/files/results.xlsx");
            book.write(new FileOutputStream(f));
        }
    }

    public ArrayList<Result> getResults() {
        return this.results;
    }

    public void readFromExcel() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook("src/main/files/results.xlsx");
        XSSFSheet sh = book.getSheetAt(0);
        for (int i = 1; i <= sh.getLastRowNum(); i++) {
            results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(), (int) sh.getRow(i).getCell(2).getNumericCellValue()));
        }

    }

    public void writeToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
