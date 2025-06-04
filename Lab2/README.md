# JavaFX - photo loading app

W repo znajduje się kod źródłowy aplikacji, która pozwala na wczytywanie obrazu. W dalszej części miała ona pozwalać na obróbkę obrazu. 

## Ekran główny
<p align="center">
  <img width="996" src="https://github.com/user-attachments/assets/a0d7f04a-3cb5-4ae8-92ac-b6ad40d6a888" />

</p>

Na ekranie głównym widzimy (od góry):
1. Nagłówek z logo pwr i nazwą aplikacji
2. Tekst powitalny
3. Button do wczytania obrazu
4. Listę rozwijaną z możliwością wyboru operacji (aktywna dopiero po wczytaniu zdjęcia)
5. Button do wykonania operacji z listy rozwijanej (aktywny po wczytaniu obrazu)
6. Button do zapisu obrazu (aktywny po wykonanej operacji)
7. Miejsce na oryginalny, wczytany obraz
8. Miejsce na obraz po operacji
9. Stopę autora

## Wczytywanie obrazu
Aplikacja pozwala wczytać obraz tylko w formacie .jpg. To założenie zostało zrealizowane w taki sposób, że systemowe okno dialogowe nie pozwala na wybranie innej opcji.

<p align="center">
  <img width="659" alt="Zrzut ekranu 2025-06-4 o 21 59 24" src="https://github.com/user-attachments/assets/961723ce-0759-4e2d-95a1-6396017cddf4" />
</p> 

``` Java
    private void onLoadImageClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik obrazu");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Obrazy JPG", "*.jpg"));
      ...
      ...
      ...  
    }
```


## Po wczytaniu obrazu

<p align="center"
  <img width="994" src="https://github.com/user-attachments/assets/31711c7f-7445-47ef-be29-2774830fce52" />
</p>
Po wczytaniu obrazu aktywują się guziki i lista rozwijana opisane w punkcie o ekranie głównym. Wczytany obraz powiększa swoje miejsce

## Po dokonaniu operacji
<p align="center">
  <img width="985" src="https://github.com/user-attachments/assets/a53e9b70-de7d-417a-a95d-3d14f1475efd" />
</p>

Miejsca na obrazy wyrównują się. 

Po kliknięciu w guzik "zapisz obraz" wyświetla się okno dialogowe, które pozwala podać nazwę zapisywanego zdjęcia. Obrazy domyślnie zapisywane są w systemowym katalogu na zdjęcia

```Java
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
```
