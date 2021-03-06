package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getEditProfileBtn() {
		return this.driver.findElement(By.xpath("//*[@id='body']/div/div/div/div[2]/div/div/div[2]/a[2]"));
	}

	public WebElement getFNameInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_first_name']"));
	}

	public WebElement getLNameInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_last_name']"));
	}

	public WebElement getAddressInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_address']"));
	}

	public WebElement getPhoneInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_phone']"));
	}

	public WebElement getZipCodeInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_zip']"));
	}

	public Select getCountrySelect() {
		Select country = new Select(this.driver.findElement(By.xpath("//select[@id='user_country_id']")));
		return country;
	}

	public Select getStateSelect() {
		Select state = new Select(this.driver.findElement(By.xpath("//select[@id='user_state_id']")));
		return state;
	}

	public Select getCitySelect() {
		Select city = new Select(this.driver.findElement(By.xpath("//select[@id='user_city']")));
		return city;
	}

	public WebElement getSubmitBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}

	public WebElement getPhotoUpload() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a"));
	}
	
	public WebElement getPhotoInput() {
		return this.driver.findElement(By.xpath("//input[@type='file']"));
	}

	public void uploadProfilePhoto(String pathToFile) throws Exception {
		js.executeScript("arguments[0].click()", getPhotoUpload());
		String imgPath = new File(pathToFile).getCanonicalPath();
		getPhotoInput().sendKeys(imgPath);
	}

	public WebElement getRemovePhotoBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]/i"));
	}

	public void removeProfilePhoto() {
		js.executeScript("arguments[0].click()", getRemovePhotoBtn());
	}

	public void updateProfileInfo(String fname, String lname, String address, String phone, String zipcode,
			String country, String state, String city) throws Exception {

		getFNameInput().clear();
		getFNameInput().sendKeys(fname);

		getLNameInput().clear();
		getLNameInput().sendKeys(lname);

		getAddressInput().clear();
		getAddressInput().sendKeys(address);

		getPhoneInput().clear();
		getPhoneInput().sendKeys(phone);

		getZipCodeInput().clear();
		getZipCodeInput().sendKeys(zipcode);

		getCountrySelect().selectByVisibleText(country);
		
		//waiting for the list of states to be loaded
		Thread.sleep(2000);
		getStateSelect().selectByVisibleText(state);
		
		//waiting for the list of cities to be loaded
		Thread.sleep(2000);
		getCitySelect().selectByVisibleText(city);

		getSubmitBtn().click();
	}
}
