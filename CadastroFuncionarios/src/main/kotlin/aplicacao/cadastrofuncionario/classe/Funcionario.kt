package aplicacao.cadastrofuncionario.classe

//Essa aplicação deve possibilitar o cadastro de um funcionário, solicitando o nome, o cargo e o salário deste.
class Funcionario {
    private var index: Int
    private var nome: String
    private var cargo: String
    private var salario: Double

    constructor(nome: String, cargo: String, salario: Double){
        this.nome = nome
        this.cargo = cargo
        this.salario = salario
        this.index = 0
    }

    constructor(index: Int, nome: String, cargo: String, salario: Double){
        this.index = index
        this.nome = nome
        this.cargo = cargo
        this.salario = salario
        this.index = index
    }

    fun getIndex(): Int {
        return index
    }

    fun getNome(): String {
        return nome
    }

    fun getCargo(): String {
        return cargo
    }

    fun getSalario(): Double {
        return salario
    }

    override fun toString(): String {
        return "$nome,$cargo,$salario"
    }
}//class Funcionario