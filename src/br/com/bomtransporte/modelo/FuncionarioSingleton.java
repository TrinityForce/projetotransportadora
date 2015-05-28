package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class FuncionarioSingleton {
    
    private static Funcionario funcionario;

    public FuncionarioSingleton() {
    }

    public static void setFuncionario(Funcionario funcionario) {
        FuncionarioSingleton.funcionario = funcionario;
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }
}
