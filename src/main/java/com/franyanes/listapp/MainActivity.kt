package com.franyanes.listapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.franyanes.listapp.presentation.productdetail.ProductDetailFragment
import com.franyanes.listapp.presentation.productlist.ProductListFragment

class MainActivity : FragmentActivity(R.layout.main_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ProductListFragment>(R.id.root)
            }
        }
    }
}
