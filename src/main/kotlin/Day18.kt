object Day18 {
    sealed interface SFNumber {
        fun addFromLeft(v: Int): SFNumber
        fun addFromRight(v: Int): SFNumber
        operator fun plus(other: SFNumber): SFNumber
        fun magnitude(): Int
    }

    data class SFPair(val level: Int, val left: SFNumber, val right: SFNumber) : SFNumber {
        override fun addFromLeft(v: Int): SFNumber = copy(left = left.addFromLeft(v))
        override fun addFromRight(v: Int): SFNumber = copy(right = right.addFromRight(v))
        override fun plus(other: SFNumber) = SFPair(level, increaseLevel(this), increaseLevel(other))
        override fun magnitude(): Int = 3 * left.magnitude() + 2 * right.magnitude()
    }

    data class SFLeaf(val level: Int, val value: Int) : SFNumber {
        override fun addFromLeft(v: Int): SFNumber = copy(value = value + v)
        override fun addFromRight(v: Int): SFNumber = copy(value = value + v)
        override fun plus(other: SFNumber) = throw UnsupportedOperationException("cannot add a leaf")
        override fun magnitude(): Int = value
    }

    fun parse(input: String): SFNumber = parse(input, 1, 0).first
    fun parse(input: String, level: Int, startingPos: Int): Pair<SFNumber, Int> {
        checkChar(input, startingPos, '[')
        val (left, leftOffset) = readBranch(input, level + 1, startingPos + 1)
        val commaPos = leftOffset + 1
        checkChar(input, commaPos, ',')
        val (right, rightOffset) = readBranch(input, level + 1, commaPos + 1)
        val number = SFPair(level, left, right)
        checkChar(input, rightOffset + 1, ']')
        return number to rightOffset + 1
    }

    private fun checkChar(input: String, pos: Int, c: Char) {
        if (input[pos] != c) error("unexpected token '${input[pos]}', expected '$c'")
    }

    private fun readBranch(input: String, level: Int, pos: Int): Pair<SFNumber, Int> {
        return if (input[pos].isDigit()) {
            readNumber(input, level, pos)
        } else if (input[pos] == '[') {
            parse(input, level, pos)
        } else {
            error("unexpected")
        }
    }

    private fun readNumber(input: String, level: Int, pos: Int): Pair<SFLeaf, Int> {
        var numberOffset = 0
        while (input[pos + numberOffset].isDigit()) numberOffset++
        val simpleNumber = input.substring(pos, pos + numberOffset).toInt()
        return SFLeaf(level, simpleNumber) to pos + numberOffset - 1
    }

    fun increaseLevel(number: SFNumber): SFNumber {
        return when (number) {
            is SFLeaf -> number.copy(level = number.level + 1)
            is SFPair -> number.copy(
                level = number.level + 1,
                left = increaseLevel(number.left),
                right = increaseLevel(number.right)
            )
        }
    }

    fun split(number: SFNumber): SFNumber? {
        return when (number) {
            is SFLeaf -> if (number.value >= 10) SFPair(
                number.level,
                SFLeaf(number.level + 1, number.value / 2),
                SFLeaf(number.level + 1, number.value - number.value / 2)
            ) else null
            is SFPair -> split(number.left)?.let { newLeft -> number.copy(left = newLeft) }
                ?: split(number.right)?.let { newRight -> number.copy(right = newRight) }
        }
    }

    fun explode(number: SFNumber): Pair<SFNumber, Pair<Int, Int>>? {
        return when (number) {
            is SFPair -> explodePair(number)
            is SFLeaf -> null
        }
    }

    private fun explodePair(number: SFPair): Pair<SFNumber, Pair<Int, Int>>? {
        if (number.level == 5 && number.left is SFLeaf && number.right is SFLeaf) {
            return Pair(SFLeaf(number.level, 0), Pair(number.left.value, number.right.value))
        }
        return explode(number.left)?.let { (left, lRem) ->
            Pair(number.copy(left = left, right = number.right.addFromLeft(lRem.second)), Pair(lRem.first, 0))
        } ?: explode(number.right)?.let { (right, rRem) ->
            Pair(number.copy(left = number.left.addFromRight(rRem.first), right = right), Pair(0, rRem.second))
        }
    }

    private fun reduce(number: SFNumber): SFNumber {
        val res1 = explode(number)?.let { reduce(it.first) } ?: number
        return split(res1)?.let { reduce(it) } ?: res1
    }

    fun assignment(input: List<String>): SFNumber = input.map(::parse).reduce { a, b -> reduce(a + b) }

    fun part1(input: List<String>): Int = assignment(input).magnitude()

    fun part2(input: List<String>): Int {
        val allCombs = input.flatMap { first -> input.map { second -> Pair(first, second) } }
        val allCombsSwapped = allCombs.map { it.swap() }
        return (allCombs + allCombsSwapped).toSet().maxOf { assignment(listOf(it.first, it.second)).magnitude() }
    }
}
