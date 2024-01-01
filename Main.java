import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int BOARD_SIZE = 3;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private String currentPlayer = PLAYER_X;
    private Button[][] buttons;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe Game developed by Durkesh Kumar and Friends");

        // Create a VBox to stack the title and the grid
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20));

        // Create a grid for the Tic-Tac-Toe board
        GridPane gridPane = createBoard();
        gridPane.setAlignment(Pos.CENTER);

        // Set a linear gradient background
        BackgroundFill backgroundFill = new BackgroundFill(
                Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        gridPane.setBackground(new Background(backgroundFill));

        // Add a title to the VBox
        Text title = new Text("Tic Tac Toe Game");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(title);

        // Add the grid to the VBox
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 300, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createBoard() {
        GridPane gridPane = new GridPane();
        buttons = new Button[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setStyle("-fx-font-size: 2em;");
                button.setOnAction(event -> handleButtonClick(button));

                gridPane.add(button, i, j);
                buttons[i][j] = button;
            }
        }

        return gridPane;
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);
            if (checkWinner()) {
                showAlert("Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isBoardFull()) {
                showAlert("It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
            }
        }
    }

    private boolean checkWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[i][0].getText().equals(currentPlayer)
                    && buttons[i][1].getText().equals(currentPlayer)
                    && buttons[i][2].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (buttons[0][i].getText().equals(currentPlayer)
                    && buttons[1][i].getText().equals(currentPlayer)
                    && buttons[2][i].getText().equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(currentPlayer)
                && buttons[1][1].getText().equals(currentPlayer)
                && buttons[2][2].getText().equals(currentPlayer))
                || (buttons[0][2].getText().equals(currentPlayer)
                && buttons[1][1].getText().equals(currentPlayer)
                && buttons[2][0].getText().equals(currentPlayer));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = PLAYER_X;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
