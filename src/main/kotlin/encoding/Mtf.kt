package encoding

fun MTFencode(s: String, symbols: MutableList<String>): IntArray {
    if (s.isEmpty()) return intArrayOf()
    val result = IntArray(s.length)
    for ((i, c) in s.withIndex()) {
        val idx = symbols.indexOf(c.toString())
        if (idx == -1)
            throw IllegalArgumentException("$s contains a non-alphabetic character")
        result[i] = idx
        symbols.remove(c.toString())
        symbols.add(0, c.toString())
    }
    return result
}
