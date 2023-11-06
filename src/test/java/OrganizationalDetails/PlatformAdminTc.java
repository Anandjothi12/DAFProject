package OrganizationalDetails;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlatformAdminTc extends UtilityClass{
	
	@Test(priority=1)
	public void TC4() throws IOException {
		WebDriverWait wait = new WebDriverWait(d, 100);
		WebElement page=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']")));
		boolean verify = page.getText().contains("Organisation Information")&& page.getText().contains("Preferences");	

		Assert.assertTrue(verify, "Organization Information section contains one or more unexpected fields");

		WebElement organisationpg=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src=organisationpg.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_004\\pic.png");
		FileUtils.copyFile(src,trg);
	}  


	@Test(priority=2)
	public void TC5() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(d, 100);
		WebElement page=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']")));
		Thread.sleep(3000);
		boolean verify = page.getText().contains("ORganization Details")&& page.getText().contains("ID")&& page.getText().contains("Name")&& page.getText().contains("Street Name")&& page.getText().contains("Street Number")
				&& page.getText().contains("Postal Code")&& page.getText().contains("City")&& page.getText().contains("Country");	

		Assert.assertTrue(verify, "Organization Information section contains expected fields");
 
		WebElement organisationpg = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src = organisationpg.getScreenshotAs(OutputType.FILE);
		File trg = new File(".\\organisation relationship management\\TC_004\\pic.png");
		FileUtils.copyFile(src, trg);
	}

	@Test(priority=3)
	public void TC6_1() {
		WebElement page=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		WebElement editicon=d.findElement(By.xpath("//*[@style='cursor: pointer; margin: 0px 0px 5px 5px;']"));
		boolean verify=editicon.isDisplayed()&&page.getText().contains("Preferences")&&page.getText().contains("Language")&&page.getText().contains("Time Zone")&&page.getText().contains("Unit")
				&&page.getText().contains("Date Format")&&page.getText().contains("Time Format")&&page.getText().contains("Vehicle Default Status")
				&&page.getText().contains("Driver Default Status")&&page.getText().contains("Set Page Refresh Time")&&page.getText().contains("Brand Logo") ;

		Assert.assertTrue(verify, "Preferences setting section should not be displayed with an Edit icon and with the  fields ");		
	}

	@Test(priority=4)
	public void TC6_2() throws IOException {
		WebElement page=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));	
		System.out.println(page.getText());
		File src=page.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_006\\pic.png");
		FileUtils.copyFile(src,trg);
	}

	@Test(priority=5)
	public void TC7_1() throws IOException {
		List<WebElement> editicons = d.findElements(By.xpath("//mat-icon[@class='mat-icon notranslate material-icons mat-icon-no-color ng-star-inserted']"));		
		boolean verify= !editicons.isEmpty();
		Assert.assertTrue(verify, "Edit icon exists.");
		d.findElement(By.xpath("//*[@class='mat-icon notranslate material-icons mat-icon-no-color ng-star-inserted']")).click();
		WebElement editablity=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card/mat-card-content/div[4]/form"));
		File src=editablity.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_007\\pic.png");		
		FileUtils.copyFile(src,trg);		
	}

	@Test(priority=6)
	public  void TC7_2() {
		WebElement logo1=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card/mat-card-content/div[4]/form/div[2]/div[3]/div[3]/div/div[1]/img"));
		WebElement browsebutton=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card/mat-card-content/div[4]/form/div[2]/div[3]/div[3]/div/div[2]/mat-form-field/div/div[1]/div[4]/button"));
		WebElement cancel=d.findElement(By.xpath("//*[@class='mat-focus-indicator resetCancelBtnColor margin-right-10 mat-raised-button mat-button-base mat-primary']"));
		WebElement reset=d.findElement(By.xpath("//*[@class='mat-focus-indicator resetCancelBtnColor margin-right-10 no-margin mat-raised-button mat-button-base mat-primary']"));
		WebElement confirm=d.findElement(By.xpath("//*[@class='mat-focus-indicator no-margin mat-raised-button mat-button-base mat-primary']"));

		boolean verify=logo1.isDisplayed()&&browsebutton.isEnabled()&&cancel.isEnabled()&&reset.isEnabled()&&confirm.isEnabled();
		Assert.assertTrue(verify, "logo,browse,cancel,reset,confirm buttons are not existed in page");
	}

	@Test(priority=7)
	public void TC7_3() throws InterruptedException {
		WebElement Language =d.findElement(By.xpath("//*[@formcontrolname='language']"));

		boolean verify=Language.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify, "The Language field is not a dropdown ");
 
		Language.click();
		Thread.sleep(5000);			
		List<WebElement> optionElements = d.findElements(By.cssSelector("mat-option[role='option']"));
		System.out.println(optionElements.size());
		//d.findElement(By.xpath("//*[@role='option'][3]")).click();
		int expectedLanguageCount = 26;

		Assert.assertEquals(optionElements.size(), expectedLanguageCount, "The Language dropdown does not have the expected number of languages");
		d.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);		
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'Greek')]")).click();



		//step3(type of timezone field and check data)
		Thread.sleep(5000);
		WebElement timeZoneDropdown = d.findElement(By.xpath("//*[@formcontrolname='timeZone']"));
		boolean verify1=timeZoneDropdown.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify1, "The timeZone field is not a dropdown ");

		timeZoneDropdown.click();
		List<WebElement> options = d.findElements(By.cssSelector("mat-option[role='option']"));
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
		boolean hasUtcOption = false;
		for (int i = 1; i < options.size(); i++) {
			WebElement option = options.get(i);
			String timeZone = option.getAttribute("textContent").trim();
			if (timeZone.contains("UTC")) {
				hasUtcOption = true;
				break;
			}
		}

		Assert.assertTrue(hasUtcOption, "The Time Zone dropdown does not contain options with 'UTC'.");


		d.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);		
		d.findElement(By.xpath("(//span[contains(text(), '(UTC +05:30) Asia/Kolkata')])[1]")).click();

		//step4(type of Unit field and check data)
		Thread.sleep(3000);
		WebElement  Unit=d.findElement(By.xpath("//*[@formcontrolname='unit']"));
		boolean verify2=Unit.getAttribute("aria-haspopup") != null;

		Assert.assertTrue(verify2, "The unit field is not a dropdown ");

		Unit.click();
		List<String> unitexpectedOptions = Arrays.asList("Imperial", "Metric"); 
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
		List<WebElement> unitoptionElements = d.findElements(By.tagName("mat-option"));
		for (WebElement unitoptionElement : unitoptionElements) {
			String unitoptionText = unitoptionElement.getText().trim();
			boolean check=unitexpectedOptions.contains(unitoptionText);

			Assert.assertTrue(check, "The Unitdropdown options does not match the expected values ");

		} 


