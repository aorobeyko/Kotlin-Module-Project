import java.util.Scanner

class NoteApp {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)
    private val menu = MenuUtils(scanner)

    fun start() {
        menu.showRootMenu(
            "Главное меню",
            listOf(
                "Архивы" to { showArchivesMenu() },
                "Выход из приложения" to { println("Всего хорошего!"); System.exit(0) }
            )
        )
    }

    private fun showArchivesMenu() {
        menu.showItemMenu(
            title = "Архивы",
            items = archives,
            displayItem = { it.name },
            createItem = { createArchive() },
            onSelect = { showNotesMenu(it) },
            onBack = { start() },
            onExit = { println("Всего хорошего!"); System.exit(0) },
            emptyMessage = "Нет архивов"
        )
    }

    private fun showNotesMenu(archive: Archive) {
        menu.showItemMenu(
            title = "Архив: ${archive.name}",
            items = archive.notes,
            displayItem = { it.name },
            createItem = { createNote(archive) },
            onSelect = { showNote(it) },
            onBack = { showArchivesMenu() },
            onExit = { println("Всего хорошего!"); System.exit(0) },
            emptyMessage = "Нет заметок"
        )
    }

    private fun showNote(note: Note) {
        println("\n-= ${note.name} =-")
        println(note.content)
        menu.waitForEnter()
    }

    private fun createArchive(): Archive? {
        //
        val name = menu.readNonEmptyString(
            "Введите название архива: ",
            "Название не может быть пустым."
        ) ?: return null

        val archive = Archive(name)
        archives.add(archive)
        println("Архив '$name' создан.")
        return archive
    }

    private fun createNote(archive: Archive): Note? {
        val name = menu.readNonEmptyString(
            "Введите название заметки: ",
            "Название не может быть пустым."
        ) ?: return null

        val content = menu.readString(
            "Введите текст заметки: ",
            "Текст заметки не может быть пустым."
        ) ?: return null

        val note = Note(name, content)
        archive.notes.add(note)
        println("Заметка '$name' создана.")
        return note
    }
}