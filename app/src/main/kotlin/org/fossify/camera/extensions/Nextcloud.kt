package org.fossify.camera.extensions

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import ch.rosenweg4303.cloud.Ablage
import ch.rosenweg4303.cloud.Eintrag
import ch.rosenweg4303.cloud.Hochladen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/*
 * Rosenweg: Fotos und Videos gehen in den persoenlichen Ordner der Nextcloud
 * (Persönlich/Kamera/<Monat>/) und werden danach auf dem Telefon geloescht.
 * Das Vorschaubild zeigt das Neueste aus der Nextcloud (Regel 6).
 */

const val NC_KAMERA = "Kamera"

private val FOTO = setOf("jpg", "jpeg", "png", "heic", "heif", "webp")
private val VIDEO = setOf("mp4", "3gp", "mkv", "webm", "mov")

/** Gespeichertes Foto/Video in die Warteschlange (Hintergrund). */
fun Context.kameraHochladen(uri: Uri) {
    val app = applicationContext
    Thread {
        try {
            var name: String? = null
            app.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use {
                if (it.moveToFirst()) name = it.getString(0)
            }
            val mime = app.contentResolver.getType(uri)
            val endung = when {
                mime?.startsWith("video") == true -> "mp4"
                else -> "jpg"
            }
            val datei = name ?: (SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ROOT).format(Date()) + ".$endung")
            Hochladen.einreihen(app, uri, Hochladen.ziel(NC_KAMERA, datei), lokalLoeschen = true, mime = mime)
        } catch (e: Exception) {
            android.util.Log.w("RosenwegKamera", "Hochladen einreihen: $e")
        }
    }.start()
}

/** Neuestes Foto (bzw. Video) der Kamera in der Nextcloud. Blockiert. */
fun Context.neuestesAusNextcloud(foto: Boolean): Eintrag? =
    Ablage.dateien(this, NC_KAMERA).orEmpty()
        .filter { it.name.substringAfterLast('.', "").lowercase(Locale.ROOT) in (if (foto) FOTO else VIDEO) }
        .maxByOrNull { it.geaendert }
