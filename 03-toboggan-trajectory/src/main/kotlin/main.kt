import java.io.File

fun main() {
    val map = readInput()
    println(partOne(map))
    println(partTwo(map))
}

fun partOne(map: List<String>) =
    countTreesForSlope(map, 3, 1)

fun partTwo(map: List<String>) = listOf(
    countTreesForSlope(map, 1, 1),
    countTreesForSlope(map, 3, 1),
    countTreesForSlope(map, 5, 1),
    countTreesForSlope(map, 7, 1),
    countTreesForSlope(map, 1, 2),
).reduce(Long::times)

fun countTreesForSlope(map: List<String>, right: Int, down: Int): Long {
    var counter = 0L
    val mapRows = map.filterIndexed { index, _ -> index % down == 0 }
    for ((index, row) in mapRows.withIndex()) {
        counter += if (row[(index * right) % row.length] == '#') 1 else 0
    }
    return counter
}

fun readInput(): List<String> {
    val data = mutableListOf<String>()
    val input = File(object {}.javaClass.getResource("input.txt").toURI())
    input.forEachLine { data.add(it) }
    return data
}
