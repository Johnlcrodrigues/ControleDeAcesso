package br.controledeacesso.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ConectaHibernateMySQL {

	public static void main(String[] args) {
		Session sessao = null;

		try {
			sessao = HibernateUtil.createSessionFactory().openSession();
			System.out.println("Conectou! Tabela gerada com sucesso!");
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sessao.close();
		}
	}
}
