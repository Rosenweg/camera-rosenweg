<p align="center"><img src="assets/logo/rosenweg-kamera-512.png" width="96" alt="Logo"></p>
<h1 align="center">Rosenweg Kamera</h1>
<p align="center">Kamera für die Smartphones im Rosenweg – Fotos und Videos landen direkt in der eigenen Rosenweg-Nextcloud.</p>
<p align="center"><a href="https://www.rosenweg4303.ch/apps">Herunterladen</a> · <a href="docs/anleitung.md">Anleitung</a></p>

## Inhalt

- [Überblick](#überblick)
- [Plattformen](#plattformen)
- [Bedienung](#bedienung)
- [Geteilte Telefone und eigenes Handy](#geteilte-telefone-und-eigenes-handy)
- [Aufbau](#aufbau)
- [Bauen](#bauen)
- [CI und Release](#ci-und-release)
- [Konfiguration](#konfiguration)
- [Herkunft und Lizenz](#herkunft-und-lizenz)

## Überblick

Die Kamera-App der Rosenweg-Smartphones. Fotos und Videos gehen in die Rosenweg-Nextcloud der Person, damit auf geteilten Telefonen nichts liegen bleibt und alles auch vom eigenen Handy oder Computer aus erreichbar ist.

Was die App kann:

- **Hochladen:** Nach dem Speichern gehen Fotos und Videos nach `Persönlich/Kamera` (nicht bei Aufnahmen, die eine andere App angefordert hat).
- **Sicher löschen:** Hochgeladen wird über eine Warteschlange, sobald Netz da ist; lokal gelöscht wird erst, wenn die Nextcloud den Empfang bestätigt hat.
- **Vorschaubild:** zeigt das neueste Foto aus der Nextcloud; Antippen öffnet es.
- **Keine Standortdaten** in den Fotos; „Galerie“ verweist auf Rosenweg Galerie statt Fossify Gallery.
- **Einstellungen pro Person:** liegen in der Nextcloud (`Persönlich/.rosenweg/`) und sind auf jedem Telefon wieder da.
- **Rosenweg Updater:** hält auf dem eigenen Handy alle Rosenweg-Apps aktuell.

| Daten | im Rosenweg-System | auf dem Telefon nur |
|---|---|---|
| Fotos und Videos | Nextcloud der Person, sofort hochgeladen | bis zum bestätigten Hochladen |
| Einstellungen | Nextcloud der Person | aktuelle Kopie; Gerätebezogenes (Widgets, Pfade, Zähler) bleibt lokal |

## Plattformen

| Plattform | Stand |
|---|---|
| Android 10 und neuer (API 29–36) | ja, APK auf [rosenweg4303.ch/apps](https://www.rosenweg4303.ch/apps) |
| Geteilte Rosenweg-Telefone | ja, Rosenweg Gerät installiert und aktualisiert sie |
| Play Store, F-Droid | nein – nur mit Rosenweg-Signatur |

## Bedienung

| Schritt | So geht’s |
|---|---|
| Anmelden (eigenes Handy) | Beim Öffnen erscheint der gemeinsame Rosenweg-Anmeldeschirm: Rosenweg-Karte hinten ans Telefon halten (NFC) oder „Mit Rosenweg-Konto anmelden“. Keine PIN. Ohne Anmeldung ist die App nicht nutzbar. |
| Nextcloud-Zugang | kommt nach der Anmeldung als eigenes App-Passwort von der Anlage – nie das eigentliche Passwort. Geht das nicht, Login der Nextcloud im Browser. |
| Foto oder Video | aufnehmen wie gewohnt – die Aufnahme wandert von selbst nach `Persönlich/Kamera`. |
| Ansehen | Vorschaubild unten rechts antippen. |
| Aktualisieren (eigenes Handy) | Der Rosenweg Updater bietet neue Versionen an; Android lässt jede Installation bestätigen. |

Ausführlich: [docs/anleitung.md](docs/anleitung.md).

## Geteilte Telefone und eigenes Handy

| | Geteiltes Telefon (mit Rosenweg Gerät) | Eigenes Handy |
|---|---|---|
| Anmeldung | gilt die Anmeldung von Rosenweg Gerät (Karte am Sperrbildschirm, PIN) – kein eigener Anmeldeschirm | eigene Anmeldung dieser App über den gemeinsamen Anmeldeschirm (Karte oder Rosenweg-Konto, keine PIN); bis zu 20 Sitzungen pro Person |
| Nextcloud-Zugang | von Rosenweg Gerät | App-Passwort von der Anlage nach der Anmeldung |
| Installieren und aktualisieren | Rosenweg Gerät aus dem App-Katalog der Telefonanlage | APK von /apps, danach der Rosenweg Updater |

## Aufbau

| Teil | Inhalt |
|---|---|
| `app/src/main/kotlin/org/fossify/camera/` | Kamera aus Fossify Camera (Kotlin-Namensraum wie im Original) |
| `…/extensions/Nextcloud.kt` | Rosenweg: Hochladen nach `Persönlich/Kamera`, neuestes Foto aus der Nextcloud |
| `…/activities/MainActivity.kt`, `SettingsActivity.kt` | Rosenweg-Anpassungen: Anmelden, Updater |
| [commons-rosenweg](https://github.com/Rosenweg/commons-rosenweg) | Fossify Commons in der Rosenweg-Fassung (`org.fossify:commons:7.1.3-rosenweg.1`) und Modul `ch.rosenweg4303:cloud` (gemeinsamer Anmeldeschirm, Nextcloud, Warteschlange, Einstellungen, Updater) |
| `assets/logo/` | Logo aus dem Baukasten von rosenweg-basis: Turm, Weg und Rose mit Kamera-Plakette, dazu ein monochromes Designsymbol (Android 13+); die wählbaren Fossify-Symbolfarben gibt es nicht mehr |

Paketname `ch.rosenweg4303.camera`; nur die `applicationId` ist eigen.

## Bauen

Zuerst [commons-rosenweg](https://github.com/Rosenweg/commons-rosenweg) ins lokale Maven-Repository veröffentlichen (`mavenLocal()`), dann die App bauen:

```bash
# in commons-rosenweg
./gradlew -PVERSION=7.1.3-rosenweg.1 publishToMavenLocal

# in diesem Repo
./gradlew assembleFossRelease
```

Die APK liegt unter `app/build/outputs/apk/foss/release/`. Logo neu erzeugen (rosenweg-basis daneben): `sh assets/logo/erzeugen.sh`; welches Zeichen die App trägt, steht in rosenweg-basis `werkzeug/logo/android-apps.txt`.

Mehr: [docs/entwickeln.md](docs/entwickeln.md).

## CI und Release

| Workflow | Auslöser | Ergebnis |
|---|---|---|
| `apps.yml` (Rosenweg) | GitHub-Release veröffentlicht, oder von Hand `gh workflow run apps.yml --ref <tag>` | APK geht an **[rosenweg4303.ch/apps](https://www.rosenweg4303.ch/apps)** und in den App-Katalog der Telefonanlage |
| `pr.yml`, `testing-build.yml`, `release.yml` u. a. | aus Fossify übernommen | Prüfungen und Play-Store-Release des Originals; für Rosenweg nicht nötig |

Ablauf einer neuen Version:

1. APK lokal bauen und mit dem Rosenweg-Schlüssel signieren.
2. `gh release create v<versionName> <datei.apk> --notes "Was ist neu"` – Tag = `versionName` der APK, eine APK je Release, Tag auf `main`.
3. `apps.yml` ruft `apps-release.yml` aus Rosenweg/Website auf. Anmeldung per GitHub-OIDC, kein gespeichertes Geheimnis; die Anlage prüft Tag, Prüfsumme, Rosenweg-Signatur und Paketnamen.

Danach bekommen die geteilten Telefone und der Rosenweg Updater die neue Version.

## Konfiguration

| Wo | Name | Wozu |
|---|---|---|
| Repo-Wurzel (nicht im Repo) | `keystore.properties` | Rosenweg-Signierschlüssel; Vorlage `keystore.properties_sample` |
| `gradle.properties` | `APP_ID`, `VERSION_NAME`, `VERSION_CODE` | Paketname und Version |
| `gradle/libs.versions.toml` | `commons`, `rosenwegCloud` | Versionen der Rosenweg-Bibliotheken |

Für `apps.yml` braucht es kein GitHub-Secret (OIDC).

## Herkunft und Lizenz

Fork von [Fossify Camera](https://github.com/FossifyOrg/Camera). Die Rosenweg-Anpassungen sind in `main` gemergt; `main` ist der Hauptzweig. Herzlichen Dank an das Fossify-Team und alle Mitwirkenden für die solide Grundlage.

Lizenz: GNU General Public License v3.0, siehe [LICENSE](LICENSE).
