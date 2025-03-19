import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;
import models.Store;

public class ProductListController {

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, String> descriptionColumn;

    @FXML
    private TableColumn<Product, LocalDate> expiryDateColumn;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private TableColumn<Product, String> sizeColumn;

    @FXML
    private TableColumn<Product, Double> weightColumn;

    private Store store;
    @FXML
    public void initialize() {
        store = new Store();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("productSize"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        expiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        
        productTable.setItems(store.getProductList());
    }

}
