package id.autodhil.koramvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koramvvm.db.FavoriteHelper
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val favoriteHelper = FavoriteHelper.getInstance(applicationContext)
        favoriteHelper?.open()
    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

}