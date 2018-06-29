package test


class u {
    fun a() {
        val f = Test.a()
        val b = StringBuilder()
        f.forEach { s -> b.append(s.plus(1)) }
        print(b)
//        val t = b.split(".").filter { s -> b.split(".").indexOf(s) % 2 != 0 }.map { x -> x.toInt(2) }
//        print(t)
    }
}

fun main(args: Array<String>) {
    u().a()
}


