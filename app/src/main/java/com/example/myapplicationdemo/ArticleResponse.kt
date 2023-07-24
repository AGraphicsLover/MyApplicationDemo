import com.example.myapplicationdemo.ArticleData

data class ArticleResponse(
  val data: ArticleList,
  val errorCode: Int,
  val errorMsg: String
)

data class ArticleList(
  val datas: List<ArticleData>
)
