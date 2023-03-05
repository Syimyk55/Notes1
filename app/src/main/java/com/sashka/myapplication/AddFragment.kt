package com.sashka.myapplication

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sashka.myapplication.databinding.FragmentAddBinding

@Suppress("DEPRECATION")
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding?= null
    private  val binding get() = _binding!!
     var imageUri:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            binding.save.text = "edit"
            var mode = arguments?.getSerializable("edit") as NoteModel
            binding.etTitle.setText(mode.title)
            binding.etDesk.setText(mode.title)
            binding.etDate.setText(mode.title)
            binding.save.setOnClickListener {
                val noteModel = NoteModel(
                    binding.etTitle.text.toString(),
                    binding.etDesk.text.toString(),
                    binding.etDate.text.toString(),
                    imageUri
                )
//                (requireActivity() as MainActivity).list.add(noteModel)
//                requireActivity().supportFragmentManager
//                    .beginTransaction().replace(
//                        R.id.nav_host_fragment, NoteFragment()
//                    ).commit()
                val bundle = Bundle()


            }
        } else {
            binding.save.setOnClickListener {
                    val title = binding.etTitle.text.toString()
                    val desc = binding.etDesk.text.toString()
                    val data = binding.etDate.text.toString()
                    val image = imageUri

//                (requireActivity() as MainActivity).list.add(noteModel)
//                requireActivity().supportFragmentManager
//                    .beginTransaction().replace(
//                        R.id.nav_host_fragment, NoteFragment()
//                    ).commit()
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("desc", desc)
                bundle.putString("data", data)
                bundle.putString("image", image)
                findNavController().navigate(R.id.addFragment)
            }

        }

        binding.imageAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == RESULT_OK) {
                if (requestCode == 1) {
                    Glide.with(requireContext()).load(data?.data).into(binding.imageAdd)
                    binding.imageAdd.setPadding(0, 0, 0, 0)
                    imageUri = "${data?.data}"
                }

            }
        }
    }
}