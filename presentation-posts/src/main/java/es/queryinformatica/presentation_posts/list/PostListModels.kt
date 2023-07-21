package es.queryinformatica.presentation_posts.list


data class PostListItemModel(
    val id: Long,
    val userId: Long,
    val authorName: String,
    val title: String,
)

data class PostListModel (
    val headerText: String = "",
    val posts: List<PostListItemModel> = listOf(),
)