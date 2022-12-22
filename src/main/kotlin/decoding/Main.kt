package decoding

import utils.IOTool

fun main() {
    val (alph, props) = IOTool.readAlphabetFano()
    val fanoTool = FanoTool(alph, props)
    val target = IOTool.readTargetString()
    println()

    print(fanoTool.fanoDecode(target))
}