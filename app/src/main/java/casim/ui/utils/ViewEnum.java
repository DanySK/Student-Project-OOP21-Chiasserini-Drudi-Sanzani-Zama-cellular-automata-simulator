package casim.ui.utils;

public enum ViewEnum {
    AUTOMATON_VIEW,
    AUTOMATON_CONFIG_MENU;

    private static final String EXT = ".fxml"; 

    public String getResourceName() {
        return "/" + this.name().toLowerCase() + EXT;
    }
}
