package edu.westga.cs1302.pantry_system.view;

//import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.westga.cs1302.pantry_system.model.Food;
import edu.westga.cs1302.pantry_system.model.Pantry;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Stores and initializes all of the fxml codebehind
 * 
 * @author wv00005
 * @version Fall 2024
 * 
 */
public class MainWindow {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> foodTypes;

	@FXML
	private ListView<Food> pantryListView;

	@FXML
	private TextField entry;

	@FXML
	private Button addButton;

	@FXML
	private Button enterButton;

	@FXML
	private Button minusButton;

	@FXML
	private Button plusButton;

	@FXML
	private Button removeButton;

	@FXML
	private Button countButton;

	@FXML
	private TextField numberEntry;

	private Pantry pantry;

	@FXML
	private void handleAddButtonClick() {
		String foodName = this.entry.getText();
		String foodType = this.foodTypes.getValue();
		Food food = new Food(foodName, foodType);

		this.pantry.addFood(food);
		this.pantryListView.getItems().add(food);

		this.pantryListView.refresh();
	}

	@FXML
	private void handleEntryButtonClick() {
		int num = Integer.parseInt(this.numberEntry.getText());
		Food food = this.pantryListView.getSelectionModel().getSelectedItem();

		for (Food currentItem : this.pantry.getPantry()) {
			if (currentItem == food) {
				currentItem.setQuantity(num);
			}
		}

		this.pantryListView.getItems().setAll(this.pantry.getPantry());

		this.pantryListView.refresh();
	}

	@FXML
	private void handleRemoveButton() {
		Food food = this.pantryListView.getSelectionModel().getSelectedItem();
		ArrayList<Food> unwantedFoods = new ArrayList<Food>();

		for (Food currentItem : this.pantry.getPantry()) {
			if (currentItem == food) {
				unwantedFoods.add(food);
			}
		}

		for (Food currentFood : unwantedFoods) {
			this.pantry.removeFood(currentFood);
			this.pantryListView.getItems().remove(currentFood);
		}

		this.pantryListView.getItems().setAll(this.pantry.getPantry());

		this.pantryListView.refresh();
	}

	@FXML
	private void handleCountButton() {

		Stage popupStage = new Stage();

		AnchorPane anchorPane = new AnchorPane();

		Label label = new Label("Food Quantity: ");
		AnchorPane.setTopAnchor(label, 10.0);
		AnchorPane.setLeftAnchor(label, 10.0);
		anchorPane.getChildren().add(label);

		Scene popupScene = new Scene(anchorPane, 200, 100);
		popupStage.setScene(popupScene);
		popupStage.setTitle("Pantry Total");
		popupStage.show();

	}

	@FXML
	void initialize() {
		assert this.addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.countButton != null : "fx:id=\"countButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.enterButton != null : "fx:id=\"enterButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.entry != null : "fx:id=\"entry\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.foodTypes != null : "fx:id=\"foodTypes\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.minusButton != null : "fx:id=\"minusButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.numberEntry != null : "fx:id=\"numberEntry\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.pantryListView != null
				: "fx:id=\"pantryListView\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.plusButton != null : "fx:id=\"plusButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.removeButton != null
				: "fx:id=\"removeButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

		this.foodTypes.setItems(
				FXCollections.observableArrayList("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));

		this.pantry = new Pantry();

	}

	/**
	 * method for the plusButton that calls plusQuantity
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void plusButton() {
		Food food = this.pantryListView.getSelectionModel().getSelectedItem();

		for (Food currentItem : this.pantry.getPantry()) {
			if (currentItem == food) {
				currentItem.plusQuantity();
			}
		}

		this.pantryListView.getItems().setAll(this.pantry.getPantry());
		this.pantryListView.refresh();
	}

	/**
	 * method for the minus button that calls the minusQuantity
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void minusButton() {

		Food food = this.pantryListView.getSelectionModel().getSelectedItem();

		for (Food currentItem : this.pantry.getPantry()) {
			if (currentItem == food) {
				currentItem.minusQuantity();
			}
		}

		this.pantryListView.getItems().setAll(this.pantry.getPantry());
		this.pantryListView.refresh();
	}

}
