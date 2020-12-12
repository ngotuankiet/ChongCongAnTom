package com.kietngo.example.laws.traffic.ui.index

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kietngo.example.laws.traffic.R
import com.kietngo.example.laws.traffic.base.BaseFragment
import com.kietngo.example.laws.traffic.databinding.FragmentIndexBinding
import com.kietngo.example.laws.traffic.ui.violation.ViolationViewModel
import timber.log.Timber


class IndexFragment : BaseFragment() {
    private lateinit var binding: FragmentIndexBinding
    private val viewModel : IndexViewModel by viewModels(){
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return activity?.application?.let { IndexViewModel(it) } as T
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentIndexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postId = arguments?.getInt("postId")
        Timber.d("check postID $postId")

        if(postId != null){
            viewModel.getViolationWithId(postId).observe(viewLifecycleOwner,{ violationUi ->
                binding.tvTitleViolation.text = violationUi.violation.name
                binding.tvContentViolation.text = violationUi.violation.objectTraffic
                binding.tvValueViolation.text = violationUi.violation.fines
                if(violationUi.violation.additionalPenalties != null){
                    binding.tvContentViolation.text = violationUi.violation.additionalPenalties
                    binding.tvContentViolation.visibility = View.VISIBLE
                }
                if(violationUi.violation.otherPenalties != null){
                    binding.tvOtherPenalties.text = violationUi.violation.otherPenalties
                    binding.tvOtherPenalties.visibility = View.VISIBLE
                }

                //share
                binding.btnShare.setOnClickListener { view ->
                    val sendIntent = Intent().apply{
                        action = Intent.ACTION_SEND
                        val contentSend = "${violationUi.violation.name} - ${violationUi.violation.fines} + app V"
                        putExtra(Intent.EXTRA_TEXT, contentSend)
                        type= "text/plain"
                    }
                    val shareIntent =Intent.createChooser(sendIntent,null)
                    startActivity(shareIntent)
                }
            })

        }
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
    }
}