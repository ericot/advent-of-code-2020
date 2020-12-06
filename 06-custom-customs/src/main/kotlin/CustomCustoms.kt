import java.io.File

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    var sum = 0
    val questions = mutableSetOf<Char>()
    loadResource("input.txt").forEachLine {
        if (it.isBlank()) {
            sum += questions.size
            questions.clear()
        } else {
            questions.addAll(it.toList())
        }
    }
    sum += questions.size
    println(sum)
}

fun partTwo() {
    var sum = 0
    var members = 0
    val questions = mutableMapOf<Char, Int>()
    loadResource("input.txt").forEachLine { line ->
        if (line.isBlank()) {
            sum += questions.filter { it.value == members }.count()
            members = 0
            questions.clear()
        } else {
            line.forEach {
                val count = questions.getOrDefault(it, 0)
                questions[it] = count + 1
            }
            members += 1
        }
    }
    sum += questions.filter { it.value == members }.count()
    println(sum)
}

fun loadResource(resource: String) =
    File(object {}.javaClass.getResource(resource).toURI())

