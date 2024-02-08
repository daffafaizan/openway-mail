package tests;

import org.testng.annotations.Test;
import pages.InboxPages;

public class InboxTests extends BaseTest {
    protected InboxPages inboxPages;

    public InboxTests() {
        this.inboxPages = new InboxPages(driver);
    }

    @Test
    public void enterQueryTest() {
        inboxPages.enterQuery("is:unread");
    }

    @Test
    public void returnEnterQueryTest() {
        inboxPages.returnEnterQuery();
    }

    @Test
    public void retrieveLatestUnreadTitleTest() {
        inboxPages.retrieveLatestUnreadTitle();
    }

}
