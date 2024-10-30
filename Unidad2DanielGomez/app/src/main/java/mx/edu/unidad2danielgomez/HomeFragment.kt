package mx.edu.unidad2danielgomez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.unidad2danielgomez.adapters.MyAdapter
import mx.edu.unidad2danielgomez.models.MyDataModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var adapter: P4MenuActivity.MyAdapter
    private lateinit var myDataList: List<MyDataModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myDataList = loadData()
        adapter = MyAdapter(myDataList)
        binding.recyclerView.adapter = adapter
    }


    private fun loadData(): List<MyDataModel> {
        return listOf(
            MyDataModel("Item 1", "Descripción del Item 1"),
            MyDataModel("Item 2", "Descripción del Item 2"),
            MyDataModel("Item 3", "Descripción del Item 3")
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}