package decoding


data class Node(
    val letter: String,
    val probability: Double,
    var code: Array<Boolean>
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        if (this.probability > other.probability) return 1
        else if (this.probability == other.probability) return 0
        else return -1
    }
}