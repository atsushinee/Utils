@file:JvmName("Lichun")

package kt

import org.omg.CORBA.portable.Delegate
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.io.BufferedReader
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import kotlin.collections.ArrayList
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun max(a: Int, b: Int) = if (a > b) a else b

class Rectangle(private val height: Int, private val width: Int) {
    val isSquare: Boolean
        get() = height == width
}

enum class Color(
        val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0);

    fun rgb() = (r * 256 + g) * 256 + b
}

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr


sealed class ExprSealed {
    class Num(val value: Int) : ExprSealed()
    class Sum(val left: ExprSealed, val right: ExprSealed) : ExprSealed()
}

fun eval(e: ExprSealed): Int =
        when (e) {
            is ExprSealed.Num ->
                e.value
            is ExprSealed.Sum ->
                eval(e.left) + eval(e.right)
        }

@JvmOverloads
fun <T> joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val sb = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) sb.append(separator)
        sb.append(element)
    }
    sb.append(postfix)
    return sb.toString()
}

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }

infix fun String.fuck(other: String) = this + other

interface Clickable {
    fun click()
    fun showOff() = println("Clickable showOff!")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("Focusable showOff!");
}

open class Button : Clickable, Focusable {
    override fun showOff() {
        super<Focusable>.showOff()
        super<Clickable>.showOff()
    }

    override fun click() = println("A button clicked!")

    open fun changeColor(color: Color) = println("Button color change to ${color.rgb()}")
}

class MyButton : Button() {
    override fun changeColor(color: Color) = println("MyButton color change to ${color.rgb()}")
}

abstract class Animated {
    abstract fun animate()
}

fun String?.fuck(): String {
    return this ?: "you're fuck null val"
}

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

interface Processor<T> {
    fun process(): T
}

class IntResultProcessor : Processor<Int> {
    override fun process(): Int {
        return 1
    }
}

class NoResultProcessor : Processor<Unit> {
    override fun process() {

    }
}


fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)
        } catch (e: NumberFormatException) {
            result.add(null)
        }
    }
    return result
}

fun main(args: Array<String>) {
    val ret = max(20, 44)

    println(ret)
    val rectangle = Rectangle(30, 30)
    println(rectangle.isSquare)

    println(eval(ExprSealed.Sum(ExprSealed.Sum(ExprSealed.Num(1), ExprSealed.Num(2)), ExprSealed.Num(4))))

    for (c in 'A'..'F') {
        println(c)
    }
    val set = hashSetOf(1, 3, 7)
    val list = arrayListOf("a", "b", 1)
    val map = hashMapOf(1 to "one", 2 to "two")
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    println("1" fuck "2" fuck "3" fuck "4")

    val button = Button()
    val myButton = MyButton()

    button.showOff()
    myButton.changeColor(Color.ORANGE)

    val people = listOf(Person("Alice", 19), Person("Bob", 21), Person("Lichun", 21))
    val names = people.joinToString(separator = " ") { p -> p.name }
    println(names)
    val getAge = Person::age

    people.forEach {
        println("${it.name}:${it.age}")
        println("${getAge.get(it)}")
    }

    val list1 = listOf(1, 2, 3, 4)
    println(list1.filter {
        it % 2 == 0
    })

    println(list1.find { it > 3 })

    val groupBy = people.groupBy(getAge)
    println(groupBy)

    class Book(val title: String, val authors: List<String>)

    val books = listOf(Book("C", listOf("Lichun", "Haha")),
            Book("JAVA", listOf("HeiHei", "Lichun")),
            Book("JAVA", listOf("Haha", "HeiHei"))
    )
    println(books.flatMap { it.authors }.toSet())

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.toList())


    var a: String? = null
    println(a?.plus("v"))
    println(a ?: "1".plus("v"))
    println(a.fuck())
    a = "999"
    println(a.fuck())
    println(1 as? Int)

    val x = 1
    val list3 = listOf(1L, 2L)
    println(x.toLong() in list3)
    a ?: fail("ee")
    val point1 = Point(1, 2)
    val point2 = Point(3, 2)
    val point3 = point1 + point2
    println(point3)

    val person = Person("Cat", 32, 2000)
    val listener = PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    }
    person.addPropertyChangeListener(listener)
    person.age = 100
    person.salary = 399

    val person2 = Person2("Dog", 200, 3000)
    person2.addPropertyChangeListener(listener)
    person2.age = 180
    person2.salary = 200
    println(person2.age)
    println(person2.salary)

    with(Person3(201, 303300)) {
        addPropertyChangeListener(listener)
        age = 222
        salary = 333
        println(age)
        println(salary)
    }

    with(Person4(2022, 303311)) {
        addPropertyChangeListener(listener)
        age = 111
        salary = 3323
        println(age)
        println(salary)
    }

    var caneturnNull: ((Int, Int) -> Int?)? = null
    caneturnNull = fun(i: Int, i2: Int): Int? {
        return 0
    }

    val averageWindowsDuration = log
            .filter { it.os == OS.WINDOWS }
            .map(SiteVisit::duration)
            .average()
    println(averageWindowsDuration)

    println(log.averageDurationFor(OS.WINDOWS))
    println(log.averageDurationFor(OS.MAC))

    val averageMobileDuration = log
            .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
            .map(SiteVisit::duration)
            .average()
    println(averageMobileDuration)
    println(log.averageDurationFor { it.os in setOf(OS.IOS, OS.ANDROID) })

    lookForAlice(listOf(Person("Alice", 11, 22)))

    fun buildString(builderAction: StringBuilder.() -> Unit): String = StringBuilder().apply(builderAction).toString()

    buildString {
        append("hell")
        append("123")
    }
    executor.execute {
        println("1")
    }
    point1("invoke it")


}

val executor = ThreadPoolExecutor(13, 13, 60L, TimeUnit.SECONDS, ArrayBlockingQueue(13))

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun invoke(s: String) {
        println(s)
    }
}

inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)
    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person(val name: String, age: Int, salary: Int = 0) : PropertyChangeAware() {
    var age: Int = age
        set(value) {
            val oldValue = field
            field = value
            changeSupport.firePropertyChange("age", oldValue, value)
        }
    var salary: Int = salary
        set(value) {
            val oldValue = field
            field = value
            changeSupport.firePropertyChange("salary", oldValue, value)
        }
}

class ObservableProperty(private val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    fun getValue(): Int = propValue
    fun setValue(value: Int) {
        val oldValue = propValue
        propValue = value
        changeSupport.firePropertyChange(propName, oldValue, value)
    }
}

class Person2(val name: String, age: Int, salary: Int = 0) : PropertyChangeAware() {
    private val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) = _age.setValue(value)

    private val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) = _salary.setValue(value)
}

class ObservableProperty2(var propValue: Int, private val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person3, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: Person3, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person3(age: Int, salary: Int = 0) : PropertyChangeAware() {
    var age: Int by ObservableProperty2(age, changeSupport)
    var salary: Int by ObservableProperty2(salary, changeSupport)
}

class Person4(age: Int, salary: Int = 0) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}


data class SiteVisit(
        val path: String,
        val duration: Double,
        val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

val log = listOf(
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
)

fun List<SiteVisit>.averageDurationFor(os: OS) = filter { it.os == os }.map(SiteVisit::duration).average()

fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) = filter(predicate).map(SiteVisit::duration).average()

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("find Alice!")
            return
        }
    }
    println("forEachEnd")
}

inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> loadService(): ServiceLoader<T>? {
    return ServiceLoader.load(T::class.java)
}

open class Animal {
    fun feed() {
    }
}

