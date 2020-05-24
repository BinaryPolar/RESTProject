package GUI;

public enum GUIConst {
	;
	
    public final static String TITLE = "TenantManagement - Tool";
    public final static String ADD_BUTTON_LABEL = "Hinzuf\u00fcgen";
    public final static String SAVE_BUTTON_LABEL = "Eingaben speichern";
    public final static String DELETE_BUTTON_LABEL = "L\u00f6schen";
    public final static String UPDATE_BUTTON_LABEL = "Aktualisieren";
    
    public final static String TENANT_ID_LABEL = "ID";
    public final static String TENANT_FIRSTNAME_LABEL = "Vorname";
    public final static String TENANT_LASTNAME_LABEL = "Nachname";
    public final static String TENANT_ADDRESS_LABEL = "Adresse";
    public final static String TENANT_CITY_LABEL = "Stadt";
    public final static String TENANT_RENT_LABEL = "Kaltmiete";

    private final static double WINDOW_WIDTH_MARGIN_OFFSET = 30;
    private final static double WINDOW_HEIGHT_MARGIN_OFFSET = 20;

    public final static double WINDOW_WIDTH = 1000 + WINDOW_WIDTH_MARGIN_OFFSET;
    public final static double WINDOW_HEIGHT = 600 + WINDOW_HEIGHT_MARGIN_OFFSET;

    public final static double BUTTON_WIDTH = (WINDOW_WIDTH - WINDOW_WIDTH_MARGIN_OFFSET) * 0.16;
    public final static double BUTTON_HEIGHT = BUTTON_WIDTH * 0.25;
    public final static double TENANT_TABLE_WIDTH = (WINDOW_WIDTH - WINDOW_WIDTH_MARGIN_OFFSET) * 0.75;
    public final static double TENANT_TABLE_HEIGHT = (WINDOW_HEIGHT - WINDOW_HEIGHT_MARGIN_OFFSET);
    
    public final static double INPUT_WIDTH = (WINDOW_WIDTH - WINDOW_WIDTH_MARGIN_OFFSET) * 0.4;
    public final static double INPUT_HEIGHT = BUTTON_WIDTH * 0.3;
    
    

}
