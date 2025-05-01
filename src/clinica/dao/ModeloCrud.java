package clinica.dao;
import java.util.List;

public interface ModeloCrud<T> {
    
    void incluir(T obj) throws Exception;

    void alterar(T obj) throws Exception;

    void excluir(T obj) throws Exception;

    T buscarUm(Integer id) throws Exception;

    List<T> buscarTodos() throws Exception;
}
