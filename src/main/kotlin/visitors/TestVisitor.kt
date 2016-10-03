package visitors

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.visitor.VoidVisitorAdapter

class TestVisitor : VoidVisitorAdapter<String>() {

    var testName = ""
    val methods = arrayListOf<MethodDeclaration>()

    override fun visit(n: ClassOrInterfaceDeclaration?, arg: String?) {
        testName = n?.name ?: ""
        super.visit(n, arg)
    }

    override fun visit(n: MethodDeclaration?, arg: String?) {
        n?.let { methods.add(it) }
        super.visit(n, arg)
    }
}