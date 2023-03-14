package com.joma.notes

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.joma.notes.databinding.FragmentNoteBinding

 class NoteFragment : Fragment(), NoteAdapter.IOnItem {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: NoteAdapter
    lateinit var navController: NavController
    val  mainList = ArrayList<NoteFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
//        navController = navHostFragment.navController

        adapter = NoteAdapter(this)
        binding.recycler.adapter = adapter
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }
        adapter.setList((requireActivity() as MainActivity).list)
        if (adapter.itemCount == 0) {
            binding.nothingFound.visibility = View.VISIBLE
//            binding.nothingFound.isVisible = true
        }
        binding.recycler.adapter = adapter
        binding.btnSort.setOnClickListener {
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    val position: Int = getAdapterPos()
                    val context = v.context
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("main_key", mainList.get(position).getMovie())
                    context.startActivity(intent)
                }
            }
            fun delete(pos: Int) {
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle(R.string.delete)
                alert.setMessage("Are you sure to delete")
                alert.setNegativeButton("Cancel", null)
                alert.setPositiveButton("Delete") { _, _ ->
                    adapter.remove(pos)
                }
                alert.show()
            }

            fun edit(pos: Int) {
                val bundle = Bundle()
                bundle.putSerializable("edit", adapter.getItem(pos))
//        navController.navigate(R.id.addFragment, bundle)
            }

            fun share(pos: Int) {

            }
            binding.recycler.adapter = adapter
            binding.btnSort.setOnClickListener {
                object : View.OnClickListener {
                    override fun onClick(v: View) {
                        val position: Int = getAdapterPos()
                        val context = v.context
                        val intent = Intent(context, MainActivity::class.java)
                        intent.putExtra("main_key", mainList.get(position).getMovie())
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

     override fun delete(pos: Int) {
         TODO("Not yet implemented")
     }

     override fun edit(pos: Int) {
         TODO("Not yet implemented")
     }

     override fun share(pos: Int) {
         TODO("Not yet implemented")
     }

     override fun getAdapterPos(): Int {
         TODO("Not yet implemented")
     }

     override fun getMovie(): String? {
         TODO("Not yet implemented")
     }
 }