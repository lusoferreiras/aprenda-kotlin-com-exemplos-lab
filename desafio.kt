enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario (val id: Int, val nome: String)

data class ConteudoEducacional(val nome: String, val nivel: Nivel, var duracao: Int = 60)
data class Formacao(var nome: String, var nivel: Nivel, val conteudos: MutableList<ConteudoEducacional>) {

    val inscritos = mutableMapOf<Int, Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        addInscritos(usuarios.toList())
    }

    fun matricular(usuarios: List<Usuario>) {
        addInscritos(usuarios)
    }

    private fun addInscritos(usuarios: List<Usuario>) {
        for (usuario in usuarios) {
            inscritos.put(usuario.id, usuario)
        }
    }

    override fun toString(): String {
        return "$nome," +
                " $nivel" +"\n"+
                " $conteudos," +"\n"+
                " $inscritos"
    }
}

class DIO() {
    val formacoes: MutableSet<Formacao> = mutableSetOf()

    fun addFormacao(vararg formacao: Formacao) = formacoes.addAll(formacao)

    fun addFormacoes(formacoes: List<Formacao>) = this.formacoes.addAll(formacoes)

    override fun toString(): String {
        var description: String = "digitalinnovationone"+"\n"+"====================================================================================================="+"\n"
        for (formacao  in formacoes){"\n"
            description+="" + formacao+":"+"\n"+"=============================================================================================="+"\n"
        }
        return "\n"+description
    }
}

fun main() {
    val dio = DIO()

    val user1 = Usuario(1,"Luan")
    val user2: Usuario = Usuario(2,"Venilton")
    val user3 = Usuario(3,"Alberto")
    val user4 = Usuario(4,"Lúcia")

    val educacional1: ConteudoEducacional = ConteudoEducacional("Fluxo de Controle", Nivel.BASICO)
    val educacional2 = ConteudoEducacional("Tratamento de Exceptions", Nivel.INTERMEDIARIO, 45)
    val educacional3: ConteudoEducacional = ConteudoEducacional("POO", Nivel.INTERMEDIARIO, 250)
    val educacional4 = ConteudoEducacional("Coleções", Nivel.DIFICIL, 200)

    val listaConteudos1 = mutableListOf<ConteudoEducacional>(educacional1, educacional2)
    val listaConteudos2 = mutableListOf<ConteudoEducacional>(educacional3, educacional4)

    val formacao1: Formacao = Formacao("Dev Java Junior,"+"\nNivel:", Nivel.INTERMEDIARIO, listaConteudos1)
        .also { it.matricular(user1, user2) }

    val formacao2: Formacao = Formacao("Dev Python Junior,"+"\nNível:", Nivel.DIFICIL, listaConteudos2)
        .apply { matricular(user3, user4) }

    dio.addFormacao(formacao1, formacao2)

    println( dio.toString())
}
