package tests;

import core.Login;
import components.elements.NavigationBar;
import components.elements.PostEditor;
import components.pages.Post;
import infrastructure.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.Login.LoginOption.GOOGLE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.junit.jupiter.api.Assertions.*;


/* could be run with Maven specifying properties like environment and browser, for example:
mvn clean test -Denv="staging" -Ddriver="chrome"

properties defined in system_props.properties, but can be overridden in command line
 */


public class Tests {

    WebDriver driver;
    NavigationBar navBar;

    @BeforeEach
    public void setUp() {
        driver = new Driver().get();
        navBar = new NavigationBar(driver);
        new Login(driver)
                .openPage()
                .loginWith(GOOGLE);
    }


    @Test
    //scenario#1
    public void canAddReaction() {
        Post openedPost = navBar
                .openChannels()
                .searchAndOpenChannel("Interview Channel")
                .clickOnRandomPost()
                .viewPost();

        String chosenReaction = openedPost
                .hoverOverReactions()
                .clickOnRandomReaction()
                .getReactionText();

        // check that reaction under post is the one that we picked
        assertEquals(chosenReaction, openedPost.getAddedReactionText());

    }

    @Test
    //scenario#2
    public void canPublishAnnouncementWithCustomDate() {

        PostEditor post = navBar
                .openChannels()
                .searchAndOpenChannel("Interview Channel")
                .startEditingNewPost();

        String postMessage = post
                .typePost()
                .getLastPostMessage();

        String publishedPostAsText = post
                .shareAsAnnouncement()
                .selectCustomExpireDateOption()
                .selectCustomDateWeekLater()
                .shareAnnouncement()
                .confirm()
                .waitForNewPostWithText(postMessage)
                .getPostByIndex(0)
                .getText();

        assertAll(
                // check that 1st (pinned) post is the one we created
                () -> assertTrue(publishedPostAsText.contains(postMessage)),

                // check that 1st post is an announcement
                () -> assertTrue(publishedPostAsText.contains("Announcement")),

                // check its expiry date (could be in 6 days or in 7 days - depends on the time published, before 12AM or after)
                () -> assertThat(publishedPostAsText, either(containsString("Expires in 6 days"))
                        .or(containsString("Expires in 7 days")))

        );

    }

    @Test
    //scenario#3
    public void canAddNestedPage() {
        String pageToEdit = "This Page Group";
        String parentPage = "First Page";
        String newPage = String.valueOf(System.currentTimeMillis());

        navBar
                .openPages()
                .openPageWithName(pageToEdit)
                .editPage()
                .managePages()
                .addNewPage()
                .createPageWithName(newPage)
                .selectParentPageWithName(parentPage)
                .createPage()
                .submitChanges();

        List<WebElement> nestedPages = navBar
                .openPages()
                .getAllPagesNestedUnder(parentPage);

        // check that nested pages contain the one that we created
        assertTrue(nestedPages.stream().anyMatch(p -> p.getText().equals(newPage)));

    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

