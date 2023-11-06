package DriverManagement;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.common.collect.Ordering;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@SuppressWarnings("deprecation")
public class DriverManagementTc extends BaseClass1{

	@Test(priority=1)
	public void TC_003() throws InterruptedException 	{
		//verify all fields 
		Thread.sleep(10000);
		WebElement DownloadTemplate =d.findElement(By.linkText("Download a Template"));
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement TextBox =d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/mat-card[1]/form/div[2]/div[2]/div/div/div/div[1]/mat-form-field/div/div[1]/div[3]"));
		WebElement Importbutton =d.findElement(By.xpath("(//*[@color='primary'])[3]"));
		WebElement BrowseButton =d.findElement(By.xpath("(//*[@color='primary'])[2]"));
		//Thread.sleep(5000);
		WebElement Search =d.findElement(By.xpath("//*[@placeholder='Search']"));
		WebElement DropDown =d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/mat-card[2]/p-table/div/div[1]/div/div[2]/p-columnfilter/div/p-columnfilterformelement/p-dropdown/div/span"));
		WebElement DriverID =d.findElement(By.xpath("(//*[@role='columnheader'])[1]"));
		WebElement DriverName =d.findElement(By.xpath("(//*[@role='columnheader'])[2]"));
		WebElement Emailid =d.findElement(By.xpath("(//*[@role='columnheader'])[3]"));
		WebElement Consent =d.findElement(By.xpath("(//*[@role='columnheader'])[4]"));
		WebElement Actions =d.findElement(By.xpath("(//*[@role='columnheader'])[5]"));
		WebElement Reset =d.findElement(By.xpath("(//*[@color='primary'])[4]"));
		WebElement optinButton =d.findElement(By.xpath("(//*[@color='primary'])[5]"));
		WebElement optoutButton=d.findElement(By.xpath("(//*[@color='primary'])[6]"));
		boolean allfields = DownloadTemplate.isDisplayed() && TextBox.isDisplayed() && Importbutton.isDisplayed() && 
				BrowseButton.isDisplayed() && Search.isDisplayed() && DropDown.isDisplayed() && 
				DriverID.isDisplayed() && DriverName.isDisplayed() && Emailid.isDisplayed() && 
				Consent.isDisplayed() && Actions.isDisplayed() && Reset.isDisplayed() &&
				optinButton.isDisplayed() && optoutButton.isDisplayed();
		Assert.assertTrue(allfields, "all fields are not available on Driver Management under Configuration Menu");
	}

	@Test(priority=2)
	public void TC_002() throws InterruptedException, IOException {
		//click opt-in icon
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");	
		//d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String driverid = d.findElement(By.xpath("//span[contains(text(), 'B  B1100001234590')] ")).getText();
		Thread.sleep(5000);
		String drivername = d.findElement(By.xpath("//span[contains(text(), ' Meghana sweety ')] ")).getText();
		WebElement optInIcon = d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]"));
		optInIcon.click();
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean popupbox = driverConsentModal.isDisplayed();
		Assert.assertTrue(popupbox, "driver consentmodal is not displayed");
		File src=driverConsentModal.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_002\\DriverConsentModal.png");
		FileUtils.copyFile(src,trg); 

		//verify all the fields in driver consent model
		WebElement ModalTitle = d.findElement(By.xpath("(//*[@fxlayout='row'])[8]"));
		WebElement expectedDriverName = d.findElement(By.xpath("(//span[contains(text(), 'Meghana sweety')])[2] "));
		WebElement expectedDriverid = d.findElement(By.xpath("(//span[contains(text(), 'B  B1100001234590')])[2] "));
		WebElement currentconsentstatus = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		boolean verifyfields = ModalTitle.isDisplayed() && expectedDriverName.getText().equals(drivername) && 
				expectedDriverid.getText().equals(driverid) && currentconsentstatus.isDisplayed();
		Assert.assertTrue(verifyfields, "all fields are not available on Driver consent model");
		System.out.println(ModalTitle.getText());
		System.out.println(expectedDriverName.getText());
		System.out.println(expectedDriverid.getText());
		System.out.println(currentconsentstatus.getText());

		//optout click
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[3]/label/span[2]")).click();
		WebElement disclaimerMessage = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content"));
		boolean disclaimermsg =disclaimerMessage.isDisplayed(); 
		Assert.assertTrue(disclaimermsg, "disclaimar msg is not displayed");
		File src1=disclaimerMessage.getScreenshotAs(OutputType.FILE);
		File trg1=new File(".\\Driver_Management\\TC_DM_002\\optoutdisclaimerMessage.png");
		FileUtils.copyFile(src1,trg1);

		//cancel
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverslandingpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean DMmainpage = driverslandingpg.isDisplayed();
		Assert.assertTrue(DMmainpage, "Driver mainpage is not displayed");

