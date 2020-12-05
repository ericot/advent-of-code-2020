import java.io.File

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    println(getSeatIds(0, 127).maxOrNull())
}

fun partTwo() {
    val seatIds = getSeatIds(1, 126).sorted()
    var prev = 0
    for (id in seatIds) {
        if (prev + 2 == id) {
            println(id - 1)
        }
        prev = id
    }
}

fun getSeatIds(startRow: Int, stopRow: Int): List<Int> {
    val seatIds = mutableListOf<Int>()
    for (v in readData()) {
        val row = findSeat(v.dropLast(3), startRow, stopRow, 'F', 'B')
        val column = findSeat(v.drop(6), 0, 8, 'L', 'R')
        seatIds.add(row * 8 + column)
    }
    return seatIds
}

fun findSeat(spec: String, start: Int, stop: Int, lowerChar: Char, upperChar: Char): Int {
    var range = start..stop
    for (char in spec) {
        when (char) {
            lowerChar -> range = range.lower()
            upperChar -> range = range.upper()
        }
    }
    return range.first
}

fun IntRange.lower() = IntRange(this.first, this.last - this.count() / 2)
fun IntRange.upper() = IntRange(this.first + this.count() / 2, this.last)

fun readData(): List<String> {
    val data = mutableListOf<String>()
    loadResource("input.txt").forEachLine { data.add(it) }
    return data
}

fun loadResource(resource: String) =
    File(object {}.javaClass.getResource(resource).toURI())

