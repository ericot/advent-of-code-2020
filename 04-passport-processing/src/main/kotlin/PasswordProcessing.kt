import java.io.File

fun main() {
    println(readPassportData().filter { it.validate() }.count())
    println(readPassportData().filter { it.validate(true) }.count())
}

typealias PassportField = Pair<String, String>

data class Passport(
    val fields: MutableList<PassportField> = mutableListOf()
) {
    private val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun validate(includeFields: Boolean = false): Boolean {
        val hasRequiredFields = fields.map(PassportField::first).containsAll(requiredFields)

        if (hasRequiredFields && includeFields) {
            return fields.none { !isFieldValid(it.first, it.second) }
        }
        return hasRequiredFields
    }

    private fun isFieldValid(field: String, value: String) = when (field) {
        "byr" -> value.toIntOrNull()?.let { (1920..2002).contains(it) } ?: false
        "iyr" -> value.toIntOrNull()?.let { (2010..2020).contains(it) } ?: false
        "eyr" -> value.toIntOrNull()?.let { (2020..2030).contains(it) } ?: false
        "hgt" -> when {
            value.endsWith("cm") -> (150..193).contains(value.dropLast(2).toInt())
            value.endsWith("in") -> (59..76).contains(value.dropLast(2).toInt())
            else -> false
        }
        "hcl" -> value.matches("""#[0-9a-f]{6}""".toRegex())
        "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
        "pid" -> value.toIntOrNull()?.let { value.length == 9 } ?: false
        "cid" -> true
        else -> false
    }
}

fun readPassportData(): List<Passport> {
    val passports = mutableListOf<Passport>()
    passports.add(Passport())

    var currentPassport = passports.last()
    loadResource("input.txt").forEachLine { line ->
        if (line.isBlank()) {
            passports.add(Passport())
            currentPassport = passports.last()
        } else {
            """(\w+):(#?\w+)""".toRegex().findAll(line).forEach {
                currentPassport.fields.add(PassportField(it.groupValues[1], it.groupValues[2]))
            }
        }
    }
    return passports
}

fun loadResource(resource: String) =
    File(object {}.javaClass.getResource(resource).toURI())

