import java.io.PrintStream

fun main() {
    System.setOut(PrintStream(System.out, true, "UTF-8"))
    NoteApp().start()
}