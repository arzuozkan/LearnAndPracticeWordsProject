package com.useryoo.learnandpracticewordsproject.ui.main.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.useryoo.learnandpracticewordsproject.R
import com.useryoo.learnandpracticewordsproject.data.database.WordModel
import com.useryoo.learnandpracticewordsproject.data.database.WordsDatabase
import com.useryoo.learnandpracticewordsproject.databinding.FragmentLearnBinding
import com.useryoo.learnandpracticewordsproject.ui.main.adapter.WordsAdapter
import com.useryoo.learnandpracticewordsproject.ui.main.viewModel.WordViewModel
import com.useryoo.learnandpracticewordsproject.ui.main.viewModel.WordViewModelFactory
import kotlin.math.hypot


class LearnFragment : Fragment(R.layout.fragment_learn) {
    private lateinit var wordList:List<WordModel>
    private lateinit var wordsDatabase: WordsDatabase
    private lateinit var wordViewModel: WordViewModel
    private lateinit var wordsAdapter:WordsAdapter
    private lateinit var binding:FragmentLearnBinding
    //private val binding by viewBinding(FragmentLearnBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wordsDatabase= WordsDatabase.getWordsDatabase(requireContext())!!
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
            ViewModelProvider(this,it)[WordViewModel::class.java]
        }!!

        wordViewModel.wordsList.observe(viewLifecycleOwner){
            wordList=it
            wordsAdapter= WordsAdapter()

            binding.adapter=wordsAdapter
            //wordRecyclerViewAdapter.updateWordList(wordList)

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
                //addWordEFAB.hide()
                //addWordView.visibility=View.VISIBLE
                circularRevealAnim(addWordEFAB,addWordView)
            }
            addWordButton.setOnClickListener{
                val wordInput=wordTextInput.editText?.text.toString()
                //Snackbar.make(requireView(),"ok clicked",2000).show()
                if(wordInput=="" && wordInput.length<10){
                    Snackbar.make(requireView(),"Please enter a word",2000).show()
                }
                else{
                    wordViewModel.wordAdd(
                    WordModel(word=wordInput,
                        wordDef = "Word Meaning",
                        wordExample = "Example")
                )
                    Snackbar.make(requireView(),"yeni kelime eklendi",2000).show()
                    circularRevealAnim(addWordView,addWordEFAB)
                    //addWordEFAB.setIconResource(R.drawable.ok_icon)
                    //addWordView.visibility=View.GONE
                    wordTextInput.editText?.text = null}

            }

        }
    }

    private fun displayAllWords() {
        wordViewModel.wordsList.observe(viewLifecycleOwner){ wordsList ->
            wordList=wordsList
            binding.apply {
                if(wordList.isEmpty()){
                    Snackbar.make(requireView(),"There is no word appended",10000).show()
                }
                else {
                    wordListRecyclerView.layoutManager=
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL,true)
                    wordListRecyclerView.setHasFixedSize(true)
                }
            }
        }

    }
    private fun circularRevealAnim(
        showedView:View,
        hiddenView:View){
        val cx=showedView.width/2
        val cy=showedView.height/2

        val initialRadius= hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim=ViewAnimationUtils.createCircularReveal(
            showedView,
            cx,
            cy,
            initialRadius,
            0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                showedView.visibility=View.GONE
                hiddenView.visibility=View.VISIBLE
                //hidedView.show()
            }

        })

        anim.start()

    }
}