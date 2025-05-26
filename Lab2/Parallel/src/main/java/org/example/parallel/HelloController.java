package org.example.parallel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Label appTitleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private ImageView logoImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Ustawienie tekstu powitalnego
        welcomeText.setText("Witamy w aplikacji do przetwarzania równoległego!\n" +
                "Politechnika Wrocławska");

        // Personalizacja danych autora - zastąp swoimi danymi
        authorLabel.setText("Autor: Szymon Senderowski | " +
                "Numer indeksu: 272603 | " +
                "Politechnika Wrocławska © 2025");

        // Nazwa aplikacji
        appTitleLabel.setText("Aplikacja Równoległa - PWr");
    }

    // Dodatkowe metody do obsługi zdarzeń (jeśli potrzebne)
    @FXML
    private void onHelloButtonClick() {
        welcomeText.setText("Dziękujemy za skorzystanie z aplikacji!");
    }
}