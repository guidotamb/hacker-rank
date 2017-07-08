/**
 * Created by guidotamborindeguy on 7/6/17.
 */

fun main(args: Array<String>) {
    val countCases: Int = Integer.parseInt(args [0])
    (1..countCases)
            .map { recursive(Integer.parseInt(args[it])) }
            .forEach { println(it) }
}

fun twoOperations(input: Int): Int {
    var result : Int = 0
    var variable : Int = input
    while (variable > 0) {
        if (variable % 2 == 0) {
            variable /= 2
        } else {
            variable --
        }
        result ++
    }
    return result
}

fun recursive(input: Int): Int {
    when {
        (input == 1) -> return 1
        (input % 2 == 0) -> return 1 + recursive(input / 2)
        else -> return 1 + recursive(input -1)
    }
}