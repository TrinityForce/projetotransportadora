package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class FuncionarioSingleton {
    
    private static Funcionario funcionario;

    /**
     *
     */
    public FuncionarioSingleton() {
    }

    /**
     *
     * @param funcionario
     */
    public static void setFuncionario(Funcionario funcionario) {
        FuncionarioSingleton.funcionario = funcionario;
    }

    /**
     *
     * @return
     */
    public static Funcionario getFuncionario() {
        return funcionario;
    }
}
