package jorge.mendoza.mydigimind.ui.home

import Task
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
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.recordatorio.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {

    var tasks = ArrayList<Task>()
    private var adaptador: AdaptadorTareas? = null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first){
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        root.gridView.adapter = adaptador

        return root
    }

    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"),"17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday, Saturday"),"17:00"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"),"14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"),"11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"),"13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"),"10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"),"12:00"))
    }

    private class AdaptadorTareas: BaseAdapter{
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks.get(position)
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.task_view,null)

            vista.tv_title.setText(task.title)
            vista.tv_time.setText(task.time)
            vista.tv_days.setText(task.days.toString())

            return vista
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }


    }

}