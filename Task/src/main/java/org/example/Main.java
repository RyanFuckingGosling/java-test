package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://ya.ru/");

        //Режим полноэкранного браузера
        driver.manage().window().maximize();

        //wait for WebElement
        driver.manage().timeouts().implicitlyWait(50000,
                TimeUnit.MILLISECONDS);

        //wait for loading page
        driver.manage().timeouts().pageLoadTimeout(100000,
                TimeUnit.MILLISECONDS);

        //wait for an asynchronous script to finish execution
        driver.manage().timeouts().setScriptTimeout(50000,
                TimeUnit.MILLISECONDS);

        //Активация поля ввода и нажатие на кнопку "Маркет"
        driver.findElement(By.xpath("//*[@id=\"text\"]")).click();
        driver.findElement(By.xpath("/html/body/main/div[3]/form/nav/ul/li[1]/a/div[2]")).click();


        //Переход на следующую вкладку браузера
        for (String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }

        //Поиск и на нажатие на кнопку "Каталог" и "Смартфоны"
        driver.findElement(By.xpath("//*[@id=\"hamburger\"]")).click();
        driver.findElement(By.xpath("/html/body/div[30]/div/div/div/div/div/div[2]/div/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[1]/ul/li[1]/div")).click();
        //Переход на последнюю вкладку барузера
        for (String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }

        //Нажатие на кнопку "Фул фильтры
        driver.findElement(By.xpath("//*[@id=\"searchFilters\"]/div/div[4]/div/div/div/a/button/span/span")).click();

        //Определение значения для максимальной стоимости и ввод в поле
        String max_price = "20000";
        driver.findElement(By.xpath("//*[@id=\"glprice\"]/div/div[2]/input")).sendKeys(max_price);


        //Скролинг на 500 пикселей вниз
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(1000);

        //Выбор производителей
        driver.findElement(By.xpath("//*[@id=\"153043\"]/div")).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"153061\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"15292504\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"22402130\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"459710\"]/div"))).click();
        Thread.sleep(1000);

        //Скролинг на 500 пикселей вниз
        js.executeScript("window.scrollBy(0,500)", "");

        //Откытие окна с диагоналями и выбор нужных параметров
        driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[2]/div[1]/div[9]/div/button/h4")).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"24896372\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"24896370\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"24896374\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"24896373\"]/div"))).click();
        Thread.sleep(1000);
        driver.findElement((By.xpath("//*[@id=\"24896371\"]/div"))).click();
        Thread.sleep(1000);

        //Нажать кнопку Подобрать
        driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div/div/div[3]/div/div/a[2]")).click();
        Thread.sleep(1000);

        //Проверка и вывод колличества элементов на странице (до начала подгрузок)
        int count = driver.findElements(By.className("nXZ_7")).size();

        //Вывод колличесва элементов на страинце
        System.out.println(count);


        //Нахождение названия первого элемента
        String text = driver.findElement(By.className("_1GfBD")).getText();
        String[] name = text.split("\\s+");


        int endindex = 0;
        int startindex = 0;
        for (int i = 0; i < name.length; i++) {
            if (name[i].equals("Смартфон")) {
                startindex = i;
            }
            if (name[i].equals("Экран:")) {
                endindex = i;
            }
        }
        String[] semy_result = Arrays.copyOfRange(name, startindex + 1, endindex);
        String result = String.join(" ", semy_result);


        //Смена сортировки на По популярности
        driver.findElement(By.xpath("//*[@id=\"serpTop\"]/div/div/div[1]/div/div/noindex/div/button[3]")).click();

        //Поиск названия по страницам и открытие ссылки
        while (true) {

            try {
                WebElement flag = driver.findElement(By.xpath("//*[text()[contains(.,'" + result + "')]]"));
                js.executeScript("arguments[0].scrollIntoView();", flag);
                driver.findElement(By.xpath("//*[text()[contains(.,'" + result + "')]]")).click();
                break;
            } catch (Throwable t) {
                WebElement flag = driver.findElement(By.xpath("//*[text()[contains(.,'Показать ещё')]]"));
                js.executeScript("arguments[0].scrollIntoView();", flag);
                driver.findElement(By.xpath("//*[text()[contains(.,'Показать ещё')]]")).click();
            }
        }

        //Переход на последнюю вкладку барузера
        for (String tab : driver.getWindowHandles()){
            driver.switchTo().window(tab);
        }

        //Вывод оценки продукта
        try {
            String test2 = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/main/div[5]/div/div[2]/div/div/div[1]/div/div/div/div/div/div[1]/a/div/div[2] ")).getText();
            String[] new_name = test2.split("\\s+");
            System.out.println(new_name[1]);
        } catch (Throwable t) {
            System.out.println(driver.findElement(By.xpath("/html/body/div[2]/div[2]/main/div[4]/div/div/div[2]/div/div/div[2]/div[1]/a/div/span[1]")).getText());
        }

        //Выключение браузера
        driver.quit();


    }

}