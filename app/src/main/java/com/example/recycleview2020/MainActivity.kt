package com.example.recycleview2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview2020.model.Guitarras
import kotlinx.android.synthetic.main.activity_main.*

//https://www.youtube.com/watch?v=QIVbnR9pQfY

class MainActivity : AppCompatActivity(),RecyclerAdapter.OnGuitarraClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

    }


    private fun setupRecyclerView(){
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.addItemDecoration((DividerItemDecoration(this,DividerItemDecoration.VERTICAL)))
        val listGuitarras:List<Guitarras> = listOf (
        Guitarras("Fender", "Stratocaster","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBhLjFpMLYv4VTF_6rjVBl3DPrzDfwE5NZYVOsqpuz2i0p0TJnJNAIbYRtUrE&usqp=CAc"),
        Guitarras("Gibson","Les Paul","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQNPmiZPQx7GPUI3hBcZxBYLAgSQY0aj2qftH8v7LqL1CsUv4J6-KwvUSeNqfggSPU3llw8EU_y&usqp=CAc"),
        Guitarras("Gibson","SG","https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTE0vOsdSad67wj84bMea8RnOweoNvuuYpf_C5bza-5svfdHwlrNcO3Byl6nDib&usqp=CAc"),
        Guitarras("Gibson","ES345","https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcR4MY6ejcuObGDIG-Evq1FtL3ET4LupmC3GN4-nIiNa9Z5x1JzVBWFz8KeSRjlh&usqp=CAc"),
        Guitarras("Gibson","Flying","https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRmG3TUoSHTu0AYnfCs3A5YnwSekyEpHCTcYdm-RSQz9JKTdfU_QNw0vg9-r29_sggvtSLYwvfBMVY&usqp=CAc"),
        Guitarras("Fender","Telecaster","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHAAAABwCAMAAADxPgR5AAAAhFBMVEVHcEzVgADKeQDDdwDOfACVWgAQEwTGeADLewACAQC8dAAHBQADAQCbYAGDUgK0awCbXQC1bADHeAALCAAQEgXGdQCybACLVAB0SAAREAPJeQHEdgCpZgELCAG3bwDJeQAgFwGTWwHSfgAPDwIbEwEfFgFhPAAuHQCaXwBdOQBgOwBbOgHjbeLyAAAALHRSTlMAcnyzqIEurKzvuODrdRGVi4RE0iGJppqvRCVjUcE1nnhgVVmrjrnNt8qZW+gaV2gAAASLSURBVGiB3ZrbbqMwEIYBYzAQsB1OIQkJFHJo+v7vtzMG0q60V5U9Fzuq2kRV9On/52DH2PN+F0Wxl0Hxyw//JqpISqkpgefpS0o6XlGdWkWpsOp4LhvCHBaXYZAjIS/6aKSidDTiX7KVdJYWhRrqVl7IeNEoZa3kmQ7IOwWW7qmAVSFfUqr6SMQrqjzXkquaiAcKx4nLmi6FVSVbrWu6SRp95Vxrykl6Gnit5YkOCCth29KlEBQ2+n5vCIFe3t4/n5TAYjyfz1Rtj3GUdV2TDTYEah0QA2tiIIcgBSodaErgRdeaOod1QKqQK65oLQ18WoV12xID64AWqIj78FIT53BPDbwoneekCmG1ILa0VT51DkmBZ2wLyj3NOQCFH4TAD+CRAs+c2NKPQCliSyGJxJbmxJYSV+lJaz+gBI5a55rS0hN1lY7Qhjnh2R4oJJ40J6xSwu/4Xo59SAn0MYgV8jwnBAYHP2h9QiCuTpxUIbbF/63QJ1aoqBXWfqtpFFbLH4WThrvHFdUUmRc1SZUeZZo2zxciFW8VV+//fEVOeOESz1e15+rFudqeBDVt4+J7Ri0WoIifqWn87Yns/lBmgX3eceUBMW04b78VBo9dcrB/eLrfgOI6l2Cp2oCFzHbJw76np7fCPnn44GkQLD0yHpJd0tvfb2xA8UqSh1IvKFOzi6qupRsgWhqjoWigUq1SXEI3fDVzsnNi6WVVeE8MEHj60TyfzQN4TorGW3jGwORgYlf2ZYK8XeLisVCAEkWLBpY9RNlnyYLb7WYXG6oLQyA6WnZFFUWFN2ULzo2jnnfFsjElkoC+suw3fcnOwaDxTGOIaw8KQWOZQSQrLrm7ObI5slBIU5MILDcgvG0dPdC/CqgZLNJvhah3l7hx1AAP/1KYuVqKDRAV7n7mEMIVMBQ4Z9DH7A00RevI0gvbFGYILLMlgfDTunmUGMRbDjMT6K0pocTJoPGKGIASAZ93E5+fn0i7DX3i5P6Xz4AY32DE5N0UmVDZnM1VF/WtA4kgECVCsBGa4jYAMId3edRFV1Hbl/jBVmCcjj0uTOU85SxmeXeLGiHsnxKFi0L4lY7VAM72ZZanLM2npGpiYf3myZ5tlsZsxPx1t3I2wOhWgcLQ9nWlOv6Rw6VkomoBdmipsDxtChEvxHhTGFUAZAYICmNxtVs2i6MLMh2nYbhBDG+F2DF2PQ0MaiEaIMQ0GWDXdQAUwm4rXr+BoPA2z3M/z8PbUga7D6t1iikUGxI6oZsmENZx7EPIZoMbuqtVIBOmXkyw52GNJ1gJwKgxW1abVXNEoNg8jVlqAvQZYGUfWKxAsRbqOxjLq9VSu0CEbUnEF81b61I0sbALhO1MvDr61hlj97GUR5VpC7tF4/liIYnvYl0VPruhg2yGod3ZdmSrNDNN4x/BGo6TLbQ8abzapFAs60X4QyWgTI1avzjI1t6Hb1B/KdyOGqwv+QVb61SECA7/woXMwe3dQjJjaShW2hWx61mRm9vC5yYFJio0IoFoaIK5OzmtzrmWjRlr63hrJNEViWEqil/VyR/DgF5FR4+YBAAAAABJRU5ErkJggg=="),
        Guitarras("Gretsch ","Streamliner","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTVclYqUurOKG8ABqE66bz3CKPNg_ailXjCCQoeAyl7dUFYoo3-kzbpW9A3gw&usqp=CAc")




        )
        recyclerView.adapter=RecyclerAdapter(this,listGuitarras,this)

    }

    override fun onImageClick(imagen: String) {
        val intent=Intent(this,MainActivity2::class.java)
        intent.putExtra("imageURL",imagen)
        startActivity(intent)

    }

    override fun onItemClick(marca: String, modelo: String) {
        Toast.makeText(this, marca+" "+modelo, Toast.LENGTH_SHORT).show()
    }


}
