package com.github.mhelmi.saryflagship.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mhelmi.saryflagship.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

  private var binding: FragmentOrdersBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentOrdersBinding.inflate(inflater, container, false)
    return binding?.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding = null
  }
}