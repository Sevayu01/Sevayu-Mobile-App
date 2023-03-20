import android.graphics.Color
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevayu.R
import com.example.sevayu.models.Appointments

class AppointmentAdapter(private val mList: List<Appointments>,val rainbow:Array<String>) : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.hospital.text = ItemsViewModel.hospital
        holder.dept.text = ItemsViewModel.dept
        holder.time.text = ItemsViewModel.time
        holder.date.text = ItemsViewModel.date

        holder.itemView.setBackgroundColor(Color.parseColor(rainbow[position % rainbow.size] ))


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val hospital: TextView = itemView.findViewById(R.id.rv_hospital)
        val dept: TextView = itemView.findViewById(R.id.rv_department)
        val time: TextView = itemView.findViewById(R.id.rv_time)
        val date: TextView = itemView.findViewById(R.id.rv_date)
    }
}
