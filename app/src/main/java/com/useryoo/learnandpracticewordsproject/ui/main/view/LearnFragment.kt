package com.useryoo.learnandpracticewordsproject.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.useryoo.learnandpracticewordsproject.R
import com.useryoo.learnandpracticewordsproject.databinding.FragmentLearnBinding
import com.useryoo.learnandpracticewordsproject.ui.main.adapter.WordRecyclerViewAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding


class LearnFragment : Fragment(R.layout.fragment_learn) {
    private val binding by viewBinding(FragmentLearnBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list= arrayListOf<String>("Word","Word")//"Word","Word","","","","","","")
        val wordRecyclerViewAdapter= WordRecyclerViewAdapter(list)

        binding.apply {
            addWordEFAB.setOnClickListener {

            }
            wordListRecyclerView.layoutManager=
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            wordListRecyclerView.adapter=wordRecyclerViewAdapter
            wordListRecyclerView.setHasFixedSize(true)
        }
    }
}