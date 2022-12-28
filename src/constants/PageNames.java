package constants;

public enum PageNames {
    LOGIN("login"),
    LOGOUT("logout"),
    REGISTER("register"),
    SEE_DETAILS("see details"),
    UPGRADES("upgrades"),
    HOMEPAGE("homepage"),
    MOVIES("movies");

    private final String text;

    public String getText() {
        return text;
    }

    PageNames(final String text) {
        this.text = text;
    }


    /**
     * generates page name from string
     * @param text  string
     * @return  page name
     */
    public static PageNames fromString(final String text) {
        for (PageNames pageName : PageNames.values()) {
            if (pageName.text.equalsIgnoreCase(text)) {
                return pageName;
            }
        }
        return null;
    }
}

