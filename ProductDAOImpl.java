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

import in.co.online.food.delivery.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAOInt {

	private static Logger log = Logger.getLogger(ProductDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ProductDTO dto) {
		log.info("ProductDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ProductDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ProductDTO dto) {
		log.info("ProductDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("ProductDAOImpl Delete method End");

	}

	@Override
	public ProductDTO findBypk(long pk) {
		log.info("ProductDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ProductDTO dto = (ProductDTO) session.get(ProductDTO.class, pk);
		log.info("ProductDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ProductDTO findByName(String name) {
		log.info("ProductDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ProductDTO.class);
		criteria.add(Restrictions.eq("name", name));
		ProductDTO dto = (ProductDTO) criteria.uniqueResult();
		log.info("ProductDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ProductDTO dto) {
		log.info("ProductDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ProductDAOImpl update method End");
	}

	@Override
	public List<ProductDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ProductDTO> list(int pageNo, int pageSize) {
		log.info("ProductDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ProductDTO> query = session.createQuery("from ProductDTO", ProductDTO.class);
		List<ProductDTO> list = query.getResultList();
		log.info("ProductDAOImpl List method End");
		return list;
	}

	@Override
	public List<ProductDTO> search(ProductDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ProductDTO> search(ProductDTO dto, int pageNo, int pageSize) {
		log.info("ProductDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ProductDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}

			if (dto.getCategoryId() > 0) {
				hql.append("and u.category = " + dto.getCategoryId());
			}

			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}

		}
		Query<ProductDTO> query = session.createQuery(hql.toString(), ProductDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ProductDTO> list = query.getResultList();
		log.info("ProductDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		ProductDTO person = (ProductDTO) session.get(ProductDTO.class, id);
		byte[] blob = person.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
