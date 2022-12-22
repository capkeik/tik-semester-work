package encoding

import utils.IOTool

fun main(args: Array<String>) {
    val alphabet = IOTool.readAlphabet()
    val transformed = IOTool.readTargetString().bwTransform()
//    println(transformed)
    val result = MTFencode(transformed.string, alphabet.toMutableList())
    for (code in result) {
        print("$code ")
    }
    println()
    println(transformed.index)
}