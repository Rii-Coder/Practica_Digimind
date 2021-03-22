package jorge.mendoza.mydigimind.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jorge.mendoza.mydigimind.R
import jorge.mendoza.mydigimind.Recordatorio
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recordatorio.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var adapterRecordatorio: RecordatorioAdapter? = null
    var recordatorios = ArrayList<Recordatorio>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        var context: Context?
        context = container?.context
        cargarRecordatorios()
        adapterRecordatorio = RecordatorioAdapter(context, recordatorios)
        var grid: GridView = GridView(context)
        grid.adapter = adapterRecordatorio


        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    fun cargarRecordatorios(){
        recordatorios.add(Recordatorio("Monday","10:00 am","Wake up"))
    }

}




class RecordatorioAdapter: BaseAdapter {
    var recordatorio = ArrayList<Recordatorio>()
    var context: Context? = null

    constructor(context: Context?, recordatorio: ArrayList<Recordatorio>){
        this.context = context
        this.recordatorio= recordatorio
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var recordatorio = recordatorio[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.recordatorio, null)
        vista.txtNombreRecordatorio.setText(recordatorio.nombre)
        vista.txtDiasRecordatorio.setText(recordatorio.dias)
        vista.txtTiempoRecordatorio.setText(recordatorio.tiempo)

        /*
        vista.iv_pelicula.setOnClickListener(){
            var intent = Intent(context, DetallePelicula::class.java)
            intent.putExtra("nombre", pelicula.titulo)
            intent.putExtra("image", pelicula.image)
            intent.putExtra("header", pelicula.header)
            intent.putExtra("sinopsis", pelicula.sinopsis)
            context!!.startActivity(intent)
        }
        */
        return vista
    }

    override fun getItem(position: Int): Any {
        return recordatorio[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recordatorio.size
    }
}