Thread.sleep(3000);
		d.findElement(By.xpath("(//span[contains(text(), ' Imperial ')])[1]")).click();

		//step5(type of DateFormat field and check data)
		WebElement DateFormat=d.findElement(By.xpath("//*[@formcontrolname='dateFormat']"));

		boolean verify3=DateFormat.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify3, "The DateFormat field is not a dropdown ");

		DateFormat.click();
		List<String> dateexpectedOptions = Arrays.asList("dd-mm-yyyy", "dd/mm/yyyy","mm-dd-yyyy","mm/dd/yyyy","yyyy-mm-dd","yyyy/mm/dd"); 
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> dateoptionElements1 = d.findElements(By.tagName("mat-option"));
		for (WebElement dateoptionElement : dateoptionElements1) {
			String dateoptionText = dateoptionElement.getText().trim();
			boolean check1=dateexpectedOptions.contains(dateoptionText);

			Assert.assertTrue(check1, "The DateFormat options does not match the expected values ");
		} 


		d.findElement(By.xpath("//mat-option/span[contains(text(), 'mm-dd-yyyy')]")).click();

		//step6(type of TimeFormat field and check data)
		WebElement TimeFormat=d.findElement(By.xpath("//*[@formcontrolname='timeFormat']"));
		boolean verify4=TimeFormat.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify4, "The TimeFormat field is not a dropdown ");
		TimeFormat.click();
		List<String> timeexpectedOptions = Arrays.asList("12 Hours", "24 Hours"); 
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);	
		List<WebElement> timeoptionElements1 = d.findElements(By.tagName("mat-option"));
		for (WebElement timeoptionElement : timeoptionElements1) {
			String timeoptionText = timeoptionElement.getText().trim();
			boolean check2 =timeexpectedOptions.contains(timeoptionText);

			Assert.assertTrue(check2, "The TimeFormat options does not match the expected values ");
		} 


		d.findElement(By.xpath("//mat-option/span[contains(text(), '12 Hours')]")).click();

		//step7(type of VehicleStatus field and check data)
		WebElement VehicleStatus=d.findElement(By.xpath("//*[@formcontrolname='vehicleDefaultStatus']"));
		boolean verify5=VehicleStatus.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify5, "The VehicleStatus field is not a dropdown ");

		VehicleStatus.click();
		List<String> vehexpectedOptions = Arrays.asList("Opt-In", "Opt-Out","Inherit"); 
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> vehoptionElements1 = d.findElements(By.tagName("mat-option"));
		for (WebElement vehoptionElement : vehoptionElements1) {
			String vehoptionText = vehoptionElement.getText().trim();
			boolean check3=vehexpectedOptions.contains(vehoptionText); 
			Assert.assertTrue(check3, "The VehicleStatus options does not match the expected values ");
		} 


		d.findElement(By.xpath("//*[@role='option'][2]")).click();

		//step8(type of DriverStatus field and check data)
		WebElement DriverStatus=d.findElement(By.xpath("//*[@formcontrolname='driverDefaultStatus']"));
		boolean verify6=DriverStatus.getAttribute("aria-haspopup") != null;
		Assert.assertTrue(verify6, "The DriverStatus field is not a dropdown ");

		DriverStatus.click();
		List<String> driverexpectedOptions = Arrays.asList("Opt-In","Opt-Out", "Inherit"); 
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> driveroptionElements1 = d.findElements(By.tagName("mat-option"));
		for (WebElement driveroptionElement : driveroptionElements1) {
			String driveroptionText = driveroptionElement.getText().trim();
			boolean check4= driverexpectedOptions.contains(driveroptionText);
			Assert.assertTrue(check4, "The DriverStatus options does not match the expected values ");
		} 


		d.findElement(By.xpath("//*[@role='option'][2]")).click();

	}

	@Test(priority=8)
	public void TC7_4() throws AWTException, InterruptedException {
		Thread.sleep(5000);
		WebElement setPage = d.findElement(By.xpath("//*[@formcontrolname='pageRefreshTime']"));
		String fieldType = setPage.getAttribute("type");
		Assert.assertTrue(fieldType.equalsIgnoreCase("text"), "Type of 'Set Page Refresh Time' field is not a Text box");

		WebElement field =d.findElement(By.xpath("//*[@formcontrolname='pageRefreshTime']"));
		Actions actions = new Actions(d);
		actions.moveToElement(field).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();;   
		d.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);		
		WebElement errormsg=d.findElement(By.xpath("//*[@role='alert']"));
		Assert.assertEquals(errormsg.getText(), "Please Enter Refresh Time", "Error message is not displayed for blank field");

		// Step 11 (validate error msg when setpage field is >60)
		field.sendKeys("63");
		WebElement errormsg1=d.findElement(By.xpath("//*[@role='alert']"));
		d.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Assert.assertEquals(errormsg1.getText(), "Refresh Time cannot be greater than 60", "Error message is not displayed for refresh time > 60");

		WebElement format=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card/mat-card-content/div[4]/form/div[2]/div[3]/div[3]/span"));
		String fileTypesMessage = format.getText();
		boolean verify = fileTypesMessage.contains("jpg")&&fileTypesMessage.contains("png")&&fileTypesMessage.contains("gif");	
		Assert.assertTrue(verify, "The supporting file type are other than .jpg,.png,.gif");


		setPage.click();
		actions.click(field).sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.DELETE).perform();   
		field.sendKeys("4");
		d.findElement(By.xpath("(//*[@mat-flat-button=\"\"])[2]")).click();
		Robot robot=new Robot();
		robot.delay(1000);
		String filePath = "C:\\Users\\a828958\\OneDrive - Atos\\Desktop\\DAF Project\\Automation\\testupload.png";
		StringSelection stringSelection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		d.findElement(By.xpath("(//*[@class='mat-icon notranslate material-icons mat-icon-no-color'])[3]")).click();
	}

	@Test(priority=9)
	public void TC8() throws IOException, InterruptedException {
		//language
		d.findElement(By.xpath("//mat-select[@formcontrolname='language']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'Greek')]")).click();
		//Date Format 
		d.findElement(By.xpath("//mat-select[@formcontrolname='dateFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'mm-dd-yyyy')]")).click();
		//Time Format
		d.findElement(By.xpath("//mat-select[@formcontrolname='timeFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), '12 Hours')]")).click();

		//cancel
		d.findElement(By.xpath("//button/span[contains(text(), 'Cancel')]")).click();
		Thread.sleep(5000);
		WebElement language=d.findElement(By.xpath("(//span[@class='font-helvetica-md'])[3]"));
		boolean verify1=language.getText().contains("Greek");
		Assert.assertFalse(verify1, "cancel functionality is working");
		
		WebElement verify=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card"));
		File src=verify.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_008\\pic.png");	
		FileUtils.copyFile(src,trg);
		System.out.println("pass");
	}

	@Test(priority=10)
	public void TC9() throws InterruptedException, IOException {
		d.findElement(By.xpath("//*[@class='mat-icon notranslate material-icons mat-icon-no-color ng-star-inserted']")).click();

		
		//step1 (a)(modifying data)
		//language
		d.findElement(By.xpath("//mat-select[@formcontrolname='language']")).click();
		Thread.sleep(5000);
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'Greek')]")).click();
		//Date Format 
		d.findElement(By.xpath("//mat-select[@formcontrolname='dateFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'mm-dd-yyyy')]")).click();
		//Time Format
		d.findElement(By.xpath("//mat-select[@formcontrolname='timeFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), '12 Hours')]")).click();

		//(b)(click on reset button)
		//Reset
		d.findElement(By.xpath("//button/span[contains(text(), 'Reset')]")).click();
	
		//Thread.sleep(5000);
		WebElement language=d.findElement(By.xpath("//mat-select[@formcontrolname='language']"));
		boolean verify1=language.getText().contains("Greek");
		Assert.assertFalse(verify1, "Reset functionality is working");
		
		Thread.sleep(3000);   
		WebElement verify=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card"));
		File src=verify.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_009\\pic.png");
		FileUtils.copyFile(src,trg);
		System.out.println("pass");
	}

	@Test(priority=11)
	public void TC10() throws InterruptedException, IOException {
		//language
		d.findElement(By.xpath("//mat-select[@formcontrolname='language']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'Greek')]")).click();
		//Date Format 
		d.findElement(By.xpath("//mat-select[@formcontrolname='dateFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), 'mm-dd-yyyy')]")).click();
		//Time Format
		d.findElement(By.xpath("//mat-select[@formcontrolname='timeFormat']")).click();
		d.findElement(By.xpath("//mat-option/span[contains(text(), '12 Hours')]")).click();

		//(b)(click on confrim button)
		//confirm 
		d.findElement(By.xpath("//button/span[contains(text(), 'Confirm')]")).click();
		WebElement validationmsg=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/div/div[1]"));
	    boolean verify1=validationmsg.getText().contains("Organisation Details Updated Successfully");
	    Assert.assertTrue(verify1, "Organisation Details Updated Successfully");
	  
	    Thread.sleep(3000);   
		WebElement verify=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-admin/app-organisation-details/div/mat-card"));
		File src=verify.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_010\\pic.png");
		FileUtils.copyFile(src,trg);
		System.out.println("pass");

	}


	@Test(priority=12)
	public void TC11() throws InterruptedException, IOException {

		logout();
        Thread.sleep(5000);
		login("jothilakshmi.anand@atos.net", "Jothilakshmi@0308");

		//usermanagement 
		
        Thread.sleep(10000);
		WebElement UserManagementLink = d.findElement(By.xpath("//a/span[contains(text(), 'User Management')]"));

		UserManagementLink.click();

		Thread.sleep(10000);

		WebElement newusermanbutton =d.findElement(By.xpath("(//*[@class='mat-focus-indicator mat-flat-button mat-button-base mat-primary'])[2]"));

		newusermanbutton.click();

		Thread.sleep(10000);
		
		String  language=d.findElement(By.xpath("//*[@formcontrolname='language']")).getText();
		String	timezone=d.findElement(By.xpath("//*[@formcontrolname='timeZone']")).getText();
		String  unit=d.findElement(By.xpath("//*[@formcontrolname='unit']")).getText();
		String	dateformat=d.findElement(By.xpath("//*[@formcontrolname='dateFormat']")).getText();
		String	timeformat=d.findElement(By.xpath("//*[@formcontrolname='timeFormat']")).getText();

		WebElement usermanagement=d.findElement(By.xpath("//mat-card[@class='mat-card mat-focus-indicator mat-elevation-z margin-bottom-xlg']"));
		File src=usermanagement.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_011\\pic.png");
		FileUtils.copyFile(src,trg);

//organ
		
		WebElement OrganisationLink = d.findElement(By.xpath("//a/span[contains(text(), 'Organisation details and preferences')]"));
		OrganisationLink.click();


		d.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String  language1=d.findElement(By.xpath("(//*[@class='font-helvetica-md'])[3]")).getText();
		String	timezone1=d.findElement(By.xpath("//*[@class='text-align font-helvetica-md']")).getText();
		String  unit1=d.findElement(By.xpath("(//*[@class='font-helvetica-md'])[4]")).getText();
		String	dateformat1=d.findElement(By.xpath("(//*[@class='font-helvetica-md'])[5]")).getText(); 
		String	timeformat1=d.findElement(By.xpath("(//*[@class='font-helvetica-md'])[6]")).getText();

		WebElement organisation=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src1=organisation.getScreenshotAs(OutputType.FILE);
		File trg1=new File(".\\organisation relationship management\\TC_011\\pic1.png");
		FileUtils.copyFile(src1,trg1);

		boolean verify=language.equals(language1)&&timezone.equals(timezone1)&&unit.equals(unit1)&&dateformat.equals(dateformat1)&&timeformat.equals(timeformat1);

		Assert.assertTrue(verify, "User management data is not matching with organisation details and preferences data");

	}
	@Test(priority=13)
	public void TC12() throws IOException {

		WebElement page=d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));

		boolean verify=page.getText().contains("street name")&&page.getText().contains("street number");

		Assert.assertTrue(verify, "street name and street number are not existed in the page");

		WebElement streetname = d.findElement(By.xpath("//a//span[contains(text(), 'Street Name')]"));
		streetname.sendKeys("1234 Very Long Street Name with Extra Characters Avenue");

		String enteredstreetname = streetname.getAttribute("value");
		boolean isstreetnameTruncated = enteredstreetname.length() <= 50;

		Assert.assertTrue(isstreetnameTruncated, "street name can be more than 50 characters");


		WebElement streetnumber = d.findElement(By.xpath("//a//span[contains(text(), 'Street Number')]"));
		streetnumber.sendKeys("1234 Very Long Street Name with Extra Characters Avenue");

		String enteredstreetnumber = streetnumber.getAttribute("value");
		boolean isstreetnumberTruncated = enteredstreetnumber.length() <= 50;

		Assert.assertTrue(isstreetnumberTruncated, "street number can be more than 50 characters");

		File src=page.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\organisation relationship management\\TC_012\\pic.png");
		FileUtils.copyFile(src,trg);

	}
}


