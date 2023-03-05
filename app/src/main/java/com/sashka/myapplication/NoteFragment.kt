package com.sashka.myapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sashka.myapplication.databinding.FragmentNoteBinding

class NoteFragment : Fragment(), NoteAdapter.IOnItem {
    private var _binding: FragmentNoteBinding?= null
    private  val binding get() = _binding!!
    private val adapter: NoteAdapter by lazy {
        NoteAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getList()
        binding.recycler.adapter = adapter
        binding.add.setOnClickListener{
            findNavController().navigateUp()
            findNavController().navigate(R.id.addFragment)
        }

    }

    private fun getList() {
        if (arguments != null) {
            val title = arguments?.getString("title").toString()
            val desc = arguments?.getString("desc").toString()
            val data = arguments?.getString("data").toString()
            val image = arguments?.getString("image")

            adapter.setList(NoteModel(title, desc, data, image))
        }
    }

    override fun delete(pos: Int) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.delete)
        alert.setMessage("Are you sure to delete")
        alert.setNegativeButton("Cancel", null)
        alert.setPositiveButton("Delete"){_, _->
            adapter.remove(pos)
        }
        alert.show()
    }

    override fun edit(pos: Int) {
        val bundle = Bundle()
        bundle.putSerializable("edit", adapter.getItem(pos))
        bundle.putInt("pos", pos)
        findNavController().navigate(R.id.addFragment, bundle)
    }

    override fun share(pos: Int) {

    }
}