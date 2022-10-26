package parser;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    //Метод получения общего количества объявлений
    public static int totalAddsAmount(Document document) {
      //  Elements inputBoxes = document.getElementsByTag("input");
        Elements inputBoxes = document.getElementsByClass("css-75hx9m e1a8pcii0");
        Elements secondInputBox = inputBoxes.get(1).getElementsByTag("input");
        String numberOfAds = secondInputBox.attr("placeholder");
        int total = Integer.parseInt(StringUtils.substringBetween(numberOfAds, "(", ")"));
        return total;
    }

    //Метод получения общего количества страниц
    public static int totalPagesAmount(int adsTotal) {
        int allPages = adsTotal / 20;
        return allPages;
    }


        //1. Метод получения даты размещения объявления
        public static ArrayList<String> getDates (Document documentNext, ArrayList < String > dates){
            Elements dateOfAdvert = documentNext.getElementsByAttributeValue("data-ftid", "bull_date");
            for (Element date : dateOfAdvert) {
                dates.add(date.text());
            }
            return dates;
        }


        //2. Метод получения всех загловков объявлений
        public static ArrayList<String> getTitles (Document documentNext, ArrayList < String > titles) {
            Elements titleOfAdvert = documentNext.getElementsByAttributeValue("data-ftid", "bull_title");
            for (Element title : titleOfAdvert) {
                titles.add(title.text());
            }
            return titles;
        }


        //3. Метод получения всех городов из превью объявления
        public static ArrayList<String> getCities (Document documentNext, ArrayList < String > cities) {
            Elements cityOfAdvert = documentNext.getElementsByClass("css-1488ad e162wx9x0");
            for (Element city : cityOfAdvert) {
                cities.add(city.text());
            }
            return cities;
        }

        //4. Метод получения цены из превью объявления
        public static ArrayList<String> getPrice (Document documentNext, ArrayList < String > prices) {
            Elements priceOfAdvert = documentNext.getElementsByAttributeValue("data-ftid", "bull_price");
            for (Element price : priceOfAdvert) {
                prices.add(price.text());
            }
            return prices;
        }


        //5. Метод получения информации со страниц объявлений по внутренней ссылке
        public static void getDataFromPage (Document documentNext,
                ArrayList < String > innerLinks,
                ArrayList < String > descriptions,
                ArrayList < String > viewers) throws IOException {
            Elements classOfAdvert = documentNext.getElementsByClass("css-5l099z ewrty961"); //получение одного объявления через класс
            for (Element absLink : classOfAdvert) {
                String advertLink = absLink.select("a").attr("href");
                innerLinks.add(advertLink);
            }
            for (String link : innerLinks) {
                Document advertDoc = Jsoup.connect(link).get();
                Elements descriptionElement = advertDoc.getElementsByClass("css-lm1m3k ezjvm5n1"); //получение описания из объявления
                descriptions.add(descriptionElement.text());
                Elements viewersElement = advertDoc.getElementsByClass("css-14wh0pm e1lm3vns0");
                viewers.add(viewersElement.text());
            }
        }
    }


