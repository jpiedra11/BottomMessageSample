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
        val addressFragment =
            BottomDialogFragment.Builder()
                .setTitle(getString(R.string.address_title))
                .setMessage(getString(R.string.address_message))
                .setFirstButton(getString(R.string.take_picture)) {
                    val takePhotoIntent = Intent(this, PhotoActivity::class.java)
                    startActivity(takePhotoIntent)
                }
                .setSecondButton(getString(R.string.load_file)) {
                    Toast.makeText(
                        this,
                        getString(R.string.second_button_clicked),
                        Toast.LENGTH_LONG,
                    ).show()
                }
                .build()

        addressFragment.show(supportFragmentManager, addressFragment.tag)
    }

    fun showCICDialog(view: View) {
        val cicFragment =
            BottomDialogFragment.Builder()
                .setTitle(getString(R.string.cic))
                .setMessage(getString(R.string.cic_message))
                .setImage(R.drawable.cic)
                .build()

        cicFragment.show(supportFragmentManager, cicFragment.tag)
    }
}
