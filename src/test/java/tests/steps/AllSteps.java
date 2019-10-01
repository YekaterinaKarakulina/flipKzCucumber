package tests.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import driver.WebDriverManager;
import runner.Runner;
import service.FileReaderJsonAndProperties;
import tests.businessObjects.Book;
import tests.businessObjects.User;
import tests.pages.HomePage;
import tests.pages.ItemPage;
import tests.pages.SearchCriteria;
import tests.pages.SectionPage;

import java.util.List;

public class AllSteps {

    private WebDriver driver;
    private HomePage homePage;
    private SearchCriteria searchCriteria;
    private ItemPage bookItemPage;
    private SectionPage bookPage;
    private User user;
    private List<String> selectedAuthors;
    private List<String> actualAuthors;
    private String bookName;
    private int publicationYearRangeFirstValue = FileReaderJsonAndProperties.getPublicationYearRangeFirstValue();
    private int publicationYearRangeLastValue = FileReaderJsonAndProperties.getPublicationYearRangeLastValue();
    private int actualPublicationYear;

    @Given("^Website flip\\.kz is opened$")
    public void websiteFlipKzIsOpened() {
        user = Runner.getUser();
        driver = Runner.getDriver();
        homePage = new HomePage(driver).open();
    }

    @When("^User does login$")
    public void userDoesLogin() {
        homePage = homePage.signIn(user);
    }

    @Given("^User is authorized$")
    public void userIsAuthorized() {
        Assert.assertEquals(String.format("SignIn error. Expected user name - %s, actual - %s.", user.getName(), homePage.getActualUserName()), homePage.getActualUserName(), user.getName());
    }

    @When("^User navigates to imaginative literature section$")
    public void userNavigatesToImaginativeLiteratureSection() {
        searchCriteria = homePage.getMainMenuComponent().clickBookSection().clickImaginativeLiteratureSection();
    }

    @And("^User selects random author$")
    public void userSelectsRandomAuthor() {
        bookPage = searchCriteria.clickRandomAuthor(1);
    }

    @And("^User selects random authors$")
    public void userSelectsRandomAuthors() {
        bookPage = searchCriteria.clickRandomAuthor(3);
    }


    @And("^User selects (\\d+) authors$")
    public void userSelectsAuthors(int amountOfAuthors) {
        bookPage = searchCriteria.clickRandomAuthor(amountOfAuthors);
    }

    @And("^User set publication year first value$")
    public void userSetPublicationYearFirstValue() {
        bookPage = searchCriteria.setPublicationYearFirstValue();
        publicationYearRangeFirstValue = bookPage.getSearchCriteria().getExpectedPublicationYearFirstValue();
    }

    @And("^User set publication year last value$")
    public void userSetPublicationYearLastValue() {
        bookPage = searchCriteria.setPublicationYearLastValue();
        publicationYearRangeLastValue = bookPage.getSearchCriteria().getExpectedPublicationYearLastValue();
    }

    @And("^User set publication year range$")
    public void userSetPublicationYearRange() {
        bookPage = searchCriteria.setPublicationYearFilter(Integer.toString(publicationYearRangeFirstValue), Integer.toString(publicationYearRangeLastValue));
    }

    @And("^User moves to random result`s page, selects random book$")
    public void userMovesToRandomResultSPageSelectsRandomBook() {
        selectedAuthors = searchCriteria.getSelectedAuthorsList();
        bookItemPage = bookPage.moveToRandomPage().clickOnRandomBookCard();
    }

    @Then("^Random book`s author from result page is selected author$")
    public void randomBookSAuthorFromResultPageIsSelectedAuthor() {
        Book actualBook = new Book(bookItemPage.getBookAuthors());
        Book expectedBook = new Book(selectedAuthors);
        Assert.assertTrue(actualBook.checkBooksEqualsByAuthorsList(expectedBook, actualBook));
    }

    @Then("^Random book`s author from result page is one of the selected authors$")
    public void randomBookSAuthorFromResultPageIsOneOfTheSelectedAuthors() {
        actualAuthors = bookItemPage.getBookAuthors();
        Assert.assertTrue(actualAuthors.stream().anyMatch(selectedAuthors::contains));
    }

    @Then("^Random book`s publication year from result page is greater or equal then user entered$")
    public void randomBookSPublicationYearFromResultPageIsGreaterOrEqualThenUserEntered() {
        actualPublicationYear = bookItemPage.getBookPublicationYear();
        bookName = bookItemPage.getBookName();
        Assert.assertTrue(String.format("Actual publication year of book + `%s` - %s. Expected year of publication %s.", bookName, Integer.toString(actualPublicationYear), Integer.toString(publicationYearRangeFirstValue)), actualPublicationYear >= publicationYearRangeFirstValue);
    }

    @Then("^Random book`s publication year from result page is lower or equal then user entered$")
    public void randomBookSPublicationYearFromResultPageIsLowerOrEqualThenUserEntered() {
        actualPublicationYear = bookItemPage.getBookPublicationYear();
        bookName = bookItemPage.getBookName();
        Assert.assertTrue(String.format("Actual publication year of book `%s` - %s. Expected year of publication %s.", bookName, Integer.toString(actualPublicationYear), Integer.toString(publicationYearRangeLastValue)), actualPublicationYear <= publicationYearRangeLastValue);
    }

    @Then("^Random book`s publication year from result page is on range user entered$")
    public void randomBookSPublicationYearFromResultPageIsOnRangeUserEntered() {
        actualPublicationYear = bookItemPage.getBookPublicationYear();
        bookName = bookItemPage.getBookName();
        Assert.assertTrue(String.format("Actual publication year of book `%s` - %s. Expected range of publication %s.", bookName, Integer.toString(actualPublicationYear), Integer.toString(publicationYearRangeFirstValue), Integer.toString(publicationYearRangeLastValue)), publicationYearRangeFirstValue <= actualPublicationYear && actualPublicationYear <= publicationYearRangeLastValue);
    }

    @And("^User set \"(\\d+)\" and \"(\\d+)\" range values$")
    public void userSetFromAndToRangeValues(int from, int to) throws Throwable {
        bookPage = searchCriteria.setPublicationYearFilter(Integer.toString(from), Integer.toString(to));
    }

    @And("User set (-?\\d+) and (-?\\d+) range values")
    public void userSetFromAndToRangeValues(String from, String to) {
        bookPage = searchCriteria.setPublicationYearFilter(from, to);
        publicationYearRangeFirstValue = Integer.parseInt(from);
        publicationYearRangeLastValue = Integer.parseInt(to);
    }

}
