package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class Wrappers {

    private static ChromeDriver driver;
    private static WebDriverWait wait;

    public Wrappers(ChromeDriver driver){
        Wrappers.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url){
        try{
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Error on opening URL:" + e.getMessage());
        }
    }

    public void typeText(By locator, String text){
        try{
            WebElement txtBox = wait.until(ExpectedConditions.elementToBeClickable(locator));
            txtBox.click();

            txtBox.sendKeys(Keys.CONTROL + "a");
            txtBox.sendKeys(Keys.DELETE);

            txtBox.sendKeys(text);
            txtBox.submit();

        }
        catch(Exception e){
            System.out.println("Error on typing Text: " + e.getMessage());
        }
    }

    public void click(By locator){
        try{
            WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ele.click();
        } catch (Exception e) {
            System.out.println("Error on Clicking: " + e.getMessage());
        }
    }

    public List<WebElement> getElements(By locator){
        try{
            return driver.findElements(locator);
        } catch (Exception e) {
            System.out.println("Error on getting list Elements: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public int getCount(List<WebElement> elements){
        try{
            return elements.size();
        }catch (Exception e){
            System.out.println("Error on getting list count: " + e.getMessage());
            return 0;
        }
    }

    public void iphoneTitleAndDiscount(List<WebElement> products){
        try{
            for(WebElement product:products){
                String discountText = product.getText();

                int discountValue =
                        Integer.parseInt(discountText.replaceAll("[^0-9]", ""));

                if (discountValue > 17) {
                    String title = product.findElement(By.xpath(
                            "ancestor::div[@class='ZFwe0M row']//div[@class='RG5Slk']")).getText();
                    System.out.println("Title of the product: " + title + " | Discount: " + product.getText());
                }

            }
        } catch (Exception e) {
            System.out.println("Error on printing Title and Discount" + e.getMessage());
        }
    }

    public void setFilter(By dropDown, By checkBox){
        try{
            if(driver.findElements(checkBox).isEmpty() || !driver.findElement(checkBox).isDisplayed()){
                WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(dropDown));
                dd.click();
            }
            WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(checkBox));
            cb.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error on setting Filter: " + e.getMessage());
        }
    }

    public void coffeeMugTitleAndImageURL(List<WebElement> products){
        try{
            Map<WebElement, Integer> reviews = new HashMap<>();

            for(WebElement ele:products){
                try{
                    String reviewTxt = ele.findElement(By.xpath(".//span[@class='PvbNMB']")).getText();
                    int reviewCnt = Integer.parseInt(reviewTxt.replaceAll("[^0-9]", ""));
                    reviews.put(ele, reviewCnt);
                } catch (Exception ignored) {
                }
            }

            List<Map.Entry<WebElement,Integer>> sortedList = new ArrayList<>(reviews.entrySet());
            sortedList.sort((a,b) -> b.getValue() - a.getValue());

            int size = Math.min(5, sortedList.size());

            System.out.println("========== Top 5 elements with highest rating ==========");
            for(int i=0;i<size;i++){

                int reviewCount = sortedList.get(i).getValue();
                System.out.println("Total reviews: " + reviewCount);
                WebElement title = sortedList.get(i).getKey().findElement(By.xpath(".//a[@class='pIpigb']"));
                System.out.println("Title of the product: " + title.getText());
                WebElement imgURL = sortedList.get(i).getKey().findElement(By.xpath(".//img[@class='UCc1lI']"));
                System.out.println("Image URL: " + imgURL.getAttribute("src"));

            }
        } catch (Exception e) {
            System.out.println("Error on printing Coffee Mugs: " + e.getMessage());
        }
    }

}

