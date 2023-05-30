package aplicacao.cadastrofuncionario.programa

import aplicacao.cadastrofuncionario.classe.Funcionario
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import kotlin.system.exitProcess

fun main(){
    menu()
}

fun menu(){
    val sc = Scanner(System.`in`)
    var filePath = "arquivo\\BancoDeDados.csv"
    var opcao = 1;

    var file = File(filePath)
    inicializaArquivo(file)

    while(opcao != 0){
        print("\n----- Menu -----")
        print("\n1 - Cadastrar Funcionário.")
        print("\n2 - Alterar Funcionário.")
        print("\n3 - Excluir Funcionário.")
        print("\n4 - Listar Funcionários.")
        print("\n0 - Sair.")
        print("\nEscolha uma opção: ")
        opcao = sc.nextInt()

        when(opcao){
            1 -> cadastraFuncionario(file)
            2 -> alteraFuncionario(file)
            3 -> removeFuncionario(file)
            4 -> listaFuncionario(file)
            0 ->{
                println("\nPrograma encerrado...")
                exitProcess(0)
            }
            else -> println("\nValor Inválido!")
        }
    }
}

fun inicializaArquivo(file: File){

    if(file.length() > 1){
        val sc = Scanner(System.`in`)
        print("\nO banco de dados possui dados armazenados, deseja limpa-ló? \n1 - Sim \n2 - Não \nOpção: ")
        var opcao = sc.nextInt()

        if(opcao == 1){
            val writer = FileWriter(file)

            writer.write("Nome do Funcionário,Cargo do Funcionário,Salário\n")
            writer.close()
        } else{
            return
        }
    }else{
        val writer = FileWriter(file)

        writer.write("Nome do Funcionário,Cargo do Funcionário,Salário\n")
        writer.close()
    }
}

fun cadastraFuncionario(file: File){
    val sc = Scanner(System.`in`)

    val reader = FileReader(file)

    val lines = reader.readLines().toMutableList()
    reader.close()

    print("\n\n----- Cadastro do Novo Funcionário -----\n")
    print("\nInforme o nome do funcionário: ")
    val nome = sc.nextLine()
    print("Informe o cargo desse funcionário: ")
    val cargo = sc.nextLine()
    print("Informe o salário desse funcionário: ")
    val salario = sc.nextDouble()

    val novoFuncionario = Funcionario(nome, cargo, salario)

    lines.add(novoFuncionario.toString())

    val writer = FileWriter(file)
    writer.write(lines.joinToString("\n"))
    writer.close()

    println("\nFuncionário cadastrado com sucesso!")
}

fun alteraFuncionario(file: File){
    val sc = Scanner(System.`in`)
    val reader = FileReader(file)

    print("\n\n----- Alterar Funcionário -----\n")
    print("\nInforme o nome do funcionário que deseja alterar: ")
    val nome = sc.nextLine()

    val lines = reader.readLines().toMutableList()
    reader.close()

    var index = -1
    for ((i, line) in lines.withIndex()) {
        val fields = line.split(",")
        if (fields[0] == nome) {
            index = i
            break
        }
    }

    if (index == -1) {
        println("\nEste funcionário não foi encontrado.")
        menu()
    }

    print("\nInforme um novo cargo para $nome: ")
    val cargo = sc.nextLine()

    print("Informe um novo salário para $nome: ")
    val salario = sc.nextDouble()

    val funcionario = Funcionario(nome, cargo, salario)

    lines[index] = funcionario.toString()

    val writer = FileWriter(file)
    writer.write(lines.joinToString("\n"))
    writer.close()

    println("\nFuncionário alterado com sucesso!")
}

fun removeFuncionario(file: File){
    val sc = Scanner(System.`in`)
    val reader = FileReader(file)

    print("\n\n----- Remover Funcionário -----\n")
    print("\nInforme o nome do funcionário que deseja remover: ")
    val nome = sc.nextLine()

    val lines = reader.readLines().toMutableList()
    reader.close()

    var index = -1
    for ((i, line) in lines.withIndex()) {
        val fields = line.split(",")
        if (fields[0] == nome) {
            index = i
            break
        }
    }

    if (index == -1) {
        println("\nEste funcionário não foi encontrado.")
        menu()
    }

    lines.removeAt(index)

    val writer = FileWriter(file)
    writer.write(lines.joinToString("\n"))
    writer.close()

    println("\nFuncionário removido com sucesso!")
}

fun listaFuncionario(file: File){
    val reader = FileReader(file)

    val lines = reader.readLines()
    reader.close()

    if (lines.size < 2) {
        println("Nenhum funcionário foi cadastrado.")
        return
    }

    var id = 1
    for (line in lines) {
        val fields = line.split(",")

        val nome = fields[0]
        val cargo = fields[1]
        val salario = fields[2]
        if(fields[0] == "Nome do Funcionário")
            println("ID \t\t | \t $nome \t | \t $cargo \t | \t $salario")
        else {
            println("$id \t\t | \t $nome \t\t | \t\t\t $cargo \t\t\t | \t\t\t R$$salario")
            id++
        }
    }
}