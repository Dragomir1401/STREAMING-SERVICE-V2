package commands;

import momentary.PageNow;

public final class HomepageSetter {
    private HomepageSetter() {

    }
    /**
     * sets page to homepage
     * @return - the new page
     */
    public static PageNow run() {
        return new PageNow("homepage");
    }
}
