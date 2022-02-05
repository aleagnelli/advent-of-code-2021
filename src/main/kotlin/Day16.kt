object Day16 {
    private val hexToBinary = mapOf(
        '0' to "0000",
        '1' to "0001",
        '2' to "0010",
        '3' to "0011",
        '4' to "0100",
        '5' to "0101",
        '6' to "0110",
        '7' to "0111",
        '8' to "1000",
        '9' to "1001",
        'A' to "1010",
        'B' to "1011",
        'C' to "1100",
        'D' to "1101",
        'E' to "1110",
        'F' to "1111"
    )

    fun String.hexToBinary() = this.toCharArray().joinToString(separator = "") { hexToBinary[it]!! }

    object PacketParser {
        fun parse(binary: String): Pair<Packet, String> {
            val version = binary.take(3).toLong(2)
            return when (val type = binary.substring(3, 6).toInt(2)) {
                4 -> parseLiteral(version, type, binary.substring(6))
                else -> parseOperator(version, type, binary.substring(6))
            }
        }

        private fun parseOperator(version: Long, type: Int, content: String): Pair<Operator, String> {
            val lengthType = content.first()
            if (lengthType == '0') {
                val lengthBits = 15
                val length = content.drop(1).take(lengthBits).toInt(2)
                val subPackets = content.drop(lengthBits + 1).take(length)
                var subPacketsRemained = subPackets
                val packets = mutableListOf<Packet>()
                while (subPacketsRemained.isNotEmpty()) {
                    val packet = parse(subPacketsRemained)
                    packets.add(packet.first)
                    subPacketsRemained = packet.second
                }

                return Pair(Operator(version, type, packets), content.drop(lengthBits + 1).drop(length))
            } else {
                val lengthBits = 11
                val amount = content.drop(1).take(lengthBits).toInt(2)
                val subPackets = content.drop(lengthBits + 1)

                var subPacketsRemained = subPackets
                val packets = mutableListOf<Packet>()
                repeat(amount) {
                    val packet = parse(subPacketsRemained)
                    packets.add(packet.first)
                    subPacketsRemained = packet.second
                }

                return Pair(Operator(version, type, packets), subPacketsRemained)
            }

        }

        private fun parseLiteral(version: Long, type: Int, literalGroups: String): Pair<Literal, String> {
            val groups = literalGroups.chunked(5).let { chunked ->
                val oneGroups = chunked.takeWhile { it.first() == '1' }
                val last = chunked.drop(oneGroups.size).first()
                oneGroups + last
            }

            val literalBinary = groups.joinToString(separator = "") { it.drop(1) }
            val groupsLength = groups.sumOf { it.length }
            return Pair(Literal(version, type, literalBinary.toLong(2)), literalGroups.drop(groupsLength))
        }

    }

    abstract class Packet(open val version: Long, open val type: Int) {
        abstract fun sumVersion(): Long
        abstract fun exec(): Long
    }

    data class Operator(override val version: Long, override val type: Int, val subPackets: List<Packet>) :
        Packet(version, type) {
        override fun sumVersion(): Long = version + subPackets.sumOf { it.sumVersion() }
        override fun exec(): Long = when (type) {
            0 -> subPackets.sumOf { it.exec() }
            1 -> subPackets.map { it.exec() }.reduce { a, b -> a * b }
            2 -> subPackets.minOf { it.exec() }
            3 -> subPackets.maxOf { it.exec() }
            5 -> if (subPackets[0].exec() > subPackets[1].exec()) 1 else 0
            6 -> if (subPackets[0].exec() < subPackets[1].exec()) 1 else 0
            7 -> if (subPackets[0].exec() == subPackets[1].exec()) 1 else 0
            else -> error("packet of type: $type unexpected")
        }
    }

    data class Literal(override val version: Long, override val type: Int, val value: Long) : Packet(version, type) {
        override fun sumVersion(): Long = version
        override fun exec(): Long = value
    }

    fun part1(input: String): Long = PacketParser.parse(input.hexToBinary()).first.sumVersion()

    fun part2(input: String): Long = PacketParser.parse(input.hexToBinary()).first.exec()

}
