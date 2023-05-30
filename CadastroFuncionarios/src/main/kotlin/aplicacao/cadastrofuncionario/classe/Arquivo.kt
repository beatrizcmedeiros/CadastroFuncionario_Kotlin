package aplicacao.cadastrofuncionario.classe

import java.io.File
import java.io.FileReader
import java.io.FileWriter

class Arquivo(private val file: File) {

    fun inicializaArquivo() {
        val writer = FileWriter(file)
        writer.write("Nome do Funcionário,Cargo do Funcionário,Salário\n")
        writer.close()
    }

    fun verificaArquivoVazio() : Boolean {
        return file.length() < 1
    }

    fun adicionarLinhaArquivo(line: String) {
        val reader = FileReader(file)
        val lines = reader.readLines().toMutableList()
        reader.close()

        lines.add(line)

        val writer = FileWriter(file)
        writer.write(lines.joinToString("\n"))
        writer.close()
    }

    fun alterarLinhaArquivo(index : Int, line: String) {
        val reader = FileReader(file)
        val lines = reader.readLines().toMutableList()
        reader.close()

        lines[index] = line

        val writer = FileWriter(file)
        writer.write(lines.joinToString("\n"))
        writer.close()
    }

    fun removerLinhaArquivo(index : Int) {
        val reader = FileReader(file)
        val lines = reader.readLines().toMutableList()
        reader.close()

        lines.removeAt(index)

        val writer = FileWriter(file)
        writer.write(lines.joinToString("\n"))
        writer.close()
    }

    fun lerTotasAsLinhasArquivo(): List<String> {
        if (file.exists()) {
            val reader = FileReader(file)
            val lines = reader.readLines()
            reader.close()
            return lines
        }
        return emptyList()
    }

    fun pesquisaLinhaPorIdentificador(ientificador: String): Int{
        val lines = lerTotasAsLinhasArquivo()

        for ((i, line) in lines.withIndex()) {
            val fields = line.split(",")
            if (fields[0] == ientificador) {
                return i
            }
        }
        return -1
    }

    fun imprimirLinhasArquivo(lines: List<String>){
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

}//class Arquivo