		//again click ooptin icon
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");	
		d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]")).click();

		//optout click
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[3]/label/span[2]")).click();

		//click confirm and verify the successfully updated or not
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		Thread.sleep(5000);
		WebElement Updatemsg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/div/div[1]"));
		boolean updatemsg = Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());

		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");
		Thread.sleep(5000);
		WebElement updatedoptoutSS = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/mat-card[2]"));
		File src2=updatedoptoutSS.getScreenshotAs(OutputType.FILE);
		File trg2=new File(".\\Driver_Management\\TC_DM_002\\updatedoptoutSS.png");
		FileUtils.copyFile(src2,trg2);
		//clear search
		WebElement Search5=d.findElement(By.xpath("//*[@placeholder='Search']"));
		Actions actions = new Actions(d);
		actions.moveToElement(Search5).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}

	@Test(priority=3)
	public void TC_005() throws IOException, InterruptedException {
		//click opt-out icon
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");	
		//d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		String driverid = d.findElement(By.xpath("//span[contains(text(), 'B  B1100001234590')] ")).getText();
		Thread.sleep(5000);
		String drivername = d.findElement(By.xpath("//span[contains(text(), ' Meghana sweety ')] ")).getText();
		WebElement optoutIcon = d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]"));
		optoutIcon.click();
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean DriverConsentModal = driverConsentModal.isDisplayed();
		Assert.assertTrue(DriverConsentModal, "opt-out driver consent model is not displayed");
		File src=driverConsentModal.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_005_SS\\DriverConsentModal.png");
		FileUtils.copyFile(src,trg);

		//verify all the fields in driver consent model
		WebElement ModalTitle = d.findElement(By.xpath("(//*[@fxlayout='row'])[8]"));
		WebElement expectedDriverName = d.findElement(By.xpath("(//span[contains(text(), 'Meghana sweety')])[2] "));
		WebElement expectedDriverid = d.findElement(By.xpath("(//span[contains(text(), 'B  B1100001234590')])[2] "));
		WebElement currentconsentstatus = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		boolean verifyfields = ModalTitle.isDisplayed() && expectedDriverName.getText().equals(drivername) &&
				expectedDriverid.getText().equals(driverid) && currentconsentstatus.isDisplayed();
		Assert.assertTrue(verifyfields, "all fields are not available on Driver consent model");
		System.out.println(ModalTitle.getText());
		System.out.println(expectedDriverName.getText());
		System.out.println(expectedDriverid.getText());
		System.out.println(currentconsentstatus.getText());

		//optin click
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[2]/label/span[2]")).click();
		WebElement disclaimerMessage = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content"));
		boolean DisclaimerMessage = disclaimerMessage.isDisplayed();
		Assert.assertTrue(DisclaimerMessage, "opt-out driver consent model is not displayed");
		File src1=disclaimerMessage.getScreenshotAs(OutputType.FILE);
		File trg1=new File(".\\Driver_Management\\TC_DM_005_SS\\optindisclaimerMessage.png");
		FileUtils.copyFile(src1,trg1);

		//cancel
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverslandingpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Driverslandingpg = driverslandingpg.isDisplayed();
		Assert.assertTrue(Driverslandingpg, "Driverslandingpage is not displayed");

		//again click optout icon
		Thread.sleep(5000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");	
		d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]")).click();

		//optin click
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[2]/label/span[2]")).click();

		//click confirm and verify it is successfully updated or not
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		Thread.sleep(5000);
		WebElement Updatemsg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/div/div[1]"));
		boolean updatemsg = Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());

		Thread.sleep(5000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("meghana s");
		Thread.sleep(5000);
		WebElement updatedoptoutSS = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/mat-card[2]/p-table/div/div[2]/table/tbody/tr"));
		File src2=updatedoptoutSS.getScreenshotAs(OutputType.FILE);
		File trg2=new File(".\\Driver_Management\\TC_DM_005_SS\\updatedoptinSS.png");
		FileUtils.copyFile(src2,trg2);
	}

	@Test(priority=4)
	public void TC_004() throws InterruptedException, IOException, AWTException {
		//download template
		Thread.sleep(10000);
		d.findElement(By.linkText("Download a Template")).click();
		String filePath = "C:\\Users\\a828958\\Downloads\\driver-Template.xlsx";
		String sheetName = "Driver Template";
		int rowNumber = 1;
		int cellNumber = 1; // The first column (0-indexed) of the Excel file

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);

			// Check if the sheet with the given name exists in the workbook
			Sheet sheet = (Sheet) workbook.getSheet(sheetName);
			if (sheet == null) {
				System.out.println("Sheet '" + sheetName + "' not found in the workbook.");
				return; // Terminate the program if the sheet is not found
			}

			Row row = sheet.getRow(rowNumber);

			if (row == null) {
				// If the row doesn't exist, create a new one
				row = sheet.createRow(rowNumber);
			}

			// Get the cell at the specified column number, create it if it doesn't exist
			Cell cell = row.getCell(cellNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			// You can perform any modification to the existing data here
			String updatedValue = modifyData("B110000190008834");

			cell.setCellValue(updatedValue);

			// Save the changes to a new file on your local system
			String editedFilePath = "C:\\Users\\a828958\\Downloads\\driver-Template-valid.xlsx";
			fos = new FileOutputStream(editedFilePath);
			workbook.write(fos);
			System.out.println("Changes saved successfully.");

			// Find the "Browse" button and click it to open the file upload dialog
			WebElement browseButton = d.findElement(By.xpath("(//*[@color='primary'])[2]"));
			browseButton.click();

			// Use Robot class to handle the file upload dialog
			Robot robot = new Robot();
			robot.delay(2000);

			StringSelection ss = new StringSelection(editedFilePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			robot.keyPress(KeyEvent.VK_CONTROL); // Press on the control key
			robot.keyPress(KeyEvent.VK_V); // Press on V key
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// Wait for the upload to complete (adjust the wait time as needed)
			Thread.sleep(5000);

			// Find and click the "Upload" button
			WebElement uploadButton = d.findElement(By.xpath("(//*[@color='primary'])[3]"));
			uploadButton.click();

			// Wait for the validation message to appear
			Thread.sleep(2000);
			WebElement importdetails = d.findElement(By.xpath("(//*[@fxlayout='row'])[7]"));
			boolean Importdetails = importdetails.isDisplayed();
			Assert.assertTrue(Importdetails, "import driver details is not uploaded");

			Thread.sleep(10000);
			WebElement verifyrecord = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/mat-card[3]/p-table/div/div[2]/table/tbody/tr[1]"));
			boolean Verifyrecord = verifyrecord.isDisplayed();
			Assert.assertTrue(Verifyrecord, "Driverslandingpage is not displayed");
			System.out.println(verifyrecord.getText());

			Thread.sleep(10000);
			d.findElement(By.xpath("(//*[@role='img'])[60]")).click();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources in the finally block
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	private static String modifyData(String oldValue) {
		// modify the  old value and return the modified value
		return oldValue;
	}


	@Test(priority=5)
	public void TC_008() throws InterruptedException, AWTException, IOException {
		//again edit and upload the file with invalid details
		String filePath = "C:\\Users\\a828958\\Downloads\\driver-Template-valid.xlsx";
		String sheetName = "Driver Template";
		int rowNumber = 1;
		int cellNumber = 1; // The first column (0-indexed) of the Excel file

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);

			// Check if the sheet with the given name exists in the workbook
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				System.out.println("Sheet '" + sheetName + "' not found in the workbook.");
				return; // Terminate the program if the sheet is not found
			}

			Row row = sheet.getRow(rowNumber);

			if (row == null) {
				// If the row doesn't exist, create a new one
				row = sheet.createRow(rowNumber);
			}

			// Get the cell at the specified column number, create it if it doesn't exist
			Cell cell = row.getCell(cellNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			// You can perform any modification to the existing data here
			String updatedValue = modifyData("B11000019666883410000");

			cell.setCellValue(updatedValue);

			// Save the changes to a new file on your local system
			String editedFilePath = "C:\\Users\\a828958\\Downloads\\driver-Template-invalid.xlsx";
			fos = new FileOutputStream(editedFilePath);
			workbook.write(fos);
			System.out.println("Changes saved successfully.");

			// Find the "Browse" button and click it to open the file upload dialog
			WebElement browseButton = d.findElement(By.xpath("(//*[@color='primary'])[2]"));
			browseButton.click();

			// Use Robot class to handle the file upload dialog
			Robot robot = new Robot();
			robot.delay(2000);

			StringSelection ss = new StringSelection(editedFilePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			robot.keyPress(KeyEvent.VK_CONTROL); // Press on the control key
			robot.keyPress(KeyEvent.VK_V); // Press on V key
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// Wait for the upload to complete (adjust the wait time as needed)
			Thread.sleep(5000);

			// Find and click the "Upload" button
			WebElement uploadButton = d.findElement(By.xpath("(//*[@color='primary'])[3]"));
			uploadButton.click();

			WebElement invalid=d.findElement(By.xpath("//*[@style='border: 1px solid #d0d0d0;']"));
			boolean Invalid = invalid.isDisplayed();
			Assert.assertTrue(Invalid, "invalid record is not uploaded");
			Thread.sleep(5000);
			File src1=invalid.getScreenshotAs(OutputType.FILE);
			File trg1=new File(".\\Driver_Management\\TC_DM_008\\invalid.png");
			FileUtils.copyFile(src1,trg1);

			//Rejected Driver Details 
			//Thread.sleep(5000);
			d.findElement(By.xpath("//span[contains(text(),'1 records')]")).click();
			Thread.sleep(5000);
			WebElement RejectedDriverDetails  =d.findElement(By.xpath("//*[@role='dialog']"));
			boolean rejectedDriverDetails = RejectedDriverDetails.isDisplayed();
			Assert.assertTrue(rejectedDriverDetails, "RejectedDriverDetails record is not displayed");
			Thread.sleep(5000);		
			File src2=invalid.getScreenshotAs(OutputType.FILE);
			File trg2=new File(".\\Driver_Management\\TC_DM_008\\RejectedDriverDetails.png");
			FileUtils.copyFile(src2,trg2);

			Thread.sleep(10000);
			d.findElement(By.xpath("(//*[@role='img'])[79]")).click();
			d.findElement(By.xpath("(//*[@role='img'])[60]")).click();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the resources in the finally block
			if (fos != null) {
				fos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	@SuppressWarnings("unused")
	private static String modifyData1(String oldValue) {
		// modify the  old value and return the modified value
		return oldValue;
	} 

	@Test(priority=5)
	public void invalidfiletestcase() throws InterruptedException, AWTException, IOException {
		//upload a file that is not related to driver management
		Thread.sleep(15000);
		WebElement browse=d.findElement(By.xpath("(//*[@color='primary'])[2]"));
		JavascriptExecutor js=(JavascriptExecutor)d; 
		js.executeScript("arguments[0].click()", browse);  //click action on the button
		Robot rb=new Robot();
		rb.delay(10000);  
		StringSelection ss=new StringSelection("C:\\Users\\a828958\\OneDrive - Atos\\Desktop\\DAF Project\\Regression Plan\\Regression Base Plan_24th July.xlsx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
		rb.keyPress(KeyEvent.VK_CONTROL); //press on control key
		rb.keyPress(KeyEvent.VK_V); //press on V key
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		d.findElement(By.xpath("(//*[@color='primary'])[3]")).click();
		Thread.sleep(2000);
		WebElement invalidfile=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div"));
		boolean Invalidfile = invalidfile.isDisplayed();
		Assert.assertTrue(Invalidfile, "fail");
		Thread.sleep(5000);
		File src=invalidfile.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\invalidfiletestcase\\invalidfile.png");
		FileUtils.copyFile(src,trg);

		//Rejected Driver Details 
		//Thread.sleep(5000);
		d.findElement(By.xpath("//span[contains(text(),'1 record')]")).click();
		Thread.sleep(5000);
		WebElement RejectedDriverDetails  =d.findElement(By.xpath("//*[@role='dialog']"));
		boolean rejectedDriverDetails = RejectedDriverDetails.isDisplayed();
		Assert.assertTrue(rejectedDriverDetails, "RejectedDriverDetails record is not displayed");
		Thread.sleep(5000);		
		File src2=RejectedDriverDetails.getScreenshotAs(OutputType.FILE);
		File trg2=new File(".\\Driver_Management\\invalidfiletestcase\\RejectedDriverDetails.png");
		FileUtils.copyFile(src2,trg2);
		
		Thread.sleep(10000);
			d.findElement(By.xpath("(//*[@role='img'])[79]")).click();
			d.findElement(By.xpath("(//*[@role='img'])[60]")).click();
	}

	@Test(priority=6)
	public void TC_010() throws InterruptedException, IOException {
		//verify dropdown
		d.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='button'])[1]")).click();
		WebElement all=d.findElement(By.xpath("(//*[@role='option'])[1]"));
		WebElement optin =d.findElement(By.xpath("(//*[@role='option'])[2]"));
		WebElement optout =d.findElement(By.xpath("(//*[@role='option'])[3]"));
		boolean dropdown = all.isDisplayed() && optin.isDisplayed() && optout.isDisplayed();
		Assert.assertTrue(dropdown, "dropdown is not displayed");

		d.findElement(By.xpath("(//*[@role='option'])[1]")).click();

		// Verify Bulk Consent buttons available
		WebElement resetButton = d.findElement(By.xpath("(//*[@color='primary'])[4]"));
		WebElement optInButton = d.findElement(By.xpath("(//*[@color='primary'])[5]"));
		WebElement optOutButton = d.findElement(By.xpath("(//*[@color='primary'])[6]"));
		boolean BulkConsentbuttons = resetButton.isDisplayed() && optInButton.isDisplayed() && optOutButton.isDisplayed();
		Assert.assertTrue(BulkConsentbuttons, "BulkConsentbuttons is not displayed");

		//check the functionality of reset-all
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		//reset-all driver consent model is displayed or not
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean resetDriverConsentModal = driverConsentModal.isDisplayed();
		Assert.assertTrue(resetDriverConsentModal, "resetDriverConsentModal box is not displayed");
		File src=driverConsentModal.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_010\\ResetAll.png");
		FileUtils.copyFile(src,trg); 

		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();   

		//check the functionality of optin-all
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		//optin-all driver consent model is displayed or not
		WebElement driverConsentModal1 = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean optinDriverConsentModal = driverConsentModal1.isDisplayed();
		Assert.assertTrue(optinDriverConsentModal, "optinDriverConsentModal box is not displayed");
		File src1=driverConsentModal1.getScreenshotAs(OutputType.FILE);
		File trg1=new File(".\\Driver_Management\\TC_DM_010\\OptInAll.png");
		FileUtils.copyFile(src1,trg1); 

		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();   

		//check the functionality of optout-all
		d.findElement(By.xpath("(//*[@color='primary'])[6]")).click();
		//optout-all driver consent model is displayed or not
		WebElement driverConsentModal2 = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean optoutDriverConsentModal = driverConsentModal2.isDisplayed();
		Assert.assertTrue(optoutDriverConsentModal, "optoutDriverConsentModal box is not displayed");
		File src2=driverConsentModal2.getScreenshotAs(OutputType.FILE);
		File trg2=new File(".\\Driver_Management\\TC_DM_010\\OptOutAll.png");
		FileUtils.copyFile(src2,trg2);

		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();   

		//verify driver id,name,email data
		WebElement table1 = d.findElement(By.xpath("//*[@role='grid']"));
		List<WebElement> headers = table1.findElements(By.xpath(".//th"));
		List<WebElement> rows = table1.findElements(By.xpath(".//tr"));
		for (int i = 0; i < headers.size(); i++) {
			// Get the column header
			String columnHeader = headers.get(i).getText();
			System.out.print(columnHeader + "\t");
			// Get the data in the first row of the column
			WebElement columnData = rows.get(1).findElements(By.xpath(".//td")).get(i);
			String columnText = columnData.getText();
			System.out.println(columnText);
		}

		//optin
		d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]")).click();
		//click optout
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[3]/label/span[2]")).click();
		String optoutmode = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span")).getText();
		boolean Optoutmode = optoutmode.contains("'DAF Connect'= Opt-Out");
		Assert.assertTrue(Optoutmode, "optoutmode is not displayed");

		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();          //cancel

		//optout
		WebElement search1=d.findElement(By.xpath("//*[@placeholder='Search']"));	
		search1.sendKeys("akmal");
		d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[3]")).click();
		//click optin
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[2]/label/span[2]")).click();
		String optinmode = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span")).getText();
		boolean Optinmode = optinmode.contains("'DAF Connect'= Opt-In");
		Assert.assertTrue(Optinmode, "optinmode is not displayed");

		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();          //cancel

		Actions actions = new Actions(d);
		actions.moveToElement(search1).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();

		//hovering on view,edit,delete icons
		Actions act=new Actions(d);
		Thread.sleep(5000);
		WebElement Viewhover=d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[4]"));
		Thread.sleep(5000);
		WebElement Edithover=d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[5]"));
		Thread.sleep(5000);
		WebElement Deletehover=d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[6]"));
		boolean hovering = Viewhover.isDisplayed() && Edithover.isDisplayed() && Deletehover.isDisplayed();
		Assert.assertTrue(hovering, "tooltips is not displayed");
		act.moveToElement(Viewhover).build().perform();
		act.moveToElement(Edithover).build().perform();
		act.moveToElement(Deletehover).build().perform();
		System.out.println("tooltips are displayed");

		//table
		Thread.sleep(5000);
		WebElement table = d.findElement(By.xpath("//*[@role='grid']"));
		//Driver ID
		WebElement DriverIDHeader = d.findElement(By.xpath("(//*[@role='columnheader'])[1]"));
		DriverIDHeader.click();
		List<WebElement> rows1 = table.findElements(By.xpath("//td[contains(@class,'feature-name')]"));
		List<String> DriverIDs = new ArrayList<String>();
		for (WebElement row : rows1) 
		{
			String Driverid = row.getText();
			DriverIDs.add(Driverid);
		}
		boolean isAscendingSorted = Ordering.natural().isOrdered(DriverIDs);
		DriverIDHeader.click();
		boolean isDescendingSorted = Ordering.natural().reverse().isOrdered(DriverIDs);
		if (isAscendingSorted && isDescendingSorted) {
			System.out.println("Sorting on ' DriverID' column is correct ascending and descending order."); 
		} 

		//Driver Name
		WebElement DriverNameHeader = d.findElement(By.xpath("(//*[@role='columnheader'])[2]"));
		DriverNameHeader.click();
		List<WebElement> rows2 = table.findElements(By.xpath("//td[contains(@class,'feature-name')]"));
		List<String> DriverNames = new ArrayList<String>();
		for (WebElement row : rows2) 
		{
			String Drivername = row.getText();
			DriverNames.add(Drivername);
		}
		boolean AscendingSorted = Ordering.natural().isOrdered(DriverNames);
		DriverNameHeader.click();
		boolean DescendingSorted = Ordering.natural().reverse().isOrdered(DriverNames);
		if (AscendingSorted && DescendingSorted) {
			System.out.println("Sorting on ' DriverName' column is correct ascending and descending order."); 
		} 

		//Email ID
		WebElement EmailIDHeader = d.findElement(By.xpath("(//*[@role='columnheader'])[3]"));
		EmailIDHeader.click();
		List<WebElement> rows3 = table.findElements(By.xpath("//td[contains(@class,'feature-name')]"));
		List<String> EmailIDs = new ArrayList<String>();
		for (WebElement row : rows3) 
		{
			String EmailID = row.getText();
			EmailIDs.add(EmailID);
		}
		boolean isAscendingSort = Ordering.natural().isOrdered(EmailIDs);
		EmailIDHeader.click();
		boolean isDescendingSort = Ordering.natural().reverse().isOrdered(EmailIDs);
		if (isAscendingSort && isDescendingSort) {
			System.out.println("Sorting on ' EmailID' column is correct ascending and descending order."); 
		}
	}


	@Test(priority=7)
	public void TC_006() throws InterruptedException, IOException {
		//click reset all button
		Thread.sleep(10000);
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean DriverConsentModal = driverConsentModal.isDisplayed();
		Assert.assertTrue(DriverConsentModal, "driverConsentModal is not displayed");

		//verify all fields in driver consent model
		WebElement ModalTitle = d.findElement(By.xpath("(//*[@fxlayout='row'])[8]"));
		WebElement TotalDrivers = d.findElement(By.xpath("(//*[@fxlayout='column'])[14]"));
		WebElement CurrentConsentStatus = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement Radiobutton = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement disclaimerMessage = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content"));
		boolean verifyfields = ModalTitle.isDisplayed() && TotalDrivers.isDisplayed() && CurrentConsentStatus.isDisplayed() &&
				Radiobutton.getText().contains("Inherit") && disclaimerMessage.isDisplayed();
		Assert.assertTrue(verifyfields, "all fields are not available on Driver consent model");
		System.out.println(ModalTitle.getText());
		System.out.println(TotalDrivers.getText());
		System.out.println(CurrentConsentStatus.getText());
		System.out.println("inherit button is selected");
		File src=disclaimerMessage.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_006\\inheritdisclaimerMessage.png");
		FileUtils.copyFile(src,trg);

		//cancel
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverslandingpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Driverslandingpg =driverslandingpg.isDisplayed();
		Assert.assertTrue(Driverslandingpg, "driverslandingpg is not displayed");

		//click reset all button
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();

		//optin click
		d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[2]/div/mat-radio-group/mat-radio-button[2]/label/span[2]")).click();

		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		Thread.sleep(7000);
		WebElement Updatemsg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/div/div[1]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=8)
	public void TC_011() throws InterruptedException, IOException {
		//click opt-out all button
		Thread.sleep(10000);
		d.findElement(By.xpath("(//*[@color='primary'])[6]")).click();
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean DriverConsentModal = driverConsentModal.isDisplayed();
		Assert.assertTrue(DriverConsentModal, "driverConsentModal is not displayed");

		//verify all fields in driver consent model
		WebElement ModalTitle = d.findElement(By.xpath("(//*[@fxlayout='row'])[8]"));
		WebElement TotalDrivers = d.findElement(By.xpath("(//*[@fxlayout='column'])[14]"));
		WebElement CurrentConsentStatus = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement Radiobutton = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement disclaimerMessage = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content"));
		boolean verifyfields = ModalTitle.isDisplayed() && TotalDrivers.isDisplayed() && CurrentConsentStatus.isDisplayed() &&
				Radiobutton.getText().contains("Opt-Out All") && disclaimerMessage.isDisplayed();
		Assert.assertTrue(verifyfields, "all fields are not available on Driver consent model");
		System.out.println(ModalTitle.getText());
		System.out.println(TotalDrivers.getText());
		System.out.println(CurrentConsentStatus.getText());
		System.out.println("opt-out all radio button is selected");
		File src=disclaimerMessage.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_011\\optoutdisclaimerMessage.png");
		FileUtils.copyFile(src,trg);

		//cancel
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverslandingpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Driverslandingpg =driverslandingpg.isDisplayed();
		Assert.assertTrue(Driverslandingpg, "driverslandingpg is not displayed");

		//again click opt-out all
		d.findElement(By.xpath("(//*[@color='primary'])[6]")).click();

		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		Thread.sleep(5000);
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=9)
	public void TC_009() throws InterruptedException, IOException {
		//click opt-in all button
		Thread.sleep(10000);
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement driverConsentModal = d.findElement(By.xpath("(//*[@tabindex='-1'])[2]"));
		boolean DriverConsentModal = driverConsentModal.isDisplayed();
		Assert.assertTrue(DriverConsentModal, "driverConsentModal is not displayed");

		//verify all fields in driver consent model
		WebElement ModalTitle = d.findElement(By.xpath("(//*[@fxlayout='row'])[8]"));
		WebElement TotalDrivers = d.findElement(By.xpath("(//*[@fxlayout='column'])[14]"));
		WebElement CurrentConsentStatus = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement Radiobutton = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content/div[2]/div[3]/div[1]/span"));
		WebElement disclaimerMessage = d.findElement(By.xpath("/html/body/div[1]/div[2]/div/mat-dialog-container/app-consent-opt/div/mat-card/mat-card-content"));
		boolean verifyfields = ModalTitle.isDisplayed() && TotalDrivers.isDisplayed() && CurrentConsentStatus.isDisplayed() && 
				Radiobutton.getText().contains("Opt-In All") && disclaimerMessage.isDisplayed();
		Assert.assertTrue(verifyfields, "all fields are not available on Driver consent model");
		System.out.println(ModalTitle.getText());
		System.out.println(TotalDrivers.getText());
		System.out.println(CurrentConsentStatus.getText());
		System.out.println("opt-in all radio button is selected");
		File src=disclaimerMessage.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_009\\optoutdisclaimerMessage.png");
		FileUtils.copyFile(src,trg);

		//cancel
		d.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverslandingpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Driverslandingpg =driverslandingpg.isDisplayed();
		Assert.assertTrue(Driverslandingpg, "driverslandingpg is not displayed");

		//again click opt-in all
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();

		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		Thread.sleep(7000);
		WebElement Updatemsg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div/div/div[1]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=10)
	public void TC_012() throws InterruptedException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();

		//need to select inherit
		d.findElement(By.xpath("(//*[@value='H'])[1]")).click();
		//click status ok
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=11)
	public void TC_013() throws IOException, InterruptedException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		//d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		Thread.sleep(5000);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		//check the elements are available on Edit Driver details page
		WebElement editpg=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div"));
		boolean Editpg =editpg.isDisplayed();
		Assert.assertTrue(Editpg, "Edit page is not open");
		File src=editpg.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_013\\editpg.png");
		FileUtils.copyFile(src,trg);

		// Check the driver ID field
		WebElement driverId = d.findElement(By.xpath("//*[@placeholder='Driver ID']"));
		boolean DriverId =driverId.isDisplayed();
		Assert.assertTrue(DriverId, "Driver ID field is not available");

		// Check the Email ID field
		WebElement emailId = d.findElement(By.xpath("//*[@placeholder='Email']"));
		if (emailId.isDisplayed()) {
			System.out.println("Email ID field is available");
			String emailValue = emailId.getAttribute("value");
			if (emailValue.isEmpty()) {
				System.out.println("Email ID field is empty");
			} 
		}

		// Check the FirstName field
		WebElement FirstName = d.findElement(By.xpath("//*[@placeholder='First Name']"));
		if (FirstName.isDisplayed()) {
			System.out.println("FirstName field is available");
			String FirstNameValue = FirstName.getAttribute("value");
			if (FirstNameValue.isEmpty()) {
				System.out.println("FirstName field is empty");
			} 
		}


		// Check the LastName field
		WebElement LastName = d.findElement(By.xpath("//*[@placeholder='Last Name']"));
		if (LastName.isDisplayed()) {
			System.out.println("LastName field is available");
			String LastNameValue = LastName.getAttribute("value");
			if (LastNameValue.isEmpty()) {
				System.out.println("LastName field is empty");
			} 
		}

		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@value='I'])[1]")).click();
		//click status ok
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=12)
	public void TC_015() throws IOException, InterruptedException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();

		//check the elements are available on Edit Driver details page
		WebElement editpg=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div"));
		boolean Editpg =editpg.isDisplayed();
		Assert.assertTrue(Editpg, "Edit page is not open");
		File src=editpg.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_015\\editpg.png");
		FileUtils.copyFile(src,trg); 

		// Update firstname, lastname, and emailid
		WebElement emailid = d.findElement(By.xpath("//*[@placeholder='Email']"));
		emailid.clear();
		emailid.sendKeys("navya123@gmail.com");
		WebElement firstname = d.findElement(By.xpath("//*[@placeholder='First Name']"));
		firstname.clear();
		firstname.sendKeys("navya");
		WebElement lastname = d.findElement(By.xpath("//*[@placeholder='Last Name']"));
		lastname.clear();
		lastname.sendKeys("k");

		// Click Reset Button
		d.findElement(By.xpath("(//*[@color='primary'])[3]")).click();
		WebElement resetpg = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Resetpg =resetpg.isDisplayed();
		Assert.assertTrue(Resetpg, "Edit page is not open");
		File src1=resetpg.getScreenshotAs(OutputType.FILE);
		File trg1=new File(".\\Driver_Management\\TC_DM_015\\resetpg.png");
		FileUtils.copyFile(src1,trg1);

		d.findElement(By.xpath("(//*[@fxlayout='column'])[4]")).click();
	}

	@Test(priority=13)
	public void TC_019() throws InterruptedException, IOException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();

		//need to select opt-out
		d.findElement(By.xpath("(//*[@value='U'])[1]")).click();
		//click status ok
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=14)
	public void TC_016() throws InterruptedException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();

		//need to select opt-in
		d.findElement(By.xpath("(//*[@value='I'])[1]")).click();
		//click status ok
		d.findElement(By.xpath("(//*[@color='primary'])[5]")).click();
		//click confirm
		d.findElement(By.xpath("(//*[@color='primary'])[4]")).click();
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=15)
	public void TC_018() throws InterruptedException, IOException {
		//click edit icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("akmal");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();

		//check the elements are available on Edit Driver details page
		WebElement editpg=d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div/div"));
		boolean Editpg =editpg.isDisplayed();
		Assert.assertTrue(Editpg, "Edit page is not open");
		File src=editpg.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\TC_DM_018\\editpg.png");
		FileUtils.copyFile(src,trg);

		// Update firstname, lastname, and emailid
		WebElement emailid = d.findElement(By.xpath("//*[@placeholder='Email']"));
		emailid.clear();
		emailid.sendKeys("navya123@gmail.com");
		WebElement firstname = d.findElement(By.xpath("//*[@placeholder='First Name']"));
		firstname.clear();
		firstname.sendKeys("navya");
		WebElement lastname = d.findElement(By.xpath("//*[@placeholder='Last Name']"));
		lastname.clear();
		lastname.sendKeys("k");

		// Click cancel Button
		d.findElement(By.xpath("(//*[@color='primary'])[2]")).click();
		WebElement mainpg = d.findElement(By.xpath("/html/body/app-root/div"));
		boolean Mainpg =mainpg.isDisplayed();
		Assert.assertTrue(Mainpg, "Edit page is not open");
		File src2=mainpg.getScreenshotAs(OutputType.FILE);
		File trg2=new File(".\\Driver_Management\\TC_DM_018\\mainpg.png");
		FileUtils.copyFile(src2,trg2);
	}

	@Test(priority=16)
	public void TC_020() throws InterruptedException, IOException {
		//click delete icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("//*[@placeholder='Search']")).sendKeys("B110000190008834");
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@tooltipposition='bottom'])[6]")).click();
		WebElement dialogbox = d.findElement(By.xpath("//*[@role='dialog']"));
		boolean Dialogbox =dialogbox.isDisplayed();
		Assert.assertTrue(Dialogbox, "Delete confirmation dialog box is not displayed");

		//click delete
		d.findElement(By.xpath("(//*[@color='primary'])[8]")).click();
		WebElement Updatemsg = d.findElement(By.xpath("(//*[@fxlayout='column'])[4]"));
		boolean updatemsg =Updatemsg.isDisplayed();
		Assert.assertTrue(updatemsg, "not updated");
		System.out.println(Updatemsg.getText());
	}

	@Test(priority=17)
	public void TC_021() throws InterruptedException, IOException {
		//click delete icon for that respective driver record
		Thread.sleep(10000);
		d.findElement(By.xpath("(//*[@role='img'])[65]")).click();
		Thread.sleep(5000);
		WebElement dialogbox = d.findElement(By.xpath("//*[@role='dialog']"));
		boolean Dialogbox =dialogbox.isDisplayed();
		Assert.assertTrue(Dialogbox, "Delete confirmation dialog box is not displayed");

		//click cancel
		d.findElement(By.xpath("(//*[@color='primary'])[7]")).click();
		WebElement driverpage = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean Driverpage =driverpage.isDisplayed();
		Assert.assertTrue(Driverpage, "Main page is not displayed");
	}

	@Test(priority=18)
	public void TC_022() throws InterruptedException, IOException {
		//click view icon for that respective driver record
		Thread.sleep(5000);
		d.findElement(By.xpath("(//*[@role='img'])[63]")).click();
		//click back
		d.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		d.findElement(By.xpath("(//*[@fxlayout='column'])[4]")).click();

		//click edit icon for that respective driver record
		d.findElement(By.xpath("(//*[@role='img'])[64]")).click();
		//click back
		d.findElement(By.xpath("(//*[@fxlayout='column'])[4]")).click();
		//verification
		WebElement DriverManagementPage = d.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-configuration/app-driver-management/div"));
		boolean driverManagementPage =DriverManagementPage.isDisplayed();
		Assert.assertTrue(driverManagementPage, "Back Functionality is not working");
	}

	@Test(priority=19)
	public void paginationView5() throws IOException, InterruptedException {
		//select 5
		//counting the rows in table
		WebElement table = d.findElement(By.xpath("//*[@role='grid']"));
		List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		int rowCount = rows.size();
		System.out.println("Number of rows in the table: " + rowCount);
		//Taking table screenshot
		WebElement count = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src=count.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\paginationAndNavigation\\select100.png");
		FileUtils.copyFile(src,trg); 
		//verifying navigation functionality
		Thread.sleep(5000);
		d.findElement(By.xpath("//button [@class='p-paginator-next p-paginator-element p-link p-ripple']")).click();
		WebElement navigationmsg=d.findElement(By.xpath("//span [@class='p-paginator-current ng-star-inserted']"));
		boolean verify=navigationmsg.getText().contains("6 - 10");
		Assert.assertTrue(verify, "page is not navigated to next page");
		d.findElement(By.xpath("//button [@class='p-paginator-prev p-paginator-element p-link p-ripple']")).click();
		boolean verify1=navigationmsg.getText().contains("1 - 5");
		Assert.assertTrue(verify1, "page is not navigated to previous page");
	}

	@Test(priority=20)
	public void paginationView10() throws IOException, InterruptedException {
		//select 10
		Thread.sleep(10000);
		d.findElement(By.xpath("(//*[@role='button'])[3]")).click();
		d.findElement(By.xpath("(//*[@role='option'])[2]")).click();
		//counting the rows in table
		WebElement table = d.findElement(By.xpath("//*[@role='grid']"));
		List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		int rowCount = rows.size();
		System.out.println("Number of rows in the table: " + rowCount);
		//Taking table screenshot
		WebElement count = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src=count.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\paginationAndNavigation\\select10.png");
		FileUtils.copyFile(src,trg); 
		//verifying navigation functionality
		Thread.sleep(5000);
		d.findElement(By.xpath("//button [@class='p-paginator-next p-paginator-element p-link p-ripple']")).click();
		WebElement navigationmsg=d.findElement(By.xpath("//span [@class='p-paginator-current ng-star-inserted']"));
		boolean verify=navigationmsg.getText().contains("11 - 20");
		Assert.assertTrue(verify, "page is not navigated to next page");
		d.findElement(By.xpath("//button [@class='p-paginator-prev p-paginator-element p-link p-ripple']")).click();
		boolean verify1=navigationmsg.getText().contains("1 - 10");
		Assert.assertTrue(verify1, "page is not navigated to previous page");
	}

	@Test(priority=21)
	public void paginationView25() throws IOException, InterruptedException {
		//select 25
		d.findElement(By.xpath("(//*[@role='button'])[3]")).click();
		d.findElement(By.xpath("(//*[@role='option'])[3]")).click();
		//counting the rows in table
		WebElement table = d.findElement(By.xpath("//*[@role='grid']"));
		List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		int rowCount = rows.size();
		System.out.println("Number of rows in the table: " + rowCount);
		//Taking table screenshot
		WebElement count = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src=count.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\paginationAndNavigation\\select25.png");
		FileUtils.copyFile(src,trg); 
		//verifying navigation functionality
		Thread.sleep(5000);
		d.findElement(By.xpath("//button [@class='p-paginator-next p-paginator-element p-link p-ripple']")).click();
		WebElement navigationmsg=d.findElement(By.xpath("//span [@class='p-paginator-current ng-star-inserted']"));
		boolean verify=navigationmsg.getText().contains("26 - 50");
		Assert.assertTrue(verify, "page is not navigated to next page");
		d.findElement(By.xpath("//button [@class='p-paginator-prev p-paginator-element p-link p-ripple']")).click();
		boolean verify1=navigationmsg.getText().contains("1 - 25");
		Assert.assertTrue(verify1, "page is not navigated to previous page");
	}

	@Test(priority=22)
	public void paginationView100() throws IOException, InterruptedException {
		//select 100
		Thread.sleep(5000);
		d.findElement(By.xpath("(//*[@role='button'])[3]")).click();
		Thread.sleep(5000);
		d.findElement(By.xpath("(//*[@role='option'])[4]")).click();
		//counting the rows in table
		WebElement table = d.findElement(By.xpath("//*[@role='grid']"));
		List<WebElement> rows = table.findElements(By.xpath(".//tr"));
		int rowCount = rows.size();
		System.out.println("Number of rows in the table: " + rowCount);
		//Taking table screenshot
		WebElement count = d.findElement(By.xpath("//*[@class='mat-card mat-focus-indicator mat-elevation-z ng-star-inserted']"));
		File src=count.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\Driver_Management\\paginationAndNavigation\\select100.png");
		FileUtils.copyFile(src,trg); 
		//verifying navigation functionality
		Thread.sleep(5000);
		d.findElement(By.xpath("//button [@class='p-paginator-next p-paginator-element p-link p-ripple']")).click();
		WebElement navigationmsg=d.findElement(By.xpath("//span [@class='p-paginator-current ng-star-inserted']"));
		boolean verify=navigationmsg.getText().contains("101");
		Assert.assertTrue(verify, "page is not navigated to next page");
		d.findElement(By.xpath("//button [@class='p-paginator-prev p-paginator-element p-link p-ripple']")).click();
		boolean verify1=navigationmsg.getText().contains("1 - 100");
		Assert.assertTrue(verify1, "page is not navigated to previous page");
	} 
}
