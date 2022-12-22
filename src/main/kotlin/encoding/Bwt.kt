package encoding

fun main() {}

data class BwtResult(val string: String, val index: Int) {

    override fun toString(): String {
        return "\'$string\' $index"
    }
}


fun String.bwTransform(): BwtResult {
    val rotations = mutableListOf<String>()
    for (i in indices) {
        rotations.add(rotate(i))
    }
//    println(rotations)
    rotations.sort()
    val idx = rotations.indexOf(this)
    val res = rotations.let {
        var res = ""
        for (s in it) {
            res += s.last()
        }
        res
    }

    return BwtResult(res, idx)
}

fun String.rotate(n: Int) = drop(n) + take(n)