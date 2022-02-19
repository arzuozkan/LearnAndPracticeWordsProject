package com.useryoo.learnandpracticewordsproject.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.useryoo.learnandpracticewordsproject.R
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.data.database.WordsDatabase
import com.useryoo.learnandpracticewordsproject.databinding.FragmentLearnBinding
import com.useryoo.learnandpracticewordsproject.ui.main.adapter.WordRecyclerViewAdapter
import com.useryoo.learnandpracticewordsproject.ui.main.viewModel.WordViewModel
import com.useryoo.learnandpracticewordsproject.ui.main.viewModel.WordViewModelFactory


class LearnFragment : Fragment(R.layout.fragment_learn) {
    private lateinit var wordList:List<WordModel>
    private lateinit var wordsDatabase: WordsDatabase
    private lateinit var wordViewModel: WordViewModel
    private lateinit var wordRecyclerViewAdapter:WordRecyclerViewAdapter
    private lateinit var binding:FragmentLearnBinding
    //private val binding by viewBinding(FragmentLearnBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //wordsDatabase= WordsDatabase.getWordsDatabase(requireContext())!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //binding= FragmentLearnBinding.inflate(inflater,container,false)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_learn,container,false)

        val application= requireNotNull(this.activity).application
        val dataSource=WordsDatabase.getWordsDatabase(application)?.wordsDao
        val viewModalFactory=dataSource?.let { WordViewModelFactory(it,application) }
        wordViewModel=viewModalFactory?.let {
            ViewModelProvider(this,it).get(WordViewModel::class.java)
        }!!

        wordViewModel.wordsList.observe(viewLifecycleOwner){
            wordList=it
            wordRecyclerViewAdapter= WordRecyclerViewAdapter(wordList)
            binding.adapter=wordRecyclerViewAdapter
        }
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayAllWords()
        //val list= arrayListOf<String>("Word","Word")//"Word","Word","","","","","","")

        binding.apply {
            addWordEFAB.setOnClickListener {
                addWordEFAB.hide()
                addWordView.visibility=View.VISIBLE
            }
            addWordButton.setOnClickListener{
                Snackbar.make(requireView(),"ok clicked",1000).show()
                addWordEFAB.show()
                //addWordEFAB.setIconResource(R.drawable.ok_icon)
                addWordView.visibility=View.GONE
                wordTextInput.editText?.text = null
            }

        }
    }

    private fun displayAllWords() {
        wordViewModel.wordsList.observe(viewLifecycleOwner){ wordsList ->
            wordList=wordsList
            binding.apply {
                if(wordList.isEmpty()){
                    Snackbar.make(requireView(),"There is no word appended",1000).show()
                }
                else {
                    wordListRecyclerView.layoutManager=
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                    wordListRecyclerView.setHasFixedSize(true)
                }
            }
        }

    }
}