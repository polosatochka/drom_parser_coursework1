package parser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.util.ArrayList;

public class Writer {

    //Метод заполнения шапки Листа 1
    public static void tableHead(Sheet sheetOne) {
        Row row0 = sheetOne.createRow(0);

        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("Время подачи");
        sheetOne.autoSizeColumn(0);
        Cell cell2 = row0.createCell(1);
        cell2.setCellValue("Модель");
        sheetOne.autoSizeColumn(1);
        Cell cell3 = row0.createCell(2);
        cell3.setCellValue("Город");
        sheetOne.autoSizeColumn(2);
        Cell cell4 = row0.createCell(3);
        cell4.setCellValue("Цена");
        sheetOne.autoSizeColumn(3);
        Cell cell5 = row0.createCell(4);
        cell5.setCellValue("Ссылка");
        sheetOne.autoSizeColumn(4);
        Cell cell6 = row0.createCell(5);
        cell6.setCellValue("Описание");
        sheetOne.autoSizeColumn(5);
        Cell cell7 = row0.createCell(6);
        cell7.setCellValue("Просмотры");
        sheetOne.autoSizeColumn(6);
    }

    //заполнение Листа 1 информацией
    public static void writeData(Workbook wb, ArrayList<String> dates, ArrayList<String> titles,
                                 ArrayList<String> cities, ArrayList<String> prices, ArrayList<String> innerLinks,
                                 ArrayList<String> descriptions, ArrayList<String> viewers, int rowCounter) {


        Sheet sheetNext = wb.createSheet("Результаты парсинга");

        //заполнениe шапки Листа 1
//        public static void tableHead(sheetOne) {
            Row row0 = sheetNext.createRow(0);

            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("Время подачи");
            sheetNext.autoSizeColumn(0);
            Cell cell2 = row0.createCell(1);
            cell2.setCellValue("Модель");
        sheetNext.autoSizeColumn(1);
            Cell cell3 = row0.createCell(2);
            cell3.setCellValue("Город");
        sheetNext.autoSizeColumn(2);
            Cell cell4 = row0.createCell(3);
            cell4.setCellValue("Цена");
        sheetNext.autoSizeColumn(3);
            Cell cell5 = row0.createCell(4);
            cell5.setCellValue("Ссылка");
        sheetNext.autoSizeColumn(4);
            Cell cell6 = row0.createCell(5);
            cell6.setCellValue("Описание");
        sheetNext.autoSizeColumn(5);
            Cell cell7 = row0.createCell(6);
            cell7.setCellValue("Просмотры");
        sheetNext.autoSizeColumn(6);

            for (int i = 0; i < dates.size(); i++) {
                Row row = sheetNext.createRow(rowCounter);

                Cell cell00 = row.createCell(0);
                cell00.setCellValue(dates.get(i));
                sheetNext.autoSizeColumn(0);

                Cell cell11 = row.createCell(1);
                cell11.setCellValue(titles.get(i));
                sheetNext.autoSizeColumn(1);

                Cell cell22 = row.createCell(2);
                cell22.setCellValue(cities.get(i));
                sheetNext.autoSizeColumn(2);

                Cell cell33 = row.createCell(3);
                cell33.setCellValue(prices.get(i));
                sheetNext.autoSizeColumn(3);

                Cell cell44 = row.createCell(4);
                cell44.setCellValue(innerLinks.get(i));
                sheetNext.autoSizeColumn(4);

                Cell cell55 = row.createCell(5);
                cell55.setCellValue(descriptions.get(i));
                sheetNext.autoSizeColumn(5);

                Cell cell66 = row.createCell(6);
                cell66.setCellValue(viewers.get(i));
                sheetNext.autoSizeColumn(6);

                rowCounter++;}
    }
}
