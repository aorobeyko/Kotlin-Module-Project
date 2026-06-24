data class Note(val name: String, val content: String)
class Archive(val name: String) {
    val notes = mutableListOf<Note>()
}