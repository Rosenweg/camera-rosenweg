# Rosenweg Kamera – Anleitung

## Was im Rosenweg anders ist

- Nach dem Speichern werden Fotos und Videos nach `Persönlich/Kamera` hochgeladen (nicht bei Aufnahmen, die eine andere App angefordert hat).
- Das Vorschaubild zeigt das neueste Foto aus der Nextcloud; Antippen öffnet es.
- Keine Standortdaten in den Fotos; Verweis auf Rosenweg Galerie statt Fossify Gallery.
- **Rosenweg-Nextcloud:** Anmeldung über den Login der Nextcloud mit dem Rosenweg-Konto im Browser; die App erhält ein eigenes App-Passwort, nie das eigentliche Passwort. Auf geteilten Telefonen kommt der Zugang von Rosenweg Gerät. Hochgeladen wird über eine Warteschlange, sobald Netz da ist; lokal gelöscht wird erst, wenn die Nextcloud den Empfang bestätigt hat. Auf dem eigenen Handy erscheint beim Öffnen der gemeinsame Rosenweg-Anmeldeschirm (Rosenweg-Karte oder Rosenweg-Konto, keine PIN); danach kommt das App-Passwort von der Anlage, sonst bleibt der Login der Nextcloud im Browser. Ohne Anmeldung ist die App nicht nutzbar.
- **Einstellungen pro Person:** Die Einstellungen der App liegen in der Nextcloud der Person (`Persönlich/.rosenweg/`) und sind auf jedem Telefon wieder da, auf dem sie angemeldet ist. Gerätebezogenes (Widgets, Pfade, Zähler) bleibt auf dem Gerät.
- **Rosenweg Updater:** Auf Handys ohne Rosenweg Gerät prüft die App den App-Katalog der Telefonanlage und bietet Aktualisierungen für alle installierten Rosenweg-Apps an. Jede APK wird vorher geprüft (Prüfsumme, Paketname, Version, Rosenweg-Signatur); Android lässt jede Installation bestätigen. Auf geteilten Telefonen übernimmt das Rosenweg Gerät.

## Installation

- **Geteilte Rosenweg-Telefone:** Rosenweg Gerät installiert die App automatisch aus dem App-Katalog der Telefonanlage und hält sie aktuell.
- **Eigenes Handy:** APK von [www.rosenweg4303.ch/apps](https://www.rosenweg4303.ch/apps) herunterladen und installieren. Danach hält der Rosenweg Updater die Rosenweg-Apps aktuell.

Die App gibt es nicht im Play Store und nicht auf F-Droid. Sie ist mit dem Rosenweg-Schlüssel signiert; Fassungen anderer Herkunft lassen sich nicht darüber installieren.
