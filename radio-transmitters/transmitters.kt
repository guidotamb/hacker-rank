import java.util.*

/**
 * Created by guidotamborindeguy on 7/7/17.
 */
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val k = input.nextInt()
    val x = IntArray(n)
    for (x_i in 0..n - 1) {
        x[x_i] = input.nextInt()
    }
    quicksort(x, 0, x.size-1)
    transmitters(n, k, x)
}

fun quicksort(x: IntArray, low: Int, high: Int) {
    var i = low
    var j = high
    val pivot = x[low + (high - low) / 2]

    while (i <= j) {
        while (x[i] < pivot) {
            i++
        }
        while (x[j] > pivot) {
            j--
        }
        if (i <= j) {
            exchange(x, i, j)
            i++
            j--
        }
    }
    if (low < j)
        quicksort(x, low, j)
    if (i < high)
        quicksort(x, i, high)
}

private fun exchange(numbers: IntArray, i: Int, j: Int) {
    val temp = numbers[i]
    numbers[i] = numbers[j]
    numbers[j] = temp
}

fun transmitters(n: Int, k: Int, x: IntArray) {
    var pivot : Int = 0
    // avanzo K, pongo transmitter, avanzo k = cubro rango (transmiter-k, transmiter+k)
    // sumo 1 y sigo con el siguiente rango
    var transmitters : MutableList<Int> = mutableListOf()
    while (pivot.inRange(n-1)) {
        pivot = avanzoK(pivot, x, k)
        transmitters.add(x[pivot])
        pivot = avanzoK(pivot, x, k) + 1
    }
    if (pivot >= n-1) {
        if (!(n-1).covered(x, k,transmitters)) {
            transmitters.add(x[n-1])
        }
    }
    println(transmitters.size)
}

fun avanzoK(pivot: Int, x: IntArray, k: Int): Int {
    var result : Int = pivot
    while (result + 1 <= x.size - 1) {
        if (x[result + 1] - x[pivot] <= k) {
            result++
        } else {
            return result;
        }
    }
    return x.size - 1
}

private fun  Int.covered(x: IntArray, k:Int, transmitters: MutableList<Int>): Boolean {
    return (0..transmitters.size - 1).any { transmitters[it] + k >= x[this] }
}

private fun Int.inRange(n: Int) : Boolean{
    return this in 0..(n - 1)
}



