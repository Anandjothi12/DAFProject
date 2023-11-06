package ReportSchedular;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import subscriptionManagement.UtilityClass;

public class RSTestsuite extends UtilityClass {
	
	@BeforeSuite
    public void launchURL() throws InterruptedException {
        
	 launchBrowser();
	 dropdownselect();
    }

    @Test
    public void testCase1() {

    	// Wait until the search field is visible
    	WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
        
       WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = \"Configuration\"]")));
    	searchField.click();
    	
    	 waitpgm();
 	    
 	    driver.findElement(By.xpath("//span[text() = \"Report Scheduler\"]")).click();
    }
    
    @Test
    public void testcase2() {
    	
    	 WebElement searchField = driver.findElement(By.xpath("//input[@data-placeholder=\"Search\"]"));
	        if (searchField.isDisplayed()) {
	            System.out.println("Search field is present.");
	        } else {
	            System.out.println("Search field is not present.");
	        }
	        
	        String[] dropdowns = {"Select Report Type", "Select Status"};
	        for (String dropdownName : dropdowns) {
	            WebElement dropdown = driver.findElement(By.xpath("//span[text() = '" + dropdownName + "']"));
	            if (dropdown.isDisplayed()) {
	                System.out.println("Dropdown '" + dropdownName + "' is present.");
	            } else {
	                System.out.println("Dropdown '" + dropdownName + "' is not present.");
	            }
	        }
	        
	        WebElement schedulereport = driver.findElement(By.xpath("//span[text() = \" Schedule Report \"]"));
	        if (schedulereport.isDisplayed()) {
	            System.out.println("Schedule Report button is present.");
	        } else {
	            System.out.println("Schedule Report button is not present.");
	        }
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        });
	        
	        String[] headings = {"Report Type","Vehicle Group/Vehicle","Frequency","Recipient","Driver","Last Run","Next Run","Status"}; // Add more headings if needed
	        for (String heading : headings) {
	        	WebElement tableHeading = driver.findElement(By.xpath("//mat-header-cell//div[text()='" + heading + "']"));
	            if (tableHeading.isDisplayed()) {
	                System.out.println("Table heading '" + heading + "' is present.");
	            } else {
	                System.out.println("Table heading '" + heading + "' is not present.");
	            }
	        }
	        WebElement tableColumn;
	        try {
	            tableColumn = driver.findElement(By.xpath("//span[text()='Action']"));
	            System.out.println("Action is present.");
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            System.out.println("Action is not present.");
	        }
	    }
    
}
