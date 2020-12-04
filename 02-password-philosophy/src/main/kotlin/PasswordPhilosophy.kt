import java.io.File

val passwordRegex = """(\d+)-(\d+)\s(.):\s(.*)""".toRegex()

fun main() {
    println(partOne())
    println(partTwo())
}

fun partOne() =
    getInput().filter { line -> line.password.count { it == line.letter } in line.num1..line.num2 }.size

fun partTwo() =
    getInput().filter { line -> line.hasLetterAtIndex(line.num1).xor(line.hasLetterAtIndex(line.num2)) }.size

data class Line(
    val num1: Int,
    val num2: Int,
    val letter: Char,
    val password: String,
) {
    fun hasLetterAtIndex(index: Int) = this.password[index - 1] == this.letter
}

fun getInput(): List<Line> {
    val data = mutableListOf<Line>()
    val input = File(object {}.javaClass.getResource("input.txt").toURI())
    input.forEachLine {
        val matches = passwordRegex.matchEntire(it)!!.groupValues
        data.add(Line(matches[1].toInt(), matches[2].toInt(), matches[3].single(), matches[4]))
    }
    return data
}
