import java.io.File

const val target = 2020

fun main() {
    partOne(target)
    partTwo(target)
}

fun partOne(target: Int) {
    val data = getSortedInput()
    for (v in data) {
        if (data.contains(target - v)) {
            println(v * (target - v))
            return
        }
    }
}

fun partTwo(target: Int) {
    val data = getSortedInput()
    for ((i, v1) in data.withIndex()) {
        for ((j, v2) in data.drop(i).withIndex()) {
            for (v3 in data.drop(j)) {
                if (v1 + v2 + v3 == target) {
                    println(v1 * v2 * v3)
                    return
                }
            }
        }
    }
}

fun getSortedInput(): List<Int> {
    val data = mutableListOf<Int>()
    val input = File(object {}.javaClass.getResource("input.txt").toURI())
    input.forEachLine { data.add(it.toInt()) }
    return data.sorted()
}
