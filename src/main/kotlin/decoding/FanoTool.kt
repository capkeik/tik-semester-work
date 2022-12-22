package decoding

import kotlin.math.abs


class FanoTool(
    val alphabet: Array<String>,
    val probs: Array<String>
) {
    fun fanoEncode(): Map<String, String> {
        var nodes = initNodes()
        nodes.sort()
        nodes = fano(nodes.reversedArray())
        val dic = mutableMapOf<String, String>()
        for (node in nodes) {
            dic[node.letter] = toStr(node.code)
        }
        return dic
    }

    fun fanoDecode(target: String): String {
        var nodes = initNodes()
        nodes.sort()
        nodes = fano(nodes.reversedArray())
        val dic = mutableMapOf<String, String>()
        for (node in nodes) {
            dic[toStr(node.code)] = node.letter
        }
        var res = ""
        var buffer = ""
        for (i in target.indices) {
            buffer += target[i]
            if (dic[buffer] != null) {
                res += dic[buffer]
                buffer = ""
            }
        }
        return res
    }

    private fun fano(nodes: Array<Node>) : Array<Node>{
        if (nodes.size == 1) return nodes
        var (left, right) = divideIntoSimilarSums(nodes)
        for (node in left) node.code += false
        for (node in right) node.code += true
        left = fano(left)
        right = fano(right)
        return left + right
    }

    private fun initNodes(): Array<Node> {
        val result = mutableListOf<Node>()
        if (alphabet.size != probs.size) throw IllegalArgumentException("Кол-во букв и вероятностей должны совпадать")
        for (i in alphabet.indices) {
            result.add(
                Node(
                    letter = alphabet[i],
                    probability = probs[i].toDouble(),
                    Array(0) { false }
                )
            )
        }
        return result.toTypedArray()
    }

    private fun divideIntoSimilarSums(nodes: Array<Node>): Pair<Array<Node>, Array<Node>> {
        val totalSum: Double = nodes.let {
            var res = 0.0
            for (i in it) {
                res += i.probability
            }
            res
        }
        var diff = totalSum
        var curDiff: Double = 0.0
        var rightSum = totalSum
        var leftSum: Double = 0.0
        var bound = 0
        for (i in nodes.indices) {
            leftSum += nodes[i].probability
            rightSum -= nodes[i].probability
            curDiff = abs(rightSum - leftSum)
            if (curDiff < diff) {
                diff = curDiff
                bound = i + 1
            }
        }
        return Pair(
            nodes.sliceArray(0 until bound),
            nodes.sliceArray(bound until nodes.size))
    }

    private fun toStr(array: Array<Boolean>) : String {
        if (array.size == 1) return if (array[0]) "1" else "0"
        return "${if (array[0]) 1 else 0}${toStr(array.sliceArray(1 until array.size))}"
    }
}