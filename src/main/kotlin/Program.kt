import com.github.javaparser.JavaParser
import visitors.TestMethodVisitor
import visitors.TestVisitor
import java.io.FileInputStream

fun main(args: Array<String>) {

    // Parse Input & generate AST
    val input = FileInputStream("testdata/CustomTests.java")
    val compilationUnit = JavaParser.parse(input)

    // Visit AST
    val visitor = TestVisitor()
    visitor.visit(compilationUnit, null)

    println("Test Name is: ${visitor.testName}")
    println("Number of Test methods: ${visitor.methods.count()}")

    visitor.methods.forEach { method ->
        val v = TestMethodVisitor()
        v.visit(method, null)

        if (v.assertions.any())
            println("Assertions: ${v.assertions}")
    }

}
