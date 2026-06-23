data class Note(val content: String)
class Archive(val name: String) {
    val notes = mutableListOf<Note>()
}