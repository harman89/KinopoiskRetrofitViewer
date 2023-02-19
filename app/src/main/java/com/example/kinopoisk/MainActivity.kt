package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinopoisk.view.EnterFragment
/*TODO:
   1. Film List
      1.7. Чтобы по клику открывался Film Deatil Fragment с нужным фильмом
      1.8. Фильмы грузятся только когда нажимаю на фильтр
   2. Auto Login
   3. Save films without internet
   4. Filter
   5. ???
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