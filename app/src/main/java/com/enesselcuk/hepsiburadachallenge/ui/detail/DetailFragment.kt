package com.enesselcuk.hepsiburadachallenge.ui.detail


import androidx.navigation.fragment.navArgs
import com.enesselcuk.hepsiburadachallenge.databinding.FragmentDetailBinding
import com.enesselcuk.hepsiburadachallenge.common.BaseFragment


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun definition() {
        binding.setData = args.itunesData
    }


}