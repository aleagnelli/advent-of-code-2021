import java.io.File

typealias Grid<A> = List<List<A>>

fun <A, B> Pair<A, B>.swap(): Pair<B, A> = second to first

fun <A, B> Map<A, B>.update(key: A, default: B, fb: (B) -> B): Map<A, B> =
    toMutableMap().also { it[key] = it[key]?.let(fb) ?: default }

fun <A> List<A>.update(index: Int, value: A): List<A> =
    toMutableList().also { it[index] = value }

fun <A> Grid<A>.update(index: Int, subIndex: Int, value: A): Grid<A> =
    update(index, this[index].update(subIndex, value))

fun readText(cls: Class<*>, name: String): String {
    val url = cls.getResource(name) ?: error("file: $name not found")
    return File(url.toURI()).readText()
}

fun readLines(cls: Class<*>, name: String): List<String> {
    val url = cls.getResource(name) ?: error("file: $name not found")
    return File(url.toURI()).readLines()
}