package teste.teste;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.projeto0001.model.ClienteFornecedor;
import com.projeto0001.model.Grupo;
import com.projeto0001.model.TipoPessoa;
import com.projeto0001.model.Usuario;

// método para criar banco de dados quando o mesmo for excluído.
// lembrar: Alterar o persistence.xml
public class CriarBanco {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projeto0001PU");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Usuário salvo com sucesso!");
		em.close();

	}

}
