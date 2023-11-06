package subscriptionManagement;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SubscriptionTestSuite extends UtilityClass {
	
	 @BeforeSuite
	    public void launchURL() throws InterruptedException {
	        
		 launchBrowser();
		 dropdownselect();
	    }

	    @Test
	    public void testCase1() {
	        // Test case 1 is launching browser
	        System.out.println("Executing Test Case 1");
	    }
	    
	    @Test
	    public void testcase2() {
	    	
	    	// Wait until the search field is visible
	    	WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        });
	        
	       WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = \"Admin\"]")));
	    	searchField.click();
	    	
	    }
	    @Test
	    public void testcase3() {
	    	
	    waitpgm();
	    
	    driver.findElement(By.xpath("//span[text() = \"Subscription Management\"]")).click();
	   	waitpgm();
	    	
	        WebElement searchField = driver.findElement(By.xpath("//input[@data-placeholder=\"Search\"]"));
	        if (searchField.isDisplayed()) {
	            System.out.println("Search field is present.");
	        } else {
	            System.out.println("Search field is not present.");
	        }
	    	
	     // Verify the presence of the dropdowns
	        waitpgm();
	        
	        String[] dropdowns = {"Organisation", "Select Type", "Select Status" };
	        for (String dropdownName : dropdowns) {
	            WebElement dropdown = driver.findElement(By.xpath("//span[text() = '" + dropdownName + "']"));
	            if (dropdown.isDisplayed()) {
	                System.out.println("Dropdown '" + dropdownName + "' is present.");
	            } else {
	                System.out.println("Dropdown '" + dropdownName + "' is not present.");
	            }
	        }
	        //Verify Export is present
	        waitpgm();
	        
	        WebElement export = driver.findElement(By.xpath("//span[text() = \"Export\"]"));
	        if (export.isDisplayed()) {
	            System.out.println("Export field is present.");
	        } else {
	            System.out.println("Export field is not present.");
	        }
	        
	     // Verify the presence of table headings
	        waitpgm();
	        String[] headings = {"Order ID", "Package Code","Package Name","Vehicle","Start Date","End Date","Status"}; // Add more headings if needed
	        for (String heading : headings) {
	        	WebElement tableHeading = driver.findElement(By.xpath("//div[text()='" + heading + "']"));
	            if (tableHeading.isDisplayed()) {
	                System.out.println("Table heading '" + heading + "' is present.");
	            } else {
	                System.out.println("Table heading '" + heading + "' is not present.");
	            }
	        }
	        
	        WebElement tableColumn;
	        try {
	            tableColumn = driver.findElement(By.xpath("//mat-header-cell[text() = ' Action ']"));
	            System.out.println("Action is present.");
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            System.out.println("Action is not present.");
	        }
	    }
	    
	    @Test
	    public void testcase4() {
	    	
	    	 // Get the handle of the main tab
	    	 String mainTabHandle = driver.getWindowHandle();
	        
	    	driver.findElement(By.xpath("//span[text() = \"Export\"]")).click();
	    	driver.findElement(By.xpath("//button[text() = \"PDF (as snapshot)\"]")).click();
	    	
	    	// Switch to the new tab
	        String newTabHandle = switchToNewTab(driver, mainTabHandle);

	        // Execute JavaScript to open the PDF link in a new window
	        String pdfLink = driver.getCurrentUrl();
	        openLinkInNewWindow(driver, pdfLink);

	        // Perform actions in the PDF report window (e.g., interact with the PDF)

	        // Close the PDF report window
	        driver.close();

	        // Switch back to the main tab
	        driver.switchTo().window(mainTabHandle);

	        // Continue interacting with the main tab (e.g., perform further actions)

	        // Close the browser
	        driver.quit();
	    }

	    private static String switchToNewTab(WebDriver driver, String mainTabHandle) {
	        Set<String> tabHandles = driver.getWindowHandles();
	        for (String handle : tabHandles) {
	            if (!handle.equals(mainTabHandle)) {
	                return handle;
	            }
	        }
	        throw new RuntimeException("New tab not found.");
	    }

	    private static void openLinkInNewWindow(WebDriver driver, String link) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.open(arguments[0])", link);
	    }
 	
}
