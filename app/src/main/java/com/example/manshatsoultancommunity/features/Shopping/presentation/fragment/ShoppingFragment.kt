package com.example.manshatsoultancommunity.features.Shopping.presentation.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentShoppingBinding
import com.example.manshatsoultancommunity.util.contactByWhatsApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment: Fragment() {
    private lateinit var binding: FragmentShoppingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookShopping.setOnClickListener {
            contactByWhatsApp("01015647267","+2")
        }
    }
    override fun onDestroyView() {
        binding.emptyListAnimation.cancelAnimation()
        super.onDestroyView()
    }
}