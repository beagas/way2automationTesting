import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.StyleConstants;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Tests {
    WebDriver _globalDriver;

    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _globalDriver = new ChromeDriver();
        _globalDriver.get("https://www.way2automation.com/way2auto_jquery/draggable.php#load_box");
        _globalDriver.manage().window().maximize();
    }

    @Test//draggable test
    public void testTC0101() {
        _globalDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[1]/ul/li[1]/a/figure")).click();//select Draggable
        WebElement iframe = _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe"));//iframe visa judėjimo erdvė
        _globalDriver.switchTo().frame(iframe);
        WebElement dragElement = _globalDriver.findElement(By.id("draggable"));//velkamas objektas
        Actions dragAction = new Actions(_globalDriver);
        dragAction.clickAndHold(dragElement).moveByOffset(300, 150).build().perform();
        dragAction.clickAndHold(dragElement).moveByOffset(-100, -50).build().perform();
    }

        @Test//drag and drop test
    public void testTC0201() {
        _globalDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[1]/ul/li[2]/a/figure")).click();//select Droppable

        WebElement iframe = _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe"));//iframe visa judėjimo erdvė
        _globalDriver.switchTo().frame(iframe);
        WebElement dragElement = _globalDriver.findElement(By.xpath("/html/body/div[1]"));//velkamas objektas
        WebElement dropElement = _globalDriver.findElement(By.xpath("/html/body/div[2]"));//numetama vieta

        _globalDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        Actions dragAndDropAction = new Actions(_globalDriver);
        dragAndDropAction.moveToElement(dragElement).dragAndDrop(dragElement, dropElement).build().perform();//naudojamas veiksmas, kad nutempti objektą į vietą
    }

    @Test//resize object
    public void testTC0301() {
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[1]/ul/li[3]/a/figure")).click();//select Resizable
        WebElement iframe = _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe"));//iframe visa judėjimo erdvė
        _globalDriver.switchTo().frame(iframe);
        Actions action = new Actions(_globalDriver);
        WebElement resizeable = _globalDriver.findElement(By.xpath("/html/body/div/div[3]"));
        Action resize = action.clickAndHold(resizeable).moveByOffset(300, 100).release().build();
        resize.perform();
    }

    @Test//select object <pats tinklapis neveikia>
    public void testTC0401() {
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[1]/ul/li[4]/a/figure")).click();//select Selectable
        WebElement iframe = _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe"));//iframe visa judėjimo erdvė
        _globalDriver.switchTo().frame(iframe);
        Actions action = new Actions(_globalDriver);
        WebElement selectable = _globalDriver.findElement(By.id("draggable"));//velkamas objektas
        Action resize = action.clickAndHold(selectable).moveByOffset(300, 100).release().build();
        resize.perform();
    }

    @Test//Accordion
    public void testTC0601() {
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[2]/ul/li[1]/a/figure")).click();//select Selectable
        WebElement iframe = _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[1]/div[3]/div[1]/div/iframe"));//iframe visa judėjimo erdvė
        _globalDriver.switchTo().frame(iframe);

        _globalDriver.findElement(By.id("ui-id-1")).click();//pasirenkamas objektas
        String text = "Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.";
        WebElement result = _globalDriver.findElement(By.id("ui-id-2"));
        String openText = result.getText();
        Assert.assertEquals(text, openText);
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        _globalDriver.findElement(By.id("ui-id-3")).click();//pasirenkamas objektas
        text = "Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.";
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        result = _globalDriver.findElement(By.id("ui-id-4"));
        openText = result.getText();
        Assert.assertEquals(text, openText);
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        _globalDriver.findElement(By.id("ui-id-5")).click();//pasirenkamas objektas
        String text2 = "Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.\n" +
                "\n" +
                "List item one\n" +
                "List item two\n" +
                "List item three";
        WebElement result2 = _globalDriver.findElement(By.id("ui-id-6"));
        String openText2 = result2.getText();
        Assert.assertEquals(text2, openText2);
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        _globalDriver.findElement(By.id("ui-id-7")).click();//pasirenkamas objektas
        String text3 = "Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est.\n" +
                "\n" +
                "Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.";
        WebElement result3 = _globalDriver.findElement(By.id("ui-id-8"));
        String openText3 = result3.getText();
        Assert.assertEquals(text3, openText3);

        _globalDriver.close();
    }

    @Test//autocomplete
    public void testTC0701() {
        _globalDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/section/div[1]/div[2]/div[2]/ul/li[2]/a/figure")).click();//select Selectable
        _globalDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        _globalDriver.findElement(By.xpath("/html/body/div/input")).sendKeys("jav");//erdvė, kur rašomas tekstas
        _globalDriver.findElement(By.xpath("/html/body/div/input")).sendKeys(Keys.DOWN);
    }



}
