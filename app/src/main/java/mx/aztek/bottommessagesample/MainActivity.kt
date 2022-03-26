package mx.aztek.bottommessagesample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showAddressDialog(view: View) {
        val addressFragment = BottomDialogFragment.Builder()
            .setTitle("Comprueba tu domicilio")
            .setMessage("No pudimos detectar con exactitud tu dirección, " +
                    "para validar tu domicilio, " +
                    "compártenos por favor un recibo de TELMEX o CFE " +
                    "con antiguedad no mayor a 3 meses")
            .setFirstButton ("Tomar foto") {
                val takePhotoIntent = Intent(this, PhotoActivity::class.java)
                startActivity(takePhotoIntent)
            }
            .setSecondButton ("Cargar archivo") {
                Toast.makeText(this, "Second button clicked", Toast.LENGTH_LONG).show()
            }
            .build()

        addressFragment.show(supportFragmentManager, addressFragment.tag)
    }

    fun showCICDialog(view: View) {
        val cicFragment = BottomDialogFragment.Builder()
            .setTitle("CIC")
            .setMessage("El número CIC está al reverso de tu INE y tiene 9 dígitos. " +
                    "Sólo aparece en modelos D, E, F, G y H")
            .setImage(R.drawable.cic)
            .build()

        cicFragment.show(supportFragmentManager, cicFragment.tag)
    }
}