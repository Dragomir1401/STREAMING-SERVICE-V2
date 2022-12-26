package constants;

public enum PageNames {
    LOGIN("login"),
    LOGOUT("logout"),
    REGISTER("register"),
    SEE_DETAILS("see details"),
    UPGRADES("upgrades"),
    HOMEPAGE("homepage"),
    MOVIES("movies");

    public final String text;

    PageNames(String text) {
        this.text = text;
    }

    public static PageNames fromString(String text) {
        for (PageNames commandType : PageNames.values()) {
            if (commandType.text.equalsIgnoreCase(text)) {
                return commandType;
            }
        }
        return null;
    }
}

