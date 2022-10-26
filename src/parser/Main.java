package parser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.util.*;
import static parser.Parser.*;
import static parser.Writer.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> innerLinks = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> viewers = new ArrayList<>();


        String choice;
        boolean exit = true;
        Scanner in = new Scanner(System.in);
        System.out.println("Привет, для начала работы введи p");
        choice = in.nextLine();

        while (exit) {
            switch (choice) {
                case "p":
                    System.out.println("Введи название сайта в таком формате: https://auto.drom.ru/марка/модель/.");
                    System.out.println("Например, https://auto.drom.ru/audi/a5/ ");
                    String urlInput = in.nextLine();
                    //подключение к сайту
                    Document document = Jsoup.connect(urlInput).get();
                    int adsTotal = totalAddsAmount(document);
                    int allPages = totalPagesAmount(adsTotal);
                    System.out.println("Общее количество объявлений: " + adsTotal);
                    System.out.println("На сайте около " + allPages + " страниц.");
                    System.out.println("Сколько страниц ты хочешь проанализировать?");
                    int page = in.nextInt();
                    System.out.println("Получаем данные от " + page + " страниц...");
                    //получение данных
                    for (int j = 1; j < page+1; j++) {
                        String urlNext = urlInput + "page" + j + "/";
                        Document documentNext = Jsoup.connect(urlNext).get();
                        getDates(documentNext, dates);
                        getTitles(documentNext, titles);
                        getCities(documentNext, cities);
                        getPrice(documentNext, prices);
                        getDataFromPage(documentNext, innerLinks, descriptions, viewers);
                    }
                    System.out.println("Работа парсера завершена");
                    System.out.println("Записать данные в файл? (yes/no)");
                    choice = "q";
                case "q":
                    String answer = in.nextLine();
                    if (answer.equals("yes")) {
                        choice = "s";
                    } else if (answer.equals("no")) {
                        choice = "n";
                    }
                    break;
                case "s":
                    System.out.println("Записываем данные в файл...");

                    //запись данных
                    Workbook workbook = new HSSFWorkbook(); //Создание excel доумента
                    int rowsCount = 1;
                    writeData(workbook, dates, titles, cities, prices, innerLinks, descriptions, viewers, rowsCount);

                    System.out.println("Введите имя файла: ");
                    String fileName = in.nextLine();

                    FileOutputStream fos = new FileOutputStream("C:/Users/Анна/Downloads/Reports/"+fileName+".xls");
                    workbook.write(fos);
                    fos.close();
                    System.out.println("Данные записаны в файл");
                    System.out.println("Ты хочешь перезапустить парсер? (yes/no)");
                    String answerTwo = in.nextLine();
                    if (answerTwo.equals("yes")) {
                        choice = "p";
                    } else if (answerTwo.equals("no")) {
                        System.out.println("Для выхода введи e");
                        String e = in.nextLine();
                        choice = "e";
                    }
                    break;
                case "n":
                    System.out.println("Ты хочешь перезапустить парсер? (yes/no)");
                    String answerThree = in.nextLine();
                    if (answerThree.equals("yes")) {
                        choice = "p";
                    } else if (answerThree.equals("no")) {
                        System.out.println("Для выхода введи e");
                        String e = in.nextLine();
                        choice = "e";
                    }
                    break;
                case "e":
                    System.out.println("Выходим из программы...");
                    System.out.println("До свидания!");
                    exit = false;
                    break;
            }
        }
        in.close();
    }
}





