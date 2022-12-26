package commands;

import momentary.PageNow;

import static constants.Constants.HOMEPAGE;

public final class HomepageSetter {
    private HomepageSetter() {

    }
    /**
     * sets page to homepage
     * @return - the new page
     */
    public static PageNow run() {
        return new PageNow(HOMEPAGE);
    }
}
