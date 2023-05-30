package aplicacao.cadastrofuncionario.classe

//Essa aplicação deve possibilitar o cadastro de um funcionário, solicitando o nome, o cargo e o salário deste.
class Funcionario {
    private var nome: String
    private var cargo: String
    private var salario: Double

    constructor(nome: String, cargo: String, salario: Double){
        this.nome = nome
        this.cargo = cargo
        this.salario = salario
    }

    override fun toString(): String {
        return "$nome,$cargo,$salario"
    }
}//class Funcionario