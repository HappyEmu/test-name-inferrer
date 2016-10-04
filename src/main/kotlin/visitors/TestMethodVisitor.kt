package visitors

import com.github.javaparser.ast.body.AnnotationDeclaration
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.visitor.VoidVisitorAdapter

class TestMethodVisitor : VoidVisitorAdapter<Any>() {

    val assertions = arrayListOf<Expression>()

    override fun visit(n: MethodCallExpr?, arg: Any?) {

        if (n != null && n.name.startsWith("assert")) {
            assertions.add(parseExpression(n.args))
        }

        super.visit(n, arg)
    }

    override fun visit(n: VariableDeclarationExpr?, arg: Any?) {
        println(n)
        super.visit(n, arg)
    }

    fun parseExpression(args: List<Expression>) : Expression {
        if (args.size > 1)
            return args[1]
        else
            return args[0]
    }

}