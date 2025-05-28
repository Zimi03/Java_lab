package org.example.parallel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button loadImageButton;

    @FXML
    private ComboBox<String> operationComboBox;

    @FXML
    private Button executeButton;

    @FXML
    private ImageView originalImageView;

    @FXML
    private ImageView processedImageView;

    @FXML
    private Button saveButton;

    @FXML
    private Label toastMessage;

    @FXML
    private VBox saveModal;

    @FXML
    private TextField filenameField;

    @FXML
    private Label filenameErrorLabel;

    @FXML
    private Label modalAlertLabel;

    @FXML
    private Label originalImageInfoLabel;

    private File loadedFile = null;
    private Image originalImage = null;
    private Image processedImage = null;

    private boolean operationPerformed = false;

    @FXML
    public void initialize() {
        // Ustawienia początkowe
        executeButton.setDisable(true);
        saveButton.setDisable(true);
        operationComboBox.setDisable(true);

        // Dodaj przykładowe operacje do ComboBox (możesz dopasować)
        operationComboBox.getItems().addAll("Operacja 1", "Operacja 2", "Operacja 3");

        // Personalizacja
        welcomeText.setText("Witamy w aplikacji do przetwarzania równoległego!\nPolitechnika Wrocławska");
        authorLabel.setText("Autor: Szymon Senderowski | Numer indeksu: 272603 | Politechnika Wrocławska © 2025");
        appTitleLabel.setText("Aplikacja Równoległa - PWr");
    }

    @FXML
    private void onLoadImageClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik obrazu");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Obrazy JPG", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(getWindow());
        if (selectedFile != null) {
            if (!selectedFile.getName().toLowerCase().endsWith(".jpg")) {
                showToast("Niedozwolony format pliku");
                return;
            }

            try {
                clearOldImages();

                loadedFile = selectedFile;
                originalImage = new Image(selectedFile.toURI().toString());
                originalImageView.setImage(originalImage);

                processedImage = null;
                processedImageView.setImage(null);

                operationPerformed = false;

                // Aktywuj przyciski
                executeButton.setDisable(false);
                saveButton.setDisable(false);
                operationComboBox.setDisable(false);
                operationComboBox.getSelectionModel().clearSelection();
                originalImageInfoLabel.setText("");

                showToast("Pomyślnie załadowano plik");
            } catch (Exception e) {
                showToast("Nie udało się załadować pliku");
            }
        }
    }

    @FXML
    private void onExecuteButtonClick() {
        String operation = operationComboBox.getValue();
        if (operation == null || operation.isEmpty()) {
            showToast("Nie wybrano operacji do wykonania");
            return;
        }

        processedImage = originalImage;
        processedImageView.setImage(processedImage);

        operationPerformed = true;
        showToast("Wykonano operację: " + operation);
    }

    @FXML
    private void onSaveImageClick() {
        if (loadedFile == null) {
            showToast("Brak wczytanego pliku do zapisu");
            return;
        }

        filenameField.clear();
        filenameErrorLabel.setVisible(false);
        modalAlertLabel.setVisible(!operationPerformed);
        saveModal.setVisible(true);
        mainContainerDisable(true);
    }

    @FXML
    private void onConfirmSaveClick() {
        String filename = filenameField.getText().trim();

        if (filename.length() < 3) {
            filenameErrorLabel.setVisible(true);
            return;
        }

        filenameErrorLabel.setVisible(false);

        if (!operationPerformed) {
            return;
        }

        try {
            Path picturesDir = Path.of(System.getProperty("user.home"), "Pictures");
            if (!Files.exists(picturesDir)) {
                Files.createDirectories(picturesDir);
            }

            Path savePath = picturesDir.resolve(filename + ".jpg");

            if (Files.exists(savePath)) {
                showToast("Plik " + filename + ".jpg już istnieje w systemie. Podaj inną nazwę pliku!");
                return;
            }

            // Tu zapisz obraz processedImage do pliku JPG (pseudokod)
            // Na potrzeby przykładu, po prostu kopiujemy oryginał:
            Files.copy(loadedFile.toPath(), savePath);

            showToast("Zapisano obraz w pliku " + filename + ".jpg");

            // Zamknij modal
            saveModal.setVisible(false);
            mainContainerDisable(false);

        } catch (Exception e) {
            showToast("Nie udało się zapisać pliku " + filename + ".jpg");
        }
    }

    @FXML
    private void onCancelSaveClick() {
        filenameField.clear();
        filenameErrorLabel.setVisible(false);
        saveModal.setVisible(false);
        mainContainerDisable(false);
    }

    private void clearOldImages() {
        originalImageView.setImage(null);
        processedImageView.setImage(null);
        loadedFile = null;
        originalImage = null;
        processedImage = null;
        operationPerformed = false;
    }

    private void mainContainerDisable(boolean disable) {
        // Zakładając, że główny VBox ma fx:id="mainContainer"
        // Można go dodać do FXML i tu sterować włączeniem/wyłączeniem
        // (w tym przykładzie, można go zadeklarować i podpiąć jeśli chcesz)
    }

    private Window getWindow() {
        return loadImageButton.getScene().getWindow();
    }

    // Prosty toast (Label pojawia się na chwilę i znika)
    private Timer toastTimer;

    private void showToast(String message) {
        Platform.runLater(() -> {
            toastMessage.setText(message);
            toastMessage.setVisible(true);

            if (toastTimer != null) {
                toastTimer.cancel();
            }

            toastTimer = new Timer();
            toastTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> toastMessage.setVisible(false));
                }
            }, 3000); // Toast widoczny przez 3 sekundy
        });
    }
}
