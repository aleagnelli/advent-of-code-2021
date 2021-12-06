import java.io.File

fun <A, B> Pair<A, B>.swap(): Pair<B, A> = second to first

fun <A, B> Map<A, B>.update(key: A, default: B, fb: (B) -> B): Map<A, B> =
    toMutableMap().also { it[key] = it[key]?.let(fb) ?: default }

fun readText(cls: Class<*>, name: String): String {
    val url = cls.getResource(name) ?: error("file: $name not found")
    return File(url.toURI()).readText()
}

fun readLines(cls: Class<*>, name: String): List<String> {
    val url = cls.getResource(name) ?: error("file: $name not found")
    return File(url.toURI()).readLines()
}