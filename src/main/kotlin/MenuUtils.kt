import java.util.Scanner

class MenuUtils(private val scanner: Scanner) {
    fun <T> showItemMenu(
        title: String,
        items: List<T>,
        displayItem: (T) -> String,
        createItem: () -> T?,
        onSelect: (T) -> Unit,
        onBack: () -> Unit,
        onExit: () -> Unit,
        emptyMessage: String
    ) {
        while (true) {
            printMenu(title, items, displayItem, emptyMessage)

            when (val choice = readInt()) {
                in 1..items.size -> onSelect(items[choice - 1])
                items.size + 1 -> createItem()
                items.size + 2 -> {
                    onBack()
                    return
                }
                items.size + 3 -> {
                    onExit()
                    return
                }
                else -> println("Нет такого пункта. Выберите пункт из списка")
            }
        }
    }

    fun showRootMenu(title: String, options: List<Pair<String, () -> Unit>>) {
        while (true) {
            println("\n-= $title =-")
            options.forEachIndexed { index, option ->
                println("${index + 1}. ${option.first}")
            }
            print("Выберите пункт меню: ")

            when (val choice = readInt()) {
                in 1..options.size -> options[choice - 1].second()
                else -> println("Нет такого пункта. Выберите пункт из списка")
            }
        }
    }

    private fun <T> printMenu(
        title: String,
        items: List<T>,
        displayItem: (T) -> String,
        emptyMessage: String
    ) {
        println("\n-= $title =-")
        if (items.isEmpty()) {
            println(emptyMessage)
        } else {
            items.forEachIndexed { index, item ->
                println("${index + 1}. ${displayItem(item)}")
            }
        }
        println("${items.size + 1}. Создать")
        println("${items.size + 2}. Назад")
        println("${items.size + 3}. Выход из приложения")
        print("Выберите пункт меню: ")
    }

    private fun readInt(): Int {
        return try {
            scanner.nextLine().toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }

    fun readNonEmptyString(prompt: String, errorMessage: String): String? {
        print(prompt)
        val input = scanner.nextLine().trim()
        if (input.isEmpty()) {
            println(errorMessage)
            return null
        }
        return input
    }

    fun readString(prompt: String, errorMessage: String): String? {
        print(prompt)
        val input = scanner.nextLine().trim()
        if (input.isEmpty()) {
            println(errorMessage)
            return null
        }
        return input
    }

    fun waitForEnter() {
        println("\nНажмите Enter, чтобы закрыть заметку")
        scanner.nextLine()
    }
}