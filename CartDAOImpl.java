package in.co.online.food.delivery.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.online.food.delivery.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAOInt {

	private static Logger log = Logger.getLogger(CartDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(CartDTO dto) {
		log.info("CartDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CartDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(CartDTO dto) {
		log.info("CartDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("CartDAOImpl Delete method End");

	}

	@Override
	public CartDTO findBypk(long pk) {
		log.info("CartDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		CartDTO dto = (CartDTO) session.get(CartDTO.class, pk);
		log.info("CartDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public CartDTO findByName(String name) {
		log.info("CartDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CartDTO.class);
		criteria.add(Restrictions.eq("name", name));
		CartDTO dto = (CartDTO) criteria.uniqueResult();
		log.info("CartDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(CartDTO dto) {
		log.info("CartDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CartDAOImpl update method End");
	}

	@Override
	public List<CartDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<CartDTO> list(int pageNo, int pageSize) {
		log.info("CartDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<CartDTO> query = session.createQuery("from CartDTO", CartDTO.class);
		List<CartDTO> list = query.getResultList();
		log.info("CartDAOImpl List method End");
		return list;
	}

	@Override
	public List<CartDTO> search(CartDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<CartDTO> search(CartDTO dto, int pageNo, int pageSize) {
		log.info("CartDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from CartDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			

		}
		Query<CartDTO> query = session.createQuery(hql.toString(), CartDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<CartDTO> list = query.getResultList();
		log.info("CartDAOImpl Search method End");
		return list;
	}

	

}
