package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinopoisk.view.EnterFragment
/*TODO:
   4. Filter
   5. Разнести по разным ViewModel
*/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EnterFragment.newInstance())
                .commitNow()
        }
    }
}