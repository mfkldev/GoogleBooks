package br.com.marciosouza.googlebooks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.marciosouza.googlebooks.R
import br.com.marciosouza.googlebooks.databinding.ActivityMainBinding
import br.com.marciosouza.googlebooks.ui.adapter.BookPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainViewPager.adapter = BookPagerAdapter(this)

        TabLayoutMediator(binding.mainTablayout, binding.mainViewPager) { tab, position -> //??
            tab.setText(
                if (position == 0)
                    R.string.tab_books
                else
                    R.string.favorites
            )
        }.attach()

//        TabLayoutMediator(binding.mainTablayout, binding.mainViewPager, TabLayoutMediator.TabConfigurationStrategy({tab, position ->
//            tab.setText(
//            if (position == 0)
//                R.string.tab_books
//            else
//                R.string.favorites
//        ) })).attach()
//
    }
}
