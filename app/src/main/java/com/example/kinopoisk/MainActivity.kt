package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinopoisk.view.EnterFragment
/*TODO:
   1. Film List
      1.1. POJO+
      1.2. Query +
      1.3. ViewModel
      1.4. Layout
      1.5. Fragment Class
      1.6. User Repository+
      1.7. Чтобы по клику открывался Film Deatil Fragment с нужным фильмом
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