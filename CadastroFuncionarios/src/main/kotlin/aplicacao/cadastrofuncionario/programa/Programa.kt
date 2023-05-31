package aplicacao.cadastrofuncionario.programa

import aplicacao.cadastrofuncionario.classe.Arquivo
import aplicacao.cadastrofuncionario.classe.Funcionario
import java.io.File
import java.util.*
import kotlin.system.exitProcess

fun main(){
    var filePath = "arquivo\\BancoDeDados.csv"
    var file = Arquivo(File(filePath))
    inicializaPrograma(file)

    menu(file)
}

fun menu(file: Arquivo) {
    val sc = Scanner(System.`in`)
    var opcao = 1;

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

fun inicializaPrograma(file: Arquivo) {
    val sc = Scanner(System.`in`)

    if(file.verificaArquivoVazio()){
        file.inicializaArquivo()
    }else{
        print("\nO banco de dados possui dados armazenados, deseja limpa-ló? \n1 - Sim \n2 - Não \nOpção: ")
        var opcao = sc.nextInt()

        if(opcao == 1)
            file.inicializaArquivo()
    }
}

fun cadastraFuncionario(file: Arquivo){
    val sc = Scanner(System.`in`)

    print("\n\n----- Cadastro do Novo Funcionário -----\n")
    print("\nInforme o nome do funcionário: ")
    val nome = sc.nextLine()
    print("Informe o cargo desse funcionário: ")
    val cargo = sc.nextLine()
    print("Informe o salário desse funcionário: ")
    val salario = sc.nextDouble()

    val novoFuncionario = Funcionario(nome, cargo, salario)

    file.adicionarLinhaArquivo(novoFuncionario.toString())

    println("\nFuncionário cadastrado com sucesso!")
}

fun alteraFuncionario(file: Arquivo){
    val sc = Scanner(System.`in`)

    print("\n\n----- Alterar Funcionário -----\n")
    print("\nInforme o nome do funcionário que deseja alterar: ")
    val nome = sc.nextLine()

    var index = file.pesquisaLinhaPorIdentificador(nome)
    if (index == -1) {
        println("\nEste funcionário não foi encontrado.")
        menu(file)
    }

    val repeticoes = file.verificaRepeticaoIdentificador(nome)
    if(repeticoes.size > 1){
        print("\nExiste mais de um funcionário com esse nome! \n")
        for(r in repeticoes){
            if (r != null) {
                print("\nID:${r.getIndex()} \tNome: ${r.getNome()} \tCargo: ${r.getCargo()} \tSalário: R$${r.getSalario()}")
            }
        }
        print("\n\nInforme o ID de qual deseja alterar: ")
        index = sc.nextInt()
    }
    sc.nextLine()

    print("\nInforme um novo cargo para $nome: ")
    val cargo = sc.nextLine()

    print("Informe um novo salário para $nome: ")
    val salario = sc.nextDouble()

    val funcionario = Funcionario(nome, cargo, salario)
    file.alterarLinhaArquivo(index, funcionario.toString())

    println("\nFuncionário alterado com sucesso!")
}

fun removeFuncionario(file: Arquivo){
    val sc = Scanner(System.`in`)

    print("\n\n----- Remover Funcionário -----\n")
    print("\nInforme o nome do funcionário que deseja remover: ")
    val nome = sc.nextLine()

    var index = file.pesquisaLinhaPorIdentificador(nome)
    if (index == -1) {
        println("\nEste funcionário não foi encontrado.")
        menu(file)
    }

    val repeticoes = file.verificaRepeticaoIdentificador(nome)
    if(repeticoes.size > 1){
        print("\nExiste mais de um funcionário com esse nome! \n")
        for(r in repeticoes){
            if (r != null) {
                print("\nID:${r.getIndex()} \tNome: ${r.getNome()} \tCargo: ${r.getCargo()} \tSalário: R$${r.getSalario()}")
            }
        }
        print("\n\nInforme o ID de qual deseja apagar: ")
        index = sc.nextInt()
    }

    file.removerLinhaArquivo(index)

    println("\nFuncionário removido com sucesso!")
}

fun listaFuncionario(file: Arquivo){
    val lines = file.lerTotasAsLinhasArquivo()
    if (lines.size < 2) {
        println("Nenhum funcionário foi cadastrado.")
        return
    }

    file.imprimirLinhasArquivo(lines)
}