package GUI;

import java.util.ArrayList;

import control.TenantControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.Tenant;
 
public class GUIMain extends Application 
{
	//Creating controller-component
	private TenantControl tenantControl = new TenantControl();
	
    private Stage window;
    private GridPane gridPane = new GridPane();
    private GridPane buttonGridPane = new GridPane();

    public TableView<Tenant> tenantTable = new TableView<Tenant>();
    public ObservableList<Tenant> observableArrayList; 
   
    private Button addButton = new Button(GUIConst.ADD_BUTTON_LABEL);
    private Button deleteButton = new Button(GUIConst.DELETE_BUTTON_LABEL);
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        
        createTenantTable();

        createButtons();
        createButtonFunctions();

        createGridPane();
        createScene(primaryStage);
        
    }
    
    private void createTenantTable() 
    {
        tenantTable.setMinWidth(GUIConst.TENANT_TABLE_WIDTH);
        tenantTable.setMinHeight(GUIConst.TENANT_TABLE_HEIGHT);

        //initializingColumns
        loadTableColumns();
        observableArrayList = FXCollections.observableArrayList(tenantControl.getTenantList());
        tenantTable.setItems(observableArrayList);
    }
    
    private void loadTableColumns() 
    {
    	
    	VBox vBox = new VBox();
    	vBox.setPrefWidth(GUIConst.TENANT_TABLE_WIDTH);
        vBox.setPrefHeight(GUIConst.TENANT_TABLE_HEIGHT);

        TableColumn<Tenant, Integer> idColumn = new TableColumn<>(GUIConst.TENANT_ID_LABEL);
        idColumn.setEditable(false);
        TableColumn<Tenant, String>  firstNameColumn = new TableColumn<>(GUIConst.TENANT_FIRSTNAME_LABEL);
        firstNameColumn.setEditable(false);
        TableColumn<Tenant, String>  lastNameColumn = new TableColumn<>(GUIConst.TENANT_LASTNAME_LABEL);
        lastNameColumn.setEditable(true);
        TableColumn<Tenant, String>  addressColumn = new TableColumn<>(GUIConst.TENANT_ADDRESS_LABEL);
        addressColumn.setEditable(false);
        TableColumn<Tenant, String> cityColumn = new TableColumn<>(GUIConst.TENANT_CITY_LABEL);
        cityColumn.setEditable(false);
        TableColumn<Tenant, Double> rentColumn = new TableColumn<>(GUIConst.TENANT_RENT_LABEL);
        rentColumn.setEditable(false);

        	//Sizing columns
        	idColumn.setMinWidth(vBox.getPrefWidth()*0.1);
            firstNameColumn.setMinWidth(vBox.getPrefWidth() * 0.2);
            lastNameColumn.setMinWidth(vBox.getPrefWidth()*0.2);
            addressColumn.setMinWidth(vBox.getPrefWidth() * 0.2);
            cityColumn.setMinWidth(vBox.getPrefWidth() * 0.2);
            rentColumn.setMinWidth(vBox.getPrefWidth()* 0.2);
            
            //Referencing attributes
            idColumn.setCellValueFactory(new PropertyValueFactory("id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory("address"));
            cityColumn.setCellValueFactory(new PropertyValueFactory("city"));
            rentColumn.setCellValueFactory(new PropertyValueFactory("rent"));

            //Editing columnn cells/rendering 
            rentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

            firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            
            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lastNameColumn.setOnEditCommit(
            	    new EventHandler<CellEditEvent<Tenant, String>>() {
            	    	
            	    	@Override
            	        public void handle(CellEditEvent<Tenant, String> t) {
            	    	
            	    		Tenant updateTenant = t.getTableView().getItems().get(
                	                t.getTablePosition().getRow());
                	                
            	    		updateTenant.setLastName(t.getNewValue());
            	    		tenantControl.updateTenant(updateTenant);
            	    		
                        	//observableArrayList = FXCollections.observableArrayList(tenantControl.getTenantList());
                        	//tenantTable.setItems(observableArrayList);
            	    		System.out.println(updateTenant.getLastName());
            	    		//observableArrayList
            	        }	

            	    }
            	);

            addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            cityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            
            rentColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            
            tenantTable.setEditable(true);
            tenantTable.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, addressColumn, cityColumn, rentColumn);    
    }
    
    private void createButtons() 
    {
        VBox vBox = new VBox();
        vBox.setPrefWidth(GUIConst.BUTTON_WIDTH);
        vBox.setPrefHeight(GUIConst.BUTTON_HEIGHT);

        addButton.setMinWidth(vBox.getPrefWidth());
        addButton.setMinHeight(vBox.getPrefHeight());
        deleteButton.setMinWidth(vBox.getPrefWidth());
        deleteButton.setMinHeight(vBox.getPrefHeight());
        	
        GridPane.setConstraints(addButton, 0, 0);
        GridPane.setConstraints(deleteButton, 0, 1);

       /* GridPane.setConstraints(loadFromJsonButton, 0, 2);
        GridPane.setConstraints(writeToJsonButton, 0, 3); */

        buttonGridPane.setVgap(10);
        buttonGridPane.setHgap(10);
        buttonGridPane.getChildren().addAll(addButton, deleteButton);
    }
    
    private void createButtonFunctions() 
    {
    	//Functionality for AddTenant-Button
        addButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
 
            	TextField firstNameText = new TextField();
            	firstNameText.setPromptText("Vorname");
            	
            	TextField lastNameText = new TextField();
            	lastNameText.setPromptText("Nachname");
            	
            	TextField addressText = new TextField();
            	addressText.setPromptText("Addresse");
            	
            	TextField cityText = new TextField();
            	cityText.setPromptText("Stadt");
            	
            	TextField rentText = new TextField();
            	rentText.setPromptText("Kaltmiete in Euro");
            	
            	VBox inputAlign = new VBox(firstNameText, lastNameText,
            			addressText, cityText, rentText);
            	inputAlign.setSpacing(20);
                inputAlign.setMaxSize(GUIConst.INPUT_WIDTH, GUIConst.INPUT_HEIGHT);
            	
            	//Initializing Sub-Button of Add-Button for saving new tenant
            	Button saveButton = new Button(GUIConst.SAVE_BUTTON_LABEL);
                saveButton.setMinWidth(inputAlign.getPrefWidth());
                saveButton.setMinHeight(inputAlign.getPrefHeight());
                
                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                	 
                    @Override
                    public void handle(ActionEvent event) {
                    	//Initializing a new tenant to match with postTenant from TenantControll.java

                        Tenant newTenant = new Tenant(firstNameText.getText(),
                        		lastNameText.getText(), addressText.getText(), 
                        		cityText.getText(), 
                        		new Double(rentText.getText()));
                      
                        //Alert for successfull saving
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText("Vorgang: Speichern");
                        
                        //Hier wird Tabelle aktualisiert
                        tenantControl.postTenant(newTenant);
                        	observableArrayList = FXCollections.observableArrayList(tenantControl.getTenantList());
                        	tenantTable.setItems(observableArrayList);
                        	alert.setContentText("Der Mieter wurde gespeichert!");
                        	alert.showAndWait();
                        
                        firstNameText.clear();
                        lastNameText.clear();
                        addressText.clear();
                        cityText.clear();
                        rentText.clear();

                     	}
                });
 
                BorderPane secondaryLayout = new BorderPane();
                BorderPane.setMargin(saveButton, new Insets(12,12,12,12)); 

                secondaryLayout.setCenter(inputAlign);
                secondaryLayout.setAlignment(saveButton, Pos.CENTER);
                secondaryLayout.setBottom(saveButton);
 
                Scene secondScene = new Scene(secondaryLayout, GUIConst.WINDOW_WIDTH, GUIConst.WINDOW_HEIGHT);
 
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Anlage eines neuen Mieters");
                newWindow.setScene(secondScene);

                
                newWindow.show();
            }
        });
        
        deleteButton.setOnAction(actionEvent -> {
        	Tenant deleteTenant = tenantTable.getSelectionModel().getSelectedItem();
        	tenantControl.deleteTenant(deleteTenant);   
        	
        	  //Alert for successfull saving
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Vorgang: Löschen");
            
           tenantControl.deleteTenant(deleteTenant);
            	alert.setContentText("Der Mieter wurde gelöscht!");
            	alert.showAndWait();
           
            	observableArrayList = FXCollections.observableArrayList(tenantControl.getTenantList());
            	tenantTable.setItems(observableArrayList);
            
        });
        
    }
    
    private void createGridPane() 
    {
        gridPane.setPadding(new Insets(10, 0, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        GridPane.setConstraints(tenantTable, 0, 0);
        GridPane.setConstraints(buttonGridPane, 1, 0);

        gridPane.getChildren().addAll(tenantTable, buttonGridPane);
    }
    
    private void createScene(Stage primaryStage) 
    {
        Scene scene = new Scene(gridPane, GUIConst.WINDOW_WIDTH, GUIConst.WINDOW_HEIGHT);

        window = primaryStage;
        window.setTitle(GUIConst.TITLE);

        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
    
 /*   public void onEditCommitSelectedProductTable(){
    	CellEditEvent<TableView<Tenant> tenantTable,TablePosition<S,T> pos,
    	EventType<TableColumn.CellEditEvent> eventType, T newValue> cee = new CellEditEvent<?,?>();
        Tenant updatetenant = (Tenant) event.getNewValue();

        // other data that might be helpful:
        TablePosition<?,?> position = event.getTablePosition();
        int row = position.getRow();
        System.out.println(row);
    }*/
    
  public static void main(String[] args) 
  {
      launch(args);
  }

}