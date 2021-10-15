package br.com.marciosouza.googlebooks.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.marciosouza.googlebooks.ui.fragments.BookFavoritesFragment
import br.com.marciosouza.googlebooks.ui.fragments.BookListFragment

class BookPagerAdapter(fragmentAcitvity: FragmentActivity) : FragmentStateAdapter(fragmentAcitvity){

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            BookListFragment()
        else
            BookFavoritesFragment()
    }
}