package dk.lundogbendsen.examples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;

public class ExploreMetamodel {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("StandaloneJpaTestPersistenceUnit");

		EntityManager em = emf.createEntityManager();

		Metamodel metamodel = em.getMetamodel();
		System.out.println("Prints info about meta model:");

		Set<EntityType<?>> entities = metamodel.getEntities();
		for (EntityType<?> entityType : entities) {
			System.out.println();
			System.out.println("----------------------");
			System.out.println();

			Class<?> entityClass = entityType.getJavaType();
			System.out.println("package " + entityClass.getPackage().getName() + ";");
			System.out.println();

			System.out.println("@Entity");

			System.out.println("public class " + entityClass.getSimpleName() + " {");

			Set<? extends Attribute<?, ?>> declaredAttributes = entityType.getDeclaredAttributes();
			for (Attribute<?, ?> attribute : declaredAttributes) {

				System.out.println();

				Member fieldMember = attribute.getJavaMember();
				if (fieldMember instanceof Field) {
					Annotation[] annotations = ((Field) fieldMember).getAnnotations();
					for (Annotation annotation : annotations) {
						System.out.println("\t@" + annotation.annotationType().getSimpleName());
					}
				}

				System.out.println("\t" + attribute.getJavaType().getSimpleName() + " " + attribute.getName() + ";");
			}

			System.out.println("}");
		}

		em.close();
	}
}
