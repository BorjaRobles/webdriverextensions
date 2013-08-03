WebDriver Extension
===================

WebDriver Extension is a framework that extends the WebDriver framework with components that makes it easier to apply the PageObject pattern and Bot Style testing pattern

### Under Development
This project is under development and therefore not recomended to use yet, though the development is in its final stages. Once the [Milestone 1.0](https://github.com/andidev/webdriver-extension/issues?milestone=1&page=1&sort=created&state=open) is released the framework will be fully functional and ready for community feedback.

### Want to Try It?
Add the Sonatype OSS Snapshot Repository
```xml
<repository>
    <id>sonatype-nexus-snapshots</id>
    <name>Sonatype Nexus Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
</repository>
```
...and the Webdriver Extension Snapshot Dependency to your pom.xml
```xml
<dependency>
    <groupId>org.andidev</groupId>
    <artifactId>webdriver-extension</artifactId>
    <version>1.0.M1-SNAPSHOT</version>
</dependency>
```
###Start Using the Bot methods
Just import the static Bot
```java
import static org.andidev.webdriverextension.Bot.*;
```
...and start interacting with your WebElements
```java
type("testuser", username);
type("ai78cGsT", password);
uncheck(keepMeLoggedInCheckbox);
click(loginButton);
```
...and write your asserts
```java
assertTextEquals("testuser", currentUser);
assertTitleEndsWith(" - Wikipedia, the free encyclopedia");
assertUrlMatches("http://[a-z]{2,3}.wikipedia.org/.*");
// ...type assert then bring up the list of all supported asserts with your IDE's autocompletion
```
...and conditional statements
```java
if (hasClass("selected", mainPageTab)) {
    ...do something
}
```
If you won't run your tests in the Webdriver Extensions JUnit Runners make sure you set the thread driver before using the Bot
```java
ThreadDriver.setDriver(yourDriver);
```

###Model Your Components
TOWRITE
```java
public class Interaction extends WebComponent {
    @FindBy(css = "h3 a")
    @Delegate
    private WebElement interactionDelegate;
    
    @FindBy(css = "#n-help a")
    public WebElement help;
    
    @FindBy(css = "#n-aboutsite a")
    public WebElement aboutWikipedia;
    
    @FindBy(css = "#n-portal a")
    public WebElement communityPortal;
    
    @FindBy(css = "#n-recentchanges a")
    public WebElement recentChanges;

    @FindBy(css = "#n-contactpage a")
    public WebElement contactPage;
}
```

###Model Your Pages
TOWRITE
```java
public class MainPage extends WebPage<WikipediaSite> {
    // Search
    @FindBy(css = "input[name='wpName']")
    public WebElement search;

    @FindBy(css = "input[name='wpName']")
    public WebElement searchButton;

    // Side Menu
    @FindBy(css = "input[name='wpName']")
    public WebElement mainPage;
    
    @FindBy(css = "input[name='wpPassword']")
    public WebElement contents;
    
    @FindBy(css = "input[name='wpRemember']")
    public WebElement featuredContent;    
    
    @FindBy(css = "input#wpLoginAttempt")
    public WebElement currentEvents;
    
    @FindBy(css = "input#wpLoginAttempt")
    public WebElement randomArticle;
    
    @FindBy(css = "input#wpLoginAttempt")
    public WebElement donateToWikipedia;
    
    @FindBy(css = "input#wpLoginAttempt")
    public Interaction interaction;
    
    //...and so the story goes on and on

    @Override
    public void open() {
        open("http://en.wikipedia.org/wiki/Main_Page");
        assertIsOpen();
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        assertIsOpen(site);

        // Assert Search is displayed
        assertIsDisplayed(search);
        assertIsDisplayed(searchButton);

        // Assert Site Menu is displayed
        assertIsDisplayed(mainPage);
        assertIsDisplayed(contents);
        assertIsDisplayed(featuredContent);
        assertIsDisplayed(currentEvents);
        assertIsDisplayed(randomArticle);
        assertIsDisplayed(donateToWikipedia);
        assertIsDisplayed(interaction);
        //...and so the story goes on and on
    }

    public void search(String query) {
        clearAndType(query, search);
        click(searchButton);
    }
}
```

###Model Your Site
TOWRITE
```java
public class WikipediaSite extends WebSite {

    // Url
    public static String url = "http://www.wikipedia.org/";

    // WebPages
    public WelcomePage welcomePage;
    public LoginPage loginPage;
    public MainPage mainPage;

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws Error {
        assertCurrentUrlContains(url);
    }

    public void login(String username, String password, boolean keepMeLoggedIn) {
        open(loginPage);
        loginPage.login(username, password, keepMeLoggedIn);
        assertIsOpen(mainPage);
    }

    public void logout() {
        click(mainPage.logoutLink);
    }
}
```

###Create Your Tests
TOWRITE
```java
ThreadDriver.setDriver(yourDriver);
```

### <a href="http://testingbot.com" target="_blank">TestingBot</a> is now supporting this project by giving it a Free account!


## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
