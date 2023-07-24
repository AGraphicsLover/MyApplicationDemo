import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationdemo.ArticleData
import com.example.myapplicationdemo.R

class ArticleAdapter(private var dataList: MutableList<ArticleData> = mutableListOf()) :
  RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
    return ArticleViewHolder(view)
  }

  override fun getItemCount(): Int {
    return dataList.size
  }

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
    val article = dataList[position]
    holder.bind(article)
  }

  fun setData(newDataList: List<ArticleData>) {
    dataList = newDataList.toMutableList()
    notifyDataSetChanged()
  }

  //添加新加载的文章数据到已有数据列表中
  fun addArticles(newDataList: List<ArticleData>) {
    dataList.addAll(newDataList)
    notifyDataSetChanged()
  }

  inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
    private val authorTextView: TextView = itemView.findViewById(R.id.textAuthor)
    private val publishTimeTextView: TextView = itemView.findViewById(R.id.textPublishTime)

    fun bind(article: ArticleData) {
      titleTextView.text = HtmlCompat.fromHtml(article.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
      val authorInfo = if (article.author.isNotEmpty()) {
        itemView.context.getString(R.string.myString8, article.author)
      } else if (article.shareUser.isNotEmpty()) {
        itemView.context.getString(R.string.myString8, article.shareUser)
      } else {
        itemView.context.getString(R.string.myString9)
      }
      authorTextView.text = authorInfo
      publishTimeTextView.text = article.getFormattedPublishTime()

      itemView.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.link))
        itemView.context.startActivity(intent)
      }
    }
  }
}